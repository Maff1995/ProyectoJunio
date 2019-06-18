/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;



/**
 *
 * @author Miguel Ángel
 * Esta clase hereda de la clase Equipo que hereda de Ligas 
 * por lo cual esta clase recibira un super con todos los atibutos
 */
public class Jugador {
        // Atributos de la clase
        private int id;
        private String nombre;
        private String apellidos;
        private double altura;
        private String posicion;
        private double sueldo;
        private static Connection miConexion=Conexion.getConnection();
        private Equipo miEquipo;

        // constructor de la clase Jugador que recibe los atributos de jugador y los que recibe de herencia
        public Jugador(int id,String nombre,String apellidos, double altura, String posicion,double sueldo,Equipo miEquipo) {
       
        this.id=id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.altura = altura;
        this.posicion = posicion;
        this.sueldo = sueldo;
        this.miEquipo=miEquipo;
        
    }
    // Getters y Setter de la clase jugador
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }
     public Equipo getMiEquipo() {
        return miEquipo;
    }

    public void setMiEquipo(Equipo miEquipo) {
        this.miEquipo = miEquipo;
    }

	
        /*
        public static Object[] listarJugador(int id,String nombre,String apellidos,double altura, String posicion,double sueldo) {
            Object[] resultado = null;
            try {


                ArrayList<Jugador> jugador = new ArrayList<>();
                
                PreparedStatement preparedStmt;
                preparedStmt = miConexion.prepareStatement("SELECT * FROM jugador WHERE equipo=?");

               

                ResultSet result;
                result = preparedStmt.executeQuery();
                while (result.next()) {
                    
                }
                new Jugador(2,"","",0.0,"",0,"",0.0,"","");

            } catch (Exception ex) {
                Logger.getLogger(Jugador.class.getName()).log(Level.SEVERE, null, ex);
            }
            return resultado;
        }
        
*/

    public int getEquipo() {
    return miEquipo.getId();
    
    }
        
}
