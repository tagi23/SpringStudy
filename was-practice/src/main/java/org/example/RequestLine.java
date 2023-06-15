package org.example;

import java.util.Objects;

public class RequestLine {
    /**
     * GET /calculate?operand1=11&operater=*&operand2=55 HTTP/1.1
     */
    private final String method;   //get
    private final String urlPath;    // /calculate
    private QueryStrings queryString;  // operand1=11&operater=*&operand2=55


    public RequestLine(String method, String urlPath,String queryString) {
        this.method = method;
        this.urlPath = urlPath;
        this.queryString = new QueryStrings(queryString);
    }
    public RequestLine(String requestLine) {
        String[] tokens = requestLine.split(" ");  // RequestLine 에 필요한 것들을 split하여 배열에 다음
        this.method = tokens[0]; //method 담기
        String[] urlPathTokens = tokens[1].split("\\?"); //Path와queryString을 split하여 나눔
        this.urlPath = urlPathTokens[0]; //urlPath 담기
        if (urlPathTokens.length == 2){
            this.queryString = new QueryStrings(urlPathTokens[1]);   //queryString 담기
        }
    }

    public boolean isGetRequest() {
        return "GET".equals(this.method);  //get이면 true를 반환
    }

    public boolean matchPath(String path) {  //path가 같으면 true를 반환
        return urlPath.equals(path);
    }

    public QueryStrings getQueryStrings() {
        return this.queryString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestLine that = (RequestLine) o;
        return Objects.equals(method, that.method) && Objects.equals(urlPath, that.urlPath) && Objects.equals(queryString, that.queryString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(method, urlPath, queryString);
    }

}
