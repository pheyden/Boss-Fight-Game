import java.util.Scanner;

public class project {

    public static void main(String[] args) {

        // Title screen.
        System.out.println("\n----------------------------------------------------------------------------------------------------------------------------");
        System.out.println("\n ________      ________      ________       ________           ________    ___      ________      ___  ___      _________   ");
        System.out.println("|\\   __  \\    |\\   __  \\    |\\   ____\\     |\\   ____\\         |\\  _____\\  |\\  \\    |\\   ____\\    |\\  \\|\\  \\    |\\___   ___\\ ");
        System.out.println("\\ \\  \\|\\ /_   \\ \\  \\|\\  \\   \\ \\  \\___|_    \\ \\  \\___|_        \\ \\  \\__/   \\ \\  \\   \\ \\  \\___|    \\ \\  \\\\\\  \\   \\|___ \\  \\_| ");
        System.out.println(" \\ \\   __  \\   \\ \\  \\\\\\  \\   \\ \\_____  \\    \\ \\_____  \\        \\ \\   __\\   \\ \\  \\   \\ \\  \\  ___   \\ \\   __  \\       \\ \\  \\  ");
        System.out.println("  \\ \\  \\|\\  \\   \\ \\  \\\\\\  \\   \\|____|\\  \\    \\|____|\\  \\        \\ \\  \\_|    \\ \\  \\   \\ \\  \\|\\  \\   \\ \\  \\ \\  \\       \\ \\  \\ ");
        System.out.println("   \\ \\_______\\   \\ \\_______\\    ____\\_\\  \\     ____\\_\\  \\        \\ \\__\\      \\ \\__\\   \\ \\_______\\   \\ \\__\\ \\__\\       \\ \\__\\");
        System.out.println("    \\|_______|    \\|_______|   |\\_________\\   |\\_________\\        \\|__|       \\|__|    \\|_______|    \\|__|\\|__|        \\|__|");
        System.out.println("                               \\|_________|   \\|_________|                                                                ");
        System.out.println("\n-----------------------------------------------------------------------------------------------------------------------------");

        Scanner keyboard = new Scanner(System.in);
        boolean playerReady = false;

        // Starting the game.
        while (!playerReady) {
            System.out.println("Ready to play? (Type Y to begin.)");
            String playerStart = keyboard.next().trim().toLowerCase();
            switch (playerStart) {
                case "y":
                    playerReady = true;
                    break;
                default:
                    System.out.println("Input not accepted.\n");
            }
        }

        // Playing the game.
        int playerHealth = 100;
        int bossHealth = 100;
        boolean shieldActive = false;
        boolean bossCharged = false;
        System.out.println("\nThe BOSS appears!");

        while (playerReady) {
            System.out.println("What will you do?\n");
            if (playerHealth > 0) {
                System.out.println("BOSS Health: " + bossHealth);
                System.out.println("Your Health: " + playerHealth + "\n");
                switch (playerTurn()) {
                    case 1:
                        System.out.println("\nYou swing your sword with all your might!\n");
                        bossHealth = (bossHealth - playerDamage());
                        break;
                    case 2:
                        System.out.println("\nYou ready your shield...\n");
                        shieldActive = true;
                        break;
                    case 3:
                        System.out.println("\nYou drink a potion.\n");
                        playerHealth = (playerHealth + drinkPotion());
                        break;
                    case 4:
                        System.out.println("The BOSS is stunned by your kindness... You become best friends!\n\nGAME OVER");
                        playerReady = false;
                }
                if (bossHealth > 0 && playerReady == true) {
                    if (bossCharged) {
                        if (shieldActive) {
                            System.out.println("The BOSS does a heavy attack! You block it with your shield!\n");
                            shieldActive = false;
                        }
                        else {
                            playerHealth = (playerHealth - bossHeavyDamage());
                            System.out.println("The BOSS does a heavy attack!\n");
                        }
                        bossCharged = false;
                    }
                    else {
                        switch (bossTurn()) {
                            case 1:
                                if (shieldActive){
                                    System.out.println("The BOSS slashes at you with his axe! You block it with your shield!\n");
                                    shieldActive = false;
                                }
                                else {
                                    System.out.println("The BOSS slashes at you with his axe!\n");
                                    playerHealth = (playerHealth - bossNormalDamage());
                                }
                                    break;
                            case 2:
                                bossCharged = true;
                                System.out.println("The BOSS charges up an attack....\n");
                                shieldActive = false;
                        }
                    }
                }

                // Win/loss conditions.
                if (bossHealth <= 0) {
                    playerReady = false;
                    System.out.println("\nThe BOSS takes a step back...\n\nHe wobbles and falls to the stone floor with a loud CRASH.");
                    System.out.println("\nYou won!\nCONGRATULATIONS");
                }
                else if (playerHealth <= 0) {
                    playerReady = false;
                    System.out.println("\n\nOh no! You've been defeated!\nBOSS: HA HA HA! I WIN! GAME OVER!");
                }
                else {
                }
            }
        }
    }

    // Methods.
    public static int playerTurn() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println(" For Attack                   For Shield                       For Health Potion");
        System.out.println("Type: Attack                 Type: Shield                        Type: Potion\n");
        String playerMove = keyboard.next().trim().toLowerCase();
        int playerChoice = 0;
        switch (playerMove) {
            case "attack":
                playerChoice = 1;
                break;
            case "shield":
                playerChoice = 2;
                break;
            case "potion":
                playerChoice = 3;
                break;
            case "healboss":
                playerChoice = 4;
                break;
            default:
                System.out.println("That doesn't work!");
        }
        return playerChoice;
    }

    public static int playerDamage() {
        int attackDamage = (int) (Math.random() * 30) + 1;
        return attackDamage;
    }

    public static int drinkPotion() {
        int potionEffect = (int) (Math.random() * 15) + 1;
        return potionEffect;
    }

    public static int bossTurn() {
        int bossMove = (int) (Math.random() * 2) + 1;
        return bossMove;
    }

    public static int bossNormalDamage() {
        int bossNormalAttack = (int) (Math.random() * 15) + 9;
        return bossNormalAttack;
    }

    public static int bossHeavyDamage() {
        int bossHeavyAttack = (int) (Math.random() * 25) + 25;
        return bossHeavyAttack;
    }
}
