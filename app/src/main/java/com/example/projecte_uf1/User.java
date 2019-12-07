package com.example.projecte_uf1;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

public class User extends RealmObject {

    // this is a replacement for an autoincrement kind of
    // what I really need is a unique key of name+time, sadly realm doesn't offer that directly still
    // https://github.com/realm/realm-java/issues/1129
//    @PrimaryKey
//    private String          id;

    // this would be the workaround
    @PrimaryKey
    private String          name_time;

    private String          name;

    private double          time;

    @Ignore
    private int             sessionId;

    public User(String userName, int timeLeft) {
//        this.id = UUID.randomUUID().toString();
        this.name_time = userName+timeLeft;
        this.name = userName;
        this.time = timeLeft;
    }

    public User(){

    }

    public String getNameTime() { return name_time; }
    public void   setNameTime(String name_time) { this.name_time = name_time; }

    public String getName() { return name; }
    public void   setName(String name) { this.name = name; }

    public double    getTime() { return time; }
    public void   setTime(double time) { this.time = time; }

    public int    getSessionId() { return sessionId; }
    public void   setSessionId(int sessionId) { this.sessionId = sessionId; }
}
