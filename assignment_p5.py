import heapq

class PriorityQueue:
	def __init__(self):
		self.elements = []
	
	def empty(self):
		return len(self.elements) == 0
	
	def put(self, item, priority):
		heapq.heappush(self.elements, (priority, item))
	
	def get(self):
		return heapq.heappop(self.elements)[1]

def numDigits(num):
	return len(str(num))

def arrToNum(digits):
	temp = 0
	x = 0
	while x < len(digits):
		temp = temp*10 + digits[x]
		x+=1
	return temp
	
def arr(digits, num):
	x = numDigits(num) - 1
	while num:
		digits[x] = num%10
		num = num/10
		x -= 1
		
def sieve(limit):
	prime = [True]*(limit+1) # create limit+1 list because it's '0' starting
	prime[0] = False
	prime[1] = False
	for i in xrange(2,limit+1): # Search through all number up till this number, using "xrange" because it's python 2
	    if prime[i] == True: # if this number hasn't been marked as False, then it's a prime #
		    for j in xrange(i+i, limit+1, i): # Find all multiples of this prime #
			    prime[j] = False # mark off unprime #
	return prime

		
def hamming_dist_heuristic(curr_digit, goal_digits):
	diff = 0
	curr_digits = [None]*numDigits(curr_digit)
	arr(curr_digits, curr_digit)
	for i in range(0, numDigits(curr_digit)):
		if (curr_digits[i] != goal_digits[i]):
			diff +=1
	return diff
	
def a_star_search(graph, start, goal):
		
print(numDigits(123))
digits = [None]*numDigits(123)
arr(digits, 123)
print(digits)
print(arrToNum(digits))
print(hamming_dist_heuristic(101, digits))

million = 1000000
prime = sieve(million)

