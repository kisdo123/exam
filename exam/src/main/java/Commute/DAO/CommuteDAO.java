package Commute.DAO;

import java.util.List;

import Commute.DTO.Commute;
import Commute.DTO.DateData;

public interface CommuteDAO {
	//회원번호로 insert
	public int insertUserNo(int userNo);
	
	//퇴근시간 update
	public int updateleaved(int commuteNo);
	
	//출근 확인
	public Commute checkattend (int userNo);
	
	//출근 확인
	public List<Commute> attendMonth(DateData dateData);
		
	//출근일 검색
	public Commute attendDay(DateData dateData);
}
