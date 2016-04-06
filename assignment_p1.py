__author__ = 'urajkuma@ucsd.edu,A91060509,yil261@ucsd.edu,PID,L1Kong@ucsd.edu,PID'

from Queue import Queue

million = 1000000
prime = [True]*million

def sieve():
	for i in range(2, 1000, 1):
		if prime[i] == False:
			for j in range(i*2, million, j = j+i):
				prime[j] = True
    
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
    a,b = map(int, raw_input().split())
    size = numDigits(a)
    digits = [None]*size
    primes = Queue()
    distance[a] = 0
    primes.put(a)
    parent[a] = 0;
    threshold = exponents(size)
    while primes.empty() == False:
    	num = primes.get()
    	for x in range((size-1), -1, -1):
    		arr(digits, num)
    		for i in range(0, 10):
    			digits[x] = i
    			temp = arrToNum(digits, size)
    			if (prime[temp] == False and distance[temp] == -1 and temp >= threshold):
    				distance[temp] = distance[num] + 1
    				parent[temp] = num
    				primes.put(temp)
	
	if(distance[b] == -1):    
		print 'UNSOLVABLE'

	else:
		
		while parent[b] != 0:
			print b, " "
			b = parent[b]


if __name__ == '__main__':
    main()
