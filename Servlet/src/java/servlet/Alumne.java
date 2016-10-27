

package servlet;

// @author: Oriol Iglesias

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Alumne {
String nom;
ArrayList<String> tutorias=new ArrayList<String>();
ArrayList<String> asignaturas=new ArrayList<String>();

    public Alumne(String nom) {
        this.nom = nom;
    }

    public void setTutorias(ArrayList<String> tutorias) {
        this.tutorias = tutorias;
    }

    public void setAsignaturas(ArrayList<String> asignaturas) {
        this.asignaturas = asignaturas;
    }

   
}
