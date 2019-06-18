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
 * Clase abstracta Liga 
 */
public class Liga {
    //atributos de la clase liga
    private String pais;
    private String nombre;
    private int id;
    
    private static Connection miConexion=Conexion.getConnection();
    
    public Liga(int id,String nombre,String pais) {
        this.id=id;
        this.nombre = nombre;
        this.pais = pais;
    }
    // Getters y Setters de la clase liga
    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   
    }
   	

		
        /*
        public static Object[] listarJugador(int id,String nombre,String apellidos,double altura, String posicion,double sueldo) {
            Object[] resultado = null;
            try {


                ArrayList<Jugador> jugador = new ArrayList<>();
                String lineaSQL;
                PreparedStatement preparedStmt;
                preparedStmt = miConexion.prepareStatement("SELECT * FROM jugador WHERE equipo=" + nombre);

               

                ResultSet result;
                result = preparedStmt.executeQuery();
                while (result.next()) {
                    jugador.add(result.getJugador("jugador"));
                }
                new Jugador(jugador);

            } catch (Exception ex) {
                Logger.getLogger(Jugador.class.getName()).log(Level.SEVERE, null, ex);
            }
            return resultado;
        }
        */
