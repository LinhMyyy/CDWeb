package com.cdweb.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "persistent_logins")
public class PersistentLogins {

    @Column
    private String username;
    @Id
    private String series;
    @Column
    private String token;
    @Column
    private Date last_used;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getLast_used() {
        return last_used;
    }

    public void setLast_used(Date last_used) {
        this.last_used = last_used;
    }
}
