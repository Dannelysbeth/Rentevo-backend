# Rentevo Backend

The backend for the **Rentevo App** is developed using **Java Spring Boot** with a **Postgres database**. This repository serves as the core backend that supports essential e-commerce functionalities, including user account management, product handling, order processing, and transactions.

---

## Features

- **User Management**: Create and manage user accounts.
- **Product Management**: Adding, updating, and managing products in the store.
- **Cart and Orders**: Add products to the cart and complete purchases.
- **File-Based Product Import**: Import product details via file uploads.

---

## Project Structure

The project follows a typical Spring Boot application structure with the following components:

- **Controllers**: Handle incoming HTTP requests and map them to respective services.
- **Services**: Implement the business logic.
- **Repositories**: Manage database interactions using **Spring Data JPA**.
- **Entities**: Define the data model and schema mappings.

---

## Getting Started

Follow the instructions below to set up and run the project locally.

### Prerequisites

- **Java Development Kit (JDK)** Version 21-preview
- **Maven** Build Tool
- **PostgreSQL** (running in your local or remote setup)
- Spring Boot dependencies installed

### Installation Steps

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/your-repo/rentevo-backend.git
   cd rentevo-backend
   ```

2. **Set Up the Database**:
  - Ensure that a PostgreSQL database is running.
  - Update the database credentials in `application.properties`:
    - `spring.datasource.url`
    - `spring.datasource.username`
    - `spring.datasource.password`

3. **Build the Project**:
   ```bash
   mvn clean install
   ```

4. **Run the Application**:
   ```bash
   mvn spring-boot:run
   ```

5. The application will be available at: `http://localhost:9000`.

---

## API Endpoints

### **Product Management**

#### 1. Add Product
Add a new product using the `/product/add` endpoint.

- **Method**: `POST`
- **URL**: `/api/product/add`
- **Request Body (JSON)**:
  ```json
  {
    "productCode": "string",
    "name": "string",
    "description": "string",
    "price": 0.0,
    "category": "string",
    "features": [
      {
        "parameter": "string",
        "value": "string"
      }
    ],
    "quantityInStock": 0,
    "SKU": "string"
  }
  ```
- **Response**:
  - **Status Code**: 200
  - **Example JSON**:
    ```json
    {
      "status": 200,
      "message": "Product added successfully."
    }
    ```

#### 2. Import Products
Import products using the `/product/import` endpoint by uploading a file.

- **Method**: `POST`
- **URL**: `/api/product/import`
- **Request Body**: Form-data
  - `file`: The file containing product data.

- **Response**:
  ```json
  {
    "status": 200,
    "message": "Products were imported successfully"
  }
  ```

---

## Database Schema

Below is a visual representation of the database schema being used:

![Database Schema](https://github.com/Dannelysbeth/Rentevo-backend-postgres/assets/72508414/fc216d2b-4085-4922-8e4e-3e07daff944b)

---

## Testing Scenarios

The project focuses on the following critical scenarios to ensure proper functionality:

1. **Creating a User Account**
2. **Adding Products to the Cart**
3. **Paying for an Order**
4. **Adding and Updating Products in the Store**

---


## License

This project is licensed under the [MIT License](LICENSE).

---

Feel free to update the README file as the project evolves!
