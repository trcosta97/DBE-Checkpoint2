# Spring Boot API for Client and Product Management

This project is a Spring Boot application that provides RESTful API endpoints for managing clients, products, and Pix transactions.

The API-Cliente-Produto application offers a comprehensive solution for handling user accounts, product management, and Pix transactions. It leverages Spring Boot's powerful features to provide a robust and scalable backend service.

Key features include:
- User management (registration, retrieval, and deactivation)
- Product management (creation, retrieval, update, and deactivation)
- Pix key management
- Pix transaction processing
- Integration with Oracle database

## Repository Structure

```
api-cliente-produto/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/api/apiClienteProduto/
│   │   │       ├── controller/
│   │   │       ├── entity/
│   │   │       └── service/
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
│           └── com/api/apiClienteProduto/
└── build.gradle
```

### Key Files:
- `ApiClienteProdutoApplication.java`: Entry point of the application
- `build.gradle`: Gradle build configuration
- `application.properties`: Application configuration

### Important Integration Points:
- Oracle Database: The application uses Oracle JDBC driver for database connectivity
- OpenAPI: SpringDoc OpenAPI is used for API documentation

## Usage Instructions

### Installation

Prerequisites:
- Java Development Kit (JDK) 17
- Gradle 7.x or later

Steps:
1. Clone the repository
2. Navigate to the project directory
3. Run `./gradlew build` to build the project

### Getting Started

To run the application:

```bash
./gradlew bootRun
```

The application will start on the default port 8080.

### Configuration Options

Key configuration options can be set in `src/main/resources/application.properties`:

```properties
# Database Configuration
spring.datasource.url=jdbc:oracle:thin:@localhost:1521/XE
spring.datasource.username=your_username
spring.datasource.password=your_password

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### API Endpoints

1. User Management:
   - POST `/usuarios`: Register a new user
   - GET `/usuarios`: Retrieve all active users
   - GET `/usuarios/{id}`: Retrieve a specific user
   - PUT `/usuarios/{id}`: Update user information
   - DELETE `/usuarios/{id}`: Deactivate a user

2. Product Management:
   - POST `/produtos`: Create a new product
   - GET `/produtos/all`: Retrieve all active products
   - GET `/produtos/{id}`: Retrieve a specific product
   - PUT `/produtos/{id}`: Update product information
   - DELETE `/produtos/{id}`: Deactivate a product

3. Pix Transactions:
   - POST `/transacoes`: Perform a Pix transaction
   - GET `/transacoes/recebidas/{id}`: Retrieve received transactions for a user
   - GET `/transacoes/efetuadas/{id}`: Retrieve sent transactions for a user

### Testing & Quality

To run tests:

```bash
./gradlew test
```

### Troubleshooting

Common Issues:

1. Database Connection Errors:
   - Ensure Oracle database is running and accessible
   - Verify database credentials in `application.properties`
   - Check for proper JDBC driver in `build.gradle`

2. Compilation Errors:
   - Ensure JDK 17 is installed and properly configured
   - Run `./gradlew clean build` to clean and rebuild the project

3. Runtime Errors:
   - Check application logs for detailed error messages
   - Verify all required dependencies are properly declared in `build.gradle`

Debugging:
- Enable debug logging by adding `logging.level.root=DEBUG` to `application.properties`
- Use an IDE like IntelliJ IDEA or Eclipse for step-by-step debugging

## Data Flow

The application follows a typical Spring MVC architecture:

1. HTTP requests are received by the respective controllers (e.g., `UsuarioController`, `ProdutoController`, `TransacaoPixController`)
2. Controllers delegate business logic to corresponding service classes (e.g., `UsuarioService`, `ProdutoService`, `TransacaoPixService`)
3. Services interact with repository interfaces to perform database operations
4. Repositories use Spring Data JPA to execute database queries
5. Results are passed back through the service layer to the controllers
6. Controllers format and return the HTTP response

```
[Client] <-> [Controller] <-> [Service] <-> [Repository] <-> [Database]
```

Key technical considerations:
- Transactional boundaries are defined at the service layer
- DTOs are used for data transfer between layers
- Validation is performed at both controller and service layers