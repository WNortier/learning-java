# CHAPTER 3: ASSIGNMENTS

- ## [1 Stack & Heap](#1_Stack_&_Heap)
- ## [2 Literals, Assignments & Variables](#2_Literals,_Assignments_&_Variables)
- ## [3 Scope](#3_Scope)
- ## [4 Variable Initialization](#4_Variable_Initialization)
- ## [5 Passing Variables Into Methods](#5_Passing_Variables_Into_Methods)
- ## [6 Garbage Collection](#6_Garbage_Collection)

# <a name="1_Stack_&_Heap"></a> 1 Stack & Heap

For the most part, the various pieces (methods, variables, and objects) of Java programs live in one of two places in memory: the stack or the heap. For now, we're concerned about only three types of things—instance variables, local variables, and objects:

- ### Instance variables and objects live on the heap.
- ### Local variables live on the stack.

![stack-and-heap-1](images/stack-and-heap-1.png)

![stack-and-heap-2](images/stack-and-heap-2.png)

# <a name="2_Literals,_Assignments_&_Variables"></a> 2 Literals, Assignments & Variables

### OCA Objectives

**2.1 Declare and initialize variables (including casting of primitive data types).**
**2.2 Differentiate between object reference variables and primitive variables.**
**2.3 Know how to read or write to object fields.**

# Literals 

## Literal Values for All Primitive Types

A primitive literal is merely a source code representation of the primitive data types—in other words, an ***integer***, ***floating-point number***, ***boolean***, or ***character*** that you type in while writing code. The following are examples of primitive literals:
```java
'b'           // char literal
42            // int literal
false         // boolean literal
2546789.343   // double literal
```

## Integer Literals

***There are four ways to represent integer numbers in the Java language: decimal (base 10), octal (base 8), hexadecimal (base 16), and, as of Java 7, binary (base 2).***

> #### Most exam questions with integer literals use decimal representations, but the few that use octal, hexadecimal, or binary are worth studying for. The odds that you'll use octal in the real world are slim, but they were included in the exam just for fun.

#### Numeric literals with underscores

As of Java 7, numeric literals can be declared using underscore characters (_), ostensibly to improve readability.

```java
int pre7 = 1000000; // pre Java 7 – we hope it's a million
int with7 = 1_000_000; // much clearer!
```
Remember: 

- 1 **You CANNOT use the underscore literal at the beginning or end of the literal.** The potential gotcha here is that you're free to use the underscore in "weird" places: 

- 2 You can use the underscore character for any of the numeric types (including doubles and floats), but for doubles and floats, **you CANNOT add an underscore character directly next to the decimal point**, or **next to the X or B in hex or binary numbers** (which are coming up soon).

```java
int i1 = _1_000_000;  // illegal, can't begin with an "_" 
int i2 = 10_0000_0;   // legal, but confusing
float f1 = 32_,1      // illegal, can't place _ next to decimal point
```
___

### 1 Decimal

Decimal integers need no explanation; in Java they are presented as-is with no prefix of any kind. 

```java
int length = 323;
```

### 2 Binary

Also new to Java 7 is the addition of binary literals. 
- Binary literals can use only the digits `0` and `1`. 
- Binary literals must start with either `0B` or `0b`, as shown:

```java
int b1 = 0B101010;   // set b1 to binary 101010 (decimal 42)
int b2 = 0b00011;    // set b2 to binary 11 (decimal 3)
```

### 3 Octal

- Octal integers use only the digits `0` to `7`. 
- You represent an integer in octal form by placing a zero in front of the number.
- You can have up to 21 digits in an octal number, not including the leading 0.

```java
class Octal {
  public static void main(String [] args) {
    int six = 06;     // Equal to decimal 6
    int seven = 07;   // Equal to decimal 7
    int eight = 010;  // Equal to decimal 8
    int nine = 011;   // Equal to decimal 9
    System.out.println("Octal 010 = " + eight);
  }
}
```
If we run the preceding program, it displays the following:

```java
Octal 010 = 8
```

### 4 Hexadecimal 

- Hexadecimal (hex for short) numbers are constructed using 16 distinct symbols. 
- Because we never invented single-digit symbols for the numbers 10 through 15, we use alphabetic characters to represent these digits. 
- Java will accept uppercase or lowercase letters for the extra digits (one of the few places Java is not case sensitive!).
- You are allowed up to 16 digits in a hexadecimal number, not including the prefix 0x (or 0X) or the optional suffix extension L, which will be explained a bit later in the chapter.

Counting from 0 through 15 in hex looks like this:

`0 1 2 3 4 5 6 7 8 9 a b c d e f`

All of the following hexadecimal assignments are legal:

```java
class HexTest {
  public static void main (String [] args) {
    int x = 0X0001;
    int y = 0x7fffffff;
    int z = 0xDeadCafe;
    System.out.println("x = " + x + " y = " + y + " z = " + z);
  }
}
```

Running HexTest produces the following output:

`x = 1 y = 2147483647 z = -559035650`

Don't be misled by changes in case for a hexadecimal digit or the x preceding it. 0XCAFE and 0xcafe are both legal and have the same value. All four integer literals (binary, octal, decimal, and hexadecimal) are defined as int by default, but they may also be specified as long by placing a suffix of L or l after the number:

```java
long jo = 110599L;
long so = 0xFFFFl; // Note the lowercase 'l'
```

## Floating point literals

Floating-point numbers are defined as a number, a decimal symbol, and more numbers representing the fraction. In the following example, the number `11301874.9881024` is the literal value: 

`double d = 11301874.9881024;`

> #### Floating-point literals are defined as double (64 bits) by default, so if you want to assign a floating-point literal to a variable of type float (32 bits), you must attach the suffix `F` or `f` to the number. 

If you don't do this, the compiler will complain about a possible loss of precision, because you're trying to fit a number into a (potentially) less precise "container." The `F` suffix gives you a way to tell the compiler, "Hey, I know what I'm doing, and I'll take the risk, thank you very much."

```java
float f = 23.467890;        // Compiler error, possible loss of precision
float g = 49837849.029847F; // OK; has the suffix "F"
```

You may also optionally attach a D or d to double literals, but it is not necessary because this is the default behavior.
```java
double d = 110599.995011D;  // Optional, not required
double g = 987.897;         // No 'D' suffix, but OK because the literal is a double by default
```
Look for numeric literals that include a comma; here's an example:

```java
int x = 25,343;             // Won't compile because of the comma
```

## Boolean literals 

Boolean literals are the source code representation for boolean values. A boolean value can be defined only as true or false.

```java
boolean t = true;           // Legal
boolean f = 0;              // Compiler error!
```

Be on the lookout for questions that use numbers where booleans are required. You might see an if test that uses a number, as in the following:

`int x = 1; if (x) { } // Compiler error!`

## Character literals 

A char literal is represented by a single character in single quotes:
```java
char a = 'a';
char b = '@';
```

You can also type in the Unicode value of the character, using the Unicode notation of prefixing the value with \u, as follows: 

```java
char letterN = '\u004E';    // The letter 'N'
```

> #### Remember, characters are just 16-bit unsigned integers under the hood. That means you can assign a number literal, assuming it will fit into the unsigned 16-bit range (0 to 65535). 

For example, the following are all legal:

```java
char a = 0x892;             // hexadecimal literal
char b = 982;               // int literal
char c = (char)70000;       // The cast is required; 70000 is out of char range
char d = (char) -98;        // Ridiculous, but legal
```

And the following are not legal and produce compiler errors:

```java
char e = -29;               // Possible loss of precision; needs a cast
char f = 70000;             // Possible loss of precision; needs a cast
```

You can also use an escape code (the backslash) if you want to represent a character that can't be typed in as a literal, including the characters for linefeed, newline, horizontal tab, backspace, and quotes:

```java
char c = '\"';              // A double quote
char d = '\n';              // A newline
char tab = '\t';            // A tab
```

A string literal is a source code representation of a value of a String object. Although strings are not primitives, they're included in this section because they can be represented as literals—in other words, they can be typed directly into code.

The following is an example of two ways to represent a string literal:
```java
String s = "Bill Joy";
System.out.println("Bill" + " Joy");
```

The only other nonprimitive type that has a literal representation is an array, which we'll look at later in the chapter.
```java
Thread t = ??? // what literal value could possibly go here?
```
___

# Assignments 

## Assignment Operators

Assigning a value to a variable seems straightforward enough; you simply assign the stuff on the right side of the = to the variable on the left. Well, sure, but don't expect to be tested on something like this:

`x = 6;`

> #### You will, however, be tested on the trickier assignments involving complex expressions and casting. We'll look at both primitive and reference variable assignments.


Before we begin, let's back up and peek inside a variable. What is a variable? How are the variable and its value related?

![literals-assignments-variables-exam-watch](images/literals-assignments-variables-exam-watch-1.png)

![literals-assignments-variables-exam-watch](images/literals-assignments-variables-exam-watch-2.png)


# <a name="3_Scope"></a> 3 Scope

# <a name="4_Variable_Initialization"></a> 4 Variable Initialization

# <a name="5_Passing_Variables_Into_Methods"></a> 5 Passing Variables Into Methods

# <a name="6_Garbage_Collection"></a> 6 Garbage Collection

