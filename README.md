# Genesis API - Proyecto para el Desafío Técnico

Proyecto Spring Boot que expone el endpoint `GET /genesis/{capitulo}`. Si el capítulo solicitado no está en la base de datos, la app lo obtiene de la API pública y lo persiste.

## Contenido del ZIP
- Código fuente Java (Spring Boot)
- `docker-compose.yml` para levantar PostgreSQL local
- `Dockerfile` para crear la imagen del servicio
- `pom.xml` con dependencias

---

## Pasos para ejecutar localmente (recomendado para entrega)

1. Asegúrate de tener: Java 17, Maven, Docker y Docker Compose instalados.
2. Descomprime el ZIP y entra en la carpeta del proyecto:
   
   cd genesis-api
  
3. Levanta la base de datos PostgreSQL:
   
   docker-compose up -d
   
4. Compila y ejecuta la app (modo desarrollo):
   
   mvn spring-boot:run
  
5. Prueba el endpoint:
  
   curl http://localhost:8080/genesis/1
 

### Alternativa: ejecutar con Docker (build + run)

mvn -DskipTests package
docker build -t genesis-api:latest .
docker run -p 8080:8080 --env SPRING_DATASOURCE_URL=jdbc:postgresql://host.docker.internal:5432/genesisdb --env SPRING_DATASOURCE_USERNAME=postgres --env SPRING_DATASOURCE_PASSWORD=postgres genesis-api:latest

> Nota: en Linux la variable `host.docker.internal` puede no funcionar; si ejecutas todo con `docker-compose` coloca la app en la misma red que la db.

---

## Opciones de despliegue en la nube (rápidas)
Te doy 3 opciones populares — cada una admite despliegue desde GitHub o a partir de un Dockerfile:
- **Railway**: despliegue directo desde GitHub o usando `railway up`. Soporta Postgres como servicio gestionado. (Guía oficial de Railway). 
- **Render**: crea un "Web Service" apuntando a tu repo y configura los comandos de build/start; también puedes usar un Dockerfile.
- **Fly.io**: despliegue desde Dockerfile con `fly launch` y `fly deploy`.

Referencias rápidas (tutoriales y docs): Railway, Render, Fly.io, guía oficial de Spring Boot + Docker.
