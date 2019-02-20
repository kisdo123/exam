package Admin.Service;

import java.util.List;

import Commute.DTO.Commute;
import Commute.DTO.DateData;
import User.DTO.User;

public interface AdminService {

	public List<Commute> getAllUserData(DateData dateData);
}
