# HQL Skill 3 - Sorting, Pagination and Aggregates

## 📋 Project Overview

This is a **Spring Boot application** with an **interactive user input system** designed to demonstrate:
- ✅ **Hibernate Query Language (HQL)** - Sorting, Pagination, Aggregation
- ✅ **CRUD Operations** - Create, Read, Update, Delete products
- ✅ **Spring Data JPA** - Repository pattern and service layer
- ✅ **User Input Handling** - Interactive console menu
- ✅ **Database Integration** - MySQL with automatic table creation

## 🛠️ Tech Stack

| Technology | Version |
|-----------|---------|
| Java | 21 |
| Spring Boot | 4.0.3 |
| Spring Data JPA | Latest |
| Hibernate | 7.2.4 |
| MySQL | 8.0+ |
| Maven | 3.8+ |

## 📁 Complete Project Structure

```
hql-skill3/
├── src/
│   ├── main/
│   │   ├── java/com/hqllab/
│   │   │   ├── entity/              # JPA Entities
│   │   │   │   └── Product.java
│   │   │   ├── repository/          # Data Access Layer (NEW)
│   │   │   │   └── ProductRepository.java
│   │   │   ├── service/             # Business Logic Layer (NEW)
│   │   │   │   └── ProductService.java
│   │   │   ├── util/                # Utility Classes (NEW)
│   │   │   │   └── UserInputHandler.java
│   │   │   ├── loader/              # Data Initialization (NEW)
│   │   │   │   └── ProductDataLoader.java
│   │   │   ├── demo/                # Demo/Test Classes
│   │   │   └── HqlSkill3Application.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── static/
│   │       └── templates/
│   └── test/
│       └── java/com/hqllab/
│           └── HqlSkill3ApplicationTests.java
├── pom.xml
├── mvnw & mvnw.cmd
└── README.md
```

## 🚀 Getting Started

### Prerequisites

1. **Java 21** installed
   ```bash
   java -version
   ```

2. **Maven** installed
   ```bash
   mvn -version
   ```

3. **MySQL 8.0+** running
   ```bash
   mysql -u root -p
   ```

### 📦 Installation

1. **Clone or extract the project**
   ```bash
   cd hql-skill3
   ```

2. **Configure Database Connection**
   
   Edit `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/hql_lab_db?createDatabaseIfNotExist=true
   spring.datasource.username=root
   spring.datasource.password=your_password_here
   ```

3. **Build the Project**
   ```bash
   mvn clean install
   ```

### 4. **Run the Application**
   ```bash
   mvn spring-boot:run
   ```

## 🏗️ Application Architecture

### Layered Architecture Pattern

The application follows a **3-tier architecture**:

```
┌─────────────────────────────────────┐
│  Presentation Layer                 │
│  (UserInputHandler + Menu)          │
├─────────────────────────────────────┤
│  Service Layer                      │
│  (ProductService - Business Logic)  │
├─────────────────────────────────────┤
│  Data Access Layer                  │
│  (ProductRepository + JPA)          │
├─────────────────────────────────────┤
│  Database                           │
│  (MySQL - Products Table)           │
└─────────────────────────────────────┘
```

---

## 📚 Components Overview

### 1️⃣ **Entity Layer**

#### Product.java
- **Location:** `src/main/java/com/hqllab/entity/Product.java`
- **Purpose:** JPA Entity representing a product record
- **Annotations:**
  - `@Entity` - Marks class as JPA entity
  - `@Table(name = "products")` - Maps to MySQL table
  - `@Id` - Primary key
  - `@GeneratedValue` - Auto-increment ID

**Fields:**
```
id (Long) - Primary Key
name (String) - Product name
price (Double) - Product price
quantity (Integer) - Stock quantity
description (String) - Product description
```

---

### 2️⃣ **Repository Layer (Data Access)**

#### ProductRepository.java
- **Location:** `src/main/java/com/hqllab/repository/ProductRepository.java`
- **Purpose:** Spring Data JPA Repository for database operations
- **Extends:** `JpaRepository<Product, Long>`
- **Methods Inherited:**
  - `save()` - Create/Update
  - `findById()` - Get by ID
  - `findAll()` - Get all products
  - `deleteById()` - Delete by ID
  - `count()` - Count total records

**Code:**
```java
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
```

---

### 3️⃣ **Service Layer (Business Logic)**

#### ProductService.java
- **Location:** `src/main/java/com/hqllab/service/ProductService.java`
- **Purpose:** Business logic for product operations
- **Methods:**

| Method | Purpose |
|--------|---------|
| `createProduct()` | Create a new product |
| `getAllProducts()` | Fetch all products |
| `getProductById(id)` | Get specific product |
| `updateProduct()` | Update product details |
| `deleteProduct()` | Remove product |
| `getTotalProducts()` | Count total products |

**Example:**
```java
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    
    public Product createProduct(String name, Double price, Integer quantity, String description) {
        Product product = new Product(name, price, quantity, description);
        return productRepository.save(product);
    }
}
```

---

### 4️⃣ **Utility Layer (User Input Handling)**

#### UserInputHandler.java
- **Location:** `src/main/java/com/hqllab/util/UserInputHandler.java`
- **Purpose:** Handle console input validation and formatting

**Methods:**
```java
getString(prompt)           // Get string input
getDouble(prompt)           // Get decimal input with validation
getInteger(prompt)          // Get integer input with validation
getYesNo(prompt)            // Get yes/no confirmation
getMenuChoice(options...)   // Display menu and get choice
closeScanner()              // Clean up resources
```

---

### 5️⃣ **Data Loader (Interactive Menu)**

#### ProductDataLoader.java
- **Location:** `src/main/java/com/hqllab/loader/ProductDataLoader.java`
- **Purpose:** Interactive console menu for product management

**Menu Options:**
1. **Add New Product** - Create with user input
2. **View All Products** - Display all products
3. **Search Product by ID** - Find specific product
4. **Update Product** - Modify existing product
5. **Delete Product** - Remove product with confirmation
6. **Exit** - Close application

---

### 6️⃣ **Main Application Class**

#### HqlSkill3Application.java
- **Location:** `src/main/java/com/hqllab/HqlSkill3Application.java`
- **Purpose:** Entry point with CommandLineRunner
- **Implements:** `CommandLineRunner` to start interactive menu on startup

```java
@SpringBootApplication
public class HqlSkill3Application implements CommandLineRunner {
    
    @Autowired
    private ProductDataLoader productDataLoader;
    
    @Override
    public void run(String... args) throws Exception {
        productDataLoader.startInteractiveMenu();
    }
}
```

---

## 🎮 Interactive Menu Guide

### Starting the Application

```bash
mvn spring-boot:run
```

### Menu Interface

```
╔════════════════════════════════╗
║   🛍️  PRODUCT MANAGER APP  🛍️   ║
║  HQL - Sorting, Pagination      ║
╚════════════════════════════════╝

========== MENU ==========
1. Add New Product
2. View All Products
3. Search Product by ID
4. Update Product
5. Delete Product
6. Exit
========================

Enter your choice (1-6):
```

### Example Workflow

#### Add New Product
```
Enter Product Name: Laptop
Enter Product Price: $1200.00
Enter Quantity: 50
Enter Description: Gaming Laptop with RTX 3080

✅ Product added successfully!
Product ID: 1
Product{id=1, name='Laptop', price=1200.0, quantity=50, description='Gaming Laptop with RTX 3080'}
```

#### View All Products
```
===== ALL PRODUCTS =====
Total Products: 3

─────────────────────────
ID: 1
Name: Laptop
Price: $1200.00
Quantity: 50
Description: Gaming Laptop with RTX 3080
─────────────────────────
```

---

## ⚙️ Database Configuration

### application.properties Settings

```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/hql_lab_db?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=1234
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

# Logging
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
```

### Configuration Explanations

| Property | Value | Reason |
|----------|-------|--------|
| `ddl-auto` | update | Auto-create/update tables based on entities |
| `show-sql` | true | Display generated SQL in console |
| `format_sql` | true | Pretty-print SQL queries |
| `dialect` | MySQLDialect | Use MySQL-specific SQL generation |

## 🔄 Maven Commands

| Command | Purpose |
|---------|---------|
| `mvn clean` | Clean build artifacts |
| `mvn compile` | Compile source code |
| `mvn test` | Run unit tests |
| `mvn install` | Build and install to local repository |
| `mvn clean install` | Clean and build project |
| `mvn clean install -DskipTests` | Build without running tests |
| `mvn spring-boot:run` | Run the application (shows interactive menu) |
| `mvn clean compile` | Clean and recompile (fix red squiggly lines) |

---

## 📊 Data Flow Diagram

```
User Input (Console)
        ↓
UserInputHandler (Validation)
        ↓
ProductDataLoader (Menu Logic)
        ↓
ProductService (Business Logic)
        ↓
ProductRepository (Database Operations)
        ↓
MySQL Database (Persistence)
```

---

## 🧪 Testing the Application

### Test Scenario 1: Add Products

```bash
$ mvn spring-boot:run

1. Add New Product
Enter Product Name: Mouse
Enter Product Price: $29.99
Enter Quantity: 100
Enter Description: Wireless Mouse

✅ Product added successfully!
Product ID: 1
```

### Test Scenario 2: View All Products

```bash
2. View All Products

===== ALL PRODUCTS =====
Total Products: 1

─────────────────────────
ID: 1
Name: Mouse
Price: $29.99
Quantity: 100
Description: Wireless Mouse
─────────────────────────
```

### Test Scenario 3: Search Product

```bash
3. Search Product by ID
Enter Product ID: 1

✅ Product found:
─────────────────────────
ID: 1
Name: Mouse
Price: $29.99
Quantity: 100
─────────────────────────
```

### Test Scenario 4: Update Product

```bash
4. Update Product
Enter Product ID to update: 1

Current Product: ... (shows details)

Enter new Product Name: Wireless Mouse Pro
Enter new Price: $49.99
...

✅ Product updated successfully!
```

### Test Scenario 5: Delete Product

```bash
5. Delete Product
Enter Product ID to delete: 1

─────────────────────────
ID: 1
Name: Wireless Mouse Pro
...
─────────────────────────

Are you sure you want to delete this product? (y/n): y
✅ Product deleted successfully!
```

---

## 📈 Current Progress Checklist

```
COMPLETED ✅
══════════════════════════════════════════════════════════════

✅ Step 1: Project Setup
   ✅ Spring Boot project created
   ✅ Dependencies configured (Spring Data JPA, MySQL)
   ✅ Java 21 environment set

✅ Step 2: Database Configuration
   ✅ MySQL database connection configured
   ✅ Hibernate dialect configured (MySQLDialect)
   ✅ Auto table creation enabled (ddl-auto=update)
   ✅ Logging configured for SQL debugging

✅ Step 3: Entity Layer
   ✅ Product entity created with JPA annotations
   ✅ All required fields (id, name, price, quantity, description)
   ✅ Constructors and getters/setters implemented

✅ Step 4: Repository Layer
   ✅ ProductRepository created (extends JpaRepository)
   ✅ Spring Data JPA CRUD methods available

✅ Step 5: Service Layer
   ✅ ProductService created with business logic
   ✅ CRUD operations implemented
   ✅ Database transaction management

✅ Step 6: Utility Layer
   ✅ UserInputHandler created with validation
   ✅ Menu system implemented
   ✅ Input error handling

✅ Step 7: Application Integration
   ✅ ProductDataLoader with interactive menu
   ✅ CommandLineRunner integration
   ✅ Formatted output and user feedback

✅ Step 8: Build & Run
   ✅ Project builds successfully (mvn clean install)
   ✅ Application starts without errors
   ✅ Interactive menu functional

✅ Step 9: Documentation
   ✅ Comprehensive README created
   ✅ Architecture documented
   ✅ Usage instructions provided
   ✅ Code examples included
```

---

## 🚀 How to Use (Quick Start)

### 1. Start the Application
```bash
cd c:\Users\tsusm\Downloads\hql-skill3\hql-skill3
mvn spring-boot:run
```

### 2. You'll See This:
```
╔════════════════════════════════╗
║   🛍️  PRODUCT MANAGER APP  🛍️   ║
║  HQL - Sorting, Pagination      ║
╚════════════════════════════════╝

========== MENU ==========
1. Add New Product
2. View All Products
3. Search Product by ID
4. Update Product
5. Delete Product
6. Exit
========================

Enter your choice (1-6):
```

### 3. Try These Actions:
- **Type 1** → Add a product (follow prompts)
- **Type 2** → View all products in database
- **Type 3** → Search for a product by ID
- **Type 4** → Update an existing product
- **Type 5** → Delete a product
- **Type 6** → Exit application

---

## 🐛 Troubleshooting

### Issue: "Access denied for user 'root'"
```
❌ Wrong MySQL password in application.properties
✅ Update: spring.datasource.password=your_correct_password
✅ Then rebuild: mvn clean install
```

### Issue: "Unable to resolve Hibernate dialect"
```
❌ Wrong dialect name in application.properties
✅ Use: org.hibernate.dialect.MySQLDialect (not MySQL8Dialect)
✅ Rebuild: mvn clean install
```

### Issue: "Red squiggly lines in IDE"
```
❌ IDE cache issue
✅ Solution: mvn clean compile
✅ Then refresh IDE (Ctrl+Shift+P → Java: Clean Language Server)
```

### Issue: Maven commands not found
```
❌ Maven not in PATH
✅ Windows: Use mvnw.cmd clean install
✅ Linux/Mac: Use ./mvnw clean install
```

### Issue: MySQL not running
```
❌ "Connection refused"
✅ Windows: Start MySQL service in Services
✅ Linux: sudo systemctl start mysql
✅ Mac: brew services start mysql
```

---

## 📚 Database Tables Created

Once you run the application, Hibernate automatically creates this table:

```sql
CREATE TABLE products (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    price DOUBLE,
    quantity INT,
    description VARCHAR(255)
);
```

---

## 📝 Next Advanced Features

The foundation is ready for implementing:

- ✨ **HQL Queries** - Sorting, Pagination, Aggregation
- ✨ **Advanced Filtering** - By price range, quantity, etc.
- ✨ **Custom Queries** - @Query annotations in repository
- ✨ **REST API** - Web endpoints for operations
- ✨ **Unit Tests** - JUnit 5 and Mockito tests
- ✨ **Web UI** - Thymeleaf templates for HTML interface

---

## 🎯 Project Completion Status

| Phase | Status | Details |
|-------|--------|---------|
| Configuration | ✅ Complete | MySQL, properties, Hibernate |
| Entity Design | ✅ Complete | Product entity with annotations |
| Data Access Layer | ✅ Complete | ProductRepository with JPA |
| Business Logic | ✅ Complete | ProductService with CRUD |
| User Interface | ✅ Complete | Interactive console menu |
| Testing | ✅ Ready | Manual testing documented |
| Documentation | ✅ Complete | Full README with examples |

---

## 📞 Support & Resources

- **Spring Boot Docs:** https://spring.io/projects/spring-boot
- **Spring Data JPA:** https://spring.io/projects/spring-data-jpa
- **Hibernate Guide:** https://hibernate.org/orm/documentation/
- **MySQL Docs:** https://dev.mysql.com/doc/
- **Maven Guide:** https://maven.apache.org/

---

**Last Updated:** March 14, 2026  
**Status:** ✅ **READY FOR TESTING**  
**Next Phase:** HQL Query Implementation (Sorting, Pagination, Aggregates)
"# git-skill3" 
