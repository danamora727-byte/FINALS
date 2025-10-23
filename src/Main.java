import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        System.out.println("=== TEXT FIGHTER ===");
        System.out.println("Choose your class:");
        System.out.println("1. Warrior");
        System.out.println("2. Mage");
        int choice = sc.nextInt();

        Character player = (choice == 1) ? new Warrior() : new Mage();
        int score = 0;
        int difficulty = 1;

        while (player.isAlive()) {
            System.out.println("\nA new enemy approaches!");
            Character enemy = (choice == 1) ? new Mage() : new Warrior();
            enemy.maxHP += difficulty * 10;
            enemy.hp = enemy.maxHP;

            while (player.isAlive() && enemy.isAlive()) {
                System.out.println("\n-- Player Turn --");
                System.out.println("HP: " + player.hp + "/" + player.maxHP +
                        " | MP: " + player.mp + "/" + player.maxMP +
                        " | SP: " + player.sp + "/" + player.maxSP);
                System.out.println("1. Attack");
                System.out.println("2. Defend");
                System.out.println("3. Skill 1");
                System.out.println("4. Skill 2");
                System.out.println("5. Use Item");
                System.out.print("Choose");
                int action = sc.nextInt();

                switch (action) {
                    case 1 -> player.attack(enemy);
                    case 2 -> player.defend();
                    case 3 -> player.Skill_1(enemy);
                    case 4 -> player.Skill_2(enemy);
                    case 5 -> player.useItem();
                    default -> System.out.println("Invalid choice.");
                }

                if (!enemy.isAlive()) {
                    System.out.println(enemy.name + " was defeated!");
                    score++;

                    if (rand.nextBoolean()) player.healPotions++;
                    else player.manaPotions++;
                    difficulty++;
                    break;
                }


                System.out.println("\n-- Enemy Turn --");
                int enemyAction = rand.nextInt(3);
                if (enemyAction == 0) enemy.attack(player);
                else if (enemyAction == 1) enemy.defend();
                else enemy.Skill_1(player);

                if (!player.isAlive()) System.out.println("\nYou were defeated!");
            }
        }

        System.out.println("\nGame Over! Final Score: " + score);
    }
}