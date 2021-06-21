package com.app.pizzaorder.datatransferobject;

import java.time.ZonedDateTime;

import javax.validation.constraints.NotNull;

import com.app.pizzaorder.domainvalue.OrderStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO
{
	private Long id;

	private ZonedDateTime dateCreated;

	@NotNull(message = "Size cannot be null!")
	private String size;
	
	@NotNull(message = "Variety cannot be null!")
	private String variety;

	private String topping;

	private Double price;

	private OrderStatus orderStatus;
	
	private boolean deleted;

	@NotNull(message = "Username cannot be null!")
	private String username;

	private OrderDTO()
	{
	}


	/**
	 * @param id
	 * @param username
	 * @param dateCreated
	 * @param orderStatus
	 * @param size
	 * @param variety
	 * @param topping
	 * @param price
	 * @param deleted
	 */
	private OrderDTO(Long id, 
			String username,
			ZonedDateTime dateCreated, 
			OrderStatus orderStatus,
			String size, 
			String variety, 
			String topping, 
			double price,
			boolean deleted)
	{
		this.id = id;
		this.username = username;
		this.dateCreated = dateCreated;
		this.orderStatus = orderStatus;
		this.size = size;
		this.variety = variety;
		this.topping = topping;
		this.price = price;
		this.deleted = deleted;
	}


	/**
	 * @return
	 */
	public static OrderDTOBuilder newBuilder()
	{
		return new OrderDTOBuilder();
	}


	/**
	 * @return
	 */
	@JsonProperty
	public Long getId()
	{
		return id;
	}


	/**
	 * @return
	 */
	public String getUsername()
	{
		return username;
	}


	/**
	 * @return
	 */
	public ZonedDateTime getDateCreated()
	{
		return dateCreated;
	}


	/**
	 * @return
	 */
	public OrderStatus getOrderStatus()
	{
		return orderStatus;
	}


	/**
	 * @return
	 */
	public String getSize()
	{
		return size;
	}


	/**
	 * @return
	 */
	public String getVariety()
	{
		return variety;
	}


	/**
	 * @return
	 */
	public String getTopping()
	{
		return topping;
	}


	/**
	 * @return
	 */
	public double getPrice()
	{
		return price;
	}
	
	
	/**
	 * @return
	 */
	public boolean getDeleted()
	{
		return deleted;
	}


	public static class OrderDTOBuilder
	{
		private Long id;
		private String username;
		private ZonedDateTime dateCreated;
		private OrderStatus orderStatus;
		private String size;
		private String variety; 
		private String topping; 
		private double price;
		private boolean deleted;


		/**
		 * @param orderId
		 * @return
		 */
		public OrderDTOBuilder setId(Long id)
		{
			this.id = id;
			return this;
		}


		/**
		 * @param username
		 * @return
		 */
		public OrderDTOBuilder setUsername(String username)
		{
			this.username = username;
			return this;
		}


		/**
		 * @param dateCreated
		 * @return
		 */
		public OrderDTOBuilder setDateCreated(ZonedDateTime dateCreated)
		{
			this.dateCreated = dateCreated;
			return this;
		}


		/**
		 * @param orderStatus
		 * @return
		 */
		public OrderDTOBuilder setOrderStatus(OrderStatus orderStatus) 
		{
			this.orderStatus = orderStatus;
			return this;
		}


		/**
		 * @param size
		 * @return
		 */
		public OrderDTOBuilder setSize(String size) 
		{
			this.size = size;
			return this;
		}


		/**
		 * @param variety
		 * @return
		 */
		public OrderDTOBuilder setVariety(String variety) 
		{
			this.variety = variety;
			return this;
		}

		/**
		 * @param topping
		 * @return
		 */
		public OrderDTOBuilder setTopping(String topping) 
		{
			this.topping = topping;
			return this;
		}

		/**
		 * @param price
		 * @return
		 */
		public OrderDTOBuilder setPrice(double price) 
		{
			this.price = price;
			return this;
		}
		
		/**
		 * @param deleted
		 * @return
		 */
		public OrderDTOBuilder setDeleted(boolean deleted) 
		{
			this.deleted = deleted;
			return this;
		}

		/**
		 * @return
		 */
		public OrderDTO createOrderDTO()
		{
			return new OrderDTO(id, username, dateCreated, orderStatus, size, variety, topping, price, deleted);
		}

	}
}