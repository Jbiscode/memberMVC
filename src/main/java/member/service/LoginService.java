package member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.bean.MemberDTO;
import member.dao.MemberDAO;

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
            System.out.println("로그인 실패");
            return "loginForm.do";
        }else {
            HttpSession session = request.getSession();
            session.setAttribute("id", memberDTO.getId());
            session.setAttribute("name", memberDTO.getName());
            session.setAttribute("gender", memberDTO.getGender());
            session.setAttribute("email1", memberDTO.getEmail1());
            session.setAttribute("email2", memberDTO.getEmail2());
            session.setAttribute("tel1", memberDTO.getTel1());
            session.setAttribute("tel2", memberDTO.getTel2());
            session.setAttribute("tel3", memberDTO.getTel3());
            session.setAttribute("zipcode", memberDTO.getZipcode());
            session.setAttribute("address1", memberDTO.getAddr1());
            session.setAttribute("address2", memberDTO.getAddr2());

            return "index.do";
        }
    }
}