package board.service;

import board.bean.BoardDTO;
import board.dao.BoardDAO;
import member.bean.MemberDTO;
import member.service.CommandProcess;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class WriteService implements CommandProcess {
    @Override
    public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        BoardDAO boardDAO = BoardDAO.getInstance();
        HttpSession session = request.getSession();

        MemberDTO memberDTO = (MemberDTO) session.getAttribute("memberDTO");
        String id = memberDTO.getId();
        String name = memberDTO.getName();
        String email = memberDTO.getEmail1() + "@" + memberDTO.getEmail2();
        String subject = request.getParameter("subjectInput");
        String content = request.getParameter("contentInput");

        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(id);
        boardDTO.setName(name);
        boardDTO.setEmail(email);
        boardDTO.setSubject(subject);
        boardDTO.setContent(content);

        boolean success = boardDAO.writeContent(boardDTO);
        request.setAttribute("success", success);
        return "/views/board/write/boardWrite.jsp";
    }
}
