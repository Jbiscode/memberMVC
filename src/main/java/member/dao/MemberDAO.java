package member.dao;

import member.bean.MemberDTO;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class MemberDAO {
    private static MemberDAO memberDAO = new MemberDAO();
    // 싱글톤
    private SqlSessionFactory sqlSessionFactory;

    public MemberDAO() {
        try {
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static MemberDAO getInstance() {
        return memberDAO;
    }

    public boolean isExistId(String id) {
        boolean exist = false;
        SqlSession sqlSession = sqlSessionFactory.openSession();
        MemberDTO memberDTO = sqlSession.selectOne("memberSQL.isExistId", id);

        if (memberDTO != null) {
            exist = true;
        }
        sqlSession.close();

        return exist;
    }

    public boolean insertMember(MemberDTO memberDTO) {
        boolean isInserted = false;
        SqlSession sqlSession = sqlSessionFactory.openSession();
        int su = sqlSession.insert("memberSQL.insertMember", memberDTO);
        sqlSession.commit();
        sqlSession.close();

        if (su > 0) {
            isInserted = true;
        }

        return isInserted;
    }

    public MemberDTO login(String id, String pwd) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("pwd", pwd);

        SqlSession sqlSession = sqlSessionFactory.openSession(); //생성
        MemberDTO memberDTO = sqlSession.selectOne("memberSQL.login", map);
        sqlSession.close();

        return memberDTO;
    }

    public boolean updateMember(MemberDTO memberDTO) {
        boolean isUpdated = false;

        SqlSession sqlSession = sqlSessionFactory.openSession();
        int success = sqlSession.update("memberSQL.updateMember", memberDTO);
        sqlSession.commit();
        sqlSession.close();
        if (success > 0) {
            isUpdated = true;
        }
        return isUpdated;
    }

}