package Util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

public class Helper {
    /**
     * check if name is valid
     *
     * @param name the name user provides
     * @return valid or not valid
     */
    public static boolean validName(String name) {
        return Constant.namePattern.matcher(name).matches();
    }

    /**
     * check if email is valid
     *
     * @param email the email user provides
     * @return valid or not valid
     */
    public static boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        return Constant.emailPattern.matcher(email).matches();
    }

    /**
     * Get username with the email
     *
     * @param email
     * @return userName
     * @throws SQLException
     */
    public static String getUserName(String email) throws SQLException {
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    	}
    	catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	}
    	
    	String url = "jdbc:mysql://localhost/pa2"; 
    	String user = "root"; 
    	String pwd = "root";  //your secret database pwd
    	Connection conn = DriverManager.getConnection(url, user, pwd);
    	
    	String sql = "SELECT username FROM users WHERE useremail=?";
    	
    	PreparedStatement ps = conn.prepareStatement(sql);
    	ps.setString(1, email);
    	
    	ResultSet rs= ps.executeQuery();
    	
    	String output = "";
    	if(rs.next()) {
    		output = rs.getString(1);
    	}
    	
        //TODO
        return output;
    }

    /**
     * Get userID with email
     *
     * @param email
     * @return userID
     * @throws SQLException
     */
    public static int getUserID(String email) throws SQLException {
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    	}
    	catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	}
    	
    	String url = "jdbc:mysql://localhost/pa2"; 
    	String user = "root"; 
    	String pwd = "root";  //your secret database pwd
    	Connection conn = DriverManager.getConnection(url, user, pwd);
    	
    	String sql = "SELECT userid FROM users WHERE useremail=?";
    	
    	PreparedStatement ps = conn.prepareStatement(sql);
    	ps.setString(1, email);
    	
    	ResultSet rs= ps.executeQuery();
    	
    	int output = 0;
    	if(rs.next()) {
    		output = rs.getInt(1);
    	}
    	
        //TODO
        return output;
    }

    /**
     * check if the email and password matches
     *
     * @param email
     * @param password
     */
    public static boolean checkPassword(String email, String password) throws SQLException {
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    	}
    	catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	}
    	
    	String url = "jdbc:mysql://localhost/pa2"; 
    	String user = "root"; 
    	String pwd = "root";  //your secret database pwd
    	Connection conn = DriverManager.getConnection(url, user, pwd);
    	
    	String sql = "SELECT userpassword FROM users WHERE useremail=?";
    	
    	PreparedStatement ps = conn.prepareStatement(sql);
    	ps.setString(1, email);
    	
    	ResultSet rs= ps.executeQuery();
    	
        //TODO
        return rs.getString("userpassword").equals(password);

    }

    /**
     * Check if email is already registered
     *
     * @param email
     * @param request
     * @param response
     * @return email registered or not
     * @throws ServletException
     * @throws IOException
     */
    public static boolean emailAlreadyRegistered(String email, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    	}
    	catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	}
    	
    	String url = "jdbc:mysql://localhost/pa2"; 
    	String user = "root"; 
    	String pwd = "root";  //your secret database pwd
    	
    	String sql = "SELECT * FROM users WHERE useremail = ?";
    	try (Connection conn = DriverManager.getConnection(url, user, pwd);
    			PreparedStatement ps = conn.prepareStatement(sql);) {
    		ps.setString(1, email);
    		ResultSet rs= ps.executeQuery();
    		if(rs.next()) {
    			return true;
    		}
    		else {
    			return false;
    		}
    	}
    	catch (SQLException ex) {
    		System.out.println("SQLException" + ex.getMessage());
    	}
        //TODO
        return true;
    }
}
