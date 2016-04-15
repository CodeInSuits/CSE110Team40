__author__ = 'urajkuma@ucsd.edu,A91060509,yil261@ucsd.edu,PID,L1Kong@ucsd.edu,PID'

# library importing
import sys # for arugment parsing
from time import time


def sieve(limit):
# Sieve of Eratosthenes method for prime number list
# Create a list where the index is the number we want to if it's prime
# First mark 0 and 1 as False, and everything else True
# Then from 2 to limit, for each True value, mark off all its multiple indices as False
# Return the list

    tmp = [True] * limit
    tmp[0] = False
    tmp[1] = False
    for i in xrange(2,limit):
        if tmp[i]:
            for j in xrange(i+i, limit, i):
                tmp[j] = False
    return tmp

def num2digits(num):
# input: string form of number
# return the list which contains every digits of the given number
# least significant digits are stored in the highest index

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
    l=[]
    for digit in xrange(N):
        save_digit = old[digit]
        for i in xrange(10):
            # skip same bit
            if save_digit == i:
                continue
            old[digit] = i
            new = digits2num(old)
            # no leading zero
            if new[0] != '0':
                if prime[int(new)]:
                    l+=[new]
        old[digit] = save_digit
    return l             
    

def printPath(visit,num):
# input: visit list, ending number
# print the path from x->y

    N = len(num)
    path=num[::-1]
    prev = visit[num][0]
    while prev:
        path = path + " " + prev[::-1]
        prev = visit[prev][0]
    return path[::-1].strip()


def getPath (p1,p2):
# input: <str> p1 and <str> p2
# return the path between two primes
# using DLS with max-depth=8 and root-depth=0
# return "UNSOLVABLE" if not path exist
# visit[]: visited state lookup table to check if prime[i] is visited
#           as well as storing path information
#           unvisited entry stores itself
#           visited entry stores its parent

    nodeCount=0
    N=len(p1)
    global prime
    prime = sieve(10**N)
    if not prime [int(p1)]:
        return "UNSOLVABLE"
    if p1 == p2:
        return p2
    DEPTH = 8+1
    for depth in xrange(1,DEPTH):
        visit = {p1:('',0)} # using '' to indicate root node
        stack=[(p1,0)]
        nodeCount+=1
        print nodeCount
        while len(stack) != 0:
            node, d = stack.pop()
            for child in getPossibleActions(node):
                if child == p2:
                    visit[child] = (node,d+1)
                    print "node visited:",nodeCount
                    return printPath(visit,p2)
                if d==depth:
                        continue
                if (visit.get(child)!= None):
                    if (d >= visit[child][1]):
                        continue
                visit[child] = (node,d+1)
                stack+=[(child,d+1)]
                nodeCount+=1
    print "node visited:",nodeCount
    return "UNSOLVABLE"

######################## Alternative ###################3
def PrintPath(visit,num):
    begin=time()
    N = len(num)
    path=num[::-1]
    prev = visit[int(num)][0]
    while prev:
        path = path + " " + prev[::-1]
        prev = visit[int(prev)][0]
    #path = path+" " + prev.zfill(N)[::-1]
    end=time()
    print "print time:",end-begin
    return path[::-1].strip()

def GetPath(p1,p2):
    begin=time()
    N=len(p1)
    global prime
    prime = sieve(10**N)
    if not prime [int(p1)]:
        return "UNSOLVABLE"
    if p1 == p2:
        return p2
    DEPTH = 8+1
    for depth in xrange(1,DEPTH):
        visit = [(None,0)]*10**N # using '' to indicate root node
        visit[int(p1)]=('',0)
        stack=[(p1,0)]
        while len(stack) != 0:
            node, d = stack.pop()
            for child in getPossibleActions(node):
                tmp = int(child)
                if child == p2:
                    visit[tmp] = (node,d+1)
                    end=time()
                    print "find:",end-begin
                    return printPath(visit,p2)
                if (visit[tmp][0]!= None and d >= visit[tmp][1]) or d==depth-1:
                        continue
                visit[tmp] = (node,d+1)
                stack+=[(child,d+1)]
    end=time()
    print "find:",end-begin
    return "UNSOLVABLE"
########################################################

def main() :
    argv=str(sys.stdin.readline()).split()
    begin=time()
    path=getPath(argv[0],argv[1])
    end=time()
    print(path)
    print "time:",end-begin


if __name__ == '__main__':
    main()
