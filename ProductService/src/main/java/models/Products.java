package models;

import java.util.ArrayList;
import java.util.List;

import db.model.Product;
import models.AbbreviatedProduct;

public class Products {
	private ArrayList<AbbreviatedProduct> products;
	
	public ArrayList<AbbreviatedProduct> getProducts( ) {
		return this.products;
	}
	
	public void addProduct( AbbreviatedProduct p ) {
		if( products == null ) {
			products = new ArrayList<AbbreviatedProduct>();
		}
		
		products.add( p );
	}
	
	public void addDBProductList( List<Product> pList ) {
		for( Product p : pList ) {
			this.addProduct( new AbbreviatedProduct( p ) );
		}
	}
	
	public void setProducts(ArrayList<AbbreviatedProduct> products) {
		this.products = products;
	}
}
