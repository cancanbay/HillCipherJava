import java.util.Scanner;

public class Main {

    public static String alphabet = "ABCÇDEFGĞHIİJKLMNOÖPRSŞTUÜVYZ";
    public static String english_Alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static String word = "HELP";

    public static void main(String[] args) {
        int[][] arr = new int[2][2];
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
        System.out.println("Plain Text: "+ "HIAT");
        String encryptedMessage = decryptHill(arr, "HIAT");
        System.out.println("Decrypted Text: "+ encryptedMessage);
    }

    public static String encryptHill(int[][] matriks, String wordToByEncrypted) {
        boolean isSingleNumber = false;
        if (wordToByEncrypted.length() % 2 == 1) {
            isSingleNumber = true;
            wordToByEncrypted = wordToByEncrypted.concat("A");
        }
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < wordToByEncrypted.length() - 1; i += 2) {
            char firstChar = wordToByEncrypted.charAt(i);
            char secondChar = wordToByEncrypted.charAt(i + 1);
            int indexOfFirst = english_Alphabet.indexOf(firstChar);
            int indexOfSecond = english_Alphabet.indexOf(secondChar);
            int firstEncrpyted = (indexOfFirst * matriks[0][0] + indexOfSecond * matriks[0][1]);
            int secondEncrypted = (indexOfFirst * matriks[1][0] + indexOfSecond * matriks[1][1]);
            int firstModulo = firstEncrpyted % 26;
            int secondModulo = secondEncrypted % 26;
            String firstEncrpytedString = String.valueOf(english_Alphabet.charAt(firstModulo));
            String secondEncrpytedString = String.valueOf(english_Alphabet.charAt(secondModulo));
            output.append(firstEncrpytedString);
            output.append(secondEncrpytedString);
        }

        if (isSingleNumber){
            return output.toString().substring(0,output.toString().length()-1);
        }
            return output.toString();
    }

    public static String decryptHill(int[][] matriks, String wordToBeDecrypted) {
        int det = (matriks[0][0] * matriks[1][1]) - (matriks[0][1]* matriks[1][0]);
        int num = findInverseOfModulo26(det);
        if(num == -1){
            return "This Matriks Cannot be the key of decryption";
        }
        StringBuilder output = new StringBuilder();
        for(int i=0;i<wordToBeDecrypted.length();i+=2){
            char firstChar = wordToBeDecrypted.charAt(i);
            char secondChar = wordToBeDecrypted.charAt(i+1);
            int indexOfFirst = english_Alphabet.indexOf(firstChar);
            int indexOfSecond = english_Alphabet.indexOf(secondChar);
            int determinant = (matriks[0][0] * matriks[1][1]) - (matriks[0][1]* matriks[1][0]);
            int inverseOfDet = findInverseOfModulo26(determinant);
            int[][] adjacentMatriks =  findAdjacentOfMatriks(matriks);
            adjacentMatriks[0][1] += 26;
            adjacentMatriks[1][0] += 26;
            adjacentMatriks[0][0] = (adjacentMatriks[0][0] * inverseOfDet) % 26;
            adjacentMatriks[0][1] = (adjacentMatriks[0][1] * inverseOfDet) % 26;
            adjacentMatriks[1][0] = (adjacentMatriks[1][0] * inverseOfDet) % 26;
            adjacentMatriks[1][1] = (adjacentMatriks[1][1] * inverseOfDet) % 26;
            int firstValue = (indexOfFirst* adjacentMatriks[0][0] + indexOfSecond * adjacentMatriks[0][1]) % 26;
            int secondValue = (indexOfFirst * adjacentMatriks[1][0] + indexOfSecond * adjacentMatriks[1][1]) %26;
            String firstOutputChar = String.valueOf(english_Alphabet.charAt(firstValue));
            String secondOutputChar = String.valueOf(english_Alphabet.charAt(secondValue));
            output.append(firstOutputChar);
            output.append(secondOutputChar);
        }
        return output.toString();
    }

    public static int[][] findAdjacentOfMatriks(int[][] matriks){
        int[][] adjacentMatriks = new int[2][2];
        adjacentMatriks[0][0] = matriks[1][1];
        adjacentMatriks[1][1] = matriks[0][0];
        adjacentMatriks[0][1] = matriks[0][1]* -1;
        adjacentMatriks[1][0] = matriks[1][0]* -1;
        return adjacentMatriks;
    }

    public static int findInverseOfModulo26(int number) {
        for (int i = 1; i < 26; i++) {
            if (i * number % 26 == 1) {
                return i;
            }
        }
        return -1;
    }


    public static int findInverseOfModulo29(int number) {
        for (int i = 1; i < 29; i++) {
            if (i * number % 29 == 1) {
                return i;
            }
        }
        return -1;
    }
}
