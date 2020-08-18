package com.atguigu.StatePattern;

import redis.clients.jedis.Jedis;

public interface State {
    public boolean choujiang(Jedis jedis);
}
