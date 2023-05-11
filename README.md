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
