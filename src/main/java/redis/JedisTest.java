package redis;


import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.runtime.RuntimeSchema;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.Date;

public class JedisTest {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(JedisTest.class);
        try (Jedis jedis = new Jedis("59.72.63.157", 6379)) {
            String key="Club:1";
            Club club=new Club(1,"AC","米兰",new Date(),1);
            byte[] clubBytes= ProtobufIOUtil.toByteArray(club,RuntimeSchema.createFrom(Club.class),LinkedBuffer.allocate(256));
            jedis.set(key.getBytes(),clubBytes);
            byte[] resultBytes=jedis.get(key.getBytes());
            RuntimeSchema<Club> runtimeSchema=RuntimeSchema.createFrom(Club.class);
            Club club1 = runtimeSchema.newMessage();
            ProtobufIOUtil.mergeFrom(resultBytes,club1,runtimeSchema);
            logger.info(club1.toString(),logger);
            } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
    }

}
