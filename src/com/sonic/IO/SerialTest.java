package com.sonic.IO;

import java.io.*;

/**
 * @author Sonic
 * @since 2020-02-20
 */
public class SerialTest {
    public static void main(String[] args) throws Exception {
        Sheep sheep = new Sheep(1, "Doly", 2);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("d:/lll")));
        oos.writeObject(sheep);
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("d:/lll")));
        Sheep sheepReturn = (Sheep) ois.readObject();
        System.out.println(sheepReturn);
    }

}

class Sheep implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private int age;

    public Sheep() {
    }

    public Sheep(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Sheep{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}