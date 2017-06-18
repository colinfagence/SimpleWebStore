package db.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import db.dao.CategoriesDAO;
import db.model.Category;

@Service
@Transactional
public class CategoriesService {
	@Autowired
	private CategoriesDAO cDAO;
	
	public Category getByName( String categoryName ) {
		return cDAO.getByName( categoryName );
	}
	
	public List<Category> getAll( ) {
		return cDAO.getAll();
	}
}
