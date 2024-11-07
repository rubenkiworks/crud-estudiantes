package com.example.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dao.FacultadDao;
import com.example.entities.Facultad;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FacultadServiceImpl implements FacultadService{

    private final FacultadDao facultadDao;

    @Override
    public List<Facultad> getFacultades() {
        return facultadDao.findAll();
    }

    @Override
    public void persistirFacultad(Facultad facultad) {
        facultadDao.save(facultad);
    }

    @Override
    public Facultad getFacultad(int idFacultad) {
        return facultadDao.findById(idFacultad).get();
    }

}
