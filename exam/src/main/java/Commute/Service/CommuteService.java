package Commute.Service;

import java.util.List;

import Commute.DTO.Commute;
import Commute.DTO.DateData;

public interface CommuteService {

	//출퇴근 조회
	public void commuteInsert(int userNo);

	//퇴근시간 update
	public void checkAndUpdate(int userNo);
	
	//출근일 조회
	public List<Commute> Datecompare(DateData dateData);
}
