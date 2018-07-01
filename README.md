# The transactional statistics challenge

The transactional statistics challenge for the N26 company.

It is about two simple APIs, one to create new transactions and another to get real-time statistics about it.

## Tech stack

- **Pragamming Language:** Java 8
- **Dependency Control:** Maven
- **Dev Framework:** Spring boot
- **Documentation:** Swagger / Spring fox

## Installing

```bash
git clone https://github.com/brunoLNCosta/statistics-challenge && ./mvnw clean install && ./mvnw spring-boot:run
```

## API Documentation

The documentation is provided to by Swagger in the following path: **/swagger-ui.html**

There are two rest endpoints:

- **/statistics:** curl -X GET "http://localhost:8080/statistics" -H "accept: */*"
- **/transactions:** curl -X POST "http://localhost:8080/transactions" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"ammount\": 105, \"timestamp\":1530390478427}"


## Contact

* [Bruno Costa](https://github.com/brunoLNCosta) - brunoluisncosta@gmail.com

## OBS

  1) The challenge was made in less than two days.

  2) I put swagger documentation to make the thing easier to me, hope you guys do not mind it.

  3) I organize the package as a Domain Drive Design project, because I am used to do so, but I know that DDD does not apply to this project.

  4) Take a look to my open-source project [Roxana](https://rooting-company.github.io/roxana/). It is Spring based.
  
  5) I used Junit 4, because Jnit 5 has problems eclipse and I decided to not waste time with it.

## License

The Spring Framework is released under version 2.0 of the
[Apache License](http://www.apache.org/licenses/LICENSE-2.0).
