package db.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import db.model.Product;

import org.springframework.stereotype.Repository;

@Repository
public class ProductsDAO {
	@PersistenceContext
	private EntityManager entityManager;
	
	public Product getProductById( int id ) {
		return entityManager.find( Product.class, id );
	}
	
	public List<Product> getAll( ) {
		Query q = entityManager.createNamedQuery( "Products.findAll" );
		
		return q.getResultList();
	}
	
	public void create( Product p ) {
		entityManager.persist( p );
	}
	
	public Product update( Product p ) {
		return entityManager.merge( p );
	}
	
	public void deleteById( int id ) {
		Product p = getProductById( id );
		
		if( p != null ) {
			entityManager.remove( p );
		}
	}
}
