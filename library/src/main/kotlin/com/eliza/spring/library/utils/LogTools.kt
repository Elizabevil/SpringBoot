package com.eliza.spring.library.utils


/*-*- coding:utf-8 -*-
 * @Author  : debi
 * @Time    : 3/6/22
 * @Software: IntelliJ IDEA
 */
object LogTools {
    /**
     *         from 0       -->     15
     *              0       -->     7
     *              8       -->     15
     *              High    -->     low

     */
    fun LogPrintln(info: Any? = null, level: Int? = null) {
        info ?: println()
        info?.let {
            level ?: getPrintln((7).toInt())(info.toString())
            level?.let { getPrintln((level % 255).toInt())(info.toString()) }
        }
    }

    fun LogPrint(info: Any? = null, level: Int? = null) {
        info ?: println()
        info?.let {
            level ?: getPrint((7).toInt())(info.toString())
            level?.let { getPrint((level % 255).toInt())(info.toString()) }
        }

    }
}


private fun getPrintln(color: Int, f: Int? = null): (info: String) -> Unit {
//            "\u001b[38;5;${color}m "
    f ?: return fun(info: String) {
        println("\u001b[38;5;${color}m$info")
        print("\u001b[0m")
    }

    return fun(info: String) {
        println("\u001b[48;5;${color}m$info")
        print("\u001b[0m")
    }
}

private fun getPrint(color: Int, f: Int? = null): (info: String) -> Unit {
//            "\u001b[38;5;${color}m "
    f ?: return fun(info: String) {
        print("\u001b[38;5;${color}m$info")
        print("\u001b[0m")
    }

    return fun(info: String) {
        print("\u001b[48;5;${color}m$info")
        print("\u001b[0m")
    }
}

