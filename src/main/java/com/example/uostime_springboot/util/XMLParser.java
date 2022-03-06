package com.example.uostime_springboot.util;

import com.example.uostime_springboot.domain.Lecture;
import com.example.uostime_springboot.dto.LectureDTO;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

@Component
public class XMLParser {

	public List<Lecture> lectureParse(Document document){
		Element root = document.getDocumentElement();
		Element element = (Element) root.getElementsByTagName("mainlist").item(0);
		if (element == null){ return new ArrayList<>(); }
		NodeList nodeList = element.getElementsByTagName("list");

		List<Lecture> lectureList = new ArrayList<>();
		for (int i = 0; i < nodeList.getLength(); i++){
			Element item = (Element) nodeList.item(i);
			LectureDTO lectureDTO = new LectureDTO();
			NodeList childNodes = item.getChildNodes();
			for (int j = 0; j < childNodes.getLength(); j++){
				Node node = childNodes.item(j);
				if (node.getChildNodes().item(0) == null) continue;
				String value = node.getChildNodes().item(0).getNodeValue();
				switch (node.getNodeName()){
					case "year":
						lectureDTO.setYear(value);
						break;
					case "term":
						lectureDTO.setTerm(value);
						break;
					case "subject_div2":
						lectureDTO.setSubject_div2(value);
						break;
					case "subject_div":
						lectureDTO.setSubject_div(value);
						break;
					case "subject_no":
						lectureDTO.setSubject_no(value);
						break;
					case "class_div":
						lectureDTO.setClass_div(value);
						break;
					case "subject_nm":
						lectureDTO.setSubject_nm(value);
						break;
					case "sub_dept":
						lectureDTO.setSub_dept(value);
						break;
					case "day_night_nm":
						lectureDTO.setDay_night_nm(value);
						break;
					case "shyr":
						lectureDTO.setShyr(Integer.parseInt(value));
						break;
					case "credit":
						lectureDTO.setCredit(Integer.parseInt(value));
						break;
					case "class_nm":
						lectureDTO.setClass_nm(value);
						break;
					case "prof_nm":
						lectureDTO.setProf_nm(value);
						break;
					case "class_type":
						lectureDTO.setClass_type(value);
						break;
					case "tlsn_limit_count":
						lectureDTO.setTlsn_limit_count(Integer.parseInt(value));
						break;
					case "tlsn_count":
						lectureDTO.setTlsn_count(Integer.parseInt(value));
						break;
					case "etc_permit_yn":
						lectureDTO.setEtc_permit_yn(value);
						break;
					case "sec_permit_yn":
						lectureDTO.setSec_permit_yn(value);
						break;
					default:
						break;
				}
			}
			lectureDTO.setUsed(true);
			Lecture lecture = lectureDTO.toEntity();
			lectureList.add(lecture);
		}
		return lectureList;
	}
}
