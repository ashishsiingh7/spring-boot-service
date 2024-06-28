### Steps to run locally
Set up MySQL database. 

MySQL port: 3306

Database: test

Set up Kafka.

kafka port: 9092

```bash
mvn spring-boot:run
```
### After running the application, try the following API

### Sample REST Create and Get API with Spring Boot, Mysql, JPA

### create command:

```bash
curl --location 'localhost:8080/api/v1/customer' \
--header 'Content-Type: application/json' \
--data '{
"firstName": "Ashish",
"lastName": "Singh",
"age": 60,
"spendingLimit": "123456.009876",
"mobileNumber": "2323232323",
"addresses": [{
"addressType": "home",
"street": "test street",
"city": "Indore",
"state": "MP",
"zipCode": "123456"
}]
}'
```



Response:

```bash
{
"customerId": 16,
"firstName": "Ashish",
"lastName": "Singh",
"age": 60,
"mobileNumber": "2323232323",
"spendingLimit": "123456.009876",
"addresses": [
{
"id": 15,
"addressType": "home",
"street": "test street",
"city": "Indore",
"state": "MP",
"zipCode": "123456"
}
]
}
```


### get customer based on name, city and city:

```bash
curl --location 'localhost:8080/api/v1/search?name=Sachin&city=Gurgaon&state=Haryana'
```


Response:

```bash
[
{
"customerId": 15,
"firstName": "Sachin",
"lastName": "Tendulkar",
"age": 30,
"mobileNumber": "2323232323",
"spendingLimit": "123456.009876",
"addresses": [
{
"id": 14,
"addressType": "home",
"street": "test street",
"city": "gurgaon",
"state": "Haryana",
"zipCode": "123456"
}
]
}
]
```
