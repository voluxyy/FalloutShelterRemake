package Shelter;

public class Ressources {
    protected int wood;
    protected int stone;
    protected int food;

    protected int maxWood;
    protected int maxStone;
    protected int maxFood;
    public Ressources() {
        this.maxWood = 10000;
        this.maxStone = 10000;
        this.maxFood = 10000;
    }

    public int getWood() {
        return this.wood;
    }
    public int getStone() {
        return this.stone;
    }
    public int getFood() {
        return this.food;
    }

    /**
     * This method is to add or remove wood from ressources.
     * @param nb number to add or remove
     */
    public void adjustWood(int nb) {
        this.wood += nb;
    }
    /**
     * This method is to add or remove stone from ressources.
     * @param nb number to add or remove
     */
    public void adjustStone(int nb) {
        this.stone += nb;
    }
    /**
     * This method is to add or remove food from ressources.
     * @param nb number to add or remove
     */
    public void adjustFood(int nb) {
        this.food += nb;
    }
}
