//Ashvin G
//Algorithms H
// HW 24
//friday may 14th 2021

public class Homework_24 {

	// Many operators have been given other names in order to allow them to be typed
    // more easily on the command line (and to distiguish different operators that
	// have the same name (e.g., pre- and post- increment operators).

	public static Expression.Node buildUnary(String operator, Expression.Node operand) {
		switch (operator) {
                    // ++ and -- was initially not working as they were not included in this method. 
			case "neg":    return new Expression.Negate(operand);
			case "abs":    return new Expression.Abs(operand);
			case "pre++":  return new Expression.PreIncrement((Expression.Variable) operand);
			case "pre--":  return new Expression.PreDecrement((Expression.Variable) operand);
                        case "++":     return new Expression.PostIncrement((Expression.Variable) operand);
                        case "--":     return new Expression.PostDecrement((Expression.Variable) operand);
			case "post++": return new Expression.PostIncrement((Expression.Variable) operand);
			case "post--": return new Expression.PostDecrement((Expression.Variable) operand);
			default:       return null;
		}
	}

	public static Expression.Node buildBinary(String operator, Expression.Node left, Expression.Node right) {
		switch (operator) {
			case "add": case "+": return new Expression.Add(left, right);
			case "sub": case "-": return new Expression.Subtract(left, right);
			case "mul": case "*": return new Expression.Multiply(left, right);
			case "div": case "/": return new Expression.Divide(left, right);
			default:              return null;
		}
	}

	public static Expression.Node buildAssign(String operator, Expression.Variable left, Expression.Node right) {
		switch (operator) {
			case "=":               return new Expression.Assign(left, right);
			case "add=": case "+=": return new Expression.PlusEquals(left, right);
			case "sub=": case "-=": return new Expression.MinusEquals(left, right);
			case "mul=": case "*=": return new Expression.TimesEquals(left, right);
			case "div=": case "/=": return new Expression.DivideEquals(left, right);
			default:                return null;
		}
	}

	public static void main(String[] args) {
		Stack<Expression.Node> operands = new LinkedListStack<>();
		Expression.Node left, right, operand, result;
		double value;

		for (String arg : args) {
			switch (arg) {
				case "+": case "add":
				case "-": case "sub":
				case "*": case "mul":
				case "/": case "div":
					right = operands.pop();
					left = operands.pop();
					operands.push(buildBinary(arg, left, right));
					break;
	
				case "=":
				case "+=": case "add=":
				case "-=": case "sub=":
				case "*=": case "mul=":
				case "/=": case "div=":
					right = operands.pop();
					left = operands.pop();
					operands.push(buildAssign(arg, (Expression.Variable) left, right));
					break;
	
				case "neg":
				case "abs":
				case "pre++":
				case "pre--":
				case "post++": case "++":
				case "post--": case "--":
					operand = operands.pop();
					operands.push(buildUnary(arg, operand));
					break;

				case ",":
					operands.pop().evaluate();
					break;

				case "eval":
					System.out.println(operands.top().evaluate());
					break;

				case "print":
					System.out.println(operands.top().format());
					break;

				default:
					try {
						value = Double.parseDouble(arg);
						result = new Expression.Literal(value);
						operands.push(result);
					} catch (NumberFormatException e) {
						result = new Expression.Variable(arg);
						operands.push(result);
					}
					break;
			}
		}
	}
}
