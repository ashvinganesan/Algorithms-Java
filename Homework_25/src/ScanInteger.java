public class ScanInteger {

	public static enum Input {
		MINUS,
		PLUS,
		DIGIT,
		UNDER,
		OTHER;

		public static Input classify(char c) {
			switch (c) {
				case '-': return MINUS;
				case '+': return PLUS;
				case '_': return UNDER;

				default:
					if (c >= '0' && c <= '9') {
						return DIGIT;
					} else {
						return OTHER;
					}
			}
		}
	}


	public static enum State {
		START(false) {
			@Override
			public State next(Input c) {
				switch (c) {
					case PLUS:  return State.SIGN;
					case MINUS: return State.SIGN;
					case DIGIT: return State.DIGIT;
					default:    return State.ERROR;
				}
			}
		},

		SIGN(false) {
            @Override
            public State next(Input c) {
                switch (c) {
                    case DIGIT: return State.DIGIT;
                    default:    return State.ERROR;
                }
            }
        },

		DIGIT(true) {
            @Override
            public State next(Input c) {
                switch (c) {
                    case DIGIT: return State.DIGIT;
					case UNDER: return State.UNDER;
                    default:    return State.ERROR;
                }
            }
        },

		UNDER(false) {
            @Override
            public State next(Input c) {
                switch (c) {
                    case DIGIT: return State.DIGIT;
                    default:    return State.ERROR;
                }
            }
        },

		ERROR(false) {
            @Override
            public State next(Input c) {
				return State.ERROR;
            }
        };

		private boolean isFinal;
 
		private State(boolean isFinal) {
			this.isFinal = isFinal;
		}

		public boolean isFinal() {
			return this.isFinal;
		}

		public State next(Input c) {
			return State.ERROR;
		}
	}


	public static boolean isValidInteger(String s) {
		State state = State.START;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			state = state.next(Input.classify(c));
		}
		return state.isFinal();
	}


	public static int parseInteger(String s) {
		State state = State.START;
		boolean negative = false;
		int value = 0;

		for (int i = 0; i < s.length(); i++) {

			// Get next character from the input and classify it.
			// Then transition to the next state.

			char c = s.charAt(i);
			state = state.next(Input.classify(c));

			// Perform the action for the curren state.

			switch (state) {
				case SIGN:
					negative = (c == '-');
					break;

				case DIGIT:
					value = 10 * value + (c - '0');
					break;

				default:
					break;
			}
		}

		// Check for errors and return the result.

		if (!state.isFinal()) {
			throw new NumberFormatException(s);
		} else {
			return negative ? -value : value;
		}
	}


	public static void main(String[] args) {
		for (String arg : args) {
			try {
				System.out.println(parseInteger(arg));
			} catch (NumberFormatException e) {
				System.out.println(e);
			}
		}
	}
}
