# UaKinoBot

UaKinoBot is a Java-based automation tool for downloading movies and series from the UaKino website(https://uakino.club/). It provides a set of classes and utilities to automate the process.

## Classes

### `SeleniumClient`

- **Description**: This abstract class serves as the base for Selenium-based web automation clients. It includes common methods and configurations for working with Selenium WebDriver.

### `FolderSizeCalculator`

- **Description**: This utility class provides methods for calculating and printing the size of a folder in a human-readable format.

### `Main`

- **Description**: The `Main` class is the entry point of the UaKinoBot application. It demonstrates how to use the `UaKinoBot` to download movies and series from UaKino.

### `UaKinoBot`

- **Description**: The `UaKinoBot` class is the core of the UaKinoBot application. It automates the process of downloading movies and series from UaKino by interacting with the website and managing movie information.

### `Movie`

- **Description**: The `Movie` class represents a movie or series with a name, main URL, and additional URL addresses. It is used to store information about movies and series.

## Getting Started

To use the UaKinoBot, you can follow these steps:

1. Clone the repository to your local machine.
2. Set up the necessary dependencies and configuration (e.g., WebDriverManager for Selenium).
3. Create a list of movie or series URLs to process.
4. Instantiate the `UaKinoBot` class with the list of URLs.
5. Use the `UaKinoBot` methods to start the program, download videos, and measure execution time.

**Note**: This project requires **Java 8** to run stably.

Example usage can be found in the `Main` class within the repository.

## Contributing

Contributions to this project are welcome. Feel free to open issues or submit pull requests if you have suggestions, improvements, or bug fixes.
