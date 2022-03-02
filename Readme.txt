


Name : Vehicle Management System
Description : Application to handle operations Create, Update, Read, Delete

Requirements
------------

- Java 11
- MongoDB

Endpoints
----------
Create :
    Url  :  https://localhost:8080/addVehicle
    Body : 	{
       		"name":"BMW M5",
       		"vin":"test",
       		"licencePlateNumber":"XX-XX-547",
       		"vehicleProperties":
       			{
       			"color":"Sky Blue",
       			"year":"2021",
       			"transition":"Diseal"
       			}
       	}


Get Details :
    Url : https://localhost:8080/getVehicle/XX-XX-547


Update Details:
    Url : https://localhost:8080/updateVehicle
    Body : 	{
           		"name":"BMW M7 Series",
           		"vin":"test_updated",
           		"licencePlateNumber":"XX-XX-547",
           		"vehicleProperties":
           			{
           			"color":"Red",
           			"year":"2022",
           			"transition":"Petrol"
           			}
           	}

Delete Details:
    Url : https://localhost:8080/deleteVehicle/XX-XX-547



Run command
- mvn clean install


