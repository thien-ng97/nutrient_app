package util;

import java.sql.*;


// With use of AI

public class DatabaseManager {
	private static DatabaseManager instance;
    private Connection connection;

    private DatabaseManager() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/nutrition_app", 
                "root", "helloworld"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        
        /*
         * Newly added
         * To allow using several DAOs or several methods in the same DAO  together 
         * Reason: Before change we were using singleton pattern to get database connection to allow 
         * concurrent using, once the first DAO finished executing the PreparedStatement,
         *  it closed our only database connection, when the next want to use, it cannot
         */        
        try {
	        if (instance.connection.isClosed()) {
	        	instance = null;
	        	return getInstance();
	        }	        
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}


