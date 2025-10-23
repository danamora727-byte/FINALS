class Warrior extends Character {
    boolean bloodthirstActive = false;
    int shieldTurns = 0;

    public Warrior() {
        super("Warrior", 120, 30, 100);
    }

    @Override
    public void Skill_1(Character target) {
        if (sp < 30) {
            System.out.println("Not enough SP!");
            return;
        }
        sp -= 30;
        bloodthirstActive = true;
        System.out.println("Warrior chooses to activate Bloodthirst! Next attack will heal for 50% of damage.");
    }

    @Override
    public void Skill_2(Character target) {
        if (sp < 50) {
            System.out.println("Not enough SP!");
            return;
        }
        sp -= 50;
        shieldTurns = 2;
        System.out.println("Warrior decides to use Shield Block! Takes 40% less damage for 2 turns.");
    }

    @Override
    public void attack(Character target) {
        if (sp < 2) {
            System.out.println(name + " is too tired to attack!");
            return;
        }
        sp -= 2;
        int dmg = rand.nextInt(10) + 15;
        if (target.defending) dmg *= 0.5;
        target.hp -= dmg;
        System.out.println(name + " attacks " + target.name + " for " + dmg + " damage!");
        if (bloodthirstActive) {
            int heal = dmg / 2;
            hp = Math.min(maxHP, hp + heal);
            System.out.println("Warrior heals for " + heal + " HP from Bloodthirst!");
            bloodthirstActive = false;
        }
    }
}
