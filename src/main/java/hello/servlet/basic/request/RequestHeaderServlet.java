package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        printStartLine(request);
        printHeader(request);
        printHeaderUtils(request);
        printEtc(request);
        /* (결과)
        * ----- REQUEST-LINE - start ----
            request.getMethod() = GET
            request.getProtocol() = HTTP/1.1
            request.getScheme() = http
            request.getRequestURL() = http://localhost:8080/request-header
            request.getRequestURI() = /request-header
            request.getQueryString() = null
            request.isSecure() = false
        ----- REQUEST-LINE - end ----
        *
        *
        *
        * */

    }

    private void printStartLine(HttpServletRequest request) {
        System.out.println("----- REQUEST-LINE - start ----");

        System.out.println("request.getMethod() = " + request.getMethod());
        System.out.println("request.getProtocol() = " + request.getProtocol());
        System.out.println("request.getScheme() = " + request.getScheme());
        // http://localhost:8080/request-header
        System.out.println("request.getRequestURL() = " + request.getRequestURL());
        // /request-header
        System.out.println("request.getRequestURI() = " + request.getRequestURI());
        // username=hi
        System.out.println("request.getQueryString() = " + request.getQueryString());
        System.out.println("request.isSecure() = " + request.isSecure());
        System.out.println("----- REQUEST-LINE - end ----");
        System.out.println();
    }

    // Header 모든 정보
    private void printHeader(HttpServletRequest request) {
        System.out.println("---- Headers - start -----");

        request.getHeaderNames().asIterator()
                .forEachRemaining(headerName  -> System.out.println(headerName + ": " + headerName));

        System.out.println("---- Headers - end -----");
        System.out.println();
    }

    // Header 편리한 조회
    private void printHeaderUtils(HttpServletRequest request) {
        System.out.println("---- Header 편의 조회 start ----");
        System.out.println("[Host 편의 조회]");
        System.out.println("request.getServerName() = " +
                request.getServerName()); //localhost
        System.out.println("request.getServerPort() = " +
                request.getServerPort()); //8080
        System.out.println();

        System.out.println("[Accept-Language 편의 조회]");
        request.getLocales().asIterator()
                .forEachRemaining(locale -> System.out.println("locale = " + locale));
        //?
        /*
        * locale = ko
            locale = en
            locale = en_US
            locale = ko
            locale = en
            locale = en_US
        * */
        System.out.println("request.getLocale() = "+ request.getLocale()); //ko
        // 가장 높은것 꺼내고 싶으면..
        System.out.println();

        System.out.println("[cookie 편의 조회]");
        if(request.getCookies() != null) {
            for(Cookie cookie : request.getCookies()) {
                System.out.println(cookie.getName() + ": " + cookie.getValue());
            }
        }
        System.out.println();

        System.out.println("[Content 편의 조회]");
        System.out.println("request.getContentType() = " +
                request.getContentType()); // 왜 null 이지
        // get 방식이라서. 컨텐트를 거의 안보내기때문에.. postman으로 가능
        System.out.println("request.getContentLength() = " +
                request.getContentLength());
        System.out.println("request.getCharacterEncoding() = " +
                request.getCharacterEncoding());

        System.out.println("---- Header 편의 조회 end ----");
        System.out.println();


    }

    // 기타 정보
    private void printEtc(HttpServletRequest request) {
        System.out.println("---- 기타 조회 start ----");

        System.out.println("[Remote 정보]");
        System.out.println("request.getRemoteHost() = " +
                request.getRemoteHost()); // 0:0:0:0:0:0:0:1
        System.out.println("request.getRemoteAddr() = " +
                request.getRemoteAddr()); // 0:0:0:0:0:0:0:1
        System.out.println("request.getRemotePort() = " +
                request.getRemotePort()); // 61414 ?이게모지
        System.out.println();

        System.out.println("[Local 정보]");
        System.out.println("request.getLocalName() = " +
                request.getLocalName()); // 0:0:0:0:0:0:0:1
        System.out.println("request.getLocalAddr() = " +
                request.getLocalAddr()); // 0:0:0:0:0:0:0:1
        System.out.println("request.getLocalPort() = " +
                request.getLocalPort());

        System.out.println("---- 기타 조회 end ----");
        System.out.println();


        /*
        * HTTP 요청 데이터 - 개요
        * HTTP 요청 메시지를 통해 클라이언트에서 서버로 데이터를 전달하는 방법을 알아보자.
        *
        * 주로 3가지 방법
        * GET - 쿼리 파라미터
        * /url?username=hello&age=20
        * 메시지 바디 없이, URL의 쿼리 파라미터에 데이터를 포함해서 전달
        * 예) 검색, 필터, 페이징 등에서 많이 사용하는 방식
        *
        * POST - HTML Form
        * content-type: application/x-www-form-urlencoded
        * 메시지 바디에 쿼리 파라미터 형식으로 전달 username=hello&age=20
        * 예) 회원가입, 상품주문, HTML Form 사용
        *
        * HTTP message body 에 데이터를 직접 담아서 요청
        * HTTP API에서 주로 사용, JSON, XML, TEXT
        * 데이터 형식은 주로 JSON 사용
        * POST, PUT, PATCH
        *
        *
        *
        *
        * */


    }
}
