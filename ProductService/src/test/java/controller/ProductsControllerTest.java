package controller;

import org.junit.Test;
import org.junit.Before;
import org.junit.Ignore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import application.main;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import db.dao.ProductsDAO;
import db.model.Category;
import db.model.Product;
import db.services.CategoriesService;
import db.services.ProductsService;

@RunWith(SpringRunner.class)
@SpringBootTest( classes=main.class )
@AutoConfigureMockMvc
public class ProductsControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	ProductsService pServiceMock;
	
    @Before
    public void setup() throws Exception {
    	// Setup goes here.
    }
    
    /**
     * Wrapper function for creating new products for return in the mock.
     * 
     * @param id
     * @param name
     * @param description
     * @return
     */
    private Product createProduct( int id, String name, String description ) {
    	Product p = new Product();
    	
    	p.setId         (id);
    	p.setName       (name);
    	p.setDescription(description);
    	p.setCategory   ( null );
    	
    	return p;
    }
	
    /**
     * Test that it can return an object without any issue.
     */
	@Test
	public void Test1() throws Exception {
		Product p = this.createProduct( 1 , "Apple", "Some sort of apple!" );
		
		when(pServiceMock.getById( 1 )).thenReturn( p );
		
		mockMvc.perform( get("/products/1") )
			.andExpect(status().isOk() )
		    .andExpect(jsonPath("$.id"   , is( 1 ) ) )
		    .andExpect(jsonPath("$.name", is("Apple"  ) ) )
		    .andExpect(jsonPath("$.description", is("Some sort of apple!") ) );
		
		verify( pServiceMock, times( 1 ) ).getById( 1 );
		verifyNoMoreInteractions( pServiceMock );
	}

	/**
	 * Test that when given a null it returns is not found 404
	 */
	@Test
	public void Test2() throws Exception {
		when(pServiceMock.getById( 1 )).thenReturn( null );
		
		mockMvc.perform( get("/products/1") )
			.andExpect(status().isNotFound() );
		
		verify( pServiceMock, times( 1 ) ).getById( 1 );
		verifyNoMoreInteractions( pServiceMock );	
	}	
}
