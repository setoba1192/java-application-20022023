
## Instalación

Clonar repositorio

```bash
  git clone https://github.com/setoba1192/java-application-20022023.git
```

Generar jar con maven desde la consola de comandos cmd ubicado en la raíz del proyecto

```bash
  mvn package
```

Ejecutar aplicación - por defecto ejecuta base de datos en memoria

```bash
  java -jar target/user-registration-0.0.1-SNAPSHOT.jar
```

Ejecutar aplicación en modo local (Conectando a base de datos local)

```bash
  java -jar target/user-registration-0.0.1-SNAPSHOT.jar --spring.profiles.active=local
```
## Environment Variables - opcional

Si desea parametrizar las siguientes variables de entorno para que sea configurable

`SECRET_KEY` -> llave de JWT

`USUARIO_PASSWORD_REGEXP` -> Regexp para validación de contraseñas


## Swagger

http://localhost:8001/swagger-ui.html

