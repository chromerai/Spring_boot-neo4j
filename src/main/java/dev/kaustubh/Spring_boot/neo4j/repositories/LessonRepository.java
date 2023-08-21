package dev.kaustubh.Spring_boot.neo4j.repositories;

import dev.kaustubh.Spring_boot.neo4j.models.Lesson;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;

public interface LessonRepository extends Neo4jRepository<Lesson, Long> {

    @Query("MATCH (:Course {identifier: $identifier})<-[:BELONGS_TO]-(lessons:Lesson) RETURN lessons;")
    List<Lesson> findLessonByCourseIdentifier(String identifier);

}
