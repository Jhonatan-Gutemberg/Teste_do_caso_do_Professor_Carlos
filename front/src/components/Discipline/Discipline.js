import React, { useState } from 'react';

const DisciplineForm = ({ setDisciplines }) => {
  const [discipline, setDiscipline] = useState({
    name: '',
    workload: '',
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setDiscipline((prevDiscipline) => ({
      ...prevDiscipline,
      [name]: value,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    setDisciplines((prevDisciplines) => [...prevDisciplines, discipline]);
    setDiscipline({ name: '', workload: '' });
  };

  return (
    <div className="form-section">
      <h2>Cadastro de Disciplina</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="name">Nome:</label>
          <input
            type="text"
            id="name"
            name="name"
            value={discipline.name}
            onChange={handleChange}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="workload">Carga Horária:</label>
          <input
            type="number"
            id="workload"
            name="workload"
            value={discipline.workload}
            onChange={handleChange}
            required
          />
        </div>
        <button type="submit" className="form-button">Cadastrar Disciplina</button>
      </form>
    </div>
  );
};

export default DisciplineForm;
