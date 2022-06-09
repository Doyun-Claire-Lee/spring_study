package com.example.springbootinitdataredis;

import com.example.springbootinitdataredis.account.Account;
import com.example.springbootinitdataredis.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RedisRunner implements ApplicationRunner {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        values.set("doyun", "lee");
        values.set("pass", "123");
        values.set("hello", "world");

        Account account = new Account();
        account.setUsername("doyun");
        account.setEmail("doyunnnnn@gmail.com");

        accountRepository.save(account);
        Optional<Account> byId = accountRepository.findById(account.getId());
        System.out.println(byId.get().getUsername());
        System.out.println(byId.get().getEmail());

    }
}

