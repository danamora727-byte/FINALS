class Mage extends Character {
    boolean fireballReady = false;
    int iceBlockTurns = 0;

    public Mage() {
        super("Mage", 80, 120, 50);
    }

    @Override
    public void Skill_1(Character target) {
        if (mp < 25) {
            System.out.println("Not enough MP!");
            return;
        }
        mp -= 25;
        fireballReady = true;
        System.out.println("Mage prepares a Fireball! Next attack will burn the enemy.");
    }

    @Override
    public void Skill_2(Character target) {
        if (mp < 60) {
            System.out.println("Not enough MP!");
            return;
        }
        mp -= 60;
        iceBlockTurns = 2;
        System.out.println("Mage uses Ice Block! Immune to all damage for 2 turns.");
    }

    @Override
    public void attack(Character target) {
        if (sp < 2) {
            System.out.println(name + " is too tired to attack!");
            return;
        }
        sp -= 2;
        int dmg = rand.nextInt(15) + 10;
        if (fireballReady) {
            dmg += 10;
            System.out.println("Fireball hits! Extra 10 damage and applies burn.");
            target.hp -= 5; // burn damage
            fireballReady = false;
        }
        if (target.defending) dmg *= 0.5;
        target.hp -= dmg;
        System.out.println(name + " casts a magic attack for " + dmg + " damage!");
    }
}
