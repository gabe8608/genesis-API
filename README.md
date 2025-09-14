# Genesis API - Proyecto para el Desafío Técnico API GENESIS

Proyecto Spring Boot que expone el endpoint `GET /genesis/{capitulo}`. Si el capítulo solicitado no está en la base de datos, la app lo obtiene de la API pública y lo persiste, por lo tanto en este proyecto de prueba se exponen dos endpoints uno con la extension [(http://localhost:8080/genesis/1)] y el segundo es para detallar el aspecto de la interfaz gráfica. [(http://localhost:8080/index.html)]

## Contenido del ZIP

- Código fuente Java (Spring Boot)
- `docker-compose.yml` para levantar PostgreSQL local
- `Dockerfile` para crear la imagen del servicio
- `pom.xml` con dependencias

## Pasos para ejecutar localmente

1. Asegúrate de tener: Java 17, Maven, Docker y Docker Compose instalados.

2. Descomprime el ZIP y entra en la carpeta del proyecto:

   cd genesis-api

3. Levanta la base de datos PostgreSQL:

   docker-compose up -d

4. Compila y ejecuta la app (modo desarrollo):

   mvn spring-boot:run

5. Prueba el endpoint:

   [http://localhost:8080/genesis/1] y [http://localhost:8080/index.html]

### Alternativa: ejecutar con Docker (build + run)

## Opciones de despliegue en la nube (rápidas)

Existen tres posiblidades — cada una admite despliegue desde GitHub o a partir de un Dockerfile:

- **Railway**: despliegue directo desde GitHub o usando `railway up`. Soporta Postgres como servicio gestionado. (Guía oficial de Railway).
- **Render**: crea un "Web Service" apuntando a tu repo y configura los comandos de build/start; también puedes usar un Dockerfile.
- **Fly.io**: despliegue desde Dockerfile con `fly launch` y `fly deploy`.

Referencias rápidas (tutoriales y docs): Railway, Render, Fly.io, guía oficial de Spring Boot + Docker.
