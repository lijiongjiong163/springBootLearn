package com.atguigu;


import org.junit.Test;
import redis.clients.jedis.Jedis;


public class RedisApplicationTests {
    /**
     * 试着连接一下redis，想连接要做三件事：
     * 1.redis配置文件中bind 给注释掉，那是绑定redis只能被固定ip访问
     * 2.redis配置文件中protect mode 设置成no
     * 3.防火墙放行6379端口
     *     #添加指定需要开放的端口：
     *     firewall-cmd --add-port=443/tcp --permanent
     *     #重载入添加的端口：
     *     firewall-cmd --reload
     *     #查询指定端口是否开启成功：
     *     firewall-cmd --query-port=123/tcp
     **/
    @Test
     public void contextLoads() {

        Jedis jedis = new Jedis("192.168.40.129",6379);
        String result = jedis.ping();
        System.out.println(result);
        jedis.set("a","张三");
        String a = jedis.get("a");
        System.out.println(a);
        jedis.setex("b",60,"100");
        jedis.get("b");
        for (int i = 0; i <10 ; i++) {
            jedis.incr("b");
             System.out.println(jedis.get("b"));
        }
        System.out.println(        jedis.ttl("b")
        );
            jedis.close();

    }

}
