/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Equipo;
import modelo.RepositorioEquipo;
import modelo.RepositorioLiga;
import vista.Secundaria;

/**
 *
 * @author Miguel Ángel
 */
public class ControladorEquipo {

    private Secundaria vistaEquipo;

    public ControladorEquipo(Secundaria vistaEquipo) {
        this.vistaEquipo = vistaEquipo;
    }

    public static void InsertarEquipo(String nombre, double presupuesto, String nLiga) throws SQLException {
        int id = 0;

        if ("".equals(nombre) || "".equals(presupuesto) || "Elija una liga".equals(nLiga)) {
            JOptionPane.showMessageDialog(null, "Revise los campos, no puede haber campos vacios", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            String idEquipo = idEquipo(nLiga);
            RepositorioEquipo.insertarEquipo(id, idEquipo, nombre, presupuesto);
        }
        //Equipo e=new Equipo(id,nombre,presupuesto,nLiga,null);
    }

    public static ArrayList<String> llenarComboLiga() throws SQLException {
        ArrayList<String> lista = new ArrayList<>();
        lista = RepositorioLiga.llenarCombo();
        return lista;
    }

    public static ArrayList<Equipo> mostrarEquipos() throws SQLException {
        ArrayList<Equipo> equipos = new ArrayList<>();
        equipos = RepositorioEquipo.listarEquipos();

        return equipos;
    }

    public static void borrarEquipo(int id) {
        RepositorioEquipo.eliminarEquipo(id);

    }

    public static void actualizarEquipo(String id, String nombre, String nLiga, double presupuesto) throws SQLException {
        System.out.println(id + nombre + nLiga + presupuesto);
        
         if ("".equals(nombre) || "".equals(presupuesto) || "Elija una liga".equals(nLiga)) {
            JOptionPane.showMessageDialog(null, "Revise los campos, no puede haber campos vacios", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            String idEquipo = idEquipo(nLiga);
           RepositorioEquipo.actualizarEquipo(Integer.parseInt(id), nombre, Integer.parseInt(idEquipo), presupuesto);
        }
        
        
        
    }

    public static String nombreLiga(int id) throws SQLException {
        String result = "";
        result = RepositorioEquipo.devolverNombre(id);

        return result;
    }

    public static String idEquipo(String nombre) throws SQLException {
        String result = "";
        result = RepositorioEquipo.devolverId(nombre);

        return result;
    }

}
