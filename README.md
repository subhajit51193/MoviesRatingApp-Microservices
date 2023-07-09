## Screenshots

![App Screenshot](https://raw.githubusercontent.com/subhajit51193/MoviesRatingApp-Microservices/main/MicroserviceServiceRegistry.jpg)

# MoviesRatingApp
it is an application where users can rate movies and give feedback. It has been created using microservices architecture where services communicate between each other to get the desired data. 


## Features

- Service Registry to check all the services status
- Config server is used to get common configurations from github
- API Gateway created for calling any services endpoint from only one port number
- Secured API Services with security using Okta
- Rate Limiter implemented to reduce pressure on server and fallback method created for dummy data in that case for services
- Actuator health status
- Using sevice names instead of port number to call other services


## API Reference

- POST: Create a new Movie or User or Rating
- GET: get according details




## Optimizations

Implementation of security at Userservice,MovieService and Ratingservice are in progress...


## Installation

Download project file and run with following properties locally

- UserService
```bash
#custom port
server.port=8081

#db specific properties
spring.application.name=USER-SERVICE
spring.datasource.url=jdbc:mysql://localhost:3306/microservice
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=sql@subhajit51193

#ORM s/w specific properties
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

#Eureka Client related
#eureka.instance.prefer-ip-address=true
#eureka.client.fetch-registry=true
#eureka.client.register-with-eureka=true
#eureka.client.service-url.defaultZone=http://localhost:8761/eureka

#Config client related configurations(eureka client related configurations directly taken from github)
spring.config.import=optional:configserver:http://localhost:8085

#Actuator Config
management.health.circuitbreakers.enabled=true
management.endpoints.web.exposure.include=health
management.endpoint.health.show-details=always

#Resilliance4j Config for circuit breaker
resilience4j.circuitbreaker.instances.ratingMovieBreaker.register-health-indicator=true
resilience4j.circuitbreaker.instances.ratingMovieBreaker.event-consumer-buffer-size=10
resilience4j.circuitbreaker.instances.ratingMovieBreaker.failure-rate-threshold=50
resilience4j.circuitbreaker.instances.ratingMovieBreaker.minimum-number-of-calls=5
resilience4j.circuitbreaker.instances.ratingMovieBreaker.automatic-transition-from-open-to-half-open-enabled=true
resilience4j.circuitbreaker.instances.ratingMovieBreaker.wait-duration-in-open-state=6s
resilience4j.circuitbreaker.instances.ratingMovieBreaker.permitted-number-of-calls-in-half-open-state=3
resilience4j.circuitbreaker.instances.ratingMovieBreaker.sliding-window-size=10
resilience4j.circuitbreaker.instances.ratingMovieBreaker.sliding-window-type=count-based

#Resilliance4j Config for retry
resilience4j.retry.instances.ratingMovieService.max-attempts=3
resilience4j.retry.instances.ratingMovieService.wait-duration=3s

#Resilliance4j Config for RateLimiter
resilience4j.ratelimiter.instances.userRateLimiter.limit-refresh-period=4s
resilience4j.ratelimiter.instances.userRateLimiter.limit-for-period=2
resilience4j.ratelimiter.instances.userRateLimiter.timeout-duration=2s
```


- MovieService

```bash
#custom port
server.port=8082

#db specific properties
spring.application.name=MOVIE-SERVICE
spring.datasource.url=jdbc:mysql://localhost:3306/microservice
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=sql@subhajit51193

#ORM s/w specific properties
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

#Eureka Client related
#eureka.instance.prefer-ip-address=true
#eureka.client.fetch-registry=true
#eureka.client.register-with-eureka=true
#eureka.client.service-url.defaultZone=http://localhost:8761/eureka

#Config client related configurations(eureka client related configurations directly taken from github)
spring.config.import=optional:configserver:http://localhost:8085
```

- RatingService

```bash
#custom port
server.port=8083

#db specific properties
spring.application.name=RATING-SERVICE
spring.datasource.url=jdbc:mysql://localhost:3306/microservice
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=sql@subhajit51193

#ORM s/w specific properties
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

#Eureka Client related
#eureka.instance.prefer-ip-address=true
#eureka.client.fetch-registry=true
#eureka.client.register-with-eureka=true
#eureka.client.service-url.defaultZone=http://localhost:8761/eureka

#Config client related configurations(eureka client related configurations directly taken from github)
spring.config.import=optional:configserver:http://localhost:8085
```

 - ServiceRegistry

```bash
server.port=8761
eureka.instance.hostname=localhost
eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false
```
- ConfigServer

```bash
#custom port
server.port=8085

#custom name
spring.application.name=CONFIG-SERVER

spring.cloud.config.server.git.uri=https://github.com/subhajit51193/MicroserviceConfiguration
spring.cloud.config.server.git.clone-on-start=true
```

- ApiGateway

```bash
#custom port
server.port=8084

#db specific properties
spring.application.name=API-GATEWAY

#Eureka Client related
#eureka.instance.prefer-ip-address=true
#eureka.client.fetch-registry=true
#eureka.client.register-with-eureka=true
#eureka.client.service-url.defaultZone=http://localhost:8761/eureka

#Config client related configurations(eureka client related configurations directly taken from github)
spring.config.import=optional:configserver:http://localhost:8085

#gateway related for userservice
spring.cloud.gateway.routes[0].id=USER-SERVICE
spring.cloud.gateway.routes[0].uri=lb://USER-SERVICE
spring.cloud.gateway.routes[0].predicates[0].name=Path
spring.cloud.gateway.routes[0].predicates[0].args[pattern]=/user/**

#gateway related for movieservice
spring.cloud.gateway.routes[1].id=MOVIE-SERVICE
spring.cloud.gateway.routes[1].uri=lb://MOVIE-SERVICE
spring.cloud.gateway.routes[1].predicates[0].name=Path
spring.cloud.gateway.routes[1].predicates[0].args[pattern]=/movie/**

#gateway related for ratingservice
spring.cloud.gateway.routes[2].id=RATING-SERVICE
spring.cloud.gateway.routes[2].uri=lb://RATING-SERVICE
spring.cloud.gateway.routes[2].predicates[0].name=Path
spring.cloud.gateway.routes[2].predicates[0].args[pattern]=/rating/**

#gateway related for movieservice test
spring.cloud.gateway.routes[3].id=MOVIE-SERVICE
spring.cloud.gateway.routes[3].uri=lb://MOVIE-SERVICE
spring.cloud.gateway.routes[3].predicates[0].name=Path
spring.cloud.gateway.routes[3].predicates[0].args[pattern]=/test/**

#okta related config
okta.oauth2.issuer=https://dev-51964859.okta.com/oauth2/default
okta.oauth2.audience=api://default
okta.oauth2.client-id=0oaa2dui9czVPe0TG5d7
okta.oauth2.client-secret=RWtw-DpSvONjrfpCk8z_UsCDppua0wcph9E0JrzB
okta.oauth2.scopes=openid,profile,email,offline_access
```
## Documentation

[SpringCloudGateway](https://spring.io/projects/spring-cloud-gateway)

[RateLimiter](https://resilience4j.readme.io/docs/ratelimiter)

[Okta](https://developer.okta.com/)


## Deployment

Not yet deployed

## Demo

https://drive.google.com/file/d/16x9yqXE6znPhab-eUbRdNdEp86-asc24/view?usp=sharing


## Tech Stack

**Client:** Java,SpringBoot,MySQL

**Server:** Embedded


## Authors

- [@Subhajit Saha](https://github.com/subhajit51193)


## Feedback

If you have any feedback, please reach out to us at nnorth87@gmail.com


## 🔗 Links
[![portfolio](https://img.shields.io/badge/my_portfolio-000?style=for-the-badge&logo=ko-fi&logoColor=white)](https://subhajit51193.github.io/)
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/subhajit-saha-103110185/)

