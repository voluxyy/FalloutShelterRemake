package Shelter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import Characters.Human;

public abstract class Room {
    protected int maxSize = 5;
    protected ArrayList<Human> humans = new ArrayList<>();

    public ArrayList<Human> getHumans() {
        return this.humans;
    }

    public ArrayList<Human> getFemale() {
        return (ArrayList<Human>) this.humans.stream().filter(Human::getIsMale).toList();
    }

    public void addHuman(Human human) {
        this.humans.add(human);
    }
    public void removeHuman(Human human) {
        this.humans.remove(human);
    }
}
