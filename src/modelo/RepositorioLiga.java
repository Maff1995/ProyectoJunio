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
public abstract class RepositorioLiga {

    public ArrayList<Liga> ligas = new ArrayList<Liga>();
    private ArrayList<Equipo> equipo = new ArrayList<Equipo>();
    private static Connection miConexion = Conexion.getConnection();

    /**
     * Metodo solo esta definido ya que es abstracta pero la funcion esta en el
     * repositorioEquipo
     *
     * @param id
     * @param nombre
     * @param pais
     * @return 
          *
     */

    
    public static boolean insertarLiga(int id, String nombre, String pais) {
        boolean result = false;
        try {

            PreparedStatement preparedStmt;
            preparedStmt = miConexion.prepareStatement("insert into Liga values (?,?,?)");
            preparedStmt.setInt(1, id);
            preparedStmt.setString(2, nombre);
            preparedStmt.setString(3, pais);

            preparedStmt.executeUpdate();

            result = true;
            JOptionPane.showMessageDialog(null, "Insertada correctamente", "", JOptionPane.PLAIN_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "No puede existir dos ligas con el mismo nombre", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return result;

    }
    /**
     * Metodo que elimina Liga.
     *
     * @param id Recibe el id que sera por lo que busque la liga ya que es su
     * clave primaria
     * @return devuelve un boolean eliminado true si consigue borrar la liga y
     * un false si no lo hace
     *
     */
    public static boolean eliminarLiga(int id) {
        boolean eliminado = false;
        try {

            //Cadena donde irán las sentencias sql de creación de tablas
            PreparedStatement preparedStmt;
            //comando sql para la eliminacion por codigo
            preparedStmt = miConexion.prepareStatement("delete from Liga where id = ?");
            preparedStmt.setInt(1, id);

            eliminado = true;

            preparedStmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Eliminado correctamente", "", JOptionPane.PLAIN_MESSAGE);
        } catch (Exception ex) {
            //Logger.getLogger(Liga.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "No borrado, contiene equipos", "Error", JOptionPane.ERROR_MESSAGE);

        }
        return eliminado;
    }

    /**
     * Metodo que actualiza una liga.
     *
     * Recibe todos los parametros para poder modificar todos lo campos y poder
     * actualizar
     *
     * @param id
     * @param nombre
     * @param pais Nos permite actualizar los datos de una liga de la bbdd
     * @return devuelve un boolean modificado true si consigue hacer la
     * actualizacion y un false si no lo hace
     * @throws java.sql.SQLException
     *
     */

    public static boolean actualizarLiga(int id, String nombre, String pais) throws SQLException {
        boolean modificado = false;

        PreparedStatement preparedStmt;

        preparedStmt = miConexion.prepareStatement("update Liga set pais=?, nombre=? WHERE id=?");
        try {

            preparedStmt.setString(1, pais);
            preparedStmt.setString(2, nombre);
            preparedStmt.setInt(3, id);

            modificado = true;
            preparedStmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Actualizado correctamente", "", JOptionPane.PLAIN_MESSAGE);
        } catch (SQLException se) {
            se.printStackTrace();
            JOptionPane.showMessageDialog(null, "No actualizada", "Error", JOptionPane.ERROR_MESSAGE);

        }

        return modificado;
    }

    /**
     * Metodo que lista ligas. Arraylist de Liga
     *
     * @return devuelve ligas que sera un arraylist de Liga con todos las ligas
     * que hay en el mismo
     * @throws java.sql.SQLException  
     *
     */

    public static ArrayList<Liga> listarLigas() throws SQLException {
        ArrayList<Liga> ligas = new ArrayList<>();
        String lineaSQL = "Select * from Liga";
        PreparedStatement preparedStmt;
        preparedStmt = miConexion.prepareStatement(lineaSQL);
        ResultSet resultado = preparedStmt.executeQuery();
        while (resultado.next()) {
            int id = resultado.getInt("id");
            String nombre = resultado.getString("nombre");
            String pais = resultado.getString("pais");

            Liga liga = new Liga(id, nombre, pais) {
            };
            ligas.add(liga);
        }

        return ligas;
    }

    /**
     * Metodo que llena un jcombobox. Arraylist llenarCombo
     *
     * @return devuelve lista que sera un flitrado con una consulta para que
     * añada al combobox todos los nombres de los ligas que hay
     * @throws java.sql.SQLException
          *
     */
    public static ArrayList<String> llenarCombo() throws SQLException {
        ArrayList<String> lista = new ArrayList<String>();
        String lineaSQL = "Select * from Liga";
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
     * cada id esta asignada a un nombre de una liga.
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
}
