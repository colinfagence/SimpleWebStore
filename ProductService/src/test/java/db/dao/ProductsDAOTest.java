package db.dao;

import static org.mockito.Mockito.*;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import application.main;
import db.model.Product;

import static org.junit.Assert.*;
import static org.mockito.Mock.*;

@RunWith(SpringRunner.class)
@SpringBootTest( classes=main.class )
@DataJpaTest( showSql=true )
public class ProductsDAOTest {
	@Autowired
	TestEntityManager entityManager;
	
	@Autowired
	ProductsDAO pDAO;
	
	/**
	 * I have the luxury of using a temporary db thats blown away and re-created at start time, so try to find a product well outside that range.
	 * @throws Exception
	 */
	@Test
	public void Test() throws Exception {
		Product p = pDAO.getProductById( 200 );
		
		assertEquals( null, p );
	}
	
	@Test
	public void AttemptToCreateNewProduct() throws Exception {
		Product p = new Product();
		
		p.setName       ( "TestProduct1234567" );
		p.setDescription( "TestProduct1234567" );
		p.setCategory   ( null );
		p.setPrice      ( (float)1.56 );
		
		pDAO.create(p);
	}
	
	@Test
	public void AttemptToUpdateProduct() throws Exception {
		Product p = new Product();
		
		p.setName       ( "TestProduct1234567" );
		p.setDescription( "TestProduct1234567" );
		p.setPrice      ( (float)1.56 );
		
		pDAO.create( p );
		
//		System.out.println( "id: " + p.getId() + " name: " + p.getName() );

		p.setName       ( "TestProduct12367" );
		p.setDescription( "TestProduct12367" );
		p.setPrice      ( (float)1.45 );	
		
		Product p2 = pDAO.update( p );
		
		assertNotNull( p2 );
		
//		System.out.println( "id: " + p2.getId() + " name: " + p2.getName() );
	}
}
