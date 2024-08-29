package com.teste.system.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.teste.system.Repositories.StudentDisciplineRepository;
import com.teste.system.Repositories.StudentRepository;
import com.teste.system.model.Student;
import com.teste.system.model.StudentDiscipline;

@Service
public class StudentServices {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentDisciplineRepository studentDisciplinaRepository;;

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Student updateStudent(Long id, Student studentDetails) {
        if (studentRepository.existsById(id)) {
            studentDetails.setId(id);
            return studentRepository.save(studentDetails);
        }
        return null;
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public Student addDisciplinesToStudent(Long studentId, List<StudentDiscipline> studentDisciplines) {
        Student student = studentRepository.findById(studentId).orElseThrow();

        List<StudentDiscipline> existingDisciplines = studentDisciplinaRepository.findByStudentId(studentId);
        if (existingDisciplines.size() + studentDisciplines.size() > 5) {
            throw new IllegalArgumentException("O aluno não pode ter mais do que 5 disciplinas.");
        }

        for (StudentDiscipline sd : studentDisciplines) {
            sd.setStudent(student);
            studentDisciplinaRepository.save(sd);
        }

        student.setStudentDisciplines(studentDisciplinaRepository.findByStudentId(studentId));
        return studentRepository.save(student);
    }

    public void updateStudentData(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow();
        double averageGrade = student.calculateAverageGrade();
        double overallFrequency = student.calculateFrequency();

        student.setFrequency(overallFrequency);
        studentRepository.save(student);
    }

    public double getStudentAverageGrade(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow();
        return student.calculateAverageGrade();
    }

    public double getStudentFrequency(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow();
        return student.getFrequency();
    }

}
