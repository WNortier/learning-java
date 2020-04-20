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
