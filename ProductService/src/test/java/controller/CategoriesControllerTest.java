package controller;

import org.junit.Test;
import org.junit.Before;

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

import db.model.Category;
import db.services.CategoriesService;

@RunWith(SpringRunner.class)
@SpringBootTest( classes=main.class )
@AutoConfigureMockMvc
public class CategoriesControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	CategoriesService cServiceMock;
	
	/**
	 * Wrapper to help create valid categorys for the mock
	 * 
	 * @param id   - int
	 * @param name - String
	 */
	private Category createCategory( int id, String name ) {
		Category c1 = new Category();
		
		c1.setId      ( id );
		c1.setName    ( name );
		c1.setProducts( null );
		
		return c1;
	}

    @Before
    public void setup() throws Exception {
    	// Setup goes here.
    }
	
    /**
     * Test populated categories section.
     */
	@Test
	public void Test1() throws Exception {
		List<Category> categoryList = new ArrayList<Category>();
		
		categoryList.add( this.createCategory(1 , "Auto"    ) );
		categoryList.add( this.createCategory(2 , "Manual"  ) );
		categoryList.add( this.createCategory(3 , "Lettuce" ) );
		categoryList.add( this.createCategory(4 , "Tomato"  ) );
		
		when(cServiceMock.getAll()).thenReturn( categoryList );
		
		mockMvc.perform( get("/categories") )
			.andExpect(status().isOk() )
		    .andExpect(jsonPath("$.categories"   , hasSize(4) ) )
		    .andExpect(jsonPath("$.categories[0]", is("Auto"  ) ) )
		    .andExpect(jsonPath("$.categories[1]", is("Manual") ) )
		    .andExpect(jsonPath("$.categories[2]", is("Lettuce") ) )
		    .andExpect(jsonPath("$.categories[3]", is("Tomato") ) );
		
		verify(cServiceMock, times(1)).getAll();
		verifyNoMoreInteractions(cServiceMock);
	}
	
	/**
	 * Test not so populated categories section.
	 * Should give an empty array, rather than null.
	 */
	@Test
	public void Test2() throws Exception {
		List<Category> categoryList = new ArrayList<Category>();
				
		when(cServiceMock.getAll()).thenReturn( categoryList );
		
		mockMvc.perform( get("/categories") )
			.andExpect(status().isOk() )
		    .andExpect(jsonPath("$.categories", hasSize(0) ) );
		
		verify(cServiceMock, times(1)).getAll();
		verifyNoMoreInteractions(cServiceMock);
	}
}
