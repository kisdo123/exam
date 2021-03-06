package User.DAO;

import Annotation.Query;
import User.DTO.User;

@Query
public interface UserMapper {
	//id를 이용하여 회원검색후 User 객체로 리턴 받음
	User selectUserById(String id);

	//insert후 int로 리턴을 받음
	int joinUser(User user);
	
	//id와 암호화된 pw검색
	User selectUser(User user);
}