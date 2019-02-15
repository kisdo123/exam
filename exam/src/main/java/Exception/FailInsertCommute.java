package Exception;

//출퇴근 insert 실패 예외처리
public class FailInsertCommute extends RuntimeException {
	public FailInsertCommute(String message) {
		super(message);
	}
}