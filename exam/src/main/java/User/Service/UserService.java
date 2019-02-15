package User.Service;

import User.DTO.User;

public interface UserService {
	//로그인
	public User login(String id, String pw);
}