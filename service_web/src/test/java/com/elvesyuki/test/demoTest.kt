package com.elvesyuki.test



/**
 * @ClassName AdminOrgController
 * @Description
 * @Author luohuan
 * @Date 2021/8/4 下午3:02
 */


fun main(args: Array<String>) {    // 包级可见的函数，接受一个字符串数组作为参数


}

data class Person(val name: String, val age: Int)

fun getPeople(): List<Person> {
    return listOf(Person("Alice", 29), Person("Bob", 31))
}

fun comparePeople(): Boolean {
    val p1 = Person("Alice", 29)
    val p2 = Person("Alice", 29)
    return p1 == p2  // should be true
}