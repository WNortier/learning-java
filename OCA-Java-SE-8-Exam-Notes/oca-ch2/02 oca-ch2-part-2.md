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

# <a name="#6_Implementing_an_Interface"></a> 6 Implementing an Interface

# <a name="7_Legal_Return_Types"></a> 7 Legal Return Types

# <a name="8_Constructors_&_Instantiation"></a> 8 Constructors & Instantiation

# <a name="9_Initialization_Blocks"></a> 9 Initialization Blocks

# <a name="10_Statics"></a> 10 Statics