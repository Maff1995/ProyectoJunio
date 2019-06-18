/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miguel Ángel
 */
public class BBDD {

    private static Connection miConexion=Conexion.getConnection();
    private String host;
    private String login;
    private String password;

    public BBDD(String host, String login,String password) {
        this.host = host;
        this.login = login;
        this.password = password;
   
    }
    /**
      *  metodo encargado de crear la base de datos. 
      * 
     */
    
    public boolean crearDDBB() {
        boolean creada=false;
        try {

            Class.forName("com.mysql.jdbc.Driver");

            this.miConexion = DriverManager.getConnection("jdbc:mysql://" + this.host + "/"
                    + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Madrid&user=" 
                    + this.login + "&password=" + this.password);
            Statement statement = miConexion.createStatement();
            String sql = "create database IF NOT EXISTS liga";
            statement.executeUpdate(sql);
            miConexion.close();
            
            
            creada=crearEstructura();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return creada;
    }
    
    /**
     * Metodo que se encarga de crear la estructura de la base de datos.
     */
    public boolean crearEstructura() {
        boolean creada=false;
        try {
            this.miConexion = DriverManager.getConnection("jdbc:mysql://"+this.host+"/"+"liga"
                    +"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Madrid&user="
                    +this.login+"&password="+this.password);
            
            Statement statement = this.miConexion.createStatement();
            String sql ="create table IF NOT EXISTS Liga(" +
                        "       id int primary key AUTO_INCREMENT,"+
                        "       nombre varchar(50) UNIQUE ," +                       
                        "	pais varchar(50)" +
                      
                        ")ENGINE=InnoDB ";
             statement.executeUpdate(sql);
             
             statement = this.miConexion.createStatement();
             sql ="create table IF NOT EXISTS Equipo(" +
                        "       id int primary key AUTO_INCREMENT,"+
                        "       nLiga int," + 
                        "       nombre varchar(50) UNIQUE," +                       
                        "	presupuesto double,"+
                     
                        "	foreign key (nLiga) references Liga(id)" +
                        ")ENGINE=InnoDB ";
             statement.executeUpdate(sql);
             
             statement = this.miConexion.createStatement();
             sql ="create table IF NOT EXISTS jugador(" +                       
                        "	id int primary key AUTO_INCREMENT," +
                        "       nEquipo int NOT NULL," +
                        "	nombre varchar(50)," +
                        "	apellidos varchar(50)," +
                        "	altura double," +
                        "	posicion varchar(600)," +
                        "	sueldo double,"+
                        "	foreign key (nEquipo) references Equipo(id)" +
                        ")ENGINE=InnoDB ";

            statement.executeUpdate(sql);
                this.miConexion.close();
            
                creada=true;
            
            
        } catch (Exception ex) {
            Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
        }   
        return creada;
        
    }
}
