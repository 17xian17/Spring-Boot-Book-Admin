# Spring-Boot-Book-Admin

========================================

## Requirements

For building and running the application you need:

- [Java 8](https://www.java.com/en/download/)


Clone
--------

```sh
git clone https://github.com/17xian17/Spring-Boot-Book-Admin.git
```

Run
--------

```sh
cd Spring-Boot-Book-Admin
```

```sh
git checkout develop
```

```sh
./gradlew bootRun
```

![image](https://user-images.githubusercontent.com/44150408/126256875-a1bd9b22-0ca8-447d-84d3-a32379b10583.png)


Access
--------

### Select
#### For Swagger

```
http://localhost:8080/swagger-ui.html#/
```

#### Each Endpoint has a description indicated.
--------

![image](https://user-images.githubusercontent.com/44150408/126255637-b9456794-097a-42ea-b963-2e6c3b72b96a.png)

For Database

```
http://localhost:8080/h2-console/
```

![image](https://user-images.githubusercontent.com/44150408/126255508-4f279769-e9a9-4c0c-9dcf-d1a0c9642fac.png)

#### Data needed to access Database
##### JDBC URL
```sh
jdbc:h2:file:./data/book
```
##### Username: `test`
##### Password: `test`

H2 Database

![image](https://user-images.githubusercontent.com/44150408/126255818-f23f849c-97c3-4ddb-a52d-d5801353ab3d.png)

--------
## Just click OK
--------

![image](https://user-images.githubusercontent.com/44150408/126255891-0c71958b-fb10-4142-97f8-dd5d1e4580b2.png)


Check you data on Database
--------
Sample Book Data

![image](https://user-images.githubusercontent.com/44150408/126256416-237db534-4757-454f-a46b-f1bd05c3d6e8.png)

Sample Category Data

![image](https://user-images.githubusercontent.com/44150408/126256762-ed5f2460-b602-419c-b9ad-c15ce3d949e5.png)

Sample Category Ids Data

![image](https://user-images.githubusercontent.com/44150408/126257050-92cfb894-a547-4605-9bb0-6e7322f25d9c.png)



Create your own
--------

- [spring.io](https://start.spring.io)
