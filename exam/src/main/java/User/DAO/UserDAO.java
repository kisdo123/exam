package User.DAO;

import User.DTO.User;

public interface UserDAO {
	//id로 회원 검색
	public User selectUserById(String id);

	//회원가입
	public int joinUser(User user);
}
