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
>              dp\[i]\[j] = Max(dp\[i-1]\[j], val\[i-1] + dp\[i-1]\[j - Wt\[i -1]);  
>          } else {  
>              dp\[i]\[j] = dp\[i-1]\[j];  
>          }
> 
> return dp\[Wt.length]\[sum];
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
>> ```java
>> int min = Integer.MIN_VALUE;
>> for (int i = 0; i < V.size; i++)
>>     min = Math.min( min, range - 2 * V[i]);
>> 
>> return min;
>> ```

#### 1.E. Count of Subset with a given diff - Find how many possible subsets has given diff among them
#### Differences:
> * Get whole array sum - arrTotalSum  
> * Find target Sum = (Sum + diff)/2
> * Call _**SubsetSum(arr[], target Sum)**_  

#### 1.F. Target Sum - Change sign to + and - and find count of subset having diff of 'sum'
#### Differences:
> * This is exactly same as Count of subset sum problem  

