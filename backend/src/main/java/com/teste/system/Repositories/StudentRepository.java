package com.teste.system.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teste.system.model.Student;

public interface StudentRepository  extends JpaRepository<Student, Long>{
    
}
