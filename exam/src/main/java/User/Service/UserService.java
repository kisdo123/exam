package User.Service;

import User.DTO.User;

public interface UserService {
	//로그인
	public User login(String id, String pw);
	
	//회원가입
	public void join(User user);

	//중복확인
	public Boolean idDuplicate(String id);
}