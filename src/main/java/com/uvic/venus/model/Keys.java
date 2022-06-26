package com.uvic.venus.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="keys")
public class Keys {

    @Id
    private int secret_id;
    private String secret_key;

    public Keys (int secret_id, String secret_key) {
        this.secret_id = secret_id;
        this.secret_key = secret_key;
    }

    public Keys () {

    }

    public int getSecret_id() {
        return secret_id;
    }

    public void setSecret_id(int secret_id) {
        this.secret_id = secret_id;
    }

    public String getSecret_key() {
        return secret_key;
    }

    public void setSecret_key(String secret_key) {
        this.secret_key = secret_key;
    }

    @Override
    public String toString() {
        return "SharedSecrets{" +
                "secret_id='" + secret_id + '\'' +
                ", secret_key='" + secret_key + '\'' +
                '}';
    }
}
