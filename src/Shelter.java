import java.util.ArrayList;
import java.util.Scanner;

import Shelter.*;
import Characters.Human;

public class Shelter {
    int yearsLived;
    Ressources ressources;
    ArrayList<Room> rooms = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public Shelter() {
        // TODO: finalize the beginning of the game
        this.ressources = new Ressources();

        this.rooms.add(new Dormitory());
        this.rooms.add(new Cafeteria());
        this.rooms.add(new Field());
        this.rooms.add(new StoneFactory());
        this.rooms.add(new WoodFactory());
        this.rooms.add(new Storage());

        this.rooms.get(0).addHuman(new Human("Christophe", 29, true));
        this.rooms.get(1).addHuman(new Human("Mari", 23, false));
    }


    public void passTime() {
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
                    continue;
                }
                if (room.getHumans().get(i).getHungry() < 0) {
                    room.getHumans().get(i).kill(1);
                    room.getHumans().remove(room.getHumans().get(i));
                    i--;
                    continue;
                }
                if(room.getHumans().get(i).getFatigue() < 0) {
                    room.getHumans().get(i).kill(2);
                    room.getHumans().remove(room.getHumans().get(i));
                    i--;
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

    public static void main(String[] args) {
        Shelter shelter = new Shelter();
        Menu menu = new Menu(shelter);
    }
}
