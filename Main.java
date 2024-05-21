package com.company;
import java.util.*;
public class Main {

    public static void main(String[] args) {
        User artem = new User("Artem");
        User aslan = new User("Aslan");
        Group group1 = new Group("Группа1");
        Group group2 = new Group("Группа2");
        Group group3 = new Group("Группа3");
        Group group4 = new Group("Группа4");
        group1.registerObserver(artem);
        group1.registerObserver(aslan);
        group2.registerObserver(artem);
        group2.registerObserver(aslan);
        group3.registerObserver(artem);
        group3.registerObserver(aslan);
        group4.registerObserver(artem);
        group1.postMessage("Новое сообщение");
        group2.postMessage("Обновнение группы");
        group3.postMessage("Подписался на группу");
        group4.postMessage("Вышел из группы");
    }
}
class User{
    private String name;
    public User(String name){
        this.name=name;
    }
    public void update(String message){
        System.out.println(name + "Получил сообщение: " + message);
    }
}
interface Subject{
    void registerObserver(User observer);
    void removeObserver(User observer);
    void notifyObserver();
}
class Group implements Subject{
    private String name;
    private List user;
    public Group(String name){
        this.name=name;
        this.user = new ArrayList();
    }
    @Override
    public void registerObserver(User observer){
        user.add(observer);
    }
    @Override
    public void removeObserver(User observers){
        int i = user.indexOf(observers);
        if(i>=0){
            user.remove(i);
        }
    }
    @Override
    public void notifyObserver(){
        for (int i = 0; i < user.size(); i++){
            User obs = (User) user.get(i);
            obs.update(name);
        }
    }
    public void postMessage(String message){
        System.out.println("Сообщение в группе " +name+ ": "+ message);
    }
}