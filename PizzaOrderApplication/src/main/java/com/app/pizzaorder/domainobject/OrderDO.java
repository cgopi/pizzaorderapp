package com.app.pizzaorder.domainobject;

import java.io.Serializable;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.app.pizzaorder.domainvalue.OrderStatus;

/**
 *
 */
@Entity
@Table(name = "orders")
public class OrderDO implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7031966744734573946L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_created", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();
    
    @Column(name = "deleted", nullable = false)
    private Boolean deleted = false;
    
    @Column(name = "size")
    private String size;
    
    @Column(name = "variety", nullable = false)
    private String variety;
    
    @Column(name = "topping", nullable = false)
    private String topping;
    
    @Column(name = "price")
    private double price;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus orderStatus;
    
    @Column(name = "username", nullable = false)
    private String username;
    
    /**
     * 
     */
    public OrderDO() {
    	
    }
    
    /**
     * 
     */
    public OrderDO(final String username) {
    	this.username = username;
    }

    /**
     * @param size
     * @param variety
     * @param orderStatus
     * @param username 
     */
    public OrderDO(final String size, final String variety, final String topping, final double price, final OrderStatus orderStatus, final String username) {
    	this.size = size;
    	this.variety = variety;
    	this.topping = topping;
    	this.price = price;
    	this.orderStatus = orderStatus;
    	this.username = username;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ZonedDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(ZonedDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getVariety() {
		return variety;
	}

	public void setVariety(String variety) {
		this.variety = variety;
	}

	public String getTopping() {
		return topping;
	}

	public void setTopping(String topping) {
		this.topping = topping;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
    
}