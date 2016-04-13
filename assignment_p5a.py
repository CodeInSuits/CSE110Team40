#!/usr/bin/python

__author__ = 'urajkuma@ucsd.edu,A91060509,yil261@ucsd.edu,PID,L1Kong@ucsd.edu,PID'

# library importing
import sys # for arugment parsing
import heapq
import itertools

class PriorityQueue:
		def __init__(self):
				self.elements = []
		
		def empty(self):
				return len(self.elements) == 0
		
		def put(self, item, priority):
				heapq.heappush(self.elements, (priority, item))
		
		def get(self):
				return heapq.heappop(self.elements)[1]

def sieve(limit):
# Sieve of Eratosthenes method for prime number list
# Create a list where the index is the number we want to if it's prime
# First mark 0 and 1 as False, and everything else True
# Then from 2 to limit, for each True value, mark off all its multiple indices as False
# Return the list
		prime = [True]*(limit)
		prime[0] = False
		prime[1] = False
		for i in xrange(2,limit):
				if prime[i] == True:
						for j in xrange(i+i, limit, i):
								prime[j] = False
		return prime
def num2digits(num):
		l=[]
		for i in num:
				l+=[int(i)]
		return l

def digits2num(l):
				# input: list of digits
				# convert a list of digits to the string form number
				# where least significant digits are stored in the highest index
		num=""
		for i in l:
				num+=str(i)
		return num
						
def getPossibleActions (num):
				# input: <str> num
				# return a list of <str> num' which are primes and
				# differ from num by exactly one digit
				# prime[]: prime number lookup table to check if prime[i] is prime

						N = len(num)
						old = num2digits(num)
						prime = sieve(10**N)
						l=[]
						for digit in xrange(N):
								save_digit = old[digit]
								for i in xrange(10):
										old[digit] = i
										new = digits2num(old)
										if prime[int(new)]:
												l+=[new]
								old[digit] = save_digit
						return l             

def reconstruct_path(came_from, start, goal):
				if goal not in came_from:
					return 'UNSOLVABLE'
				current = goal
				path = [current]
				while current != start:
						current = came_from[current]
						path.append(current)
				path.reverse()
				return path
def heuristic(str1, str2):
	return sum(itertools.imap(str.__ne__, str1, str2))

def a_star_search(start, goal):
				frontier = PriorityQueue()
				frontier.put(start, 0)
				came_from = {}
				cost_so_far = {}
				came_from[start] = None
				cost_so_far[start] = 0
				
				while not frontier.empty():
						current = frontier.get()
						
						if current == goal:
								break
						
						for element in getPossibleActions(str(current)):
								next = int(element)
								new_cost = cost_so_far[current] + 1
								if next not in cost_so_far or new_cost < cost_so_far[next]:
										cost_so_far[next] = new_cost
										priority = new_cost + heuristic(str(goal), str(next))
										frontier.put(next, priority)
										came_from[next] = current
				
				return came_from

def lev(a, b):
	if not a: return len(b)
	if not b: return len(a)
	return min(lev(a[1:], b[1:])+(a[0] != b[0]), lev(a[1:], b)+1, lev(a, b[1:])+1)

print(lev(str(423), str(342)))
print(heuristic(str(423), str(342)))
came_from = a_star_search(100001, 293339)
print(reconstruct_path(came_from, 100001, 293339))
