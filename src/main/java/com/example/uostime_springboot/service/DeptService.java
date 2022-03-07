package com.example.uostime_springboot.service;

import com.example.uostime_springboot.domain.Dept;
import com.example.uostime_springboot.dto.DeptDTO;
import com.example.uostime_springboot.dto.wise.ResponseDTO;
import com.example.uostime_springboot.repository.DeptRepository;
import com.example.uostime_springboot.util.DeptFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
@PropertySource("classpath:wiseapi.properties")
public class DeptService {
	@Value("${wise.apikey}")
	private String wiseApiKey;

	private final DeptRepository deptRepository;
	private final DeptFilter deptFilter;

	@Autowired
	public DeptService(DeptRepository deptRepository, DeptFilter deptFilter){
		this.deptRepository = deptRepository;
		this.deptFilter = deptFilter;
	}

	public List<Dept> loadDept() throws ParserConfigurationException, IOException, SAXException {
		List<Dept> deptList = new ArrayList<>();
		String wiseApiUri = "https://wise.uos.ac.kr/uosdoc/api.ApiApiDeptList.oapi";
		UriComponents uri = UriComponentsBuilder.fromHttpUrl(wiseApiUri)
				.queryParam("apiKey", this.wiseApiKey)
				.build(false);

		Document deptInfo = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder().parse(uri.toUriString());
		deptInfo.getDocumentElement().normalize();

		Element root = deptInfo.getDocumentElement();
		Element element = (Element) root.getElementsByTagName("subDeptList").item(0);
		NodeList nodeList = element.getElementsByTagName("list");

		for(int i = 0; i < nodeList.getLength(); i++){
			Element item = (Element) nodeList.item(i);
			DeptDTO deptDTO = new DeptDTO();
			NodeList childNodes = item.getChildNodes();
			for(int j = 0; j < childNodes.getLength(); j++){
				Node node = childNodes.item(j);
				if (node.getChildNodes().item(0) == null){
					continue;
				}
				String value = node.getChildNodes().item(0).getNodeValue();
				switch (node.getNodeName()){
					case "prt_ord":
						deptDTO.setPtr_ord(value);
						break;
					case "dept":
						deptDTO.setDept(value);
						break;
					case "dept_code_nm":
						deptDTO.setDept_code_nm(value);
						break;
					case "dept_nm":
						deptDTO.setDept_nm(value);
						break;
					case "up_nm":
						deptDTO.setUp_nm(value);
						break;
					case "up_dept":
						deptDTO.setUp_dept(value);
						break;
					case "dept_div":
						deptDTO.setDept_div(value);
						break;
					case "colg":
						deptDTO.setColg(value);
						break;
					case "colg_nm"	:
						deptDTO.setColg_nm(value);
						break;
					default:
						break;
				}
			}
			deptDTO.setUsed(true);
			Dept dept = deptDTO.toEntity();
			deptList.add(dept);
		}
		return deptList;
	}

	public ResponseDTO updateDept() throws ParserConfigurationException, IOException, SAXException {
		List<Dept> oldDeptList = deptRepository.findAll();
		List<Dept> newDeptList = loadDept();
		ResponseDTO result = new ResponseDTO();

		List<Dept> deletedDeptList = deptFilter.getDeletedDept(oldDeptList, newDeptList);
		deletedDeptList.forEach(d -> d.changeUsedFlag(false));
		deptRepository.saveAll(deletedDeptList);
		result.setDelete(deletedDeptList.size());

		List<Dept> addedDeptList = deptFilter.getAddedDept(oldDeptList, newDeptList);
		deptRepository.saveAll(addedDeptList);
		result.setInsert(addedDeptList.size());

		List<Dept> updatedDeptList = deptFilter.getUpdatedDept(oldDeptList, newDeptList);
		deptRepository.saveAll(updatedDeptList);
		result.setUpdate(updatedDeptList.size());

		return result;
	}
}