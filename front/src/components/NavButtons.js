import React from 'react';

const NavButtons = ({ activeTab, setActiveTab }) => (
  <div className="nav-buttons">
    {['student', 'discipline', 'student-discipline'].map((tab) => (
      <button
        key={tab}
        onClick={() => setActiveTab(tab)}
        className={`nav-button ${activeTab === tab ? 'active' : 'inactive'}`}
      >
        {`Cadastro de ${tab.replace('-', ' ')}`}
      </button>
    ))}
  </div>
);

export default NavButtons;
