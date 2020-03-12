# OBJECT ORIENTED PROGRAMMING 

There are 4 core concepts in OOP: 
### - *encapsulation*
### - *inheritance* 
### - *polymorphism*
### - *abstraction*

# ENCAPSULATION

The idea behind encapsulation is to ensure that implementation details are not visible to users. The variables of one class will be hidden from the other classes, accessible only through the methods of the current class. This is called **data hiding**.

To achieve encapsulation in Java, declare the class' variables as private and provide public setter and getter methods to modify and view the variables' values.
For example:

```java
class BankAccount {
  private double balance=0;
  public void deposit(double x) {
    if(x > 0) {
      balance += x;
    }
  }
}
```

This implementation hides the balance variable, enabling access to it only through the deposit method, which validates the amount to be deposited before modifying the variable.

# INHERITANCE 

**Inheritance** is the process that enables one class to acquire the properties (methods and variables) of another. With inheritance, the information is placed in a more manageable, hierarchical order.

The class inheriting the properties of another is the **subclass** (also called derived class, or child class); the class whose properties are inherited is the **superclass** (base class, or parent class).

To inherit from a class, use the **extends** keyword.
This example shows how to have the class Dog to inherit from the class Animal.
> ## Here, Dog is the subclass, and Animal is the superclass.

```java
class Dog extends Animal {
 // some code
}
```

____

When one class is inherited from another class, it inherits all of the superclass' non-private variables and methods.

```java
class Animal {
    protected int legs;
    public void eat() {
        System.out.println("Animal eats");
    }
}

class Dog extends Animal {
    Dog() {
        legs = 4;
    }
}

class MyClass {
    public static void main(String[ ] args) {
        Dog d = new Dog();
        d.eat();
        //Outputs "Animal eats"
        System.out.println(d.legs);
        //Outputs "4"
    }
}
```
As you can see, the Dog class inherits the legs variable from the Animal class.
We can now declare a Dog object and call the eat method of its superclass:

> ## Recall the protected access modifier, which makes the members visible only to the subclasses.

___

Constructors are not member methods, and so are not inherited by subclasses.
However, the *constructor* of the superclass is called when the subclass is instantiated.
Example:

```java
class A {
  public A() {
    System.out.println("New A");
  }
}

class B extends A {
  public B() {
    System.out.println("New B");
  }
}

class Program {
  public static void main(String[ ] args) {
      B obj = new B();
  }
}

/*Outputs
"New A"
"New B"
*/
```

> ## You can access the superclass from the subclass using the **super** keyword.
> ## For example, **super.var** accesses the var member of the superclass.

# POLYMORPHISM

Briefly **polymorphism** (having many forms) is **one method with several implementations** due to a hierarchy of classes related to each other through inheritance.

A call to a member method will cause a different implementation to be executed, depending on the type of the object invoking the method.
Dog and Cat are classes that inherit from the Animal class. Each class has its own implementation of the makeSound() method.

```java
class Animal {
    protected int legs = 4;
    public void makeSound() {
        System.out.println("Grr...");
    }
}
class Cat extends Animal {
    public void makeSound() {
        System.out.println("Meow");
    }
}
class Dog extends Animal {
    public void makeSound() {
        System.out.println("Woof");
    }
}
```

As all Cat and Dog objects are Animal objects, we can do the following in main:

```java
class Program {
    public static void main(String args[ ]) {
        Animal a = new Dog();
        Animal b = new Cat();
        Animal c = new Animal();
        
       a.makeSound();
       //Outputs "Woof"
       b.makeSound();
       //Outputs "Meow"
       System.out.println(b.legs);
       //Outputs "4"
       c.makeSound();
       //Outputs "Grr..."
       System.out.println(c.legs) 
       //Outputs "4"
    }
}
```

We've created two reference variables of type Animal, and pointed them to the Cat and Dog objects.
Now, we can call the makeSound() methods.

> ## This demonstrates that you can use the Animal variable without actually knowing that it contains an object of the subclass.
> ## This is very useful when you have multiple subclasses of the superclass.

# METHOD OVERRIDING AND METHOD OVERLOADING

## METHOD OVERRIDING 

As we saw in the previous lesson, a subclass can define a behavior that's specific to the subclass type, meaning that a subclass can implement a parent class method based on its requirement.
This feature is known as method **overriding**.

```java
class Animal {
    public void makeSound() {
        System.out.println("Grr...");
    }
}
class Cat extends Animal {
    public void makeSound() {
        System.out.println("Meow");
    }
}
```

In the code above, the Cat class overrides the makeSound() method of its superclass Animal.

Rules for Method Overriding:
- Should have the same return type and arguments
- The access level cannot be more restrictive than the overridden method's access level (Example: If the superclass method is declared public, the overriding method in the sub class can be neither private nor protected)
- A method declared final or static cannot be overridden
- If a method cannot be inherited, it cannot be overridden
- Constructors cannot be overridden

> ## Method overriding is also known as runtime polymorphism.

## METHOD OVERLOADING 

When methods have the same name, but different parameters, it is known as method **overloading**.
This can be very useful when you need the same method functionality for different types of parameters.
The following example illustrates a method that returns the maximum of its two parameters.

```java
int max(int a, int b) {
  if(a > b) {
    return a;
  }
  else {
    return b;
  }
}
```

The method shown above will only work for parameters of type integer.
However, we might want to use it for doubles, as well. For that, you need to **overload** the max method:

```java
double max(double a, double b) {
  if(a > b) {
    return a;
  }
  else {
    return b;
  }
}
```

Now, our max method will also work with doubles.
## An overloaded method must have a different argument list; the parameters should differ in their type, number, or both.

> ## Another name for method overloading is compile-time polymorphism.

# ABSTRACTION

The concept of abstraction is that we focus on essential qualities, rather than the specific characteristics of one particular example.

In Java, abstraction is achieved using abstract **classes** and **interfaces**.
An abstract class is defined using the **abstract** keyword.
- If a class is declared abstract it cannot be instantiated (you cannot create objects of that type).
- To use an abstract class, you have to inherit it from another class.
- Any class that contains an abstract method should be defined as abstract.

> ## An abstract method is a method that is declared without an implementation (without braces, and followed by a semicolon): abstract void walk();

The makeSound method is also abstract, as it has no implementation in the superclass.
We can inherit from the Animal class and define the makeSound() method for the subclass:

```java
abstract class Animal {
    int legs = 0;
    abstract void makeSound();
}

class Cat extends Animal {
    public void makeSound() {
        System.out.println("Meow");
    }
}

public class Program {
    public static void main(String[] args) {
        Cat c = new Cat();
        c.makeSound();
    }
}
```

> ## Every Animal makes a sound, but each has a different way to do it. That's why we define an abstract class Animal, and leave the implementation of how they make sounds to the subclasses.
> ## This is used when there is no meaningful definition for the method in the superclass.

# INTERFACE 

An **interface** is a completely **abstract** class (*cannot be instantiated and you have to inheret from it*) that contains only abstract methods.
Some specifications for interfaces:
- Defined using the **interface** keyword.
- May contain only static final variables.
- Cannot contain a constructor because interfaces cannot be instantiated.
- Interfaces can extend other interfaces.
- A class can implement any number of interfaces.

Use the implements keyword to use an interface with your class:  

```java
interface Animal {
  public void eat();
  public void makeSound();
}

class Cat implements Animal {
  public void makeSound() {
    System.out.println("Meow");
  }
  public void eat() {
    System.out.println("omnomnom");
  }
}
```

Interfaces have the following properties:
- An interface is **implicitly abstract**. You do not need to use the abstract keyword while declaring an interface.
- Each method in an interface is also implicitly abstract, so the abstract keyword is not needed.
- Methods in an interface are implicitly public.

> ## A class can inherit from just one superclass, but can implement multiple interfaces!