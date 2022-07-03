package com.uvic.venus.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="shared_secrets")
public class SharedSecrets {

    @Id
    private int secret_id;
    private int shared_id;
    private String sender;
    private String recipient;
    private String date_shared;
    private String temp_content;

    public SharedSecrets (int shared_id, int secret_id, String sender, String recipient, String date_shared, String temp_content) {
        this.shared_id = shared_id;
        this.secret_id = secret_id;
        this.sender = sender;
        this.recipient = recipient;
        this.date_shared = date_shared;
        this.temp_content = temp_content;
    }

    public SharedSecrets () {

    }

    public int getSecret_id() {
        return secret_id;
    }

    public void setSecret_id(int secret_id) {
        this.secret_id = secret_id;
    }

    public int getShared_id() {
        return shared_id;
    }

    public void setShared_id(int shared_id) {
        this.shared_id = shared_id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getDate_shared() {
        return date_shared;
    }

    public void setDate_shared(String date_shared) {
        this.date_shared = date_shared;
    }

    public String getTemp_content() {
        return temp_content;
    }

    public void setTemp_content(String content) {
        this.temp_content = content;
    }

    @Override
    public String toString() {
        return "SharedSecrets{" +
                "shared_id'" + shared_id + '\'' +
                ", secret_id='" + secret_id + '\'' +
                ", sender='" + sender + '\'' +
                ", recipient='" + recipient + '\'' +
                ", date_shared='" + date_shared + '\'' +
                '}';
    }
}
