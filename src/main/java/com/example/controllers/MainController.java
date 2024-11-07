package com.example.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entities.Correo;
import com.example.entities.Estudiante;
import com.example.entities.Facultad;
import com.example.entities.Telefono;
import com.example.services.CorreoService;
import com.example.services.EstudianteService;
import com.example.services.FacultadService;
import com.example.services.TelefonoService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor

public class MainController {
    private final EstudianteService estudianteService;
    private final TelefonoService telefonoService;
    private final CorreoService correoService;
    private final FacultadService facultadService;
    
    @GetMapping("/estudiantes")
    public String listadoEstudiante(Model model){
        
        List<Estudiante> estudiantes = estudianteService.getEstudiantes();

        model.addAttribute("estudiantes", estudiantes);

        return "views/listarEstudiantes.html";
    }

    @GetMapping("/alta")
    public String frmAlta(Model model){
        Estudiante estudiante = new Estudiante(); 
        model.addAttribute("estudiante", estudiante);

        List<Facultad> facultades = facultadService.getFacultades();
        model.addAttribute("facultades", facultades);

        return "views/formularioAltaModificacion";
    }

    @PostMapping("/guardarEstudiante")
    @Transactional
    public String guardarEstudiante(@ModelAttribute(name="estudiante") Estudiante estudiante,
    @RequestParam(name="numerosTelefono", required=false) String numerosTelefonoRecibidos,
    @RequestParam(name="direccionesCorreo", required=false) String correosRecibidos){
        
        Estudiante estudiantecreado = estudianteService.persistirEstudiante(estudiante);

        if (numerosTelefonoRecibidos != null && !numerosTelefonoRecibidos.isEmpty()) {
            String[] arrayNumeros = numerosTelefonoRecibidos.split(";");
            List<String> numeros = Arrays.asList(arrayNumeros);

            numeros.stream().forEach(n -> {
                Telefono telefono =  Telefono.builder()
                .numero(n)
                .estudiante(estudiantecreado)
                .build();

                telefonoService.persistirTelefono(telefono);
            });
        }

        if (correosRecibidos != null && !correosRecibidos.isEmpty()) {
            String[] arrayCorreos = correosRecibidos.split(";");
            List<String> correos = Arrays.asList(arrayCorreos);

            correos.stream().forEach(c -> {
                
                correoService.persistirCorreo(
                    Correo.builder()
                        .direccion(c)
                        .estudiante(estudiantecreado)
                        .build()
                );
            });
        }
        
        return "redirect:/estudiantes";
    }
}
