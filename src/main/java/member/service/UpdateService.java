package member.service;

import member.bean.MemberDTO;
import member.dao.MemberDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateService implements CommandProcess{
    @Override
    public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String name = request.getParameter("name");
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String email1 = request.getParameter("email1");
        String email2 = request.getParameter("email2");
        String phone1 = request.getParameter("phone1");
        String phone2 = request.getParameter("phone2");
        String phone3 = request.getParameter("phone3");
        String zipcode = request.getParameter("zipcode");
        String address1 = request.getParameter("address1");
        String address2 = request.getParameter("address2");

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setName(name);
        memberDTO.setId(id);
        memberDTO.setPwd(password);
        memberDTO.setGender(gender);
        memberDTO.setEmail1(email1);
        memberDTO.setEmail2(email2);
        memberDTO.setTel1(phone1);
        memberDTO.setTel2(phone2);
        memberDTO.setTel3(phone3);
        memberDTO.setZipcode(zipcode);
        memberDTO.setAddr1(address1);
        memberDTO.setAddr2(address2);

        MemberDAO memberDAO = MemberDAO.getInstance();
        memberDAO.updateMember(memberDTO);

        request.getSession().setAttribute("memberDTO", memberDTO);
        return "/views/member/update/update.jsp";
    }
}
