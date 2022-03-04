package com.example.uostime_springboot.service;

import com.example.uostime_springboot.domain.Dept;
import com.example.uostime_springboot.repository.DeptRepository;
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
import java.util.ArrayList;
import java.util.List;

@Service
@PropertySource("classpath:wiseapi.properties")
public class DeptService {
	@Value("${wise.apikey}")
	private String wiseApiKey;

	private final DeptRepository deptRepository;

	@Autowired
	public DeptService(DeptRepository deptRepository){
		this.deptRepository = deptRepository;
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
			Dept dept = new Dept();
			NodeList childNodes = item.getChildNodes();
			for(int j = 0; j < childNodes.getLength(); j++){
				Node node = childNodes.item(j);
				if (node.getChildNodes().item(0) == null) continue;
				String value = node.getChildNodes().item(0).getNodeValue();
				switch (node.getNodeName()){
					case "prt_ord":
						dept.setPtrOrd(value);
						break;
					case "dept":
						dept.setSubDept(value);
						break;
					case "dept_code_nm":
						dept.setDeptCodeNm(value);
						break;
					case "dept_nm":
						dept.setDeptNm(value);
						break;
					case "up_nm":
						dept.setUpNm(value);
						break;
					case "up_dept":
						dept.setDept(value);
						break;
					case "dept_div":
						dept.setDeptDiv(value);
						break;
					case "colg":
						dept.setColg(value);
						break;
					case "colg_nm"	:
						dept.setColgNm(value);
						break;
					default:
						break;
				}
			}
			deptList.add(dept);
		}
		return deptList;
	}

	public void updateDept() throws ParserConfigurationException, IOException, SAXException {
		List<Dept> deptList = loadDept();
		// deptList 업데이트
	}
}