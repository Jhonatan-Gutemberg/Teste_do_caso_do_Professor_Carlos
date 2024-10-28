import React from 'react';

const StudentForm = ({ student, onStudentChange, onSubmit, error }) => (
  <div className="form-section">
    <h2>Cadastro de Aluno</h2>
    <form onSubmit={onSubmit}>
      {['name', 'email', 'address', 'registration', 'dateBirth'].map((field) => (
        <div key={field} className="form-group">
          <label htmlFor={field}>{field[0].toUpperCase() + field.slice(1)}:</label>
          <input
            type={field === 'email' ? 'email' : 'text'}
            id={field}
            name={field}
            value={student[field]}
            onChange={onStudentChange}
            required
          />
        </div>
      ))}
      {error && <p className="error-message">{error}</p>} {/* Mensagem de erro */}
      <button type="submit" className="form-button">Cadastrar Aluno</button>
    </form>
  </div>
);

export default StudentForm;
