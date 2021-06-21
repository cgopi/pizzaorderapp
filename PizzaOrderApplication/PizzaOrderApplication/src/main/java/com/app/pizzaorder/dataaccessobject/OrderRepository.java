package com.app.pizzaorder.dataaccessobject;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.app.pizzaorder.domainobject.OrderDO;
import com.app.pizzaorder.domainvalue.OrderStatus;

/**
 * Database Access Object for order table.
 * <p/>
 */
public interface OrderRepository extends CrudRepository<OrderDO, Long>
{

    /**
     * @param orderStatus
     * @return
     */
    List<OrderDO> findByOrderStatus(OrderStatus orderStatus);
    
    /**
     * @param username
     * @param onlineStatus
     * @return
     */
    List<OrderDO> findByUsernameAndOrderStatus(String username, OrderStatus orderStatus);
    
}
