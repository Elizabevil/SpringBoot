package com.eliza.spring.library.controller

import com.eliza.spring.library.mapper.IUserMapper
import com.eliza.spring.library.utils.LogTools
import com.eliza.spring.library.model.User
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import javax.annotation.Resource


/*-*- coding:utf-8 -*-
 * @Author  : debi
 * @Time    : 4/19/22
 * @Software: IntelliJ IDEA
 */
@Controller
class Test {
    @Resource
    lateinit var iUserMapper: IUserMapper

    @ResponseBody
    @GetMapping("/name")
    fun getUsersByName(): String {
//        Tools.LogPrintln(iUserMapper.getUsersByName("b"), 3)
        LogTools.LogPrintln()
        return "${User("as", 12)}"
    }

    @ResponseBody
    @GetMapping("/getAll")
    fun getUsersAll(): String {
//        Tools.LogPrintln(iUserMapper.getUsersByName("b"), 3)
        return "${iUserMapper.getAllUser()}"
    }

}