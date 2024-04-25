# Backend for Rentevo App developed in Java Spring Boot and Postgres database

## Testing scenarios
To create a comprehensive study 4 most important testing scenarios were created:
* creating user account,
* adding products to the cart,
* paying for the order,
* adding and updating product into the store.

## Database Schema
![image](https://github.com/Dannelysbeth/Rentevo-backend-postgres/assets/72508414/fc216d2b-4085-4922-8e4e-3e07daff944b)

## Endpoints description



The `POST /product/add` endpoint is used to add a new product.

### Request Body

- The request body should be in JSON format and should include the following parameters:
    - `productCode` (string): The code of the product.
    - `name` (string): The name of the product.
    - `description` (string): The description of the product.
    - `price` (number): The price of the product.
    - `category` (string): The category of the product.
    - `features` (array): An array of objects containing `parameter` (string) and `value` (string) for the features of the product.
    - `quantityInStock` (number): The quantity of the product in stock.
    - `SKU` (string): The SKU (stock keeping unit) of the product.

### Response

The response to this request has a status code of 200 and a content type of `text/plain`. The response body contains the message "Products were imported successfully".

### JSON Schema for Response

``` json
{  "type": "object",
  "properties": {
    "status": {
      "type": "number",
      "description": "The status code of the response."
    },
    "message": {
      "type": "string",
      "description": "The message indicating the result of the request."
    }
  }
}

 ```
