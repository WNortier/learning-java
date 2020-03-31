# Declare and use enums 

## Declaring enums 

Java lets you restrict a variable to having one of only a few predefined values—in other words, one value from an enumerated list. (The items in the enumerated list are called, surprisingly, enums.)

> ### Using enums can help reduce the bugs in your code

With the following simple declaration, you can guarantee that the compiler will stop you from assigning anything to a CoffeeSize except BIG, HUGE, or OVERWHELMING:

```java
enum CoffeeSize { BIG, HUGE, OVERWHELMING }; // Enum declaration
CoffeeSize cs = CoffeeSize.BIG; // Getting a CoffeeSize from the enum 
```

Declaring enum constants in all caps is an Oracle convention.  
Remember:
- Enums can also be declared as their own separate class or as a class member
- Enums cannot be declared within a method

```java
public class CoffeeTest1 {
    public static void main(String[] args) {
        enum CoffeeSize { BIG, HUGE, OVERWHELMING } // WRONG! Cannot declare enums in any methods

        Coffee drink = new Coffee();
        drink.size = CoffeeSize.BIG;
    }
}
```
___

Example of enum outside a class:

```java
enum CoffeeSize { BIG, HUGE, OVERWHELMING } // this cannot be private or protected

class Coffee {
    CoffeeSize size;
}

public class CoffeeTest1 {
    public static void main(String[] args) {
        Coffee drink = new Coffee();
        drink.size = CoffeeSize.BIG; // enum outside class
    }
}
```
> ### An enum declared outside of a class can only be declared with the default or public modifier.  

In the preceding code the file must be named CoffeeTest1.java because that's the name of the public class in the file.  In general, enum classes can exist in their own file like *CoffeeSize.java*.  
___

Example of enum inside a class:

```java
class Coffee2 {
    enum CoffeeSize {BIG, HUGE, OVERWHELMING }
    CoffeeSize size;
}

public class CoffeeTest2 {
    public static void main(String[] args) {
        Coffee2 drink = new Coffee2();
        drink.size = Coffee2.CoffeeSize.BIG; // enclosing class name required
    }
}
```
___

The key takeaway from these examples are:
- ### 1 Enums can be declared inside or outside of a class
- ### 2 The syntax for accessing an enums members depends on where the enum was declared 
___

> ### The Java language designers made it optional to put a semicolon at the end of the enum declaration (when no other declarations for this enum follow)

```java
public class CoffeeTest1 {
    enum CoffeeSize { BIG, HUGE, OVERWHELMING }; // <--semicolon is optional here

    public static void main(String[] args) {
        Coffee drink = new Coffee();
        drink.size = CoffeeSize.BIG;
    }
}
```
The most important thing to remember is that enums are not Strings or ints! Each of the enumerated CoffeeSize values is actually an instance of CoffeeSize. In other words, BIG is of type CoffeeSize. Think of an enum as a kind of class that looks something (but not exactly) like this:

```java
// Conceptual example of how you can think about enums
class CoffeeSize {
    public static final CoffeeSize BIG = new CoffeeSize("BIG", 0);
    public static final CoffeeSize HUGE = new CoffeeSize("HUGE", 1);
    public static final CoffeeSize OVERWHELMING = new CoffeeSize("OVERWHELMING", 2);

    CoffeeSize(String enumName, int index) {
        // stuff here
    }

    public static void main(String[] args) {
        System.out.println(CoffeeSize.BIG);
    }
}
```

Notice how each of the enumerated values, BIG, HUGE, and OVERWHELMING, is an instance of type CoffeeSize. They're represented as static and final, which, in the Java world, is thought of as a constant. Also notice that each enum value knows its index or position—in other words, the order in which enum values are declared matters. You can think of the CoffeeSize enums as existing in an array of type CoffeeSize, and as you'll see in a later chapter, you can iterate through the values of an enum by invoking the values() method on any enum type. (Don't worry about that in this chapter.)