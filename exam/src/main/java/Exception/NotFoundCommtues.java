package Exception;

//목록을 찾을수 없을때 예외처리
public class NotFoundCommtues extends RuntimeException {
	public NotFoundCommtues(String message) {
		super(message);
	}
}