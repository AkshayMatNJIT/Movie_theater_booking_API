## `movie-theater`
This is a RESTful API created using Spring boot with Apache Derby as the embedded database.
Following are the URL endpoints for Customer and Reservation operations -  
#### Create Customer (POST) -
http://localhost:8090/customer/create
#### Get Customer reservations by Customer Id (GET) -
http://localhost:8090/customer/{customerId}
#### Get Showing today schedule (GET) -
http://localhost:8090/reservation/schedule
#### Get ticketPrice for a movie by sequenceNum (GET) -
http://localhost:8090/reservation/ticket/fare
#### Buy tickets / make Reservation (POST) -
http://localhost:8090/reservation/tickets/buy
### Current Features
* Customer can make a reservation for the movie
  * And, system can calculate the ticket fee for customer's reservation
* Theater have a following discount rules
  * 20% discount for the special movie
  * $3 discount for the movie showing 1st of the day
  * $2 discount for the movie showing 2nd of the day
* System can display movie schedule with simple text format

## New Requirements (Implemented)
* New discount rules; In addition to current rules
  * Any movies showing starting between 11AM ~ 4pm, you'll get 25% discount
  * Any movies showing on 7th, you'll get 1$ discount
  * The discount amount applied only one if met multiple rules; biggest amount one
* We want to print the movie schedule with simple text & json format