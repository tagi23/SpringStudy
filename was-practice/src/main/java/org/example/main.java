package org.example;

import java.io.IOException;

// GET /calculate?operand1=11&operater=*&operand2=55

/**
 * HttpRequest
 *  -RequestLine (GET /calculate?operand1=11&operater=*&operand2=55 HTTP/1.1)
 *      *HttpMethod
 *      *path
 *      *queryString
 *      *protocol/version
 *  -Header
 *  -Body
 * */
public class main {
    public static void main(String[] args) throws IOException {
        new CustomWebApplicationServer (8080).start();   //서버 시작
    }
}
