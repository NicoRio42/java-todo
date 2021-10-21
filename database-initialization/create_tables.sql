CREATE TABLE tasks
(
    task_id       SERIAL PRIMARY KEY,
    title         varchar(30) NOT NULL,
    deadline      date NOT NULL,
    done          boolean NOT NULL,
    description   varchar(100) NOT NULL
);