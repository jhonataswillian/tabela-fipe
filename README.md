# Tabela FIPE CLI - Vehicle Valuation System

## Overview

This project is a Command Line Interface (CLI) application developed in **Java 25** using the **Spring Boot 4.0.1** framework. Its primary purpose is to query and display average market values for vehicles (cars, motorcycles, and trucks) in the Brazilian market, utilizing the FIPE (Fundação Instituto de Pesquisas Econômicas) table data via a public REST API.

The application demonstrates the implementation of modern software architecture principles, including separation of concerns, consumption of external APIs using the new Spring `RestClient`, and data manipulation using Java Records and Streams.

## Technology Stack

*   **Language:** Java 25 (LTS)
*   **Framework:** Spring Boot 4.0.1
*   **Build Tool:** Maven
*   **HTTP Client:** Spring RestClient (Synchronous)
*   **JSON Processing:** Jackson Databind
*   **Architecture:** Layered Architecture (Service-Model-Principal)

## Key Features

*   **Vehicle Type Selection:** Support for Cars, Motorcycles, and Trucks.
*   **Dynamic API Consumption:** Real-time data retrieval from `https://parallelum.com.br/fipe/api/v1/`.
*   **Data Filtering:** Implementation of Java Streams to filter vehicle models by name.
*   **Comprehensive Valuation:** Retrieval of market values for all available manufacturing years of a specific vehicle model in a single execution.
*   **Robust Error Handling:** Management of API connection errors and data parsing exceptions.

## Architecture

The project follows a strict separation of concerns to ensure maintainability and scalability:

### 1. Model Layer (`com.jhonataswillian.tabela_fipe.model`)
Contains **Java Records** representing the data structure returned by the API. Records were chosen for their immutability and conciseness.
*   `Dados`: Generic representation of code/name pairs (Brands, Years).
*   `Modelos`: Representation of the complex object containing lists of models.
*   `Veiculo`: Detailed representation of the final vehicle data (Value, Brand, Model, Year, Fuel Type), utilizing `@JsonAlias` for flexible mapping.

### 2. Service Layer (`com.jhonataswillian.tabela_fipe.service`)
Handles infrastructure logic and external communication.
*   `ConsumoApi`: Encapsulates the `RestClient` logic to perform HTTP GET requests.
*   `ConverteDados`: Implements the `IConverteDados` interface, utilizing `ObjectMapper` to deserialize JSON strings into Java Objects (Generics).

### 3. Principal Layer (`com.jhonataswillian.tabela_fipe.principal`)
Acts as the controller/presenter for the CLI.
*   `Principal`: Manages the user interaction flow, input reading, and orchestration of service calls.

## Getting Started

### Prerequisites

*   Java Development Kit (JDK) 25 or higher.
*   Maven 3.8+ (or use the included `mvnw` wrapper).
*   Internet connection for API access.

### Installation

1.  Clone the repository:
    ```bash
    git clone https://github.com/jhonataswillian/tabela-fipe.git
    ```
2.  Navigate to the project directory:
    ```bash
    cd tabela-fipe
    ```
3.  Build the project and download dependencies:
    ```bash
    ./mvnw clean install
    ```

### Execution

Run the application using the Spring Boot Maven plugin:

```bash
./mvnw spring-boot:run
```

## Usage Example

Upon execution, follow the interactive prompts in the terminal:

1.  **Select Vehicle Type:** Enter `Carro`, `Moto`, or `Caminhão`.
2.  **Select Brand:** A list of brands will be displayed. Enter the corresponding code (e.g., `6` for Audi).
3.  **Filter Model:** Enter a text snippet to filter the models (e.g., `RS7`).
4.  **Select Model:** Enter the specific code of the desired model (e.g., `6883`).
5.  **View Results:** The application will output the valuation for every available year of that model.

**Output Sample:**

```text
Veiculo[valor=R$ 1.068.180,00, marca=Audi, modelo=RS7 Sportback 4.0 TFSI Quattro Tiptronic, anoModelo=2022, combustivel=Gasolina]
Veiculo[valor=R$ 809.240,00, marca=Audi, modelo=RS7 Sportback 4.0 TFSI Quattro Tiptronic, anoModelo=2021, combustivel=Gasolina]
Veiculo[valor=R$ 499.003,00, marca=Audi, modelo=RS7 Sportback 4.0 TFSI Quattro Tiptronic, anoModelo=2018, combustivel=Gasolina]
Veiculo[valor=R$ 437.227,00, marca=Audi, modelo=RS7 Sportback 4.0 TFSI Quattro Tiptronic, anoModelo=2017, combustivel=Gasolina]
Veiculo[valor=R$ 409.855,00, marca=Audi, modelo=RS7 Sportback 4.0 TFSI Quattro Tiptronic, anoModelo=2016, combustivel=Gasolina]
...
```

## License

This project is developed for educational purposes.

---

## 📬 Contact

Feel free to reach out for collaborations or questions about this project!

📧 **Email:** [jhonatas.willian.dev@gmail.com](mailto:jhonatas.willian.dev@gmail.com)

🔗 **LinkedIn:** [Jhonatas Willian](https://www.linkedin.com/in/jhonataswillian/)

💻 **GitHub:** [jhonataswillian](https://github.com/jhonataswillian)
