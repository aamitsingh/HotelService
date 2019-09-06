   
# Overview

Service: Hotel Service

Description: This service will do the following operation on the hotel inventory:

(1) Get the inventory 
    URL: "/hotelservice/cities/{city}"
    Purpose: This will return the valid Inventory Map which contains the hotel inventory for a
             city in the same order as that stored in the JSON. It will not apply any logic/processing on the data.

(2) Get the inventory ordered by price
    URL: "/priceorderedhotel/cities/{city}"
    Purpose: This will return the hotel inventory [SORTED BY PRICE]. All the hotel will be sorted by the price 
    as well as the partners inside the hotels will be sorted. So, the hotel with lowest price will come first and 
    also the hotels partners will be sorted. Refer to the following illustration:
    
       City: ABC
       [Hotel-1] : <Lowest Price> <Offers from other partners sorted in ascending order.>
       [Hotel-2] : <Second Lowest> <--Same as above-->
       [Hotel-3] : <Third Lowest> <--Same as above-->
       .........
       [Hotel-N] : <Highest Price> <--Same as above-->
    
       e.g.
       City: Düsseldorf
       [Hotel Mercure] <Last minute: Eur 52>  <Booking: Eur 60>  <Zen Hotels: Eur 70>
       [Hotel Hilton ] <Booking.com: Eur 125> <Expedia: Eur 130> <Zen Hotels: Eur 140>
    

(3) Validate Partners
    URL : "/validatePartner"
    Purpose : It Validate JSON structure and check whether the mandatory (amount, dateFrom, dateTo) field for partner 
    offer is present or not.

Inout JSON Data format: (Hotel) -[hasMany]-> (Partner) -[hasMany]-> (Price)

### Requirements:

* Oracle Java Runtime 1.8 or higher
* Maven 3.5.0
* Eclipse / IntelliJ

### Start-up the application

Build : ``./mvnw clean install``
Run: ``mvn spring-boot:run``

## Execution URLs:

      - http://localhost:8080/hotelservice/cities/Düsseldorf

      - http://localhost:8080/priceorderedhotel/cities/Düsseldorf 

      - http://localhost:8080/validatePartner

## Assumptions, limitations & probable future extension:
(1) In [datasource.json] I considered that there would be multiple JSON. One city has a lengthy inventory.
    So keeping one JSON for one city would be one way to keep the inventory.
(2) A partner will have multiple room category, e.g. Single, double etc. Probably, we should ask user the room category and show 
    the prices of that room category only. This I considered as extension and not implemented at this moment.
      
    
