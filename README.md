
# Basic Spring Security

Using `spring-security` to implement Role Based Access Control

| API  | Roles | Description |
| ------------ | ---- | --- |
| /greet/citizen | No Authentication | Any user can Access this API  
| /greet/avenger | Avenger, Director | Only users with roles `Avenger` and  `Director` can access this API
| /greet/director | Director | Only users with role  `Director` can access this API

The Security Configuration can be found [here]()