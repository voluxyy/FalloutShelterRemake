import Shelter.*;
import Characters.Human;

import java.util.ArrayList;

public class Menu {
    private final Shelter shelter;

    public Menu(Shelter shelter) {
        this.shelter = shelter;

        this.shelter.rooms.get(0).addHuman(new Human("Christophe", 20, true));
        this.shelter.rooms.get(0).addHuman(new Human("Mari", 10, false));
        this.shelter.rooms.get(0).addHuman(new Human("Christelle", 20, false));
        this.shelter.rooms.get(0).addHuman(new Human("Francois", 15, true));
    }
    public void mainMenu() {
        int input = -1;
        while (input != 0) {
            System.out.println("\n**** THE CASTLE ****");
            System.out.println("(1) Build");
            System.out.println("(2) See the castle");
            System.out.println("(3) Show resources");
            System.out.println("(4) Pass time");
            System.out.println("(0) Exit");

            try {
                input = Integer.parseInt(this.shelter.scanner.nextLine());
            } catch(Exception ignored) {
                System.err.println("You can only use numbers");
            }

            switch (input) {
                case 1:
                    this.buildMenu();
                    break;
                case 2:
                    this.showRooms();
                    break;
                case 3:
                    this.showResources();
                    break;
                case 4:
                    this.shelter.passTime();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("* unknown input *");
                    break;
            }
        }
    }

    public void buildMenu() {
        int input = -1;

        while (input != 0) {
            System.out.println("\n**** BUILD MENU ****");
            System.out.println("(1) Cafeteria");
            System.out.println("(2) Dormitory");
            System.out.println("(3) Stone Factory");
            System.out.println("(4) Wood Factory");
            System.out.println("(5) Field");
            System.out.println("(6) Storage");
            System.out.println("(0) Back to menu");

            try {
                input = Integer.parseInt(this.shelter.scanner.nextLine());
            } catch(Exception ignored) {
                System.err.println("You can only use numbers");
            }

            switch (input) {
                case 1:
                    if (this.buildRoomMenu("Cafeteria", 200, 100)) {
                        this.shelter.rooms.add(new Cafeteria());
                        this.shelter.ressources.adjustWood(-200);
                        this.shelter.ressources.adjustStone(-100);
                    }
                    return;
                case 2:
                    if (this.buildRoomMenu("Dormitory", 350, 150)) {
                        this.shelter.rooms.add(new Dormitory());
                        this.shelter.ressources.adjustWood(-350);
                        this.shelter.ressources.adjustStone(-150);
                    }
                    return;
                case 3:
                    if (this.buildRoomMenu("Stone Factory", 400, 200)) {
                        this.shelter.rooms.add(new Dormitory());
                        this.shelter.ressources.adjustWood(-400);
                        this.shelter.ressources.adjustStone(-200);
                    }
                    return;
                case 4:
                    if (this.buildRoomMenu("Wood Factory", 400, 200)) {
                        this.shelter.rooms.add(new WoodFactory());
                        this.shelter.ressources.adjustWood(-400);
                        this.shelter.ressources.adjustStone(-200);
                    }
                    return;
                case 5:
                    if (this.buildRoomMenu("Field", 300, 150)) {
                        this.shelter.rooms.add(new Field());
                        this.shelter.ressources.adjustWood(-300);
                        this.shelter.ressources.adjustStone(-150);
                    }
                    return;
                case 6:
                    if (this.buildRoomMenu("Storage", 300, 100)) {
                        this.shelter.rooms.add(new Storage());
                        this.shelter.ressources.adjustWood(-300);
                        this.shelter.ressources.adjustStone(-100);
                    }
                    return;
                case 0:
                    break;
                default:
                    System.out.println("* unknown input *");
                    break;
            }
        }
    }

    public boolean buildRoomMenu(String name, int wood, int stone) {
        System.out.println("\nYou need to build a " + name + ":\n" + wood + " woods" +
                "\n" + stone + " stones");

        int input = -1;

        while (input != 0) {
            System.out.println("(1) Yes\n(0) No");

            try {
                input = Integer.parseInt(this.shelter.scanner.nextLine());
            } catch(Exception ignored) {
                System.err.println("You can only use numbers");
            }

            if (input == 1) {
                if (!this.shelter.verifyWood(wood)) {
                    System.err.println("You have not enough wood.");
                    return false;
                }
                if (!this.shelter.verifyStone(stone)) {
                    System.err.println("You have not enough stone.");
                    return false;
                }
                return true;
            } else if (input == 0) {
                return false;
            } else {
                System.out.println("* unknown input *");
            }
        }
        return false;
    }

    public void showResources() {
        System.out.println("\n**** RESOURCES ****");
        System.out.println("Wood: " + this.shelter.ressources.getWood());
        System.out.println("Stone: " + this.shelter.ressources.getStone());
        System.out.println("Food: " + this.shelter.ressources.getFood());
    }

    public void showRooms() {
        if (this.shelter.rooms == null) {
            System.out.println("You shelter is empty.");
            return;
        }
        int input = -1;
        while (input != 0) {
            int index = 1;
            System.out.println("\n**** LIST OF YOUR ROOMS ****");
            for (Room room : this.shelter.rooms) {
                System.out.println("(" + index + ") " + room.getClass().getSimpleName());
                index++;
            }
            System.out.println("(0) Back to menu");

            try {
                input = Integer.parseInt(this.shelter.scanner.nextLine());
            } catch(Exception ignored) {
                System.err.println("You can only use numbers");
            }

            switch (input) {
                case 0:
                    break;
                default:
                    Room room = null;

                    try {
                        room = this.shelter.rooms.get(input-1);
                        this.showRoomOptions(room);
                    } catch (Exception e) {
                        System.err.println("* unknown input *");
                    }
                    break;
            }
        }
    }

    public void showRoomOptions(Room room) {
        int input = -1;
        while (input != 0) {
            System.out.println("\n**** " + room.getClass().getSimpleName() + " ****");
            System.out.println("(1) Show humans");
            System.out.println("(0) Back to menu");

            try {
                input = Integer.parseInt(this.shelter.scanner.nextLine());
            } catch(Exception ignored) {
                System.err.println("You can only use numbers");
            }

            switch (input) {
                case 1:
                    this.showHumans(room);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("* unknown input *");
                    break;
            }
        }
    }

    public void showHumans(Room room) {
        int input = -1;
        while (input != 0) {
            if (room.getHumans() == null || room.getHumans().size() == 0) {
                System.out.println("You room is empty.");
                return;
            }
            int index = 1;
            System.out.println("\n**** List of humans in " + room.getClass().getSimpleName() + " ****");
            for (Human human : room.getHumans()) {
                System.out.println("(" + index + ") " + human.getName());
                index++;
            }
            System.out.println("(0) Back to menu");

            try {
                input = Integer.parseInt(this.shelter.scanner.nextLine());
            } catch(Exception ignored) {
                System.err.println("You can only use numbers");
            }

            if (input != 0) {
                Human human = null;
                try {
                    human = room.getHumans().get(input-1);
                } catch (Exception ignored) {
                    System.out.println("* unknown input *");
                }
                if (human != null) {
                    this.showHumanOption(human, room);
                }
            }
        }
    }

    public void showHumanOption(Human human, Room room) {
        int input = -1;
        while (input != 0) {
            System.out.println("\n**** " + human.getName() + " ****");
            System.out.println("(1) Move");
            System.out.println("(2) Crac-crac");
            System.out.println("(3) Information");
            System.out.println("(0) Back to menu");

            try {
                input = Integer.parseInt(this.shelter.scanner.nextLine());
            } catch(Exception ignored) {
                System.err.println("You can only use numbers");
            }

            switch (input) {
                case 1:
                    if (this.moveHuman(human)) {
                        room.removeHuman(human);
                    }
                    return;
                case 2:
                    this.reproductionMenu(human, room);
                    return;
                case 3:
                    this.showInfoHuman(human);
                    return;
                case 0:
                    break;
                default:
                    System.out.println("* unknown input *");
                    break;
            }
        }
    }

    public boolean moveHuman(Human human) {
        int input = -1;
        while (input != 0) {
            int index = 1;
            System.out.println("\n**** MOVE OPTIONS ****");
            for (Room room : this.shelter.rooms) {
                System.out.println("(" + index + ") " + room.getClass().getSimpleName());
                index++;
            }
            System.out.println("(0) Back to menu");

            try {
                input = Integer.parseInt(this.shelter.scanner.nextLine());
            } catch(Exception ignored) {
                System.err.println("You can only use numbers");
            }

            switch (input) {
                case 0:
                    break;
                default:
                    try {
                        Room room = this.shelter.rooms.get(input-1);
                        if (room.getHumans().size() >= room.getMaxSize()) {
                            System.out.println("There is already 4 persons.");
                            return false;
                        }
                        room.addHuman(human);
                        return true;
                    } catch (Exception ignored) {
                        System.out.println("* unknown input *");
                    }
            }
        }
        return false;
    }

    public void reproductionMenu(Human human, Room room) {
        if (human.getAge() < 18) {
            System.err.println(human.getName() + " is too young.");
            return;
        }
        if (!(room instanceof Dormitory)) {
            System.err.println("You can only make a child in a dormitory.");
            return;
        }
        if (room.getHumans().size() >= room.getMaxSize()) {
            System.out.println("There is already 4 persons.");
            return;
        }
        int input = - 1;
        while (input != 0) {
            int index = 1;
            ArrayList<Human> humans = (human.getIsMale() ? room.getFemale() : room.getMale());
            for (Human human2 : humans) {
                System.out.println("(" + index + ") " + human2.getName());
                index++;
            }
            System.out.println("(0) Back to menu");

            try {
                input = Integer.parseInt(this.shelter.scanner.nextLine());
            } catch (Exception ignored) {
                System.err.println("You can only use numbers");
            }

            switch (input) {
                case 0:
                    break;
                default:
                    Human human2 = humans.get(input-1);
                    if (human2.getAge() < 18) {
                        System.err.println(human2.getName() + " is too young.");
                        return;
                    }
                    room.addHuman(this.childMenu(human, human2));
                    return;
            }
        }
    }

    public Human childMenu(Human human1, Human human2) {
        System.out.println("\n**** Congratulations !! ****");
        System.out.println(human1.getName() + " and " + human2.getName() + " just make a child !");
        System.out.println("What name do you want ?");

        String name = this.shelter.scanner.nextLine();

        return human1.makeAChild(name);
    }

    public void showInfoHuman(Human human) {
        System.out.println("\n**** Info about " + human.getName() + " ****");
        System.out.println("Age: " + human.getAge());
        System.out.println("Fatigue (%): " + human.getFatigue() + "/100");
        System.out.println("Hunger (%): " + human.getHungry() + "/100");
        System.out.println("Sex: " + (human.getIsMale() ? "Male" : "Female"));
    }
}
