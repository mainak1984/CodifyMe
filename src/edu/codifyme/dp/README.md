# **DP Cheat Sheet**

## _**1. 0/1 Knapsack problem**_
### Source Code:
> * Given Wt[], Val[], Sum, create dp\[Wt.length+1]\[Sum+1]
> * Initialize 0th Row as 0
> * Initialize 0th column as 0
> * DP code section
> 
> ```java  
> for (int i = 1; i < Wt.length+1; i++)
>      for (int j = 1; j < sum+1; j++)
>          if ( Wt[i-1] <= j ) {  
>              dp[i][j] = Max(dp[i-1][j], val[i-1] + dp[i-1][j - Wt[i -1]);  
>          } else {  
>              dp[i][j] = dp[i-1][j];  
>          }
> 
> return dp[Wt.length][sum];
> ```

### Variations:

#### 1.A. Subset Sum Problem - Find a subset whose sum is given
##### Differences:
> * Init dp\[0]\[j] as _**False**_, dp\[i]\[0] as _**True**_  
> * Treat only array as Wt arr and target sum as sum  
> * Instead of Max, use _**OR**_ on boolean returns  

#### 1.B. Equal Sum Partition Problem - Find two subsets whose sum is equal
##### Differences:
> * Get whole array sum - arrTotalSum  
> * Call _**SubsetSum(arr[], arrTotalSum/2)**_  
> * [Code ref](PartitionEqualSubsetSum.java)

#### 1.C. Count of Subset Sum - Find how many possible subsets whose sum matches is given sum
#### Differences:
> * Init dp\[0]\[j] as _**0**_, dp\[0]\[0] as _**1**_  
> * Treat only array as Wt arr and target sum as sum  
> * Instead of Max, use _**+**_ on int returns  

#### 1.D. Minimum Subset Sum difference
#### Differences:
> * If one subset sum is S1, other will be 'Range - S1'; hence 'Range - 2S1' becomes the diff  
> * call _**SubsetSum(arr[], target Sum)**_  
> * Get the last row as Array and parse until half of the column range
> * Check for every element and find min subset
>
> ```java
> int min = Integer.MIN_VALUE;
> for (int i = 0; i < V.size; i++)
>     min = Math.min( min, range - 2 * V[i]);
> 
> return min;
> ```

#### 1.E. Count of Subset with a given diff - Find how many possible subsets has given diff among them
#### Differences:
> * Get whole array sum - arrTotalSum  
> * Find target Sum = (Sum + diff)/2
> * Call _**SubsetSum(arr[], target Sum)**_  

#### 1.F. Target Sum - Change sign to + and - and find count of subset having diff of 'sum'
#### Differences:
> * This is exactly same as Count of subset with given diff  
> * [Code Ref](../leetcode/practice/dp/TargetSum.java)   


## _**2. Unbounded Knapsack problem**_
### Source Code:
> * Given Wt[], Val[], Sum, create dp\[Wt.length+1]\[Sum+1]
> * Initialize 0th Row as 0
> * Initialize 0th column as 0
> * DP code section
> 
> ```java  
> for (int i = 1; i < Wt.length+1; i++)
>      for (int j = 1; j < sum+1; j++)
>          if ( Wt[i-1] <= j ) {  
>              dp[i][j] = Max(dp[i-1][j], val[i-1] + dp[i][j - Wt[i -1]]);  
>          } else {  
>              dp[i][j] = dp[i-1][j];  
>          }
> 
> return dp[Wt.length][sum];
> ```
> * **Note after taking choice, we still use i instead of i-1 in '(dp\[i]\[j - Wt\[i -1]])' for unbounded knapsack**

### Variations:

#### 2.A. Rod Cutting Problem - Given a rod and price for each possible pieces, find maximum value by cutting any no of time
##### Differences:
> * Given length\[] and price\[] for rod values and N as rod length  
> * create dp\[length\[].length]\[N+1]  
> * Use length\[] as Wt\[] and price\[] as value\[] in unbounded knapsack  

#### 2.B. Coin Change - Find maximum no. of ways to have the coin change
##### Differences:
> * Init dp\[0]\[j] as _**0**_, dp\[0]\[0] as _**1**_  
> * Treat only array (Coin\[]) as Wt arr and target change as sum  
> * Instead of Max, use _**+**_ on int returns  

#### 2.C. Coin Change II - Find min coin for giving a change
##### Differences:
> * Init dp\[0]\[j] as _**Integer.MAX_VALUE**_, dp\[0]\[0] as _**0**_  
> * Treat only array (Coin\[]) as Wt arr and target change as sum  
> * Instead of Max, use _**MIN**_ on int returns  
> * [Code Ref](../leetcode/interview/googledeck/dp/CoinChange.java)   

#### 2.D. Minimum Ribbon Cut


## _**3. Longest Common Substring**_
### Source Code:
> * Sample (15)
> ```java
> // Recursion
> int LCS(String x, String y, int n, int m) {
>     if (n == 0 || m == 0) {
>         return 0;
>     }
> 
>     // Memoization
>     if ( dp[m][n] != -1 ) {
>         return dp[m][n];
>     }
> 
>     if ( x.charAt(n-1) == y.charAt(m-1) ) {
>         dp[m][n] = 1 + LCS(x, y, n-1, m-1);
>     } else {
>         dp[m][n] = Math.max( LCS(x, y, n-1, m), LCS(x, y, n, m-1) );
>     }
>     return dp[m][n];
> }
> 
> // DP
> int LCS(String x, String y, int n, int m) {
>     int[][] dp = new int[n+1][m+1];
> 
>     for (int i = 0; i < n; i++) {
>         for (for j = 0; j < m; j++) {
>             if ( i == 0 || j == 0 ) {
>                 dp[i][j] = 0;
>             }
> 
>             if ( x.charAt(i-1) == y.charAt(j-1) ) {
>                 dp[i][j] = 1 + dp[i-1][j-1];
>             } else {
>                 dp[i][j] = Math.max( dp[i][j-1], dp[i-1][j] );
>             }
>         }
>     }
>     return dp[n][m];
> }
> ```

#### 3.A. Print LCS - Print longest common substring between 2 strings

#### 3.B. Shortest Common Super sequence - Find the common super sequence and the shortest one of them
> * Get the DP array for LCS and use backtracking for getting the string
>
> ```java
> String getLCSString(String x, String y, int n, int m) {
>     int[][] dp = new int[n][m];
>     // Get the DP Array ready
>     
>     StringBuilder sb = new StringBuilder();    
> 
>     while ( n > 0 && m > 0 ) {
>         if ( x.charAt(n - 1) == y.charAt(m - 1) ) {
>             sb.append(x.charAt(n - 1));
>             n--;
>             m--;
>         } else {
>             if ( dp[n-1][m] > dp[n][m-1] ) {
>                 n--;
>             } else {
>                 m--;
>             }
>         }
>     }
> 
>     return sb.reserse().toString();
> }
> ```

#### 3.C. Print SCS - Print Shortest Common Super sequence

#### 3.D. Edit distance - Minimum number of insert and deletion to change from A to B

#### 3.E. Longest Repeating Subsequence

#### 3.F. Length of longest subsequence of A which is subsequence in B

#### 3.G. Subsequence Pattern Matching

#### 3.H. Count how many times A appears as subsequnce in B

#### 3.I. Largest Palindromic Subsequence
> Reverse the original substring
> return LCS(A, reverse(A))

#### 3.J. Largest Palindromic Substring

#### 3.K. Count of Palindromic Substring

#### 3.L. Minimum no. of deletion in a String to make it a Palindrome

#### 3.M. Minimum no. of Insertion in a String to make it a Palindrome


## _**4. Longest Increasing Subsequence**_
### Source Code:
> * Sample (10)


## _**5. Kadane's Algorithm**_
### Source Code:
> * Sample (6)


## _**6. Fibonacci Sequence**_
### Source Code:
> * Sample (7)


## _**7. Matrix Chain Multiplication**_
### Source Code:
> * Sample (7)

#### 7.A. Print MCM - Print multiplied Matrix chain

#### 7.B. Evaluate Expression to True/Boolean parenthesis

#### 7.C. Min/Max value of an expression

#### 7.D. Palindrome Partition

#### 7.E. Scramble string

#### 7.F. Egg Dropping problem


## _**8. DP On Trees**_
### Source Code:
> * Sample (4)
> ```java
> int result;
> 
> int solve(TreeNode root) {
>     if (null == root) {
>         return 0;
>     }
>     
>     int l = solve(root.left, result);
>     int r = solve(root.right, result);
> 
>     int temp = 1 + Math.max(l, r); // May change assignment based on problem
>     int ans = Math.max(temp, 1+l+r); // May change 2nd param based on problem
>     result = Math.max(result, ans);
> 
>     return temp;
> }
> ```

#### 8.A. Diameter of a Binary Tree

#### 8.B. Maximum path Sum from any node to any Node

#### 8.C. Maximum path Sum from leaf to leaf


## _**9. DP On Grid**_
### Source Code:
> * Sample (14)


## _**10. Others**_
### Source Code:
> * Sample (5)
