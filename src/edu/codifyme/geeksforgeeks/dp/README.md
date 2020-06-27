# **DP Cheat Sheet**

## _**1. 0/1 Knapsack problem**_
### Source Code:
> * Given Wt[], Val[], Sum, create dp\[Wt.length+1]\[Sum+1]
> * Initialize 0th Row as 0
> * Initialize 0th column as 0
> * DP code section
>>  Loop: i => 1 to Wt.length 
>>      Loop: j => 1 to sum
>>          if ( Wt\[i-1] <= j ) {
>>              dp\[i]\[j] = Max(dp\[i-1]\[j], val\[i-1] + dp\[i-1]\[j - Wt\[i -1]);
>>          } else {
>>              dp\[i]\[j] = dp\[i-1]\[j];
>>          }
> * Return dp\[Wt.length]\[sum]

### Variations:

#### 1.A. Subset Sum Problem - Find a subset whose sum is given
Differences:
> * Init 1st row as F, 1st col as T; 
> * Treat only array as Wt arr and target sum as sum
> * Instead of Max, use Or on boolean returns

#### 1.A. Equal Sum Partition Problem - Find two subsets whose sum is equal