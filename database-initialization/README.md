# Postgres Course Setup

## Docker Postgres Setup

Pull Postgresql image:

    docker pull postgres

Create Docker container with Postgres database:

    docker create --name postgres-todo -e POSTGRES_PASSWORD=Welcome -p 5432:5432 postgres

Start container:

    docker start postgres-todo

Stop container:

    docker stop postgres-todo

Connection Info:

    JDBC URL: `jdbc:postgresql://localhost:5432/postgres`

    Username: `postgres`

    Password: `Welcome`

Note: This stores the data inside the container - when you delete the container, the data is deleted as well.

Connect to PSQL prompt from docker:  
   docker exec -it postgres-todo psql -U postgres
   
## Application Database Setup

Create the Database:

    psql> create database conference_app;

Setup the Tables:

    psql -d conference_app -f create_tables.sql

Install the Data:

    psql -d conference_app -f insert_data.sql
    
Note: The if you are using Docker, the last two steps can be done like so:

Setup the Tables:

    docker cp create_tables.sql postgres-todo:/create_tables.sql
    docker exec -it postgres-todo psql -d postgres -f create_tables.sql -U postgres

Install the Data:

    docker cp insert_data.sql postgres-todo:/insert_data.sql
    docker exec -it postgres-todo psql -d postgres -f insert_data.sql -U postgres
