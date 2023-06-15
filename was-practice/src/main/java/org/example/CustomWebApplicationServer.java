package org.example;

import ch.qos.logback.classic.spi.PackagingDataCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.example.calculate.*;

public class CustomWebApplicationServer {

    private final int port;

    private  final ExecutorService executorService = Executors.newFixedThreadPool(10);  // 쓰레드풀 생성

    private static final Logger logger = LoggerFactory.getLogger(CustomWebApplicationServer.class);  //logger 생성

    public CustomWebApplicationServer(int port) {
        this.port = port;   //포트 전달받기
    }

    public void start() throws IOException {   //start가 호출하면 웹애플리케이션이 동작하는 형태
        try(ServerSocket serverSocket = new ServerSocket(port)) { //해당포트로 서버소켓 생성
            logger.info("[CustomWebApplicationServer] started {} port",port);  //logger 시작됐다는 로그 남기기

            Socket clientSocket;   //클라이언트소켓 생성
            logger.info("[CustomWebApplicationServer] waiting for client");  //클라이언트를 기다린다는 로그 남기기

            while ((clientSocket = serverSocket.accept()) != null){   //서버소켓이 해당 클라이언트를 기다리고 클라이언트가 들어오면은 클라이언트소켓이 생성된다. 그러면 NotNULL이기에 While문이 작동됨
                logger.info("[CustomWebApplicationServer] client connected");  //연결됐다는 로그 남기기

                /**
                 * Step2 - 사용자 요청이 들어올 때마다 Thread를 새로 생성해서 사용자 요청을 처리하도록 한다.
                 */
                // new Thread(new ClientRequestHandler(clientSocket)).start();  //클라이언트 소켓을 넣고 별도의 쓰레드를 생성한다.

                /**
                 * Step3 - ThreadPool을 사용하여 안정적인 서비스를 높인다
                 */
                executorService.execute(new ClientRequestHandler(clientSocket));


            }
        }
    }
}
