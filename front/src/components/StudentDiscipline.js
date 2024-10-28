import React, { useState } from 'react';

const StudentDisciplineForm = ({ students, disciplines, loadStudentDisciplineRecords }) => {
  const [selectedStudent, setSelectedStudent] = useState('');
  const [selectedDiscipline, setSelectedDiscipline] = useState('');
  const [grade, setGrade] = useState('');

  const handleStudentChange = (e) => {
    setSelectedStudent(e.target.value);
  };

  const handleDisciplineChange = (e) => {
    setSelectedDiscipline(e.target.value);
  };

  const handleGradeChange = (e) => {
    setGrade(e.target.value);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const record = {
      studentId: selectedStudent,
      disciplineId: selectedDiscipline,
      grade: grade,
    };

    loadStudentDisciplineRecords();

    setSelectedStudent('');
    setSelectedDiscipline('');
    setGrade('');
  };

  return (
    <div className="form-section">
      <h2>Cadastro de Registro de Disciplina</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="student">Aluno:</label>
          <select id="student" value={selectedStudent} onChange={handleStudentChange} required>
            <option value="">Selecione um aluno</option>
            {Array.isArray(students) && students.length > 0 ? (
              students.map((student) => (
                <option key={student.id} value={student.id}>
                  {student.name}
                </option>
              ))
            ) : (
              <option value="">Nenhum aluno disponível</option>
            )}
          </select>
        </div>
        <div className="form-group">
          <label htmlFor="discipline">Disciplina:</label>
          <select id="discipline" value={selectedDiscipline} onChange={handleDisciplineChange} required>
            <option value="">Selecione uma disciplina</option>
            {Array.isArray(disciplines) && disciplines.length > 0 ? (
              disciplines.map((discipline) => (
                <option key={discipline.id} value={discipline.id}>
                  {discipline.name}
                </option>
              ))
            ) : (
              <option value="">Nenhuma disciplina disponível</option>
            )}
          </select>
        </div>
        <div className="form-group">
          <label htmlFor="grade">Nota:</label>
          <input
            type="number"
            id="grade"
            value={grade}
            onChange={handleGradeChange}
            min="0"
            max="10"
            required
          />
        </div>
        <button type="submit" className="form-button">Cadastrar Registro</button>
      </form>
    </div>
  );
};

export default StudentDisciplineForm;
