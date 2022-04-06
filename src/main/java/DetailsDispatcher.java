import java.io.IOException;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DetailsDispatcher
 */
@WebServlet("/DetailsDispatcher")
public class DetailsDispatcher extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailsDispatcher() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //TODO check if you've done the initialization
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		
		String url = "jdbc:mysql://localhost/pa2"; 
    	String user = "root"; 
    	String pwd = "root";
		
		String rest_id = request.getParameter("rest_id");
		System.out.println(rest_id);
		String display = "";
		
		String sql = "SELECT image_url, restaurant_name, address, phone_no, estimated_price, rating, group_concat(category_name separator ', ') as categories "
				+ "FROM restaurant "
				+ "INNER JOIN restaurant_details ON restaurant_details.details_id=restaurant.id "
				+ "INNER JOIN rating_details ON rating_details.rating_id=restaurant.id "
				+ "INNER JOIN category ON category.restaurant_id=restaurant.restaurant_id "
				+ "WHERE restaurant.restaurant_id = ? "
				+ "GROUP BY restaurant.restaurant_id";
		
		try (Connection conn = DriverManager.getConnection(url, user, pwd);
				PreparedStatement ps = conn.prepareStatement(sql);) {
			
			ps.setString(1, rest_id);
			
    		ResultSet rs= ps.executeQuery();
    		
    		while(rs.next()) {	
    			double wholeStars = Math.floor(rs.getFloat("rating"));
    			boolean halfStar = (wholeStars != rs.getFloat("rating"));
    	
    			display += "<div class=\"row\">"
    					+"<h1>" + rs.getString("restaurant_name") + "</h1> "
    							+ "<img class=\"rest_img\" src=\"" + rs.getString("image_url") + "\" alt=\"Restaurant image\">"
    							+ "<div class=\"column\">"
    								+ "<br><br>Address: " + rs.getString("address")
    								+ "<br><br>" + rs.getString("phone_no")
    								+ "<br><br> Categories: " + rs.getString("categories")
    								+ "<br><br>Price: " + rs.getString("estimated_price")
    								+ "<br><br>Rating: ";
    			for(double idx = 0; idx < wholeStars; idx++) {
    				display += "<i class=\"fa fa-star\" aria-hidden=\"true\"></i>";
    			}
    			if(halfStar) display += "<i class=\"fa fa-star-half\" aria-hidden=\"true\"></i>";
    			display += "</div></div>";

    		}
    		
    		
		}
		catch (SQLException ex) {
    		System.out.println("SQLException " + ex.getMessage());
    		ex.printStackTrace();
    	}
		request.setAttribute("display", display);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/details.jsp"); 
		dispatcher.forward(request, response); 
		
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
