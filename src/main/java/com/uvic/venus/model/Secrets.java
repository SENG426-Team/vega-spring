package com.uvic.venus.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="secrets")
public class Secrets {

    @Id
    private int secret_id;
    private String username;
    private String date_created;
    private String secret_content;

    public Secrets (int secret_id, String username, String date_created, String secret_content) {
        this.secret_id = secret_id;
        this.username = username;
        this.date_created = date_created;
        this.secret_content = secret_content;
    }

    public Secrets () {

    }

    public int getSecret_id() {
        return this.secret_id;
    }

    public void setSecret_id (int secret_id) {
        this.secret_id = secret_id;
    }

    public String getUsername () {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate_created () {
        return this.date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public String getSecret_content() {
        return this.secret_content;
    }

    public void setSecret_content(String secret_content) {
        this.secret_content = secret_content;
    }

    @Override
    public String toString() {
        return "Secret{" +
                "secret_id='" + secret_id + '\'' +
                ", username='" + username + '\'' +
                ", date_created='" + date_created + '\'' +
                ", secret_content='" + secret_content + '\'' +
                '}';
    }
}
