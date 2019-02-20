package Commute.Service;

import java.util.List;

import Commute.DTO.Commute;
import Commute.DTO.DateData;
import User.DTO.User;

public interface CommuteService {

	// 출퇴근 조회
	public Commute commuteInsert(User user);

	// 퇴근시간 update
	public Commute checkAndUpdate(int userNo);

	// 출근일 조회
	public List<Commute> dateCompare(DateData dateData);
	
	// 출근 시간 조회
	public Commute TimeCheck(int userNo);
}
