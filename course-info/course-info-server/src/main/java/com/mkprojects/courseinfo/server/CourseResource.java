package com.mkprojects.courseinfo.server;

import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mkprojects.courseinfo.domain.Course;
import com.mkprojects.courseinfo.repository.CourseRepository;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/courses")
public class CourseResource {
    private static final Logger LOG = LoggerFactory.getLogger(CourseResource.class);
    private final CourseRepository courseRepository;

    public CourseResource(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @GET
    @Produces("application/json;  charset=UTF-8")
    public List<Course> getCourses() {
        try {
            return courseRepository
                .getAllCourses()
                .stream()
                .sorted(Comparator.comparing(course -> course.id()))
                .toList();
        } catch (RuntimeException e) {
            LOG.error("could not retrieve course info.", e);
            throw new NotFoundException();
        }
    }

    @POST
    @Path("/{id}/notes")
    @Consumes(MediaType.TEXT_PLAIN)
    public void addNotes(@PathParam("id") String id, String notes) {
        LOG.info("update notes '{}' '{}'", id, notes);
        courseRepository.addNotes(id, notes);
    }

}
