
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




