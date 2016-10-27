package servlet;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * M�dulo de conexi�n y manejo de una BD.
 * 
 * Lista con par�metros �tiles:
 * 
 *      Driver: "com.mysql.jdbc.Driver" -> Para el manejo de BD en mysql
 *      
 *      Host: "jdbc:mysql://localhost/nombredelabd" -> Conexi�n a la BD de mysql con el XAMPP
 *      User: "root" -> Usuario por defecto
 *      Password: "" -> Contrase�a por defecto
 * 
 * Nota: Importante importar los Drivers para no tener tener error de conexi�n!
 * 
 * Errores comunes:
 * 
 *  - Drivers no importados.
 * 
 *  - Error al conectar la BD por par�metros (driver, host, user, password).
 * 
 *  - Error al no tener el XAMPP abierto.
 * 
 * @author Jose David P�rez Ca�ellas
 */
public class JavaDB {
    
    //Conexi�n con la Base de Datos
    private Connection connection = null;
    
    //Sentencia a ejecutar en la base de Datos
    private Statement statement = null;
    
    //Controla si la conexi�n con la Base de Datos est� activa
    private boolean connected;

    /**
     * Permite crear una conexi�n a una BD.
     * En caso de haber un error a la hora de conectarse
     * lanza una excepci�n y establece el estado de la conexi�n "false".
     * @param driver String que contiene el Driver a usar.
     * @param host String que contiene la URL del host de la BD.
     * @param user String que contiene el usuario de la BD.
     * @param password String que contiene la contrase�a de dicho usuario.
     */
    public JavaDB(String driver, String host, String user, String password) {
        try {
            setDriver(driver);
            setConnection(host, user, password);
            if(isConnected())
                createStatement();
        } catch (SQLException ex) {
            System.out.println("SQLException");
            setConnectionState(false);
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFound");
            setConnectionState(false);
        }
    }
    
    /**
     * Establece la query que se va a ejecutar.
     * Este m�todo es exclusito para Inserts, Updates y Deletes.
     * @param sql String que contiene la query a ejecutar.
     * @return En el caso de haber un error de SQL devuelve false.
     */
    public boolean setStatementDataModify(String sql){
        try {
            return statement.executeUpdate(sql) != 0;
        } catch (SQLException ex) {
            System.out.println("Error en la query" + ex);
            return false;
        }
    }
    
    /**
     * Permite ejecutar y obtener el resultado de una select.
     * @param sql Sentencia sql de la select.
     * @return 
     */
    public ResultSet getStatementDataSelect(String sql){
        ResultSet rs=null;
        try {
            rs= statement.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(JavaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    /**
     * Obtiene el estado de la conexi�n con la BD.
     * @return 
     */
    public final boolean isConnected() {
        return connected;
    }
    
    /**
     * Cierra la conexi�n con la base de datos.
     */
    public void closeDB(){
        try {
            statement.close();
            connection.close();
            setConnectionState(false);
        } catch (SQLException ex) {
            
        }
    }

    /**
     * Crea una sentencia para poder ejecutar una query.
     * @throws SQLException 
     */
    private void createStatement() throws SQLException{
        statement = connection.createStatement();
    }

    /**
     * Establece la conexi�n con la BD.
     * @param host Direcci�n de la BD (url).
     * @param user Usuario de la BD.
     * @param password Contrase�a de dicho usuario.
     * @throws SQLException 
     */
    private void setConnection(String host, String user, String password) throws SQLException {
        connection = DriverManager.getConnection(host, user, password);
        setConnectionState(true);
    }

    /**
     * Establece el driver de la conexi�n
     * @param driver Driver que utilizaremos, necesario para manejar la BD
     * @throws ClassNotFoundException 
     */
    private void setDriver(String driver) throws ClassNotFoundException{
        Class.forName(driver);
    }

    /**
     * Establece el estado de la conexi�n al inicializar el constructor.
     * @param state 
     */
    private void setConnectionState(boolean state) {
        connected = state;
    }
}
