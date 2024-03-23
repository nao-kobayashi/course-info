package com.mkprojects.courseinfo.cli.service;

import java.util.List;

import com.mkprojects.courseinfo.domain.Course;
import com.mkprojects.courseinfo.repository.CourseRepository;

public class CourseStorageService {
    private static final String PS_BASE_URL = "https://app.pluralsight.com";
    private CourseRepository courseRepository = null;

    public CourseStorageService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void storePluralsightCourses(List<PluralsightCourse> psCourses) {
        for (PluralsightCourse psCourse: psCourses) {
            Course course = new Course(
                psCourse.id(),
                psCourse.title(),
                psCourse.durationInMinutes(),
                PS_BASE_URL + psCourse.contentUrl()
            );

            if (!courseRepository.exists(course.id())) {
                courseRepository.saveCourse(course);
            }
        }
    }
}
