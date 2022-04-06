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
import java.sql.*;

/**
 * Servlet implementation class LoginDispatcher
 */
@WebServlet("/LoginDispatcher")
public class LoginDispatcher extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

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
    	email = request.getParameter("user_email");
    	password = request.getParameter("user_password");
    	String errorMessage = "<p style=\"background-color:#FFCCCB;text-align:center;padding: 15px;\">"
    			+ "Invalid email or password. Or, bad Google login. Please try again.</p>";
    	
    	PrintWriter out = response.getWriter();
    	
    	if(password == null || password.length() == 0 || password.equals("")) {
    		out.println(errorMessage);
    		request.getRequestDispatcher("/auth.html").include(request, response);
    		return;
    	}
    	
    	if(email == null || email.length() == 0 || email.equals("") || email.indexOf("@") == -1) {
    		out.println(errorMessage);
    		request.getRequestDispatcher("/auth.html").include(request, response);
    		return;
    	}
    	
    	if(!Helper.isValidEmail(email)) {
    		out.println(errorMessage);
    		request.getRequestDispatcher("/auth.html").include(request, response);
    		return;
    	}
    	
    	if(!Helper.emailAlreadyRegistered(email, request, response)) {
    		out.println(errorMessage);
    		request.getRequestDispatcher("/auth.html").include(request, response);
    		return;
    	}
    	try {
    		if(!Helper.checkPassword(email, password)) {
        		out.println(errorMessage);
        		request.getRequestDispatcher("/auth.html").include(request, response);
        		return;
        	}
    	}
    	catch(SQLException e) {
    		out.println(errorMessage);
    	}
    	
    	
    	String name = "";
    	try {
    		name = Helper.getUserName(email);
    	}
    	catch (SQLException e){
    		e.printStackTrace();
    	}
    	
    	Cookie ck = new Cookie("ck_name", name);
    	
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
        doGet(request, response);
    }
}
