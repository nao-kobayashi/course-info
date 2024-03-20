package com.mkprojects.courseinfo.cli.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PluralsightCourseTest {
    @ParameterizedTest
    @CsvSource(textBlock = """
            01:08:54.38383,68
            00:05:37,5
            00:00:00,0
            """)
    void durationInMinutesParameterized(String input, long expected) {
        PluralsightCourse course = new PluralsightCourse("id", "title", input, "url", false);
        assertEquals(expected, course.durationInMinutes());
    }

    @Test
    void testDurationInMinutes() {
        PluralsightCourse course = new PluralsightCourse("id", "title", "00:05:37", "url", false);
        assertEquals(5, course.durationInMinutes());
    }

    @Test
    void testDurationInMinutesOverHour() {
        PluralsightCourse course = new PluralsightCourse("id", "title", "01:08:37", "url", false);
        assertEquals(68, course.durationInMinutes());
    }

    @Test
    void testDurationInMinutesZero() {
        PluralsightCourse course = new PluralsightCourse("id", "title", "00:00:00", "url", false);
        assertEquals(0, course.durationInMinutes());
    }
}
