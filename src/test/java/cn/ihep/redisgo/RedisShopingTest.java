package cn.ihep.redisgo;

import cn.ihep.redisgo.redisExample.RedisShoping;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.bind.PrintConversionEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * @author WuYe
 * @vesion 1.0 2019/11/2
 * /
 * /**
 * @program: redisgo
 * @description:
 * @author: WuYe
 * @create: 2019-11-02 19:37
 **/
public class RedisShopingTest extends RedisgoApplicationTests {
    @Autowired
    private RedisShoping redisShoping;
    @Test
    public void update() throws InterruptedException {
        redisShoping.update();
    }
    @Test
    public void creatData(){
        int i=0;
        while (i<100) {
            Map<String, String> map = new HashMap<>();
            map.put("price","100");
            map.put("count","100");
            redisTemplate.opsForHash().putAll("product:"+i,map);
            redisTemplate.opsForZSet().add("schedule","product:"+i,System.currentTimeMillis()/1000+i);
            long l = (long) (Math.random() * 10);
            redisTemplate.opsForZSet().add("delay","product:"+i,l);
            i++;
        }

    }
}
