package com.atguigu.StatePattern;

import redis.clients.jedis.Jedis;

public class start implements State {
    @Override
    public boolean choujiang(Jedis jedis) {
            //抽奖过程
        
        return true;
    }
}
