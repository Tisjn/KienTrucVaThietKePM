package com.library.observer;

public class LibraryEvent {
    private String eventType;
    private String message;
    private long timestamp;

    public LibraryEvent(String eventType, String message) {
        this.eventType = eventType;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }

    public String getEventType() {
        return eventType;
    }

    public String getMessage() {
        return message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "[" + eventType + "] " + message + " (Time: " + timestamp + ")";
    }
}
