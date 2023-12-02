package hello.servlet.web.servletmvc;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
*
* HttpServletRequest를 Model로 사용한다.
* request가 제공하는 setAttribute()를 사용하면 request 객체에 데이터를 보관해서 뷰에 전달할 수 있다.
* 뷰는 request.getAttribute()를 사용해서 데이터를 꺼내면 된다.
*
* MVC 패턴 - 한계,,
* Mvc패턴을 적용한 덕분에 컨트롤러의 역할과 뷰를 렌더링 하는 역할을 명확하게 구분할 수 있다.
* 특히 뷰는 확면을 그리는 역할에 충실한 덕분에, 코드가 깔끔하고 직관적이다. 단순하게 모델에서 필요한
* 데이터를 꺼내고, 화면을 만들면 된다.
* 그런데 컨트롤러는 딱봐도 중복이 많고 필요하지 않는 코드들도 많이 보인다.
*
* MVC컨트롤러의 단점
*
* 포워드 중복
* View로 이동하는 코드가 항상 중복 호출되어야 한다. 물론 이 부분을 메서드로 공통화해도 되지만, 해당 메서드도
* 항상 직접 호출해야 한다.
*
* ViewPath에 중복
*
* 그리고 만약 jsp가 아닌 thymeleaf 같은 다른 뷰로 변경한다면 전체 코드를 다 변경해야 한다.
*
* 사용하지 않는 코드
* 다음 코드를 사용할 때도 있고, 사용하지 않을 때도 있다. 특히 response는 현재 코드에서 사용되지 않는다.
* 그리고 이런 HttpServletRequest, HttpServletResponse를 사용하는 코든느 테스트 케이스를 작성하기도 어렵다.
*
* 공통처리가 어렵다.
* 기능이 복잡해질수록 컨트롤러에서 공통으로 처리해야하는 부분이 점점더 많이 증가할거이다. 단순히
* 공통 기능을 메서드로 뽑으면 될 것 같지만, 결과적으로 해당 메서드를 항상 호출해야하고, 실수로 호출하지
* 않으면 문제가 될 것이다. 그리고 호출하는 것 자체도 중복이다.
*
* 정리하면 공통 처리가 어렵다는 문제가 있다.
* 이 문제를 해결하려면 컨트롤러 호출전에 먼저 공통 기능을 처리해야한다. 소위 수문장 역할을 하는 기능이
* 필요하다. 프론트 컨트롤러 패턴을 도입하면 이런 문제를 깔끔하게 해결할수있다.
* 입구를 하나로.
* 스프링 MVC의 핵심도 바로 이 프론트 컨트롤러에 있다.
*
*
*
* */

@WebServlet(name = "mvcMemberSaveServlet", urlPatterns = "/servlet-mvc/members/save")
public class MvcMemberSaveServlet extends HttpServlet {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        System.out.println("member = " + member);
        memberRepository.save(member);

        //Model에 데이터를 보관한다.
        request.setAttribute("member", member);

        String viewPath = "/WEB-INF/views/save-result.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);




    }
}
