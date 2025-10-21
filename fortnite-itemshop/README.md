# Fortnite Item Shop

This project is a simple REST API for a Fortnite item shop built using Spring Boot. It allows users to manage items available in the shop, including creating, updating, retrieving, and deleting items.

## Project Structure

```
fortnite-itemshop
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           └── itemshop
│   │   │               ├── FortniteItemShopApplication.java
│   │   │               ├── controller
│   │   │               │   └── ItemController.java
│   │   │               ├── service
│   │   │               │   └── ItemService.java
│   │   │               ├── repository
│   │   │               │   └── ItemRepository.java
│   │   │               ├── model
│   │   │               │   └── Item.java
│   │   │               └── dto
│   │   │                   └── ItemDto.java
│   │   └── resources
│   │       ├── application.yml
│   │       └── data.sql
│   └── test
│       └── java
│           └── com
│               └── example
│                   └── itemshop
│                       └── ItemControllerTest.java
├── pom.xml
├── .gitignore
└── README.md
```

## Setup Instructions

1. **Clone the repository:**
   ```
   git clone <repository-url>
   cd fortnite-itemshop
   ```

2. **Build the project:**
   ```
   mvn clean install
   ```

3. **Run the application:**
   ```
   mvn spring-boot:run
   ```

4. **Access the API:**
   The API will be available at `http://localhost:8080/api/items`.

## Usage

### Endpoints

- `GET /api/items` - Retrieve all items
- `GET /api/items/{id}` - Retrieve a specific item by ID
- `POST /api/items` - Create a new item
- `PUT /api/items/{id}` - Update an existing item
- `DELETE /api/items/{id}` - Delete an item

## Technologies Used

- Java
- Spring Boot
- Maven
- JPA/Hibernate
- H2 Database (for development)

## License

This project is licensed under the MIT License.