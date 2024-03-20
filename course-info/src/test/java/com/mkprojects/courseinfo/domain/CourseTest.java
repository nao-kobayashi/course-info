package com.mkprojects.courseinfo.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;


public class CourseTest {
    @Test
    void testIdIsNull() {
        try {
            new Course(null, "name", 0, "url");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
        }
    }
    
    @Test
    void testNameIsNull() {
        try {
            new Course("id", null, 0, "url");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
        }
    }

    @Test
    void testUrlIsNull() {
        try {
            new Course("id", "name", 0, null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
        }
    }

    @Test
    void testIdIsEmpty() {
        try {
            new Course("", "name", 0, "url");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
        }
    }
    
    @Test
    void testNameIsEmpty() {
        try {
            new Course("id", "", 0, "url");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
        }
    }

    @Test
    void testUrlIsEmpty() {
        try {
            new Course("id", "name", 0, "");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
        }
    }
}
