package redis;


import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

import java.util.List;

public class JedisTest3 {
    public static void main(String[] args) {
        GenericObjectPoolConfig<Jedis> genericObjectPoolConfig = new GenericObjectPoolConfig<>();
        genericObjectPoolConfig.setMaxTotal(GenericObjectPoolConfig.DEFAULT_MAX_TOTAL*5);
        genericObjectPoolConfig.setMaxIdle(GenericObjectPoolConfig.DEFAULT_MAX_IDLE*3);
        genericObjectPoolConfig.setMinIdle(GenericObjectPoolConfig.DEFAULT_MIN_IDLE*2);
        genericObjectPoolConfig.setJmxEnabled(true);
        genericObjectPoolConfig.setMaxWaitMillis(3000);

        Logger logger= LoggerFactory.getLogger(JedisTest3.class);
        JedisPool jedisPool=new JedisPool(genericObjectPoolConfig,"59.72.63.157",6379);
        try(Jedis jedis=jedisPool.getResource()) {
            Pipeline pipeline= jedis.pipelined();
            pipeline.set("hello","world");
            pipeline.incr("counter");
            pipeline.incr("counter");
            pipeline.incr("counter");
            pipeline.incr("counter");
            pipeline.incr("counter");
            pipeline.incr("counter");
            pipeline.incr("counter");
            List<Object> resultList=pipeline.syncAndReturnAll();
            for(Object object:resultList){
                System.out.println(object);
            }
            pipeline.sync();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
   }

}
