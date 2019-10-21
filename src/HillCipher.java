/**
 * Created by canbay on 21.10.2019.
 */
public class HillCipher {
    public static String english_Alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

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
        boolean isSingleNumber = false;
        if(wordToBeDecrypted.length() % 2 == 1){
            isSingleNumber = true;
            wordToBeDecrypted = wordToBeDecrypted.concat("A");

        }
        int det = (matriks[0][,0] * matriks[1][1]) - (matriks[0][1]* matriks[1][0]);
        while(det <= 0){
            det+=26;
        }
        int num = findInverseOfModuloN(det,26);
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
            int inverseOfDet = findInverseOfModuloN(determinant,26);
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
        if(isSingleNumber){
            return output.toString().substring(0,output.toString().length()-1);
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

    public static int findInverseOfModuloN(int number, int moduloNumber) {
        for (int i = 1; i < moduloNumber; i++) {
            if (i * number % moduloNumber == 1) {
                return i;
            }
        }
        return -1;
    }
}
