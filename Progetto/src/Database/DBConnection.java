package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static DBConnection instance;
    private Connection connection = null;
    private final String USERNAME = "postgres";
    private final String PASSWORD = "adminadmin";
    private final String IP = "database.ckeutmg1tgie.eu-central-1.rds.amazonaws.com";
    private final String DATABASE = "progetto";
    private final String PORT = "5432";
	private final String url = "jdbc:postgresql://" + IP +":"+PORT+"/"+DATABASE;

    private DBConnection() throws SQLException {
        try
        {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
        }
        catch (ClassNotFoundException ex)
        {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }

    }

    public Connection getConnection() {
        return connection;
    }

    public static DBConnection getInstance() throws SQLException {
        if (instance == null)
        {
            instance = new DBConnection();
        }
        else
            if (instance.getConnection().isClosed())
            {
                instance = new DBConnection();
            }

        return instance;
    }
}
