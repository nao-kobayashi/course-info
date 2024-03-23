package com.mkprojects.courseinfo.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;

import com.mkprojects.courseinfo.domain.Course;

class CourseJdbcRepository implements CourseRepository {
    private static final String H2_DATABASE_URL = "jdbc:h2:file:%s;AUTO_SERVER=TRUE;INIT=RUNSCRIPT FROM './db_init.sql'";
    private final DataSource datasource;

    private static final String INSERT_COURSE = """
            insert into COURSES (ID, NAME, LENGTH, URL) values (?, ?, ?, ?)
            """;;

    public CourseJdbcRepository(String databaseFile) {
        JdbcDataSource jdbcDataSource = new JdbcDataSource();
        jdbcDataSource.setURL(H2_DATABASE_URL.formatted(databaseFile));
        this.datasource = jdbcDataSource;
    }

    @Override
    public boolean exists(String id) {
        try (Connection connection  = datasource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from COURSES where id = ?");
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new RepositoryException("Failed to retrieve courses ", e);
        }
    }

    @Override
    public void saveCourse(Course course) {
        try (Connection connection  = datasource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(INSERT_COURSE);
            statement.setString(1, course.id());
            statement.setString(2, course.name());
            statement.setLong(3, course.length());
            statement.setString(4, course.url());
            statement.execute();
        } catch (SQLException e) {
            throw new RepositoryException("Failed to save " + course, e);
        }
    }

    @Override
    public List<Course> getAllCourses() {
        try (Connection connection  = datasource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from COURSES");

            List<Course> courses = new ArrayList<>();
            while (resultSet.next()) {
                Course course = new Course(
                resultSet.getString(1),
                resultSet.getString(2),
                resultSet.getLong(3),
                resultSet.getString(4));
                courses.add(course);
            }

            return Collections.unmodifiableList(courses);
        } catch (SQLException e) {
            throw new RepositoryException("Failed to retrieve courses ", e);
        }
    }

}
