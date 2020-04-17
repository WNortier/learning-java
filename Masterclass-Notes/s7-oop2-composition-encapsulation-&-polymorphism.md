- ### [1 Composition](#1_Composition)
- ### [2 Encapsulation](#2_Encapsulation)
- ### [3 Polymorphism](#3_Polymorphism)

# <a name="1_Composition"></a> 1 Composition

Composition refers to **modelling parts of the greater whole**. 

Lets take a look at Has-A relationships in Java.  If you think of the example of a computer - a computer 'has-a' motherboard, 'has-a' case and 'has-a' monitor. 

Lets start off by modelling these three parts:

1 **Motherboard.java**

```java
package com.timbuchalka;

 class Motherboard {

    private String model;
    private String manufacturer;
    private int ramSlots;
    private int cardSlots;
    private String bios;

    public Motherboard(String model, String manufacturer, int ramSlots, int cardSlots, String bios) {
        this.model = model;
        this.manufacturer = manufacturer;
        this.ramSlots = ramSlots;
        this.cardSlots = cardSlots;
        this.bios = bios;
    }

    public void loadProgram(String programName) {
        System.out.println("Program " + programName + " is now loading...");
    }
    public String getModel() {
        return model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public int getRamSlots() {
        return ramSlots;
    }

    public int getCardSlots() {
        return cardSlots;
    }

    public String getBios() {
        return bios;
    }
}
```

In the monitors case the `Resolution` is a component of the monitor therefore we can say that the monitor 'has-a' resolution.

2 **Monitor.java**

```java
public class Monitor {
    private String model;
    private String manufacturer;
    private int size;
    private Resolution nativeResolution;

    public Monitor(String model, String manufacturer, int size, Resolution nativeResolution) {
        this.model = model;
        this.manufacturer = manufacturer;
        this.size = size;
        this.nativeResolution = nativeResolution;
    }

    public void drawPixelAt(int x, int y, String color) {
        System.out.println("Drawing pixel at " + x + "," + y + " in colour " + color);
    }

    public String getModel() {
        return model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public int getSize() {
        return size;
    }

    public Resolution getNativeResolution() {
        return nativeResolution;
    }
}
```

**Resolution.java**
```java
public class Resolution {
    private int width;
    private int height;

    public Resolution(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
```

Since the case has dimensions we create a separate class for its `Dimensions`. 

3 **Case.java**

```java
public class Case {
    private String model;
    private String manufacturer;
    private String powerSupply;
    private Dimensions dimensions;

    public Case(String model, String manufacturer, String powerSupply, Dimensions dimensions) {
        this.model = model;
        this.manufacturer = manufacturer;
        this.powerSupply = powerSupply;
        this.dimensions = dimensions;
    }

    public void pressPowerButton() {
        System.out.println("Power button pressed");
    }

    public String getModel() {
        return model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getPowerSupply() {
        return powerSupply;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }
}
```

**Dimensions.java**
```java
public class Dimensions {
    private int width;
    private int height;
    private int depth;

    public Dimensions(int width, int height, int depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getDepth() {
        return depth;
    }
}
```

Finally we can put our computer together. Once again we are looking at composition since a PC 'has-a' case, 'has-a' monitor and 'has-a' motherboard. 

**PC.java**
```java
public class PC {
    private Case theCase;
    private Monitor monitor;
    private Motherboard motherboard;

    public PC(Case theCase, Monitor monitor, Motherboard motherboard) {
        this.theCase = theCase;
        this.monitor = monitor;
        this.motherboard = motherboard;
    }

    public Case getTheCase() {
        return theCase;
    }

    public Monitor getMonitor() {
        return monitor;
    }

    public Motherboard getMotherboard() {
        return motherboard;
    }
}
```

After creating our instantiating our `thePC` object we are able to access the methods inside the other classes via its **getters**. 

**Main.java**
```java
package com.warwick;

public class Main {

    public static void main(String[] args) {
	    Dimensions dimensions = new Dimensions(20, 20, 5);
        Case theCase = new Case("2208", "Dell", "240", dimensions);

        Monitor theMonitor = new Monitor("27inch Beast", "Acer", 27, new Resolution(2540, 1440)); // We are passing Resolution without creating a variable since we are not using it anywhere 

        Motherboard theMotherboard = new Motherboard("BJ-200", "Asus", 4, 6, "v2.44");

        PC thePC = new PC(theCase, theMonitor, theMotherboard);
        thePC.getMonitor().drawPixelAt(1500, 1200, "red" );
        thePC.getMotherboard().loadProgram("Windows 1.0");
        thePC.getTheCase().pressPowerButton();
    }
}
```
___ 
## *Composition continued*
___

In essence composition is creating objects within objects. You have a master object managing and looking after other objects. In certain cases you should use inheritance and other cases composition. As a rule its better to use composition as opposed to inheritance. 

Lets take a look at another example using composition.  

# <a name="2_Encalpsulation"></a> 2 Encapsulation

# <a name="3_Polymorphism"></a> 3 Polymorphism











- ### [1 Classes](#1_classes)
# <a name="1_classes"></a> 1 Classes

- ### [1 Classes](#1_classes)
# <a name="1_classes"></a> 1 Classes