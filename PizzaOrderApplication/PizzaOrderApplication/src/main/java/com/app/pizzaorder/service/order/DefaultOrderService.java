package com.app.pizzaorder.service.order;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.pizzaorder.dataaccessobject.OrderRepository;
import com.app.pizzaorder.domainobject.OrderDO;
import com.app.pizzaorder.domainvalue.OrderStatus;
import com.app.pizzaorder.exception.ConstraintsViolationException;
import com.app.pizzaorder.exception.EntityNotFoundException;
import com.app.pizzaorder.util.PizzaApplicationConstants;
import com.app.pizzaorder.util.StoreManagerUtil;

/**
 * Service to encapsulate the link between DAO and controller and to have business logic for some order specific things.
 * <p/>
 */
@Service
public class DefaultOrderService implements OrderService
{

	private final OrderRepository orderRepository;

	/**
	 * @param orderRepository
	 */
	public DefaultOrderService(final OrderRepository orderRepository)
	{
		this.orderRepository = orderRepository;
	}

	/**
	 * Selects an order by id.
	 *
	 * @param orderId
	 * @return found order
	 * @throws EntityNotFoundException if no order with the given id was found.
	 */
	@Override
	public OrderDO find(Long orderId) throws EntityNotFoundException
	{
		return findOrderChecked(orderId);
	}

	/**
	 * Creates a new order.
	 *
	 * @param orderDO
	 * @return
	 * @throws ConstraintsViolationException if a order already exists with the given username, ... .
	 */
	@Override
	public OrderDO create(OrderDO orderDO) throws ConstraintsViolationException
	{
		OrderDO order;
		double price;
		String deal;
		int inventory;
		try
		{
			if (!(PizzaApplicationConstants.SMALL_SIZE.equals(orderDO.getSize()) && PizzaApplicationConstants.CHEESE_PIZZA.equals(orderDO.getVariety()))
					&& !(PizzaApplicationConstants.MEDIUM_SIZE.equals(orderDO.getSize()) && PizzaApplicationConstants.CHICKEN_PIZZA.equals(orderDO.getVariety()))
					&& !(PizzaApplicationConstants.LARGE_SIZE.equals(orderDO.getSize()) && PizzaApplicationConstants.BEEF_PIZZA.equals(orderDO.getVariety()))) {
				throw new ConstraintsViolationException("Invalid Order");
			}
			order = orderRepository.save(orderDO);
			if (order.getOrderStatus().equals(OrderStatus.ORDERED)) {
				//Write logic to find price based on size of pizza, variety of pizza, toppings(if any), deals(if any)
				if (PizzaApplicationConstants.SMALL_SIZE.equals(order.getSize()) 
						&& PizzaApplicationConstants.CHEESE_PIZZA.equals(order.getVariety())) {
					inventory = StoreManagerUtil.getInventory(order.getVariety()); 
					if (inventory >= 1) {
						deal = StoreManagerUtil.getAvailableDeal(order.getVariety());
						if (PizzaApplicationConstants.ONION_TOPPING.equals(order.getTopping())) {
							price = 11.00d;
						} else {
							price = 9.00d;
						}
						if (deal != null && deal.equals(PizzaApplicationConstants.ONE_PERCENT_ON_CHEESE_PIZZA)) { //Deal: 1%
							price = StoreManagerUtil.getDealPrice(price, deal);
						}
						order.setPrice(price);
					}
					PizzaApplicationConstants.CHEESE_PIZZA_INVENTORY--;
				} else if (PizzaApplicationConstants.MEDIUM_SIZE.equals(order.getSize()) 
						&& PizzaApplicationConstants.CHICKEN_PIZZA.equals(order.getVariety())) {
					inventory = StoreManagerUtil.getInventory(order.getVariety()); 
					if (inventory >= 1) {
						deal = StoreManagerUtil.getAvailableDeal(order.getVariety());
						if (PizzaApplicationConstants.OLIVES_TOPPING.equals(order.getTopping())) {
							price = 13.00d;
						} else {
							price = 11.00d;
						}
						if (deal != null && deal.equals(PizzaApplicationConstants.THREE_PERCENT_ON_CHICKEN_PIZZA)) { //Deal: 3%
							price = StoreManagerUtil.getDealPrice(price, deal);
						}
						order.setPrice(price);
					}
					PizzaApplicationConstants.CHICKEN_PIZZA_INVENTORY--;
				} else if (PizzaApplicationConstants.LARGE_SIZE.equals(order.getSize()) 
						&& PizzaApplicationConstants.BEEF_PIZZA.equals(order.getVariety())) {
					inventory = StoreManagerUtil.getInventory(order.getVariety()); 
					if (inventory >= 1) {
						deal = StoreManagerUtil.getAvailableDeal(order.getVariety());
						if (PizzaApplicationConstants.SPINACH_TOPPING.equals(order.getTopping())) {
							price = 15.00d;
						} else {
							price = 13.00d;
						}
						if (deal != null && deal.equals(PizzaApplicationConstants.TWO_PERCENT_ON_BEEF_PIZZA)) { //Deal: 2%
							price = StoreManagerUtil.getDealPrice(price, deal);
						}
						order.setPrice(price);
						PizzaApplicationConstants.BEEF_PIZZA_INVENTORY--;
					}
				} 
			}
		}
		catch (DataIntegrityViolationException e)
		{
			Logger.getLogger("DefaultOrderService").warning("ConstraintsViolationException while creating a order");
			throw new ConstraintsViolationException(e.getMessage());
		}
		return order;
	}


	/**
	 * Deletes an existing order by id.
	 *
	 * @param orderId
	 * @throws EntityNotFoundException if no order with the given id was found.
	 */
	@Override
	@Transactional
	public void delete(Long orderId) throws EntityNotFoundException
	{
		OrderDO orderDO = findOrderChecked(orderId);
		orderDO.setDeleted(true);
	}


	/**
	 * Update the order.
	 *
	 * @param orderId
	 * @param orderStatus
	 * @throws EntityNotFoundException
	 */
	@Override
	@Transactional
	public void update(long orderId, OrderStatus orderStatus) throws EntityNotFoundException
	{
		OrderDO orderDO = findOrderChecked(orderId);
		orderDO.setOrderStatus(orderStatus);
	}

	/**
	 * @param orderId
	 * @return
	 * @throws EntityNotFoundException
	 */
	private OrderDO findOrderChecked(Long orderId) throws EntityNotFoundException
	{
		return orderRepository.findById(orderId)
				.orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + orderId));
	}

	@Override
	public List<OrderDO> find(OrderStatus orderStatus) throws EntityNotFoundException {
		return orderRepository.findByOrderStatus(orderStatus);
	}

}