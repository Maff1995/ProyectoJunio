/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Jugador;
import modelo.RepositorioEquipo;
import modelo.RepositorioJugador;
import vista.Secundaria;

/**
 *
 * @author Miguel Ángel
 */
public class ControladorJugador {
    private Secundaria vistaJugador;
    
    public ControladorJugador(Secundaria vistaJugador){
        this.vistaJugador=vistaJugador;
    }

    public ControladorJugador() {
    }
    public   void InsertarJugador( String nombre, String apellido , String posicion ,double sueldo, double altura, String equipo) throws SQLException {
        int id = 0;
      
        
        if ("".equals(nombre) || "".equals(apellido) || "".equals(altura) || "".equals(posicion) || "".equals(sueldo) || "Elija un equipo".equals(equipo)) {
                JOptionPane.showMessageDialog(null, "Revise los campos, no puede haber campos vacios", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
            String idEquipo = idEquipo(equipo);
            RepositorioJugador.insertarJugador(id, idEquipo, nombre, apellido, altura, posicion, sueldo);
        
        }
    }
    public static ArrayList<String> llenarComboEquipo() throws SQLException {
        ArrayList<String> lista=new ArrayList<>();
        lista =  RepositorioEquipo.llenarCombo();
        return lista;    
    }
    public static ArrayList<Jugador>mostrarJugadores() throws SQLException{
       
       ArrayList<Jugador> jugadores=new ArrayList<>();
        jugadores =  RepositorioJugador.listarJugadores();
        
        return jugadores;    
    }
    public static void borrarJugador(int id){
        RepositorioJugador.eliminarJugador(id);
        
    }
    public static void actualizarJugador(String id,String nombre,String apellidos,double altura, String posicion,double sueldo,String nEquipo) throws SQLException{
          System.out.println(Integer.parseInt(id)+ nombre+ apellidos+ altura+ posicion+ sueldo+nEquipo);
         if ("".equals(nombre) || "".equals(apellidos) || "".equals(altura) || "".equals(posicion) || "".equals(sueldo) || "Elija un equipo".equals(nEquipo)) {
                JOptionPane.showMessageDialog(null, "Revise los campos, no puede haber campos vacios", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
            String idEquipo = idEquipo(nEquipo);
        RepositorioJugador.actualizarJugador(Integer.parseInt(id), nombre, apellidos, altura, posicion, sueldo,idEquipo);
      
        
    }
    }
    
      public static String nombreEquipo(int id) throws SQLException{
        String result="";
       result=RepositorioJugador.devolverNombre(id);
    
    return result;
    }
    
    
    public static String idEquipo(String nombre) throws SQLException{
        String result="";
       result=RepositorioJugador.devolverId(nombre);
    
    return result;
    }
    
    
    
}
