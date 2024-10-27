package com.teste.system.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teste.system.model.Student;

// Por padrão, o Java tende a colocar uma letra 'I' antes do nome de suas classes. Nesse caso, mudar esta e outras 
// como, por exemplo, 'IStudentDisciplineRepository'.

// Colocar anotação @Repository nos repositórios para o Spring reconhe-los como componentes. 

public interface StudentRepository  extends JpaRepository<Student, Long>{
    
}
