package model;

@SuppressWarnings("serial")
public class GameMapException extends Exception{

	public GameMapException() {
			return;
		}

		public GameMapException(String message) {
			super(message);
		}

		public GameMapException(Throwable throwable) {
			super(throwable);
		}

		public GameMapException(String message, Throwable throwable) {
			super(message, throwable);
		}

}

