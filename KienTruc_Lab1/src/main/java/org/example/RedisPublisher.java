package org.example;

import redis.clients.jedis.Jedis;

public class RedisPublisher {

    public static void main(String[] args) {
        try (Jedis jedis = new Jedis("localhost", 6379)) {

            String channel = "order_channel";
            String message = "New Order ID = 123";

            jedis.publish(channel, message);

            System.out.println("✅ Sent message: " + message);
        }
    }
}
