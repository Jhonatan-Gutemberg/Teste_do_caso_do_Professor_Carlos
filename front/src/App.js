import React, { useState, useEffect } from 'react';
import axios from 'axios';
import StudentForm from './components/Students/Student';
import DisciplineForm from './components/Discipline/Discipline';
import StudentDisciplineForm from './components/StudentDiscipline';
import RecordsList from './components/RecordList';
import './index.css';

const App = () => {
  const [activeTab, setActiveTab] = useState('student');
  const [students, setStudents] = useState([]); 
  const [disciplines, setDisciplines] = useState([]); 
  const [studentDisciplineRecords, setStudentDisciplineRecords] = useState([]); 
  const [newStudent, setNewStudent] = useState({
    name: '',
    email: '',
    address: '',
    registration: '',
    dateBirth: '',
  });
  const [error, setError] = useState('');

  useEffect(() => {
    const loadOptions = async () => {
      try {
        const studentsResponse = await axios.get('http://localhost:8080/student/all');
        const disciplinesResponse = await axios.get('http://localhost:8080/discipline/all');
        setStudents(studentsResponse.data || []); 
        setDisciplines(disciplinesResponse.data || []); 
      } catch (error) {
        console.error('Erro ao carregar opções:', error);
      }
    };
    loadOptions();
  }, []);

  const handleStudentChange = (e) => {
    const { name, value } = e.target;
    setNewStudent((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/student/register', newStudent);
      setStudents((prev) => [...prev, response.data]);
      setNewStudent({
        name: '',
        email: '',
        address: '',
        registration: '',
        dateBirth: '',
      });
      setError(''); 
    } catch (error) {
      setError('Erro ao cadastrar aluno. Verifique os dados.');
      console.error('Erro ao cadastrar aluno:', error);
    }
  };

  const loadStudentDisciplineRecords = async () => {
    try {
      const response = await axios.get('http://localhost:8080/student-discipline/all');
      setStudentDisciplineRecords(response.data || []); 
    } catch (error) {
      console.error('Erro ao carregar registros de disciplinas:', error);
    }
  };

  return (
    <div className="container">
      <div className="header">
        <h1>Cadastro</h1>
      </div>

      <div className="nav-buttons">
        <button
          onClick={() => setActiveTab('student')}
          className={`nav-button ${activeTab === 'student' ? 'active' : ''}`}
        >
          Cadastro de Aluno
        </button>
        <button
          onClick={() => setActiveTab('discipline')}
          className={`nav-button ${activeTab === 'discipline' ? 'active' : ''}`}
        >
          Cadastro de Disciplina
        </button>
        <button
          onClick={() => setActiveTab('student-discipline')}
          className={`nav-button ${activeTab === 'student-discipline' ? 'active' : ''}`}
        >
          Cadastro de Registro de Disciplina
        </button>
      </div>

      {activeTab === 'student' && (
        <StudentForm 
          student={newStudent}
          onStudentChange={handleStudentChange}
          onSubmit={handleSubmit}
          error={error} 
        />
      )}
      {activeTab === 'discipline' && (
        <DisciplineForm setDisciplines={setDisciplines} />
      )}
      {activeTab === 'student-discipline' && (
        <StudentDisciplineForm
          students={students}
          disciplines={disciplines}
          loadStudentDisciplineRecords={loadStudentDisciplineRecords}
        />
      )}

      {activeTab === 'student-discipline' && (
        <RecordsList studentDisciplineRecords={studentDisciplineRecords} />
      )}
    </div>
  );
};

export default App;
