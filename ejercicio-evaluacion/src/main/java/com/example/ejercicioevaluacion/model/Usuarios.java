package com.example.ejercicioevaluacion.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuarios {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_usuarios;
	
	private String user_nombre;
	private String user_correo;
	private String user_genero;
	private int user_estado;
	
	// Guardar la dirección de la imagen copiada dentro del servidor.
	private String user_direccion_imagen;
	
	public Usuarios() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId_usuarios() {
		return id_usuarios;
	}

	public void setId_usuarios(int id_usuarios) {
		this.id_usuarios = id_usuarios;
	}

	public String getUser_nombre() {
		return user_nombre;
	}

	public void setUser_nombre(String user_nombre) {
		this.user_nombre = user_nombre;
	}

	public String getUser_correo() {
		return user_correo;
	}

	public void setUser_correo(String user_correo) {
		this.user_correo = user_correo;
	}

	public String getUser_genero() {
		return user_genero;
	}

	public void setUser_genero(String user_genero) {
		this.user_genero = user_genero;
	}

	public int getUser_estado() {
		return user_estado;
	}

	public void setUser_estado(int user_estado) {
		this.user_estado = user_estado;
	}

	public String getUser_direccion_imagen() {
		return user_direccion_imagen;
	}

	public void setUser_direccion_imagen(String user_direccion_imagen) {
		this.user_direccion_imagen = user_direccion_imagen;
	}
	
}
