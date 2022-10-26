package redis;


import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

import java.util.List;

public class JedisLua {
    public static void main(String[] args) {
        GenericObjectPoolConfig<Jedis> genericObjectPoolConfig = new GenericObjectPoolConfig<>();
        Logger logger= LoggerFactory.getLogger(JedisLua.class);
        JedisPool jedisPool=new JedisPool(genericObjectPoolConfig,"59.72.63.157",6379);

   }

}
