package Admin.Service;

import java.util.List;

import Admin.DTO.Admin;
import Commute.DTO.Commute;
import Commute.DTO.DateData;
import User.DTO.User;

public interface AdminService {
	
	// 모든유저의 출근일 가져오기
	public List<Commute> getAllUserData(DateData dateData);
	
	public List<Admin> getAllData();
}
