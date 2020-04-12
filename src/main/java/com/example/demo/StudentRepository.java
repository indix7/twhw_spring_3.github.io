package com.example.demo;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface StudentRepository extends CrudRepository<Student, String> {


    @Query("INSERT INTO student (name, gender, classmate) VALUE (:name, :gender, :classmate)")
    @Modifying()
    public void realSave(@Param("name")String name, @Param("gender")String gender, @Param("classmate")String classmate);
}
