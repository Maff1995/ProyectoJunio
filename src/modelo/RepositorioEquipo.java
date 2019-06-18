/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Miguel Ángel
 */
public class RepositorioEquipo {

    // Arraylist de tipo jugador que almacenara los jugadores
    public static ArrayList<Liga> liga = new ArrayList<Liga>();
    private static Connection miConexion = Conexion.getConnection();

    /**
     * Metodo que inserta equipo.
     *
     * @param id
     * @param nLiga
     * @param nombre
     * @param presupuesto Esta funcion recibe los parametros para la inserccion
     * @return devuelve un boolean result true si consigue hacer la insercion y
     * un false si no lo hace
     *
     */
    public static boolean insertarEquipo(int id, String nLiga, String nombre, double presupuesto) {
        boolean result = false;
        try {

            PreparedStatement preparedStmt;
            preparedStmt = miConexion.prepareStatement("insert into equipo values (?,?,?,?)");
            preparedStmt.setInt(1, id);
            preparedStmt.setString(2, nLiga);
            preparedStmt.setString(3, nombre);
            preparedStmt.setDouble(4, presupuesto);

            preparedStmt.executeUpdate();

            result = true;
            JOptionPane.showMessageDialog(null, "Insertado correctamente", "", JOptionPane.PLAIN_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "No puede existir dos equipos con el mismo nombre", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return result;

    }

    /**
     * Metodo que elimina equipo.
     *
     * @param id Recibe el id que sera por lo que busque el equipo ya que es su
     * clave primaria y no habra dos iguales
     * @return devuelve un boolean eliminado true si consigue borrar el equipo y
     * un false si no lo hace
     *
     */
    public static boolean eliminarEquipo(int id) {
        boolean eliminado = false;
        try {

            //Cadena donde irán las sentencias sql de creación de tablas
            PreparedStatement preparedStmt;
            //comando sql para la eliminacion por codigo
            preparedStmt = miConexion.prepareStatement("delete from Equipo where id = ?");
            preparedStmt.setInt(1, id);

            eliminado = true;
            
            preparedStmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Eliminado correctamente", "", JOptionPane.PLAIN_MESSAGE);
        } catch (Exception ex) {
            // Logger.getLogger(Equipo.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "No borrado, contiene jugadores", "Error", JOptionPane.ERROR_MESSAGE);

        }
        return eliminado;
    }

    /**
     * Metodo que actualiza equipo.
     *
     * @param id
     * @param nLiga
     * @param nombre
     * @param presupuesto Recibe todos los parametros para poder modificar todos
     * lo campos y poder actualizar
     * @return devuelve un boolean modificado true si consigue hacer la
     * actualizacion y un false si no lo hace
     * @throws java.sql.SQLException
     *
     */
    
    public static boolean actualizarEquipo(int id, String nombre, int nLiga, double presupuesto) throws SQLException {
        boolean modificado = false;

        PreparedStatement preparedStmt;
        System.out.println(id + nombre + nLiga + presupuesto);
        preparedStmt = miConexion.prepareStatement("update Equipo set  nLiga=?,presupuesto=?, nombre=? WHERE id=?");
        try {
            preparedStmt.setInt(1, nLiga);
            preparedStmt.setDouble(2, presupuesto);
            preparedStmt.setString(3, nombre);
            preparedStmt.setInt(4, id);

            modificado = true;
            preparedStmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Actualizado correctamente", "", JOptionPane.PLAIN_MESSAGE);
        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null, "No puede existir dos equipos con el mismo nombre", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return modificado;
    }

    /**
     * Metodo que lista equipos. Arraylist de equipos
     *
     * @return devuelve equipos que sera un arraylist de Equipo con todos los
     * equipos que hay en el array
     * @throws java.sql.SQLException
          *
     */

    public static ArrayList<Equipo> listarEquipos() throws SQLException {
        
        ArrayList<Equipo> equipos = new ArrayList<>();
        String lineaSQL = "Select * from Equipo";
        PreparedStatement preparedStmt;
        preparedStmt = miConexion.prepareStatement(lineaSQL);
        ResultSet resultado = preparedStmt.executeQuery();
        while (resultado.next()) {
            int id = resultado.getInt("id");
            String nombre = resultado.getString("nombre");
            double presupuesto = resultado.getDouble("presupuesto");
            String nLiga = resultado.getString("nLiga");
           
            Liga liga = new Liga(Integer.parseInt(nLiga),null,null);
            Equipo equipo = new Equipo(id, nombre, presupuesto,liga);
            equipos.add(equipo);
        }

        return equipos;
    }

    /**
     * Metodo que llena un jcombobox. Arraylist llenarCombo
     *
     * @return devuelve lista que sera un flitrado con una consulta para que
     * añada al combobox todos los nombres de los equipos que hay
     * @throws java.sql.SQLException
          *
     */

    public static ArrayList<String> llenarCombo() throws SQLException {
        ArrayList<String> lista = new ArrayList<String>();
        String lineaSQL = "Select * from Equipo";
        PreparedStatement preparedStmt;
        preparedStmt = miConexion.prepareStatement(lineaSQL);
        ResultSet resultado = preparedStmt.executeQuery();
        while (resultado.next()) {
            lista.add(resultado.getString("nombre"));
        }

        return lista;
    }
    
    
    /**
     * Metodo que devuelve un nombre dependiendo de la id que le pasemos ya que
     * cada id esta asignada a un nomnre de equipo.
     *
     * @param id
     * @return devuelve el nombre asociado a la id que se le pase
          *
     */

    public static String devolverNombre(int id) throws SQLException {
        String nombre = "";
        PreparedStatement preparedStmt;

        preparedStmt = miConexion.prepareStatement("Select nombre from Liga WHERE id=?");
        try {

            preparedStmt.setInt(1, id);

            ResultSet resultado = preparedStmt.executeQuery();
            while (resultado.next()) {
                nombre = resultado.getString("nombre");
            }

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(Equipo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return nombre;

    }

    /**
     * Metodo que devuelve un id dependiendo de nombre que le pasemos ya que
     * cada id esta asignada a un nombre de equipo.
     *
     * @param nombre
     * @return devuelve la id asociada al nombre que se le pase
     * @throws java.sql.SQLException
          *
     */

    public static String devolverId(String nombre) throws SQLException {

        String id = "";
        PreparedStatement preparedStmt;

        preparedStmt = miConexion.prepareStatement("Select id from Liga WHERE nombre=?");
        try {

            preparedStmt.setString(1, nombre);

            ResultSet resultado = preparedStmt.executeQuery();
            while (resultado.next()) {
                id = resultado.getString("id");
            }

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(Equipo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return id;

    }

}
