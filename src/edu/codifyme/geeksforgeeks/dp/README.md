# **DP Cheat Sheet**

## _**1. 0/1 Knapsack problem**_
### Source Code:
> * Given Wt[], Val[], Sum, create dp\[Wt.length+1]\[Sum+1]
> * Initialize 0th Row as 0
> * Initialize 0th column as 0
> * DP code section
> 
>> Loop: i => 1 to Wt.length  
>> Loop: j => 1 to sum
>> ```java  
>>      if ( Wt[i-1] <= j ) {  
>>          dp\[i]\[j] = Max(dp\[i-1]\[j], val\[i-1] + dp\[i-1]\[j - Wt\[i -1]);  
>>      } else {  
>>          dp\[i]\[j] = dp\[i-1]\[j];  
>>      }
> ```
>> Loop: i => 1 to Wt.length  
>> &nbsp;&nbsp;Loop: j => 1 to sum  
>> &nbsp;&nbsp;&nbsp;&nbsp;if ( Wt\[i-1] <= j ) {  
>> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;dp\[i]\[j] = Max(dp\[i-1]\[j], val\[i-1] + dp\[i-1]\[j - Wt\[i -1]);  
>> &nbsp;&nbsp;&nbsp;&nbsp;} else {  
>> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;dp\[i]\[j] = dp\[i-1]\[j];  
>> &nbsp;&nbsp;&nbsp;&nbsp;}
> * Return dp\[Wt.length]\[sum]

### Variations:

#### 1.A. Subset Sum Problem - Find a subset whose sum is given
Differences:
> * Init 1st row as _**False**_, 1st col as _**True**_; 
> * Treat only array as Wt arr and target sum as sum
> * Instead of Max, use _**OR**_ on boolean returns

#### 1.B. Equal Sum Partition Problem - Find two subsets whose sum is equal
Differences:
> * Get whole array sum - arrTotalSum
> * Call _**SubsetSum(arr[], arrTotalSum/2)**_

#### 1.A. Subset Sum Problem - Find a subset whose sum is given
Differences:
> * Init 1st row as _**False**_, 1st col as _**True**_; 
> * Treat only array as Wt arr and target sum as sum
> * Instead of Max, use _**OR**_ on boolean returns
