# SECTION 6 CLASSES, CONSTRUCTORS & INHERITANCE PART 1

- ### [1 Classes](#1_classes)
- ### [2 Constructors](#2_constructors)
- ### [3 Inheritance](#3_inheritance)
- ### [4 Reference vs Object vs Instance vs Class](#4_Reference_vs_Object_vs_Instance_vs_Class)
- ### [5 this vs super](#5_this_vs_super)

# <a name="1_classes"></a> 1 Classes

Classes are templates or blueprints for creating objects.  Software objects store their state in fields (variables) and expose their behavior with methods.  

Here we declare a car class: 
**Car.java**
```java
package com.warwick;

public class Car {
    private int doors;
    private int wheels;
    private String model;
    private String engine;
    private String colour;
}
```
We create two car objects in the main method.  Objects are initialised with the *new* keyword.     
**Main.java**
```java
package com.warwick;

public class Main {

    public static void main(String[] args) {
	    Car porsche = new Car();
        Car holden = new Car();
        porsche.model = "Carrera"; // Compiler error due to model being a private field
    }
}
```
You will notice that if you type 'porsche.' intellij brings up methods that are accessible from this object.  They are created automatically by Java when we create a new object.

![01-classes-01](images/section-6/01-classes-01.png)

___ 
## *Classes continued*
___

Its best practice to keep variables private and to use setters and getters which are defined in the class to access their values.  This ensures that data is validated and set correctly.  Notice how we can use conditional logic in the setter.

**Car.java**
```java
package com.warwick;

public class Car {

    private int doors;
    private int wheels;
    private String model;
    private String engine;
    private String colour;

    public void setModel(String model) {
        String validModel = model.toLowerCase();
        //setModel setter limits the model type to carrera or commodore
        if(validModel.equals("carrera") || validModel.equals("commodore")) {
            this.model = model;
        } else {
            this.model = "Unknown";
        }
    }

    public String getModel() {
        return this.model;
    }
}
```
**Main.java**
```java
package com.warwick;

public class Main {

    public static void main(String[] args) {
	    Car porsche = new Car();
        Car holden = new Car();
        porsche.setModel("911");

        System.out.println("Model is " + porsche.getModel()); 
        // Since it is not carrera or commodore
        // Output: Model is Unknown 
    }
}
```
___ 
## *Another class example*
___

> ### Intellij has a trick to quickly create getters and setters.  Click on *code -> generate* and choose desired fields to target.  There is also a hotkey.

![03-generate-03](images/section-6/03-generate-03.png)

The class below also has two methods.  One for setting a balance and another for withdrawal which checks if it is possible to make a withdrawal before doing so.

**Account.java**
```java
package com.warwick;

public class Account {

    private String number;
    private double balance;
    private String customerName;
    private String customerEmailAddress;
    private String customerPhoneNumber;


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmailAddress() {
        return customerEmailAddress;
    }

    public void setCustomerEmailAddress(String customerEmailAddress) {
        this.customerEmailAddress = customerEmailAddress;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public void deposit(double depositAmount) {
        this.balance += depositAmount;
        System.out.println("Deposit of " + depositAmount + " made. New balance is " + this.balance);
    }

    public void withdrawal(double withdrawalAmount) {
        
        if (this.balance - withdrawalAmount < 0) {
            System.out.println("Only " + this.balance + " available. Withdrawal not processed");
         } else {
            this.balance -= withdrawalAmount;
            System.out.println("Withdrawal of " + withdrawalAmount + " processed, Remaining balance = " + thisbalance);
        }
    }
}
```
**Main.java**
```java
package com.warwick;

public class Main {
    public static void main(String[] args) {
        
        Account bobsAccount = new Account();

        bobsAccount.setNumber("12345");
        bobsAccount.setBalance(0.00);
        bobsAccount.setCustomerName("Bob Brown");
        bobsAccount.setCustomerEmailAddress("bob@gmail.com");
        bobsAccount.setCustomerPhoneNumber("082 505 8736");

        bobsAccount.withdrawal(100.0);

        bobsAccount.deposit(50.0);
        bobsAccount.withdrawal(100.0);

        bobsAccount.deposit(51.0);
        bobsAccount.withdrawal(100.0);

        //Output: Only 0.0 available. Withdrawal not processed
        //Deposit of 50.0 made. New balance is 50.0
        //Only 50 available.  Withdrawal not processed
        //Deposit of 51.0 made. New balance is 101.0
        //Withdrawal of 100.0 processed. Remaining balance = 1.0
    }
}
```
Instead of initialising everything using getters and setters there is another way we can do this when we are creating an object for the first time using a class.  This is by using constructors to set the initial values.

# <a name="2_constructors"></a> 2 Constructors

- ### Whenever we are initialising a class with the new keyword, the constructor will be executed to create the object for us  
- ### Constructors can be created with or without parameters and you can have more than one constructor in a class  
- ### Constructors' name must be the same as the classes name and it has no return type 
- ### Constructors can be overloaded so there can be more than one constructor
- ### Constructors with no parameters can pass default values to the constructor with parameters given it uses the this keyword on the first line of the constructor body

Lets add constructors to the Account class:
Notice how the constructor without any paramters uses the '*this*' keyword to pass default paramters which are then assigned in the second constructor.  

**Account.java**
```java
package com.warwick;

public class Account {
    private String number;
    private double balance;
    private String customerName;
    private String customerEmailAddress;
    private String customerPhoneNumber;

    public Account() {
        this("56789", 2.50, "default name", "default address", "default phone");
        System.out.println("Empty constructor called");
    }

    public Account(String number, double balance, String customerName, String customerEmailAddress,
                   String customerPhoneNumber) {
        System.out.println("Account constructor with parameters called");
        this.number = number;
        this.balance = balance;
        this.customerName = customerName;
        this.customerEmailAddress = customerEmailAddress;
        this.customerPhoneNumber = customerPhoneNumber;
    }

    // METHODS GO HERE
    
    // GETTERS AND SETTERS GO HERE
}
```
**Main.java**
```java
package com.timbuchalka;

public class Main {

    public static void main(String[] args) {

        Account bobsAccount = new Account();
        // Parameterless constructor called &
        // Default values specified are passed on to the second constructor as a result of the 'this' keyword
        
        //Outputs: 
        // Account constructor with parameters called // This line is outputted first because the 'this' keyword called the second constructor 
        // Empty constructor called 

        //Respective getter and setter values will be: "56789", 2.5, "default name", "default address", "default phone"
    }
}
```
___ 
## *Constructors continued*
___

As a general rule of thumb when working with constructors it is better to use the *this* keyword for assigning values to instance variables.  This is the point where the object is being created and it is less error-prone approach. 

> ### Intellij has a trick to quickly create constructors.  Click on *code -> generate* and choose desired fields to target.

- ## It is not uncommon to have multiple constructors but there will always be a main constructor which assigns all the classes' instance variables values. 

- ### Other constructors may have zero parameters or only a few parameters.  Zero parameters will set all the default parameter values using the this keyword whereas constructors with a few parameters will pass the respective default and parameter values to the this keyword.   

See VipPerson class below which has **3** constructors: 

**VipPerson.java**
```java
package com.warwick;

public class VipPerson {
    private String name;
    private double creditLimit;
    private String emailAddress;

    public VipPerson() {
        this("Default name", 50000.00, "default@email.com");
    }
    public VipPerson(String name, double creditLimit) {
        this(name, creditLimit, "unknown@email.com");
    }

    public VipPerson(String name, double creditLimit, String emailAddress) {
        this.name = name;
        this.creditLimit = creditLimit;
        this.emailAddress = emailAddress;
    }

    public String getName() {
        return name;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
}
```
**Main.java**
```java
package com.warwick;

public class Main {

    public static void main(String[] args) {
        // Purely default contructor
        VipPerson person1 = new VipPerson();
        System.out.println(person1.getName());

        // 2 Parameter constructor
        VipPerson person2 = new VipPerson("Bob", 25000.00);
        System.out.println(person2.getName());
        System.out.println(person2.getEmailAddress());
        // Email address here outputs: unknown@email.com since it is the default for the 2 parameter constructor

        // All Parameter constructor 
        VipPerson person3 = new VipPerson("Tim", 100.00, "tim@email.com");
        System.out.println(person3.getName());
        System.out.println(person3.getEmailAddress());
    }
}
```
# <a name="3_inheritance"></a> 3 Inheritance

All animals are different in many ways but also share some common features.  Inheritance is used to share common features among classes.

We declare an animal class:

**Animal.java**
```java
package com.warwick;

public class Animal {

    private String name;
    private int brain;
    private int body;
    private int size;
    private int weight;

    public Animal(String name, int brain, int body, int size, int weight) {
        this.name = name;
        this.brain = brain;
        this.body = body;
        this.size = size;
        this.weight = weight;
    }

    public void eat() {
        System.out.println("Animal.eat() called");
    }

}
```

- ### Dog class extends the animal class and as a result it gains access to the parameters and properties of the animal constructor.  
- ### It also includes parameters and properties from its own instance variables.  
- ### **super** keyword accesses the animal constructor and in this case since dogs always have a brain and a body those parameters are removed from the constructor parameters and passed directly to the *super* call.
- ### Its possible to call methods from the parent without the super keyword but if a overriden method already exists in the child then this method will take precedence if the this keyword is omitted.   

> ### **Super** refers to the class that was extended 

Dog overrides the public eat() method to create its own version, but it also calls the *super.eat()* which accesses the original eat on the super class. 

**Dog.java**
```java
package com.warwick;

public class Dog extends Animal {

    private int eyes;
    private int legs;
    private int tail;
    private int teeth;
    private String coat;

    public Dog(String name, int size, int weight, int eyes, int legs, int tail, int teeth, String coat) {
        super(name, 1, 1, size, weight);
        this.eyes = eyes;
        this.legs = legs;
        this.tail = tail;
        this.teeth = teeth;
        this.coat = coat;
    }

    private void chew() {
        System.out.println("Dog.chew() called");
    }

    @Override
    public void eat() {
        System.out.println("Dog.eat() called");
        chew();
        super.eat();
    }
}
```

Notice how dog has many parameters since it 
**Main.java**
```java
package com.warwick;

public class Main {

    public static void main(String[] args) {
	    Animal animal = new Animal("Animal", 1, 1, 5, 5);

        Dog dog = new Dog("Yorkie", 8, 20, 2, 4, 1, 20, "long silky");
        dog.eat();

    }
}
// Outputs: 

// Dog.eat() called
// Dog.chew() called
// Animal.eat() called
```
___ 
## *Constructors continued*
___

The concept of inheritance is taking the base characteristics defined in one class and then extending from or inheriting from (the methods and fields) that class to create a new class to inherit those states which we can use in the new class and then we can add new methods and new fields to the new class as well.

Once again referring to the Animal class.
**Animal.java**
```java
package com.warwick;

public class Animal {

    private String name;
    private int brain;
    private int body;
    private int size;
    private int weight;

    public Animal(String name, int brain, int body, int size, int weight) {
        this.name = name;
        this.brain = brain;
        this.body = body;
        this.size = size;
        this.weight = weight;
    }

    public void eat() {
        System.out.println("Animal.eat() called");

    }

    public void move(int speed) {
        System.out.println("Animal.move() called.  Animal is moving at " +speed);

    }

    public String getName() {
        return name;
    }

    public int getBrain() {
        return brain;
    }

    public int getBody() {
        return body;
    }

    public int getSize() {
        return size;
    }

    public int getWeight() {
        return weight;
    }
}
```
**Fish.java**
```java
package com.warwick;

public class Fish extends Animal {

    private int gills;
    private int eyes;
    private int fins;

    public Fish(String name, int size, int weight, int gills, int eyes, int fins) {

        super(name, 1, 1, size, weight);
        this.gills = gills;
        this.eyes = eyes;
        this.fins = fins;
    }

    private void rest() {

    }

    private void moveMuscles() {

    }

    private void moveBackFin() {

    }

    private void swim(int speed) {
        moveMuscles();
        moveBackFin();
        super.move(speed);
    }
}
```
**Main.java**
```java
package com.warwick;

public class Main {

    public static void main(String[] args) {

        Fish fish = new Fish("Goldfish", 5, 3, 10, 2, 3);
        fish.move(10);
        // fish.move() in this case calls the move method which it inherited from the animal class 
        // Outputs: 
        // Animal.move() called.  Animal is moving at 10
    }
}
```
# <a name="4_Reference_vs_Object_vs_Instance_vs_Class"></a> 4 Reference vs Object vs Instance vs Class

- ### Lets use the analogy of building a house to understand classes
- ### A **class** is basically a blueprint for a house, using the blueprint (plans) we can build as many houses as we like based on those plans

- ### Each house you build (in other words instantiate using the new operator) is an **object** also known as an **instance**

- ### Each house you build has an address (a physical location). In other words, if you want to tell someone where you live, you give them your address (perhaps written one a piece of paper). This is known as a reference.
- ### You can copy the reference as many times as you like but there is still just one house. In other words we are copying the address not the house.  

- ### We can pass **references** as **parameters** to **constructors** and **methods**.

![reference-vs-object-vs-instance-vs-class](images/section-6/reference-vs-object-vs-instance-vs-class.png)

# <a name="5_this_vs_super"></a> 5 this vs super

## this

The keyword **this** is used to call the current class members (variables and methods).  This is required when we have a parameter with the same name as an instance variable(field).

Note that we can use both of them anywhere in a class except static areas(the static block or a static method). 

## super

The keyword **super** is used to access/call the parent class members (variables and methods).
___

## this example

The keyword **this** is commonly used with **constructors** and **setters**, and optionally in getters (easier for beginners). In the example below we are using the **this** keyword in the **constructor** and **setter** since there is a parameter with the same name. In the getter we don't have any parameters so the **this** keyword is optional. 

```java
class House {
    private String color;

    public House(String color) {
        // this keyword is required since it has same parameter name as field
        this.color = color;
    }
    
    public void setColor(String color) {
        // once again this keyword is required same parameter as field
        this.color = color;
    }

    public String getColor() {
        // this is optional
        return color;
    }
}
```

## super example 

The keyword **super** is commonly used with **method overriding**, when we call a method with the same name from the parent class. In the example below we have a method printMethod that calls super.printMethod.

In other words its calling the method with the same name from the parent class. Without the keyword **super** in this case it would be a recursive call. Meaning that the method would call it self forever (or until memory is fully used). This is why the **super** keyword is needed. 

```java
class SuperClass {  // parent class
    public void printMethod() {
        System.out.println("Printed in Superclass");
    }
}

class SubClass extends SuperClass { // subclass is a child class
    // override parent method
    @Override
    public void printMethod() {
        super.printMethod();
        System.out.println("Printed in Subclass");
    }
}

class MainClass {
    public static void main(String[] args) {
        SubClass s = new SubClass();
        s.printMethod();
    }
}
```

## this()


## super()





- ### [1 Method overloading vs overriding](#1_Method_overloading_vs_overriding)

# <a name="1_Method_overloading_vs_overriding"></a> 1 Method overloading vs overriding

- ### [1 Classes](#1_classes)
# <a name="1_classes"></a> 1 Classes