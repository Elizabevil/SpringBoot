package com.eliza.spring.library.utils.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.AlgorithmMismatchException
import com.auth0.jwt.exceptions.SignatureVerificationException
import com.auth0.jwt.exceptions.TokenExpiredException
import com.auth0.jwt.interfaces.DecodedJWT
import com.eliza.spring.library.utils.jwt.JwtTools.JWT_TOKEN
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.web.servlet.HandlerInterceptor
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


object JwtTools {
    const val JWT_TOKEN = "JWT_TOKEN"
    fun JwtCreat(Token: String, dataMap: Map<String, String>, timeSecond: Int = 1000): String {
        val builder = JWT.create()
        dataMap.forEach { (k, v) ->
            builder.withClaim(k, v)
        }
        return builder
            .withExpiresAt(Calendar.getInstance().let {
                it.add(Calendar.SECOND, timeSecond)
                it
            }.time) //过期时间
            .sign(Algorithm.HMAC256(Token))
    }

    fun Verify(Token: String, JwtString: String = ""): DecodedJWT {
        val build = JWT.require(Algorithm.HMAC256(Token)).build()
        return build.verify(JwtString)
    }

}

class JWTInterceptor : HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val hashMap = HashMap<String, Any>()

        var token = request.getHeader("token") ?: ""
        try {
            JwtTools.Verify(JWT_TOKEN, token)
            return true
            /*
            * 不报错即为成功，
            * 若报错则返回报错信息
            * */
        } catch (e: SignatureVerificationException) {
            e.printStackTrace();
            println("无效签名");
            hashMap["msg"] = "无效签名"
        } catch (e: TokenExpiredException) {
            e.printStackTrace();
            println("token过期");

            hashMap["msg"] = "token过期"
        } catch (e: AlgorithmMismatchException) {
            e.printStackTrace();
            println("token算法不一致");

            hashMap["msg"] = "token算法不一致"
        } catch (e: Exception) {
            e.printStackTrace();
            println("token无效");

            hashMap["msg"] = "token无效"
        }
        hashMap.put("state", false)
        response.also {
            it.contentType = "application/json;charset=UTF-8"
            it.writer.println(ObjectMapper().writeValueAsString(hashMap))
        }
        return false
    }
}

/**
 * 自定义拦截器
 * @author shengwu ni
 * @date 2018/08/03
 */
/*
class MyInterceptor : HandlerInterceptor {
    @Throws(java.lang.Exception::class)
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val handlerMethod = handler as HandlerMethod
        val method: Method = handlerMethod.method
        val methodName: String = method.getName()
        logger.info("====拦截到了方法：{}，在该方法执行之前执行====", methodName)
        // 返回 true 才会继续执行，返回 false 则取消当前请求
        return true
    }

    @Throws(java.lang.Exception::class)
    override fun postHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        modelAndView: ModelAndView?
    ) {
        logger.info("执行完方法之后进执行(Controller方法调用之后)，但是此时还没进行视图渲染")
    }

    @Throws(java.lang.Exception::class)
    override fun afterCompletion(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        ex: java.lang.Exception?
    ) {
        logger.info("整个请求都处理完咯，DispatcherServlet也渲染了对应的视图咯，此时我可以做一些清理的工作了")
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(MyInterceptor::class.java)
    }
}
*/
