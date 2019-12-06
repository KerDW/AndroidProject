package com.example.projecte_uf1;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

public class User extends RealmObject {

    @PrimaryKey
    private String          name;

    private double          time;

    @Ignore
    private int             sessionId;
    

    public String getName() { return name; }
    public void   setName(String name) { this.name = name; }

    public double    getTime() { return time; }
    public void   setTime(double time) { this.time = time; }

    public int    getSessionId() { return sessionId; }
    public void   setSessionId(int sessionId) { this.sessionId = sessionId; }
}
