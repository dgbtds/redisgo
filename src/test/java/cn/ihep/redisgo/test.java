package cn.ihep.redisgo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author WuYe
 * @vesion 1.0 2019/10/29
 * /
 * /**
 * @program: redislearn
 * @description: redis测试
 * @author: WuYe
 * @create: 2019-10-29 17:25
 **/

public class test {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Test
    public void get(){

       // redisTemplate.opsForValue().set("redis","jadjk");
        redisTemplate.opsForZSet().add("group","customer",20);
        redisTemplate.opsForList().leftPushAll("list","1","23","da","2aa");
    }
}
