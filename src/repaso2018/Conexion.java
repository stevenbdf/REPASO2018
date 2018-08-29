
package repaso2018;

import java.sql.Connection;
import java.sql.DriverManager;




public class Conexion {
    public Connection conectar() {
        Connection cn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            cn= DriverManager.getConnection("jdbc:sqlserver://USER-PC\\SQLEXPRESS;"
                    + "databaseName=partidos;user=sa;password=123;");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 
        return cn; 
    }
}
