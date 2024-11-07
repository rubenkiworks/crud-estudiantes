package com.example.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dao.EstudianteDao;
import com.example.entities.Estudiante;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EstudianteServiceImpl implements EstudianteService{

    private final EstudianteDao estudianteDao;

    @Override
    public List<Estudiante> getEstudiantes() {
        return estudianteDao.findAll();
    }

    @Override
    public Estudiante persistirEstudiante(Estudiante estudiante) {
        return estudianteDao.save(estudiante);
    }

    @Override
    public void deleteEstudiante(Estudiante estudiante) {
        estudianteDao.delete(estudiante);
    }

    @Override
    public Estudiante getEstudiante(int idEstudiante) {
        return estudianteDao.findById(idEstudiante).get();
    }

}
