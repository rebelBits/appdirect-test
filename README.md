# AppDirect integration challenge #

## Requires ##
* Java 8
* maven

## Implemented endpoints ##
 * Subscriber
   * Create: http://desolate-journey-69317.herokuapp.com/api/notification/subscription/change?url={eventUrl}
   * Cancel: http://desolate-journey-69317.herokuapp.com/api/notification/subscription/cancel?url={eventUrl}
   * Change: http://desolate-journey-69317.herokuapp.com/api/notification/subscription/status?url={eventUrl}
 * User
   * assign: http://desolate-journey-69317.herokuapp.com/api/notification/user/assignment?url={eventUrl}
   * unassign: http://desolate-journey-69317.herokuapp.com/api/notification/user/unassignment?url={eventUrl}

## Security ##
* OpenID authentication (login/logout)
* Endpoints OAuth security

## Technologies used ##
* spring-boot
* spring security
* openid4java
* thymeleaf
* JDBC
* postgresql (default on heroku)
* h2 DB for unit tests

## How to build & use ##
```code
mvn clean install
```
The build will generate an war file. 

Several options to run the app:
1. deploy the war file in Tomcat
2. run the app using the embedded webcontainer: java -jar target/sample-0.0.1-SNAPSHOT.war
(_deploying under heroku: '$>heroku war:deploy target/sample-0.0.1-SNAPSHOT.war --app your-app-name_')

## Other info ##
 * Application url: http://desolate-journey-69317.herokuapp.com/
   * the app contains a simple UI that lists the subscriptions and users
 * Appdirect app name: sample
   _* The exported app profile can be found in the Product-168206.zip file_


