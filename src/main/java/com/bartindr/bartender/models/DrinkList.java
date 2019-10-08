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
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="name")
public class DrinkList {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
	@DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User owner;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name="drink_lists_drinks",
			joinColumns = @JoinColumn(name = "drink_list_id"),
			inverseJoinColumns = @JoinColumn(name = "drink_id")
			)
	private List<Drink> drinks;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name="drink_lists_ingredients",
			joinColumns = @JoinColumn(name = "drink_list_id"),
			inverseJoinColumns = @JoinColumn(name = "ingredient_id")
			)
	private List<Ingredient> ingredients;
	
	public DrinkList() {
		//constructor
	}
	
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public User getOwnder() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public List<Drink> getDrinks() {
		return drinks;
	}
	public void setDrinks(List<Drink> drinks) {
		this.drinks = drinks;
	}
	public List<Ingredient> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
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
