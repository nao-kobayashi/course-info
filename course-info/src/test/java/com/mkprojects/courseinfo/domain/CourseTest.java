package com.mkprojects.courseinfo.domain;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;


public class CourseTest {
    @Test
    void testIdIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Course(null, "name", 0, "url", Optional.empty()));
    }
    
    @Test
    void testNameIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Course("id", null, 0, "url", Optional.empty()));
    }

    @Test
    void testUrlIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Course("id", "name", 0, null, Optional.empty()));
    }

    @Test
    void testIdIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Course("", "name", 0, "url", Optional.empty()));
    }
    
    @Test
    void testNameIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Course("id", "", 0, "url", Optional.empty()));
    }

    @Test
    void testUrlIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Course("id", "name", 0, "", Optional.empty()));
    }
}
