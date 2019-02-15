package Exception;

//출퇴근 insert 실패 예외처리
public class FailJoinUser extends RuntimeException {
	public FailJoinUser(String message) {
		super(message);
	}
}