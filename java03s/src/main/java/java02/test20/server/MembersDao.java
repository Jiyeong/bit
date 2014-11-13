/* 
페이징 처리
=> DBMS마다 처리하는 방법이 다르다.  
 */
package java02.test20.server;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class MembersDao {
  SqlSessionFactory sqlSessionFactory;

  public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  public MembersDao() {}


  public Members selectOne(String id) {
    SqlSession sqlSession = sqlSessionFactory.openSession();

    // 시그너처(signature) - 메소드 이름과 파라미터 타입을 말함.
    try {
      return sqlSession.selectOne(
          "java02.test20.server.MembersDao.selectOne", 
          id/*new Integer(no)*/); // auto-boxing
    } finally {
      sqlSession.close();
    }

  }

  public void update(Members members) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    try {
      sqlSession.update(
          "java02.test20.server.MembersDao.update", members);
      sqlSession.commit();
    } finally {
      sqlSession.close();
    }
  }

  public void delete(String id) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    try {
      sqlSession.delete(
          "java02.test20.server.MembersDao.delete", id);
      sqlSession.commit();
    } finally {
      sqlSession.close();
    }
  }

  public List<Members> selectList(int pageNo, int pageSize) {
    SqlSession sqlSession = sqlSessionFactory.openSession();

    HashMap<String, Object> paramMap = new HashMap<>();
    paramMap.put("startIndex", ((pageNo - 1) * pageSize));
    paramMap.put("pageSize", pageSize);

    try {
      return sqlSession.selectList(
          // 네임스페이스 + SQL문 아이디
          "java02.test20.server.MembersDao.selectList", 
          paramMap/* SQL문 실행할 때 필요한 값 전달*/);
    } finally {
      sqlSession.close();
    }
  }
  
  
  public void insert(Members members) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    try {
      sqlSession.insert(
          "java02.test20.server.MembersDao.insert", members);
      sqlSession.commit();
    } finally {
      sqlSession.close();
    }
  }
}

