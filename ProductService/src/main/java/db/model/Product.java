package db.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "products")
@NamedQuery(name="Products.findAll", query="SELECT p FROM Product p")
public class Product {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int    id;
	private String name;
	private String description;
	private float  price;
	
	@ManyToOne
	@JoinColumn(name="categoryId", referencedColumnName="id", insertable=false, updatable=false)
	@JsonIgnore
	private Category category;
	
	public int getId() {
		return id;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getName() {
		return name;
	}
	
	public float getPrice() {
		return price;
	}
	
	public Category getCategory() {
		return category;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}
}
