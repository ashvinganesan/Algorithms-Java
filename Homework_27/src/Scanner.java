/*
Ashvin Ganesan
Homework 27
Wednesday, June 2nd, 2021
 */

public class Scanner {

	private static boolean isDigit(char c) {
		return c >= '0' && c <= '9';
	}

	private static boolean isLetter(char c) {
		return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
	}


	public static enum State {
		START() {
			@Override
			public State next(char c) {
				switch (c) {
					case '\t': return START;
					case ' ':  return START;
					case '=':  return EQUAL;
					case '+':  return PLUS;
					case '-':  return MINUS;
					case '*':  return STAR;
					case '/':  return SLASH;
					case '(':  return LEFT_PAREN;
					case ')':  return RIGHT_PAREN;
					default:
						if (isDigit(c))  return NUMBER;
						if (isLetter(c)) return IDENTIFIER;
						return ERROR;
				}
			}
		},

		PLUS(Token.Kind.PLUS) {
			@Override
			public State next(char c) {
				switch (c) {
					case '+': return PLUS_PLUS;
					case '=': return PLUS_EQUAL;
					default:  return isDigit(c) ? NUMBER : ERROR;
				}
			}
		},

		MINUS(Token.Kind.MINUS) {
			@Override
			public State next(char c) {
				switch (c) {
					case '-': return MINUS_MINUS;
					case '=': return MINUS_EQUAL;
					default:  return isDigit(c) ? NUMBER : ERROR;
				}
			}
		},

		STAR(Token.Kind.STAR) {
			@Override
			public State next(char c) {
				switch (c) {
					case '=': return STAR_EQUAL;
					default:  return ERROR;
				}
			}
		},

		SLASH(Token.Kind.SLASH) {
			@Override
			public State next(char c) {
				switch (c) {
					case '=': return SLASH_EQUAL;
					default:  return ERROR;
				}
			}
		},

		IDENTIFIER(Token.Kind.IDENTIFIER) {
			@Override
			public State next(char c) {
				if (isLetter(c)) return IDENTIFIER;
				if (c == '_') return IDENTIFIER_UNDERSCORE;
				return ERROR;
			}
		},

		IDENTIFIER_UNDERSCORE() {
			@Override
			public State next(char c) {
				if (isLetter(c)) return IDENTIFIER;
				return ERROR;
			}
		},

		NUMBER(Token.Kind.NUMBER) {
			@Override
			public State next(char c) {
				if (isDigit(c)) return NUMBER;
				if (c == '_') return NUMBER_UNDERSCORE;
                                if (c == '.') return NUMBER_DOT;
                                if(c == 'e' || c == 'E') return EXP;
				return ERROR;
			}
		},
                
                NUMBER_DOT() {
                    public State next(char c) {
                        if(isDigit(c)) return NUMBER_FRAC;
                        return ERROR;
                    }
                },
                NUMBER_FRAC(Token.Kind.NUMBER_DOUBLE) {
                    public State next(char c) {
                        if(isDigit(c)) return NUMBER_FRAC;
                        if(c == '_') return NUMBER_FRAC_UNDER;
                        if(c == 'e' || c == 'E') return EXP;
                        return ERROR;
                    }
                },
                NUMBER_FRAC_UNDER() {
                    public State next(char c) {
                        if(isDigit(c)) return NUMBER_FRAC;
                        return ERROR;
                    }
                },
                
                NUMBER_EXP_SIGN() {
                    public State next(char c) {
                        if(isDigit(c)) return NUMBER_EXP;
                        return ERROR;
                    }
                },
                EXP() {
                    @Override
                    public State next(char c) {
                        if(isDigit(c)) return NUMBER_EXP;
                        if(c == '+' || c == '-') return NUMBER_EXP_SIGN;
                        return ERROR;
                    }
                },
                NUMBER_EXP() {
                    @Override
                    public State next(char c) {
                        if(isDigit(c)) return NUMBER_EXP;
                        if(c == '_') return NUMBER_EXP_UNDER;
                        return ERROR;
                    }
                },
                NUMBER_EXP_UNDER() {
                    @Override
                    public State next(char c) {
                        if(isDigit(c)) return NUMBER_EXP;
                        return ERROR;
                    }
                },

		NUMBER_UNDERSCORE() {
			@Override
			public State next(char c) {
				if (isDigit(c)) return NUMBER;
				return ERROR;
			}
		},
                

		EQUAL(Token.Kind.EQUAL),
		PLUS_PLUS(Token.Kind.PLUS_PLUS),
		PLUS_EQUAL(Token.Kind.PLUS_EQUAL),
		MINUS_MINUS(Token.Kind.MINUS_MINUS),
		MINUS_EQUAL(Token.Kind.MINUS_EQUAL),
		STAR_EQUAL(Token.Kind.STAR_EQUAL),
		SLASH_EQUAL(Token.Kind.SLASH_EQUAL),
		LEFT_PAREN(Token.Kind.LEFT_PAREN),
		RIGHT_PAREN(Token.Kind.RIGHT_PAREN),
		ERROR();


		private Token.Kind kind;
		private boolean isFinal;

		private State() {
			this.kind = null;
			this.isFinal = false;
		}

		private State(Token.Kind kind) {
			this.kind = kind;
			this.isFinal = true;
		}

		public boolean isFinal() {
			return this.isFinal;
		}

		public Token.Kind kind() {
			return this.kind;
		}

		public State next(char c) {
			return ERROR;
		}
	}


	private String line;
	private int position;


	public Token nextToken() {
		State state = State.START;
		Token.Kind kind = Token.Kind.END;
		int first = this.position;
		int last = 0;

		while (this.position < this.line.length()) {
			if (state == State.START) {
				first = position;
			}
			char c = this.line.charAt(this.position++);
			state = state.next(c);
			if (state.isFinal()) {
				last = this.position;
				kind = state.kind();
			} else if (state == State.ERROR) {
				this.position = last;
				String image = line.substring(first, last);
				return new Token(kind, image, first);
			}
		}
		String image = line.substring(first, this.position);
		return new Token(kind, image, position);
	}


	public Scanner(String line) {
		this.line = line;
		this.position = 0;
	}

	public static void main(String[] args) {
		for (String arg : args) {
			Scanner scanner = new Scanner(arg);
			Token token;
			do {
				token = scanner.nextToken();
				System.out.println(token.kind() + ": " + token.image());
			} while (token.kind() != Token.Kind.END);
		}
	}
}

