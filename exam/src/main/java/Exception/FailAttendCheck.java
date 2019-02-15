package Exception;

//출근확인 실패 예외처리
public class FailAttendCheck extends RuntimeException {
	public FailAttendCheck(String message) {
		super(message);
	}
}