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