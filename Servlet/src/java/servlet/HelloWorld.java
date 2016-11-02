/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Oriol
 */
public class HelloWorld extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet HelloWorld</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HelloWorld at " + request.getContextPath() + "</h1>");
            out.println("hola");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    
    Gson gson= new Gson();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DBHandler dbh= new DBHandler();
         if(dbh.conexio()){
            ArrayList<String> nombres= dbh.nombreAlumnos();
            ArrayList<Alumne> lista= new ArrayList<Alumne>();
            for(int i=0;i!=nombres.size();i++){
                String name= nombres.get(i);
                Alumne al= new Alumne(name);
                al.setTutorias(dbh.tutoriasAlumno(name));
                al.setAsignaturas(dbh.asignaturasAlumno(name));
                lista.add(al);
            }
            String json= gson.toJson(lista);
            request.setAttribute("json", json);
            response.setContentType("application/json");
            RequestDispatcher a = request.getRequestDispatcher("hola.jsp");
            a.forward(request, response);
         }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Se crea una conexion con la base de datos
        DBHandler dbh= new DBHandler();
        // Se comprueba que la conexion con la base de datos ha sido establecida antes de proceder
         if(dbh.conexio()){
             // Se carga una lista con los nombres de los alumnos de la base de datos
            ArrayList<String> nombres= dbh.nombreAlumnos();
            // Se crea una lista vacia para alumnos
            ArrayList<Alumne> lista= new ArrayList<Alumne>();
            // Se obtiene el nombre del alumno del qual se quiere saber la informacion
            String name= (String) request.getParameter("select");
            // Se recorre la lista de alumnos
            for(int i=0;i!=nombres.size();i++){
                // Se comprueba si el nombre del alumno coincide con el nombre de la lista
                if(name.equals(nombres.get(i))){
                // Se crea un objeto alumno y se le asigna su nombre
                Alumne al= new Alumne(name);
                // Se carga la lista de tutorias en el objeto alumno desde la base de datos
                al.setTutorias(dbh.tutoriasAlumno(name));
                // Se carga la lista de asignaturas en el objeto alumno desde la base de datos
                al.setAsignaturas(dbh.asignaturasAlumno(name));
                // Se añade el objeto alumno a la lista de alumnos
                lista.add(al);
                }
            }
            // Se crea un String el qual
            String json= gson.toJson(lista);
            request.setAttribute("json", json);
            response.setContentType("application/json");
            RequestDispatcher a = request.getRequestDispatcher("hola.jsp");
            a.forward(request, response);
    }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
