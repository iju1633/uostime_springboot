package com.example.uostime_springboot.service;

import com.example.uostime_springboot.domain.Dept;
import com.example.uostime_springboot.domain.Lecture;
import com.example.uostime_springboot.dto.RequestDTO;
import com.example.uostime_springboot.repository.DeptRepository;
import com.example.uostime_springboot.repository.LectureRepository;
import com.example.uostime_springboot.util.LectureFilter;
import com.example.uostime_springboot.util.XMLParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@PropertySource("classpath:wiseapi.properties")
public class WiseService {
	@Value("${wise.apikey}")
	private String wiseApiKey;

	private final XMLParser xmlParser;
	private final DeptRepository dr;
	private final LectureRepository lr;
	private final LectureFilter lf;

	@Autowired
	public WiseService(XMLParser xmlParser, DeptRepository dr, LectureRepository lr, LectureFilter lf) {
		this.dr = dr;
		this.lr = lr;
		this.lf = lf;
		this.xmlParser = xmlParser;
	}

	public List<Lecture> loadCultureLecture(String year, String term, String subjectDiv){
		String wiseApiUri = "https://wise.uos.ac.kr/uosdoc/api.ApiUcrCultTimeInq.oapi";
		try {
			UriComponents uri = UriComponentsBuilder.fromHttpUrl(wiseApiUri)
					.queryParam("apiKey", this.wiseApiKey)
					.queryParam("year", year)
					.queryParam("term", term)
					.queryParam("subjectDiv", subjectDiv)
					.build(false);

			Document lectureInfo = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().parse(uri.toUriString());
			lectureInfo.getDocumentElement().normalize();

			return (xmlParser.lectureParse(lectureInfo));
		} catch (IOException | ParserConfigurationException | SAXException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Lecture> getCultureLecture(String year, String term){
		List<String> subjectDivList = Arrays.asList("A01", "A02", "A05", "A06", "A07", "A08", "A09", "A10");
		List<Lecture> cultureLectureList = new ArrayList<>();
		subjectDivList.forEach(s -> cultureLectureList.addAll(loadCultureLecture(year, term, s)));
		// (year, term) -> load culture lecture => add to list
		return cultureLectureList;
	}

	public List<Lecture> loadMajorLecture(String year, String term, String deptDiv, String dept, String subDept){
		String wiseApiUri = "https://wise.uos.ac.kr/uosdoc/api.ApiUcrMjTimeInq.oapi";
		try {
			UriComponents uri = UriComponentsBuilder.fromHttpUrl(wiseApiUri)
					.queryParam("apiKey", this.wiseApiKey)
					.queryParam("year", year)
					.queryParam("term", term)
					.queryParam("deptDiv", deptDiv)
					.queryParam("dept", dept)
					.queryParam("subDept", subDept)
					.build(false);

			Document lectureInfo = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().parse(uri.toUriString());
			lectureInfo.getDocumentElement().normalize();

			return (xmlParser.lectureParse(lectureInfo));
		} catch (IOException | ParserConfigurationException | SAXException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Lecture> getMajorLecture(String year, String term){
		List<Dept> deptList = dr.findAll();
		List<Lecture> majorLectureList = new ArrayList<>();
		deptList.forEach(d -> {
			String deptDiv = d.getColg();
			String dept = d.getDept();
			String subDept = d.getSubDept();

			if (!deptDiv.equals("") && !dept.equals("") && !subDept.equals("")){
				majorLectureList.addAll(loadMajorLecture(year, term, deptDiv, dept, subDept));
			}
		});
		return majorLectureList;
	}

	@Transactional
	public Integer updateLecture(RequestDTO requestDTO){
		String year = requestDTO.getYear();
		String term = requestDTO.getTerm();

		List<Lecture> newLectureList = new ArrayList<>();
		newLectureList.addAll(getMajorLecture(year, term));
		newLectureList.addAll(getCultureLecture(year, term));

		List<Lecture> oldLectureList = lr.findByYearAndTerm(year, term);
		List<Lecture> deletedLectureList = lf.getDeletedLecture(oldLectureList, newLectureList);
		deletedLectureList.forEach(l -> l.setUsed(false));
		lr.saveAll(deletedLectureList);

		List<Lecture> updatedLectureList = lf.getUpdatedLecture(oldLectureList, newLectureList);
		lr.saveAll(updatedLectureList);

		return updatedLectureList.size();
	}
}
