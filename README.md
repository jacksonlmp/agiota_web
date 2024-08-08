spring.application.name=agiota

spring.datasource.driverClassName=org.postgresql.Driver
spring.datasource.url=jdbc:postgresql://localhost:5432/agiota
spring.datasource.username=postgres
spring.datasource.password=luana01=

spring.jpa.hibernate.ddl.auto=update
spring.jpa.show-sql=true
spring.jpa.database=postgresql

api.security.token.secret=${JWT_SECRET:86931524}
