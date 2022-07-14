package com.example.ejercicioevaluacion.interfacesServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ejercicioevaluacion.interfaces.IUsuarios;
import com.example.ejercicioevaluacion.interfacesService.IUsuariosService;
import com.example.ejercicioevaluacion.model.Usuarios;

@Service
public class IUsuariosServiceImpl implements IUsuariosService {
	
	@Autowired
	IUsuarios iUsuarios;

	@Override
	public Usuarios save(Usuarios entidad) {
		return iUsuarios.save(entidad);
	}

	@Override
	public void delete(Integer id) {
		iUsuarios.deleteById(id);
	}

	@Override
	public Usuarios get(Integer id) {
		Optional<Usuarios> obOptional = iUsuarios.findById(id);
		if(obOptional.isPresent())
			return obOptional.get();
		
		return null;
	}

	@Override
	public List<Usuarios> getAll() {
		List<Usuarios> returnList = new ArrayList<>();
		iUsuarios.findAll().forEach(obj -> returnList.add(obj));
		
		return returnList;
	}

}
