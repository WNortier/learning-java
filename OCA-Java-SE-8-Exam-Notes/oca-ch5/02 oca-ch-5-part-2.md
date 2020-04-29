- ## [3 Handling Exceptions](#3_Handling_Exceptions)
- ## [4 Common Exceptions & Errors](#4_Common_Exceptions_&_Errors)

# <a name="3_Handling_Exceptions"></a> 3 Handling Exceptions

### OCA Objectives

- **8.1 Differentiate among checked exceptions, unchecked exceptions, and errors.**
- **8.2 Create a try-catch block and determine how exceptions alter normal program flow.**
- **8.3 Describe the advantages of Exception handling.**
- **8.4 Create and invoke a method that throws an exception.**
- **8.5 Recognize common exception classes (such as NullPointerException, ArithmeticException, ArrayIndexOutOfBoundsException, ClassCastException) (sic)**

In many languages, writing program code that checks for and deals with errors is tedious and bloats the application source into confusing spaghetti. Still, error detection and handling may be the most important ingredient of any robust application.

Here are some of the benefits of Java's exception-handling features:

- It _arms developers with an elegant mechanism for handling errors_ that produces efficient and organized error-handling code.

- It allows developers to _detect errors easily_ without writing special code to test return values.

- It lets us _keep exception-handling code cleanly separated from exception-generating code_.

- It also lets us use the same exception-handling _code to deal with a range of possible exceptions_.

# <a name="4_Common_Exceptions_&_Errors"></a> 4 Common Exceptions & Errors

Java 7 added several new exception-handling capabilities to the language. For our purposes, Oracle split the various exception-handling topics into two main parts:

- #### 1. The OCA exam covers the Java 6 version of exception handling.
- #### 2. The OCP exam adds the new exception features added in Java 7.

_In order to mirror Oracle's OCA 8 objectives versus the OCP 8 objectives, this chapter will give you only the basics of exception handling._

### Catching an Exception Using try and catch

Before we begin, let's introduce some terminology. The term exception means "exceptional condition" and is an occurrence that alters the normal program flow.

A bunch of things can lead to exceptions, including hardware failures, resource exhaustion, and good old bugs.

> ### When an exceptional event occurs in Java, an exception is said to be "thrown." The code that's responsible for doing something about the exception is called an "exception handler," and it "catches" the thrown exception. Exception handling works by transferring the execution of a program to an appropriate exception handler when an exception occurs.

For example, if you call a method that opens a file but the file cannot be opened, execution of that method will stop, and code that you wrote to deal with this situation will be run. Therefore, we need a way to tell the JVM what code to execute when a certain exception happens.

To do this, we use the `try` and `catch` keywords.

The `try` is used to define a block of code in which exceptions may occur. This block of code is called a "guarded region" (which really means "risky code goes here"). One or more catch clauses match a specific exception (or group of exceptions—more on that later) to a block of code that handles it.

Here's how it looks in pseudocode:

```java
1. try {
2. // This is the first line of the "guarded region"
3. // that is governed by the try keyword.
4. // Put code here that might cause some kind of exception.
5. // We may have many code lines here or just one.
6. }
7. catch(MyFirstException) {
8. // Put code here that handles this exception.
9. // This is the next line of the exception handler.
10. // This is the last line of the exception handler.
11. }
12. catch(MySecondException) {
13. // Put code here that handles this exception
14. }
15.
16. // Some other unguarded (normal, non-risky) code begins here
```

-> In this pseudocode example, lines 2 through 5 constitute the guarded region that is governed by the try clause.
-> Line 7 is an exception handler for an exception of type MyFirstException.
-> Line 12 is an exception handler for an exception of type MySecondException.

`catch` blocks immediately follow the `try` block. This is a requirement;
If you have one or more `catch` blocks, they must immediately follow the try block.

Additionally, the catch blocks must all follow each other, without any other statements or blocks in between. Also, the order in which the catch blocks appear matters, as we'll see a little later.

Execution of the guarded region starts at line 2. If the program executes all the way past line 5 with no exceptions being thrown, execution will transfer to line 15 and continue downward. However, if at any time in lines 2 through 5 (the `try` block) an exception of type `MyFirstException` is thrown, execution will immediately transfer to line 7. Lines 8 through 10 will then be executed so that the entire catch block runs, and then execution will transfer to line 15 and continue.

Note that if an exception occurred on, say, line 3 of the `try` block, the remaining lines in the `try` block (4 and 5) would never be executed. Once control jumps to the catch block, it never returns to complete the balance of the `try` block. This is exactly what you want, though.

Imagine that your code looks something like this pseudocode:

```java
try {
    getTheFileFromOverNetwork
    readFromTheFileAndPopulateTable
}
catch(CantGetFileFromNetwork) {
    displayNetworkErrorMessage
}
```

_This pseudocode demonstrates how you typically work with exceptions. Code that's dependent on a risky operation (as populating a table with file data is dependent on getting the file from the network) is grouped into a `try` block in such a way that if, say, the first operation fails, you won't continue trying to run other code that's also guaranteed to fail. In the pseudocode example, you won't be able to read from the file if you can't get the file off the network in the first place. One of the benefits of using exception handling is that code to handle any particular exception that may occur in the governed region needs to be written only once. Returning to our earlier code example, there may be three different places in our `try` block that can generate a `MyFirstException`, but wherever it occurs it will be handled by the same catch block (on line 7). We'll discuss more benefits of exception handling near the end of this chapter._

### Using Finally

Although try and catch provide a terrific mechanism for trapping and handling exceptions, we are left with the problem of how to clean up after ourselves if an exception occurs. The cleanup code can neither be placed at the bottom of a try block or within a catch block.

Exception handlers are a poor place to clean up after the code in the try block because each handler then requires its own copy of the cleanup code. If, for example, you allocated a network socket or opened a file somewhere in the guarded region, each exception handler would have to close the file or release the socket. That would make it too easy to forget to do cleanup and also lead to a lot of redundant code.

To address this problem, Java offers the finally block. A finally block encloses code that is always executed at some point after the try block, whether an exception was thrown or not. Even if there is a return statement in the try block, the finally block executes right after the return statement is encountered and before the return executes! This is the right place to close your files, release your network sockets, and perform any other cleanup your code requires.

> #### If the try block executes with no exceptions, the finally block is executed immediately after the try block completes. If there was an exception thrown, the finally block executes immediately after the proper catch block completes.

Let's look at another pseudocode example:

```java
1: try {
2: // This is the first line of the "guarded region".
3: }
4: catch(MyFirstException) {
5: // Put code here that handles this exception
6: }
7: catch(MySecondException) {
8: // Put code here that handles this exception
9: }
10: finally {
11: // Put code here to release any resource we
12: // allocated in the try clause
13: }
14:
15: // More code here
```

- As before, execution starts at the first line of the try block, line 2. If there are no exceptions thrown in the try block, execution transfers to line 11, the first line of the finally block.

- On the other hand, if a MySecondException is thrown while the code in the try block is executing, execution transfers to the first line of that exception handler, line 8 in the catch clause. After all the code in the catch clause is executed, the program moves to line 11, the first line of the finally clause.

_If an exception is thrown, finally runs. If an exception is not thrown, finally runs. If the exception is caught, finally runs. If the exception is not caught, finally runs. Later we'll look at the few scenarios in which finally might not run or complete._

> #### Remember, finally clauses are not required. Using a finally block allows the cleanup code to execute even when there isn't a catch clause.

If you don't write one, your code will compile and run just fine. In fact, if you have no resources to clean up after your try block completes, you probably don't need a finally clause. Also, because the compiler doesn't even require catch clauses, sometimes you'll run across code that has a try block immediately followed by a finally block. Such code is useful when the exception is going to be passed back to the calling method, as explained in the next section.

The following legal code demonstrates a try with a finally but no catch:

```java
try {
// do stuff
} finally {
// clean up
}
```

The following legal code demonstrates a `try`, `catch`, and `finally`:

```java
try {
// do stuff
} catch (SomeException ex) {
// do exception handling
} finally {
// clean up
}
```

The following ILLEGAL code demonstrates a try without a catch or finally:

```java
try {
// do stuff
}
// need a catch or finally here
System.out.println("out of try block");
```

The following ILLEGAL code demonstrates a misplaced catch block:

```java
try {
// do stuff
}
// can't have code between try/catch
System.out.println("out of try block");
catch(Exception ex) { }
```

### Propagating Uncaught Exceptions

Why aren't catch clauses required? What happens to an exception that's thrown in a try block when there is no catch clause waiting for it? Actually, there's no requirement that you code a catch clause for every possible exception that could be thrown from the corresponding try block. In fact, it's doubtful that you could accomplish such a feat! If a method doesn't provide a catch clause for a particular exception, that method is said to be "ducking" the exception (or "passing the buck"). So what happens to a ducked exception? Before we discuss that, we need to briefly review the concept of the call stack. Most languages have the concept of a method stack or a call stack. Simply put, the call stack is the chain of methods that your program executes to get to the current method. If your program starts in method main() and main() calls method a(), which calls method b(), which in turn calls method c(), the call stack consists of the following:

```java
c
b
a
main
```
