package com.eliza.spring.library.model


/*-*- coding:utf-8 -*-
 * @Author  : debi
 * @Time    : 4/11/22
 * @Software: IntelliJ IDEA
 */
class School {
    var id: Int = 0
    var name: String = ""
    var addr: String = ""
    var years: Int = 0

    constructor()
    constructor(id: Int, name: String, addr: String, years: Int) : this(name, addr, years) {
        this.id = id
        this.name = name
        this.addr = addr
        this.years = years
    }

    constructor(name: String, addr: String, years: Int) : this() {
        this.name = name
        this.addr = addr
        this.years = years
    }

    override fun toString(): String {
        return "Addr:${this::class.java.hashCode()}::School(id=$id, name='$name', addr='$addr', years=$years)"
    }

}