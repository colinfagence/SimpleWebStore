package models;

import java.util.ArrayList;
import java.util.List;

import db.model.Product;
import models.AbbreviatedProduct;

public class Categories {
	private ArrayList<String> categories;

	public ArrayList<String> getCategories() {
		if( categories == null ) {
			categories = new ArrayList<String>();
		}
		
		return categories;
	}
	
	public void addCategory( String c ) {
		this.getCategories().add( c );
	}
}
