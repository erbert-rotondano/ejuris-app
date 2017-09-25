package com.nimbus.ejuris.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by nimbus on 22/09/17.
 */
public class Process extends RealmObject{
    public static final String ID = "com.nimbus.ejuris.domain.RealmObject.ID";
    @PrimaryKey
    private long id;

    private String name;
    private String description;
    private boolean process_completed;
    private boolean published;

    public boolean isProcess_completed() {
        return process_completed;
    }

    public void setProcess_completed(boolean process_completed) {
        this.process_completed = process_completed;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }
}
