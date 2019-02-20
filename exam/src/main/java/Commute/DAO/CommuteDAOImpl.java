package Commute.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import Commute.DTO.Commute;
import Commute.DTO.DateData;
import User.DTO.User;

@Repository("commuteDAO")
public class CommuteDAOImpl implements CommuteDAO {

	@Autowired
	CommuteMapper commuteMapper;

	//User객체로 출퇴근 insert
	public int insertUserNo(User user) {
		return commuteMapper.insertUserNo(user);
	}

	//퇴근시간 update
	public int updateleaved(int commuteNo) {
		return commuteMapper.updateleaved(commuteNo);
	}

	//오늘 출근했는지 조회
	public Commute checkattend(int userNo) {
		return commuteMapper.checkattend(userNo);
	}

	//년 월 기준 출근 조회
	public List<Commute> attendMonth(DateData dateData) {
		return commuteMapper.attendMonth(dateData);
	}

	//출근일 검색
	public Commute attendDay(DateData dateData) {
		return commuteMapper.attendDay(dateData);
	}

}