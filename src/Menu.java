import Shelter.*;
import Characters.Human;

public class Menu {
    private Shelter shelter;

    public Menu(Shelter shelter) {
        this.shelter = shelter;

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
                // TODO: Ajouter les pièces dans la liste de pièces
                case 1:
                    if (this.buildRoomMenu("Cafeteria", 0, 0)) {
                        this.shelter.rooms.add(new Cafeteria());
                    }
                    return;
                case 2:
                    if (this.buildRoomMenu("Dormitory", 0, 0)) {
                        this.shelter.rooms.add(new Dormitory());
                    }
                    return;
                case 3:
                    if (this.buildRoomMenu("Stone Factory", 0, 0)) {
                        this.shelter.rooms.add(new Dormitory());
                    }
                    return;
                case 4:
                    if (this.buildRoomMenu("Wood Factory", 0, 0)) {
                        this.shelter.rooms.add(new WoodFactory());
                    }
                    return;
                case 5:
                    if (this.buildRoomMenu("Field", 0, 0)) {
                        this.shelter.rooms.add(new Field());
                    }
                    return;
                case 6:
                    if (this.buildRoomMenu("Storage", 0, 0)) {
                        this.shelter.rooms.add(new Storage());
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
        System.out.println("Stone: " + this.shelter.ressources.getStone());
        System.out.println("Wood: " + this.shelter.ressources.getWood());
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
                    input--;
                    Room room = null;

                    try {
                        room = this.shelter.rooms.get(input);
                        this.showRoomOptions(room);
                    } catch (Exception ignored) {
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
        if (room.getHumans() == null || room.getHumans().size() == 0) {
            System.out.println("You room is empty.");
            return;
        }
        int input = -1;
        while (input != 0) {
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
                input--;
                Human human = null;
                try {
                    human = room.getHumans().get(input);
                } catch (Exception ignored) {
                    System.out.println("* unknown input *");
                }
                if (human != null) {
                    this.showHumanOption(human, room);
                }
                break;
            }
        }
    }

    public void showHumanOption(Human human, Room room) {
        int input = -1;
        while (input != 0) {
            System.out.println("\n**** " + human.getName() + " ****");
            System.out.println("(1) Move");
            System.out.println("(2) Crac-crac");
            System.out.println("(3) Rename (IN DEV)");
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
                    break;
                case 3:
                    //this.renameHuman(human);
                    // TODO: rename option for human
                    return;
                case 0:
                    break;
                default:

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
                    input--;
                    try {
                        this.shelter.rooms.get(input).addHuman(human);
                        return true;
                    } catch (Exception ignored) {
                        System.out.println("* unknown input *");
                    }
            }
        }
        return false;
    }

    public void reproductionMenu(Human human, Room room) {
        if (!(room instanceof Dormitory)) {
            System.err.println("You can only make a child in a dormitory.");
            return;
        }
        int input = - 1;
        while (input != 0) {
            int index = 0;
            for (Human human2 : room.getHumans()) {
                System.out.println("(" + index + ") " + human2.getName());
                index++;
            }
        }
    }
}
