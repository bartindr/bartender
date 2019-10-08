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
    
    @ManyToMany(fetch=FetchType.LAZY)
   	@JoinTable(
   			name="users_dricks",
   			joinColumns = @JoinColumn(name = "drink_id"),
   			inverseJoinColumns = @JoinColumn(name = "user_id")
   			)
   	private List<User> users;
    
    @ManyToMany(fetch=FetchType.LAZY)
   	@JoinTable(
   			name="drinks_ingredients",
   			joinColumns = @JoinColumn(name = "drink_id"),
   			inverseJoinColumns = @JoinColumn(name = "ingredient_id")
   			)
   	private List<Ingredient> ingredients;
       
    
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
    
    
    
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
}
