# cadastro-evento-jdbc  
Cadastro de evento usando Java JDBC  

# Getting Started  

### MySQL 8 docker  
* [MySQL Docker Official image](https://hub.docker.com/_/mysql)  
```sh
docker run --name mysql8 --network minha-rede -v $(pwd)/mysql-datadir:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=my-secret-pw -p 3306:3306 -d mysql:8
```

### Connect do MySQL database with WorkBench or mysql terminal client and Execute DLL   
```sql
create database cadastroevento;

create table evento (
    id bigint not null auto_increment,
    nome varchar(60) not null,
    data datetime not null,
    primary key(id)
);

insert into evento (id, nome, data)
values (null, 'Palestra João da Silva', sysdate());

select * from evento;

```