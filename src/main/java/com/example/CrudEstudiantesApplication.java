package com.example;

import java.time.LocalDate;
import java.time.Month;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.entities.Correo;
import com.example.entities.Estudiante;
import com.example.entities.Facultad;
import com.example.entities.Telefono;
import com.example.models.Genero;
import com.example.services.CorreoService;
import com.example.services.EstudianteService;
import com.example.services.FacultadService;
import com.example.services.TelefonoService;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor

public class CrudEstudiantesApplication implements CommandLineRunner{

	private final EstudianteService estudianteService;
	private final FacultadService facultadService;
	private final TelefonoService telefonoService;
	private final CorreoService correoService;

	public static void main(String[] args) {
		SpringApplication.run(CrudEstudiantesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		facultadService.persistirFacultad(
			Facultad.builder()
			.nombre("INFORMATICA")
			.id(1)
			.build()
		);

		estudianteService.persistirEstudiante(
			Estudiante.builder()
			.nombre("Ruben")
			.primerApellido("Gomez")
			.facultad(facultadService.getFacultad(1))
			.fechaNacimiento(LocalDate.of(2000, Month.SEPTEMBER, 1))
			.becaConcedida(3500.5)
			.genero(Genero.HOMBRE)
			.id(1)
			.build()
		);

		telefonoService.persistirTelefono(
			Telefono.builder()
			.numero("666666666")
			.id(1)
			.estudiante(estudianteService.getEstudiante(1))
			.build()
		);

		correoService.persistirCorreo(
			Correo.builder()
			.direccion("jfgkdjdfkg@fjdkfjd.com")
			.id(1)
			.estudiante(estudianteService.getEstudiante(1))
			.build()
		);
	}

}
