**适用于 07_redis_SecKill**

# Redis笔记

## 1.数据类型

![image-20200814111156284](assets/image-20200814111156284.png)

其实都是键值对的形式（key-value），只不过这个值可以是以上五种类型的：

- #### String：字符串

- #### set：

  底层是一个value为null的hash表（map），所以他是无序且无重复的。

- #### list：

  底层是双向链表，两端操作性强，中间操作性差；可以通过下标取数据	

- #### hash:

  相当于java中的Map<String,String>

- #### zset:

  无重复且有序的一个set，底层是一个key为vlaue，value为score的hash（Map），所以它无重复（map中key不能重复呀），且有序（用score的大小进行排序，score可以重复）

  