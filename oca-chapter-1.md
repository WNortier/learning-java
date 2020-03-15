# CHAPTER 1 

## Using public static void main(String[] args)

Legal declarations of main() include:

```java
static public void main(String[] args)
public static void main(String... x)
static public void main(String bang_a_gong[])
```

## Import Statements and the Java API 

**Import statements** are used for less syntax.  In the exam there may be a mixture of both versions.    

Without import:
```java
public class MyClass {
    public static void main(String[] args) {
        java.util.ArrayList<String> a = new java.util.ArrayList<String>();
    }
}
```
With:
```java
import java.util.ArrayList;
public class MyClass {
    public static void main(String[] args) {
        ArrayList<String> a = new ArrayList<String>();
    }
}
```
> ## Interpretation -> In the Java API there is a package called 'util', and in that package is a class called 'ArrayList'.  Thus, 'ArrayList' is a shorthand for 'java.util.ArrayList'.  

## Static imports

**Static imports** can be used to save time typing when you want to use a class' static members.  This example includes static members from a commonly used class in the Java API java.lang.Integer.  Another static member the **out** field in the System class is also used.    

Before static imports:
```java
public class MyClass {
    public static void main(String[] args) {
       System.out.println(Integer.MAX_VALUE);
       System.out.println(Integer.toHexString(42));
    }
}
```
With:
```java
import static java.lang.System.out;
import static java.lang.Integer.*;

public class MyClass {
    public static void main(String[] args) {
       out.println(MAX_VALUE);
       out.println(toHexString(42));
    }
}
```
Here shortcuts are used for out.println(method) and MAX_VALUE constant.  

> ## You can use this feature on your own classes too.  

- import java.util.* (Searches the java.util package)
- import static java.lang.Integer.* (Searches the java.lang.Integer class)
- import java.* (Legal, but will not search across packages)

# CLASS DECLARATIONS AND MODIFIERS

Modifiers fall into 2 categories:
- Access modifiers (**public, protected, private**)
- Non-access modifiers (including **strictfp, final and abstract**)

In Java there are 4 access controls but only three access modifiers.  The fourth access control level (called default or package access) is what you get when you don't use any of the access modifiers.

All four access controls work for most method and variable declarations, but **a class can be declared with only public or default access**.  

When we say code from class A has access to code from class B it means class A can do one of three things:

- Create an instance of class B
- Extend class B (in other words, become a subclass of class B)
- Access certain methods and variables in class B, depending on the access control of those methods and variables

### 1 Default Access

**Default access is the same as package level access because a class with default access can only be seen by classes within the same package.**  

> ##  When you see a question with complex logic check the access modifiers first.  If you spot an access violation such as a class in package A trying to access a default class in package B you know the code wont compile.

### 2 Public Access

**The public keyword gives all classes from all packages access to the public class.**

### Other non-acess modifiers

You can modify a class declaration using the keyword final, abstract, or 
### 1 strictfp.  

These modifiers are in addition to whatever is already on the class, so you could mark a class as both public and final but you can never mark a class as both final and abstract.

For the exam you only need to know that strictfp is a keyword that can modify a class or a method but never a variable.  

### 2 Final classes

**When a class is marked as final it cannot be inherited.** In other words, no other class can ever extend (inherit from) a final class.

You should make a final class only if you need an absolute garauntee that none of the methods in that class will ever be overridden.  *Final classes are rare.*  

### 3 Abstract classes

**An abstract class can never be instantiated.  Its sole purpose is to be extended (subclassed).**  Note however that you can compile and execute an abstract class even if you don't make an instance of it.  

Why make a class if you can't make objects out of it? Well the class might be too abstract.  Take a car for example, we don't just want a generic car... we want a BMW or a Sabaru.  

```java
abstract class Car {
private double price;
private String model;
private String year;
public abstract void goFast();
public abstract void goUpHill();
public abstract void impressNeighbors();
}

//Another class

Car x = new Car();
// 1 error
```

> ## Notice that methods marked as abstract end with a semicolon rather than curly braces.  

Look for questions with method declarations that ends with a semicolon rather than curly braces.  If the method is in a class as opposed to an interface then the method and the class must be marked as abstract.  