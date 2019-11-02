package cn.ihep.redisgo;

import cn.ihep.redisgo.pojo.Articel;
import cn.ihep.redisgo.redisExample.RedisGroup;
import javafx.application.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.Set;

/**
 * @author WuYe
 * @vesion 1.0 2019/11/2
 * /
 * /**
 * @program: redisgo
 * @description:
 * @author: WuYe
 * @create: 2019-11-02 15:40
 **/

public class RedisGroupTest extends RedisgoApplicationTests{
    @Autowired
    private RedisGroup redisGroup;

    @Test
    public void releaseArticel() throws InterruptedException {
        int i=0;
        while (i<30) {
            Articel articel = new Articel();
            articel.setId(i++);
            articel.setTitle("article"+i);
            articel.setLink("www.baidu.com");
            articel.setVotes(0);
            Thread.sleep(1000);
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
            articel.setTime(timestamp.getTime()/1000);
            redisGroup.releaseArticel(articel);
        }
    }

    @Test
    public void vote() {
        int i=0;
        while (i<30){
            int v =(int) (Math.random() * 10);
            System.out.println("acle"+i+"; vote"+v);
            for(int j=0;j<v;j++){
              redisGroup.vote(i,i+j+30);
            }
            i++;
        }

    }
    @Test
    public void addTag() {
            int i=0;
        while (i<30){
        redisGroup.addTag(i,""+i/10);
        i++;
        }
    }

    @Test
    public void getTagbyTime() {
        Set<String> time = redisGroup.getTagbyTime("" + 1);
        Iterator<String> iterator = time.iterator();
        while (iterator.hasNext()){
            System.out.println("时间排序:"+iterator.next());
        }
    }

    @Test
    public void getTagbyscore() {
        Set<String> score = redisGroup.getTagbyscore("" + 1);
        Iterator<String> iterator = score.iterator();
        while (iterator.hasNext()){
            System.out.println("投票排序:"+iterator.next());
        }
    }

}
