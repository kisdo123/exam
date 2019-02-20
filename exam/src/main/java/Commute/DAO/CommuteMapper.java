package Commute.DAO;

import java.util.List;

import Annotation.Query;
import Commute.DTO.Commute;
import Commute.DTO.DateData;
import User.DTO.User;

@Query
public interface CommuteMapper {
	//User객체로 insert후 int로 결과값을 받음 0실패 1성공 1이상 에러
	int insertUserNo(User user);
	
	//퇴근시간을 현재시간으로 update int로 결과값을 받음 0실패 1성공 1이상 에러
	int updateleaved(int commuteNo);
	
	//오늘 출근 했는지 확인한다
	Commute checkattend (int userNo);
	
	//년 월을 기준으로 검색후 list로 결과값을 받는다.
	List<Commute> attendMonth(DateData dateData);
	
	//출근일을 검색후 날짜를 넘겨주고 Commute객체로 돌려받는다.
	Commute attendDay(DateData dateData);
}
