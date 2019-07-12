# Simple Todos app

The app is just for keeping a list of todos and mark them done or delete them, nothing more nothing less.

It's mainly for demonstration of 3-tier architecture, and unit testing on the backend. Its deployed using [docker swarm](https://docs.docker.com/engine/swarm/) and served on [Caddy Server](https://caddyserver.com/) as the reverse proxy and everything is deployed on a ubuntu server on [Digital Ocean](https://www.digitalocean.com/) the live app is here [https://todo.mcmipi.xyz/](https://todo.mcmipi.xyz/) its a public site, anyone can save todos and they will be seen by anyone who accesses the site.

## UI

Built using the [Angular](https://angular.io/) framework, and its just consuming the [RESTful API](https://restfulapi.net/).

## Backend [![CircleCI](https://circleci.com/gh/chrismipi/todos/tree/master.svg?style=svg)](https://circleci.com/gh/chrismipi/todos/tree/master)

Build using [SpringBoot](https://spring.io/projects/spring-boot) framework, exposing a [RESTful API](https://restfulapi.net/) and savingTodos in the database.

## Database

Using a relational database MySQL, could have used PostgreSQL or MariaDB or a no-SQL database.