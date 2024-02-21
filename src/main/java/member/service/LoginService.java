package member.service;

import member.bean.MemberDTO;
import member.dao.MemberDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginService implements CommandProcess {

    @Override
    public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // 데이터
        String id = request.getParameter("user_id");
        String pwd = request.getParameter("user_pw");

        System.out.println("id: " + id);
        System.out.println("pwd: " + pwd);
        // DB
        MemberDAO memberDAO = MemberDAO.getInstance();
        MemberDTO memberDTO = memberDAO.login(id, pwd);

        // 응답
        if (memberDTO == null) {
            return "loginForm.do";
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("memId", memberDTO.getId());
            session.setAttribute("memName", memberDTO.getName());

            session.setAttribute("memberDTO", memberDTO);
            return "index.do";
        }
    }
}