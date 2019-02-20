package User.DAO;

import User.DTO.User;

public interface UserDAO {
	//중복확인 id로 회원검색
	public User selectUserById(String id);

	//회원가입
	public int joinUser(User user);

	//로그인
	public User selectUser(User user);
}
