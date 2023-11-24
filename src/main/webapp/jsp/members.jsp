<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page import="java.util.List" %>
<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--
회원 리포지토리를 먼저 조회하고, 결과 List를 사용해서 중간에 <tr><td> HTML 태그를 반복해서
출력하고 있다.

서블릿과 JSP의 한계

서블릿으로 개발할 때는 뷰(view)화면을 위한 HTML을 만드는 작업이 자바 코드에 섞여서 지저분하고
복잡했다.
JSP를 사용한 덕분에 뷰를 생성하는 HTML 작업을 깔끔하게 가져가고, 중간중간 동적으로 변경이 필요한
부분에만 자바 코드를 적용했다. 그런데 이렇게 해도 해결되지 않는 몇가지 고민이 남는다.

회원 저장 JSP를 보자. 코드의 상위 절반은 회원을 저장하기 위한 비즈니스 로직이고, 나머지 하위 절반만
결과를 HTML로 보여주기 위한 뷰 영역이다. 회원 목록의 경우에도 마찬가지다.
코드를 잘 보면, JAVA코드, 데이터를 조회하는 리포지토리 등등 다양한 코드가 모두 JSP에 노출되어 있다.
JSP가 너무 많은 역할을 한다. 이렇게 작은 프로젝트도 벌썸 ㅓ리가 아파오는데, 수백 수천 줄이 넘어가는
JSP를 떠올리면 정말 지옥같다.

MVC 패턴의 등장
비즈니스 로직은 서블릿 처럼 다른 곳에서 처리하고, JSP는 목적에 맞게 HTML로 화면(View)을 그리는
일에 집중하도록 하자. 과거 개발자들도 모두 비슷한 고민이 있었고, 그래서 MVC 패턴이 등장했다.
우리도 직접 MVC패턴을 적용해서 프로젝트를 리팩터링 해보자.




-%>
<%
    MemberRepository memberRepository = MemberRepository.getInstance();
    List<Member> members = memberRepository.findAll();
%>
<html>
<head>
    <title>Title</title>
</head>
<body>

<a href="/index.html">메인</a>
<table>
    <thead>
    <th>id</th>
    <th>username</th>
    <th>age</th>
    </thead>
    <tbody>
    <%
        for (Member member : members) {
            out.write(" <tr>\n");
            out.write(" <td>" + member.getId() + "</td>");
            out.write(" <td>" + member.getUsername() + "</td>");
            out.write(" <td>" + member.getAge() + "</td>");
            out.write(" </tr>");
        }
    %>
    </tbody>
</table>

</body>
</html>
