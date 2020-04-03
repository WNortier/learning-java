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
         System.out.println(m1()); // Illegal: reference to interface is required
    }

    void go() {
        System.out.println(StaticIface.m1()); // Legal from an instance 
    }

}
```

Which outputs the following
```java
42
42
```

We will return to our discussion on default and static interface methods in chapter 2. 