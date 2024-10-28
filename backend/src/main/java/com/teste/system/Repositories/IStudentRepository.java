package com.teste.system.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teste.system.model.Student;

@Repository
public interface IStudentRepository  extends JpaRepository<Student, Long>{
    
}
