package com.eliza.spring.library.model


/*-*- coding:utf-8 -*-
 * @Author  : debi
 * @Time    : 4/4/22
 * @Software: IntelliJ IDEA
 */
/*
* 默认使用 无参构造，哪怕是使用 有参构造创建的对象
* */
class Stu {
    var id: Int = 0
    var name: String = ""
    var age: Int = 0
    var schoolId: Int = 0

    constructor()
    constructor(name: String, age: Int, schoolId: Int) {
        this.name = name
        this.age = age
        this.schoolId = schoolId
    }

    constructor(id: Int, name: String, age: Int, schoolId: Int) : this(name, age, schoolId) {
        this.id = id
        this.name = name
        this.age = age
        this.schoolId = schoolId
    }

    override fun toString(): String {
        return "Addr:${this::class.java.hashCode()}::Stu(id=$id, name='$name', age=$age, schoolId=$schoolId)"
    }

}