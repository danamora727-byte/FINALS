import java.util.*;
abstract class Character {
    String name;
    int maxHP, hp, maxMP, mp, maxSP, sp;
    int healPotions = 0, manaPotions = 0;
    boolean defending = false;

    Random rand = new Random();

    public Character(String name, int hp, int mp, int sp) {
        this.name = name;
        this.maxHP = hp;
        this.hp = hp;
        this.maxMP = mp;
        this.mp = mp;
        this.maxSP = sp;
        this.sp = sp;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public void attack(Character target) {
        if (sp < 2) {
            System.out.println(name + " is too tired to attack!");
            return;
        }
        sp -= 2;
        int dmg = rand.nextInt(10) + 10;
        if (target.defending) {
            dmg *= 0.5;
            target.defending = false;
        }
        target.hp -= dmg;
        System.out.println(name + " attacks " + target.name + " for " + dmg + " damage!");
    }

    public void defend() {
        if (sp < 3) {
            System.out.println(name + " doesn’t have enough SP to defend!");
            return;
        }
        sp -= 3;
        defending = true;
        System.out.println(name + " defends and will take reduced damage next turn!");
    }

    public void useItem() {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Healing Potion (" + healPotions + ")");
        System.out.println("2. Mana Potion (" + manaPotions + ")");
        System.out.print("Choose item: ");
        int choice = sc.nextInt();
        if (choice == 1 && healPotions > 0) {
            int healAmt = rand.nextInt(20) + 30;
            hp = Math.min(maxHP, hp + healAmt);
            healPotions--;
            System.out.println(name + " used a Healing Potion and restored " + healAmt + " HP!");
        } else if (choice == 2 && manaPotions > 0) {
            int manaAmt = rand.nextInt(20) + 30;
            mp = Math.min(maxMP, mp + manaAmt);
            manaPotions--;
            System.out.println(name + " used a Mana Potion and restored " + manaAmt + " MP!");
        } else {
            System.out.println("No such potion or out of stock!");
        }
    }

    public abstract void Skill_1(Character enemy);
    public abstract void Skill_2(Character enemy);
}