package Exception;

//비밀번호가 다를때 예외처리
public class PasswordNotMatch extends RuntimeException {
	public PasswordNotMatch(String message) {
		super(message);
	}
}