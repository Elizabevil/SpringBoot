package com.eliza.spring.library.model


/*-*- coding:utf-8 -*-
 * @Author  : debi
 * @Time    : 4/19/22
 * @Software: IntelliJ IDEA
 */
class User() {
    var name = ""
    var age = 0

    constructor(name: String, age: Int) : this() {
        this.name = name
        this.age = age
    }

    override fun toString(): String {
        return "Addr:${this::class.java.hashCode()}::User(name='$name', age=$age)"
    }


}