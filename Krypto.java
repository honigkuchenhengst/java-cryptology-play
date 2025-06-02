import java.util.HashMap;

public class Krypto {
    public HashMap<String, Integer> letterCount(String text) {
        HashMap<String, Integer> letterCount = new HashMap<>();
        for (int i = 97; i < 123; i++) {
            letterCount.put(String.valueOf((char) i), 0);
        }
        for (int i = 0; i < text.length(); i++) {
            String letter = String.valueOf(text.charAt(i)).toLowerCase();
            if (letterCount.containsKey(letter)) {
                int currentCount = letterCount.get(letter);
                letterCount.put(letter, currentCount + 1);
            }
        }
        return letterCount;
    }

    public String caesar(String text, int n) {
        text = text.toLowerCase();
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                res.append(" ");
            } else {
                int letterCode = ((int) text.charAt(i) - 'a' + n) % 26;
                res.append((char) (letterCode + 'a'));
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        Krypto krypto = new Krypto();
        String text = "Bailey Whitfield 'Whit' Diffie ForMemRS (born June 5, 1944) is an American cryptographer and mathematician and one of the pioneers of public-key cryptography along with Martin Hellman and Ralph Merkle. Diffie and Hellman's 1976 paper New Directions in Cryptography[5] introduced a radically new method of distributing cryptographic keys, that helped solve key distribution—a fundamental problem in cryptography. Their technique became known as Diffie–Hellman key exchange. The article stimulated the almost immediate public development of a new class of encryption algorithms, the asymmetric key algorithms.";
        String example = "Hallo Moin";
        String exampleEncrypt = krypto.caesar(example, 5);
        System.out.println(exampleEncrypt);
        String exampleDecrypt = krypto.caesar(exampleEncrypt, -5);
        System.out.println(exampleDecrypt);
    }
}
