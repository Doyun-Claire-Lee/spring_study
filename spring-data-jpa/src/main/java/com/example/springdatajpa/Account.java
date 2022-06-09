package com.example.springdatajpa;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Account {

    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created = new Date();

    @Transient
    private String notColumn;

    @Embedded
    @AttributeOverrides(
            @AttributeOverride(name = "street", column = @Column(name = "new_street"))  //엔티티의 컬럼명은 Street이지만 DB 에는 new_street으로 저장하겠다는 의미
    )
    private Address address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getNotColumn() {
        return notColumn;
    }

    public void setNotColumn(String notColumn) {
        this.notColumn = notColumn;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
