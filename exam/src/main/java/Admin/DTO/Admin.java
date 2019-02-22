package Admin.DTO;

import org.apache.ibatis.type.Alias;

@Alias("Admin")
public class Admin {
	private int userNo;
	private String id;
	private String name;
	private Boolean admin;
	private String attend;
	private Boolean vacation;
	private String textData;
	
	public Admin() {
		
	}
	
	public Admin(int userNo, String id, String name, Boolean admin, String attend, Boolean vacation, String textData) {
		this.userNo = userNo;
		this.id = id;
		this.name = name;
		this.admin = admin;
		this.attend = attend;
		this.vacation = vacation;
		this.textData = textData;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public String getAttend() {
		return attend;
	}

	public void setAttend(String attend) {
		this.attend = attend;
	}

	public Boolean getVacation() {
		return vacation;
	}

	public void setVacation(Boolean vacation) {
		this.vacation = vacation;
	}

	public String getTextData() {
		return textData;
	}

	public void setTextData(String textData) {
		this.textData = textData;
	}
	
	
	
}
