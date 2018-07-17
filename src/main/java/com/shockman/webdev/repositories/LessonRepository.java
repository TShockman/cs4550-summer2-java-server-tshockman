package com.shockman.webdev.repositories;

import org.springframework.data.repository.CrudRepository;

import com.shockman.webdev.models.Lesson;

public interface LessonRepository extends CrudRepository<Lesson, Integer>{

}
