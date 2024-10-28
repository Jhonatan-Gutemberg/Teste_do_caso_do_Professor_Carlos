import React from 'react';

const RecordsList = ({ studentDisciplineRecords }) => {
  return (
    <div className="records-list">
      <h2>Registros de Disciplinas por Aluno</h2>
      {studentDisciplineRecords.length > 0 ? (
        <table>
          <thead>
            <tr>
              <th>Aluno</th>
              <th>Disciplina</th>
              <th>Nota</th>
              <th>Frequência (%)</th>
            </tr>
          </thead>
          <tbody>
            {studentDisciplineRecords.map((record, index) => (
              <tr key={index}>
                <td>{record.studentName}</td>
                <td>{record.disciplineName}</td>
                <td>{record.grade}</td>
                <td>{record.frequency}</td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <p>Nenhum registro encontrado.</p>
      )}
    </div>
  );
};

export default RecordsList;
