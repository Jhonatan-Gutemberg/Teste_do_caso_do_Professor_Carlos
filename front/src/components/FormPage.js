import React from 'react';
import './FormPage.css';

const FormPage = () => {
  return (
    <div className="container">
      <header className="header">
        <h1>Formulário de Cadastro</h1>
      </header>

      <div className="nav-buttons">
        <button className="nav-button active">Formulário</button>
        <button className="nav-button inactive">Lista de Registros</button>
      </div>

      <section className="form-section">
        <h2>Dados do Usuário</h2>
        <div className="form-group">
          <label htmlFor="nome">Nome</label>
          <input type="text" id="nome" name="nome" />
        </div>
        <div className="form-group">
          <label htmlFor="dataNascimento">Data de Nascimento</label>
          <input type="date" id="dataNascimento" name="dataNascimento" />
        </div>
        <button className="form-button">Enviar</button>
      </section>

      <section className="records-list">
        <h3>Lista de Registros</h3>
        <ul>
          
        </ul>
      </section>
    </div>
  );
};

export default FormPage;
