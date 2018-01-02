# web-notes

Java Spring Boot example for Heroku Platform.
 - Rest Exposition: Spring Data REST  
 - Persistence: Spring Data JPA with Hibernate  
 - Database: PostgreSQL
 - Building tool: Maven

Note object is exposed at http://localhost:8080/note. Available methods are: GET(id), PUT, POST, DELETE(id). 
Search operations are exposed at http://localhost:8080/note/find. It is possible to search by writer or date. 

Examples:
- GET http://localhost/note?id=1
- GET http://localhost/note/find
- GET http://localhost/note/find?writer=Espronceda
- GET http://localhost/note/find?date=2017-10-10
- POST http://localhost/note {"text":"Viento en popa a toda vela","writer":"Espronceda"}
- PUT http://localhost/note {"id":1,"text":"no corta el mar sino vuela","writer":"Espronceda"}
- DELETE http://localhost/note?id=1

NOTE object:
- Id: Autogenerated by database
- Date: Server generated
- Timestamp: Server generated
- Text: String, mandatory. Max 500
- Writer: String, optional. Max 100

There are two profiles for this project:
 - default: Property file is configured to use a local PostgreSQL database. nbactions.xml is included for Netbeans. Run app with default profile with: java -jar target/*.jar or  mvn spring-boot:run
 - heroku: Specific property file for Heroku PostgreSQL database. Procfile is included to tell heroku how to run the app. Deploy to heroku just pushing the repository: git push heroku master
