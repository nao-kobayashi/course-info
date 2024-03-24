package com.mkprojects.courseinfo.cli;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mkprojects.courseinfo.cli.service.CourseRetrievalService;
import com.mkprojects.courseinfo.cli.service.CourseStorageService;
import com.mkprojects.courseinfo.cli.service.PluralsightCourse;
import com.mkprojects.courseinfo.repository.CourseRepository;

public class CourseRetriever {
    private static final Logger LOG = LoggerFactory.getLogger(CourseRetriever.class);

    public static void main(String... args) {
        LOG.info("CourseRetriever started!");

        if (args.length == 0) {
            LOG.warn("Please provide an author name as first argument.");
            return;
        }

        try {
            retrieveCourse(args[0]);
        } catch (Exception e) {
            LOG.error("Unexpected error", e);
        }
    }

    private static void retrieveCourse(String authorId) {
        LOG.info("Retrieving courses for author '{}'", authorId);

        CourseRetrievalService courseRetrieveService = new CourseRetrievalService();
        CourseRepository courseRepository = CourseRepository.openCourseRepository("./courses.db");
        CourseStorageService courseStorageService = new CourseStorageService(courseRepository);

        List<PluralsightCourse> coursesToStore = courseRetrieveService.getCoursesFor(authorId)
            .stream()
            .filter(course -> !course.isRetired())
            .toList();

        LOG.info("Retrieved the following {} courses {}", coursesToStore.size(), coursesToStore);

        courseStorageService.storePluralsightCourses(coursesToStore);
        LOG.info("Courses successfully stored");
    }
}
