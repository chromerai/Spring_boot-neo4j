package dev.kaustubh.Spring_boot.neo4j.requests;

public class CourseEnrolmentRequest {

    private String courseidentifier;

    public CourseEnrolmentRequest() {
    }

    public String getCourseidentifier() {
        return courseidentifier;
    }

    public void setCourseidentifier(String courseidentifier) {
        this.courseidentifier = courseidentifier;
    }
}
