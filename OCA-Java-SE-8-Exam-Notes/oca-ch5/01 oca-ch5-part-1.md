- ## [1 Using if & switch Statements](#1_Using_if_&_switch_Statements)
- ## [2 Creating Loops Constructs](#2_Creating_Loops_Constructs)

# <a name="1_Using_if_&_switch_Statements"></a> 1 Using if & switch Statements

### if-else Branching

The `if` and `switch` statements are commonly referred to as decision statements. When you use decision statements in your program, you're asking the program to evaluate a given expression to determine which course of action to take.

The basic format of an if statement is as follows:

```java
if (booleanExpression) {
    System.out.println("Inside if statement");
}
```

Typically, you're testing something to see if it's `true` and then running a code block (one or more statements) if it is `true` and (optionally) another block of code if it isn't.

```java
if (x > 3) {
    System.out.println("x is greater than 3");
} else {
    System.out.println("x is not greater than 3");
}
```

The following code example is legal (although not recommended for readability):

```java
if (x > 3) // bad practice, but seen on the exam
    y = 2;
z += 8;
a = y + x;
```

Most developers consider it good practice to enclose blocks within curly braces, even if there's only one statement in the block. Be careful with code like the preceding, because you might think it should read as:

"If x is greater than 3, then set y to 2, z to z + 8, and a to y + x."

But the last two lines are going to execute no matter what! They aren't part of the conditional flow. You might find it even more misleading if the code were indented as follows:

```java
if (x > 3)
    y = 2;
    z += 8;
    a = y + x;
```

This brings up the other if-else construct, the if, else if, else.

```java
if (price < 300) {
    buyProduct();
} else if (price < 400) {
    getApproval();
} else {
    dontBuyProduct();
}
```

There are a couple of rules for using else and else if:

- You can have zero or one `else` for a given `if`, and it must come after any `else ifs`.
- You can have zero to many `else ifs` for a given `if`, and they must come before the (optional) `else`.
- Once an `else if` succeeds, none of the remaining `else if`s nor the `else` will be tested.

Sometimes you can have a problem figuring out which if your else should pair with, as follows:

```java
if (exam.done())
if (exam.getScore() < 0.61)
System.out.println("Try again.");
// Which if does this belong to?
else System.out.println("Java master!");
```

We intentionally left out the indenting in this piece of code so it doesn't give clues as to which if statement the else belongs to.

> #### Java law decrees that an else clause belongs to the innermost if statement to which it might possibly belong (in other words, the closest preceding if that doesn't have an else).

In the case of the preceding example, the else belongs to the second if statement in the listing. With proper indenting, it would look like this:

```java
if (exam.done())
    if (exam.getScore() < 0.61)
        System.out.println("Try again.");
// Which if does this belong to?
    else
        System.out.println("Java master!");
```

Following our coding conventions by using curly braces, it would be even easier to read:

```java
if (exam.done()) {
    if (exam.getScore() < 0.61) {
        System.out.println("Try again.");
// Which if does this belong to?
    } else {
        System.out.println("Java master!");
    }
}
```

Be prepared for questions that not only fail to indent nicely but also intentionally indent in a misleading way. Pay close attention for misdirection like the following:

```java
if (exam.done())
    if (exam.getScore() < 0.61)
        System.out.println("Try again.");
else
System.out.println("Java master!"); // Hmmmmm... now where does it belong?
```

Of course, the preceding code is exactly the same as the previous two examples, except for the way it looks.

### Legal Expressions for if Statements

The expression in an if statement must be a boolean expression. Any expression that resolves to a boolean is fine, and some of the expressions can be complex. Assume doStuff() returns true,

```java
int y = 5;
int x = 2;
    if (((x > 3) && (y < 2)) | doStuff()) {
        System.out.println("true");
    }
```

which prints

`true`

You can read the preceding code as, "If both `(x > 3)` and `(y < 2)` are true, or if the result of `doStuff()` is `true`, then print `true`." So, basically, if just `doStuff()` alone is `true`, we'll still get `true`. If`doStuff()` is `false`, though, then both `(x > 3)` and `(y < 2)` will have to be `true` in order to print `true`.

The preceding code is even more complex if you leave off one set of parentheses as follows:

```java
int y = 5;
int x = 2;
    if ((x > 3) && (y < 2) | doStuff()) {
        System.out.println("true");
    }
```

This now prints...nothing! Because the preceding code (with one less set of parentheses) evaluates as though you were saying, "If `(x > 3)` is `true`, and either `(y < 2)` or the result of `doStuff()` is `true`, then print `true`.

> #### Remember that the only legal expression in an if test is a boolean. In some languages, 0 == false and 1 == true. Not so in Java!

The following code shows if statements that might look tempting but are illegal, followed by legal substitutions:

```java
int trueInt = 1;
int falseInt = 0;
if (trueInt) // illegal
if (trueInt == true) // illegal
if (1) // illegal
if (falseInt == false) // illegal
if (trueInt == 1) // legal
if (falseInt == 0) // legal
```

![exam-watch-1](images/exam-watch-1.png)

### switch Statements

You've seen how `if` and `else-if` statements can be used to support both simple and complex decision logic. In many cases, the `switch` statement provides a cleaner way to handle complex decision logic.

```java
int x = 3;
switch (x) {
    case 1:
        System.out.println("x equals 1");
        break;
    case 2:
        System.out.println("x equals 2");
        break;
    default:
        System.out.println("No idea what x is");
}
```

Note: The reason this `switch` statement emulates the `if` is because of the break statements that were placed inside of the `switch`. In general, `break` statements are optional, and as you will see in a few pages, their inclusion or exclusion causes huge changes in how a `switch` statement will execute.

### Legal Expressions for switch and case

The general form of the switch statement is

```java
switch (expression) {
    case constant1: code block
    case constant2: code block
    default: code block
}
```

> #### A switch's expression must evaluate to a char, byte, short, int, an enum (as of Java 5), and a String (as of Java 7). That means if you're not using an enum or a String, only variables and values that can be automatically promoted (in other words, implicitly cast) to an int are acceptable.

You won't be able to compile if you use anything else, including the remaining numeric types of long, float, and double.

A case constant must evaluate to the same type that the switch expression can use, with one additional—and big—constraint: **the case constant must be a compile-time constant!**

Since the case argument has to be resolved at compile time, you can use only a constant or final variable that is immediately initialized with a literal value. It is not enough to be final; it must be a compile-time constant.

Here's an example:

```java
final int a = 1;
final int b;
b = 2;
int x = 0;

switch (x) {
    case a: // ok
    case b: // compiler error
```

Also, the `switch` can only check for equality. This means the other relational operators such as greater than are rendered unusable in a `case`. The following is an example of a valid expression using a method invocation in a `switch` statement. Note that for this code to be legal, the method being invoked on the object reference **must return a value compatible with an int**.

```java
String s = "xyz";
switch (s.length()) {
    case 1:
        System.out.println("length is one");
        break;
    case 2:
        System.out.println("length is two");
        break;
    case 3:
        System.out.println("length is three");
        break;
    default:
        System.out.println("no match");
}
```

One other rule you might not expect involves the question, "What happens if I `switch` on a variable smaller than an int?"

Look at the following `switch`:

```java
byte g = 2;
switch(g) {
    case 23:
    case 128:
}
```

This code won't compile. Although the switch argument is legal—a byte is implicitly cast to an int—the second case argument (`128`) is too large for a byte, and the compiler knows it!

Attempting to compile the preceding example gives you an error something like this:

```java
Test.java:6: possible loss of precision
found : int
required: byte
case 128:
    ^
```

It's also illegal to have more than one case label using the same value. For example, the following block of code won't compile because it uses two cases with the same value of 80:

```java
int temp = 90;
switch(temp) {
    case 80 : System.out.println("80");
    case 80 : System.out.println("80"); // won't compile!
    case 90 : System.out.println("90");
    default : System.out.println("default");
}
```

It is legal to leverage the power of boxing in a `switch` expression. For instance, the following is legal:

```java
switch(new Integer(4)) {
    case 4: System.out.println("boxing is OK");
}
```

![exam-watch-2](images/exam-watch-2.png)

### An intro to String "equality"

We've talked about how we know when primitives are equal, but what does it mean for objects to be equal? This is another one of those surprisingly tricky topics, and for those of you who intend to take the OCP exam, you'll spend a lot of time studying "object equality."

> #### For you OCA candidates, all you have to know is that for a switch statement, two Strings will be considered "equal" if they have the same case-sensitive sequence of characters.

For example, in the following partial switch statement, the expression would match the case:

```java
String s = "Monday";
switch(s) {
case "Monday": // matches!
```

But the following would NOT match:

```java
String s = "MONDAY";
switch(s) {
case "Monday": // Strings are case-sensitive, DOES NOT match
```

### Break and Fall-Through in switch Blocks

The most important thing to remember about the flow of execution through a switch statement is this:

> #### Case constants are evaluated from the top down, and the first case constant that matches the switch's expression is the execution entry point.

In other words, once a case constant is matched, the Java Virtual Machine (JVM) will execute the associated code block and ALL subsequent code blocks (barring a `break` statement), too! The following example uses a `String` in a case statement:

```java
class SwitchString {
    public static void main(String [] args) {
        String s = "green";
        switch(s) {
            case "red": System.out.print("red ");
            case "green": System.out.print("green ");
            case "blue": System.out.print("blue ");
            default: System.out.println("done");
        }
    }
}
```

In this example case "green": matched, so the JVM executed that code block and all subsequent code blocks to produce the output:

`green blue done`

Again, when the program encounters the keyword `break` during the execution of a `switch` statement, execution will immediately move out of the `switch` block to the next statement after the `switch`. If `break` is omitted, the program just keeps executing the remaining case blocks until either a `break` is found or the `switch` statement ends.

> #### This dropping down is actually called "fall-through," because of the way execution falls from one case to the next. Remember, the matching case is simply your entry point into the `switch` block! If you do want that "just the matching code" behavior, you'll insert a `break`.

An interesting example of this fall-through logic is shown in the following code:

```java
int x = someNumberBetweenOneAndTen;
switch (x) {
case 2:
case 4:
case 6:
case 8:
case 10: {
    System.out.println("x is an even number"); break;
}}
```

This `switch` statement will print `x is an even number` or nothing, depending on whether the number is between one and ten and is odd or even. For example, if `x` is `4`, execution will begin at `case 4`, but then fall down through `6`, `8`, and `10`, where it prints and then `breaks`. The `break` at `case 10`, by the way, is not needed; we're already at the end of the `switch` anyway.

Note: Because fall-through is less than intuitive, Oracle recommends that you add a comment such as // fall through when you use fall-through logic.

### The default case

What if, using the preceding code, you wanted to print `x is an odd number` if none of the cases (the even numbers) matched? You couldn't put it after the `switch` statement, or even as the last case in the `switch`, because in both of those situations it would always print `x is an odd number`. To get this behavior, you'd use the _`default`_ keyword. The only change we need to make is to add the default case to the preceding code:

```java
int x = someNumberBetweenOneAndTen;
switch (x) {
case 2:
case 4:
case 6:
case 8:
case 10: { System.out.println("x is even"); break; }
default: System.out.println("x is an odd number");
}
```

![exam-watch-3](images/exam-watch-3.png)

# <a name="2_Creating_Loops_Constructs"></a> 2 Creating Loops Constructs

Java loops come in three flavors: `while`, `do`, and `for` (and as of Java 5, the for loop has two variations). All three let you repeat a block of code as long as some condition is `true` or for a specific number of iterations.

### 1 Using while loops

The while loop is good when you don't know how many times a block or statement should repeat but you want to continue looping as long as some condition is `true`.

A while statement looks like this:

```java
while (expression) {
// do stuff
}
```

Or this:

```java
int x = 2;
while(x == 2) {
    System.out.println(x);
    ++x;
}
```

In this case, as in all loops, the expression (test) must evaluate to a boolean result. The body of the while loop will execute only if the expression (sometimes called the "condition") results in a value of true. Once inside the loop, the loop body will repeat until the condition is no longer met because it evaluates to false. In the previous example, program control will enter the loop body because x is equal to 2. However, x is incremented in the loop, so when the condition is checked again it will evaluate to false and exit the loop. _Any variables used in the expression of a while loop must be declared before the expression is evaluated._

In other words, you can't say this:

`while (int x = 2) { } // not legal`

The key point to remember about a `while` loop is that it might not ever run. If the test expression is `false` the first time the `while` expression is checked, the loop body will be skipped and the program will begin executing at the first statement after the `while` loop.

Look at the following example:

```java
int x = 8;
while (x > 8) {
    System.out.println("in the loop");
    x = 10;
}
System.out.println("past the loop");
```

Running this code produces:

`past the loop`

Because the expression `(x > 8)` evaluates to `false`, none of the code within the while loop ever executes.

### 2 Using do loops

> #### The `do` loop is similar to the `while` loop, except the expression is not evaluated until after the `do` loop's code is executed. Therefore, the code in a `do` loop is guaranteed to execute at least once.

The following shows a do loop in action:

```java
do {
System.out.println("Inside loop");
} while(false);
```

The `System.out.println()` statement will print once, even though the expression evaluates to `false`. Remember, the do loop will always run the code in the loop body at least once. Be sure to note the use of the semicolon at the end of the while expression.

### 3 Using for loops

We'll call the old style of `for` loop the "basic `for` loop," and we'll call the new style of `for` loop the "enhanced `for` loop" (it's also sometimes called the `for-each`). The terms `for-in`, `for-each`, and "enhanced for" all refer to the same Java construct.

> #### The basic for loop is more flexible than the enhanced for loop, but the enhanced for loop was designed to make iterating through arrays and collections easier to code.

### The basic for loop

The `for` loop is especially useful for flow control when you already know how many times you need to execute the statements in the loop's block. The `for` loop declaration has three main parts besides the body of the loop:

- Declaration and initialization of variables
- The boolean expression (conditional test)
- The iteration expression

The three for declaration parts are separated by semicolons. The following two examples demonstrate the for loop. The first example shows the parts of a forloop in a pseudocode form, and the second shows a typical example of a for loop:

```java
for (/*Initialization*/ ; /*Condition*/ ; /* Iteration */) {
    /* loop body */
}
```

```java
for (int i = 0; i<10; i++) {
    System.out.println("i is " + i);
}
```

### The basic for loop declaration & initialization

The first part of the for statement lets you declare and initialize zero, one, or multiple variables of the same type inside the parentheses after the for keyword. If you declare more than one variable of the same type, you'll need to separate them with commas as follows:

`for (int x = 10, y = 3; y > 3; y++) { }`

The declaration and initialization happen before anything else in a `for` loop. And whereas the other two parts—the boolean test and the iteration expression — will run with each iteration of the loop, the declaration and initialization happen just once, at the very beginning. You also must know that the scope of variables declared in the `for` loop ends with the `for` loop!

The following demonstrates this:

```java
for (int x = 1; x < 2; x++) {
    System.out.println(x); // Legal
}
System.out.println(x); // Not Legal! x is now out of scope and can't be accessed.
```

If you try to compile this, you'll get something like this:

```java
Test.java:19: cannot resolve symbol
symbol : variable x
location: class Test
System.out.println(x);
                    ^
```

### Basic for Loop: Conditional (boolean) Expression

The next section that executes is the conditional expression, which (like all other conditional tests) must evaluate to a boolean value. You can have only one logical expression, but it can be very complex. Look out for code that uses logical expressions like this:

```java
for (int x = 0; ((((x < 10) && (y-- > 2)) | x == 3)); x++) { }
```

The preceding code is legal, but the following is not:

```java
for (int x = 0; (x > 5), (y < 2); x++) { } // too many expressions
```

> #### The rule to remember is this: You can have only one test expression.

In other words, you can't use multiple tests separated by commas, even though, the other two parts of a for statement can have multiple parts.

### Basic for Loop: Iteration Expression

After each execution of the body of the for loop, the iteration expression is executed. This is where you get to say what you want to happen with each iteration of the loop. Remember that it always happens after the loop body runs!

Look at the following:

```java
for (int x = 0; x < 1; x++) {
// body code that doesn't change the value of x
}
```

This loop executes just once. The first time into the loop, `x` is set to `0`, then `x` is tested to see if it's less than `1` (which it is), and then the body of the loop executes.

- 1 After the body of the loop runs, the iteration expression runs, incrementing `x` by `1`.
- 2 Next, the conditional test is checked, and since the result is now false, execution jumps to below the `for` loop and continues.

> #### Keep in mind that barring a forced exit, evaluating the iteration expression and then evaluating the conditional expression are always the last two things that happen in a for loop!

Examples of forced exits include a `break`, a `return`, a `System.exit()`, and an `exception`, which will all cause a loop to terminate abruptly, without running the iteration expression.

Look at the following code:

```java
static boolean doStuff() {
    for (int x = 0; x < 3; x++) {
        System.out.println("in for loop");
        return true;
    }
    return true;
}
```

Running this code produces

`in for loop`

The statement prints only once because a return causes execution to leave not just the current iteration of a loop, but the entire method. So the iteration expression never runs in that case.

![causes-of-early-loop-termination](images/causes-of-early-loop-termination.png)

### Basic for loop: for Loop Issues

None of the three sections of the for declaration are required! The following example is perfectly legal (although not necessarily good practice):

```java
for( ; ; ) {
    System.out.println("Inside an endless loop");
}
```

In this example, all the declaration parts are left out, so the for loop will act like an endless loop.

For the exam, it's important to know that with the absence of the _initialization_ and _increment sections_, the loop will act like a **while loop**.

The following example demonstrates how this is accomplished:

```java
int i = 0;
for (;i<10;) {
i++;
// do some other work
}
```

The next example demonstrates a for loop with multiple variables in play. A comma separates the variables, and they must be of the same type. Remember that the variables declared in the for statement are all local to the `for` loop and can't be used outside the scope of the loop.

```java
for (int i = 0,j = 0; (i<10) && (j<10); i++, j++) {
    System.out.println("i is " + i + " j is " +j);
}
```

![exam-watch-4](images/exam-watch-4.png)

The last thing to note is that all three sections of the for loop are independent of each other. The three expressions in the for statement don't need to operate on the same variables, although they typically do. But even the iterator expression, which many mistakenly call the "increment expression," doesn't need to increment or set anything; you can put in virtually any arbitrary code statements that you want to happen with each iteration of the loop.

Look at the following:

```java
int b = 3;
for (int a = 1; b != 1; System.out.println("iterate")) {
    b = b - a;
}
```

The preceding code prints

`iterate`

`iterate`

![exam-watch-5](images/exam-watch-5.png)

### The Enhanced for Loop (for Arrays)

The enhanced `for` loop, new as of Java 5, is a _specialized_ `for` loop that simplifies looping through an array or a collection. In this chapter we're going to focus on using the enhanced for to loop through arrays. In Chapter 6 we'll revisit the enhanced for, when we discuss the `ArrayList` collection class—where the enhanced for really comes into its own.

Instead of having three components, the enhanced for has two. Let's loop through an array the basic (old) way and then using the enhanced for:

```java
int [] a = {1,2,3,4};

for(int x = 0; x < a.length; x++) // basic for loop
    System.out.print(a[x]);

for(int n : a) // enhanced for loop
    System.out.print(n);
```

This produces the following output:

`12341234`

More formally, let's describe the enhanced for as follows:

`for(declaration : expression)`

The two pieces of the for statement are

#### declaration

- The _newly declared_ block variable of a type compatible with the elements of the array you are accessing.
- This variable will be available within the for block, and its value will be the same as the current array element.

#### expression

- This must evaluate to the array you want to loop through.
- This could be an array variable or a method call that returns an array.
- The array can be any type: primitives, objects, or even arrays of arrays.

Using the preceding definitions, let's look at some legal and illegal enhanced `for` declarations:

```java
int x;
long x2;
long [] la = {7L, 8L, 9L};
int [][] twoDee = {{1,2,3}, {4,5,6}, {7,8,9}};
String [] sNums = {"one", "two", "three"};
Animal [] animals = {new Dog(), new Cat()};

// legal 'for' declarations
for(long y : la ) ; // loop thru an array of longs
for(int[] n : twoDee) ; // loop thru the array of arrays
for(int n2 : twoDee[2]) ; // loop thru the 3rd sub-array
for(String s : sNums) ; // loop thru the array of Strings
for(Object o : sNums) ; // set an Object reference to each String
for(Animal a : animals) ; // set an Animal reference to each element

// ILLEGAL 'for' declarations
for(x2 : la) ; // x2 is already declared
for(int x4 : twoDee) ; // can't stuff an array into an int
for(int x3 : la) ; // can't stuff a long into an int
for(Dog d : animals) ; // you might get a Cat!
```

> #### The enhanced for loop assumes that, barring an early exit from the loop, you'll always loop through every element of the array.

The following discussions of break and continue apply to both the basic and enhanced for loops.

## Using Break & Continue

> #### The `break` and `continue` keywords are used to stop either the entire loop (`break`) or just the current iteration (`continue`). The difference between them is whether or not you `continue` with a new iteration or jump to the first statement below the loop and `continue` from there.

Typically, if you're using `break` or `continue`, you'll do an if test within the loop, and if some condition becomes `true` (or `false` depending on the program), you want to get out immediately.

![exam-watch-6](images/exam-watch-6.png)

The `break` statement causes the program to stop execution of the innermost loop and start processing the next line of code after the block. The `continue` statement causes only the current iteration of the innermost loop to cease and the next iteration of the same loop to start if the condition of the loop is met. When using a `continue` statement with a `for` loop, you need to consider the effects that continue has on the `loop` iteration.

Examine the following code:

```java
for (int i = 0; i < 10; i++) {
    System.out.println("Inside loop");
    continue;
}
```

The question is, is this an endless loop? The answer is no. When the `continue` statement is hit, the iteration expression still runs! It runs just as though the current iteration ended "in the natural way." So in the preceding example, i will still increment before the condition (`i < 10`) is checked again.

Most of the time, a `continue` is used within an `if` test as follows:

```java
for (int i = 0; i < 10; i++) {
    System.out.println("Inside loop");
    if (foo.doStuff() == 5) {
        continue;
    }
// more loop code, that won't be reached when the above if test is true
}
```

## Unlabeled & Labeled Statements

Both the `break` statement and the `continue` statement can be unlabeled or labeled.

### Unlabeled statements

As stated before, a `break` statement (unlabeled) will exit out of the innermost looping construct and proceed with the next line of code beyond the loop block.

The following example demonstrates a `break` statement:

```java
boolean problem = true;
while (true) {
    if (problem) {
        System.out.println("There was a problem");
        break;
    }
}
// next line of code
```

In the previous example, the `break` statement is unlabeled. The following is an example of an unlabeled `continue` statement:

```java
while (!EOF) {
// read a field from a file
if (wrongField) {
    continue; // move to the next field in the file
    }
// otherwise do other stuff with the field
}
```

_In this example, a file is being read one field at a time. When an error is encountered, the program moves to the next field in the file and uses the `continue` statement to go back into the loop (if it is not at the end of the file) and keeps reading the various fields. If the `break` command were used instead, the code would stop reading the file once the error occurred and move on to the next line of code after the loop._

> #### The `continue` statement gives you a way to say, "This particular iteration of the loop needs to stop, but not the whole loop itself. I just don't want the rest of the code in this iteration to finish, so do the iteration expression and then start over with the test, and don't worry about what was below the `continue` statement."

### Labeled statements

> #### Although many statements in a Java program can be labeled, it's most common to use labels with loop statements like for or while, in conjunction with `break` and `continue` statements. A label statement must be placed just before the statement being labeled, and it consists of a _valid identifier that ends with a colon (:)_.

You need to understand the difference between labeled and unlabeled break and continue.

The labeled varieties are needed only in situations where you have a nested loop, and they need to indicate which of the nested loops you want to break from, or from which of the nested loops you want to continue with the next iteration. A break statement will exit out of the labeled loop, as opposed to the innermost loop, if the break keyword is combined with a label.
Here's an example of what a label looks like:

```java
foo:
for (int x = 3; x < 20; x++) {
    while(y > 7) {
        y--;
    }
}
```

The label must adhere to the rules for a valid variable name and should adhere to the Java naming convention. The syntax for the use of a label name in conjunction with a `break` statement is the `break` keyword, then the label name, followed by a semicolon. A more complete example of the use of a labeled break statement is as follows:

```java
boolean isTrue = true;
outer:
for(int i=0; i<5; i++) {
    while (isTrue) {
        System.out.println("Hello");
        break outer;
    }// end of inner while loop
    System.out.println("Outer loop."); // Won't print
} // end of outer for loop
System.out.println("Good-Bye");
```

Running this code produces

`Hello`

`Good-Bye`

In this example, the word "Hello" will be printed one time. Then, the labeled `break` statement will be executed, and the flow will exit out of the loop labeled outer. The next line of code will then print "Good-Bye".

Let's see what will happen if the `continue` statement is used instead of the `break` statement. The following code example is similar to the preceding one, with the exception of substituting `continue` for `break`:

```java
outer:
for (int i=0; i<5; i++) {
    for (int j=0; j<5; j++) {
        System.out.println("Hello");
        continue outer;
    } // end of inner loop
    System.out.println("outer"); // Never prints
}
System.out.println("Good-Bye");
```

Running this code produces

```java
Hello
Hello
Hello
Hello
Hello
Good-Bye
```

In this example, "Hello" will be printed five times. After the `continue` statement is executed, the flow continues with the next iteration of the loop identified with the label. Finally, when the condition in the outer loop evaluates to `false`, this loop will finish and "Good-Bye" will be printed.

![exam-watch-7](images/exam-watch-7.png)
