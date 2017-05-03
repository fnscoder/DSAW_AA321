# DSAW AA 3-1

A Java based container application implementing Threads and Sockets.

## How to use

1. Install Docker in your host machine (if you wish, it will work with manual javac compiling, but to do it is up to you).
1. Run `docker-compose pull` to download container images
1. Execute `docker-compose run mvn` to build Java classes and download dependencies.
1. Run `docker-compose run mvn dependency:copy-dependencies` to copy mysql odbc driver to target.
1. Launch `docker-compose up server` to start the server.
1. To start the client, run `docker-compose run --rm client` in a new terminal inside project root.
1. Play with the application.

## Important notes

Use Maven [Standard Directory Layout](https://maven.apache.org/guides/introduction/introduction-to-the-standard-directory-layout.html) to organize the app.

This project is in package `br.ufscar`.

Maven container entrypoint is set to `mvn`, making easy to run maven commands (e.g: `docker-compose run mvn clean install`). Default command is `install`, so running `docker-compose run mvn` is the same as `docker-compose run maven mvn install` in case if entrypoint is not set.

A database dump file could be placed on `database\data` folder. When MySQL container is created, all scripts in that folder will be executed.
