# Thomas_Louvet_3_16032023 - CHATOP API 

# Project Requirements

NodeJS v16+
Angular CLI v14+
Java 17+
MySQL v8.0
Maven (installed on your system or available with your IDE)

# Get the project

You can clone the whole project from here : `git clone https://github.com/TLouvet/Thomas_Louvet_3_16032023.git`

# Prepare the database

In a mysql terminal or in the workbench, create a database and select it.

From MySQL Workbench : 
Import the sql script from the /backend/src/main/resources/script.sql and execute it, this will create the tables

From MySQL terminal: 
Use the command `SOURCE </absolute/path/to/script>`

# Setup the backend

Create a file named application.properties in the /backend/src/main/resources folder.
Fill it with the variables existing on the application.example.properties and your custom values

With maven, compile and run the project : `mvn compile` and `mvn spring-boot:run`

A message is displayed at the end of the loading time: "App started with success"

# Setup the frontend

From the frontend folder, execute `npm install` in the terminal, once finished start the project with `npm run start`. 

# Frontend environment

By default the frontend will call the backend at port 3001, if needed change the environment file to match your server.port of the application.properties file.
