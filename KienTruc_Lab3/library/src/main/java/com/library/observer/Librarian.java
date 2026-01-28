package com.library.observer;

public class Librarian implements Observer {
    private String name;

    public Librarian(String name) {
        this.name = name;
    }

    @Override
    public void update(LibraryEvent event) {
        System.out.println("Librarian [" + name + "] received notification: " + event);
    }
}
