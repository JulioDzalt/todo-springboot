
# Image base maven
    Forwardeo del puerto entre el anfitrion y el contenedor
    Montando el codigo como volumen
    Ejecutamos el bash para que el contenedor permanezca vivo


docker run -p 8080:8080 -it -d --rm --name todo_crud -v C:\Users\105506781\Desktop\EjeccicioCrud\todo_crud:/usr/src/mymaven -w /usr/src/mymaven maven:3.8.4-jdk-11 /bin/bash
> be71ed030a4fb2b5e83a683cca2a4502b45a82017f2776dd554d9a15ecf7a3a6

# Entrar al contenedor y ejecutar el codigo de spring

docker exec -it be71ed030a4fb2b5e83a683cca2a4502b45a82017f2776dd554d9a15ecf7a3a6 /bin/bash
docker exec -it todo_crud /bin/bash
mvn spring-boot:run



# Docker compose
docker-compose up -d --service-ports

## MS
docker-compose run --service-ports todo_crud
mvn spring-boot:run


## DB
docker-compose run --service-ports bd_todos
/etc/init.d/mysql start
mysql -u root -p
root

create database todos;

use todos;

drop table todos;

CREATE TABLE IF NOT EXISTS todos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT,
    datecreation VARCHAR(255),
    status enum('DONE', 'IN_PROGRESS', 'TO_DO', 'CANCEL')
    
);

select * from todos;

INSERT INTO todos (title, content, datecreation, status) VALUES  ("A","aaaaa","2008-7-04","TO_DO");
INSERT INTO todos (title, content, datecreation, status) VALUES  ("b","bbbbb","2020-7-04","TO_DO");


CREATE USER 'julio'@'localhost' IDENTIFIED BY 'julio';
GRANT ALL PRIVILEGES ON * . * TO 'julio'@'localhost';
GRANT ALL PRIVILEGES ON todos.* TO 'julio'@'localhost' WITH GRANT OPTION;
FLUSH PRIVILEGES;


CREATE USER 'username'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON *.* TO 'username'@'localhost' WITH GRANT OPTION;
CREATE USER 'username'@'%' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON *.* TO 'username'@'%' WITH GRANT OPTION;
FLUSH PRIVILEGES;


# SonarQube

docker run -p 9000:9000 -ti sonarqube:9.4.0-community

mvn clean verify sonar:sonar -Dsonar.projectKey=Julio -Dsonar.host.url=http://172.17.0.2:9000 -Dsonar.login=5b1448a93eacd09d2e88ae047e08a8cc0be312d2 -Dsonar.coverage.jacoco.xmlReportPaths=tests/target/site/jacoco-aggregate/jacoco.xml,../tests/target/site/jacoco-aggregate/jacoco.xml