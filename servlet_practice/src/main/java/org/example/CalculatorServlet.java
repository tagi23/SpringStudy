package org.example;

import org.example.calculator.domain.Caculator;
import org.example.calculator.domain.positiveNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/calculate")
public class CalculatorServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(CalculatorServlet.class);
    //서블릿 객체는 싱글톤으로 관리(인스턴스하나만 생성하고 공유하는 방식)

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("service");
        int operand1 = Integer.parseInt(request.getParameter("operand1"));
        String operator = request.getParameter("operator");
        int operand2 = Integer.parseInt(request.getParameter("operand2"));

        log.info(String.valueOf(operand1));
        log.info(operator);
        log.info(String.valueOf(operand2));

        int result = Caculator.calculator(new positiveNumber(operand1),operator,new positiveNumber(operand2));

        PrintWriter writer =  response.getWriter();
        writer.println(result);
    }



//    @Override
//    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {   서블릿 인터페이스와 제네릭 서블릿을 활용할때 서비스를 구현해줘야함
//        log.info("service");
//        int operand1 = Integer.parseInt(request.getParameter("operand1"));
//        String operator = request.getParameter("operator");
//        int operand2 = Integer.parseInt(request.getParameter("operand2"));
//
//        log.info(String.valueOf(operand1));
//        log.info(operator);
//        log.info(String.valueOf(operand2));
//
//
//        int result = Caculator.calculator(new positiveNumber(operand1),operator,new positiveNumber(operand2));
//
//        PrintWriter writer =  response.getWriter();
//        writer.println(result);
//    }
}
