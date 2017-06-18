package db.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import db.model.Category;

import org.springframework.stereotype.Repository;

@Repository
public class CategoriesDAO {
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Category> getAll( ) {
		Query q = entityManager.createNamedQuery( "Categories.findAll" );
		
		return q.getResultList();
	}
	
	public Category getByName( String categoryName ) {
		Query q = entityManager.createNamedQuery( "Categories.findByName" ).setParameter( "name", categoryName );
		
		return (Category)q.getSingleResult();
	}
}
