package controllers;

import org.springframework.web.bind.annotation.RestController;

import db.model.Category;
import db.services.CategoriesService;
import models.Categories;
import models.Products;

import java.util.List;

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
public class CategoriesController {
	static Logger log = LogManager.getLogger( CategoriesController.class );
	
	@Autowired
	CategoriesService cService;
	
	/**
	 * End point for getting the various categories that are used to populate a categories menu. 
	 * 
	 * Returns a string array of categories ( any other information is removed )
	 */
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method=RequestMethod.GET, value="/categories", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Categories> getCategories( ) {
		try {
			List<Category> cList = cService.getAll( );
			
			if( cList != null ) {
				Categories cs = new Categories();
				
				for( Category singleCategory : cList ) {
					cs.addCategory( singleCategory.getName() );
				}
				
				return new ResponseEntity<Categories>( cs, HttpStatus.OK );
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
