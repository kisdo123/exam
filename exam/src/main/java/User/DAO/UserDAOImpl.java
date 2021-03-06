package User.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import User.DTO.User;

@Repository("userDAO")
public class UserDAOImpl implements UserDAO {
	@Autowired
	UserMapper userMapper;
	
	//유저 id로 회원조회
	public User selectUserById(String id) {
		return userMapper.selectUserById(id);
	}

	//회원가입
	public int joinUser(User user) {
		return userMapper.joinUser(user);
	}

	//로그인을 위해 id와 암호화된 pw로 검색
	public User selectUser(User user) {
		return userMapper.selectUser(user);
	}

}
