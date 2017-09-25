package com.nimbus.ejuris.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by nimbus on 25/09/17.
 */
public class User extends RealmObject {
    public static final String ID = "com.nimbus.ejuris.domain.RealmObject.ID";
    @PrimaryKey
    private long id;

    private String email;
    private String password;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String name) {
        this.email = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
