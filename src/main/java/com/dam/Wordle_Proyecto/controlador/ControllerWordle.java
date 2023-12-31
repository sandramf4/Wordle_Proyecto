package com.dam.Wordle_Proyecto.controlador;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dam.Wordle_Proyecto.repositorio.RepositoryWordle;
import com.dam.Wordle_Proyecto.servicio.ServicesWordle;

@Controller
public class ControllerWordle {
	public static Connection conexion; 
	@Autowired
	protected ServicesWordle conexServicio;
	@Autowired
	protected RepositoryWordle repWordle;
	
	@ResponseBody
	@GetMapping("/guardarPalabras")
	public String guardarPalabras() {
		
		
		int numPalabras = conexServicio.gestionarFicheroPalabras();
		
		return "Se han insertado " + numPalabras;
	 
	}
	@ResponseBody
	@GetMapping("/verPalabras")
	public String verPalabras() {
		
		
		conexServicio.verPalabras();
		
		return "";
	 
	}
	@ResponseBody
	@GetMapping("/crearTablas")
	public String crearTablas() {
		repWordle.createTable();
		return"tablas creadas";
	}
	
	
	@ResponseBody
	@GetMapping("/insertar")
	public String insertar() throws SQLException, IOException {
		repWordle.insertarPalabrasCategoria();
		return "datos insertados";
	}
}
