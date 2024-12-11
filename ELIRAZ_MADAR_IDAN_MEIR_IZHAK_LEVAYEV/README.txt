```markdown
# Store-managment

## Introduction
Store-managment is a Java-based application designed to manage store operations efficiently. 

## Installation Instructions
To set up the Store-managment application locally, follow these steps:

1. **Clone the repository**:
   ```sh
   git clone https://github.com/Eliraz-Madar/Store-managment.git
   cd Store-managment
   ```

2. **Install dependencies**:
   Ensure you have Java installed. You may need additional dependencies listed in the `pom.xml` if using Maven.

3. **Build the project**:
   ```sh
   javac -d bin src/*.java
   ```

4. **Run the application**:
   ```sh
   java -cp bin Main
   ```

## Usage Examples
Here are some examples of how to use the Store-managment application:

1. **Add a new product**:
   ```sh
   java -cp bin Main add-product "Product Name" 100
   ```

2. **List all products**:
   ```sh
   java -cp bin Main list-products
   ```

3. **Update product price**:
   ```sh
   java -cp bin Main update-price "Product Name" 120
   ```

## Screenshots
![Application Screenshot](path/to/screenshot1.png)
![Application Screenshot](path/to/screenshot2.png)

## Contribution Guidelines
If you would like to contribute to this project, please fork the repository and submit a pull request. For major changes, please open an issue first to discuss what you would like to change.
