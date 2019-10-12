package com.bartindr.bartender.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="drinks")
public class Drink {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long drinkId;
    private String name;
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    private String instruction;
    private String imgUrl;
    
    //REALTIONSHIP MAPPING
    
//    @JsonIgnore
    @ManyToMany(fetch=FetchType.LAZY)
   	@JoinTable(
   			name="favorites",
   			joinColumns = @JoinColumn(name = "drink_id"),
   			inverseJoinColumns = @JoinColumn(name = "user_id")
   			)
   	private List<User> users;
    
//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToMany(fetch=FetchType.LAZY)
   	@JoinTable(
   			name="drinks_ingredients",
   			joinColumns = @JoinColumn(name = "drink_id"),
   			inverseJoinColumns = @JoinColumn(name = "ingredient_id")
   			)
   	private List<Ingredient> ingredients;
       
//    @JsonIgnore
    @ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name="drink_lists_drink",
			joinColumns = @JoinColumn(name = "drink_id"),
			inverseJoinColumns = @JoinColumn(name = "drink_list_id")
			)
	private List<DrinkList> drinkLists;
    
    public Drink() {
    	// constructor
    }
    
    public Drink(String name, Long drinkId, String imgUrl) {
    	this.name = name;
    	this.drinkId = drinkId;
    	this.imgUrl = imgUrl;
    }
        
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDrinkId() {
		return drinkId;
	}

	public void setDrinkId(Long drinkId) {
		this.drinkId = drinkId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public List<DrinkList> getDrinkLists() {
		return drinkLists;
	}

	public void setDrinkLists(List<DrinkList> drinkLists) {
		this.drinkLists = drinkLists;
	}

	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
}
