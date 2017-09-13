package cn.imexue.ec.common.configuration.redis;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.session.data.redis.config.ConfigureRedisAction;

@Configuration
public class RedisConfig{  
  
	@Value("${verify.timeout.second}")
	private Long verifyTimeoutSecond;
	
    @Bean
    public RedisTemplate<?, ?> redisTemplate(RedisConnectionFactory connectionFactory) {  
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();  
        template.setConnectionFactory(connectionFactory);  
        template.setDefaultSerializer(stringRedisSerializer());
        return template;  
    }  
  
    @Bean
    public CacheManager cacheManager(RedisTemplate<String, Object> template){
    	RedisCacheManager cacheManager = new RedisCacheManager(template);
    	cacheManager.setDefaultExpiration(3000);
    	Map<String, Long> expires = new HashMap<>();
    	expires.put("VerifyLoginCache", verifyTimeoutSecond);
    	cacheManager.setExpires(expires);
    	
    	
    	return cacheManager;
    }
    
    @Bean
    @ConditionalOnProperty(havingValue="true",name="redis.noop")
    public static ConfigureRedisAction configureRedisAction() {
        return ConfigureRedisAction.NO_OP;
    }
    
    @Bean
    public RedisSerializer<String> stringRedisSerializer(){
    	return new RedisSerializer<String>() {

			@Override
			public byte[] serialize(String t) throws SerializationException {
				if(t==null){
					return null;
				}
				return t.getBytes();
			}

			@Override
			public String deserialize(byte[] bytes)
					throws SerializationException {
				if(bytes==null){
					return null;
				}
				return new String(bytes);
			}
		};
    }
}  
