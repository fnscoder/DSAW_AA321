# Java boilerplate

A Java based conteiner application with Maven, Docker, MySQL and some samples.

## How to use

1. Install Docker in your host machine.
1. Run `docker-compose pull` to download container images
1. Execute `docker-compose run mvn` to build Java classes and download .jar files. 
1. Launch with `docker-compose up` and open your browser
1. The default address is `http://localhost:8080` but this can be changed in `docker-compose.yml`

## Important notes

Use Maven [Standard Directory Layout](https://maven.apache.org/guides/introduction/introduction-to-the-standard-directory-layout.html) to organize the app.

`web.xml` file is located on `src/main/webapp/WEB-INF`.

This project is in package `br.ufscar`.

Maven container entrypoint is set to `mvn`, making easy to run maven commands (e.g: `docker-compose run mvn clean install`). Default command is `install`, so running `docker-compose run mvn` is the same as `docker-compose run maven mvn install` in case if entrypoint is not set.

A database dump file could be placed on `database\data` folder. When MySQL container is created, all scrips in that folder will be executed.
