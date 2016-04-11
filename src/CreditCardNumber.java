import java.util.Scanner;

public class CreditCardNumber {
  public static void main(String[] args){
    Scanner input = new Scanner(System.in);  
    System.out.print("Enter a credit card number as a long integer: ");
    long number = input.nextLong();
    
    if (isValid(number) == true)
      System.out.println(number + " is valid");
    else
      System.out.println(number + " is invalid");
  }  
  
  /** Return true if the card number is valid */
  public static boolean isValid(long number){
    int sum = sumOfDoubleEvenPlace(number) + sumOfOddPlace(number);
    
    if (sum % 10 != 0){
      return false;
    }
    
    if (prefixMatched(number,4)==false && prefixMatched(number,5)==false
         && prefixMatched(number,37)==false && prefixMatched(number,6) == false)
    {
      return false;
    }
    
    
    if (getSize(number) < 13 || getSize(number) > 16)
      return false;
    
    return true;
  }
  
  /** Get the result from Step 2 */
  public static int sumOfDoubleEvenPlace(long number){
    int sum = 0;
    
    for(int i = 2; i < getSize(number); i+=2){
      sum += getDigit(2 * ((int)(number / Math.pow(10, i - 1))%10));  
    }
    
    return sum;
  }
  
    /** Return this number if it is a single digit, otherwise,
* return the sum of the two digits */
  public static int getDigit(int number){
    int first = number / 10;
    int second = number % 10;
    if (first == 0)
      return number;
    else
      return first + second;
  }
  
  /** Return sum of odd-place digits in number */
  public static int sumOfOddPlace(long number){
    int sum = 0; 
    
    for(int i = 1; i <= getSize(number); i += 2)
      sum += ((int)(number / Math.pow(10, i - 1))%10);
    
    return sum;
  }
  
  /** Return true if the digit d is a prefix for number */
  public static boolean prefixMatched(long number, int d){
    if (getPrefix(number, getSize(d)) == d)
      return true;
    return false;
  }
  
  /** Return the number of digits in d */
  public static int getSize(long d){
    String num = Long.toString(d);
    int length = num.length();
    return length;
  }
  
  /** Return the first k number of digits from number. If the
* number of digits in number is less than k, return number. */
  public static long getPrefix(long number, int k){
    return number / (long)(Math.pow(10, (double)(getSize(number) - k)));  
  }
} // class CreditCardNumber
