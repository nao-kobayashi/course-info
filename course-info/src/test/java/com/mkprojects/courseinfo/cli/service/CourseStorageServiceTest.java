package com.mkprojects.courseinfo.cli.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.mkprojects.courseinfo.domain.Course;
import com.mkprojects.courseinfo.repository.CourseRepository;

public class CourseStorageServiceTest {
    @Test
    void storePluralsightCourses() {
        CourseRepository courseRepository = new InMemoryCourseRepository();
        CourseStorageService courseStorageService = new CourseStorageService(courseRepository);

        PluralsightCourse ps1 = new PluralsightCourse("1", "title", "01:40:59.123", "/url-1", false);
        courseStorageService.storePluralsightCourses(List.of(ps1));

        Course expected = new Course("1", "title", 100, "https://app.pluralsight.com/url-1", Optional.empty());
        assertEquals(List.of(expected), courseRepository.getAllCourses());

        assertEquals(true, courseRepository.exists("1"));
        assertEquals(false, courseRepository.exists("a"));
    }

    static class InMemoryCourseRepository implements CourseRepository {
        private List<Course> Courses = new ArrayList<>();

        @Override
        public void saveCourse(Course course) {
            Courses.add(course);
        }

        @Override
        public List<Course> getAllCourses() {
            return Courses;
        }

        @Override
        public boolean exists(String id) {
            return Courses
                .stream()
                .anyMatch(c -> c.id().equals(id));
        }

        @Override
        public void addNotes(String id, String notes) {
            throw new UnsupportedOperationException("Unimplemented method 'addNotes'");
        }
        
    }
}
