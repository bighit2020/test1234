package jpubs.ch25;
public class MyStringUtility {
  public static String reverseString(String s) {
    return ( new StringBuffer(s) ).reverse().toString();
  }
}
