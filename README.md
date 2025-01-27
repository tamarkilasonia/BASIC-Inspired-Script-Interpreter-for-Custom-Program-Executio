Project: BASIC language interpreter OVERVIEW-This project implements a simple interpreter for a subset of the BASIC programming language. The interpreter processes and executes code written in a BASIC-like syntax, supporting constructs such as variable declarations, loops (FOR, WHILE), conditionals (IF-THEN-ELSE), and PRINT statements. The goal of this project is to demonstrate the construction of a minimal yet functional interpreter capable of parsing and executing BASIC code in Java.

Features

-Variable Declaration: Using the dim keyword for declaring integer and string variables.

-Assignment: Assign values or evaluate expressions to assign to variables.

-Arithmetic and Logical Operations: Support for addition, subtraction, multiplication, division, modulo, and relational operators.

-Control Structures:

-FOR loops with support for numeric ranges.

-WHILE loops for condition-based iteration.

-IF-THEN-ELSE conditionals for branching logic.

-PRINT Statements: Output values, strings, or expressions to the console.

-Error Handling: Robust error handling for invalid syntax or unsupported operations. Here's a detailed expansion of the algorithm descriptions, focusing on how each algorithm operates step by step:

Sum of First N Numbers Algorithm:
-Initialize a variable sum to 0 to store the cumulative sum.

-Define a variable n to specify the range (e.g., n = 10).

-Use a FOR loop that iterates from 1 to n.

-On each iteration, add the loop variable i to sum.

-After the loop ends, print the value of sum along with a descriptive message.

-dim sum as integer sum = 0 dim n as integer n = 10 FOR i = 1 TO n sum = sum + i NEXT PRINT "Sum of first "; n; " numbers is: "; sum

Factorial Calculation Algorithm:
Initialize a variable fact to 1 to store the product of numbers. Set the input variable n to the desired number (e.g., n = 5). Use a FOR loop that iterates from 1 to n. On each iteration, multiply fact by the loop variable i and update fact. After the loop completes, print the factorial result with a descriptive message. Purpose: Illustrates multiplication and how loops can be used for repetitive computation.

dim fact as integer fact = 1 dim n as integer n = 5 FOR i = 1 TO n fact = fact * i NEXT PRINT "Factorial of "; n; " is: "; fact

Greatest Common Divisor (GCD) Algorithm:
Define two integers a and b for which the GCD will be calculated. While b is not zero: Assign b to a temporary variable temp. Update b to a MOD b (remainder when a is divided by b). Set a to temp. When the loop ends, a contains the GCD. Print the result. Purpose: Demonstrates the Euclidean algorithm using arithmetic and WHILE loops.

dim a as integer dim b as integer a = 56 b = 98 dim temp as integer WHILE b != 0 temp = b b = a MOD b a = temp WEND PRINT "GCD is: "; a

Check if a Number is Palindrome Algorithm:
Defined four integers num, original, reverse, digit and need to determine if num is a palindrome. Assign the input number to noth num and original. Reverse the number using a WHILE loop, extract the las degit using MOD 10, append the digit to reversed, remove the last digit by dividing num bu 10. Compare original and reversed.

dim num as integer dim original as interer dim reversed as integer dim digir as integer num = 12321 original = num revwrsed = 0 WHILE num != 0 digit = num MOD 10 reverseg = reversed * 10 + digit num = num/10 WEND IF original == reversed THEN PRINT original;"is a palindrome." ELSE original;"is not a palindrome." ENDIF PRINT original; " is not a palindrome." ENDIF

Sum of difits Algorithm:
Define three integers N (input number), SUM (to store the sum of digits), and DIGIT (to hold the current digit). Initialize SUM to 0. Extract each digit using a WHILE loop, extract the last digit using MOD 10, add the digit to SUM, remove the last digit by dividing N by 10. Print the total sum of the digits

dim N as integer dim SUM as integer dim DIGIT as integer N = 1234 SUM = 0 WHILE N != 0 DIGIT = N MOD 10 SUM = SUM + DIGIT N = N / 10 WEND PRINT "The sum of the digits is: "; SUM

6.Reverse a Number Algorithm:

This program reverses a given integer. Define integers: N (input number), REVERSED (to store the reversed number), and DIGIT (to hold the current digit during reversal). Initialize REVERSED to 0. Reverse the number using a WHILE loop. Extract the last digit using MOD 10, append the digit to REVERSED, remove the last digit by dividing N by 10.

dim N as integer dim REVERSED as integer dim DIGIT as integer N = 456789 REVERSED = 0 WHILE N != 0 DIGIT = N MOD 10 REVERSED = REVERSED * 10 + DIGIT N = N / 10 WEND PRINT "The reversed number is: "; REVERSED

7.Find the Largest Digit in a Number Algorithm:

Define integers: N (input number), LARGEST (to store the largest digit found), and DIGIT (to hold the current digit). Initialize LARGEST to 0. Extract and compare each digit using a WHILE loop: Extract the last digit using MOD 10. If the digit is greater than LARGEST, update LARGEST. Remove the last digit by dividing N by 10. Print the largest digit.

dim N as integer dim LARGEST as integer dim DIGIT as integer N = 457839 LARGEST = 0 WHILE N != 0 DIGIT = N MOD 10 IF DIGIT > LARGEST THEN LARGEST = DIGIT ENDIF N = N / 10 WEND PRINT "The largest digit is: "; LARGEST

8.Check if a Number is Prime Algorithm:

Define integers: num (input number), isPrime (flag to indicate if the number is prime), i (loop counter), and limit (half of the input number). Initialize isPrime to 1, assume the number is prime. Check if num is less than or equal to 1. If true, set isPrime to 0 (not prime). Otherwise, use a FOR loop to test divisors from 2 to limit. If num MOD i equals 0, set isPrime to 0 and exit the loop. After the loop, check the value of isPrime: If 1, print that the number is prime. Otherwise, print that it is not prime.

dim num as integer dim isPrime as integer dim i as integer dim limit as integer num = 29 isPrime = 1 limit = num / 2 IF num <= 1 THEN isPrime = 0 ELSE FOR i = 2 TO limit IF num MOD i == 0 THEN isPrime = 0 EXIT ENDIF NEXT ENDIF

IF isPrime == 1 THEN PRINT num; " is a prime number." ELSE PRINT num; " is not a prime number." ENDIF

9.Multiplication table Algorithm:

This generates and prints the multiplication table for a given number. Define three integers num (the number for which the multiplication table is generated), i (loop counter), and result (result of multiplication). Use a FOR loop to iterate from 1 to 10. Multiply num by the current value of i. Store the result in result. Print the multiplication in the format "num * i = result".

dim num as integer dim i as integer dim result as integer num = 5 PRINT "Multiplication Table for "; num FOR i = 1 TO 10 result = num * i PRINT num; " * "; i; " = "; result NEXT

10.Fibonacci Algorithm :

This generates and prints the Fibonacci sequence up to a specified number of terms. Define three integers: n1 (first term of the sequence), n2 (second term of the sequence), and next (next term in the sequence). Use a FOR loop to iterate through the desired number of terms. Print n1 and calculate the next term as the sum of n1 and n2. Assign n2 to n1 and next to n2 to update the values for the next iteration.

dim n1 as integer dim n2 as integer dim next as integer dim terms as integer

n1 = 0 n2 = 1 PRINT "Fibonacci Sequence:"

PRINT n1 PRINT n2

FOR i = 3 TO terms next = n1 + n2 PRINT next n1 = n2 n2 = next NEXT

Explanation:

-Initialize the first two terms of the Fibonacci sequence (n1 = 0 and n2 = 1). -Start the sequence by printing the first two terms (n1 and n2). -Use a FOR loop to calculate and print subsequent terms up to the number of terms specified (terms). -Update n1 and n2 in each iteration to continue the sequence.
