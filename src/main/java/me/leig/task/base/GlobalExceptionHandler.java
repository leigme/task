package me.leig.task.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Map<String, String> errorMsg(HttpServletRequest request, Exception e) {
        Map<String, String> result = new HashMap<>();
        result.put("errorCode", "9000");
        result.put("errorMessage", "服务响应失败");
        log.error("异常请求: {}", request.getRequestURL().toString());
        log.error("异常: ", e);
        return result;
    }
}
