package board.dao;

import board.bean.BoardDTO;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BoardDAO {
    // 싱글톤
    private static BoardDAO boardDAO = new BoardDAO();
    private SqlSessionFactory sqlSessionFactory;

    public BoardDAO() {
        try {
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BoardDAO getInstance() {
        return boardDAO;
    }

    public boolean writeContent(BoardDTO boardDTO) {
        boolean write = false;
        SqlSession sqlSession = sqlSessionFactory.openSession();
        int success = sqlSession.insert("boardSQL.writeContent", boardDTO);
        sqlSession.commit();
        sqlSession.close();

        if (success > 0) {
            write = true;
        }
        return write;
    }

    //    public ArrayList<BoardDTO> loadContents(String scope, String key, int startPage) {
//        ArrayList<BoardDTO> boardDTOs = new ArrayList<>();
//        //language=MySQL
//        StringBuilder sql = new StringBuilder("SELECT * FROM board where");
//        if (scope.equals("all")) {
//            sql.append(" subject LIKE ? OR name LIKE ? OR content LIKE ?");
//        }
//        if (scope.equals("title")) {
//            sql.append(" subject LIKE ?");
//        }
//        if (scope.equals("writer_name")) {
//            sql.append(" name LIKE ?");
//        }
//        if (scope.equals("content")) {
//            sql.append(" content LIKE ?");
//        }
//        sql.append(" ORDER BY seq DESC LIMIT ?, ?");
//
//
//        getConnection();
//        try {
//            pstmt = conn.prepareStatement(sql.toString());//생성
//            if (scope.equals("all")) {
//                pstmt.setString(1, "%" + key + "%");
//                pstmt.setString(2, "%" + key + "%");
//                pstmt.setString(3, "%" + key + "%");
//                pstmt.setInt(4, (startPage - 1) * 10);
//                pstmt.setInt(5, 10);
//            } else {
//                pstmt.setString(1, "%" + key + "%");
//                pstmt.setInt(2, (startPage - 1) * 10);
//                pstmt.setInt(3, 10);
//            }
//
//            rs = pstmt.executeQuery();
//
//            while (rs.next()) {
//                BoardDTO boardDTO = new BoardDTO();
//                boardDTO.setSeq(rs.getInt("seq"));
//                boardDTO.setName(rs.getString("name"));
//                boardDTO.setSubject(rs.getString("subject"));
//                boardDTO.setContent(rs.getString("content"));
//
//                boardDTOs.add(boardDTO);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (pstmt != null) pstmt.close();
//                if (conn != null) conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return boardDTOs;
//    }
    public ArrayList<BoardDTO> loadContents(String scope, String key, int startPage) {
        Map<String, Object> params = new HashMap<>();
        params.put("scope", scope);
        params.put("key", "%" + key + "%");
        params.put("startPage", (startPage - 1) * 10);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        ArrayList<BoardDTO> boardDTOs = new ArrayList<>(sqlSession.selectList("loadContents", params));
        sqlSession.close();

        return boardDTOs;
    }
//
//    public int dataCnt(String scope, String key) {
//        int Cnt = 0;
//        StringBuilder sql = new StringBuilder("SELECT * FROM board where");
//        if (scope.equals("all")) {
//            sql.append(" subject LIKE ? OR name LIKE ? OR content LIKE ?");
//        }
//        if (scope.equals("title")) {
//            sql.append(" subject LIKE ?");
//        }
//        if (scope.equals("writer_name")) {
//            sql.append(" name LIKE ?");
//        }
//        if (scope.equals("content")) {
//            sql.append(" content LIKE ?");
//        }
//
//        getConnection();
//        try {
//            pstmt = conn.prepareStatement(sql.toString());//생성
//            if (scope.equals("all")) {
//                pstmt.setString(1, "%" + key + "%");
//                pstmt.setString(2, "%" + key + "%");
//                pstmt.setString(3, "%" + key + "%");
//            } else {
//                pstmt.setString(1, "%" + key + "%");
//            }
//
//            rs = pstmt.executeQuery();
//
//            while (rs.next()) {
//                Cnt++;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (pstmt != null) pstmt.close();
//                if (conn != null) conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return Cnt;
//    }

    public int dataCnt(String scope, String key) {
        Map<String, Object> params = new HashMap<>();
        params.put("scope", scope);
        params.put("key", "%" + key + "%");

        SqlSession sqlSession = sqlSessionFactory.openSession();

        int count = sqlSession.selectOne("dataCnt", params);
        sqlSession.close();

        return count;
    }
}
