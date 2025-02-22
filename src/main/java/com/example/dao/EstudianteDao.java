package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.Estudiante;

@Repository
public interface EstudianteDao extends JpaRepository<Estudiante, Integer>{
    
    List<Estudiante> findByNombre(String nombre);
}
