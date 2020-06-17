/*
Write a function to check whether an input string is a valid IPv4 address or IPv6 address or neither.

IPv4 addresses are canonically represented in dot-decimal notation, which consists of four decimal numbers, each ranging from 0 to 255, separated by dots ("."), e.g.,172.16.254.1;

Besides, leading zeros in the IPv4 is invalid. For example, the address 172.16.254.01 is invalid.

IPv6 addresses are represented as eight groups of four hexadecimal digits, each group representing 16 bits. The groups are separated by colons (":"). For example, the address 2001:0db8:85a3:0000:0000:8a2e:0370:7334 is a valid one. Also, we could omit some leading zeros among four hexadecimal digits and some low-case characters in the address to upper-case ones, so 2001:db8:85a3:0:0:8A2E:0370:7334 is also a valid IPv6 address(Omit leading zeros and using upper cases).

However, we don't replace a consecutive group of zero value with a single empty group using two consecutive colons (::) to pursue simplicity. For example, 2001:0db8:85a3::8A2E:0370:7334 is an invalid IPv6 address.

Besides, extra leading zeros in the IPv6 is also invalid. For example, the address 02001:0db8:85a3:0000:0000:8a2e:0370:7334 is invalid.

Note: You may assume there is no extra space or special characters in the input string.

Example 1:
Input: "172.16.254.1"

Output: "IPv4"

Explanation: This is a valid IPv4 address, return "IPv4".
Example 2:
Input: "2001:0db8:85a3:0:0:8A2E:0370:7334"

Output: "IPv6"

Explanation: This is a valid IPv6 address, return "IPv6".
Example 3:
Input: "256.256.256.256"

Output: "Neither"

Explanation: This is neither a IPv4 address nor a IPv6 address.
*/

//Solution:


class Solution {
    public String validIPAddress(String IP) {
        if(validIPv4(IP)) return "IPv4";
        if(validIPv6(IP)) return "IPv6";
        return "Neither";
    }
    public boolean validIPv4(String IP){
        String[] str = IP.split("\\.");
        // Check if String length is less thn 4
        if(str.length != 4) return false;
        // Check if last char is not .
        if(IP.charAt(IP.length()-1) == '.')return false;
        for(int i = 0; i < str.length; i++){
            // Try to convert string into the number
            try{
                int number = Integer.parseInt(str[i]);
                //check if number is greater than 255
                if(number > 255){
                    return false;
                }
                //check if number is less thn 10 and have leading zero
                if((number < 10 && str[i].length()>1) || (number < 100 && str[i].length()>2) ){
                    return false;
                }
            }
            // catch the exception is string is not converted to number i.e. in case of invalid IPv4
            catch (Exception e){
                return false;
            }
        }
        return true;
    }
    public boolean validIPv6(String IP){
        String[] str = IP.split(":");
        // Check if String length is less thn 8
        if(str.length != 8) return false;
        // Check if last char is not :
        if(IP.charAt(IP.length()-1) == ':')return false;
        for(int i = 0; i < str.length; i++){
            String currentStr = str[i].toLowerCase();
            if(currentStr.length() >4 || currentStr.length() < 1) return false;
            int count = 0;
            for(int j = 0; j < currentStr.length(); j++){
                char c = currentStr.charAt(j);
                if( ((c >= 48 && c<= 57) || (c >= 97 && c <= 102 ))){
                    if(c == 48){
                        count++;
                    }else if(count  == 4 ){
                        return false;
                    }
                }
                else return false;
            }

        }
        return true;
    }
}
