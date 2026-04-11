# Shopping Cart Application — Database Localization

A multilingual JavaFX shopping cart application extended with **database‑driven localization** and **persistent cart storage**.  
This project builds on Week 2 by replacing ResourceBundle files with a **translation table** in MariaDB/MySQL and saving shopping cart calculations into the database.

---

#  Features

###  UI Localization from Database
All UI text is loaded from the `localization_strings` table using a translation‑table localization strategy.

###  Shopping Cart Persistence
Every calculation is saved into:

- `cart_records` (main cart summary)
- `cart_items` (individual items linked via foreign key)

###  Multi‑Language Support
Languages included:

- English (`en`)
- Finnish (`fi`)
- Swedish (`sv`)
- Japanese (`ja`)
- Arabic (`ar`)

### Arabic RTL Support
The UI automatically switches to **right‑to‑left** layout when Arabic is selected.

---

# Database Setup

## 1. Create the database

```sql
CREATE DATABASE IF NOT EXISTS shopping_cart_localization
CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE shopping_cart_localization;
```

## 2. Create required tables

```sql
CREATE TABLE IF NOT EXISTS cart_records (
    id INT AUTO_INCREMENT PRIMARY KEY,
    total_items INT NOT NULL,
    total_cost DOUBLE NOT NULL,
    language VARCHAR(10),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS cart_items (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cart_record_id INT,
    item_number INT NOT NULL,
    price DOUBLE NOT NULL,
    quantity INT NOT NULL,
    subtotal DOUBLE NOT NULL,
    FOREIGN KEY (cart_record_id) REFERENCES cart_records(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS localization_strings (
    id INT AUTO_INCREMENT PRIMARY KEY,
    `key` VARCHAR(100) NOT NULL,
    value VARCHAR(255) NOT NULL,
    language VARCHAR(10) NOT NULL
);

```
---
## 3. Insert localization strings

```sql
INSERT INTO localization_strings (`key`, value, language) VALUES
-- English
('title', 'Shopping Cart App', 'en'),
('select_language', 'Select the language:', 'en'),
('confirm_language', 'Confirm Language', 'en'),
('enter_num_items', 'Enter number of items:', 'en'),
('enter_items', 'Enter Items', 'en'),
('calculate_total', 'Calculate Total', 'en'),
('total', 'Total =', 'en'),
('price_label', 'Enter price for item:', 'en'),
('quantity_label', 'Enter quantity for item:', 'en'),

-- Finnish
('title', 'Ostoskärry Sovellus', 'fi'),
('select_language', 'Valitse kieli:', 'fi'),
('confirm_language', 'Vahvista kieli', 'fi'),
('enter_num_items', 'Syötä tuotteiden määrä:', 'fi'),
('enter_items', 'Syötä tuotteet', 'fi'),
('calculate_total', 'Laske kokonaishinta', 'fi'),
('total', 'Yhteensä =', 'fi'),
('price_label', 'Syötä tuotteen hinta:', 'fi'),
('quantity_label', 'Syötä määrä:', 'fi'),

-- Swedish
('title', 'Shoppingvagn App', 'sv'),
('select_language', 'Välj språk:', 'sv'),
('confirm_language', 'Bekräfta språk', 'sv'),
('enter_num_items', 'Ange antal varor:', 'sv'),
('enter_items', 'Ange varor', 'sv'),
('calculate_total', 'Beräkna total', 'sv'),
('total', 'Totalt =', 'sv'),
('price_label', 'Ange pris för varan:', 'sv'),
('quantity_label', 'Ange antal:', 'sv'),

-- Japanese
('title', 'ショッピングカートアプリ', 'ja'),
('select_language', '言語を選択:', 'ja'),
('confirm_language', '言語を確定', 'ja'),
('enter_num_items', '商品の数を入力:', 'ja'),
('enter_items', '商品を入力', 'ja'),
('calculate_total', '合計を計算', 'ja'),
('total', '合計 =', 'ja'),
('price_label', '商品の価格を入力:', 'ja'),
('quantity_label', '数量を入力:', 'ja'),

-- Arabic
('title', 'تطبيق سلة التسوق', 'ar'),
('select_language', 'اختر اللغة:', 'ar'),
('confirm_language', 'تأكيد اللغة', 'ar'),
('enter_num_items', 'أدخل عدد العناصر:', 'ar'),
('enter_items', 'أدخل العناصر:', 'ar'),
('calculate_total', 'احسب المجموع', 'ar'),
('total', 'المجموع =', 'ar'),
('price_label', 'أدخل سعر العنصر:', 'ar'),
('quantity_label', 'أدخل كمية العنصر:', 'ar');

```
---

## Project Structure

src/main/java/org/example/shoppingcart/

- MainApp.java
- MainController.java
- DatabaseConnection.java
- LocalizationService.java
- CartService.java
- CartItem.java
- CartCalculator.java
- ItemRow.java

---

## Core Logic Overview

### LocalizationService
Loads UI text dynamically from the database using:

SELECT `key`, value FROM localization_strings WHERE language = ?

---

### CartService
Responsible for persisting cart data:

- Inserts into `cart_records`
- Retrieves generated ID
- Inserts each item into `cart_items`

---

### MainController
Main application controller that:

- Loads localized UI text
- Handles user input and calculations
- Saves cart data to the database
- Supports Arabic digit normalization
- Supports RTL (Right-to-Left) layout

---

## 🐳 Docker Instructions

### Build Image
`docker build -t amirnoori1/shoppingcart-v3 . `

### Run Container
`docker run -t amirnoori1/shoppingcart-v3:latest`

### Important
Your database connection URL must be:

`jdbc:mariadb://host.docker.internal:3306/shopping_cart_localization`

---

## Testing & Coverage

### Run Tests
`mvn clean`
`mvn install`
`mvn test`

### Generate JaCoCo Report
`mvn jacoco:report`

### Coverage Report Location
`target/site/jacoco/index.html`

---

## 🛠 Jenkins Pipeline

The Jenkinsfile performs:

- Checkout source code
- Maven build
- Run unit tests
- Generate JaCoCo coverage
- Build Docker image
- Push Docker image to Docker Hub

### Requirements

Ensure Jenkins has:

- Maven installed
- Docker installed
- Docker Hub credentials configured

---

## Features

- Multi-language support (English, Arabic, etc.)
- RTL layout support
- Arabic digit normalization
- Dynamic UI generation
- Database persistence (MariaDB/MySQL)
- Dockerized deployment
- CI/CD with Jenkins

---

## Technologies Used

- Java
- JavaFX
- JDBC
- MariaDB / MySQL
- Maven
- Docker
- Jenkins
- JaCoCo

---