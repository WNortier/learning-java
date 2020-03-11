# MAIN:

public static void main(String[ ] args)
This definition indicates that the **main** method takes an array of Strings as its parameters, and does not return a value.

When you do not need to return any value from your method, use the keyword *void*.
Notice the void keyword in the definition of the main method - this means that main does not return anything.

# METHODS:

**Methods** define behavior. A method is a collection of statements that are grouped together to perform an operation. 

```java
class MyClass {

  static void sayHello() {
    System.out.println("Hello World!");
  }

  public static void main(String[ ] args) {
    sayHello();
  }
}
```

# RETURN TYPES:

```java
static int sum(int val1, int val2) {
   return val1 + val2;
}
```

Notice that in the method definition, we defined the return type before we defined the method name. For our sum method, it is int, as it takes two parameters of the type int and returns their sum, which is also an int.

# CLASSES:

A class describes what the object will be, but is separate from the object itself.
**Classes** can be described as blueprints. The first step is to define the class, which then becomes a blueprint for object creation.

In order to create your own custom objects, you must first create the corresponding classes.
Clicking on the src folder in Eclipse and selecting Create->New->Class.  An object is an instance of a class.
A class has attributes and methods. The attributes are basically variables within a class.

__Animal.java__
```java
public class Animal {

String name;
int age;
  
void bark() {
    System.out.println("Woof-Woof");
  }
}
```
Let's head over to our main and create a new object of our class.
We can create multiple objects of our Animal class, and use the dot syntax to access their attributes and methods.

__MyClass.java__
```java
class MyClass {
  public static void main(String[ ] args) {
    Animal toypom = new Animal();
    toypom.bark();
    Animal poodle = new Animal();
    poodle.age = 5;
  }
}
```
# GETTERS AND SETTERS

**Getters** and **setters** are building blocks for encapsulation.  
The keyword this is used to refer to the current object.
camelCase naming => setColor or getColor 

```java
public class Vehicle {
  private String color;

  public String getColor() {
    return color;
  }

  public void setColor(String c) {
    this.color = c;
  }
}
```

```java
public static void main(String[ ] args) {
  Vehicle v1 = new Vehicle();
  v1.setColor("Red");
  System.out.println(v1.getColor());
  //Outputs "Red"
}
```

# CONSTRUCTORS

Constructors are special methods invoked when an object is created and are used to initialize them.
A constructor can be used to provide initial values for object attributes.
Java automatically provides a default constructor, so all classes have a constructor, whether one is specifically defined or not.

- A **constructor** name must be same as its class name.
- A **constructor** must have no explicit return type.


```java
public class Vehicle {
  private String color;
  Vehicle() {
     color = "Red";
  }
}
```

The Vehicle() method is the constructor of our class, so whenever an object of that class is created, the color attribute will be set to "Red".
A constructor can also take parameters to initialize attributes.

```java
public class Vehicle {
  private String color;
  Vehicle(String c) {
    color = c;
  }
}
```
> ### You can think of constructors as methods that will set up your class by default, so you don’t need to repeat the same code every time.
___
A single class can have multiple constructors with different numbers of parameters.
The setter methods inside the constructors can be used to set the attribute values.

```java
public class Vehicle {
  private String color;

  Vehicle() {
    this.setColor("Red");
  }
  Vehicle(String c) {
    this.setColor(c);
  }

  // Setter
  public void setColor(String c) {
    this.color = c;
  }
}
```
The class above has two constructors, one without any parameters setting the color attribute to a default value of "Red", and another constructor that accepts a parameter and assigns it to the attribute.

> ### The constructor is called when you create an object using the new keyword.

```java
public class MyClass {
  public static void main(String[ ] args) {
    //color will be "Red"
    Vehicle v1 = new Vehicle();

    //color will be "Green"
    Vehicle v2 = new Vehicle("Green");
  }
}
```
# VALUE AND REFERENCE TYPES

## VALUE TYPES

**Value types** are the basic types, and include:
- 1 *byte* 
- 2 *short* 
- 3 *int* 
- 4 *long* 
- 5 *float* 
- 6 *double* 
- 7 *boolean*
- 8 *char*

These data types store the values assigned to them in the corresponding memory locations.
So, when you pass them to a method, you basically operate on the variable's **value**, rather than on the variable itself.

```java
public class MyClass {
  public static void main(String[ ] args) {
    int x = 5;
    addOneTo(x);
    System.out.println(x);       
  }
  static void addOneTo(int num) {
    num = num + 1;
  }
}
// Outputs "5"
```

> ## The method from the example above takes the **value** of its parameter, which is why the original variable is not affected and 5 remains as its value.

____

# REFERENCE TYPES

A **reference type** stores a reference (or address) to the memory location where the corresponding data is stored, and include:
- 1 *Strings*
- 2 *Arrays*

When you create an object using the *constructor* (special methods that are used to initialize objects, invoked at the time of object creation), you create a reference variable.

For example, consider having a **Person** class defined:
```java
public class Person {
    private String name;
    private int age;
    
    Person (String n) {
        this.name = n;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int a) {
        this.age = a;
    }
}
```

```java
public class MyClass {
  public static void main(String[ ] args) {
    Person j;
    j = new Person("John");
    j.setAge(20);
    celebrateBirthday(j);
    System.out.println(j.getAge());
  }
  static void celebrateBirthday(Person p) {
    p.setAge(p.getAge() + 1);
  }
}
//Outputs "21"
```
The method **celebrateBirthday** takes a Person object as its parameter, and increments its attribute.
Because j is a reference type, the method affects the object itself, and is able to change the actual value of its attribute.

# STATIC 

When you declare a variable or a method as **static**, it belongs to the class, rather than to a specific instance. 

This means that only one instance of a **static** member exists, even if you create multiple objects of the class, or if you don't create any. It will be shared by all objects.

```java
public class Counter {
  public static int COUNT=0;
  Counter() {
    COUNT++;
  }
}
```

The COUNT variable will be shared by all objects of that class.
Now, we can create objects of our Counter class in main, and access the **static** (it belongs to the class rather than a specific instance) variable.

```java
public class MyClass {
  public static void main(String[ ] args) {
    Counter c1 = new Counter();
    Counter c2 = new Counter();
    System.out.println(Counter.COUNT);
  }
}
//Outputs "2"
```

The output is 2, because the COUNT variable is static and gets incremented by one each time a new object of the Counter class is created. In the code above, we created 2 objects.
You can also access the static variable using any object of that class, such as c1.COUNT.

> ## It’s a common practice to use upper case when naming a static variable, although not mandatory.
___

The same concept applies to **static** methods.  The horn method and wheels variable can be called without creating an object:

```java
public class Vehicle {
    public static int wheels = 4;
    public static void horn() {
        System.out.println("Beep");
    }
}

public class MyClass {
    public static void main(String[ ] args) {
        System.out.println(Vehicle.wheels);
        //Outputs "4"
        Vehicle.horn();
        //Outputs "Beep"
    }
}
```

Another example of **static** methods are those of the *Math* class, which is why you can call them without creating a *Math* object.

> ## Also, the main method must always be static.

# FINAL

Use the **final** keyword to mark a variable constant, so that it can be assigned only once.
PI is now a constant. Any attempt to assign it a value will cause an error.

```java
class MyClass {
  public static final double PI = 3.14; 
  public static void main(String[ ] args) {
    System.out.println(PI);
  }
}
```

> ## Methods and classes can also be marked final. This serves to restrict methods so that they can't be overridden and classes so that they can't be subclassed.

# PACKAGES

**Packages** are used to avoid name conflicts and to control access to classes.
A *package* can be defined as a group made up of similar types of classes, along with sub-packages.

![packages](https://api.sololearn.com/DownloadFile?id=2868)