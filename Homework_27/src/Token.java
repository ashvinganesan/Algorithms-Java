/*
Ashvin Ganesan
Homework 27
Wednesday, June 2nd, 2021
 */

public class Token {

	public static enum Kind {
		PLUS,
		MINUS,
		STAR,
		SLASH,
		EQUAL,
		PLUS_PLUS,
		PLUS_EQUAL,
		MINUS_MINUS,
		MINUS_EQUAL,
		STAR_EQUAL,
		SLASH_EQUAL,
		LEFT_PAREN,
		RIGHT_PAREN,
		IDENTIFIER,
		NUMBER,
                NUMBER_DOUBLE,
		ERROR,
		END;

		public boolean isAssignmentOperator() {
			switch (this) {
				case EQUAL:       return true;
				case PLUS_EQUAL:  return true;
				case MINUS_EQUAL: return true;
				case STAR_EQUAL:  return true;
				case SLASH_EQUAL: return true;
				default:          return false;
			}
		}

		public boolean isAddingOperator() {
			switch (this) {
				case PLUS:  return true;
				case MINUS: return true;
				default:    return false;
			}
		}

		public boolean isMultiplyingOperator() {
			switch (this) {
				case STAR:  return true;
				case SLASH: return true;
				default:    return false;
			}
		}

		public boolean isIncrementOperator() {
			switch (this) {
				case PLUS_PLUS:   return true;
				case MINUS_MINUS: return true;
				default:          return false;
			}
		}

		public boolean isSign() {
			switch (this) {
				case PLUS:  return true;
				case MINUS: return true;
				default:    return false;
			}
		}
	}


	private Kind kind;
	private String image;
	private int position;

	public Token(Kind kind, String image, int position) {
		this.kind = kind;
		this.image = image;
		this.position = position;
	}

	public Kind kind() {
		return this.kind;
	}

	public int position() {
		return this.position;
	}

	public String image() {
		return this.image;
	}

	public boolean isAssignmentOperator() {
		return this.kind.isAssignmentOperator();
	}

	public boolean isAddingOperator() {
		return this.kind.isAddingOperator();
	}

	public boolean isMultiplyingOperator() {
		return this.kind.isMultiplyingOperator();
	}

	public boolean isIncrementOperator() {
		return this.kind.isIncrementOperator();
	}

	public boolean isSign() {
		return this.kind.isSign();
	}
}
