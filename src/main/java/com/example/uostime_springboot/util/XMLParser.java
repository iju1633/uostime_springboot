package com.example.uostime_springboot.util;

import com.example.uostime_springboot.domain.Lecture;
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
			Lecture lecture = new Lecture();
			NodeList childNodes = item.getChildNodes();
			for (int j = 0; j < childNodes.getLength(); j++){
				Node node = childNodes.item(j);
				if (node.getChildNodes().item(0) == null) continue;
				String value = node.getChildNodes().item(0).getNodeValue();
				switch (node.getNodeName()){
					case "year":
						lecture.setYear(value);
						break;
					case "term":
						lecture.setTerm(value);
						break;
					case "subject_div2":
						lecture.setSubjectDiv2(value);
						break;
					case "subject_div":
						lecture.setSubjectDiv(value);
						break;
					case "subject_no":
						lecture.setSubjectNo(value);
						break;
					case "class_div":
						lecture.setClassDiv(value);
						break;
					case "subject_nm":
						lecture.setSubjectNm(value);
						break;
					case "sub_dept":
						lecture.setSubDept(value);
						break;
					case "day_night_nm":
						lecture.setDayNightNm(value);
						break;
					case "shyr":
						lecture.setShyr(Integer.parseInt(value));
						break;
					case "credit":
						lecture.setCredit(Integer.parseInt(value));
						break;
					case "class_nm":
						lecture.setClassNm(value);
						break;
					case "prof_nm":
						lecture.setProfNm(value);
						break;
					case "class_type":
						lecture.setClassType(value);
						break;
					case "tlsn_limit_count":
						lecture.setTlsnLimitCount(Integer.parseInt(value));
						break;
					case "tlsn_count":
						lecture.setTlsnCount(Integer.parseInt(value));
						break;
					case "etc_permit_yn":
						lecture.setEtcPermitYn(value);
						break;
					case "sec_permit_yn":
						lecture.setSecPermitYn(value);
						break;
					default:
						break;
				}
			}
			lecture.setUsed(true);
			lectureList.add(lecture);
		}
		return lectureList;
	}

}
