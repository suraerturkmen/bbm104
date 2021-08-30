public class Character {
    private int hp;
    private final String name;
    private final int constant_hp;

    public Character(String name, int constant_hp) {
        this.name = name;
        this.constant_hp = constant_hp;
    }

    public int getConstant_hp() {
        return constant_hp;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public String getName() {
        return name;
    }
}
