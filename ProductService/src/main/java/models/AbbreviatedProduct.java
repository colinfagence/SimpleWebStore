package models;
/**
 * Used for sliming down the detail of a product, we only want to give id, name and price for all list type viewing.
 */

import db.model.Product;

public class AbbreviatedProduct {
	int    id;
	String name;
	float  price;
	
	public AbbreviatedProduct( Product p ) {
		this.id    = p.getId();
		this.name  = p.getName();
		this.price = p.getPrice();
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public float getPrice() {
		return price;
	}
}
