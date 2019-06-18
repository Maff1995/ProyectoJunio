/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;


/**
 *
 * @author Miguel Ángel
 * esta clase hereda de Liga
 */
public  class Equipo implements IEquipo{
    private int id;
    private String nombre;
    private double presupuesto;

    private Liga miLiga;
  

   
   
    
    /**
     * Coonstructor de la clase equipo
     * que recibira los atributos de la clase que sera liga
     *
     **/
    public Equipo(int id,String nombre,double presupuesto,Liga miLiga) {
        
        this.id=id;
        this.nombre = nombre;
        this.presupuesto = presupuesto;
        this.miLiga=miLiga;
     }
    
    // Getters y Setters de todos los atributos de la clase Equipo
    
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

    public double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
    }

    
    public Liga getMiLiga() {
        return miLiga;
    }

    public void setMiLiga(Liga miLiga) {
        this.miLiga = miLiga;
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

    @Override
    public String getLiga() {
   return getMiLiga().getNombre();
    }

    @Override
    public int getIdLiga() {
    return getMiLiga().getId();
    }

 
   
  
 

   
   

 
 }

