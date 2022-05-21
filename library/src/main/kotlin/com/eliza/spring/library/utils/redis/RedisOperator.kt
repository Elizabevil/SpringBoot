package com.eliza.spring.library.utils.redis

import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit


/**
 *
 * @Title: RedisOperator.java
 * @Package com.weiz.util
 * @Description: 使用redisTemplate的操作实现类 Copyright: Copyright (c) 2016
 *
 * @author weiz
 * @date 2017年9月29日 下午2:25:03
 * @version V1.0
 */
@Component
class RedisOperator(var stringRedisTemplate: StringRedisTemplate) {
    // @Autowired
    //    private RedisTemplate<String, Object> redisTemplate;
//    StringRedisTemplate 主要给我们提供字符串操作
    // Key（键），简单的key-value操作
    /**
     * 实现命令：expire 设置过期时间，单位秒
     *
     * @param key
     * @return
     */
    fun expire(key: String, timeout: Long) {
        stringRedisTemplate.expire(key, timeout, TimeUnit.SECONDS)
    }

    /**
     * 实现命令：TTL key，以秒为单位，返回给定 key的剩余生存时间(TTL, time to live)。
     *
     * @param key
     * @return
     */
    fun ttl(key: String): Long {
        return stringRedisTemplate.getExpire(key)
    }

    /**
     * 实现命令：INCR key，增加key一次
     *
     * @param key
     * @return
     */
    fun incr(key: String, delta: Long): Long {
        return stringRedisTemplate.opsForValue().increment(key, delta)!!
    }

    /**
     * 实现命令：KEYS pattern，查找所有符合给定模式 pattern的 key
     */
    fun keys(pattern: String): Set<String> {
        return stringRedisTemplate.keys(pattern)
    }

    /**
     * 实现命令：DEL key，删除一个key
     *
     * @param key
     */
    fun del(key: String) {
        stringRedisTemplate.delete(key)
    }
    // String（字符串）
    /**
     * 实现命令：SET key value，设置一个key-value（将字符串值 value关联到 key）
     *
     * @param key
     * @param value
     */
    operator fun set(key: String, value: String) {
        stringRedisTemplate.opsForValue().set(key, value)
    }

    /**
     * 实现命令：SET key value EX seconds，设置key-value和超时时间（秒）
     *
     * @param key
     * @param value
     * @param timeout
     * （以秒为单位）
     */
    operator fun set(key: String, value: String, timeout: Long) {
        stringRedisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS)
    }

    /**
     * 实现命令：GET key，返回 key所关联的字符串值。
     *
     * @param key
     * @return value
     */
    operator fun get(key: String): String? {
        return stringRedisTemplate.opsForValue().get(key)
    }
    // Hash（哈希表）
    /**
     * 实现命令：HSET key field value，将哈希表 key中的域 field的值设为 value
     *
     * @param key
     * @param field
     * @param value
     */
    fun hset(key: String, field: String, value: Any) {
        stringRedisTemplate.opsForHash<Any, Any>().put(key, field, value)
    }

    /**
     * 实现命令：HGET key field，返回哈希表 key中给定域 field的值
     *
     * @param key
     * @param field
     * @return
     */
    fun hget(key: String, field: String): Any? {
        return stringRedisTemplate.opsForHash<Any, Any>().get(key, field)
    }

    /**
     * 实现命令：HDEL key field [field ...]，删除哈希表 key 中的一个或多个指定域，不存在的域将被忽略。
     *
     * @param key
     * @param fields
     */
    fun hdel(key: String, vararg fields: Any?) {
        stringRedisTemplate.opsForHash<Any, Any>().delete(key, *fields)
    }

    /**
     * 实现命令：HGETALL key，返回哈希表 key中，所有的域和值。
     *
     * @param key
     * @return
     */
    fun hgetall(key: String): Map<Any, Any> {
        return stringRedisTemplate.opsForHash<Any, Any>().entries(key)
    }
    // List（列表）
    /**
     * 实现命令：LPUSH key value，将一个值 value插入到列表 key的表头
     *
     * @param key
     * @param value
     * @return 执行 LPUSH命令后，列表的长度。
     */
    fun lpush(key: String, value: String): Long? {
        return stringRedisTemplate.opsForList().leftPush(key, value)
    }

    /**
     * 实现命令：LPOP key，移除并返回列表 key的头元素。
     *
     * @param key
     * @return 列表key的头元素。
     */
    fun lpop(key: String): String? {
        return stringRedisTemplate.opsForList().leftPop(key)
    }

    /**
     * 实现命令：RPUSH key value，将一个值 value插入到列表 key的表尾(最右边)。
     *
     * @param key
     * @param value
     * @return 执行 LPUSH命令后，列表的长度。
     */
    fun rpush(key: String, value: String): Long? {
        return stringRedisTemplate.opsForList().rightPush(key, value)
    }
}