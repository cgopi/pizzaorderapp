# Pizza Order Application

## Task Description
You should be able to start the example application by executing com.app.pizzaorder.PizzaOrderApplication, which starts a webserver on port 8080 (http://localhost:8080) and serves SwaggerUI where can inspect and try existing endpoints.

The project is based on a small web service which uses the following technologies:

* Java 1.8
* Spring MVC with Spring Boot
* Database H2 (In-Memory)
* Maven


 * All new entities have an ID with type of Long and a date_created with type of ZonedDateTime.
 * The architecture of the web service is built with the following components:
   * DataTransferObjects: Objects which are used for outside communication via the API
   * Controller: Implements the processing logic of the web service, parsing of parameters and validation of in- and outputs.
   * Service: Implements the business logic and handles the access to the DataAccessObjects.
   * DataAccessObjects: Interface for the database. Inserts, updates, deletes and reads objects from the database.
   * DomainObjects: Functional Objects which might be persisted in the database.
 
#Usecase 1:
* Created a new Controller for maintaining orders (CRUD).
* Entity Order: have at least the following characteristics: id, date_created, deleted, size, variety, topping, price, order_status, username in orders table.
* Add example data to resources/data.sql

#Usecase 2:
* Created a new endpoint to get the order details based on order id
* return details of an order

#Usecase 3:
* Created a new endpoint for searching all the orders based on order status
* return list of orders

#Usecase 4:
* Created a new endpoint to create a new order based on id, date_created, size, variety, topping, order_status, username in order table.
* order is created in order table
* return the details of an order with price

#Usecase 5:
* Created a new endpoint to modify the order based on order status (DELIVERED, ORDERED, CANCELED)
* update the order table 

#Usecase 6:
* Created a new endpoint to delete the order based on order id
* delete the order based on boolean flag (Soft Delete)