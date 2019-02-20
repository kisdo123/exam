package Admin.DAO;

import java.util.List;

import Commute.DTO.Commute;
import Commute.DTO.DateData;
import User.DTO.User;

public interface AdminDAO {

	// 모든 유저의 출근일을 년 월을 기준으로 검색후 list로 결과값을 받는다.
	public List<Commute> adminattendMonth(DateData dateData);

	// 모든 유저의 출근일을 검색후 날짜를 넘겨주고 Commute객체로 돌려받는다.
	public Commute adminattendDay(DateData dateData);
}
