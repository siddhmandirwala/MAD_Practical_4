import java.util.Scanner;

public class HillCipher {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the plainText: ");
        String plainText = sc.nextLine();

        System.out.print("Enter Block Size: ");
        int blockSize = sc.nextInt();

        System.out.print("Enter Keyword: ");
        String keyword = sc.next();

        int check = checker(plainText, blockSize, keyword);
        if (check == 1) {
            Encryption(plainText, blockSize, keyword);
        }

        sc.close();
    }

    public static int checker(String plainText, int blockSize, String key) {
        if (plainText.length() < blockSize) {
            return 0;
        }
        if (key.length() != (blockSize * blockSize)) {
            System.out.println("You Entered incorrect keyword!");
            return 0;
        }

        int length = key.length();

        for (int i = 0; i < length - 1; i++) {
            char currentChar = key.charAt(i);
            for (int j = i + 1; j < length; j++) {
                if (currentChar == key.charAt(j)) {
                    System.out.println("You Entered incorrect keyword!");
                    return 0; // Character is redundant
                }
            }
        }
        return 1;
    }

    public static String Encryption(String plainText, int blockSize, String key) {
        String cipherText = "";

        int[][] keyMatrix = createKeymatrix(key, blockSize);

        String[] blocks = createBlocks(plainText, blockSize);

        for (String block : blocks) {
            int[][] vector = createBlockVector(block);
            int row1 = keyMatrix.length;
            int col1 = keyMatrix[0].length;
            // int row2 = blocks.length;
            int col2 = 1;

            int[][] cipher = new int[row1][col2];

            for (int i = 0; i < row1; i++) {
                for (int j = 0; j < col2; j++) {
                    cipher[i][j] = 0;
                    for (int k = 0; k < col1; k++) {
                        cipher[i][j] += keyMatrix[i][k] * vector[k][j];
                    }
                    cipher[i][j] %= 26;
                }
            }

            for (int i = 0; i < cipher.length; i++) {
                cipherText += (char) (cipher[i][0] + 97);
            }
        }
        return cipherText;
    }

    public static int[][] createKeymatrix(String key, int blockSize) {
        int[][] keyMatrix = new int[blockSize][blockSize];
        int keyIndex = 0;

        for (int i = 0; i < keyMatrix.length; i++) {
            for (int j = 0; j < keyMatrix[i].length; j++) {
                keyMatrix[i][j] = key.charAt(keyIndex) - 97;
                keyIndex++;
            }
        }
        return keyMatrix;
    }

    public static String[] createBlocks(String plainText, int blockSize) {
        int temp = plainText.length() % blockSize;
        if (temp != 0) {
            for (int i = 0; i < (blockSize - temp); i++) {
                plainText += 'x';
            }
        }

        String[] blocks = new String[plainText.length() / blockSize];

        int index = 0;
        for (int i = 0; i < blocks.length; i++) {
            blocks[i] = plainText.substring(index, (index + blockSize));
            index += blockSize;
        }

        return blocks;
    }

    public static int[][] createBlockVector(String block) {
        int[][] vector = new int[block.length()][1];

        for (int i = 0; i < vector.length; i++) {
            vector[i][0] = block.charAt(i) - 97;
        }

        return vector;
    }

}