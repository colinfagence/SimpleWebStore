package db.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
@NamedQueries( { @NamedQuery( name="Categories.findByName", query="SELECT c FROM Category c WHERE c.name=:name" ),
		         @NamedQuery( name="Categories.findAll"   , query="SELECT c FROM Category c") } )
public class Category {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int    id;
	private String name;
	
	@OneToMany( targetEntity=Product.class, mappedBy="category" )
	private List<Product> products;
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public List<Product> getProducts() {
		return products;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setProducts( List<Product> products ) {
		this.products = products;
	}
}
