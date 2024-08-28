package com.teste.system.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teste.system.model.Discipline;

public interface DisciplineRepository  extends JpaRepository<Discipline, Long>{
    
}
