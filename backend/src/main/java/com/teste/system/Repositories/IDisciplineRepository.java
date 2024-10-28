package com.teste.system.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teste.system.model.Discipline;


@Repository
public interface IDisciplineRepository  extends JpaRepository<Discipline, Long>{
    
}
