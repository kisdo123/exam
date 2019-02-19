package Commute.DTO;

import org.apache.ibatis.type.Alias;

@Alias("DateData")
//출근 날짜를 저장하기 위한 객체
public class DateData {
	private String toYear; // 년 체크
	private String toMonth; // 월 체크
	private String toDay; // 일 체크
	private String toTime; // 시간 체크
	private int userNo; // 회원번호 체크

	public DateData(String toYear, String toMonth, String toDay, String toTime, int userNo) {
		this.toYear = toYear;
		this.toMonth = toMonth;
		this.toDay = toDay;
		this.toTime = toTime;
		this.userNo = userNo;
	}

	public DateData() {

	}

	public DateData(String toYear, String toMonth, String toDay, int userNo) {
		this.toYear = toYear;
		this.toMonth = toMonth;
		this.toDay = toDay;
		this.userNo = userNo;
	}

	public String getToTime() {
		return toTime;
	}

	public void setToTime(String toTime) {
		this.toTime = toTime;
	}

	public String getToYear() {
		return toYear;
	}

	public void setToYear(String toYear) {
		this.toYear = toYear;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getToMonth() {
		return toMonth;
	}

	public void setToMonth(String toMonth) {
		this.toMonth = toMonth;
	}

	public String getToDay() {
		return toDay;
	}

	public void setToDay(String toDay) {
		this.toDay = toDay;
	}

}
