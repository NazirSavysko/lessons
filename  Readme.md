# Smart Smartphone Factory

---

## Project Description

This application simulates the operation of a smartphone manufacturing factory. The system allows creating production orders for various types of smartphones, tracking their status and completion.

---

## Functionality

- Creating manufacturing orders for smartphones of different categories and models
- Asynchronous order processing through a queue system
- Real-time order status tracking
- Recording order information to a file
- Modular architecture using design patterns (Factory, Builder, Observer)

---

## Architecture

The project is built using the following components:

- **entity**: Core application entities (smartphones, orders, director)
- **factory**: Factories for creating smartphones and builders
- **io**: Components for working with the file system
- **test**: Application tests

---

## Usage Instructions

1. Run the application (Application class)
2. Select option `'1'` to create an order
3. Specify the number of smartphones for the order
4. Choose a smartphone category from the provided list
5. Select a specific smartphone model
6. Enter the name and model of the smartphone
7. Order information will be displayed in the console and written to a file
8. To exit the application, select option `'2'` in the main menu

---

## Technical Details

- Programming Language: Java (SDK 24)
- Design Patterns: Singleton, Factory, Builder, Observer
- Multithreading for parallel order processing