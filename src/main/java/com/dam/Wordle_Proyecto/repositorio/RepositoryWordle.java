package com.dam.Wordle_Proyecto.repositorio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Scanner;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;
@Repository
@ResponseBody
public class RepositoryWordle {
	
public static Connection conexion; 
	 
	public static void abrirConexion() {
		String connectionUrl =
                "jdbc:mariadb://localhost:3306/wordle";
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conexion = DriverManager.getConnection(
                    connectionUrl, "root", "Mhlbwaves4");
            System.out.println("Conectado...");
        } catch (SQLException | ClassNotFoundException ce) {
            ce.printStackTrace();
        }
	}
	
	public static void cerrarConexion() {
		if (conexion != null) {
			try {
				conexion.close();
			} catch ( SQLException sqle) {
				sqle.printStackTrace();
			}
		}
	}

	public void createTable() {                                                    //metodo para crear tablas con jdbc
		abrirConexion();
	    String createTablePalabras = "CREATE TABLE IF NOT EXISTS Palabras (" +
	            "id_palabra INT AUTO_INCREMENT PRIMARY KEY," +
	            "Palabra VARCHAR(255) NOT NULL," +
	            "Categoria INT,"+
	            "Fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"+
	            "FOREIGN KEY (id_palabra) REFERENCES categoria(id_categoria))";
	    String createTableCategoria= "CREATE TABLE IF NOT EXISTS Categoria("+
	    		"id_categoria INT AUTO_INCREMENT PRIMARY KEY,"+
	    		"Categoria VARCHAR(100) NOT NULL)";
	    String createTableJugador= "CREATE TABLE IF NOT EXISTS Jugador (\r\n"
	    		+ "    id INT AUTO_INCREMENT PRIMARY KEY,"
	    		+ "    nombre VARCHAR(255) NOT NULL,"
	    		+ "    email VARCHAR(255) NOT NULL,"
	    		+ "    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";
	    String createTableIntentos="CREATE TABLE IF NOT EXISTS Intentos ("
	    		+ "    id INT AUTO_INCREMENT PRIMARY KEY,"
	    		+ "    palabra VARCHAR(255) NOT NULL,"
	    		+ "    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
	    		+ "    colorResultado VARCHAR(255) NOT NULL,"
	    		+ "    idJugador INT,"
	    		+ "    FOREIGN KEY (idJugador) REFERENCES Jugador(id));";
	    try {
	        Statement statement = conexion.createStatement();
	        //statement.execute(createTableCategoria);
	        //statement.execute(createTablePalabras);
	        statement.execute(createTableJugador);
	        statement.execute(createTableIntentos);
	    } catch (Exception e) {
	        e.printStackTrace(); 
	    }finally {
	    	cerrarConexion();
	    }
	}
		  public static Date FechaHoy() {
		    long miliseconds = System.currentTimeMillis();
		    Date date = new Date(miliseconds);
		    System.out.println(date);
		    return date;
		  }
		  
		  public void insertarPalabrasCategoria() throws SQLException, IOException {
				abrirConexion();
			
				String sql2 = "INSERT INTO categoria (nombre_categoria) VALUES(?)";
				PreparedStatement ps = conexion.prepareStatement(sql2);
				
				String sql = "INSERT INTO palabras (fecha, texto, id_categoria) VALUES (?,?,?)";
		        PreparedStatement preparedStatement = conexion.prepareStatement(sql);
		        
		        FileReader fr = new FileReader("C:\\Users\\SandraMegollaFernánd\\Documents\\csv_Wordle\\Palabras.csv");
		        BufferedReader br = new BufferedReader(fr);
				
				FileReader fr2 = new FileReader("C:\\Users\\SandraMegollaFernánd\\Documents\\csv_Wordle\\Categorias.csv");
		        BufferedReader br2 = new BufferedReader(fr2);
				
				String l;
		        while ((l = br2.readLine()) != null) {
		            ps.setString(1, l);
		            ps.executeUpdate();
		            
		        }
			
		        //preparedStatement.setDate(1, java.sql.Date.valueOf("2021-09-17"));
		        
		 
		        String linea;
		        while ((linea = br.readLine()) != null) {
		        	String string = linea;
		        	String [] parts = string.split(",");
		        	
		        	preparedStatement.setObject(1,LocalDateTime.now());
		            preparedStatement.setString(2, parts[0]);
		            preparedStatement.setInt(3, Integer.parseInt(parts[1]));
		            preparedStatement.executeUpdate();
		        }
		        
		        
		 
		        preparedStatement.close();
		        conexion.close();
			}
		  public static void insertarUsuarios(String nombre,String email) throws SQLException {
			  abrirConexion();
			  PreparedStatement ps_ = conexion.prepareStatement("INSERT INTO jugador (nombre,email,fecha) VALUES (?,?,?)");
		      ps_.setString(1,nombre);
		      ps_.setString(2,email);
		      ps_.setObject(3,LocalDateTime.now());

		      ps_.executeUpdate();
		      cerrarConexion();
		  }

		  }

