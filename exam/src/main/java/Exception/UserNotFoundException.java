package Exception;

//유저를 찾지못할때 예외처리
public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException(String message) {
		super(message);
	}
}
