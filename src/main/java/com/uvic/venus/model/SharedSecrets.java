package com.uvic.venus.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="shared_secrets")
public class SharedSecrets {

    @Id
    private int shared_id;
    private int secret_id;
    private String sender;
    private String recipient;

    public SharedSecrets (int shared_id, int secret_id, String sender, String recipient) {
        this.shared_id = shared_id;
        this.secret_id = secret_id;
        this.sender = sender;
        this.recipient = recipient;
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

    @Override
    public String toString() {
        return "SharedSecrets{" +
                "shared_id'" + shared_id + '\'' +
                ", secret_id='" + secret_id + '\'' +
                ", sender='" + sender + '\'' +
                ", recipient='" + recipient + '\'' +
                '}';
    }
}
