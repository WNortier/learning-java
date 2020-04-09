- ### [5 Casting](#5_Casting)
- ### [6 Implementing an Interface](#6_Implementing_an_Interface)
- ### [7 Legal Return Types](#7_Legal_Return_Types)
- ### [8 Constructors & Instantiation](#8_Constructors_&_Instantiation)
- ### [9 Initialization Blocks](#9_Initialization_Blocks)
- ### [10 Statics](#10_Statics)

# <a name="5_Casting"></a> 5 Casting

### OCA Objectives

- **2.2 Differentiate between object reference variables and primitive variables.**
- **7.3 Determine when casting is necessary.**

You've seen how it's both possible and common to use general reference variable types to refer to more specific object types. It's at the heart of polymorphism.

`Animal animal = new Dog();`

What happens when you want to use that animal reference variable to invoke a method that only class Dog has? You know it's referring to a Dog, and you want to do a Dog-specific thing? Let's agree for now that all this code is okay, except that we're not sure about the line of code that invokes the playDead method.

```java
class Animal {
    void makeNoise() {System.out.println("generic noise"); }
}

class Dog extends Animal {
    void makeNoise() {System.out.println("bark"); }
    void playDead() { System.out.println("roll over"); }
}

class CastTest2 {
    public static void main(String [] args) {
        Animal [] a = {new Animal(), new Dog(), new Animal() };
    
        for(Animal animal : a) {
            animal.makeNoise();
            if(animal instanceof Dog) {
                animal.playDead(); // try to do a Dog behavior?
            }
        }
    }
}
```

When we try to compile this code, the compiler says something like this:

`cannot resolve symbol`

The compiler is saying, "Hey, class Animal doesn't have a playDead() method." Let's modify the if code block:

```java
if(animal instanceof Dog) {
    Dog d = (Dog) animal; // casting the ref. var.
    d.playDead();
}
```

The new and improved code block contains a **cast**, which in this case is sometimes called a downcast, because we're casting down the inheritance tree to a more specific class. Now the compiler is happy. Before we try to invoke playDead, we cast the animal variable to type Dog. What we're saying to the compiler is, "We know it's really referring to a Dog object, so it's okay to make a new Dog reference variable to refer to that object." In this case we're safe, because before we ever try the cast, we do an instanceof test to make sure. It's important to know that the compiler is forced to trust us when we do a downcast, even when we screw up:

```java
class Animal { }
class Dog extends Animal { }
class DogTest {

    public static void main(String [] args) {
        Animal animal = new Animal();
        Dog d = (Dog) animal; // compiles but fails later
    }
}
```

All the compiler can do is verify that the two types are in the same inheritance tree, so that depending on whatever code might have come before the downcast, it's possible that animal is of type Dog. The compiler must allow things that might possibly work at runtime. However, if the compiler knows with certainty that the cast could not possibly work, compilation will fail. The following replacement code block will NOT compile:

```java
Animal animal = new Animal();
Dog d = (Dog) animal;
String s = (String) animal; // animal can't EVER be a String
```

In this case, you'll get an error like this:

```incovertible types```

Unlike *downcasting*, **upcasting** (casting up the inheritance tree to a more general type) works implicitly (that is, you don't have to type in the cast) because when you upcast you're implicitly restricting the number of methods you can invoke, as opposed to *downcasting*, which implies that later on, you might want to invoke a more specific method. Here's an example:

```java
class Animal { }
class Dog extends Animal { }
class DogTest {
    public static void main(String [] args) {
        Dog d = new Dog();
        Animal a1 = d; // upcast ok with no explicit cast
        Animal a2 = (Animal) d; // upcast ok with an explicit cast
    }
}
```

Both of the previous upcasts will compile and run without exception because a `Dog` IS-A(n) Animal, which means that anything an `Animal` can do, a `Dog` can do. 

A `Dog` can do more, of course, but the point is that anyone with an `Animal` reference can safely call `Animal` methods on a `Dog` instance. The `Animal` methods may have been *overridden* in the `Dog` class, but all we care about now is that a `Dog` can always do at least everything an `Animal` can do. 

> The compiler and JVM know it, too, so the implicit upcast is always legal for assigning an object of a subtype to a reference of one of its supertype classes (or interfaces).
___

If `Dog` implements `Pet` and `Pet` defines `beFriendly()`, then a `Dog` can be implicitly cast to a `Pet`, but the only `Dog` method you can invoke then is `beFriendly()`, which Dog was forced to implement because `Dog` implements the `Pet` interface. One more thing...if `Dog` implements `Pet`, then, if `Beagle` extends `Dog` but `Beagle` does not declare that it implements `Pet`, `Beagle` is still a `Pet`! 

> ### `Beagle` is a `Pet` simply because it extends `Dog`, and Dog's **already taken care of the `Pet` parts for itself and for all its children when it implemented the `Pet` interface**. 

The `Beagle` class can always override any method it inherits from `Dog`, including methods that `Dog` implemented to fulfill its interface contract.

And just one more thing...if `Beagle` does declare that it implements `Pet`, just so that others looking at the `Beagle` class API can easily see that `Beagle` IS-A Pet without having to look at Beagle's superclasses, `Beagle` still doesn't need to implement the `beFriendly()` method if the Dog class (Beagle's superclass) has already taken care of that. In other words, if `Beagle` IS-A `Dog`, and `Dog` IS-A `Pet`, then `Beagle` IS-A `Pet` and has already met its `Pet` obligations for implementing the `beFriendly()` method since it inherits the `beFriendly()` method. The compiler is smart enough to say, "I know `Beagle` already IS a `Dog`, but it's okay to make it more obvious by adding a cast." 

So don't be fooled by code that shows a concrete class that declares it implements an interface but doesn't implement the methods of the interface. Before you can tell whether the code is legal, you must know what the supertypes of this implementing class have declared. 

> ### If any supertype in its inheritance tree has already provided concrete (that is, nonabstract) method implementations, then regardless of whether the supertype declares that it implements the interface, the subclass is under no obligation to reimplement (override) those methods.

![casting-exam-watch](images/casting-exam-watch.png)

## More on Casting

[A great video on casting](https://www.youtube.com/watch?v=Qpz2MA4KE9U)

# <a name="#6_Implementing_an_Interface"></a> 6 Implementing an Interface

When you implement an interface, you're agreeing to adhere to the contract defined in the interface.

The JVM runs a compiler check on any class that claims to implement an interface. If the class says it's implementing an interface, it darn well better have an implementation for each abstract method in the interface (with a few exceptions that we'll look at in a moment). Assuming an interface Bounceable with two methods, bounce() and setBounceFactor(), the following class will compile:

```java
public class Ball implements Bounceable { // Keyword
// 'implements'
    public void bounce() { }
    public void setBounceFactor(int bf) { }
}
```

It compiles, though. And it runs. The interface contract guarantees that a class will have the method (in other words, others can call the method subject to access control), but it never guaranteed a good implementation—or even any actual implementation code in the body of the method. Keep in mind, though, that if the interface declares that a method is NOT void, your class's implementation code has to include a return statement.

Implementation classes must adhere to the same rules for method implementation as a class extending an abstract class. To be a legal implementation class, a nonabstract implementation class must do the following:

- ### Provide concrete (nonabstract) implementations for all abstract methods from the declared interface.
- ### Follow all the rules for legal overrides, such as the following:

#### 1 *Declare no checked exceptions on implementation methods other than those declared by the interface method or subclasses of those declared by the interface method.*

#### 2 *Maintain the signature of the interface method, and maintain the same return type (or a subtype). (But it does not have to declare the exceptions declared in the interface method declaration.)*

![interface-exam-watch](images/interface-exam-watch.png)

An implementation class can itself be abstract! For example, the following is legal for a class Ball implementing Bounceable: 

`abstract class Ball implements Bounceable { }`

Notice anything missing? We never provided the implementation methods. And that's okay. If the implementation class is *abstract*, it can simply pass the buck to its first concrete subclass. For example, if class BeachBall extends Ball, and BeachBall is not abstract, then BeachBall has to provide an implementation for all the abstract methods from Bounceable:

```java
class BeachBall extends Ball {
// Even though we don't say it in the class declaration above,
// BeachBall implements Bounceable, since BeachBall's abstract superclass (Ball) implements Bounceable
// Therefore it must declare all the methods in the Bounceable interface

    public void bounce() {
    // interesting BeachBall-specific bounce code
    }

    public void setBounceFactor(int bf) {
    // clever BeachBall-specific code for setting a bounce factor
    }
    
// If Ball defined any abstract methods they would have to be implemented here also
}
```

> Look for classes that claim to implement an interface but don't provide the correct method implementations. Unless the implementing class is abstract, the implementing class must provide implementations for all abstract methods defined in the interface.

### There are two more rules:

#### A class can implement more than one interface. It's perfectly legal to say, for example, the following:

`public class Ball implements Bounceable, Serializable, Runnable { ... }`

*You can extend only one class, but you can implement many interfaces (which, as of Java 8, means a form of multiple inheritance, which we'll discuss shortly). In other words:*

 - Subclassing defines who and what you are, whereas 
 - Implementing defines a role you can play or a hat you can wear, despite how different you might be from some other class implementing the same interface (but from a different inheritance tree). 
 
 *For example, a Person extends HumanBeing (although for some, that's debatable). But a Person may also implement Programmer, Snowboarder, Employee, Parent, or PersonCrazyEnoughToTakeThisExam.*

#### An interface can itself extend another interface (or multiple). The following code is perfectly legal: 

`public interface Bounceable extends Moveable { }`

The first concrete (nonabstract) implementation class of Bounceable must implement all the abstract methods of Bounceable, plus all the abstract methods of Moveable! 

> ### The subinterface, as we call it, simply adds more requirements to the contract of the superinterface.

The first concrete (nonabstract) implementation class of Bounceable must implement all the abstract methods of Bounceable, plus all the abstract methods of Moveable! The subinterface, as we call it, simply adds more requirements to the contract of the superinterface.

*You'll see this concept applied in many areas of Java, especially Java EE, where you'll often have to build your own interface that extends one of the Java EE interfaces.*

As we mentioned earlier, a class is not allowed to extend multiple classes in Java.

An interface, however, is free to extend multiple interfaces:
```java
interface Bounceable extends Moveable, Spherical { // ok!
    void bounce();
    void setBounceFactor(int bf);
}

interface Moveable {
    void moveIt();
}

interface Spherical {
    void doSphericalThing();
}
```

In the next example, `Ball` is required to implement `Bounceable`, plus all abstract methods from the interfaces that `Bounceable` extends (including any interfaces those interfaces extend, and so on, until you reach the top of the stack—or is it the bottom of the stack?). So Ball would need to look like the following:
```java
class Ball implements Bounceable {
    public void bounce() { } // Implement Bounceable's methods
    public void setBounceFactor(int bf) { }
    public void moveIt() { } // Implement Moveable's method
    public void doSphericalThing() { } // Implement Spherical
}
```
`Ball` needs to implement all of the abstract methods from `Bounceable`, unless `Ball` is marked abstract. In that case,`Ball` could choose to implement any, all, or none of the abstract methods from anyof the interfaces, thus leaving the rest of the implementations to a concrete subclass of `Ball`, as follows:
```java
abstract class Ball implements Bounceable {
    public void bounce() { ... } // Define bounce behavior
    public void setBounceFactor(int bf) { ... }
    // Don't implement the rest; leave it for a subclass
}

class SoccerBall extends Ball { 
    // class SoccerBall must
    // implement the interface
    // methods that Ball didn't
    public void moveIt() { ... }
    public void doSphericalThing() { ... }
    // SoccerBall can choose to override the Bounceable methods
    // implemented by Ball
    public void bounce() { ... }
}
```

![interface-exam-watch-2](images/interface-exam-watch-2.png)

Figure 2-5 compares ***concrete*** and ***abstract*** examples of extends and implements for both classes and interfaces.

![interface-comparisons](images/interface-comparisons.png)

A class CAN implement interfaces with duplicate, concrete method signatures! But the good news is that the compiler's got your back, and if you DO want to implement both interfaces, you'll have to provide an overriding method in your class. 

Let's look at the following code:
```java
interface I1 {
    default int doStuff() { return 1; }
}
interface I2 {
    default int doStuff() { return 2; }
}
    
public class MultiInt implements I1, I2 { // needs to override
    public static void main(String[] args) {
        new MultiInt().go();
    }

    void go() {
        System.out.println(doStuff());
    }
    // public int doStuff() {
    // return 3;
    // }
}
```
As the code stands, it WILL NOT COMPILE because it's not clear which version of `doStuff()` should be used. In order to make the code compile, you need to ***override*** `doStuff()` in the class. Uncommenting the class's `doStuff()` method would allow the code to compile and when run produce the following output:

`3`

# <a name="7_Legal_Return_Types"></a> 7 Legal Return Types

# <a name="8_Constructors_&_Instantiation"></a> 8 Constructors & Instantiation

# <a name="9_Initialization_Blocks"></a> 9 Initialization Blocks

# <a name="10_Statics"></a> 10 Statics