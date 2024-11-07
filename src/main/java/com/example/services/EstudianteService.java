package com.example.services;

import java.util.List;

import com.example.entities.Estudiante;

public interface EstudianteService {
    List<Estudiante> getEstudiantes();

    Estudiante persistirEstudiante(Estudiante estudiante);

    void deleteEstudiante(Estudiante estudiante);

    Estudiante getEstudiante(int idEstudiante);
}
