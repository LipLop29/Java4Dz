import java.util.Random;

public class Main {
    public static int bossHealth = 900;
    public static int bossDamage = 100;
    public static String heroesAttackType[] = {"Mechnik", "Mag", "Telepat", "Medic", "Golem"};
    public static int heroesHealth[] = {250, 200, 220, 200, 500};
    public static int heroesDamage[] = {25, 20, 20, 0, 10};
    public static String bossDefenceType;
    public static int rounds = 0;

    public static void main(String[] args) {
        printStatistic();
//        round();
        while (!isGameOver()) {
            round();
        }
    }

    public static void printStatistic() {
        System.out.println("****************");
        System.out.println("boss health : " + bossHealth +
                "; damage: " + bossDamage);
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println("Heroes health: " + heroesAttackType[i] + " " + heroesHealth[i] +
                    "; Damage: " + heroesDamage[i]);

        }
//        System.out.println("heroes health: " + heroesHealth[0] +
//                "; damage:" + heroesDamage[0]);
//        System.out.println("heroes health: " + heroesHealth[1] +
//                "; damage: "+ heroesDamage[1]);
//        System.out.println("heroes health: " + heroesHealth[2] +
//                "; damage:" + heroesDamage[2]);
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] < bossDamage) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void heroesHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (bossHealth > 0) {
                if (bossHealth < heroesDamage[i]) {
                    bossHealth = 0;
                } else {
                    bossHealth = bossHealth - heroesDamage[i];
                }
            }
        }
    }

    public static void round() {
        rounds++;
        System.out.println("ROUND: " + rounds);
        bossHits();
        setBossDefence();
        heroesHits();
        medic();
        golem();
        printStatistic();

    }

    public static boolean isGameOver() {
        if (bossHealth <= 0) {
            System.out.println("heroes win");
            return true;
        }
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }

        }
        if (allHeroesDead) {
            System.out.println("boss win");

        }
        return allHeroesDead;
    }

    public static void setBossDefence() {
        Random random = new Random();

        int rand = random.nextInt(heroesAttackType.length);
        bossDefenceType = heroesAttackType[rand];
        System.out.println("boss choose" + bossDefenceType);

        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesAttackType[i] == bossDefenceType) {

            }
        }
    }

    public static void medic() {
        for (int i = 0; i < heroesAttackType.length; i++) {
            if (heroesAttackType[i] == "Medic") {
                for (int j = 0; j < heroesAttackType.length; j++) {
                    if (heroesHealth[i] > 0 && heroesHealth[j] < 100 && heroesHealth[j] > 0
                            && heroesAttackType[j] != heroesAttackType[i]) {
                        heroesHealth[j] = heroesHealth[j] + 40;
                        System.out.println("medic исцелил " + heroesAttackType[j]);
                        break;
                    }
                }
            }
        }
    }

    public static void golem() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[4] > 0) {
                heroesHealth[4] -= bossDamage * 1 / 5;
                heroesHealth[i] += bossDamage * 1 / 5;
                System.out.println("Golem get " + bossDamage);
                break;
            }
        }
    }
}