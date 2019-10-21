import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int[][] arr = new int[2][2];
        String plainText = "";
        ProcessType processType = null;
        try {
            System.out.println("Please Have a Choice...");
            System.out.println("Type 1 for Hill Encryption, 2 for Hill Decryption");
            Scanner choiceScanner = new Scanner(System.in);
            if(choiceScanner.nextInt() == 1){
                processType = ProcessType.ENCRYPTION;
            }
            else{
                processType = ProcessType.DECRYPTION;
            }
            System.out.println("Please give a 2x2 matriks to encrypt the words.");
            System.out.println("Matriks' 0x0 Element: ");
            Scanner scan = new Scanner(System.in);
            int zeroOfZero = scan.nextInt();
            arr[0][0] = zeroOfZero;
            System.out.println("Matriks' 0x1 Element: ");
            int zeroOfOne = scan.nextInt();
            arr[0][1] = zeroOfOne;
            System.out.println("Matriks' 1x0 Element: ");
            int oneOfZero = scan.nextInt();
            arr[1][0] = oneOfZero;
            System.out.println("Matriks' 1x1 Element: ");
            int oneOfOne = scan.nextInt();
            arr[1][1] = oneOfOne;
            if(processType == ProcessType.ENCRYPTION) {
                System.out.println("Give Plain Text to Encrypt: ");
            } else{
                System.out.println("Give Plain Text to Decrypt");
            }
            Scanner scanner = new Scanner(System.in);
            plainText = scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Please give inputs right way.");
        }
        if(ProcessType.ENCRYPTION.equals(processType)) {
            String encryptedMessage = HillCipher.encryptHill(arr, plainText);
            System.out.println("Encrypted Text: " + encryptedMessage);
        }
        else{
            String decryptedMessage = HillCipher.decryptHill(arr, plainText);
            System.out.println("Decrypted Text: " + decryptedMessage);
        }
    }


}
