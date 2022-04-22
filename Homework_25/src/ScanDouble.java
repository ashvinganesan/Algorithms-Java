
public class ScanDouble {

    public static enum Input {
        MINUS,
        PLUS,
        DIGIT,
        UNDER,
        DOT,
        EXP,
        OTHER;

        public static Input classify(char c) {
            switch (c) {
                case '-':
                    return MINUS;
                case '+':
                    return PLUS;
                case '_':
                    return UNDER;
                case '.':
                    return DOT;
                case 'e':
                case 'E':
                    return EXP;

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
                    case PLUS:
                        return State.SIGN;
                    case MINUS:
                        return State.SIGN;
                    case DIGIT:
                        return State.DIGIT;
                    //case DOT: return State.DOT;
                    default:
                        return State.ERROR;
                }
            }
        },
        SIGN(false) {
            @Override
            public State next(Input c) {
                switch (c) {
                    case DIGIT:
                        return State.DIGIT;
                    default:
                        return State.ERROR;
                }
            }
        },
        DIGIT(true) {
            @Override
            public State next(Input c) {
                switch (c) {
                    case DIGIT:
                        return State.DIGIT;
                    case UNDER:
                        return State.UNDER;
                    case DOT:
                        return State.DOT;
                    case EXP:
                        return State.EXP;
                    default:
                        return State.ERROR;
                }
            }
        },
        UNDER(false) {
            @Override
            public State next(Input c) {
                switch (c) {
                    case DIGIT:
                        return State.DIGIT;
                    default:
                        return State.ERROR;
                }
            }
        },
        DOT(false) {
            @Override
            public State next(Input c) {
                switch (c) {
                    case DIGIT:
                        return State.DIGIT_FRACTION;

                    default:
                        return State.ERROR;
                }
            }
        },
        DIGIT_FRACTION(true) {
            @Override
            public State next(Input c) {
                switch (c) {
                    case DIGIT:
                        return State.DIGIT_FRACTION;
                    case UNDER:
                        return State.UNDER_FRACTION;
                    case EXP:
                        return State.EXP;
                    default:
                        return State.ERROR;
                }
            }
        },
        EXP(false) {
            @Override
            public State next(Input c) {
                switch (c) {
                    case DIGIT:
                        return State.DIGIT_EXP;
                    case PLUS:
                        return State.PLUS_EXP;
                    case MINUS:
                        return State.MINUS_EXP;
                    default:
                        return State.ERROR;
                }
            }

        },
        DIGIT_EXP(true) {
            @Override
            public State next(Input c) {
                switch (c) {
                    case DIGIT:
                        return State.DIGIT_EXP;
                    case UNDER:
                        return State.UNDER_EXP;
                    default:
                        return State.ERROR;
                }
            }

        },
        PLUS_EXP(false) {
            @Override
            public State next(Input c) {
                switch (c) {
                    case DIGIT:
                        return State.DIGIT_EXP;
                    default:
                        return State.ERROR;
                }
            }

        },
        MINUS_EXP(false) {
            @Override
            public State next(Input c) {
                switch (c) {
                    case DIGIT:
                        return State.DIGIT_EXP;
                    default:
                        return State.ERROR;
                }
            }

        },
        ERROR(false) {
            @Override
            public State next(Input c) {
                return State.ERROR;
            }
        },
        UNDER_FRACTION(false) {
            @Override
            public State next(Input c) {
                switch (c) {
                    case DIGIT:
                        return State.DIGIT_FRACTION;
                    default:
                        return State.ERROR;
                }
            }
        },
        UNDER_EXP(false) {
            @Override
            public State next(Input c) {
                switch (c) {
                    case DIGIT:
                        return State.DIGIT_EXP;
                    default:
                        return State.ERROR;
                }
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

    public static boolean isValidDouble(String s) {
        State state = State.START;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            state = state.next(Input.classify(c));
        }
        return state.isFinal();
    }

    public static Double parseDouble(String s) {
        State state = State.START;
        boolean negative = false;
        double value = 0.0;
        int eVal = 0;
        int eSign = 1;
        double k = 10.0;

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
                    //System.out.println("Value is " + value);
                    break;
//                case DOT:
//                    System.out.println("in DOT");
//                    System.out.println(value);
//                    break;
                case DIGIT_FRACTION:
//                    System.out.println("IN DIGIT_FRAC");
//                    System.out.println("value is " + value);
//                    System.out.println("c is " + c);
//                    System.out.println("k is " + k);
//                    System.out.println("delta is " + (c - '0') / k);
                    value = value + (c - '0') / k;
                    k = k * 10;
                    //System.out.println("value is " + value);
                    break;
                case MINUS_EXP:
                    if(c == '-')
                        eSign = -1;
                    break;
                case DIGIT_EXP:
//                    System.out.println("IN DIGIT_EXP");
                    eVal = 10 * eVal + (c - '0'); 
//                    System.out.println("eVal is " + eVal);
                default:
                    break;
            }
        }

        // Check for errors and return the result.
        if (!state.isFinal()) {
            throw new NumberFormatException(s);
        } else {
//            System.out.println("value is " + value);
            return negative ? -value*Math.pow(10,(eSign*eVal)) : value*Math.pow(10,(eSign*eVal));
        }
    }

    public static void main(String[] args) {
        for (String arg : args) {
//            System.out.println("Converting: " + arg);
            try {
                System.out.println(parseDouble(arg));
            } catch (NumberFormatException e) {
                System.out.println(e);
            }
        }
    }
}
