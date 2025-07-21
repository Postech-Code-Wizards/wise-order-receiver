# 📲 wise-order-receiver

### 🌟 **Overview**

The **Order Receiver** is responsible for creating an order receiving channel, allowing the customer, item and payment method of the order to be informed.

## 🗂️ Project Structure

### **1. Core Modules**
- **`domain`**: Contains the core business logic and domain models.
- **`application`**: Includes service classes and facades that orchestrate business operations.
- **`gateway`**: Gateways for external systems like RabbitMQ and OpenFeign.
- **`infrastructure`**: Handles external integrations, configurations, and persistence layers.
    - **`controller`**: REST and GraphQL controllers for handling API requests.

    - **`configuration`**: Spring Boot configuration classes.
- **`tests`**: Unit and integration tests for all modules.

---

## 🛠️ Technologies Used

- **Java JDK 21**: Core programming language.
- **Spring Boot**: Framework for building microservices.
- **Maven**: Dependency management and build tool.
- **RabbitMQ**: Message broker for asynchronous communication.
- **JUnit 5**: Testing framework.
- **Instancio**: Library for generating test data.
- **Docker**: Application containerization, RabbitMQ.

---

## 🧪 Testing

The project includes comprehensive unit and integration tests for all modules. Key testing practices:
- **Instancio**: Used for generating dynamic test data.
- **Mocking**: Mockito is used to mock dependencies in unit tests.
- **Coverage**: Tests cover all methods and scenarios, ensuring reliability.

---

## 📂 Directory Structure

```plaintext
src/
├── main/
│   ├── java/
│   │   ├── com.order.wise/
│   │   │   ├── domain/
│   │   │   ├── application/
│   │   │   ├── gateway/
│   │   │   ├── infrastructure/
│   │   │   └── ...
│   └── resources/
│       ├── application.properties
│       └── ...
├── test/
│   ├── java/
│   │   ├── com.order.wise/
│   │   │   ├── domain/
│   │   │   ├── application/
│   │   │   ├── infrastructure/
│   │   │   └── ...
│   └── resources/
│       └── ...
  ```

---

## 🚀 How to Run
- **1. Clone the repository**  
  Clone the repository to your development environment:
    ```bash
    git clone https://github.com/Postech-Code-Wizards/wise-order-receiver
    ```
  In the terminal, navigate to the root of your project:
    ```bash
    cd wise-order-receiver
    ```

  Run the project:
    ```bash
    docker-compose up -d --build
    ```
  Access the API:
    - The application will be available at the URL: http://localhost:8084
