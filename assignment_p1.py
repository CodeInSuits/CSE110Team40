__author__ = 'urajkuma@ucsd.edu,A91060509,yil261@ucsd.edu,PID,L1Kong@ucsd.edu,PID'

from Queue import Queue

million = 1000000
prime = [True]*million #Initialize all numbers to be "prime"

def sieve(limit):
    prime = [True]*(limit+1) # create limit+1 list because it's '0' starting
    prime[0] = False
    prime[1] = False
    for i in xrange(2,limit+1): # Search through all number up till this number, using "xrange" because it's python 2
	    if prime[i] == True: # if this number hasn't been marked as False, then it's a prime #
		    for j in xrange(i+i, limit, i): # Find all multiples of this prime #
			    prime[j] = False # mark off unprime #
    return prime
    
def numDigits(num):
	return len(str(num))

def arrToNum(digits, size):
	temp = 0
	x = 0
	while x < size:
		temp = temp*10 + digits[x]
		x+=1

	return temp

def arr(digits, num):
	x = numDigits(num) - 1
	while num:
		digits[x] = num%10
		num = num/10
		x -= 1

def exponents(size):
	threshold = 1
	while size > 1:
		threshold *= 10
		size -= 1

	return threshold




def main():
    sieve()
    parent = [-1]*100000
    distance = [-1]*100000
    b,a = map(int, raw_input().split())
    size = numDigits(a)
    digits = [None]*size
    primes = Queue()
    distance[a] = 0
    primes.put(a)
    parent[a] = 0;
    threshold = exponents(size)
    while (primes.empty() != True):
    	num = primes.get()
    	for x in range((size-1), -1, -1):
    		arr(digits, num)
    		for i in range(0, 10):
    			digits[x] = i
    			temp = arrToNum(digits, size)
    			if ((prime[temp] == 1) and (distance[temp] == -1) and (temp >= threshold)):
    				distance[temp] = distance[num] + 1
    				parent[temp] = num
    				primes.put(temp)
    if(parent[b] != -1):
        while (parent[b] != 0) and (parent[b] != -1):
            print b, " "
            b = parent[b]
    else:
        print 'UNSOLVABLE'

if __name__ == '__main__':
    main()

