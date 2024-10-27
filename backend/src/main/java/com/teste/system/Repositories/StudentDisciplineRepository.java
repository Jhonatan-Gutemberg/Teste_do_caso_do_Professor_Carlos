package com.teste.system.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teste.system.model.Student;
import com.teste.system.model.StudentDiscipline;

// Por padrão, o Java tende a colocar uma letra 'I' antes do nome de suas classes. Nesse caso, mudar esta e outras 
// como, por exemplo, 'IStudentDisciplineRepository'.

// Colocar anotação @Repository nos repositórios para o Spring reconhe-los como componentes. 

public interface StudentDisciplineRepository extends JpaRepository< StudentDiscipline, Long> {
    List<StudentDiscipline> findByStudentId(Long studentId);

    List<StudentDiscipline> findByStudent(Student student);
}
