package cn.ihep.redisgo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisgoApplicationTests {
    @Autowired
    protected StringRedisTemplate redisTemplate;
    @Test
    public void contextLoads() {
    }
    @Before
    public void init() {
        System.out.println("开始测试goooooooooooooo-----------------");
    }

    @After
    public void after() {
        System.out.println("测试结束-----------------");
    }

}
