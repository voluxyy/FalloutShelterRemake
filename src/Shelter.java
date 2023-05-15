import java.util.ArrayList;
import java.util.Scanner;

import Shelter.*;

public class Shelter {
    int yearsLived;
    Ressources ressources;
    ArrayList<Room> rooms = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public Shelter() {
        this.rooms.add(new Storage());
        this.ressources = new Ressources();
        this.ressources.adjustWood(1500);
        this.ressources.adjustStone(750);
        this.ressources.adjustFood(1000);
    }

    public boolean verifyWood(int nb) {
        return this.ressources.getWood() + nb > 0;
    }

    public boolean verifyStone(int nb) {
        return this.ressources.getStone() + nb > 0;
    }

    // TODO: Event lors d'un passTime
    public void passTime() {
        boolean isEvent = false;
        while (!isEvent) {
            this.yearsLived++;
            for (Room room : this.rooms) {
                for (int i = 0; i < room.getHumans().size(); i++) {
                    room.getHumans().get(i).updateAge();

                    if (room instanceof Dormitory) {
                        room.getHumans().get(i).updateFatigue(0);
                    } else {
                        room.getHumans().get(i).updateFatigue(100);
                    }

                    if (room instanceof Cafeteria) {
                        room.getHumans().get(i).updateHungry(0);
                    } else {
                        room.getHumans().get(i).updateHungry(100);
                    }

                    if (room.getHumans().get(i).getAge() > 30) {
                        room.getHumans().get(i).kill(0);
                        room.getHumans().remove(room.getHumans().get(i));
                        i--;
                        isEvent = true;
                        continue;
                    }
                    if (room.getHumans().get(i).getHungry() < 0) {
                        room.getHumans().get(i).kill(1);
                        room.getHumans().remove(room.getHumans().get(i));
                        i--;
                        isEvent = true;
                        continue;
                    }
                    if (room.getHumans().get(i).getFatigue() < 0) {
                        room.getHumans().get(i).kill(2);
                        room.getHumans().remove(room.getHumans().get(i));
                        i--;
                        isEvent = true;
                        continue;
                    }
                }

                if (room instanceof StoneFactory) {
                    this.ressources.adjustStone(room.getHumans().size() * 100);
                    continue;
                }
                if (room instanceof WoodFactory) {
                    this.ressources.adjustWood(room.getHumans().size() * 100);
                    continue;
                }
                if (room instanceof Field) {
                    this.ressources.adjustFood(room.getHumans().size() * 100);
                    continue;
                }
            }

            this.yearsLived++;
        }
    }

    public static void main(String[] args) {
        Shelter shelter = new Shelter();
        Menu menu = new Menu(shelter);
        menu.mainMenu();
        shelter.scanner.close();
    }
}
