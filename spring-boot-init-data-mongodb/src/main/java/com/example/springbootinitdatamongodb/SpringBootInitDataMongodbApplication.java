package com.example.springbootinitdatamongodb;

import com.example.springbootinitdatamongodb.account.Account;
import com.example.springbootinitdatamongodb.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootInitDataMongodbApplication {

//    @Autowired
//    MongoTemplate mongoTemplate;

    @Autowired
    AccountRepository accountRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootInitDataMongodbApplication.class, args);
    }

    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> {
            Account account = new Account();
            account.setUsername("doyun3");
            account.setEmail("doyunnnnn3@gmail.com");

//            mongoTemplate.save(account);

            accountRepository.save(account);
        };
    }
}
