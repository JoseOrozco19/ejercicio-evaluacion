package com.example.ejercicioevaluacion.interfacesService;

import java.util.List;

import com.example.ejercicioevaluacion.model.Usuarios;

public interface IUsuariosService {

	Usuarios save(Usuarios entidad);
	
	void delete(Integer id);
	
	Usuarios get(Integer id);
	
	List<Usuarios> getAll();
}
