package towerofhanoi;
import java.util.Scanner;


public class towerofhanoi3 {

    static Scanner input = new Scanner(System.in);

    static int[][] towers = {{}, {6, 4, 2}, {0, 0, 0}, {0, 0, 0}};
    static int[][] level2 = {{} , {10,8,6,4,2},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}};
    
    public static void drawTowers() {
        System.out.println("\t\t\tTower of Hanoi Game");
        System.out.println("\t\t\t-------------------");
        System.out.println("\n\n");

        for (int i = 7; i >= 0; i--) {
            if (i < towers[1].length) {
                System.out.print("\t\t");
                for (int j = 1; j <= 3; j++) {
                    int nStars = towers[j][i];
                    if (nStars != 0) {
                        System.out.printf("%" + (7 - nStars ) + "s", " ");
                        for (int k = 0; k < nStars ; k++) {
                            System.out.print("*");
                        }
                        System.out.print("|");
                        for (int k = 0; k < nStars ; k++) {
                            System.out.print("*");
                        }
                        System.out.printf("%" + (7 - nStars ) + "s", " ");
                        System.out.print("\t");
                    } else {
                        System.out.print("       |\t");
                    }
                }

                System.out.println();
            } else {
                System.out.printf("\t\t       |\t       |\t       |\n", '|', '|', '|');
            }
        }

        System.out.println("\t\t---------------\t---------------\t--------------");
        System.out.println("\t\t     (1)      \t      (2)     \t      (3)     ");

    }
    
    public static void drawTowerslvl2() {
        System.out.println("\t\t\tTower of Hanoi Game");
        System.out.println("\t\t\t-------------------");
        System.out.println("\n\n");

        for (int i = 10; i >= 0; i--) {
            if (i < level2[1].length) {
                System.out.print("       \t        ");
                for (int j = 1; j <= 5; j++) {
                    int nStars = level2[j][i];
                    if (nStars != 0) {
                        System.out.printf("%" + (14 - nStars ) + "s", " ");
                        for (int k = 0; k < nStars ; k++) {
                            System.out.print("*");
                        }
                        System.out.print("|");
                        for (int k = 0; k < nStars ; k++) {
                            System.out.print("*");
                        }
                        System.out.printf("%" + (14 - nStars ) + "s", " ");
                        System.out.print("\t        ");
                    } else {
                        System.out.print("            |\t\t                ");
                    }
                }

                System.out.println();
            } else {
                System.out.printf("\t\t              |\t                                    |\t                                    |\t                                    |\t                                    |\n", '|', '|', '|','|','|');
            }
        }

        System.out.println("\t\t-------------------------\t\t-------------------------\t\t------------------------\t\t-------------------------\t\t-------------------------");
        System.out.println("\t\t             (1)    \t\t                   (2)     \t\t                   (3)\t\t                           (4)\t\t                           (5)     ");

    }

    public static int readBarFrom() {
        System.out.print("From Bar:");
        int X = input.nextInt();
        return X;

    }

    public static int readBarTo() {
        System.out.print("To Bar :");
        int Y = input.nextInt();
        return Y;
    }

    public boolean isValidBarNumber(int barNumber) {
        return (barNumber == 1 || barNumber == 2 || barNumber == 3);

    }
    
    public boolean isValidBarNumberlvl2(int barNumber) {
        return (barNumber == 1 || barNumber == 2 || barNumber == 3 || barNumber == 4 || barNumber == 5);

    }

    public static int getTopCylinderIndex(int barNumber) {
        int index = 0, count = 0;
        for (int i = 0; i < 3; i++) {
            if (towers[barNumber][i] == 0) {
                count++;
            }
        }

        if (count == 2 || count == 3) {
            return 0;
        }
        if (count == 1) {
            return 1;
        }
        if (count == 0) {
            return 2;
        }

        return 0;
    }
    
    public static int getTopCylinderIndexlvl2(int barNumber) {
        int index = 0, count = 0;
        for (int i = 0; i < 5; i++) {
            if (level2[barNumber][i] == 0) {
                count++;
            }
        }
        if (count == 4 || count == 5){
            return 0;
        }
        if (count == 3 ) {
            return 1;
        }
        if (count == 2){
            return 2;
        }
        if (count == 1) {
            return 3;
        }
        if (count == 0) {
            return 4;
        }

        return 0;
    }

    public static boolean isLegalMove(int From, int To) {
        if (From >3 || To >3)
            return false ;
        if (From == To) {
            return false;
        }else if(towers[To][getTopCylinderIndex(To)]==0){
            return true;
    }
        else {
            return (towers[To][getTopCylinderIndex(To)] >towers[From][getTopCylinderIndex(From)]);
        }
    }
    
    public static boolean isLegalMovelvl2(int From, int To) {
        if (From >5 || To >5)
            return false ;
        if (From == To) {
            return false;
        }else if(level2[To][getTopCylinderIndexlvl2(To)]==0){
            return true;
    }
        else {
            return (level2[To][getTopCylinderIndexlvl2(To)] >level2[From][getTopCylinderIndexlvl2(From)]);
        }
    }
    

    public static void moveCylinder(int From, int To) {
        if (towers[To][getTopCylinderIndex(To)] == 0) {
            towers[To][getTopCylinderIndex(To)] = towers[From][getTopCylinderIndex(From)];
        } else {
            towers[To][getTopCylinderIndex(To) + 1] = towers[From][getTopCylinderIndex(From)];
        }
        towers[From][getTopCylinderIndex(From)] = 0;
        
        
    }
    
    public static void moveCylinderlvl2(int From, int To) {
        if (level2[To][getTopCylinderIndexlvl2(To)] == 0) {
            level2[To][getTopCylinderIndexlvl2(To)] = level2[From][getTopCylinderIndexlvl2(From)];
        } else {
            level2[To][getTopCylinderIndexlvl2(To) + 1] = level2[From][getTopCylinderIndexlvl2(From)];
        }
        level2[From][getTopCylinderIndexlvl2(From)] = 0;
    }

    public static boolean isWinner() {
        for (int i = 0; i < 3; i++) {
            if (towers[3][i] == 0) {
                return false;
            }

        }
        return true;
    }
    public static boolean isWinnerlvl2() {
        for (int i = 0; i < 5; i++) {
            if (level2[5][i] == 0) {
                return false;
            }

        }
        return true;
    }

    public static void clearScreen() throws Exception {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                
    }

    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner (System.in);
        System.out.println("choose The Difficulty");
        System.out.println("For Esy Mode Enter -> 1 ");
        System.out.println("For Hard Mode Enter -> 2 ");
        int x = s.nextInt();
        if (x==1){
            clearScreen();
            System.out.println("you have only 10 step to End the game can you do it ?");
            while (true) {
                for(int n=1 ; n <=12 ; n++){
                    
                    if (n == 11 ){
                        System.out.println("\t\tyou are lose");
                        System.exit(0);
                    }
                       
                clearScreen();
                drawTowers();
                System.out.println("step " + n);
                int From = readBarFrom();
                int To = readBarTo();
                if (isLegalMove(From, To)) {
                    moveCylinder(From, To);
                    
                    if (isWinner()) {
                        clearScreen();
                        drawTowers();
                        System.out.println("\n\n\n\t\t\tCongratulations");
                        System.exit(0);
                    }
                } else {
                    System.out.println("\n\n\n\t\t\tillegal cylinder move");
                    n--;
                    System.in.read();
                    
                }
            }
            
        }
        }else if (x==2){
            clearScreen();
            System.out.println("you have only 12 step to End the game can you do it ?");
            while (true) {
                for(int n=1 ; n <=14 ; n++){
                    if (n == 13 ){
                        System.out.println("\t\t\t\tyou are lose");
                        System.exit(0);
                    }
            
                clearScreen();
                drawTowerslvl2();
                System.out.println("step " + n);
                int From = readBarFrom();
                int To = readBarTo();
                if (isLegalMovelvl2(From, To)) {
                    moveCylinderlvl2(From, To);
                if (isWinnerlvl2()) {
                    clearScreen();
                    drawTowerslvl2();
                    System.out.println("\n\n\n\t\t\t\t\t\t\t\t\tCongratulations");
                    System.exit(0);
                }
                } else {
                    System.out.println("\n\n\n\t\t\t\t\t\t\t\t\tillegal cylinder move"); 
                    n--;
                    System.in.read();
                }
                }
            }
        }
    }
}