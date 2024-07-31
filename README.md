# MoneyExService

## Overview

MoneyExService is a simple web application designed to facilitate money exchanges between different currencies. It simplifies the process of currency conversion and provides a user-friendly interface to manage and execute exchanges.

## Features

- **Currency Exchange**: Easily exchange between various currencies.
- **User-Friendly Interface**: Simple and intuitive interface for performing money operations.

## Requirements

To run the MoneyExService application, ensure you have the following:

- **Java**: JDK 11 or later
- **Maven**: Build tool for managing dependencies
- **MySQL**: Relational database for storing currency and exchange data

## Installation

### Setup Database

1. **Run Database Initialization**

   Open and execute the `DB-init.sql` file to set up the required tables and initial data in your MySQL database.

### Application Setup

1. **Clone the Repository**

   Clone the repository to your local machine:

   ```
   git clone https://github.com/Kriv09/MoneyExService.git
   cd MoneyExService
   ```
   
2. **Load Maven Dependencies**
```
mvn clean install
```

3. **Configure Database Connection**
_in 'src/main/resources/application.properties'_
```
spring.datasource.url=jdbc:mysql://localhost:3306/***your_database***
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=***your_password***
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update

```

4. **Start the Application**
_Run the application:_
MVN :
```
mvn spring-boot:run
```
or just click **RUN** button in your IDE

5. **Access the Application**
_Open your web browser and navigate to:_
```
http://localhost:8080/currencies
```

## Contributing
1. Fork the repository.
2. Create a new branch:
```
git checkout -b feature/your-feature
```

3. Make your changes.
   
4. Commit your changes:
```
git commit -am 'Add new feature'
```

5. Push to the branch:
```
git push origin feature/your-feature
```

6. Create a new Pull Request.













