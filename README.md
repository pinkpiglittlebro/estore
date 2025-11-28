# E-Store – Docker Deployment Guide

This project uses Docker Compose to run:
* **Tomcat 9** (serving the estore.war)
* **MySQL 8** (preloaded with sample product data)

##  Requirements

* Docker
* Docker Compose

##  How to Run

1. **Clone the project:**
```bash
git clone https://github.com/pinkpiglittlebro/estore
cd estore
```

2. **Start the containers:**
```bash
docker-compose up --build
```

3. **Wait until both containers show "ready for connections".**

4. **Open the application in browser:**

 http://localhost:8081/estore

> **Note:** Port 8081 is used because port 8080 was already in use on my machine during testing, so the container’s Tomcat port (8080) is mapped to host port 8081.

##  Stop Containers
```bash
docker-compose down -v
```

This removes containers and resets the MySQL volume.
