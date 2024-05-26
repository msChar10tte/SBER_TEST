import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class BDUtils {
    /**
     * Создание и подключение к базе данных
     *
     * dbURL - передаем сюда URL для подключене к нужножной базе и скрип для созданеи базы данных(если нужно)
     * dbUsername - Передаём название базы
     * dbPassword - передаекм пороль
     *
     * */

    public static Connection getConnection(){

        String dbURL = null;
        String dbUsername = null;
        String dbPassword = null;

        FileInputStream fis;
        Properties properties =new Properties();

        /**
         * Подключаемся к базе через конфиг
         * fis - прописан путь где лежат данные для подключение к базе данных
         * */
        try {
            fis = new FileInputStream("src/main/resources/config.properties");
            properties.load(fis);

            dbURL = properties.getProperty("db.host");
            dbUsername = properties .getProperty("db.usermane");
            dbPassword = properties .getProperty("db.password");

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        Connection connection = null;
        /**
         * Делаем проверку на случий если что-то пойдёт не так с подключение к бд
         * */
        try {
            connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
