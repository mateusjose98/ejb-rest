## EJB 3 + JPA

## Tecnologias utilizadas
- MySql 5.6
- Java 8
- Wildfly 15 com standalone-full.xml
- JPA

Configurar um datasource no wildfly

- module remove --name=com.mysql

- module add --name=com.mysql --resources=C:\Users\pc\Downloads\mysql-connector-java-8.0.15\conn.jar --dependencies=javax.api,javax.transaction.api

(Ao adicionar um module, reinicie o servidor)

- /subsystem=datasources/jdbc-driver=mysql:add(driver-name=mysql,driver-module-name=com.mysql,driver-xa-datasource-class-name=com.mysql.cj.jdbc.MysqlXADataSource)

- data-source add --name=AgendamentoDS --jndi-name=java:jboss/datasources/AgendamentoDS --driver-name=mysql  --connection-url=jdbc:mysql://localhost:3306/agendamentobd --user-name=root --password=pass --min-pool-size=10 --max-pool-size=20