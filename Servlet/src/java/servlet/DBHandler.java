

package servlet;

// @author: Oriol Iglesias

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DBHandler {
    private static JavaDB obj;

    public DBHandler() {
    }
    
    public boolean conexio() {
        String driver = "com.mysql.jdbc.Driver";
        String host = "jdbc:mysql://localhost/School";
        String user = "Oriol";
        String password = "1234";
        obj = new JavaDB(driver, host, user, password);
        return obj.isConnected();
    }
    
    public ArrayList<String> nombreAlumnos(){
        ResultSet rs= obj.getStatementDataSelect("select nom from alumne");
        ArrayList<String> nombres=new ArrayList<String>();
        try {
            while(rs.next()){
                nombres.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nombres;
    }
    
    public ArrayList<String> tutoriasAlumno(String name){
        ResultSet rs= obj.getStatementDataSelect("select tt.nom from alumne al, tutoriaalumne ta, tutoria tt where al.codi=ta.codiAlumne "
                + "and ta.codiTutoria=tt.codi and ta.actiu=1 and al.nom='" + name + "'");
        ArrayList<String> tutorias=new ArrayList<String>();
        try {
            while(rs.next()){
                tutorias.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tutorias;
    }
            
    public ArrayList<String> asignaturasAlumno(String name){
        ResultSet rs= obj.getStatementDataSelect("select ag.nom from alumne al, tutoriaalumne ta, tutoria tt, assignatura ag where al.codi=ta.codiAlumne "
                + "and ta.codiTutoria=tt.codi and tt.codiAssignatura=ag.codi and al.nom='" + name+"'");
        ArrayList<String> asignaturas=new ArrayList<String>();
        try {
            while(rs.next()){
                asignaturas.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return asignaturas;
    }
    
    

    
    
    
    
    
    
}
