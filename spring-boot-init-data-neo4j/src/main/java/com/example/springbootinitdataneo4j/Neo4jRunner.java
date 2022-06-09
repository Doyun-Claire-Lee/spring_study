package com.example.springbootinitdataneo4j;

import com.example.springbootinitdataneo4j.account.Account;
import com.example.springbootinitdataneo4j.account.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.neo4j.core.Neo4jTemplate;
import org.springframework.stereotype.Component;

@Component
public class Neo4jRunner implements ApplicationRunner {

    @Autowired
    Neo4jTemplate neo4jTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account account = new Account();
        account.setUsername("doyun");
        account.setEmail("doyunnnnn@gmail.com");

        Role role = new Role();
        role.setName("admin");
        account.getRoles().add(role);

        neo4jTemplate.save(account);
    }
}
