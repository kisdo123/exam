package Exception;

//퇴근 update 실패 예외처리
public class FailUpdateCommute extends RuntimeException {
	public FailUpdateCommute(String message) {
		super(message);
	}
}