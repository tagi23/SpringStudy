package org.example;

import org.example.calculate.Caculator;
import org.example.calculate.positiveNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientRequestHandler implements Runnable{
    private static final Logger logger = LoggerFactory.getLogger(CustomWebApplicationServer.class);  //logger 생성

    private final Socket clientSocket;

    public ClientRequestHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        /**
         * Step1 - 사용자 요청을 메인 Thread가 처리하도록 한다.
         * Step2 - 사용자 요청이 들어올 때마다 Thread를 새로 생성해서 사용자 요청을 처리하도록 한다.
         */
        logger.info("[ClientRequestHandler] new client {} started",Thread.currentThread().getName());
        try(InputStream in = clientSocket.getInputStream(); OutputStream out = clientSocket.getOutputStream()) { //clientSocket통해서 Stream들을 염
            BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8)); //InputStream을 Reader로 바꿈
            DataOutputStream dos = new DataOutputStream(out);   //DataOutputStream도 한번 더 감싸줌


            HttpRequest httpRequest = new HttpRequest(br);    //HttpRequest에 클라이언트로부터 입력된것을 BufferedReader를 전달함

            // GET /calculate?operand1=11&operater=*&operand2=55
            if (httpRequest.isGetRequest() && httpRequest.matchPath("/calculate")) {  //get요청이면서 Path가 같은지?
                QueryStrings queryStrings = httpRequest.getQueryStrings();  //쿼리스트링 가져오기

                int operand1 = Integer.parseInt(queryStrings.getValue("operand1"));
                String operator = queryStrings.getValue("operater");
                int operand2 = Integer.parseInt(queryStrings.getValue("operand2"));

                int result = Caculator.calculator(new positiveNumber(operand1), operator, new positiveNumber(operand2));
                byte[] body = String.valueOf(result).getBytes();

                HttpResponse response = new HttpResponse(dos);
                response.response200Header("application/json", body.length);
                response.responseBody(body);
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
