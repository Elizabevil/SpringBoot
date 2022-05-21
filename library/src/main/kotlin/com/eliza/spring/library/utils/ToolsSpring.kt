package com.eliza.spring.library.utils

import org.springframework.boot.SpringApplication
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.support.ClassPathXmlApplicationContext


class ToolsSpring {
    lateinit var beanPath: String
    lateinit var ac: ConfigurableApplicationContext
    lateinit var springApp: Class<*>

    constructor(springApp: Class<*>) {
        this.springApp = springApp
        ac = SpringApplication.run(springApp) as ConfigurableApplicationContext
    }

    constructor(beanPath: String) {
        beanPath.let { this.beanPath = beanPath }
        ac = ClassPathXmlApplicationContext(beanPath)

    }


    fun getSpringInfo() {
        LogTools.LogPrintln("配置文件中的对象数量: ${ac.beanDefinitionCount}", 2)
        ac.beanDefinitionNames.forEach { it ->
            LogTools.LogPrintln(it, 5)
        }

    }

    fun getObj(obj: String): Any {
        return ac.getBean(obj)
    }

    fun <T : Any>getObj(obj: Class<T>): T {
//        java.lang.Class<T> aClass
        return ac.getBean(obj)
    }
}