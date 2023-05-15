package Characters;

import java.util.Scanner;
import java.util.Random;

public class Human {
    private Scanner scanner;
    protected String name;
    protected int age;
    boolean isMale;
    int fatigue = 0;
    int hungry = 0;
    public Human(String name, int age, boolean isMale) {
        this.name = name;
        this.age = age;
        this.isMale = isMale;
    }

    public String getName() {
        return this.name;
    }
    public int getAge() {
        return this.age;
    }
    public boolean getIsMale() {
        return this.isMale;
    }
    public int getFatigue() {
        return this.fatigue;
    }
    public int getHungry() {
        return this.hungry;
    }

    public void updateAge() {
        this.age++;
    }

    public void updateFatigue(int fatigue) {
        this.fatigue = fatigue;
    }
    public void updateHungry(int hungry) {
        this.hungry = hungry;
    }

    public void kill(int code) {
        System.out.print(this.name + " is dead because of ");
        if (code == 0) {
            System.out.println("his age.");
            return;
        }
        if (code == 1) {
            System.out.println("he was hungry.");
        }
        if (code == 2) {
            System.out.println("he was fatigue.");
        }
    }

    public Human makeAChild(String name) {
        Random random = new Random();
        return new Human(name, 0, random.nextBoolean());
    }
}
