/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Liga;
import modelo.RepositorioLiga;
import vista.Secundaria;

/**
 *
 * @author Miguel Ángel
 */
public class ControladorLiga {
    private Secundaria vistaLiga;
   
    public ControladorLiga(Secundaria vistaLiga){
        this.vistaLiga=vistaLiga;
    }
    public static void InsertarLiga(String id,String nombre,String pais) throws SQLException{
        
       
        
        if ("".equals(nombre) || "".equals(pais)){
            JOptionPane.showMessageDialog(null,"Revise los campos, no puede haber campos vacios","Error", JOptionPane.ERROR_MESSAGE);
        }
        else{
            RepositorioLiga.insertarLiga(Integer.parseInt(id),nombre,pais); 
        }
     
    }
    public static ArrayList<Liga>mostrarLigas() throws SQLException{
       ArrayList<Liga> ligas=new ArrayList<>();
        ligas =  RepositorioLiga.listarLigas();
        
        return ligas;    
    }
      
    public static void borrarLiga(String id){
        RepositorioLiga.eliminarLiga(Integer.parseInt(id));
        
    }
    public static void actualizarLiga(String id,String nombre,String pais) throws SQLException{
        System.out.println(nombre+pais);
       
         if ("".equals(nombre) || "".equals(pais)){
            JOptionPane.showMessageDialog(null,"Revise los campos, no puede haber campos vacios","Error", JOptionPane.ERROR_MESSAGE);
        }
        else{
             RepositorioLiga.actualizarLiga(Integer.parseInt(id),nombre,pais);; 
        }
        
    }
    
    
   
}
