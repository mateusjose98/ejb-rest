# EJB 3 + JPA - Curso Alura

### Objetivo

A aplicação provê endpoints JAXRS que recebem e listam agendamentos de emails. 
Os agendamentos são salvos como não enviados. De tempos em tempos (todo minuto, no momento) um job envia os agendamentos para uma fila JMS para futuramente serem de fato enviados.

## Tecnologias utilizadas
- MySql 5.6
- Java 8
- Wildfly 15 com standalone-full.xml
- JPA
- JavaMail
- JMS

##Configurações

Observações: os comandos devem ser executados dentro do servidor, para acessá-lo, utilize: `wildfly-instalation\bin\jboss-cli.[bat/sh] --connect`

### Configurar um datasource no wildfly

- module remove --name=com.mysql

- module add --name=com.mysql --resources=C:\Users\pc\Downloads\mysql-connector-java-8.0.15\conn.jar --dependencies=javax.api,javax.transaction.api

(Ao adicionar um module, reinicie o servidor)

- /subsystem=datasources/jdbc-driver=mysql:add(driver-name=mysql,driver-module-name=com.mysql,driver-xa-datasource-class-name=com.mysql.cj.jdbc.MysqlXADataSource)

- data-source add --name=AgendamentoDS --jndi-name=java:jboss/datasources/AgendamentoDS --driver-name=mysql  --connection-url=jdbc:mysql://localhost:3306/agendamentobd --user-name=root --password=pass --min-pool-size=10 --max-pool-size=20

Após realizar isso, é necessário ter o persistence.xml para que a aplicação saiba como se conectar. Exemplo:

```
<?xml version="1.0" encoding="UTF-8"?>
<persistence>
   <persistence-unit name="agendamento">

        <jta-data-source>java:jboss/datasources/AgendamentoDS</jta-data-source>
        <properties>
            <property name="javax.persistence.schema-generation.database.action" value="update" />
        </properties>

    </persistence-unit>
</persistence>
```

### Configurar email no wildfly

- /subsystem=mail/mail-session=agendamentoMailSession:add(jndi-name=java:jboss/mail/AgendamentoMailSession)

- /socket-binding-group=standard-sockets/remote-destination-outbound-socket-binding=my-smtp-binding:add(host=SEU_HOST, port=2525)

- /subsystem=mail/mail-session=agendamentoMailSession/server=smtp:add(outbound-socket-binding-ref= my-smtp-binding, username=SEUU_SUARIO, password=SUA_SENHA, tls=true)


### Criar uma fila JMS no wildfly

jms-queue add --queue-address=EmailQueue --entries=java:/jms/queue/EmailQueue


### Testando a aplicação:

Adicione o wildfly em sua IDE

Use as configurações anteriores no wildfly

Adicione a aplicação ao wildfly

Acesse no navegador o link `http://localhost:8080/ `