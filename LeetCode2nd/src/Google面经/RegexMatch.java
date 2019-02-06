package Google面经;

class Node {
    String type;
    Node left;
    Node right;
    char c;
}
public class RegexMatch {

    public boolean isMatch(Node regex, String s) {
        if (regex == null) return s.length() == 0;
        if (regex.type.equals("CHAR")) {
            return s.length() == 1 && s.charAt(0) == regex.c;
        }
        if (regex.type.equals("DIST")) {
            for (int i = 0; i < s.length(); i++) {
                if(isMatch(regex.left,s.substring(0, i)) || isMatch(regex.right, s.substring(i))) return true;
            }
            return false;
        }
        if (regex.type.equals("CONTACT")) {
            for (int i = 0; i < s.length(); i++) {
                if(isMatch(regex.left,s.substring(0, i)) && isMatch(regex.right, s.substring(i))) return true;
            }
            return false;
        }
        if (regex.type.equals("REP")) {
            if (regex.right != null) return false;
            for (int i = 0; i < s.length(); i++) {
                if (isMatch(regex.left, s.substring(0,i)) && isMatch(regex.left, s.substring(i))) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    public static void main(String[] args) {


    }
}
