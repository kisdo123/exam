package Exception;

//출근확인 실패 예외처리
public class AlreadyAttend extends RuntimeException {
	public AlreadyAttend(String message) {
		super(message);
	}
}