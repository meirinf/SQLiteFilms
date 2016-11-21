
import java.sql.*;

/**
 * Created by dremon on 09/11/15.
 */
public class selectSQLite {

    public static void main(String[] args)
        {
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
                    int id = rs.getInt("id");
                    String  name = rs.getString("name");
                    int age  = rs.getInt("age");
                    System.out.println( "ID = " + id );
                    System.out.println( "NAME = " + name );
                    System.out.println( "AGE = " + age );
                    System.out.println();
                }
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


