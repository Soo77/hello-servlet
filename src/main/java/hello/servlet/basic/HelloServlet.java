package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        //http://localhost:8080/hello?username=shim
        /*
        *  쿼리파라미터
        *  서블릿은 쿼리파라미터를 편하게 읽도록 지원해줌
        * */
        String username = request.getParameter("username");
        System.out.println("username = " + username);

        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("hello "+username);

        /*
        * @WebServlet 서블릿 애노테이션
        * name : 서블릿 이름
        * urlPatterns : URL 매핑 (두개 겹치면 안됨)
        *
        * HTTP 요청을 통해 매핑된 URL이 호출되면 서블릿 컨테이너는 다음 메서드를 실행한다.
        * protected void service(HttpServletRequest, HttpServletResponse reponse)
        *
        * application.properties
        * logging.level.org.apache.coyote.http11=debug
        * 요청해보면 서버가 받은 HTTP 요청 메세지를 출력하는 것 확인 가능
        * 참고: 운영서버에 이렇게 모든 요청 정보를 다 남기면 성능저하 발생할 수 있음. 개발단계에만 적용하자.
        *
        * 서블릿 컨테이너 동작 방식 설명
        * 스프링 부트가 실행하면서 내장 톰캣 서버를 띄워줍니다.
        * 톰캣 서버는 내부에 서블릿 컨테이너 기능을 가지고 있음.
        * 이 서블릿 컨테이너를 통해서 그 서블릿을 다 생성을 해줍니다.
        * 서블릿 컨테이너 안에 헬로 서블릿이 생성되고요.
        * 우리가 요청을하면
        * GET /hello?username=world HTTP/1.1
        * Host: localhost:8080
        * 웹브라우저가 이렇게 http 메세지를 만들어서 저희쪽에 던져줌.
        *
        * 웹애플리케이션 서버의 요청 응답 구조
        * resquestResponse 객체를 만들어서(HTTP 요청 메세지를 기반으로 생성) 싱글톤으로 떠있는 헬로 서블릿을 호출해줌.
        * 서비스 메서드를 호출하면서 requestResponse를 딱 넘겨주는 거에요.
        * 그리고 내가 필요한 작업을 하고 예를들어 response 데이터에 컨텐츠타입에 대한 정보를
        * hello world라는 메세지를 넣게되면
        * 종료되고 나가면서 WAS 서버가 response 정보를 가지고 HTTP 응답 메세지를 만들어서 반환해줌.
        * [HTTP 응답]
        * HTTP/1.1 200 OK
        * Content-Type: text/plain;charset=utf-8
        * Content-Length: 11
        *
        * hello world
        *
        * 그러면 웹브라우저에서 hello world라고 볼수있다.
        *
        * HttpServletRequest 개요
        * 역할?
        * HTTP 요청 메시지를 개발자가 직접 파싱해서 사용해도 되지만, 매우 불편하다. 서블릿은 개발자가 HTTP 요청 메시지를
        * 편리하게 사용할 수 있도록 개발자 대신에 HTTP 요청 메시지를 파싱한다. 그리고 그 결과를 HttpServletRequest 객체에
        * 담아서 제공한다.
        *
        * HttpServletRequest를 사용하면 다음과 같은 HTTP 요청 메시지를 편리하게 조회할 수 있다.
        *
        * [HTTP 요청 메시지]
        * POST /save HTTP/1.1
        * Host: localhost:8080
        * Content-Type: application/x-www-form-urlencoded
        *
        * username=shim&age=29
        *
        * START LINE
        * - HTTP 메소드
        * - URL
        * - 쿼리 스트링
        * - 스키마, 프로토콜
        *
        * 헤더
        * - 헤더 조회
        *
        * 바디
        * - form 파라미터 형식 조회
        * - message body 직접 조회
        *
        * HttpServletRequest 객체는 추가로 여러가지 부가기능도 함께 제공한다.
        *
        * 임시 저장소 기능
        * 해당 HTTP 요청이 시작부터 끝날때까지 유지되는 임시 저장소 기능
        * (고객의 요청이 오고 요청의 응답이 나갈때까지 생존 범위)
        * 저장: request.setAttribute(name, value)
        * 조회: request.getAttribute(name)
        *
        * 세션 관리 기능
        * request.getSession(create: true)
        *
        * HttpServletRequest, HttpServletResponse 를 사용할때 가장 중요한 점은 이 객체들이
        * HTTP 요청메세지, HTTP 응답메시지를 편리하게 사용하도록 도와주는 객체라는 점이다. 따라서 이 기능에 대해서
        * 깊이있는 이해를 하려면 "HTTP 스펙이 제공하는 요청, 응답 메시지 자체를 이해" 해야 한다.
        *
        *
        *
        *
        *
        *
        * */
    }

}
