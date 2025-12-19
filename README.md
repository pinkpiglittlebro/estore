# E-Store – Docker Deployment Guide

This project uses Docker Compose to run:
* **Tomcat 9** (serving the estore.war)
* **MySQL 8** (preloaded with sample product data)

## Requirements

* Docker
* Docker Compose
* Git

## How to Run

1. **Ensure Docker is running:**

   Make sure Docker Desktop is running on your machine before proceeding.

2. **Clone the project:**

   Open Git Bash, go to the folder you want to save the project, then

   ```bash
   git clone https://github.com/pinkpiglittlebro/estore
   cd estore
   ```

3. **Start the containers:**

   ```bash
   docker-compose up --build
   ```

4. **Wait until the terminal output shows:**

   ```
   MySQL: ready for connections
   Tomcat: Server startup in ... ms
   ```

5. **Open the application in browser:**

   http://localhost:8081/estore

   > **Note:** Port 8081 is used because port 8080 was already in use on my machine during testing, so the container's Tomcat port (8080) is mapped to host port 8081.

## Database Initialization Notice

This project uses a MySQL container with an initialization SQL script.

On first run, if catalog does not appear, please run in the project folder:

```bash
docker-compose down -v
docker-compose up --build
```

## Stop Containers

```bash
docker-compose down -v
```

This removes containers and resets the MySQL volume.

## Manual Deployment (Without Docker)

### Requirements
* Apache Tomcat 9 or higher
* MySQL 8 or higher
* JDK 8 or higher
* Maven
* Git

### Setup Instructions

#### 1. Clone the Project
```bash
git clone https://github.com/pinkpiglittlebro/estore
cd estore
```

#### 2. Set Up MySQL Database

1. **Install MySQL**

2. **Start MySQL service and create database:**
```bash
   mysql -u root -p
```
   
   Run these commands:
```sql
   CREATE DATABASE estore;
   EXIT;
```

3. **Initialize database with sample data:**
```bash
   mysql -u root -p estore < estore_mysql_full.sql
```
   Enter your root password.

#### 3. Configure Database Connection

Update the database connection settings in `src/main/resources/database.properties`:
```properties
db.url=jdbc:mysql://localhost:3306/estore?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
db.user=root
db.password=root
```

> **Note:** Change `db.password` to match your MySQL root password.

#### 4. Build the Project
```bash
mvn clean package
```

The WAR file will be generated in `target/estore.war`

#### 5. Deploy to Tomcat

1. Copy the WAR file to Tomcat's webapps directory:
```bash
   cp target/estore.war <TOMCAT_HOME>/webapps/
```

2. Start Tomcat:
   - Windows: `<TOMCAT_HOME>\bin\startup.bat`
   - Mac/Linux: `<TOMCAT_HOME>/bin/startup.sh`

#### 6. Access the Application

Open your browser and navigate to:
```
http://localhost:8080/estore
```

> **Note:** If port 8080 is already in use on your machine, you'll need to change Tomcat's port in `<TOMCAT_HOME>/conf/server.xml`. Look for `<Connector port="8080"` and change it to `8081` or another available port, then access the application at `http://localhost:8081/estore`.

### Troubleshooting

- **Database connection failed:** Verify MySQL is running and credentials in `database.properties` are correct
- **Port 8080 in use:** Change Tomcat's port in `<TOMCAT_HOME>/conf/server.xml`
- **Catalog not appearing:** Verify SQL script executed successfully:
```bash
  mysql -u root -p estore -e "SHOW TABLES;"
```
