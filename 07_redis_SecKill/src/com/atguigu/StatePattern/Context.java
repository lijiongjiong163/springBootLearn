package com.atguigu.StatePattern;

import redis.clients.jedis.Jedis;

public class Context {
    private State state;

    public boolean doAction(Jedis jedis) {
        return state.choujiang(jedis);
    }
    public void setState(State state){
        this.state=state;
    }
}
