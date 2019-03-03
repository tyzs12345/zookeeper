package com.tian.collection.list;

import java.util.Comparator;
import java.util.Objects;

public class Person implements Comparator<Person> {

    public int age;
    public Person(){}
    public Person(int age){
        this.age = age;
    }

    @Override
    public int compare(Person o1, Person o2) {
        if(o1.age > o2.age){
            return 1;
        }else if(o1.age < o2.age){
            return -1;
        }else{
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                '}';
    }

}
