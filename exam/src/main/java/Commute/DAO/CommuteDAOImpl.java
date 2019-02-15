package Commute.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import Commute.DTO.Commute;
import Commute.DTO.DateData;

@Repository("commuteDAO")
public class CommuteDAOImpl implements CommuteDAO {

	@Autowired
	CommuteMapper commuteMapper;

	//회원번호로 insert
	public int insertUserNo(int userNo) {
		return commuteMapper.insertUserNo(userNo);
	}

	//퇴근시간 update
	public int updateleaved(int commuteNo) {
		return commuteMapper.updateleaved(commuteNo);
	}

	//출근 확인
	public Commute checkattend(int userNo) {
		return commuteMapper.checkattend(userNo);
	}

	//출근 확인
	public List<Commute> attendMonth(DateData dateData) {
		return commuteMapper.attendMonth(dateData);
	}

	//출근일 검색
	public Commute attendDay(DateData dateData) {
		return commuteMapper.attendDay(dateData);
	}

}