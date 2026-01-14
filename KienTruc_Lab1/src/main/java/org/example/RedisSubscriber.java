package org.example;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class RedisSubscriber {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost", 6379);

        JedisPubSub subscriber = new JedisPubSub() {

            @Override
            public void onMessage(String channel, String message) {
                System.out.println("📩 Received from " + channel + ": " + message);
            }
        };

        System.out.println("🔄 Waiting for messages...");
        jedis.subscribe(subscriber, "order_channel");
    }
}

