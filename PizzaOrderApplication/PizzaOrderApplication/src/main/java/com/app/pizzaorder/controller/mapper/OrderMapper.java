package com.app.pizzaorder.controller.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.app.pizzaorder.datatransferobject.OrderDTO;
import com.app.pizzaorder.domainobject.OrderDO;

/**
 *
 */
public class OrderMapper
{
    /**
     * @param orderDTO
     * @return
     */
    public static OrderDO makeOrderDO(OrderDTO orderDTO)
    {
        return new OrderDO(orderDTO.getSize(), 
        				   orderDTO.getVariety(), 
        				   orderDTO.getTopping(), 
        				   orderDTO.getPrice(), 
        				   orderDTO.getOrderStatus(), 
        				   orderDTO.getUsername());
    }


    /**
     * @param orderDO
     * @return
     */
    public static OrderDTO makeOrderDTO(OrderDO orderDO)
    {
        OrderDTO.OrderDTOBuilder orderDTOBuilder = OrderDTO.newBuilder()
            .setId(orderDO.getId())
            .setDateCreated(orderDO.getDateCreated())
            .setDeleted(orderDO.getDeleted())
            .setSize(orderDO.getSize())
            .setVariety(orderDO.getVariety())
            .setTopping(orderDO.getTopping())
            .setPrice(orderDO.getPrice())
            .setOrderStatus(orderDO.getOrderStatus())
            .setUsername(orderDO.getUsername());
        return orderDTOBuilder.createOrderDTO();
    }


    /**
     * @param orders
     * @return
     */
    public static List<OrderDTO> makeOrderDTOList(Collection<OrderDO> orders)
    {
        return orders.stream()
            .map(OrderMapper::makeOrderDTO)
            .collect(Collectors.toList());
    }
}
