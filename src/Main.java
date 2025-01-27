import java.util.HashMap;
import java.util.Map;

public class Main {
    //Maps to store integer and string variables
    private final Map<String, Integer> intVariables = new HashMap<>();
    private final Map<String, String> stringVariables = new HashMap<>();

    //Main evaluation method that prcesses a given code string line by line

    public void eval(String code) {
        try {
            String[] lines = code.split("\n"); //Spliting the code into lines
            int i = 0; //Initialize the line index to 0
            while (i < lines.length) { //Iterate through each line
                String line = lines[i].trim(); //Trim spaces from the line
                if (line.isEmpty()) { //Skip empty lines
                    i++;
                    continue;
                }
                //Determine the type of statement and handle it aapropriately
                if (line.startsWith("FOR")) {
                    i = handleFor(line, lines, i); //Handle FOR loops
                } else if (line.startsWith("WHILE")) {
                    i = handleWhile(line, lines, i); // Handle WHILE loops
                } else if (line.startsWith("PRINT")) {
                    handlePrint(line); //Handle PRINT statements
                } else if (line.startsWith("dim")) {
                    handleDim(line); //Handle variable declarations
                } else if (line.startsWith("IF")) {
                    i = handleIf(line, lines, i); //Handle IF-ELSE conditional statements
                } else if (line.contains("=")) {
                    handleAssignment(line); //Handle variable assignment
                }
                i++; //moving to the next line
            }
        }catch (Exception e) {
            //Catch and report any runtime errors
            System.err.println("Error while evaluating code: " +e.getMessage());
        }
    }
    //Handle variable declarations with "dim" keyword

    private void handleDim(String line) {
        try{
            String[] parts = line.split(" as "); //Split declaration into name and type
            if (parts.length != 2) { //Check if the declaration format is valid
                throw new IllegalArgumentException("Invalid dim statement: " + line);
            }
            String varName = parts[0].replace("dim", "").trim(); //Extract variable name
            String varType = parts[1].trim(); //Extract variable type
            if ("integer".equals(varType)) {
                intVariables.put(varName, 0); //Initialize integer variables to 0
            } else {
                throw new IllegalArgumentException("Unsupported type: " + varType);
            }
        }
        catch (Exception e) {
            System.err.println("Error in dim statement: " + e.getMessage());
        }
    }

    //Handle assignment statements

    private void handleAssignment(String line) {
        try{
            String[] parts = line.split("="); //Split assignment into variable name and value
            if (parts.length != 2) { //Check if the assignment format is valid
                throw new IllegalArgumentException("Invalid assignment: " + line);
            }
            String varName = parts[0].trim(); //Extract name
            String value = parts[1].trim(); //Extract value to be assigned
            if (value.startsWith("\"") && value.endsWith("\"")) {
                //Handle string assignments by removing quotes
                stringVariables.put(varName, value.substring(1, value.length() - 1)); //Handle string assignments
            } else {
                //HAndle integer assignments by evaluating the expression
                int intValue = evaluateExpression(value); //Evaluate integer expressions.
                intVariables.put(varName, intValue);
            }
        }
        catch (Exception e) {
            System.err.println("Error in assignment: " + e.getMessage());
        }
    }
    //Evalute mathematical expressions
    private int evaluateExpression(String expression) {
        try{
            String[] tokens = expression.split("\\s+"); //Split the expression into tokens by spaces
            int result = resolveValue(tokens[0]); //Initialize the result with the first token's value
            for (int i = 1; i < tokens.length; i += 2) { //loop through operators and operands
                String operator = tokens[i]; //Extract the operator
                int operand = resolveValue(tokens[i + 1]); //Resolve the next operand's value
                //Perform the appropriate operation based on the operator
                switch (operator.toUpperCase()) {
                    case "+" -> result += operand;
                    case "-" -> result -= operand;
                    case "*" -> result *= operand;
                    case "/" -> result /= operand;
                    case "MOD", "%" -> result %= operand;
                    default -> throw new IllegalArgumentException("Unsupported operator: " + operator);
                }
            }
            return result;
        }
        catch (Exception e) {
            System.err.println("Error in expression evaluation: " + e.getMessage());
            return 0; // Return a default value on error
        }
    }
    //Resolve a token into corresponding value (either a variable or literal)
    private int resolveValue(String token) {
        try{
            if (intVariables.containsKey(token)) { //If the token is an integer variable
                return intVariables.get(token);
            } else {
                try {
                    return Integer.parseInt(token); //Parse the token as an integer literal
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid value: " + token);
                }
            }
        }
        catch (NumberFormatException e) {
            System.err.println("Invalid value: " + token);
            return 0; // Return a default value on error
        }
    }
    //Handle PRINT statement
    private void handlePrint(String line) {
        String content = line.substring(5).trim();
        String[] parts = content.split(";");
        StringBuilder output = new StringBuilder();
        for (String part : parts) {
            part = part.trim();
            if (stringVariables.containsKey(part)) {
                output.append(stringVariables.get(part)).append(" ");
            } else if (intVariables.containsKey(part)) {
                output.append(intVariables.get(part)).append(" ");
            } else {
                output.append(part).append(" ");
            }
        }
        System.out.println(output.toString().trim()); //Print the final output
    }
    //Handle FOR loops
    private int handleFor(String line, String[] lines, int index) {
        try{
            String[] parts = line.split(" "); //Split the FOR line into parts
            if (parts.length != 6 || !"TO".equals(parts[4])) { //Check if the loop syntax is valid
                throw new IllegalArgumentException("Invalid FOR loop syntax: " + line);
            }

            String varName = parts[1];//Extract the loop variable name
            int start = evaluateExpression(parts[3]); //Evaluate the starting value
            int end = evaluateExpression(parts[5]); //Evaluate the ending value
            intVariables.put(varName, start); //Initialize the lop variable with the starting value

            int loopStart = index + 1; //Determine the starting index of the loop body
            while (intVariables.get(varName) <= end) { //Loop until the variable exceed the end value
                int i = loopStart;

                // This loop will now iterate through the loop body, executing each line
                while (i < lines.length && !lines[i].trim().equals("NEXT")) {
                    String innerLine = lines[i].trim();
                    if (!innerLine.isEmpty()) {
                        eval(innerLine); // Execute inner line
                    }
                    i++;
                }

                intVariables.put(varName, intVariables.get(varName) + 1);
            }
//skip the NEXT statement
            while (index < lines.length && !lines[index].trim().equals("NEXT")) {
                index++;
            }

            return index;
        }
        catch (Exception e) {
            System.err.println("Error in FOR loop: " + e.getMessage());
            return index;
        }
    }

    //Handle WHILE loops
    private int handleWhile(String line, String[] lines, int index) {
        try{
            String condition = line.substring(6).trim();
            int startIndex = index + 1;
            while (evaluateCondition(condition)) {
                int i = startIndex;
                //Iterate through the loop body and excute each line
                while (i < lines.length && !lines[i].trim().equals("WEND")) {
                    eval(lines[i].trim());
                    i++;
                }
            }
            //Skip the WEND statement and return the updated index
            while (index < lines.length && !lines[index].trim().equals("WEND")) {
                index++;
            }
            return index;
        }
        catch (Exception e) {
            System.err.println("Error in WHILE loop: " + e.getMessage());
            return index;
        }
    }
    //Evaluate conditions in IF and WHILE statements
    private boolean evaluateCondition(String condition) {
        String[] parts = condition.split(" ");
        int left = resolveValue(parts[0]);
        int right = resolveValue(parts[2]);
        return switch (parts[1].toUpperCase()) {
            case "!=" -> left != right;
            case ">" -> left > right;
            case "<" -> left < right;
            case ">=" -> left >= right;
            case "<=" -> left <= right;
            case "==" -> left == right;
            case "MOD" -> left % right == 0;
            default -> throw new IllegalArgumentException("Unsupported operator: " + parts[1]);
        };
    }

    //Handle IF statemenets with optional ELSE blocks.
    private int handleIf(String line, String[] lines, int index) {
        try{
            String condition = line.substring(2, line.indexOf("THEN")).trim();
            boolean conditionResult = evaluateCondition(condition);
            if (conditionResult) {
                int i = index + 1;
                while (i < lines.length && !lines[i].trim().equals("ELSE") && !lines[i].trim().equals("ENDIF")) {
                    eval(lines[i].trim());
                    i++;
                }
                while (i < lines.length && !lines[i].trim().equals("ENDIF")) {
                    i++;
                }
                return i;
            } else {
                int i = index + 1;
                while (i < lines.length && !lines[i].trim().equals("ELSE") && !lines[i].trim().equals("ENDIF")) {
                    i++;
                }
                if (i < lines.length && lines[i].trim().equals("ELSE")) {
                    i++;
                    while (i < lines.length && !lines[i].trim().equals("ENDIF")) {
                        eval(lines[i].trim());
                        i++;
                    }
                }
                return i;
            }
        }
        catch (Exception e) {
            System.err.println("Error in IF statement: " + e.getMessage());
            return index;
        }
    }

    public static void main(String[] args) {
        Main interpreter = new Main();

        String program = """
                    dim sum as integer
                    sum = 0
                    dim n as integer
                    n = 10
                    FOR i = 1 TO n
                        sum = sum + i
                    NEXT
                    PRINT "Sum of first "; n; " numbers is: "; sum
                """;
        interpreter.eval(program);

        String program2 = """
                    dim fact as integer
                    fact = 1
                    dim n as integer
                    n = 5
                    FOR i = 1 TO n
                        fact = fact * i
                    NEXT
                    PRINT "Factorial of "; n; " is: "; fact
                """;
        interpreter.eval(program2);

        String program3 = """
                    dim a as integer
                    dim b as integer
                    a = 56
                    b = 98
                    dim temp as integer
                    WHILE b != 0
                        temp = b
                        b = a MOD b
                        a = temp
                    WEND
                    PRINT "GCD is: "; a
                """;
        interpreter.eval(program3);


        String program4 = """
                    dim num as integer
                    dim original as integer
                    dim reversed as integer
                    dim digit as integer
                    num = 12321  
                    original = num
                    reversed = 0
                
                    WHILE num != 0
                        digit = num MOD 10
                        reversed = reversed * 10 + digit
                        num = num / 10
                    WEND
                
                    IF original == reversed THEN
                        PRINT original; " is a palindrome."
                    ELSE
                        PRINT original; " is not a palindrome."
                    ENDIF
                """;
        interpreter.eval(program4);


        //Example: Sum of digits
        String program5 = """
                dim N as integer
                dim SUM as integer
                dim DIGIT as integer
                N = 1234
                SUM = 0
                WHILE N != 0
                DIGIT = N MOD 10
                SUM = SUM + DIGIT
                N = N / 10
                WEND
                PRINT "The sum of the digits is: "; SUM
                """;
        interpreter.eval(program5);
        //Reversing a number
        String program6 = """
                    dim N as integer
                    dim REVERSED as integer
                    dim DIGIT as integer
                    N = 456789
                    REVERSED = 0
                    WHILE N != 0
                        DIGIT = N MOD 10
                        REVERSED = REVERSED * 10 + DIGIT
                        N = N / 10
                    WEND
                    PRINT "The reversed number is: "; REVERSED
                """;
        interpreter.eval(program6);
        //Example: Finding the largest digit in a number

        String program7 = """
                    dim N as integer
                    dim LARGEST as integer
                    dim DIGIT as integer
                    N = 957839
                    LARGEST = 0
                    WHILE N != 0
                        DIGIT = N MOD 10
                        IF DIGIT > LARGEST THEN
                            LARGEST = DIGIT
                        ENDIF
                        N = N / 10
                    WEND
                    PRINT "The largest digit is: "; LARGEST
                """;
        interpreter.eval(program7);

        String program8 = """
                    dim num as integer
                    dim isPrime as integer
                    dim i as integer
                    dim limit as integer
                    num = 30 
                    isPrime = 1
                    limit = num / 2
                    IF num <= 1 THEN
                        isPrime = 0 
                    ELSE
                        FOR i = 2 TO limit
                            IF num MOD i == 0 THEN
                                isPrime = 0
                                EXIT
                            ENDIF
                        NEXT
                    ENDIF
                
                    IF isPrime == 1 THEN
                        PRINT num; " is a prime number."
                    ELSE
                        PRINT num; " is not a prime number."
                    ENDIF
                """;
        interpreter.eval(program8);

        String program9 = """
                    dim num as integer
                    dim i as integer
                    dim result as integer
                    num = 5
                    PRINT "Multiplication Table for "; num
                    FOR i = 1 TO 10
                        result = num * i
                        PRINT num; " * "; i; " = "; result
                    NEXT
                """;
        interpreter.eval(program9);


        String program10 = """
                      String program10= ""
                           dim N as integer
                           dim a as integer
                           dim b as integer
                           dim temp as integer
                           a = 0
                           b = 1
                           N = 10 
                           FOR i = 2 TO N
                               temp = a + b
                               a = b
                               b = temp
                           NEXT
                           PRINT "The "; N; "th Fibonacci number is: "; b
                     
                      
               """;
        interpreter.eval(program10);

    }
}