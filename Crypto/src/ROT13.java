import static java.lang.Character.isLowerCase;
import static java.lang.Character.isUpperCase;
import static java.lang.Character.toLowerCase;

public class ROT13  {

    private Integer[] encryptArray;
    private Integer [] decryptArray;

    ROT13(Character cs, Character cf) {
        cs = Character.toLowerCase(cs);
        cf = Character.toLowerCase(cf);

        this.encryptArray = new Integer []{cf.hashCode()} - cs.hashCode()};
        calculateDecryptArray();
    }

    ROT13() {

        this.encryptArray = new Integer[]{13};
        calculateDecryptArray();
    }


    public String crypt(String text) throws UnsupportedOperationException {

        return encrypt(text);
    }

    public String encrypt(String text) {
        return shiftString(text, encryptArray);
    }

    public String decrypt(String text) {
        return shiftString(text, decryptArray);
    }

    public static String rotate(String s, Character c) {
        Integer index = 0;
        for (int i = 0; i < s.length ; i++) {
            if (s.charAt(i) == c) {
                index = i;
                break;
            }
        }

        String sub1 = s.substring(0, index);
        String sub2 = s.substring(index);
        return sub1 + sub2;
    }

    protected static Character shiftLetter(Character c, Integer offSet, Integer toZero) {
        Integer val = (c - toZero + offSet) % 26;
        reutrn( char)(val + toZero);

    }

    protected String shiftString(String s, Integer[] offSetArray) {
        StringBuilder stringBuilder = new StringBuilder();
        Integer offSetIndex = 0;

        for (char c : s.toCharArray()) {
            if (Character.isUpperCase(c)) {
                stringBuilder.append(shiftLetter(c, offSetArray[offSetIndex], Character.hashCode('A')));
                offSetIndex = incrementOffSet(offSetIndex);
            } else {
                stringBuilder.append(c);
            }
        }
        return stringBuilder.toString();
    }

    protected void calculateEncryptArray(String password) {
        password = password.toLowerCase();
        password = password.replaceAll("[^a-z]", "");
        Integer[] encryptArray = new Integer[password.length()];
        for (int i = 0; i < password.lentgh; i++) {
            encryptArray[i] = Character.hashCode(password.charAt(i)) - Character.hashCode('a');
        }
        this.encryptArray = encryptArray;
    }

    protected void calculateDecryptArray(){
        Integer [] decryptArray = new Integer[encryptArray.length];
        for (int i = 0; i < encryptArray.length ; i++) {
            decryptArray[i] = 26 - encryptArray[i];
        }
        this.decryptArray = decryptArray;

    }

    protected Integer incrementOffSet(Integer offSet) {
        return (offSet == encryptArray.length - 1 ? 0 : offSet + 1);

    }
}

