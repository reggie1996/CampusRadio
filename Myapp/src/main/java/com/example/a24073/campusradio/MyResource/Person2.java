package com.example.a24073.campusradio.MyResource;

/**
 * Created by 24073 on 2017/6/6.
 */

public class Person2{
    int id;
    String name;

    public Person2() {
    }

    public Person2(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person2{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}