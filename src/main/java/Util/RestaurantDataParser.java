package Util;


import com.google.gson.*;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * A class that pretends to be the Yelp API
 */
public class RestaurantDataParser{
    private static Boolean ready = false;
    

    /**
     * Initializes the DB with json data
     *
     * @param responseString the Yelp json string
     */
    public static void Init(String responseString) {
    	
        if (ready) {
            return;
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //TODO check if you've done the initialization
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ready = true;
        String url = "jdbc:mysql://localhost/pa2"; 
    	String user = "root"; 
    	String pwd = "root";  //your secret database pwd
        Gson gson = new Gson();
        BusinessWrapper wrap;
        Business[] businesses;
        
        try {
        	wrap = gson.fromJson(responseString, BusinessWrapper.class);
        	businesses = wrap.getBusinesses();
        	for (Business bus: businesses) {
        		String id = bus.getId();
        		String name = bus.getName();
        		System.out.println(name);
        		
        		String image_url = bus.getImage_url();
        		String yelp_url = bus.getUrl();
        		String price = bus.getPrice();
        		String phone_no = bus.getPhone();
        		Location locate = bus.getLocation();
        		String loc = String.join(" ", locate.getDisplay_address());
        		
        		int review_count = bus.getReview_count();
        		float rating = bus.getRating();
        		
        		Categories[] category = bus.getCategories();
        		for(Categories cat: category) {
        			String add = "INSERT INTO category(category_name, restaurant_id) VALUES(?, ?)";
        			try (Connection conn = DriverManager.getConnection(url, user, pwd);
        	    			PreparedStatement ps = conn.prepareStatement(add);) {
        	    		ps.setString(1, cat.getTitle());
        	    		ps.setString(2, id);
        	    		int row = ps.executeUpdate();

        	    	}
        			catch (SQLException ex) {
        	    		System.out.println("SQLException" + ex.getMessage());
        	    	}
        		}
        		
        		
        		String sql = "INSERT INTO restaurant(restaurant_id, restaurant_name) VALUES (?, ?)";
        		try (Connection conn = DriverManager.getConnection(url, user, pwd);
            			PreparedStatement ps = conn.prepareStatement(sql);){
        			ps.setString(1, id);
            		ps.setString(2, name);
            		int row = ps.executeUpdate();
        		}
        		catch (SQLException ex) {
            		System.out.println("SQLException" + ex.getMessage());
            	}
        		
        		
        		sql = "INSERT INTO restaurant_details(image_url, address, phone_no, estimated_price, yelp_url) VALUES (?, ?, ?, ?, ?)";
        		try (Connection conn = DriverManager.getConnection(url, user, pwd);
            			PreparedStatement ps = conn.prepareStatement(sql);){
        			ps.setString(1, image_url);
            		ps.setString(2, loc);
            		ps.setString(3, phone_no);
            		ps.setString(4, price);
            		ps.setString(5, yelp_url);
            		int row = ps.executeUpdate();
        		}
        		catch (SQLException ex) {
            		System.out.println("SQLException" + ex.getMessage());
            	}
        		
        		sql = "INSERT INTO rating_details(review_count, rating) VALUES (?, ?)";
        		try (Connection conn = DriverManager.getConnection(url, user, pwd);
            			PreparedStatement ps = conn.prepareStatement(sql);){
        			ps.setInt(1, review_count);
            		ps.setFloat(2, rating);
            		int row = ps.executeUpdate();
        		}
        		catch (SQLException ex) {
            		System.out.println("SQLException" + ex.getMessage());
            	}
        	}
        }
		catch(JsonParseException e) {
			System.out.println("The file could not be parsed.\n");
			e.printStackTrace();
		}

        //TODO get businessHelper array from json
        //TODO iterate the businessHelper array and insert every business into the DB
    }

    
    public static Business getBusiness(String id) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        String url = "jdbc:mysql://localhost/pa2"; 
    	String user = "root"; 
    	String pwd = "root";  //your secret database pwd
    	
    	
        //TODO return business based on id
        return null;
    }

    /**
     * @param keyWord    the search keyword
     * @param sort       the sort option (price, review count, rating)
     * @param searchType search in category or name
     * @return the list of business matching the criteria
     */
    public static ArrayList<Business> getBusinesses(String keyWord, String sort, String searchType) {
        ArrayList<Business> businesses = new ArrayList<Business>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //TODO get list of business based on the param
        return businesses;

    }
}

