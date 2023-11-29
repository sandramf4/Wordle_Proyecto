package com.dam.Wordle_Proyecto.repositorio;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;

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
	

}
