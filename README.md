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
