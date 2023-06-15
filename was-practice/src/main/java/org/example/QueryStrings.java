package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QueryStrings {
    //operand1=11 & operater=* & operand2=55
    private List<QueryString> quertStrings = new ArrayList<>();   //일급컬렉터
    public QueryStrings(String QueryStringLine) {
        String[] queryStringToken = QueryStringLine.split("&");
        Arrays.stream(queryStringToken)
                .forEach(queryStrings -> {
                    String[] values = queryStrings.split("=");
                    if(values.length !=2){
                        throw new IllegalArgumentException("잘못된 QueryString 포맷을 가진 문자열입니다.");
                    }
                    quertStrings.add(new QueryString(values[0],values[1]));
                });
//        for (String qr: queryStringToken) {
//            String values[] = qr.split("=");
//            if (values.length !=2){
//                throw new IllegalArgumentException("잘못된 QueryString 포맷을 가진 문자열입니다.");
//            }
//            quertStrings.add(new QueryString(values[0],values[1]));
//        }
    }

    public String getValue(String key) {
        return quertStrings.stream()
                .filter(queryString -> queryString.exists(key)) // queryString에 키가 존재하는지
                .map(QueryString::getValue)  //존재한다면 QueryString에서 키에 해당하는 value값을 반환
                .findFirst()  // 첫번째값
                .orElse(null);  //없으면 null 반환
    }
}
