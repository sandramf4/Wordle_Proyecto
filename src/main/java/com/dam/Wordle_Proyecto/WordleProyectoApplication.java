package com.dam.Wordle_Proyecto;

import java.sql.SQLException;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dam.Wordle_Proyecto.repositorio.RepositoryWordle;

@SpringBootApplication
public class WordleProyectoApplication {

	public static void main(String[] args) throws SQLException {
		int opcion;
		  do {
			  mostrarMenu();
			  Scanner sc=new Scanner(System.in);
			  opcion=sc.nextInt();
			  switch(opcion) {
				  case 1:
					  datosUsuario();
					  break;
				  case 2:
					  jugar();
					  break;
				  case 3:
					  break;
			  }
		  }while(opcion!=3);
		SpringApplication.run(WordleProyectoApplication.class, args);
		
	}
	public static void mostrarMenu() {
        System.out.println("------ Menú ------");
        System.out.println("1. Insertar Usuario");
        System.out.println("2. Jugar");
        System.out.println("3. Salir");
        System.out.print("Selecciona una opción: ");
    }
	static String nombre=null;
  public static void datosUsuario() throws SQLException {
	  if (nombre == null) {
		  Scanner sc=new Scanner(System.in);
		  System.out.println("Bienvenido dime tu email");
		  	nombre=sc.nextLine();
		  System.out.println("nombre");
		  	String email=sc.nextLine();
          System.out.println("Jugador " + nombre + " insertado correctamente.");
          RepositoryWordle.insertarUsuarios(nombre,email);
      } else {
          System.out.println("Ya se ha insertado un jugador (" + nombre + "). No se permite insertar otro.");
      }
	  
  }
  public static void jugar() {
      if (nombre == null) {
          System.out.println("Debes insertar un jugador antes de jugar.");

      } else {
          System.out.println("¡Jugando como " + nombre + "!");
          
      }
  }
  

}
