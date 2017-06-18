package db.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import db.dao.ProductsDAO;
import db.model.Product;

@Service
@Transactional
public class ProductsService {
	@Autowired
	private ProductsDAO pDAO;
	
	public void create( Product p ) {
		pDAO.create( p );
	}
	
	public Product getById( int id ) {
		return pDAO.getProductById( id );
	}
	
	public List<Product> getAll( ) {
		return pDAO.getAll();
	}
}
