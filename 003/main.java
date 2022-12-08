import pkg.*;
public class main {

    public static void main (String[] args) {
        System.out.println(checkPal(1221));
    }

    public static boolean checkPal(int num) {
       if (flipNumber(num) == num) {
        return true;
       } else
       return false;
    }

    public static int flipNumber(int theNum) {
        int newNum = 0;
        while (theNum >0) {
            newNum = newNum *10+theNum%10;
            theNum /=10;
        }
        return newNum;
    }
}