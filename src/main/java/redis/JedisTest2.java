package redis;


import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


import java.util.Date;

public class JedisTest2 {
    public static void main(String[] args) {
        GenericObjectPoolConfig<Jedis> genericObjectPoolConfig = new GenericObjectPoolConfig<>();
        Logger logger= LoggerFactory.getLogger(JedisTest2.class);
        JedisPool jedisPool=new JedisPool(genericObjectPoolConfig,"59.72.63.157",6379);
        try(Jedis jedis=jedisPool.getResource()) {
            jedis.get("hello");
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
   }

}
