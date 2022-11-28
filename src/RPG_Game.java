import java.util.Random;

    public class RPG_Game {
        private static int roundNumber;
        public static Random random = new Random();

        public static void startGame() {
            Boss boss = new Boss(1500, 50, "Tanos");
            Warrior warrior = new Warrior(290, 10, "Thor");
            Medic doc = new Medic(250, 5, 15, "Aibolit");
            Magic magic = new Magic(280, 15, "Medivh");
            Berserk berserk = new Berserk(270, 10, "Spider Man");
            Medic assistant = new Medic(300, 5, 5, "Choper");

            Hero[] heroes = {warrior, doc, magic, berserk, assistant};

            printStatistics(boss, heroes);
            while (!isGameFinished(boss, heroes)) {
                playRound(boss, heroes);
            }
        }

        private static void playRound(Boss boss, Hero[] heroes) {
            roundNumber++;
            boss.chooseDefence(heroes);
            boss.hit(heroes);
            for (int i = 0; i < heroes.length; i++) {
                if (heroes[i].getHealth() > 0 && boss.getHealth() > 0
                        && boss.getDefence() != heroes[i].getAbility()) {
                    heroes[i].hit(boss);
                    heroes[i].applySuperPower(boss, heroes);
                }
            }
            printStatistics(boss, heroes);
        }

        private static void printStatistics(Boss boss, Hero[] heroes) {
            System.out.println("ROUND " + roundNumber + " ----------");
            System.out.println(boss);
            for (int i = 0; i < heroes.length; i++) {
                System.out.println(heroes[i]);
            }
        }

        private static boolean isGameFinished(Boss boss, Hero[] heroes) {
            if (boss.getHealth() <= 0) {
                System.out.println("Heroes won!!!");
                return true;
            }
            boolean allHeroesDead = true;
            for (int i = 0; i < heroes.length; i++) {
                if (heroes[i].getHealth() > 0) {
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


