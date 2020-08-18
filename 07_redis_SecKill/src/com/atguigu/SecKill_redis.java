package com.atguigu;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.atguigu.StatePattern.Context;
import com.atguigu.StatePattern.notStart;
import com.atguigu.StatePattern.over;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.LoggerFactory;

import ch.qos.logback.core.rolling.helper.IntegerTokenConverter;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.Transaction;



public class SecKill_redis {
	
	private static final  org.slf4j.Logger logger =LoggerFactory.getLogger(SecKill_redis.class) ;

	public static void main(String[] args) {
 
 
		Jedis jedis =new Jedis("192.168.223.132",6379);
		
		System.out.println(jedis.ping());
	 
		jedis.close();
	}
	
	public static boolean doSecKill(String uid,String prodid) throws IOException {
		
		//拼接key
		String kcKey = "Seckill:" + prodid + ":kc";
		String userKey = "Seckill:" + prodid + ":user";

		Jedis jedis = new Jedis("192.168.40.129",6379);
		//由于多个情况，我们使用状态模式去优化一下它(其实这里并不适用。。。)
		Context context = new Context();
		//情况一:秒杀还未开始
		if(){
			context.setState(new notStart());
			return context.doAction(jedis);
		}

		//情况二：这孙子秒杀过了
		else if(jedis.sismember(userKey,uid)){
			context.setState(new notStart());
			return context.doAction(jedis);
		}
		//情况三：秒杀成功

		return false;

	}
	
	
	
	
	
	
}
















