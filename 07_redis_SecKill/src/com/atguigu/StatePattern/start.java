package com.atguigu.StatePattern;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;
import java.util.Random;

public class start implements State {
    private Context context;

    private String kcKey;
    private String userKey;
    private String uid;

    public start(Context context, String kcKey, String userKey,String uid) {
        this.context = context;
        this.kcKey = kcKey;
        this.userKey = userKey;
        this.uid = uid;
    }



    @Override
    public boolean choujiang(Jedis jedis) {

        //开启事务
        Transaction transaction = jedis.multi();
        //用事务对象去操作

        transaction.decr(kcKey);
        transaction.sadd(userKey,uid);
        List<Object> list = transaction.exec();
        if (list==null||list.size()==0){
            System.out.println("秒杀失败");
            return false;
        }

        System.out.println("恭喜秒杀成功了bitch");
        System.out.println("库存还有："+jedis.get(kcKey)+"个");

        if (Integer.parseInt(jedis.get(kcKey))<=0){
            context.setState(new over());
            context.doAction(jedis);
        }
        jedis.close();
        return true;
    }
}
