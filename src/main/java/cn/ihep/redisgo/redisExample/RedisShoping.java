package cn.ihep.redisgo.redisExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.Set;

/**
 * @author WuYe
 * @vesion 1.0 2019/11/2
 * /
 * /**
 * @program: redisgo
 * @description: 促销修改缓存信息策略，商品信息，执行表，延迟表
 * @author: WuYe
 * @create: 2019-11-02 19:33
 **/
@Component
public class RedisShoping {
    @Autowired
    private StringRedisTemplate redisTemplate;
    public  void update() throws InterruptedException {
        while (true){
//            redisTemplate.opsForHash().putAll("product:"+i,map);
//            redisTemplate.opsForZSet().add("schedule","product:"+i,System.currentTimeMillis()/1000+10);
//            long l = (long) (Math.random() * 10);
//            redisTemplate.opsForZSet().add("delay","product:"+i,l);
            Set<ZSetOperations.TypedTuple<String>> schedule = redisTemplate.opsForZSet().rangeWithScores("schedule", 0, 2);
            Iterator<ZSetOperations.TypedTuple<String>> iterator = schedule.iterator();
            String value="";
            Double score=0d;
            while (iterator.hasNext()){
                ZSetOperations.TypedTuple<String> next = iterator.next();
                 value = next.getValue();
                 score = next.getScore();
                    Double delay = redisTemplate.opsForZSet().score("delay", value);
                    if (delay<=0){
                        redisTemplate.delete(value);
                        redisTemplate.opsForZSet().remove("schedule",value);
                        redisTemplate.opsForZSet().remove("delay",value);
                        continue;
                    }
                long time = System.currentTimeMillis() / 1000;
                    System.out.println("\n---sleep---"+value+new Timestamp(time*1000));
                while (time<score){
                    Thread.sleep(1000);
                    time = System.currentTimeMillis() / 1000;
                }
                    System.out.println("---process---"+value+new Timestamp(time*1000)+"\n");
                System.out.flush();
                redisTemplate.opsForHash().increment(value,"count",-1);
                redisTemplate.opsForZSet().add("schedule",value,score+delay);
            }
        }
    }

}
