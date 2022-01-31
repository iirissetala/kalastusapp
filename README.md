# KalastusApp
This app is made for fishers to use as a "catch diary". A catch consist of fisher, date, location, lure and a list of fishes. You can save a new catch, edit an existing catch or delete one. With this backend implementation you can also add, modify and delete fishes. 

## Technologies
Project is created with 
* Spring Boot 2.6.2
* Java 11
* Maven 4.0.0

Project uses h2 as database and JPA with Hibernate for data mapping. For tests, an in-memory database is created.

## How to use it?
Clone the project, start the app with your ide and try it with postman! 
You can get an empty fish object with url /api/kalat/0 and catch object with url /api/saaliit/0.
Note that you can only insert a list of fishes, so if you only wish to insert one, put it inside [] and it will work.

## Further development
My intention is to implement frontend at some point. Also it would be nice to add possibility to upload a photo of a catch.
