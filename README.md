# AppDirect integration challenge #

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

## How to build ##
```code
mvn clean install
```

## Other info ##
 * Application url: http://desolate-journey-69317.herokuapp.com/
 * Appdirect app name: sample
   _* The exported app profile can be found in the Product-168206.zip file_


