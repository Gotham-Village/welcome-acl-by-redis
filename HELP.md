# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.2.3/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.2.3/gradle-plugin/reference/html/#build-image)
* [Coroutines section of the Spring Framework Documentation](https://docs.spring.io/spring/docs/6.1.4/spring-framework-reference/languages.html#coroutines)
* [Distributed Tracing Reference Guide](https://micrometer.io/docs/tracing)
* [Getting Started with Distributed Tracing](https://docs.spring.io/spring-boot/docs/3.2.3/reference/html/actuator.html#actuator.micrometer-tracing.getting-started)
* [Spring Boot Testcontainers support](https://docs.spring.io/spring-boot/docs/3.2.3/reference/html/features.html#features.testing.testcontainers)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/3.2.3/reference/htmlsingle/index.html#using.devtools)
* [Spring Configuration Processor](https://docs.spring.io/spring-boot/docs/3.2.3/reference/htmlsingle/index.html#appendix.configuration-metadata.annotation-processor)
* [Docker Compose Support](https://docs.spring.io/spring-boot/docs/3.2.3/reference/htmlsingle/index.html#features.docker-compose)
* [Rest Repositories](https://docs.spring.io/spring-boot/docs/3.2.3/reference/htmlsingle/index.html#howto.data-access.exposing-spring-data-repositories-as-rest)
* [Spring HATEOAS](https://docs.spring.io/spring-boot/docs/3.2.3/reference/htmlsingle/index.html#web.spring-hateoas)
* [Spring Data Reactive Redis](https://docs.spring.io/spring-boot/docs/3.2.3/reference/htmlsingle/index.html#data.nosql.redis)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/3.2.3/reference/htmlsingle/index.html#actuator)
* [Sentry](https://docs.sentry.io/platforms/java/)
* [Prometheus](https://docs.spring.io/spring-boot/docs/3.2.3/reference/htmlsingle/index.html#actuator.metrics.export.prometheus)
* [Testcontainers](https://java.testcontainers.org/)

### Guides
The following guides illustrate how to use some features concretely:

* [Accessing JPA Data with REST](https://spring.io/guides/gs/accessing-data-rest/)
* [Accessing Neo4j Data with REST](https://spring.io/guides/gs/accessing-neo4j-data-rest/)
* [Accessing MongoDB Data with REST](https://spring.io/guides/gs/accessing-mongodb-data-rest/)
* [Building a Hypermedia-Driven RESTful Web Service](https://spring.io/guides/gs/rest-hateoas/)
* [Messaging with Redis](https://spring.io/guides/gs/messaging-redis/)
* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)
* [Getting Started with Sentry](https://docs.sentry.io/platforms/java/guides/spring-boot/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

### Docker Compose support
This project contains a Docker Compose file named `compose.yaml`.
In this file, the following services have been defined:

* redis: [`redis:latest`](https://hub.docker.com/_/redis)
* zipkin: [`openzipkin/zipkin:latest`](https://hub.docker.com/r/openzipkin/zipkin/)

Please review the tags of the used images and set them to the same as you're running in production.

### Testcontainers support

This project uses [Testcontainers at development time](https://docs.spring.io/spring-boot/docs/3.2.3/reference/html/features.html#features.testing.testcontainers.at-development-time).

Testcontainers has been configured to use the following Docker images:

* [`redis:latest`](https://hub.docker.com/_/redis)
* [`openzipkin/zipkin:latest`](https://hub.docker.com/r/openzipkin/zipkin/)

Please review the tags of the used images and set them to the same as you're running in production.

## Additional Resources

* [On Redis Insight container](https://redis.io/docs/install/install-redisinsight/install-on-docker/ "Run RedisInsight Docker image")
* [Redis OM Spring](https://redis.io/docs/connect/clients/om-clients/stack-spring/ "Redis OM Spring")
* [Spring and Redis](https://developer.redis.com/develop/java/spring/ "Spring and Redis")
* GitHub Spring Boot [Reactive Redis Guide](https://github.com/spring-guides/gs-spring-data-reactive-redis "gs-spring-data-reactive-redis")
  * [Redis Spring Guide](https://spring.io/guides/gs/spring-data-reactive-redis "Accessing Data Reactively with Redis") 

### Publications and Community

* [Spring and Redis Streams Intro](https://honstain.com/spring-boot-and-redis-streams-intro/ "Spring and Redis Streams Intro by Anthony Honstain") - [Anthony Honstain](https://www.linkedin.com/in/anthony-honstain/ "Anthony Honstain, LinkedIn") - [Dev Notes](https://honstain.com/ "Dev Notes by Anthony Honstain")
* 