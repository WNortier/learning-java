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

```java
package cert;
class Beverage { }
```
```java
package exam.stuff;
import cert.Beverage;
class Tea extends Beverage { }
```
Tea won't compile because its superclass, Beverage, has default access and is in a different package. 

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

**An abstract class can never be instantiated.  Its sole purpose is to be extended (subclassed).**  Note however that you can compile and execute an abstract class even if you don't make an instance of it.  A class can never be marked as both abstract and final - they have nearly opposite meanings.  An abstract class must be subclassed, whereas a final class must not be subclassed.  If you see this combination of *abstract* and *final* modifiers used for a class or method declaration, the code will not compile.    

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

# Interfaces 

# Interface declarations

In general, when you create an interface, you're defining a contract for *what* a class can do, without saying anything about *how* the class will do it.
An abstract class can have both abstract and nonabstract methods but an an interface generally has only abstract methods.
Interfaces have very little flexibility in how methods and variables defined in the interface are declared.  

- ### Interface must be declared with the keyword interface.  
- ### Interface methods are implicitly **public** and **abstract** unless delcared as *default* or *static*. 
- ### Interface methods cannot be marked final, strictfp or native. 
- ### All variables declared must be **public, static and final** - in other words interfaces can declare only constants, not instance variables.  
- ### An interface can only extend one or more other interfaces.  They cannot implement another interface or class.
- ### Interface types can be used polymorphically. 

What you declare:
```java
interface Bounceable
void bounce();
void setBounceFactor(int bf);
```

What the compiler sees:
```java
public abstract void bounce() { }
public abstract void setBounceFactor(int bf) { }
```

All interface methods must be implemented and must be marked public.  
What the implementing class must do:

```java
Class Tire implements Bounceable
public void bounce() {...}
public void setBounceFactor(int bf) { }
```

Both these declarations are legal:
```java
public abstract interface Rollable { }
public interface Rollable { }
```

Interfaces are **implicitly abstract** whether you type abstract or not.  Typing the **abstract** modifier is redundant.  
The **public** modifier is required if you want the interface to have public rather than default access.   

# Interface methods and constants 

## Interface methods

Interface methods are **implicitly public and abstract**.  Typing public and abstract modifiers on methods is redundant.  The following method declarations are equavalent:
```java
public interface Bounceable {
    public abstract void bounce();
    public abstract void setBounceFactor(int bf);
}
```

```java
public interface Bounceable {
    void bounce();
    void setBounceFactor(int bf);
}
```

All interface methods not declared default or static are **public and abstract** regardless of what you see in the interface definition.  
The following five method declarations, if declared within their own interface declarations, are legal and identical:
```java
void bounce();
public void bounce();
abstract void bounce();
public abstract void bounce();
abstract public void bounce();
```
The following interface method declarations won't compile:
```java
final void bounce(); // Final and abstract can never be used together (abstract is implied)
private void bounce(); // Interface methods are always public
protected void bounce(); // Interface methods are always public 
```

## Interface constants 

You're allowed to put constants in an interface.  Interface constants must always be ```public static final```  Any class that implements the interface has direct access to the constants just as if the class had inherited them.  Interface constants cannot be changed by the classes implementign the interface after they have been assigned.
The following interface constant declarations are identical:

```java
// Interface constants must always be public static final
public int x = 1;
int x = 1;
static int x = 1;
public static int x = 1;
public final int x = 1;
static final int x = 1;
public static final int x = 1;
```
> ## On the exam you might see questions that you cannot answer correctly unless you know that an interface variable is final and can never be given a value by the implementing (or any other) class.

## Declaring ```default``` Interface Methods

As of Java 8 interfaces can include inheritable methods with concrete implementations.  These concrete implementations are called default methods.  
The following declaration rules apply:

- ### Default methods are declared by using the default keyword.  It can only be used with interface method signatures, not with class method signatures.
- ### Default methods are **implicitly public** and the public modifier is optional 
- ### Default methods cannot be marked as private, protected, static, final or abstract 
- ### Default methods must have a concrete method body 

Examples of legal and illegal default methods:
```java
interface TestDefault {
    default int m1(){return 1;} // Legal
    public default void m2(){;} // Legal 
    static default void m3(){;} // Illegal: default cannot be marked static 
    default void m4(); // Illegal: default must have a method body 
}
```

## Declaring ```static``` Interface Methods

As of Java 8, interfaces can include static methods with concrete implementations.  As with interface default methods, there are OO implications that we'll discuss in Chapter 2.
The following declaration rules apply:

- ### Static interface methods are declared by using the static keyword 
- ### Static methods are **implicitly public** and the public modifier is optional 
- ### Static interface methods cannot be marked as private, protected, final or abstract 
- ### Static interface methods must have a concrete method body 
- ### When invoking a static interface method, the methods type (interface name) must be included in the invocation

Examples of legal and illegal static interface methods and their use:
```java
interface StaticIface {
    static int m1(){ return 42; } // Legal
    public static void m2(){ ; } // Legal 

    final static void m3(){ ; } // Illegal: final not allowed
    abstract static void m4(){ ; } // Illegal: abstract not allowed
    static void m5() //Illegal: needs a method body
}

public class TestSIF implements StaticIface {
    public static void main(String[] args) {
        System.out.println(StaticIface.m1()); // Legal: m1()'s type must be included

        new TestSIF().go();
         System.out.println(StaticIface.m1()); // Illegal: reference to interface is required
    }

    void go() {
        System.out.println(StaticIface.m1()); // Legal from an instance 
    }

}
```




