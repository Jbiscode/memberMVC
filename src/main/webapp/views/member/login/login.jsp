<%@ page contentType="application/json" language="java" %>
<%@ page import="member.bean.MemberDTO" %>
<%@ page import="member.dao.MemberDAO" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page import="org.json.JSONObject" %>
<%
    BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
    String json = "";
    if(br != null){
        json = br.readLine();
    }

    JSONObject data = new JSONObject(json);
    String inputId = data.getString("id");
    String inputPwd = data.getString("pwd");

    JSONObject result = new JSONObject();
%>
<%

    MemberDAO memberDAO = MemberDAO.getInstance();
    if (memberDAO.isExistId(inputId)) {
    MemberDTO memberDTO = memberDAO.login(inputId, inputPwd);
        if (memberDTO != null) {
//            session.setAttribute("id", memberDTO.getId());
//            session.setAttribute("name", memberDTO.getName());
//            session.setAttribute("gender", memberDTO.getGender());
//            session.setAttribute("email1", memberDTO.getEmail1());
//            session.setAttribute("email2", memberDTO.getEmail2());
//            session.setAttribute("tel1", memberDTO.getTel1());
//            session.setAttribute("tel2", memberDTO.getTel2());
//            session.setAttribute("tel3", memberDTO.getTel3());
//            session.setAttribute("zipcode", memberDTO.getZipcode());
//            session.setAttribute("address1", memberDTO.getAddr1());
//            session.setAttribute("address2", memberDTO.getAddr2());

            result.put("result", "Success");
            response.getWriter().print(result);
        } else {
            result.put("result", "WrongPassword");
            response.getWriter().print(result);
        }
    }else {
        result.put("result", "WrongId");
        response.getWriter().print(result);
    }
%>

