package cn.ihep.redisgo.redisExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author WuYe
 * @vesion 1.0 2019/11/2
 * /
 * /**
 * @program: redisgo
 * @description:
 * @author: WuYe
 * @create: 2019-11-02 16:24
 **/
public class test {
    @Autowired
    private StringRedisTemplate redisTemplate;

    public static void main(String[] args) {
        new test().redisTemplate.opsForValue().set("121","dadasdas");
    }
}
