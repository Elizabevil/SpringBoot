package com.eliza.spring.library.utils.redis

import org.springframework.data.redis.core.RedisTemplate
import java.util.concurrent.TimeUnit


/**
 * Redis工具类，使用之前请确保RedisTemplate成功注入
 *
 * @author ye17186
 * @version 2019/2/22 10:48
 */
class RedisUtils(val redisTemplate: RedisTemplate<String, String>) {

//    @Autowired
//    private val redisTemplate: RedisTemplate<String, String>? = null

    /**
     * 设置有效时间
     *
     * @param key Redis键
     * @param timeout 超时时间
     * @return true=设置成功；false=设置失败
     */
    fun expire(key: String, timeout: Long, unit: TimeUnit = TimeUnit.SECONDS): Boolean {

        val ret = redisTemplate.expire(key, timeout, unit)
        return ret
    }

    /**
     * 删除单个key
     *
     * @param key 键
     * @return true=删除成功；false=删除失败
     */
    fun del(key: String): Boolean {
        val ret = redisTemplate.delete(key)
        return ret
    }

    /**
     * 删除多个key
     *
     * @param keys 键集合
     * @return 成功删除的个数
     */
    fun del(keys: Collection<String>?): Long {
        val ret = redisTemplate.delete(keys!!)
        return ret ?: 0
    }

    /**
     * 存入普通对象
     *
     * @param key Redis键
     * @param value 值
     */
    operator fun set(key: String, value: String) {
        redisTemplate.opsForValue()[key, value, 1] = TimeUnit.MINUTES
    }
    // 存储普通对象操作
    /**
     * 存入普通对象
     *
     * @param key 键
     * @param value 值
     * @param timeout 有效期，单位秒
     */
    operator fun set(key: String, value: String, timeout: Long) {
        redisTemplate.opsForValue()[key, value, timeout] = TimeUnit.SECONDS
    }

    /**
     * 获取普通对象
     *
     * @param key 键
     * @return 对象
     */
    operator fun get(key: String?): String? {
        return redisTemplate.opsForValue()[key!!]
    }
    // 存储Hash操作
    /**
     * 往Hash中存入数据
     *
     * @param key Redis键
     * @param hKey Hash键
     * @param value 值
     */
    fun hPut(key: String, hKey: String, value: String) {
        redisTemplate.opsForHash<String, String>().put(key, hKey, value)
    }

    /**
     * 往Hash中存入多个数据
     *
     * @param key Redis键
     * @param values Hash键值对
     */
    fun hPutAll(key: String, values: Map<String, String>?) {
        redisTemplate.opsForHash<String, String>().putAll(key, values!!)
    }

    /**
     * 获取Hash中的数据
     *
     * @param key Redis键
     * @param hKey Hash键
     * @return Hash中的对象
     */
    fun hGet(key: String, hKey: String?): String? {
        return redisTemplate.opsForHash<String, String>()[key, hKey!!]
    }

    /**
     * 获取多个Hash中的数据
     *
     * @param key Redis键
     * @param hKeys Hash键集合
     * @return Hash对象集合
     */
    fun hMultiGet(key: String, hKeys: Collection<String>?): List<String> {
        return redisTemplate.opsForHash<String, String>().multiGet(key, hKeys!!)
    }
    // 存储Set相关操作
    /**
     * 往Set中存入数据
     *
     * @param key Redis键
     * @param values 值
     * @return 存入的个数
     */
    fun sSet(key: String, vararg values: String?): Long {
        val count = redisTemplate.opsForSet().add(key, *values)
        return count ?: 0
    }

    /**
     * 删除Set中的数据
     *
     * @param key Redis键
     * @param values 值
     * @return 移除的个数
     */
    fun sDel(key: String, vararg values: String?): Long {
        val count = redisTemplate.opsForSet().remove(key, *values)
        return count ?: 0
    }
    // 存储List相关操作
    /**
     * 往List中存入数据
     *
     * @param key Redis键
     * @param value 数据
     * @return 存入的个数
     */
    fun lPush(key: String, value: String): Long {
        val count = redisTemplate.opsForList().rightPush(key, value)
        return count ?: 0
    }

    /**
     * 往List中存入多个数据
     *
     * @param key Redis键
     * @param values 多个数据
     * @return 存入的个数
     */
    fun lPushAll(key: String, values: Collection<String>): Long {
        val count: Long? = redisTemplate.opsForList().rightPushAll(key, values)
        return count ?: 0
    }

    /**
     * 往List中存入多个数据
     *
     * @param key Redis键
     * @param values 多个数据
     * @return 存入的个数
     */
    fun lPushAll(key: String, vararg values: String?): Long {
        val count = redisTemplate.opsForList().rightPushAll(key, *values)
        return count ?: 0
    }

    /**
     * 从List中获取begin到end之间的元素
     *
     * @param key Redis键
     * @param start 开始位置
     * @param end 结束位置（start=0，end=-1表示获取全部元素）
     * @return List对象
     */
    fun lGet(key: String, start: Int, end: Int): List<String>? {
        return redisTemplate.opsForList().range(key, start.toLong(), end.toLong())
    }
}