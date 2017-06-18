package controllers;

import org.springframework.web.bind.annotation.RestController;

import db.model.Category;
import db.model.Product;
import db.services.CategoriesService;
import db.services.ProductsService;
import models.Products;

import java.util.List;

import javax.persistence.NoResultException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
public class ProductController {
	static Logger log = LogManager.getLogger( ProductController.class );
	
	@Autowired
	ProductsService pService;
	
	@Autowired
	CategoriesService cService;

	/**
	 * End point for getting the details ( expanding ) on a particular product
	 * 
	 * id is given as part of the path
	 * 
	 * At the moment it just expands on description, but its going to be extended later.
	 * 
	 * @param id
	 * @return
	 */
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method=RequestMethod.GET, value="/products/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getSingleProduct( @PathVariable("id") int id ) {
		try {
			Product p = pService.getById( id );
			
			if( p != null ) {
				return new ResponseEntity<Product>( p, HttpStatus.OK );
			}
			else {
				return new ResponseEntity<>( HttpStatus.NOT_FOUND );
			}
		}
		catch ( Exception e ) {
			log.error( e.toString() );
			return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR );
		}
	};
	
	/**
	 * End point for getting the list of products of a particular category
	 * 
	 * @param category
	 * @return
	 */
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method=RequestMethod.GET, value="/products/category/{category}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Products> getProductsFromCategory( @PathVariable("category") String category ) {
		try {
			Category c = cService.getByName( category );
			
			if( c != null ) {
				Products ps = new Products();
				
				ps.addDBProductList( c.getProducts() );
				
				return new ResponseEntity<Products>( ps, HttpStatus.OK );
			}
			else {
				return new ResponseEntity<>( HttpStatus.NOT_FOUND );
			}
		}
		catch ( Exception e ) {
			log.error( e.toString() );
			return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR );
		}
	};	
	
	/**
	 * List all products we currently have in the DB, at this time it will only be a few.
	 * 
	 * Might get disabled if there are too many records to display in favor of the above
	 * 
	 * @return
	 */
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method=RequestMethod.GET, value="/products", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Products> getProducts( ) {
		try {
			List<Product> pList = pService.getAll( );
			
			if( pList != null ) {
				Products ps = new Products();
				
				ps.addDBProductList( pList );
				
				return new ResponseEntity<Products>( ps, HttpStatus.OK );
			}
			else {
				return new ResponseEntity<>( HttpStatus.NOT_FOUND );
			}
		}
		catch ( Exception e ) {
			log.error( e.toString() );
			return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR );
		}
	};
}
