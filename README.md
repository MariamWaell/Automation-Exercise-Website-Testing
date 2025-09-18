# Automatic Testing For Automation Exercise Website 
This project is a comprehensive automated testing suite for the [Automation Exercise](https://automationexercise.com/) website.
It contains 26 Test Cases.

## Features 
A. 26 fully automated test cases covering:
- User registration and login
- Product search and details
- Add to cart and checkout
- Order placement and payment
- Navigation and UI validation
- Contact us form
- Subscription and logout workflows

B. Modular Page Object Model (POM) architecture

C. Data-driven testing using JSON files

D. Reporting with Allure for clear visual test reports

## Tech Stack
- Language: Java
- Build Tool: Maven
- Testing Framework: TestNG
- Automation Tool: Selenium WebDriver
- Reporting: Allure
- Design Pattern: Page Object Model (POM)

## 📁 Project Structure
```tree
FinalProject/
├── pom.xml
├── src/
│   ├── main/java/
│   │   ├── Pages/          # Page Object classes
│   │   ├── Sections/       # Common UI sections
│   │   └── Frameworks/     # Core framework utilities
│   └── test/java/
│       ├── <Feature>/      # Test classes for each feature
│       └── TestingData/    # JSON test data
└── target/                  # Build and test outputs

