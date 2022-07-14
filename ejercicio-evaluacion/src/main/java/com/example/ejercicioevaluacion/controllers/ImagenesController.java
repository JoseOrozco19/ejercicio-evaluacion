package com.example.ejercicioevaluacion.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.ejercicioevaluacion.model.RespuestaSubidaImagenes;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("api/v1/file")
@CrossOrigin("*")
public class ImagenesController {

	@Operation(summary = "Subida de Imágenes al Servidor, probar preferentemente con Postman. Se selecciona archivo, ruta dentro del servidor para guardar la imagen y el nombre de la imagen.")
	@PostMapping(value = "/upload")
	public RespuestaSubidaImagenes subirImagen(@RequestParam("file") MultipartFile file, @RequestParam("ruta") String ruta, @RequestParam("nombrearch") String nombrearch) {
		ruta = ruta.replace("\\", File.separator);	
		
		RespuestaSubidaImagenes response;
		
		if(file == null || file.isEmpty()) {
			response = new RespuestaSubidaImagenes(new Date().toString(), 501, "No se ha Seleccionado una Imagen");
			return response;
		}
		
		StringBuilder builder = new StringBuilder();
		builder.append("E:");
		builder.append(File.separator);
		builder.append("Orozco");
		builder.append(File.separator);
		builder.append("Documentos");
		builder.append(File.separator);
		builder.append(ruta);
		
		File directorio = new File(builder.toString());
		
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                System.out.println("Directorio creado");
            } else {
            	response = new RespuestaSubidaImagenes(new Date().toString(), 501, "Error al Crear Directorio");
    			return response;
            }
        }
		
		builder.append(File.separator);
		builder.append(nombrearch);
		
		byte[] fileBytes;
		
		try {
			
			fileBytes = file.getBytes();
			Path path = Paths.get(builder.toString());
			Files.write(path, fileBytes);
			
		} catch (IOException e) {
			response = new RespuestaSubidaImagenes(new Date().toString(), 501, "Error al Obtener los Bytes de la Imagen");
			return response;
		}
		
		response = new RespuestaSubidaImagenes(new Date().toString(), 200, File.separator + "Documentos" + File.separator + ruta + File.separator + nombrearch);
		
		return response;
	}
	
	@Operation(summary = "Eliminar imagen del servidor a partir de la ruta que tenga el registro en base de datos.")
	@GetMapping(value = "/delete")
	public RespuestaSubidaImagenes eliminarImagen(@RequestParam("nombrearch") String nombrearch) {
		
		RespuestaSubidaImagenes response;
		
		if(nombrearch.trim().equals("") || nombrearch == null) {
			response = new RespuestaSubidaImagenes(new Date().toString(), 501, "El nombre del Archivo está vacío");
			return response;
		}
		
		StringBuilder builder = new StringBuilder();
		builder.append("E:");
		builder.append(File.separator);
		builder.append("Orozco");
		builder.append(nombrearch);
		
		File directorio = new File(builder.toString());
		
        if (directorio.exists()) {
            if (directorio.delete()) {
            	response = new RespuestaSubidaImagenes(new Date().toString(), 200, "Imagen eliminada correctamente");
    			return response;
            } else {
            	response = new RespuestaSubidaImagenes(new Date().toString(), 501, "Error al eliminar Imagen");
    			return response;
            }
        }else {
        	response = new RespuestaSubidaImagenes(new Date().toString(), 501, "La Imagen a eliminar no existe");
			return response;
        }
	}
}