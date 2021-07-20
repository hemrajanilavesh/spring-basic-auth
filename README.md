
# Basic Spring Security

Using `spring-security` to implement Role Based Access Control

![Java CI with Maven](https://github.com/hemrajanilavesh/spring-basic-authentcation/actions/workflows/maven.yml/badge.svg)

| API  | Required Roles | Description |
| ------------ | ---- | --- |
| /greet/citizen | No Authentication | Any user can Access this API  
| /greet/avenger | Avenger or Director | Only users with roles `Avenger` and  `Director` can access this API
| /greet/director | Director | Only users with role  `Director` can access this API

The Security Configuration can be found [here](https://github.com/hemrajanilavesh/spring-basic-authentcation/blob/main/src/main/java/io/hemrlav/basicauthentication/config/SecurityConfiguration.java)
