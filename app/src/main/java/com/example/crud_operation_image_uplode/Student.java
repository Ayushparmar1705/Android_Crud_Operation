package com.example.crud_operation_image_uplode;

public class Student {
    private int id , age;
    private String name , country , gender , hobby , date;

    public Student(String name , int age, String country, String gender, String hobby, String date) {

        this.name = name;
        this.age = age;
        this.country = country;
        this.gender = gender;
        this.hobby = hobby;
        this.date = date;
    }

    public Student(int id, int age, String name, String country, String gender, String hobby, String date) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.country = country;
        this.gender = gender;
        this.hobby = hobby;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Student{" +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", gender='" + gender + '\'' +
                ", hobby='" + hobby + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
