package com.eliza.spring.redis.controller

import com.eliza.spring.library.utils.redis.RedisOperator
import com.eliza.spring.library.utils.redis.RedisUtils
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.annotation.Resource


@ComponentScan
@RestController
@RequestMapping("/redis")
class RedisController {

    @Resource
    private val redisTemplate: RedisTemplate<String, String>? = null

    @Resource
    private lateinit var stringRedisTemplate: StringRedisTemplate

    @GetMapping("/set")
    fun setUser() {
        redisTemplate!!.opsForValue().set("name", "Asas")
    }

    @GetMapping("/get")
    fun getUser() {
        println(redisTemplate!!.opsForValue()["name"])
    }


    @GetMapping("/set1")
    fun setUse1r() {
        redisTemplate!!.opsForValue().set("name", "set1")
    }

    @GetMapping("/get1")
    fun getUse1r() {
        println(RedisOperator(stringRedisTemplate).get("name"))
    }

    @GetMapping("/set2")
    fun setUse2r() {
        redisTemplate?.let { RedisUtils(it).set("name", "set2") }
    }

    @GetMapping("/get2")
    fun getUse2r() {
        println(redisTemplate?.let { RedisUtils(it).get("name") })


    }
}