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
* [`redis/redisinsight:latest`](https://hub.docker.com/r/redis/redisinsight/)
* [`openzipkin/zipkin:latest`](https://hub.docker.com/r/openzipkin/zipkin/)

Please review the tags of the used images and set them to the same as you're running in production.

## Additional Resources

* [On Redis Insight container](https://redis.io/docs/install/install-redisinsight/install-on-docker/ "Run RedisInsight Docker image")
* [Redis OM Spring](https://redis.io/docs/connect/clients/om-clients/stack-spring/ "Redis OM Spring")
* [Spring and Redis](https://developer.redis.com/develop/java/spring/ "Spring and Redis")
* GitHub Spring Boot [Reactive Redis Guide](https://github.com/spring-guides/gs-spring-data-reactive-redis "gs-spring-data-reactive-redis")
  * [Redis Spring Guide](https://spring.io/guides/gs/spring-data-reactive-redis "Accessing Data Reactively with Redis") 
* [Use Redis](https://redis.io/docs/manual/ "Redis Documentation")
  * [Redis Server Configuration](https://raw.githubusercontent.com/redis/redis/7.2/redis.conf "Redis Configuration Explained")
  * [Redis keyspace notifications](https://redis.io/docs/manual/keyspace-notifications/ "Redis keyspace notifications")
  * [Redis Persistence](https://redis.io/docs/management/persistence/ "Redis Persistence")
  * [Redis Client Side Caching](https://redis.io/docs/manual/client-side-caching/ "Redis Client Side Caching")
  * [Redis Image](https://hub.docker.com/_/redis "Redis Image")

### Publications and Community

* [Spring Data Redis](https://hantsy.github.io/spring-reactive-sample/data/data-redis.html "Spring Data Redis by Hantsy Bai Revision")
* [Spring and Redis Streams Intro](https://honstain.com/spring-boot-and-redis-streams-intro/ "Spring and Redis Streams Intro by Anthony Honstain") - [Anthony Honstain](https://www.linkedin.com/in/anthony-honstain/ "Anthony Honstain, LinkedIn") - [Dev Notes](https://honstain.com/ "Dev Notes by Anthony Honstain")
* [Reactive Event Streaming with Redis Streams in Spring Boot 3](https://blog.stackademic.com/reactive-event-streaming-with-redis-streams-in-spring-boot-3-4bf7dabf7196 "by Ruchira Madhushan Rajapaksha")
* [Integrating Cucumber into a Spring Boot Project: A Step-by-Step Guide](https://medium.com/@francislainy.campos/integrating-cucumber-into-a-spring-boot-project-a-step-by-step-guide-f899c04bf81f "by Francis Campos")
   * [Grasshopper Cucumber Adapter](https://github.com/grasshopper7/extentreports-cucumber7-adapter "Grasshopper Cucumber Adapter")
   * [Cucumber-JVM 7 Report generation using ExtentReports Adapter plugin](https://ghchirp.site/3196/ "Cucumber-JVM 7 Report generation using ExtentReports Adapter plugin")
* [Qodana Yaml Configuration](https://www.jetbrains.com/help/qodana/qodana-yaml.html "Qodana Yaml Configuration")