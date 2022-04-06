package Util;

import java.util.*;

/**
 * The class used to model a business
 */
public class Business {
  	private String id;
    private String alias;
    private String name;
    private String image_url;
    private boolean is_closed;
    private String url;
    private int review_count;
    private Categories[] categories;
    private float rating;
    private Coordinates coordinates;
    private String[] transactions;
    private String price;
    private Location location;
    private String phone;
    private String display_phone;
    private float distance;
	public Business(String id, String alias, String name, String image_url, boolean is_closed, String url,
			int review_count, Categories[] categories, float rating, Coordinates coordinates, String[] transactions,
			String price, Location location, String phone, String display_phone, float distance) {
		super();
		this.id = id;
		this.alias = alias;
		this.name = name;
		this.image_url = image_url;
		this.is_closed = is_closed;
		this.url = url;
		this.review_count = review_count;
		this.categories = categories;
		this.rating = rating;
		this.coordinates = coordinates;
		this.transactions = transactions;
		this.price = price;
		this.location = location;
		this.phone = phone;
		this.display_phone = display_phone;
		this.distance = distance;
	}
	public String getId() {
		return id;
	}
	public String getAlias() {
		return alias;
	}
	public String getName() {
		return name;
	}
	public String getImage_url() {
		return image_url;
	}
	public boolean isIs_closed() {
		return is_closed;
	}
	public String getUrl() {
		return url;
	}
	public int getReview_count() {
		return review_count;
	}
	public Categories[] getCategories() {
		return categories;
	}
	public float getRating() {
		return rating;
	}
	public Coordinates getCoordinates() {
		return coordinates;
	}
	public String[] getTransactions() {
		return transactions;
	}
	public String getPrice() {
		return price;
	}
	public Location getLocation() {
		return location;
	}
	public String getPhone() {
		return phone;
	}
	public String getDisplay_phone() {
		return display_phone;
	}
	public float getDistance() {
		return distance;
	}



    //TODO Add Getters (No Setters as the business does not change in our assignment once constructed)
}

