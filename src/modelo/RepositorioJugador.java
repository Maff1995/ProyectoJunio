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
public class RepositorioJugador {

    private static Connection miConexion = Conexion.getConnection();

    /**
     * Metodo que inserta jugador.
     *
     *
     * Esta funcion recibe los parametros para la inserccion
     *
     * @param id
     * @param nEquipo
     * @param nombre
     * @param apellidos
     * @param altura
     * @param posicion
     * @param sueldo Inserta un jugador en la base de datos con sus datos
     * @return devuelve un boolean result true si consigue hacer la insercion y
     * un false si no lo hace
     * @throws java.sql.SQLException
     *
     */
    public static boolean insertarJugador(int id, String nEquipo, String nombre, String apellidos, double altura, String posicion, double sueldo) throws SQLException {
        boolean result = false;
        int idLiga = 0;
        int idEquipo = 0;
        PreparedStatement preparedStmt;
        System.out.println(+id + nEquipo + nombre + apellidos + altura + posicion + sueldo);
        try {
            
             Equipo equipo = new Equipo(Integer.parseInt(nEquipo),null,0,null) ;
          
            Jugador j =  new Jugador(id, nombre, apellidos, altura, posicion, sueldo, equipo);
            preparedStmt = miConexion.prepareStatement("insert into jugador values (?,?,?,?,?,?,?)");
            preparedStmt.setInt(1, id);
            preparedStmt.setString(2, nEquipo);
            preparedStmt.setString(3, nombre);
            preparedStmt.setString(4, apellidos);
            preparedStmt.setDouble(5, altura);
            preparedStmt.setString(6, posicion);
            preparedStmt.setDouble(7, sueldo);;
            preparedStmt.executeUpdate();

            result = true;
            JOptionPane.showMessageDialog(null, "Insertado correctamente", "", JOptionPane.PLAIN_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se ha insertado correctamente", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return result;

    }

    /**
     * Metodo que elimina jugador.
     *
     * @param id Recibe el id que sera por lo que busque el jugador ya que es su
     * clave primaria
     * @return devuelve un boolean eliminado true si consigue borrar el jugador
     * y un false si no lo hace
     *
     */
    public static boolean eliminarJugador(int id) {
        boolean eliminado = false;
        try {

            //Cadena donde irán las sentencias sql de creación de tablas
            PreparedStatement preparedStmt;
            //comando sql para la eliminacion por codigo
            preparedStmt = miConexion.prepareStatement("delete from Jugador where id = ?");
            preparedStmt.setInt(1, id);

            eliminado = true;

            preparedStmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Eliminado correctamente", "", JOptionPane.PLAIN_MESSAGE);
        } catch (Exception ex) {
            Logger.getLogger(Jugador.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "No se ha eliminado", "Error", JOptionPane.ERROR_MESSAGE);

        }
        return eliminado;
    }

    /**
     * Metodo que actualiza un jugador.
     *
     * Recibe todos los parametros para poder modificar todos lo campos y poder
     * actualizar
     *
     * @param id
     * @param nombre
     * @param apellidos
     * @param altura
     * @param posicion
     * @param sueldo Nos permite actualizar los datos de un jugador de la bbdd
     * @param nEquipo
     * @return devuelve un boolean modificado true si consigue hacer la
     * actualizacion y un false si no lo hace
     * @throws java.sql.SQLException
     *
     */

    public static boolean actualizarJugador(int id, String nombre, String apellidos, double altura, String posicion, double sueldo, String nEquipo) throws SQLException {
        boolean modificado = false;

        PreparedStatement preparedStmt;

        preparedStmt = miConexion.prepareStatement("update Jugador set nombre=?,apellidos=?,altura=?,posicion=?,sueldo=?,nEquipo=? WHERE id=?");
        try {

            preparedStmt.setString(1, nombre);
            preparedStmt.setString(2, apellidos);
            preparedStmt.setDouble(3, altura);
            preparedStmt.setString(4, posicion);
            preparedStmt.setDouble(5, sueldo);
            preparedStmt.setString(6, nEquipo);
            preparedStmt.setInt(7, id);
            modificado = true;
            preparedStmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Actualizado correctamente", "", JOptionPane.PLAIN_MESSAGE);
        } catch (SQLException se) {
            se.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se ha actualizado", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return modificado;
    }

    /**
     * Metodo que lista jugadores. Arraylist de Jugador
     *
     * @return devuelve jugadores que sera un arraylist de Jugador con todos los
     * jugadores que hay en el mismo
     * @throws java.sql.SQLException  
     *
     */
    public static ArrayList<Jugador> listarJugadores() throws SQLException {
        
        ArrayList<Jugador> jugadores = new ArrayList<>();
        String lineaSQL = "Select * from Jugador";
        int idLiga = 0;
        int idEquipo = 0;
        PreparedStatement preparedStmt;
        preparedStmt = miConexion.prepareStatement(lineaSQL);
        ResultSet resultado = preparedStmt.executeQuery();
        while (resultado.next()) {
            int id = resultado.getInt("id");
            String nEquipo = resultado.getString("nEquipo");
            String nombre = resultado.getString("nombre");
            String apellidos = resultado.getString("apellidos");
            double altura = resultado.getDouble("altura");
            String posicion = resultado.getString("posicion");
            double sueldo = resultado.getDouble("sueldo");
            
             Equipo equipo = new Equipo(Integer.parseInt(nEquipo),null,0,null) ;
            Jugador jugador = new Jugador(id, nombre, apellidos, altura, posicion, sueldo, equipo);
            System.out.println(jugador.getMiEquipo());
            jugadores.add(jugador);
        }

        return jugadores;
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

        preparedStmt = miConexion.prepareStatement("Select nombre from Equipo WHERE id=?");
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
          *
     */

    public static String devolverId(String nombre) throws SQLException {

        String id = "";
        PreparedStatement preparedStmt;

        preparedStmt = miConexion.prepareStatement("Select id from Equipo WHERE nombre=?");
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
