package com.company;

import java.util.Random;

public class Main {
    public static int[] heroesHealth = new int[]{270, 280, 250, 300};
    public static int[] heroesDamage = new int[]{20, 15, 25, 0};
    public static String[] heroesAttackType = new String[]{"Physical", "Magical", "Kinetic, Medical"};
    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefenceType = "";
    public static int roundNumber = 0;

    public Main() {
    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length);
        bossDefenceType = heroesAttackType[randomIndex];
        System.out.println("Boss chose: " + bossDefenceType);
    }

    public static int health() {
        Random hpRandom = new Random();
        return hpRandom.nextInt(100) + 1;
    }

    public static void medicAbility() {
        int indexMedic = 0;
        String[] var1 = heroesAttackType;
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            String name = var1[var3];
            if (name == "Medical") {
                if (heroesHealth[indexMedic] > 0) {
                    for(int i = 0; i < heroesHealth.length; ++i) {
                        if (heroesHealth[i] < 100 && heroesHealth[i] > 0 && indexMedic != i) {
                            heroesHealth[i] = heroesHealth[i] + health() + 50;
                            String var10001 = heroesAttackType[i];
                            System.out.println("был вылечен:" + var10001);
                            break;
                        }
                    }
                }
            } else {
                ++indexMedic;
            }
        }

    }

    public static void main(String[] args) {
        printStatistics();

        while(!isGameFinished()) {
            round();
        }

    }

    public static void round() {
        ++roundNumber;
        chooseBossDefence();
        bossHits();
        heroesHit();
        printStatistics();
        medicAbility();
    }

    public static void bossHits() {
        for(int i = 0; i < heroesHealth.length; ++i) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] < bossDamage) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] -= bossDamage;
                }
            }
        }

    }

    public static void heroesHit() {
        for(int i = 0; i < heroesDamage.length; ++i) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (bossDefenceType == heroesAttackType[i]) {
                    Random random = new Random();
                    int coeff = random.nextInt(8) + 2;
                    if (bossHealth < heroesDamage[i] * coeff) {
                        bossHealth = 0;
                    } else {
                        bossHealth -= heroesDamage[i] * coeff;
                    }

                    System.out.println("Critical damage: " + heroesDamage[i] * coeff);
                } else if (bossHealth < heroesDamage[i]) {
                    bossHealth = 0;
                } else {
                    bossHealth -= heroesDamage[i];
                }
            }
        }

    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        } else {
            boolean allHeroesDead = true;
            int[] var1 = heroesHealth;
            int var2 = var1.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                int j = var1[var3];
                if (j > 0) {
                    allHeroesDead = false;
                    break;
                }
            }

            if (allHeroesDead) {
                System.out.println("Boss won!!!");
            }

            return allHeroesDead;
        }
    }

    public static void printStatistics() {
        System.out.println(roundNumber + " ROUND ----------------------");
        System.out.println("Boss health: " + bossHealth + " [" + bossDamage + "]");

        for(int i = 0; i < heroesHealth.length; ++i) {
            String var10001 = heroesAttackType[i];
            System.out.println(var10001 + " health: " + heroesHealth[i] + " [" + heroesDamage[i] + "]");
        }

        System.out.println();
    }
}
