//implementation of the assignment in c++ for reference
#include <bits/stdc++.h>
using namespace std;
static int million = 100000;
bool prime[100000]; //list of primes up to 1,000,000

//create list of primes up to 1,000,000
void sieve()
{
	//indices that are primes will stay as false
    for(int i=2; i*i< million; i++)
    {
        if(!prime[i])
        {
            for(int j=i*2; j< million; j += i)
                prime[j] = true; 
        }
    }
}

//number of digits in a number
int numDigits(int number)
{
    int digits = 0;
    while (number) {
        number /= 10;
        digits++;
    }
    return digits;
}

//conver array back to number
int arrToNum(int digits[], int size)
{
    int temp = 0, x = 0;
    while(x < size)
        temp = temp*10 + digits[x++];

    return temp;
}

void arr(int digits[], int num)
{
    int x = numDigits(num)-1;
    while(num)
    {
        digits[x--] = num%10;
        num/=10;
    }
}

int exponents(int size)
{
	int threshold = 1;

	while(size-- > 1)
		threshold *= 10;

	return threshold;
}

int main()
{
	sieve(); //create the list of primes
	int a, b, parent[100000], distance[100000];
	scanf("%d %d", &a, &b);
	int size = numDigits(a);
	int  digits[size];
	memset(distance, -1, sizeof(distance));
    memset(parent, -1, sizeof(parent));
    queue<int> primes;
    distance[a]=0; 
    primes.push(a); //queue for bfs
    int threshold = exponents(size);//to ensure additions and deletions don't occue
    parent[a]=0;
    
    while(!primes.empty()){

    	int num = primes.front();
    	primes.pop();
    	for(int x = (size-1); x >= 0; x--){

    		arr(digits, num);
    		for(int i = 0; i <= 9; i++){

    			digits[x] = i;
    			int temp = arrToNum(digits, size);
    			//last condition is to ensure additions/removals of numbers don't occur
    			if((!prime[temp]) && distance[temp]==-1 && temp >= threshold) 
    			{
    				distance[temp] = distance[num] + 1;
    				parent[temp] = num;
    				primes.push(temp);
    			}
    		}
    	}
    }
    if(distance[b]==-1) 
        cout << "UNSOLVABLE" << endl; 
    else
    {
        while(parent[b] != 0)
        {
            cout << b << " ";
            b = parent[b];
        }
    }
    return 0;

}
