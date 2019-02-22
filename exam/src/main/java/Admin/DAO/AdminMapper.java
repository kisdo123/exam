package Admin.DAO;

import java.util.List;

import Admin.DTO.Admin;
import Annotation.Query;
import Commute.DTO.Commute;
import Commute.DTO.DateData;
import User.DTO.User;

@Query
public interface AdminMapper {
	//모든 유저의 출근일을 년 월을 기준으로 검색후 list로 결과값을 받는다.
	List<Commute> adminattendMonth(DateData dateData);

	//모든 유저의 출근일을 검색후 날짜를 넘겨주고 Commute객체로 돌려받는다.
	Commute adminattendDay(DateData dateData);
	
	List<Admin> adminAttendData();
}
