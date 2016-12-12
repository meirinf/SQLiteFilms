/**
 * Created by dremon on 09/11/15.
 */
import java.sql.*;

public class createSQLite {

    public static void main(String[] args) {
        {
            Connection c = null;
            Statement stmt = null;
            try {
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:movie.db");
                System.out.println("Opened database successfully");

                stmt = c.createStatement();

                String sql = "CREATE TABLE FILMS " +
                        "(ID INT PRIMARY KEY     NOT NULL," +
                        " NAME           TEXT    NOT NULL, " +
                        " FECHA_ESTRENO  DATE     NOT NULL) " ;

                String sqlActor = "CREATE TABLE ACTORES"+
                        "(ID_ACTOR INT PRIMARY KEY    NOT NULL,"+
                        "Nombre_Actor TEXT NOT NULL,"+
                        "Personaje TEXT NOT NULL)";


                stmt.executeUpdate(sql);
                stmt.executeUpdate(sqlActor);


                stmt.close();
                c.close();
            } catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);
            }
            System.out.println("Table created successfully");
        }

    }


}
