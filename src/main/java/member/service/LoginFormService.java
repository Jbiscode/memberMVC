package member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFormService implements CommandProcess{
    @Override
    public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        return "/views/member/login/loginForm.jsp";
    }
}
