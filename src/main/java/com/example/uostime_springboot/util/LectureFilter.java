package com.example.uostime_springboot.util;

import com.example.uostime_springboot.domain.Lecture;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor
public class LectureFilter {
	//		oldLectureList.sort(Comparator.comparing(Lecture::getSubjectNo)
//				.thenComparing(Lecture::getClassDiv)
//				.thenComparing(Lecture::getProfNm)
//				.thenComparing(Lecture::getClassNm)
//		);
//		newLectureList.sort(Comparator.comparing(Lecture::getSubjectNo)
//				.thenComparing(Lecture::getClassDiv)
//				.thenComparing(Lecture::getProfNm)
//				.thenComparing(Lecture::getClassNm)
//		);
	public List<Lecture> getDeletedLecture(List<Lecture> oldLectureList, List<Lecture> newLectureList){
		List<LectureMapper> oldLectureMapper = mapList(oldLectureList);
		List<LectureMapper> newLectureMapper = mapList(newLectureList);

		oldLectureMapper.removeAll(newLectureMapper);

		return oldLectureMapper.stream()
				.map(l -> l.getLecture())
				.collect(Collectors.toList());
	}

	public List<Lecture> getUpdatedLecture(List<Lecture> oldLectureList, List<Lecture> newLectureList){
		List<LectureMapper> oldLectureMapper = mapList(oldLectureList);
		List<LectureMapper> newLectureMapper = mapList(newLectureList);

		newLectureMapper.removeAll(oldLectureMapper);

		return newLectureMapper.stream()
				.map(l -> l.getLecture())
				.collect(Collectors.toList());
	}

	public List<LectureMapper> mapList(List<Lecture> lectureList){
		return lectureList.stream()
				.map(l -> new LectureMapper((Lecture) l))
				.collect(Collectors.toList());
	}
}
