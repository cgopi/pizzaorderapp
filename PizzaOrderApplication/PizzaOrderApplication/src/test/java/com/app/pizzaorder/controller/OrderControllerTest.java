package com.app.pizzaorder.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.ZonedDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.app.pizzaorder.controller.mapper.OrderMapper;
import com.app.pizzaorder.datatransferobject.OrderDTO;
import com.app.pizzaorder.datatransferobject.OrderDTO.OrderDTOBuilder;
import com.app.pizzaorder.domainobject.OrderDO;
import com.app.pizzaorder.domainvalue.OrderStatus;
import com.app.pizzaorder.service.order.OrderService;
import com.app.pizzaorder.util.PizzaApplicationConstants;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Test controller for maintaining orders.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@PrepareForTest(OrderController.class)
public class OrderControllerTest {

	private OrderController controller;
	private OrderService orderService;
	private MockMvc mockMvc;
	private static final String ORDERS_ENDPOINT = "/v1/orders";
	private static final String ORDER_ID = "orderId";
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		orderService = mock(OrderService.class);
		controller = PowerMockito.spy(new OrderController(orderService));
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	//@Test
	public void testCreateOrder() throws Exception {
		
		OrderDTOBuilder orderDTOBuilder = OrderDTO.newBuilder()
											     .setId(1L)
											     .setDateCreated(ZonedDateTime.now())
											     .setDeleted(false)
											     .setOrderStatus(OrderStatus.ORDERED)
											     .setSize(PizzaApplicationConstants.SMALL_SIZE)
											     .setVariety(PizzaApplicationConstants.CHEESE_PIZZA)
											     .setTopping(PizzaApplicationConstants.ONION_TOPPING)
											     .setUsername("test");
		OrderDTO orderDTO = orderDTOBuilder.createOrderDTO();
		OrderDO orderDO = OrderMapper.makeOrderDO(orderDTO);
		
		Mockito.when(orderService.create(Matchers.anyObject())).thenReturn(orderDO);
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(ORDERS_ENDPOINT)
				.content(asJsonString(orderDTO)).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isCreated()).andReturn();
    	MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(201, response.getStatus());
	}
	
	@Test
	public void testCreateOrder_BadRequest() throws Exception {
		OrderDO orderDO = new OrderDO();
		orderDO.setDateCreated(ZonedDateTime.now());
		orderDO.setDeleted(false);
		orderDO.setId(1L);
		orderDO.setOrderStatus(OrderStatus.ORDERED);
		orderDO.setSize(PizzaApplicationConstants.SMALL_SIZE);
		orderDO.setVariety(PizzaApplicationConstants.CHEESE_PIZZA);
		orderDO.setTopping(PizzaApplicationConstants.ONION_TOPPING);
		orderDO.setUsername("test");
		
		Mockito.when(orderService.create(Matchers.anyObject())).thenReturn(orderDO);
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(ORDERS_ENDPOINT))
				.andExpect(status().isBadRequest())
    			.andReturn();
    	MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(400, response.getStatus());
	}
	
	@Test
	public void testCreateOrder_MethodNotAllowed() throws Exception {
		OrderDO orderDO = new OrderDO();
		Mockito.when(orderService.create(Matchers.anyObject())).thenReturn(orderDO);
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(ORDERS_ENDPOINT+"/1"))
				.andExpect(status().isMethodNotAllowed())
    			.andReturn();
    	MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(405, response.getStatus());
	}
	
	@Test
	public void testGetOrderById() throws Exception {
		OrderDO orderDO = new OrderDO();
		orderDO.setDateCreated(ZonedDateTime.now());
		orderDO.setDeleted(false);
		orderDO.setId(1L);
		orderDO.setOrderStatus(OrderStatus.ORDERED);
		orderDO.setSize(PizzaApplicationConstants.SMALL_SIZE);
		orderDO.setVariety(PizzaApplicationConstants.CHEESE_PIZZA);
		orderDO.setTopping(PizzaApplicationConstants.ONION_TOPPING);
		orderDO.setUsername("test");
		
		Mockito.when(orderService.find(1L)).thenReturn(orderDO);
		MvcResult result = mockMvc.perform(get(ORDERS_ENDPOINT+"/1"))
    			.andExpect(status().isOk())
    			.andReturn();
    	MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(200, response.getStatus());		
		assertNull(response.getErrorMessage());	
	}
	
	@Test
	public void testGetOrder_NotAllowedWithoutOrderId() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(ORDERS_ENDPOINT))
    			.andExpect(status().isBadRequest())
    			.andReturn();
    	MockHttpServletResponse response = result.getResponse();
		assertEquals(400, response.getStatus());
	}
	
	@Test
	public void testDeleteOrder() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete(ORDERS_ENDPOINT+"/1"))
				.andExpect(status().isOk())
    			.andReturn();
    	MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(200, response.getStatus());
	}
	
	@Test
	public void testDeleteOrder_InvalidOrderId() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete(ORDERS_ENDPOINT)
				.param(ORDER_ID, "-1"))
				.andExpect(status().isMethodNotAllowed())
    			.andReturn();
    	MockHttpServletResponse response = result.getResponse();
		assertEquals(405, response.getStatus());
	}
	
	@Test
	public void testUpdateOrder() throws Exception {
		Mockito.doNothing().when(orderService).update(Mockito.anyLong(), Mockito.anyObject());
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put(ORDERS_ENDPOINT+"/1")
				.param("orderStatus",  OrderStatus.DELIVERED.toString()))
				.andExpect(status().isOk())
    			.andReturn();
    	MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(200, response.getStatus());
	}
	
	@Test
	public void testFindOrders() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(ORDERS_ENDPOINT)
				.param("orderStatus", OrderStatus.ORDERED.toString()))
				.andExpect(status().isOk())
    			.andReturn();
    	MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(200, response.getStatus());
	}
	
	
	public static String asJsonString(final Object obj) {
	    try {
	        final ObjectMapper mapper = new ObjectMapper();
	        final String jsonContent = mapper.writeValueAsString(obj);
	        return jsonContent;
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	} 
	
	@After
	public void tearDown() {
		mockMvc = null;
		orderService = null;
		controller = null;
	}
	
}