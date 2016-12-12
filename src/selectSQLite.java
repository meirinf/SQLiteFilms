
import java.sql.*;

/**
 * Created by dremon on 09/11/15.
 */
public class selectSQLite {

    public static void main(String[] args) {
            Connection c = null;
            Statement stmt = null;
            try {
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:movie.db");
                c.setAutoCommit(false);
                System.out.println("Opened database successfully");

                stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery( "SELECT * FROM FILMS;" );

                 while ( rs.next() ) {
                     //Recogemos los datos de la base de datos con el tag
                    int id = rs.getInt("id");
                    String  name = rs.getString("name");
                    int fecha_estreno  = rs.getInt("FECHA_ESTRENO");
                    //Imprimimos
                    System.out.println( "ID = " + id );
                    System.out.println( "NAME = " + name );
                    System.out.println( "FECHA_ESTRENO = " + fecha_estreno);
                    System.out.println();
                    }

                  ResultSet rs1 = stmt.executeQuery( "SELECT * FROM ACTORES;" );
                while ( rs1.next() ) {
                    //Recogemos los datos de la base de datos con el tag
                    int ida = rs1.getInt("ID_ACTOR");
                    String nombre_act =  rs1.getString("Nombre_Actor");
                    String personaje = rs1.getString("Personaje");

                    //Imprimimos
                    System.out.println( "ID_ACTOR  = " + ida );
                    System.out.println( "Nombre_Actor = " + nombre_act);
                    System.out.printf("Personaje = "+personaje);
                    System.out.println();
                }
                                  System.out.println();
                rs.close();
                stmt.close();
                c.close();
            } catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);
            }
            System.out.println("Operation done successfully");
        }

}


