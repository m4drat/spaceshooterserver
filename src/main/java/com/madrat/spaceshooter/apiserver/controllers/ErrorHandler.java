package com.madrat.spaceshooter.apiserver.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
public class ErrorHandler implements ErrorController {

    @RequestMapping("/error")
    @ResponseBody
    public Error handleError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        return new Error("ErrorCode", statusCode);
    }

    private class Error {
        HashMap<String, Integer> error = new HashMap<>();

        public Error(String msg, int code) {
            error.put(msg, code);
        }

        public HashMap<String, Integer> getError() {
            return error;
        }
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}