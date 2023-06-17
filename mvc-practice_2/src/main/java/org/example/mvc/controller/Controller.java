package org.example.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.http.HttpRequest;

public interface Controller {
    String handleRequest(HttpServletRequest request , HttpServletResponse response) throws Exception;
}
