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

	// 로그인
	public User login(String id, String pw) {
		// 회원 조회
		User user = new User();
		user.setId(id);
		user.setPw(pw);
		
		User loginUser = userDAO.selectUser(user);

		// user가 존재하지않으면 에러
		if (loginUser == null) {
			throw new UserNotFoundException("유저가 존재하지 않습니다.");
		}

		return loginUser;
	}

	// 회원가입
	public void join(User user) {
		// 회원가입
		int res = userDAO.joinUser(user);

		if (res == 0 || res > 1) {
			throw new FailJoinUser("회원가입 실패");
		}

	}

	//중복확인
	public Boolean idDuplicate(String id) {
		User user = userDAO.selectUserById(id);
		if (user == null) {
			return false;
		} else {
			return true;
		}
	}

}
