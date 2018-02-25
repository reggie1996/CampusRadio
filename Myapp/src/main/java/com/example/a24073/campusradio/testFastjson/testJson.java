package com.example.a24073.campusradio.testFastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.StringCodec;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 24073 on 2017/6/4.
 */

public class testJson {

    public void jsonToObject(){
        String json = null;
        Person person = JSON.parseObject(json,Person.class);
    }

    public void objectToJson(){
        String s = JSON.toJSONString(null);
    }

    public void jsonToList(){
        String json = null;
        List<Person> persons = JSON.parseArray(json, Person.class);
    }

    public void listToJson(){
        List<Person> persons = new ArrayList<>();
        String json = JSON.toJSONString(persons);
    }

    public class Person {
        private int id;
        private String name;
        private Date birthday;
        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }
        public String getName() {
            return name;
        }
        public Person() {
            super();
        }
        public Person(int id, String name, Date birthday) {
            super();
            this.id = id;
            this.name = name;
            this.birthday = birthday;
        }
        @Override
        public String toString() {
            return "Person [id=" + id + ", name=" + name + ", birthday=" + birthday + "]";
        }
        public void setName(String name) {
            this.name = name;
        }
        public Date getBirthday() {
            return birthday;
        }
        public void setBirthday(Date birthday) {
            this.birthday = birthday;
        }
    }

}
