package board.service;

import board.bean.BoardDTO;
import board.dao.BoardDAO;
import member.service.CommandProcess;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class BoardContentService implements CommandProcess {
    @Override
    public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String scope = request.getParameter("search_field") != null ? request.getParameter("search_field") : "all";
        String key = request.getParameter("search_key") != null ? request.getParameter("search_key") : "";
        String Page = request.getParameter("pageNum") != null ? request.getParameter("pageNum") : "1";
        int startPage = Integer.parseInt(Page);

        BoardDAO boardDAO = BoardDAO.getInstance();
        ArrayList<BoardDTO> boardDTOs = boardDAO.loadContents(scope, key, startPage);
        request.setAttribute("boardDTOs", boardDTOs);

        return "/views/board/contents/boardContents.jsp";
    }
}
