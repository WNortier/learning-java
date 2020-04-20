- ### [2 Encapsulation](#2_Encapsulation)

# <a name="2_Encalpsulation"></a> 2 Encapsulation

Encapsulation is the mechanism that allows you to restrict access to certain components in the objects that you are creating. You're able to protect the members of a class from external access. We're stopping classes from outside the class we're working on from accessing the inner workings of a class.

Lets look at an example of encapsulation.

**EnhancedPlayer.java**

```java
public class EnhancedPlayer {
    private String name;
    private int hitPoints = 100;
    private String weapon;

    public EnhancedPlayer(String name, int health, String weapon) {
        this.name = name;

        if(health >0 && health <= 100) {
            this.hitPoints = health;
        }

        this.weapon = weapon;
    }

    public void loseHealth(int damage) {
        this.hitPoints = this.hitPoints - damage;
        if(this.hitPoints <=0) {
            System.out.println("Player knocked out");
            // Reduce number of lives remaining for the player
        }
    }

    public int getHealth() {
        return this.hitPoints;
    }
}
```

**Main.java**

```java
public class Main {
  public static void main(String[] args) {
    EnhancedPlayer player = new EnhancedPlayer("Tim", 200, "Sword");
    System.out.println("Initial health is " + player.getHealth());
  }
}
```

---

## _Encapsulation continued_

---

**Printer.java**

```java
public class Printer {
  private int tonerLevel;
  private int pagesPrinted;
  private boolean duplex;

  public Printer(int tonerLevel, boolean duplex) {
      if(tonerLevel >-1 && tonerLevel <=100) {
          this.tonerLevel = tonerLevel;
      } else {
          this.tonerLevel = -1; // This is often a way to set a variable if it doesnt fulfill some validation, set it to -1
      }

      this.duplex = duplex;
      this.pagesPrinted = 0;
  }

  public int addToner(int tonerAmount) {
      if(tonerAmount >0 && tonerAmount <=100) {
          if(this.tonerLevel + tonerAmount >100) {
              return -1;
          }
          this.tonerLevel += tonerAmount;
          return this.tonerLevel;
      } else {
          return -1;
      }
  }

  public int printPages(int pages) {
      int pagesToPrint = pages;
      if(this.duplex) {
          pagesToPrint = (pages / 2) + (pages % 2);   // In the event that the pages to print is an odd amount (5) the remainder will be 1
          System.out.println("Printing in duplex mode");
      }
      this.pagesPrinted += pagesToPrint;
      return pagesToPrint;
  }

  public int getPagesPrinted() {
      return pagesPrinted;
  }
}
```

**Main.java**

```java

```
