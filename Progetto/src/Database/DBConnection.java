package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

public class DBConnection {

    private static DBConnection instance;
    private Connection connection = null;
//    private final String USERNAME = "postgres";
//    private final String PASSWORD = "adminadmin";
//    private final String IP = "jdbc:postgresql://db.c3kk24vgqllo.eu-north-1.rds.amazonaws.com/progetto";
//    private final String PORT = "5432";
//    private String url = "jdbc:postgresql://"+IP+":"+PORT+"/postgres";
	private String url = "jdbc:postgresql://db.c3kk24vgqllo.eu-north-1.rds.amazonaws.com/progetto";
	private String USERNAME = "postgres";
	private String PASSWORD = "adminadmin";

    private DBConnection() throws SQLException {
    	
        //Properties props = new Properties();
        //props.setProperty("user", USERNAME);
        //props.setProperty("pwd", PASSWORD);
    	
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
