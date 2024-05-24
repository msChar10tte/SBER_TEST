import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class BDUtils {
//    private static String dbURL = "jdbc:h2:mem:test; INIT=RUNSCRIPT FROM 'classpath:init.sql";
//    private static String dbUsername = "sa";
//    private static String dbPassword = "";

    public static Connection getConnection(){

        String dbURL = null;
        String dbUsername = "sa";
        String dbPassword = "";

        FileInputStream fis;
        Properties properties =new Properties();


        try {
            fis = new FileInputStream("src/main/resources/config.properties");
            properties.load(fis);

            dbURL = properties.getProperty("db.host");

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        Connection connection = null;

        try {
            connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
