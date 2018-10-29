package com.example.virg.thesis;

import java.io.Serializable;

public class Wrestler implements Serializable {
    private String name = null;

    public Wrestler(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}








