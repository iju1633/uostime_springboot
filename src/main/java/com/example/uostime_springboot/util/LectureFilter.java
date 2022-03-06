package com.example.uostime_springboot.util;

import com.example.uostime_springboot.domain.Lecture;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor
public class LectureFilter {
	public List<Lecture> getDeletedLecture(List<Lecture> oldLectureList, List<Lecture> newLectureList){
		List<LectureMapper> oldLectureMapper = mapList(oldLectureList);
		List<LectureMapper> newLectureMapper = mapList(newLectureList);

		oldLectureMapper.removeAll(newLectureMapper);

		return oldLectureMapper.stream()
				.map(l -> l.getLecture())
				.collect(Collectors.toList());
	}

	public List<Lecture> getAddedLecture(List<Lecture> oldLectureList, List<Lecture> newLectureList){
		List<LectureMapper> oldLectureMapper = mapList(oldLectureList);
		List<LectureMapper> newLectureMapper = mapList(newLectureList);

		newLectureMapper.removeAll(oldLectureMapper);

		return newLectureMapper.stream()
				.map(l -> l.getLecture())
				.collect(Collectors.toList());
	}

	public List<Lecture> getUpdatedLecture(List<Lecture> oldLectureList, List<Lecture> newLectureList){
		List<LectureMapper> oldLectureMapper = mapList(oldLectureList);
		List<LectureMapper> newLectureMapper = mapList(newLectureList);

		oldLectureMapper.retainAll(newLectureMapper);
		newLectureMapper.retainAll(oldLectureMapper);

		oldLectureMapper.sort(Comparator.comparing(LectureMapper::getSubjectNo)
				.thenComparing(LectureMapper::getClassDiv)
				.thenComparing(LectureMapper::getYear)
				.thenComparing(LectureMapper::getTerm));

		newLectureMapper.sort(Comparator.comparing(LectureMapper::getSubjectNo)
				.thenComparing(LectureMapper::getClassDiv)
				.thenComparing(LectureMapper::getYear)
				.thenComparing(LectureMapper::getTerm));

		for (int i = 0; i < oldLectureMapper.size(); i++){
			oldLectureMapper.get(i).getLecture().updateInfo(newLectureMapper.get(i).getLecture());
		}

		return oldLectureMapper.stream()
				.map(l -> l.getLecture())
				.collect(Collectors.toList());
	}

	public List<LectureMapper> mapList(List<Lecture> lectureList){
		return lectureList.stream()
				.map(l -> new LectureMapper((Lecture) l))
				.collect(Collectors.toList());
	}
}
