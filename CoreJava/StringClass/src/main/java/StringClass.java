/**
 * Created by hsmak on 3/3/15.
 */
public class StringClass {
    public static void main(String[] args){

        isPalindrome_v1("12321");//using StringBuilder
        isPalindrome_v1("123421");

        isPalindrome_v2("12321");//using String itself
        isPalindrome_v2("123421");

        removeChar_v1("Husain uuu ululu pouo", 'u');//using String itself
        removeChar_v2("Husain uuu ululu pouo", 'u');//using StringBuilder

    }

    static void isPalindrome_v1(String s){

        StringBuilder stringBuilder = new StringBuilder(s);
        stringBuilder.reverse();
        String revS = stringBuilder.toString();
        if(revS.equals(s))
            System.out.println("v1: Palindrome!");
        else
            System.out.println("v1: Not Palindrome!");

    }

    static void isPalindrome_v2(String s){

        int length = s.length();
        for(int i = 0; i < length/2; i++){
            if(s.charAt(i) != s.charAt(length-1-i)){
                System.out.println("v2: Not Palindrome!");
                return;
            }
        }
        System.out.println("v2: Palindrome!");

    }

    static void removeChar_v1(String s, char c){

        String newS = s.replaceAll(Character.toString(c), "");
        System.out.println(newS);

    }

    static void removeChar_v2(String s, char c){

        StringBuilder stringBuilder = new StringBuilder(s);

        while(true){
            int charIndex = stringBuilder.indexOf(Character.toString(c));
            if(charIndex < 0)
                break;
            stringBuilder.deleteCharAt(charIndex);
        }
        System.out.println(stringBuilder.toString());

    }
}
