package User.DTO;

import org.apache.ibatis.type.Alias;

@Alias("User")
//회원 관리를 위한 객체
public class User {
	private int userNo; // 회원번호
	private String id; // 회원 id
	private String pw; // 회원 pw
	private String name; // 회원 이름
	
	public User() {
		
	}
	
	public User(int userNo, String id, String pw) {
		this.userNo = userNo;
		this.id = id;
		this.pw = pw;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	
	
}
