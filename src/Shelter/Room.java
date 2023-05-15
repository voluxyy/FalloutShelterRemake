package Shelter;

import java.util.ArrayList;
import java.util.stream.Collectors;

import Characters.Human;

public abstract class Room {
    protected int maxSize = 4;
    protected ArrayList<Human> humans = new ArrayList<>();

    public ArrayList<Human> getHumans() {
        return this.humans;
    }

    public ArrayList<Human> getMale() {
        return this.humans.stream()
                .filter(Human::getIsMale)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Human> getFemale() {
        return this.humans.stream()
                .filter(human -> !human.getIsMale())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public int getMaxSize() {
        return this.maxSize;
    }

    public void addHuman(Human human) {
        this.humans.add(human);
    }
    public void removeHuman(Human human) {
        this.humans.remove(human);
    }
}
