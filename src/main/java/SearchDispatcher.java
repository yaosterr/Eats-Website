import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Util.Business;
import Util.RestaurantDataParser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Serial;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Servlet implementation class SearchDispatcher
 */

@WebServlet("/SearchDispatcher")
public class SearchDispatcher extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public SearchDispatcher() {
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        // TODO get json file as stream, Initialize FakeYelpAPI by calling its initalize
        // method
    	super.init(config);
        ServletContext servletContext = getServletContext();
        
        InputStream stream = servletContext.getResourceAsStream("/restaurant_data.json");
    	Scanner sc = new Scanner(stream);
        //Reading line by line from scanner to StringBuffer
        StringBuffer sb = new StringBuffer();
        while(sc.hasNext()){
           sb.append(sc.nextLine());
        }
        sc.close();
    	RestaurantDataParser.Init(sb.toString());
       
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //TODO check if you've done the initialization
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    	
    	String catOrName = request.getParameter("question");
    	String search = request.getParameter("searchCriteria").toLowerCase();
    	String sortBy = request.getParameter("sortBy");
    	
    	String url = "jdbc:mysql://localhost/pa2"; 
    	String user = "root"; 
    	String pwd = "root";  //your secret database pwd
    	
    	String display = "";
    	
    	if(catOrName.equals("Category")) {
    		String sql = "";
    		if(sortBy.equals("price")) {
    			sql = "SELECT restaurant.restaurant_id, restaurant_name, image_url, estimated_price, review_count, rating, yelp_url "
            			+ "FROM restaurant "
            			+ "INNER JOIN category ON category.restaurant_id=restaurant.restaurant_id "
            			+ "INNER JOIN restaurant_details ON restaurant_details.details_id=restaurant.id "
            			+ "INNER JOIN rating_details ON rating_details.rating_id=restaurant.id "
            			+ "WHERE category.category_name LIKE ? "
            			+ "ORDER BY CHAR_LENGTH(restaurant_details.estimated_price) ";
    		}
    		if(sortBy.equals("rating")) {
    			sql = "SELECT restaurant.restaurant_id, restaurant_name, image_url, estimated_price, review_count, rating, yelp_url "
            			+ "FROM restaurant "
            			+ "INNER JOIN category ON category.restaurant_id=restaurant.restaurant_id "
            			+ "INNER JOIN restaurant_details ON restaurant_details.details_id=restaurant.id "
            			+ "INNER JOIN rating_details ON rating_details.rating_id=restaurant.id "
            			+ "WHERE category.category_name LIKE ? "
            			+ "ORDER BY rating_details.rating DESC ";
    		}
    		if(sortBy.equals("review-count")) {
    			sql = "SELECT restaurant.restaurant_id, restaurant_name, image_url, estimated_price, review_count, rating, yelp_url "
            			+ "FROM restaurant "
            			+ "INNER JOIN category ON category.restaurant_id=restaurant.restaurant_id "
            			+ "INNER JOIN restaurant_details ON restaurant_details.details_id=restaurant.id "
            			+ "INNER JOIN rating_details ON rating_details.rating_id=restaurant.id "
            			+ "WHERE category.category_name LIKE ? "
            			+ "ORDER BY rating_details.review_count DESC ";
    		}
    		
    		try (Connection conn = DriverManager.getConnection(url, user, pwd);
        			PreparedStatement ps = conn.prepareStatement(sql);) {
    			ps.setString(1, "%" + search + "%");
        		ResultSet rs= ps.executeQuery();
        		while(rs.next()) {	
        			double wholeStars = Math.floor(rs.getFloat("rating"));
        			boolean halfStar = (wholeStars != rs.getFloat("rating"));
        			display += "<div class=\"row\">"
        							+ "<img class=\"rest_img\" src=\"" + rs.getString("image_url") + "\" alt=\"Restaurant image\">"
        							+ "<div class=\"column\">"
        								+ "<form action=\"DetailsDispatcher\" method=\"GET\"> "
        									+ "<button name = \"rest_id\" value=\"" + rs.getString("restaurant_id") + "\" class=\"detail_button\" type=\"submit\">" + rs.getString("restaurant_name") + "</button> "
        								+ "</form> "
        								+ "<br><br>Price: " + rs.getString("estimated_price")
        								+ "<br><br>Review Count: " + rs.getInt("review_count")
        								+ "<br><br>Rating: ";
        			for(double idx = 0; idx < wholeStars; idx++) {
        				display += "<i class=\"fa fa-star\" aria-hidden=\"true\"></i>";
        			}
        			if(halfStar) display += "<i class=\"fa fa-star-half\" aria-hidden=\"true\"></i>";
        								
        			display += "<br><br><br><a href=\"" + rs.getString("yelp_url") + "\" target=_blank>Yelp Link<a>"
        						+ "</div>"
        					+ "</div>";
        		}
        		
        		
    		}
    		catch (SQLException ex) {
        		System.out.println("SQLException " + ex.getMessage() + sql);
        	}
    	}
    	
    	if(catOrName.equals("Name")) {
    		String sql = "";
    		if(sortBy.equals("price")) {
    			sql = "SELECT restaurant.restaurant_id, restaurant.restaurant_name, restaurant_details.image_url, restaurant_details.estimated_price, rating_details.review_count, rating_details.rating, restaurant_details.yelp_url "
            			+ "FROM restaurant "
            			+ "INNER JOIN restaurant_details ON restaurant_details.details_id=restaurant.id "
            			+ "INNER JOIN rating_details ON rating_details.rating_id=restaurant.id "
            			+ "WHERE restaurant.restaurant_name LIKE ? "
            			+ "ORDER BY CHAR_LENGTH(restaurant_details.estimated_price) ";
    		}
    		if(sortBy.equals("rating")) {
    			sql = "SELECT restaurant.restaurant_id, restaurant.restaurant_name, restaurant_details.image_url, restaurant_details.estimated_price, rating_details.review_count, rating_details.rating, restaurant_details.yelp_url "
            			+ "FROM restaurant "
            			+ "INNER JOIN restaurant_details ON restaurant_details.details_id=restaurant.id "
            			+ "INNER JOIN rating_details ON rating_details.rating_id=restaurant.id "
            			+ "WHERE restaurant.restaurant_name LIKE ? "
            			+ "ORDER BY rating_details.rating DESC ";
    		}
    		if(sortBy.equals("review-count")) {
    			sql = "SELECT restaurant.restaurant_id, restaurant.restaurant_name, restaurant_details.image_url, restaurant_details.estimated_price, rating_details.review_count, rating_details.rating, restaurant_details.yelp_url "
            			+ "FROM restaurant "
            			+ "INNER JOIN restaurant_details ON restaurant_details.details_id=restaurant.id "
            			+ "INNER JOIN rating_details ON rating_details.rating_id=restaurant.id "
            			+ "WHERE restaurant.restaurant_name LIKE ? "
            			+ "ORDER BY rating_details.review_count DESC ";
    		}
    		try (Connection conn = DriverManager.getConnection(url, user, pwd);
        			PreparedStatement ps = conn.prepareStatement(sql);) {
    			ps.setString(1, "%" + search + "%");
        		ResultSet rs= ps.executeQuery();
        		while(rs.next()) {
        			double wholeStars = Math.floor(rs.getFloat("rating"));
        			boolean halfStar = (wholeStars != rs.getFloat("rating"));
        			display += "<div class=\"row\">"
							+ "<img class=\"rest_img\" src=\"" + rs.getString("image_url") + "\" alt=\"Restaurant image\">"
							+ "<div class=\"column\">"
								+ "<form action=\"DetailsDispatcher\" method=\"GET\"> "
									+ "<button name = \"rest_id\" value=\"" + rs.getString("restaurant_id") + "\" class=\"detail_button\" type=\"submit\">" + rs.getString("restaurant_name") + "</button> "
								+ "</form> "
								+ "<br><br>Price: " + rs.getString("estimated_price")
								+ "<br><br>Review Count: " + rs.getInt("review_count")
								+ "<br><br>Rating: ";
			for(double idx = 0; idx < wholeStars; idx++) {
				display += "<i class=\"fa fa-star\" aria-hidden=\"true\"></i>";
			}
			if(halfStar) display += "<i class=\"fa fa-star-half\" aria-hidden=\"true\"></i>";
								
			display += "<br><br><br><a href=\"" + rs.getString("yelp_url") + "\" target=_blank>Yelp Link<a>"
						+ "</div>"
					+ "</div>";
		}
    		}
    		catch (SQLException ex) {
        		System.out.println("SQLException" + ex.getMessage());
        	}
    	}
    	request.setAttribute("display", display);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/search.jsp"); 
		dispatcher.forward(request, response); 
    	
    	
    			
        // TODO
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}