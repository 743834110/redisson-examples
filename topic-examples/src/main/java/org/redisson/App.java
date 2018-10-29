package org.redisson;

import io.netty.util.internal.PlatformDependent;
import org.redisson.Redisson;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.api.listener.MessageListener;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {

        Config redisConfig = new Config();
        SingleServerConfig singleServerConfig = redisConfig.useSingleServer();
        singleServerConfig.setAddress("www.berby.xyz:6379")
                .setPassword("judp-2018")
                .setTimeout(2000)
                .setRetryAttempts(100);

        RedissonClient redissonClient = Redisson.create(redisConfig);
        RTopic<String> rTopic = redissonClient.getTopic("chat");
        rTopic.addListener((channel, msg) -> {
            System.out.println(channel + ":" + msg);
        });

        rTopic.publishAsync("redisson");
    }
}
