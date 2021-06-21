package com.app.pizzaorder.service.order;

import java.util.List;

import com.app.pizzaorder.domainobject.OrderDO;
import com.app.pizzaorder.domainvalue.OrderStatus;
import com.app.pizzaorder.exception.ConstraintsViolationException;
import com.app.pizzaorder.exception.EntityNotFoundException;

/**
 *
 */
public interface OrderService
{

    /**
     * @param orderId
     * @return
     * @throws EntityNotFoundException
     */
    OrderDO find(Long orderId) throws EntityNotFoundException;

    /**
     * @param orderDO
     * @return
     * @throws ConstraintsViolationException
     */
    OrderDO create(OrderDO orderDO) throws ConstraintsViolationException;

    /**
     * @param orderId
     * @param orderStatus
     * @throws EntityNotFoundException
     */
    void update(long orderId, OrderStatus orderStatus) throws EntityNotFoundException;

    /**
     * @param orderId
     * @throws EntityNotFoundException
     */
    void delete(Long orderId) throws EntityNotFoundException;

    /**
     * @param orderStatus
     * @return
     * @throws EntityNotFoundException
     */
    List<OrderDO> find(OrderStatus orderStatus) throws EntityNotFoundException;
    
}