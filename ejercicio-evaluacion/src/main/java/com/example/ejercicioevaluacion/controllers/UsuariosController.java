package com.example.ejercicioevaluacion.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ejercicioevaluacion.interfacesServiceImpl.IUsuariosServiceImpl;
import com.example.ejercicioevaluacion.model.Usuarios;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("api/ejemplo/usuarios")
@CrossOrigin("*")
public class UsuariosController {
	
	@Autowired
	IUsuariosServiceImpl implServiceImpl;
	
	@Operation(summary = "Obtener lista de todos los Usuarios")
	@GetMapping(value = "/all")
	public List<Usuarios> getAllList(){
		return implServiceImpl.getAll();
	}
	
	@Operation(summary = "Buscar un Usuario por ID")
	@GetMapping(value = "/find/{id}")
	public Usuarios getEntityPorId(@PathVariable Integer id) {
		return implServiceImpl.get(id);
	}
	
	@Operation(summary = "Guardar o Actualizar información de un usuario, si es nuevo, el ID dejarlo en null y si se quiere actualizar, enviar el ID del usuario a actualizar")
	@PostMapping(value = "/save")
	public ResponseEntity<Usuarios> save (@RequestBody Usuarios entidad){
		Usuarios objeto = implServiceImpl.save(entidad);
		
		return new ResponseEntity<Usuarios>(objeto, HttpStatus.OK);
	}
	
	@Operation(summary = "Eliminar un Usuario por su ID")
	@GetMapping(value =  "/delete/{id}")
	public ResponseEntity<Usuarios> delete (@PathVariable Integer id){
		Usuarios entidad = implServiceImpl.get(id);
		
		if(entidad!=null) {
			implServiceImpl.delete(id);
		}else {
			return new ResponseEntity<Usuarios>(entidad, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Usuarios>(entidad, HttpStatus.OK);
	}

}
