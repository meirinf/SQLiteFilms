import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Created by y2793623b on 12/12/16.
 */
public class selectSQLite {

    public static void main( String args[] )
    {

        Scanner scr = new Scanner(System.in);
        System.out.println("elige el numero del opcion que quieres : ");
        System.out.println("1) mostrar las peliculas . ");
        System.out.println("2) mostrar los actores . ");
        System.out.println("3) mostrar los actores de una pelicula . ");
        System.out.println("4) mostrar las peliculas de un actor . ");
        System.out.println("5) mostrar el actor seleccionado y su personaje");

        int opcion = scr.nextInt();

        switch (opcion)
        {
            case 1:
                sacar_peliculas();
                break;
            case 2:
                sacar_actores();
                break;
            case 3:
                relacion_pelicula();
                break;
            case 4:
                relacion_actor();
                break;
            default:
                break;
        }



    }


    private static void sacar_peliculas() {
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

    private static void sacar_actores() {

        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:movie.db");
            conn.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM ACTORES;" );
            while ( rs.next() ) {
                //Recogemos los datos de la base de datos con el tag
                int ida = rs.getInt("ID_ACTOR");
                String nombre_act =  rs.getString("Nombre_Actor");

                //Imprimimos
                System.out.println( "ID_ACTOR  = " + ida );
                System.out.println( "Nombre_Actor = " + nombre_act);
                System.out.println("");
            }
            System.out.println("");
            rs.close();
            stmt.close();
            conn.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }

    private static void relacion_pelicula() {

    }

    private static void relacion_actor() {

        Connection conn = null;
        Statement stmt = null;
        try {

            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:movie.db");
            conn.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM ACTORES;" );
            while ( rs.next() ) {
                //Recogemos los datos de la base de datos con el tag
                int ida = rs.getInt("ID_ACTOR");
                String nombre_act =  rs.getString("Nombre_Actor");
                String personaje = rs.getString("Personaje");

                //Imprimimos
                System.out.println("");
                System.out.println( "ID_ACTOR  = " + ida );
                System.out.println( "Nombre_Actor = " + nombre_act);
                System.out.printf("Personaje = "+personaje);
                System.out.println("");
            }
            System.out.println();
            rs.close();
            stmt.close();
            conn.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }

}
