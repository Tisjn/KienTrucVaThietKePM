package com.library.observer;

public class UserSubscriber implements Observer {
    private String userId;

    public UserSubscriber(String userId) {
        this.userId = userId;
    }

    @Override
    public void update(LibraryEvent event) {
        System.out.println("User [" + userId + "] received notification: " + event);
    }
}
