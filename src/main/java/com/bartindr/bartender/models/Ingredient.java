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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ingredients")
public class Ingredient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String name;
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;

    @JsonIgnore
    @ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name="drinks_ingredients",
			joinColumns = @JoinColumn(name = "ingredient_id"),
			inverseJoinColumns = @JoinColumn(name = "drink_id")
			)
	private List<Drink> drinks;
    
    @JsonIgnore
    @ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name="drink_lists_ingredients",
			joinColumns = @JoinColumn(name = "ingredient_id"),
			inverseJoinColumns = @JoinColumn(name = "drink_list_id")
			)
	private List<DrinkList> drinkLists;
    
    public Ingredient() {
    	//constructor
    }
    
    public Ingredient(String name) {
    	this.name = name;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<Drink> getDrinks() {
		return drinks;
	}

	public void setDrinks(List<Drink> drinks) {
		this.drinks = drinks;
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
