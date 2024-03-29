/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Miguel Ángel
 */

public class Conexion {
	
	//Atributo que gestiona la conexión con la BBDD
	private static Connection miConexion;
	private String host; //Host que contiene la BBDD
	private String bbdd; //Nombre de la BBDD
	private String login; //Login 
	private String password; //Password
	private boolean estado=false;//Estado de la conexión
	private static Connection con=null;
      
        
        public static Connection getConnection(){
      try{
         if( con == null ){
            String driver="com.mysql.jdbc.Driver"; 
          
            Class.forName(driver);
            new BBDD("127.0.0.1","root","").crearDDBB();
            con = DriverManager.getConnection("jdbc:mysql://localhost/liga?autoReconnect=true","root","");
            System.out.println("Conectionesfull");
         }
     }
     catch(ClassNotFoundException | SQLException ex){
        ex.printStackTrace();
     }
     return con;
   }
	
	public Conexion(String host, String bbdd, String login, String password) 
	{
		this.host=host;
		this.bbdd=bbdd;
		this.login=login;
		this.password=password;		
		new BBDD(host, login, password).crearDDBB();	
		
	}
	
	/*Método: conectar()
	Tipo: boolean
	Parámetros: ninguno
	Devuelve: booleano que indica el estado de la conexión
	Funcionalidad: Realiza la conexión a la base de datos y pone el indicador de estado a true
	en el caso de que la conexión se haya realizado de manera correcta */
	
	public boolean conectar() throws Exception
	{
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
         // Setup the connection with the DB
         
		//conexion normal
		
		//miConexion= DriverManager.getConnection("jdbc:mysql://"+this.host+"/"+this.bbdd+"?user="+this.login+"&password="+this.password);

		//conexión completa para evitar errores de sincronizacion con el servidor
		miConexion= DriverManager.getConnection("jdbc:mysql://"+this.host+"/"+this.bbdd+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Madrid&user="+this.login+"&password="+this.password);
		
        
		this.estado=true;
		} 
		catch (Exception e) {
			e.printStackTrace();
           }
         
		return this.estado;
	}
	
	/*Método: getConexion()
	Tipo: Connection
	Parámetros: ninguno
	Devuelve: Connection
	Funcionalidad: Devuelve la conexión a la base de datos para poder realizar sentencias
	contra la misma */
	
	public Connection getConexion()
	{
		return miConexion;
	}
	
	/*Método: getEstado()
	Tipo: boolean
	Parámetros: ninguno
	Devuelve: boolean
	Funcionalidad: Devuelve si la conexión está establecida o no*/
	
	public boolean getEstado()
	{
		return estado;
		
	}
        
        
	
	/*Método: cerrarConexion()
	Tipo: boolean
	Parámetros: ninguno
	Devuelve: boolean
	Funcionalidad: Devuelve true si ha cerrado la conexión a la BBDD y false en caso de que
	este cierre no se haya podido llevar a cabo*/
	
	public boolean cerrarConexion() throws Exception
	{
		boolean seCerro=false;
		try
		{
			this.miConexion.close();
			seCerro=this.miConexion.isClosed();
			
		}catch(SQLException se){
			se.printStackTrace();
		}
		return seCerro;
		
	}
	
	

}//fin de clase




