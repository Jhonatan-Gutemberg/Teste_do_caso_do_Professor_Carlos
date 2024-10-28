package com.teste.system.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teste.system.model.Student;
import com.teste.system.model.StudentDiscipline;
 
@Repository
public interface IStudentDisciplineRepository extends JpaRepository< StudentDiscipline, Long> {
    List<StudentDiscipline> findByStudentId(Long studentId);

    List<StudentDiscipline> findByStudent(Student student);
}
