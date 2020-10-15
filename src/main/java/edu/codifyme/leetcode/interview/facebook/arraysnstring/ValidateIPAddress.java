package edu.codifyme.leetcode.interview.facebook.arraysnstring;

/**
 * 468. Validate IP Address
 * MEDIUM: https://leetcode.com/problems/validate-ip-address/solution/
 *
 * Given a string IP, return "IPv4" if IP is a valid IPv4 address, "IPv6" if IP is a valid IPv6 address or "Neither" if
 * IP is not a correct IP of any type.
 *
 * A valid IPv4 address is an IP in the form "x1.x2.x3.x4" where 0 <= xi <= 255 and xi cannot contain leading zeros.
 * For example, "192.168.1.1" and "192.168.1.0" are valid IPv4 addresses but "192.168.01.1", while "192.168.1.00" and
 * "192.168@1.1" are invalid IPv4 addresses.
 *
 * A valid IPv6 address is an IP in the form "x1:x2:x3:x4:x5:x6:x7:x8" where:
 *
 * 1 <= xi.length <= 4
 * xi is a hexadecimal string which may contain digits, lower-case English letter ('a' to 'f') and upper-case English
 * letters ('A' to 'F').
 * Leading zeros are allowed in xi.
 * For example, "2001:0db8:85a3:0000:0000:8a2e:0370:7334" and "2001:db8:85a3:0:0:8A2E:0370:7334" are valid IPv6 addresses,
 * while "2001:0db8:85a3::8A2E:037j:7334" and "02001:0db8:85a3:0000:0000:8a2e:0370:7334" are invalid IPv6 addresses.
 *
 * Example 1:
 * Input: IP = "172.16.254.1"
 * Output: "IPv4"
 * Explanation: This is a valid IPv4 address, return "IPv4".
 *
 * Example 2:
 * Input: IP = "2001:0db8:85a3:0:0:8A2E:0370:7334"
 * Output: "IPv6"
 * Explanation: This is a valid IPv6 address, return "IPv6".
 *
 * Example 3:
 * Input: IP = "256.256.256.256"
 * Output: "Neither"
 * Explanation: This is neither a IPv4 address nor a IPv6 address.
 *
 * Example 4:
 * Input: IP = "2001:0db8:85a3:0:0:8A2E:0370:7334:"
 * Output: "Neither"
 *
 * Example 5:
 * Input: IP = "1e1.4.5.6"
 * Output: "Neither"
 *
 * Constraints:
 * IP consists only of English letters, digits and the characters '.' and ':'.
 *
 * Algorithm 1: Divide and Conquer
 * For the IPv4 address, we split IP into four chunks by the delimiter ., while for IPv6 address, we split IP into eight
 * chunks by the delimiter :.
 * For each substring of "IPv4" address, we check if it is an integer between 0 - 255, and there is no leading zeros.
 * For each substring of "IPv6" address, we check if it's a hexadecimal number of length 1 - 4.
 *
 * Algorithm 2: Using pattern and regex
 *
 */
public class ValidateIPAddress {
    // Approach 1:
    public String validIPAddress(String IP) {
        String ip = IP.toLowerCase();

        String[] ipv4parts = ip.split("\\.");

        if (ipv4parts.length == 4 && ip.charAt(0) != '.' && ip.charAt(ip.length() - 1) != '.') {
            return validateIPv4(ipv4parts);
        }

        String[] ipv6parts = ip.split(":");

        if (ipv6parts.length == 8 && ip.charAt(0) != ':' && ip.charAt(ip.length() - 1) != ':') {
            return validateIPv6(ipv6parts);
        }

        return "Neither";
    }

    String validateIPv4(String[] ipv4Parts) {
        for (int i = 0; i < 4; i++) {
            String part = ipv4Parts[i];

            if (part.length() > 1 && part.charAt(0) == '0') {
                return "Neither";
            }

            try {
                Integer partInt = Integer.parseInt(part);
                if (partInt == null || partInt < 0 || partInt > 255) {
                    return "Neither";
                }
            } catch (NumberFormatException ne) {
                return "Neither";
            }
        }

        return "IPv4";
    }

    String validateIPv6(String[] ipv6Parts) {
        for (int i = 0; i < 8; i++) {
            String part = ipv6Parts[i];

            if (part.length() < 1 || part.length() > 4) {
                return "Neither";
            }

            for (char ch: part.toCharArray()) {
                if ((ch >= '0' && ch <= '9') || (ch >= 'a' && ch <= 'f')) {
                    // do nothing
                } else {
                    return "Neither";
                }
            }
        }

        return "IPv6";
    }

    //Approach 1: alternate implementation
//    public String validateIPv4(String IP) {
//        String[] nums = IP.split("\\.", -1);
//        for (String x : nums) {
//            // Validate integer in range (0, 255):
//            // 1. length of chunk is between 1 and 3
//            if (x.length() == 0 || x.length() > 3) return "Neither";
//            // 2. no extra leading zeros
//            if (x.charAt(0) == '0' && x.length() != 1) return "Neither";
//            // 3. only digits are allowed
//            for (char ch : x.toCharArray()) {
//                if (! Character.isDigit(ch)) return "Neither";
//            }
//            // 4. less than 255
//            if (Integer.parseInt(x) > 255) return "Neither";
//        }
//        return "IPv4";
//    }
//
//    public String validateIPv6(String IP) {
//        String[] nums = IP.split(":", -1);
//        String hexdigits = "0123456789abcdefABCDEF";
//        for (String x : nums) {
//            // Validate hexadecimal in range (0, 2**16):
//            // 1. at least one and not more than 4 hexdigits in one chunk
//            if (x.length() == 0 || x.length() > 4) return "Neither";
//            // 2. only hexdigits are allowed: 0-9, a-f, A-F
//            for (Character ch : x.toCharArray()) {
//                if (hexdigits.indexOf(ch) == -1) return "Neither";
//            }
//        }
//        return "IPv6";
//    }
//
//    public String validIPAddress(String IP) {
//        if (IP.chars().filter(ch -> ch == '.').count() == 3) {
//            return validateIPv4(IP);
//        }
//        else if (IP.chars().filter(ch -> ch == ':').count() == 7) {
//            return validateIPv6(IP);
//        }
//        else return "Neither";
//    }

    // Approach 2: Regex:
//    String chunkIPv4 = "([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])";
//    Pattern pattenIPv4 =
//            Pattern.compile("^(" + chunkIPv4 + "\\.){3}" + chunkIPv4 + "$");
//
//    String chunkIPv6 = "([0-9a-fA-F]{1,4})";
//    Pattern pattenIPv6 =
//            Pattern.compile("^(" + chunkIPv6 + "\\:){7}" + chunkIPv6 + "$");
//
//    public String validIPAddress(String IP) {
//        if (pattenIPv4.matcher(IP).matches()) return "IPv4";
//        return (pattenIPv6.matcher(IP).matches()) ? "IPv6" : "Neither";
//    }
}
