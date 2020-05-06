# CurrencyExchange project from Bitcoin to US-Dollar

## How to run this project ?
Run this command "mvn spring-boot:run" in command prompt or open the project with any IDE and just Run As Java Appliction class CurrencyExchangeApplication.java

## Project specs
Java12
Springboot
H2 Database embedded
YahooFinance library to get currency exchange information
Swagger for documentation.

## Notes
This project has created based on the Challenge defined by SearchMetrics Company.

## User manual

### 1) Get latest rate
http://localhost:8080/currency-exchange/v7/latestrate

### 2) Get historical rates between startDate and endDate
http://localhost:8080/currency-exchange/v7/historicalrates?startdate=2017-07-25&enddate=2017-09-01

### 3) Get all (just for test)
http://localhost:8080/currency-exchange/v7/all


## API documentation (with Swagger)
You can check API Documentation at this URL:
http://localhost:8080/swagger-ui.html

## Database console
You can explore and perform queries in H2 Database by opening this URL:
http://localhost:8080/console