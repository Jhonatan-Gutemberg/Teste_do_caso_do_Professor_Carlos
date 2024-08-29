package com.teste.system.services;

import com.teste.system.model.Student;
import com.teste.system.model.StudentDiscipline;
import com.teste.system.Repositories.StudentRepository;
import com.teste.system.Repositories.StudentDisciplineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;

@Service
public class CalculationService {

    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private StudentDisciplineRepository studentDisciplineRepository;

    public void updateAverages() {
        List<Student> students = studentRepository.findAll();
        for (Student student : students) {
            List<StudentDiscipline> disciplines = studentDisciplineRepository.findByStudent(student);
            double totalNotes = 0;
            int count = disciplines.size();

            for (StudentDiscipline sd : disciplines) {
                totalNotes += sd.getNote();
            }

            double averageNote = count > 0 ? totalNotes / count : 0;

            
            student.setFrequency(averageNote); 
            studentRepository.save(student);
        }
    }
}
