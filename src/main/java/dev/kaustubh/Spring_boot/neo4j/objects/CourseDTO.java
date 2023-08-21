package dev.kaustubh.Spring_boot.neo4j.objects;

import dev.kaustubh.Spring_boot.neo4j.models.Lesson;

import java.util.ArrayList;
import java.util.List;

public class CourseDTO {

    private String identifier;
    private String title;
    private String teacher;
    private boolean isEnrolled;
    private List<Lesson> lessons = new ArrayList<>();

    public CourseDTO(String identifier, String title, String teacher) {
        this.identifier = identifier;
        this.title = title;
        this.teacher = teacher;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getTitle() {
        return title;
    }

    public String getTeacher() {
        return teacher;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public boolean isEnrolled() {
        return isEnrolled;
    }

    public void setEnrolled(boolean enrolled) {
        isEnrolled = enrolled;
    }
}
