package dev.kaustubh.Spring_boot.neo4j.controllers;

import dev.kaustubh.Spring_boot.neo4j.models.Course;
import dev.kaustubh.Spring_boot.neo4j.objects.CourseDTO;
import dev.kaustubh.Spring_boot.neo4j.objects.CourseEnrolmentDTO;
import dev.kaustubh.Spring_boot.neo4j.queryresults.CourseEnrollmentQueryResult;
import dev.kaustubh.Spring_boot.neo4j.requests.CourseEnrolmentRequest;
import dev.kaustubh.Spring_boot.neo4j.services.CourseEnrolmentService;
import dev.kaustubh.Spring_boot.neo4j.services.LessonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/enrolments")
public class CourseEnrolmentController {
    private final CourseEnrolmentService courseEnrolmentService;
    private final LessonService lessonService;

    public CourseEnrolmentController(CourseEnrolmentService courseEnrolmentService, LessonService lessonService) {
        this.courseEnrolmentService = courseEnrolmentService;
        this.lessonService = lessonService;
    }

    @GetMapping("/")
    public ResponseEntity<List<CourseDTO>> enrolments(Principal principal) {
        List<Course> courses = courseEnrolmentService.getAllEnrolledCoursesByUsername((principal.getName()));

        List<CourseDTO> responseCourses = courses.stream().map(
                (course) -> {
                    CourseDTO responseCourse = new CourseDTO(course.getIdentifier(), course.getTitle(), course.getTeacher());
                    responseCourse.setLessons(lessonService.getAllLessonsByCourseIdentifier(course.getIdentifier()));
                    responseCourse.setEnrolled(true);
                    return responseCourse;
                }
        ).collect(Collectors.toList());

        return new ResponseEntity<>(responseCourses, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<CourseEnrolmentDTO> enrollIn(@RequestBody CourseEnrolmentRequest request, Principal principal) {
        CourseEnrollmentQueryResult enrollmentQueryResult = courseEnrolmentService.enrollIn(principal.getName(), request.getCourseidentifier());

        CourseEnrolmentDTO responseEnrolment = new CourseEnrolmentDTO(
                enrollmentQueryResult.getUser().getUsername(),
                enrollmentQueryResult.getUser().getName(),
                enrollmentQueryResult.getCourse()
        );

        return new ResponseEntity<>(responseEnrolment, HttpStatus.OK);
    }
}
