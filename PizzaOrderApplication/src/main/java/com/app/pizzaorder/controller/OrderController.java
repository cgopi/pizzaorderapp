package com.app.pizzaorder.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.pizzaorder.controller.mapper.OrderMapper;
import com.app.pizzaorder.datatransferobject.OrderDTO;
import com.app.pizzaorder.domainobject.OrderDO;
import com.app.pizzaorder.domainvalue.OrderStatus;
import com.app.pizzaorder.exception.ConstraintsViolationException;
import com.app.pizzaorder.exception.EntityNotFoundException;
import com.app.pizzaorder.service.order.OrderService;

import io.swagger.annotations.ApiOperation;

/**
 * All operations with an order will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/orders")
public class OrderController
{
    private final OrderService orderService;

    /**
     * @param orderService
     */
    @Autowired
    public OrderController(final OrderService orderService)
    {
        this.orderService = orderService;
    }
    
    /**
     * @param orderId
     * @return
     * @throws EntityNotFoundException
     */
    @ApiOperation("Get order details using order id")
    @GetMapping("/{orderId}")
    public OrderDTO getOrderById(@PathVariable long orderId) throws EntityNotFoundException
    {
    	return OrderMapper.makeOrderDTO(orderService.find(orderId));
    }

    /**
     * @param orderDTO
     * @return
     * @throws ConstraintsViolationException
     */
    @ApiOperation("Create an order")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDTO createOrder(@Valid @RequestBody OrderDTO orderDTO) throws ConstraintsViolationException
    {
        OrderDO orderDO = OrderMapper.makeOrderDO(orderDTO);
        return OrderMapper.makeOrderDTO(orderService.create(orderDO));
    }
    
    /**
     * @param orderId
     * @throws EntityNotFoundException
     */
    @ApiOperation("Update an order by order status")
    @PutMapping("/{orderId}")
    public ResponseEntity<Void> updateOrder(
        @PathVariable long orderId, @RequestParam OrderStatus orderStatus)
        throws EntityNotFoundException
    {
        orderService.update(orderId, orderStatus);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    /**
     * @param orderId
     * @throws EntityNotFoundException
     */
    @ApiOperation("Delete an order")
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrderById(@PathVariable long orderId) throws EntityNotFoundException
    {
    	orderService.delete(orderId);
    	return new ResponseEntity<Void>(HttpStatus.OK);
    }

    /**
     * @param onlineStatus
     * @return
     * @throws EntityNotFoundException
     */
    @ApiOperation("Find all orders based on order status")
    @GetMapping
    public List<OrderDTO> findOrders(@RequestParam OrderStatus orderStatus) throws EntityNotFoundException
    {
        return OrderMapper.makeOrderDTOList(orderService.find(orderStatus));
    }

}