/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;


/**
 *
 * @author Miguel Ángel
 */
public interface IEquipo {
    String getNombre();
    void setNombre(String nombre);      
    double getPresupuesto();
    void setPresupuesto(double presupuesto);
    String getLiga();
    int getIdLiga();
}
