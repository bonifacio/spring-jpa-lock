package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "person")
public class PersonStatus {

    @Id
    private String id;

    private String status;

    public void setStatus(final String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PersonStatus{" +
                "id='" + id + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
