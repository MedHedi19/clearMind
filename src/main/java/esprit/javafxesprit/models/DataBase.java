package esprit.javafxesprit.models;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBase {
    public static Connection connectDB() {

        try {

            Class.forName("com.mysql.jdbc.Driver");

            Connection connect
                    = DriverManager.getConnection("jdbc:mysql://localhost/espritjavafx", "root", ""); // root IS OUR DEFAULT USERNAME AND EMPTY OR NULL OR BLANK TO OUR PASSWORD
            return connect;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
