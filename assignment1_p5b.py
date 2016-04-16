#!/usr/bin/python

__author__ = 'urajkuma@ucsd.edu,A91060509,yil261@ucsd.edu,A91085115,L1Kong@ucsd.edu,A97010449'

# library importing
import sys # for arugment parsing
import heapq
import itertools

class FrontierRunner:
    def __init__(self):
        self.elements = []
    
    def empty(self):
        return len(self.elements) == 0
    
    def put(self, item, priority):
        heapq.heappush(self.elements, (priority, item))
    
    def get(self):
        return heapq.heappop(self.elements)[1]
            
def getPossibleActions(num):
    # input: <str> num
    # return a list of <str> num' which are primes and
    # differ from num by exactly one digit
    # prime[]: prime number lookup table to check if prime[i] is prime

    N = len(num)
    old = num2digits(num)
    l=[]
    for digit in xrange(N):
        save_digit = old[digit]
        for i in xrange(10):
            if save_digit == i:
                continue
            old[digit] = i
            new = digits2num(old)
            # no leading zero
            if str(int(new)) == new:
                if prime[int(new)]:
                    l+=[new]
        old[digit] = save_digit
    return l            
    

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
                     

def printPath(came_from, start, goal):
    if goal not in came_from:
        return 'UNSOLVABLE'
    current = goal
    path = [current]
    while current != start:
        current = came_from[current]
        path.append(current)
    path.reverse()
    return " ".join(path)

def heuristic(str1, str2):
    return 1.5*sum(itertools.imap(str.__ne__, str1, str2))

def getPath(start, goal):
    N=len(start)
    global prime
    prime = sieve(10**N)
    came_from_map = {}
    cost_so_far_map = {}
    frontier_queue = FrontierRunner()
    if not prime[int(start)]:
        return came_from_map
    frontier_queue.put(start, 0)
    came_from_map[start] = None
    cost_so_far_map[start] = 0
    
    while not frontier_queue.empty():
        current = frontier_queue.get()
        if current == goal:
            break
        
        for element in getPossibleActions(current):
            new_cost = cost_so_far_map[current] + 1
            if element not in cost_so_far_map or new_cost < cost_so_far_map[element]:
                cost_so_far_map[element] = new_cost
                priority = new_cost + heuristic(goal,element)
                frontier_queue.put(element, priority)
                came_from_map[element] = current
    return came_from_map

def main() :
    argv=str(sys.stdin.readline()).split()
    came_from = getPath(argv[0],argv[1])
    print(printPath(came_from, argv[0],argv[1]))

if __name__ == '__main__':
    main()


