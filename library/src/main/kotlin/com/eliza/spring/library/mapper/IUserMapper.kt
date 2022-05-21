package com.eliza.spring.library.mapper


/*-*- coding:utf-8 -*-
 * @Author  : debi
 * @Time    : 4/19/22
 * @Software: IntelliJ IDEA
 */
import com.eliza.spring.library.model.User
import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select


/*
*
* mapper 映射文件中 namespace 必须与对应的 mapper 接口的完全限定名一致。
mapper 映射文件中 statement 的 id 必须与 mapper 接口中的方法的方法名一致
mapper 映射文件中 statement 的 parameterType 指定的类型必须与 mapper 接口中方法的参数类型一致。
mapper 映射文件中 statement 的 resultType 指定的类型必须与 mapper 接口中方法的返回值类型一致。
* */
@Mapper
interface IUserMapper {
    /** xml 配置文件
     *    yml 中设置
     *          mybatis :
     *               configuration :
     *                       mapper-locations: classpath:mybatis/mapper/ xxx.xml
     */

    fun getUserById(id: Int): User

    @Select("SELECT * FROM Spring.SprUser   ")
    fun getAllUser(): List<User>

    /**
     * 直接使用注解
     * */
    @Select("SELECT t.* FROM Spring.SprUser t where name = #{name} ")
    fun getUsersByName(name: String): List<User>

    //    简单方法就写在注解上。复杂的就写在配置文件里
    @Insert("insert into Spring.SprUser(name, age) values (#{name}, #{age})")
    fun insertUser(user: User)

    @Delete("DELETE FROM Spring.SprUser where name=#{name} ")
    fun deleteUser(name: String)


}