package models;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import db.model.Category;
import db.model.Product;

public class ProductsTest {
	Category c;

	Products productsTest = new Products();
	
	private AbbreviatedProduct AbbreviatedProductHelper( int id, String name, String description, float price, Category c ) {
		Product p = ProductHelper( id, name, description, price, c );
		
		AbbreviatedProduct AP = new AbbreviatedProduct( p );
		
		return AP;
	}
	
	private Product ProductHelper( int id, String name, String description, float price, Category c ) {
		Product p = new Product();

		p.setId         ( id   );
		p.setName       ( name );
		p.setDescription( description );
		p.setPrice      ( price );
		p.setCategory   ( c );
		
		return p;
	}
	
    @Before
    public void setup() throws Exception {
    	// Setup goes here.
    	c = new Category();
    	
    	c.setId      ( 1 );
    	c.setName    ("None");
    	c.setProducts( null );
    }
	
	@Test
	public void testGetProducts() {
		ArrayList<AbbreviatedProduct> apInitialList = new ArrayList<AbbreviatedProduct>();
		
		apInitialList.add( AbbreviatedProductHelper( 1,  "none",  "none", (float)1.56 , c ) );
		apInitialList.add( AbbreviatedProductHelper( 2,   "one",   "one", (float)5.34 , c ) );
		apInitialList.add( AbbreviatedProductHelper( 3, "three", "three", (float)50.23, c ) );
		
		productsTest.setProducts( apInitialList );
		
		ArrayList<AbbreviatedProduct> apList = productsTest.getProducts();
		
		assertEquals( apList, apInitialList );
	}

	@Test
	public void testAddDBProductList() {
		ArrayList<Product> proListInitial = new ArrayList<Product>();
		
		ArrayList<AbbreviatedProduct> apInitialList = new ArrayList<AbbreviatedProduct>();
		
		apInitialList.add( AbbreviatedProductHelper( 1,  "none",  "none", (float)1.56 , c ) );
		apInitialList.add( AbbreviatedProductHelper( 2,   "one",   "one", (float)5.34 , c ) );
		apInitialList.add( AbbreviatedProductHelper( 3, "three", "three", (float)50.23, c ) );
		
		proListInitial.add( ProductHelper          ( 1,  "none",  "none", (float)1.56 , c ) );
		proListInitial.add( ProductHelper          ( 2,   "one",   "one", (float)5.34 , c ) );
		proListInitial.add( ProductHelper          ( 3, "three", "three", (float)50.23, c ) );
		
		productsTest.addDBProductList( proListInitial );
		
		ArrayList<AbbreviatedProduct> apResultList = productsTest.getProducts();
		
		for( int i = 0; i < apResultList.size(); i++ ) {
			assertEquals( apResultList.get( i ).getId()   , proListInitial.get( i ).getId() );
			assertEquals( apResultList.get( i ).getName() , proListInitial.get( i ).getName() );
			assertEquals( apResultList.get( i ).getPrice(), proListInitial.get( i ).getPrice(), 0 );
		}
	}
}
