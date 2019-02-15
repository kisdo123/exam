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



}
