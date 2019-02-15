package User.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Exception.FailJoinUser;
import Exception.PasswordNotMatch;
import Exception.UserNotFoundException;
import User.DAO.UserDAO;
import User.DTO.User;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	
	
	//로그인
	public User login(String id, String pw) {
		//회원 조회
		User user = userDAO.selectUserById(id);
		
		// user가 존재하지않으면 에러
		if (user.equals("") || user.equals(null)) {
			throw new UserNotFoundException("유저가 존재하지 않습니다.");
		}

		// 조회한 유저의 비밀번호와 입력한 비밀번호가 일치하지 않으면 에러
		if (!user.getPw().equals(pw)) {
			throw new PasswordNotMatch("비밀번호가 맞지않습니다.");
		}

		return user;
	}
	
	//회원가입
	public void join(User user) {
		//회원가입
		int res = userDAO.joinUser(user);
		
		if(res == 0 || res>1) {
			throw new FailJoinUser("회원가입 실패");
		}
		
	}

}
