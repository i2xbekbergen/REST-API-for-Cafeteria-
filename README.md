# REST-API-for-Cafeteria-
## Overview
This is REST API to use CRUD commands for cafeteria app using Java Spring-boot framework and MongoDB Database system.

## How to run?
First, you have to have Docker installed in your machine. You have to run Docker Desktop, and then type the following command to the terminal

```
docker run -d --name container_name -e MONGO_INITDB_ROOT_USERNAME=admin -e MONGO_INITDB_ROOT_PASSWORD=password -p 27017:27017 mongo
```

You can change container_name however you want. Then you should run the image in the Docker Desktop.

Then you have to open a new terminal and run the following command:
```
./mvnw spring-boot:run
```

This will launch the app on localhost:8080.

Then, open a browser and go to ```http://localhost:8080/swagger-ui/```
or you can use curl commands to see how the app works. 

## How it works?

You can see different controller functions if you open ```http://localhost:8080/swagger-ui/```. You can click to any of those but here is the brief information about each of them

### save API
This API is just a simple `POST` API that allows you to save the data in the database.

#### How to use it?

* If you use the __swagger ui__, there you can find a save API. You can click on its icon, there you will see the text editor with the input type of the json format as shown below:
  ```
  {
    "cafeteriaId": "string",
    "food": "string",
    "name": "string",
    "price": 0
  }
  ```
  You can change the inputs by setting the cafeteriaId/food/name/price. If the posting succeeded, the funciton will return you the cafeteriaId back.
* If you are on the terminal, you can write the following command in the terminal
  ```
  curl -X POST "http://localhost:8080/cafeteria" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"cafeteriaId\": \"string\", \"food\": \"string\", \"name\": \"string\", \"price\": 0}"
  ```
  You can change the inputs by setting the cafeteriaId/food/name/price. If the posting succeeded, the funciton will return you the cafeteriaId back.

### getCafeteriaStartWith API

This API allows you to find the information about the cafeteria that starts with a certain string.

#### How to use it?

* If you use the __swagger ui__, there you can post a data using `save` API. Then you can use `getCafeteriaStartWith` API to find the information desired cafeteria.
  For example, let's input the following data 
  ```
  {
    "cafeteriaId": "1",
    "food": "Korean",
    "name": "Student Cafeteria",
    "price": 4000
  }
  ```
  Then if you open the `getCafeteriaStartWith` API, you will see a search bar requiring string input. If you input `Stud`, it will return the above information that we've already posted via `save` API.
* If you are on the terminal, you can write the following command in the terminal
  ```
  curl -X GET "http://localhost:8080/cafeteria?name=Stud" -H "accept: */*"
  ```
It will return the above information that we've already posted via `save` API. 

### delete API

This API is just a simple `DELETE` API that allows you to delete the data in the database by id.

#### How to use it?

* If you use the __swagger ui__, there you can find a `delete` API. You can click on its icon, there you will see the text editor with the input requiring `id`. 
* If you are on the terminal, you can write the following command in the terminal
  ```
  curl -X DELETE "http://localhost:8080/cafeteria/1" -H "accept: */*"
  ```

If the posting succeeded, the function will return code 200.

### getHighestPrice API

This API will return the food with the highest price in each of the cafeterias in the database.

#### How to use it?

* If you use the __swagger ui__, there you can find a `getHighestPrice` API. You can click on its icon, there you will see `Try it out` button, then if you press the execute button.
* If you are on the terminal, you can write the following command in the terminal
  ```
  curl -X GET "http://localhost:8080/cafeteria/highestPrice" -H "accept: */*"
  ```

This will return you the list of foods with the highest prices in each cafeteria.

### getByCafeteriaPrice API

This API requires two inputs: `minPrice` and `maxPrice`, and returns the cafeterias with prices that lie between `minPrice` and `maxPrice`.

#### How to use it?

* If you use the __swagger ui__, there you can find a `getByCafeteriaPrice` API. You can click on its icon, there you will see `Try it out` button, then you will find two type bars with inputs requiring integers for `minPrice` and `maxPrice`. 
* If you are on the terminal, you can write the following command in the terminal
  ```
  curl -X GET "http://localhost:8080/cafeteria/price?maxPrice=yourInput1&minPrice=yourInput2" -H "accept: */*"
  ```
  you can change `yourInput1` and `yourInput2`.

This will return the cafeterias with prices that lie between `minPrice` and `maxPrice`.

### searchCafeteria API

This API is basically the filter tab for the app. It can accept four different inputs: `food`, `name`, `minPrice` and `maxPrice`. This will return you the list of cafeterias that fit under the filters user inserted.

#### How to use it?

* If you use the __swagger ui__, there you can find a `searchCafeteria` API. You can click on its icon, there you will see `Try it out` button, then you will find four type bars with inputs requiring integers for `minPrice` and `maxPrice`, and strings for `name` and `food`. 
* If you are on the terminal, you can write the following command in the terminal
  ```
  curl -X GET "http://localhost:8080/cafeteria/search?food=yourFoodName&maxPrice=yourMaxPrice&minPrice=yourMinPrice&name=yourCafeteriaName&page=0&size=5" -H "accept: */*"
  ```
  You can change `yourFoodName`, `yourMaxPrice`, `yourMinPrice` and `yourCafeteriaName`.

This will return the list of cafeterias that fit under the filters user inserted.
