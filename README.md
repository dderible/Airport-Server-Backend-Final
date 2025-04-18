Declan, Logan, and Nicholas's Airport API QAP (BACK END)
***************************

For Front End, please see: https://github.com/oram79/Airport-Client-Frontend

***************************

This project features endpoints that allow for the following:
- Create/Delete Aircrafts (ID, Model, Airline Name, Number of Passengers)
- Create/Delete Airlines (ID, Name, Origin Country)
- Create/Delete Airports (ID, Name, Code)
- Create/Delete Cities (ID, Name, Country)
- Create/Delete Flights (ID, Seat, Origin, Destination)
- Create/Delete Passengers (ID, Name, Address, Phone Number)
- View an Airport’s arrivals and departures (as a list)
- An Admin Panel to fully add, edit, or delete any of the above entities

***************************

Installation Process:
1) Clone this repository
2) Run the following Docker commands in order:
   - First, create a Maven package of this project
   - Second, run this command: docker build -t airport-server .
   - Thirdly, run this command: docker compose up
   - Finally, run the project!
3) And you're live! Feel free to test this as you wish! All API Endpoints can be found in these files:
- AircraftController.java
- AirlineController.java
- AirportController.java
- CitiesController.java
- FlightController.java
- GateController.java
- PassengersController.java

This project also has Docker Support for cloning and testing!

***************************
