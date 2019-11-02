package cn.ihep.redisgo.redisExample;

import cn.ihep.redisgo.Utils.BeanUtils;
import cn.ihep.redisgo.pojo.Articel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author WuYe
 * @vesion 1.0 2019/11/2
 * /
 * /**
 * @program: redisgo
 * @description: 文章分组
 * @author: WuYe
 * @create: 2019-11-02 14:49
 **/
@Component
public class RedisGroup {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void releaseArticel(Articel articel){
        Map<String,String>map=new HashMap<>();
        map.put("title",articel.getTitle());
        map.put("link",articel.getLink());
        map.put("votes",articel.getVotes()+"");
        map.put("time",articel.getTime()+"");

        redisTemplate.opsForHash().putAll("articel:"+articel.getId(),map);
        redisTemplate.opsForZSet().add("score","articel:"+articel.getId(),articel.getTime());
        redisTemplate.opsForZSet().add("time","articel:"+articel.getId(),articel.getTime());
    }


    public void vote(int id, int userId){
        String time = (String)redisTemplate.opsForHash().get("articel:" + id, "time");
        Long aLong = Long.valueOf(time);
        if( System.currentTimeMillis()/1000-aLong>60*60*24*7){
             return;
         }

        if(!redisTemplate.opsForSet().isMember("vote:"+id,"user:"+userId)) {
         redisTemplate.opsForZSet().incrementScore("score", "articel:" + id, 86400);
             redisTemplate.opsForHash().increment("articel:"+id,"votes",1L);
             redisTemplate.opsForSet().add("vote:"+id,"user:"+userId);
         }

    }
    public void addTag(int id, String tag){
            redisTemplate.opsForSet().add("tag:"+tag,"articel:"+id);
        }
     public Set<String> getTagbyTime(String tag){
        redisTemplate.opsForZSet().intersectAndStore("tag:" + tag, "time","tag:"+tag+":time");
         Set<String> time = redisTemplate.opsForZSet().range("tag:" + tag + ":time", 0, -1);
        return  time;

     }
     public Set<String> getTagbyscore(String tag){
         redisTemplate.opsForZSet().intersectAndStore("tag:" + tag, "score","tag:"+tag+":score");
         Set<String> score = redisTemplate.opsForZSet().range("tag:" + tag + ":score", 0, -1);
        return  score;
     }





}
