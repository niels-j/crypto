package nielsj.crypto.view;

// class Hex provides formatting methods
// for conversion between a hex string and a byte array.

// A hex string "0A2B" is converted to the byte array {10, 43}

// There are 16 hex digits: 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, A, B, C, D, E, F
// A hex string provides a human-readable view
// of a byte array with arbitrary bytes


public class Hex {

  /********************
   *  public methods  *
   ********************/

  // conversion of string of hex digits to byte array
  public static byte[] hexStringToByteArray(String s) {
    String us = s.toUpperCase();
    int characters = us.length();
    if ( isOdd(characters) ) {
      System.out.println("Hex-exception: uneven size of hex string ??");
      return new byte[0];
    }
    int bytes = characters/2;
    byte[] b = new byte[bytes];
    for (int i = 0; i < bytes; i++) {
      b[i] = (byte) hexPairToByte(us.substring(2*i,2*i+2));
    }
    return b;
  }

  // conversion of byte array to string of hex digits

  public static String byteArrayToHexString(byte[] b) {
    StringBuffer sb = new StringBuffer("");
    String hexString;
    for (int i = 0; i < b.length; i++) {
      hexString = byteToHexPair(b[i]);
      sb.append(hexString);
    }
    return sb.toString();
  }

  // conversion to and from multiline strings
  // 'multiline' strings are displayed on multiple lines
  // (so when don't have to do horizontal scrolling)

  // to multiline
  public static String hexStringToMultiLine(String hexString, int charPerLine) {
    int inLength = hexString.length();
    StringBuffer sb = new StringBuffer("");
    String s = sb.toString();
    int localc = 0;
    for (int i = 0; i < inLength; i++) {
      sb.append(hexString.charAt(i));
      localc++;
      if (localc == charPerLine && i + 1 < inLength ) {
        sb.append("\n");
        localc = 0;
      }
    }
    return sb.toString();
  }

  public static String hexStringToSingleLine(String hexString) {
    int inLength = hexString.length();
    StringBuffer sb = new StringBuffer("");
    String s = sb.toString();
    char c;
    for (int i = 0; i < inLength; i++) {
      c = hexString.charAt(i);
      if (c != '\n') {
        sb.append(c);
      }
    }
    return sb.toString();
  }

  /*********************
   *  private methods  *
   *********************/

  // helper methods for hex to byte conversion

  private static boolean isOdd(int c) {
    return (c % 2) == 1;
  }

  //  "hex pair" (two hex digits) converted to one byte
  private static int hexPairToByte(String hexPair) {
    int b0 = hexToHalfByte(hexPair.charAt(0));
    int b1 = hexToHalfByte(hexPair.charAt(1));
    int result = b0*16 + b1;
    return result;
  }

  // the basic conversion of hex digit to "half byte"
  // (a "half byte" (nibble) is four bits of information)
  private static int hexToHalfByte(char c) {
    switch(c) {
      case '0': return 0;
      case '1': return 1;
      case '2': return 2;
      case '3': return 3;
      case '4': return 4;
      case '5': return 5;
      case '6': return 6;
      case '7': return 7;
      case '8': return 8;
      case '9': return 9;
      case 'A': return 10;
      case 'B': return 11;
      case 'C': return 12;
      case 'D': return 13;
      case 'E': return 14;
      case 'F': return 15;

      default:
        System.out.println("Util: Input must be char in range '0'-'F'");
        // System.exit(0); // error
        // for hard exit, use:
        // android.os.Process.killProcess(android.os.Process.myPid());
        // https://stackoverflow.com/questions/17719634/how-to-exit-an-android-app-programmatically
    } // switch
    return 0;
  }

  // helper methods for byte to hex conversion

  // one byte to "hex pair"

  public static String byteToHexPair(int b) {
    // rename to hexpair
    int cleanByte = discardNegativeBit(b);
    int b0 = cleanByte / 16;
    int b1 = cleanByte % 16;
    char c0 = halfByteToHex(b0);
    char c1 = halfByteToHex(b1);
    return new String(new char[] {c0, c1});
  }

  // the basic conversion of hex digit to "haalf
  private static char halfByteToHex(int b) {
    switch(b) {
      case 0: return '0';
      case 1: return '1';
      case 2: return '2';
      case 3: return '3';
      case 4: return '4';
      case 5: return '5';
      case 6: return '6';
      case 7: return '7';
      case 8: return '8';
      case 9: return '9';
      case 10: return 'A';
      case 11: return 'B';
      case 12: return 'C';
      case 13: return 'D';
      case 14: return 'E';
      case 15: return 'F';

      default:
        System.out.println("Input must be byte in range 0 - 15");
    } // switch
    return '0';
  }

  // discardNegativeBit(b)
  // - discards (ie., sets to 0)
  //   all bits to the left of the rightmost eight bits
  // - this is to prevent the following error
  // - hex pair 10 is converted to byte 10000000 and int 100..0010000000
  //   but this byte is interpreted as '-128'
  //   (with the leftmost '1' interpreted as a minus sign)
  //   where as we want the number to be interpreted as '128'
  // - the error would be due to Java's two's complement implementation
  //   of the byte and int types:

  private static int discardNegativeBit(int b) {
    return b & 0b11111111;
  }

  // some test methods that are to be deleted

  // test1() and test2() test challenges
  // with two's complement implementation of a byte

  // test1(): converting "80"
  public static void test1() {
    String s = "80"; // ie. 128
    System.out.println("Converting: " + s);
    byte[] b = hexStringToByteArray(s);
    for (int i=0; i<b.length; i++) {
      System.out.print(b[i] + " ");
    }
    System.out.println("");
  }

  // test2(): converting int to byte to int,
  // risking total conversion is from 128 to -128
  // (without the "bit-anding")
  public static void test2() {
    int i = 128;
    System.out.println("Util: int is: " + i);
    byte b = (byte) i;
    System.out.println("Util: same int as byte is: " + b);
    int i2 = b;
    System.out.println("Util: same int but back to int is: " + i2);
    int i3 = b & 0xFF;  // bit-anding
    System.out.println("Util: again same but after masking: " + i3);
  }

}
