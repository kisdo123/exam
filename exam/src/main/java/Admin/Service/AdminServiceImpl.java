package Admin.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Admin.DAO.AdminDAO;
import Commute.DTO.Commute;
import Commute.DTO.DateData;
import User.DTO.User;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDAO adminDAO;

	// 모든유저의 출근일 가져오기
	public List<Commute> getAllUserData(DateData dateData) {
		// Controller에서 받은 년도과 월의 출근 날 검색
		List<Commute> commutes = adminDAO.adminattendMonth(dateData);

		for (Commute comm : commutes) {
			// Commute의 출근일을 가공
			String toDay = comm.getAttend().substring(8, 10);

			// dateData에 데이터 삽입
			dateData.setToDay(toDay);

			// 해당하는 날짜에 출근했는지 검색 후 데이터 삽입
			adminDAO.adminattendDay(dateData);
		}
		return commutes;
	}
}
