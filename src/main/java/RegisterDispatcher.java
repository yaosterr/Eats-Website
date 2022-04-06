import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Util.Helper;
import Util.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class RegisterDispatcher
 */

@WebServlet("/RegisterDispatcher")
public class RegisterDispatcher extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public RegisterDispatcher() {
    }


    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    	}
    	catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	}
    	String email = null;
    	String password = null;
    	String name = null;
    	String confirm_password = null;
    	
    	email = request.getParameter("user_email");
    	password = request.getParameter("user_password");
    	name = request.getParameter("user_name");
    	confirm_password = request.getParameter("confirm_password");
    	
    	String errorMessage = "<p style=\"background-color:#FFCCCB;text-align:center;padding: 15px;\">";
    	String ogerrorMessage = "<p style=\"background-color:#FFCCCB;text-align:center;padding: 15px;\">";
    	
    	PrintWriter out = response.getWriter();
    	
    	if(Helper.emailAlreadyRegistered(email, request, response)) {
    		errorMessage += "User with this email is already registered. Please log in.</p>";
    		out.println(errorMessage);
    		request.getRequestDispatcher("/auth.html").include(request, response);
    		return;
    	}
    	
    	if(name == null || name.length() == 0 || name.equals(" ") || !Helper.validName(name)) {
    		errorMessage += "Name is missing or invalid. ";
    	}
    	
    	if(email == null || email.length() == 0 || email.equals(" ") || email.indexOf("@") == -1 || !Helper.isValidEmail(email)) {
    		errorMessage += "Email is missing or invalid. ";
    	}
    	
    	if(password == null || password.length() == 0 || password.equals(" ")) {
    		errorMessage += "Password is missing or invalid. ";
    	}
    	
    	else if (confirm_password == null || confirm_password.length() == 0 || confirm_password.equals(" ")|| !password.equals(confirm_password)) {
    		errorMessage += "Password confirmation does not match password. ";
    	}
    	
    	if(!errorMessage.equals(ogerrorMessage)) {
    		errorMessage += "</p>";
    		out.println(errorMessage);
    		request.getRequestDispatcher("/auth.html").include(request, response);
    		return;
    	}
    	
    	String url = "jdbc:mysql://localhost/pa2"; 
    	String user = "root"; 
    	String pwd = "root"; 
    	
    	String sql = "INSERT INTO users(useremail, username, userpassword) VALUES (?, ?, ?)";
    	try (Connection conn = DriverManager.getConnection(url, user, pwd);
    			PreparedStatement ps = conn.prepareStatement(sql);) {
    		ps.setString(1, email);
    		ps.setString(2, name);
    		ps.setString(3, password);
    		int row = ps.executeUpdate();

    	}
    	catch (SQLException ex) {
    		System.out.println("SQLException" + ex.getMessage());
    	}
    	
    	Cookie ck = new Cookie("ck_name", request.getParameter("user_name"));
    	
    	ck.setMaxAge(60*60);
    	response.addCookie(ck);
    	
    	response.setContentType("text/html");
    	
    	response.sendRedirect("index.jsp");
    	
        //TODO
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
