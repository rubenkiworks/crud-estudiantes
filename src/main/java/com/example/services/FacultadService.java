package com.example.services;

import com.example.entities.Facultad;

import java.util.List;

public interface FacultadService {
    List<Facultad> getFacultades();
    void persistirFacultad(Facultad facultad);
    Facultad getFacultad(int idFacultad);
}
