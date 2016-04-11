__author__ = 'urajkuma@ucsd.edu,A91060509,yil261@ucsd.edu,PID,L1Kong@ucsd.edu,PID'

# library importing
import sys # for arugment parsing

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

def swap(l,i,num):
# input: (list l,index i,num)
# change the ith element of l to num

    tmp=[x for x in l]
    

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
    

def printPath(visit,num):
# input: visit list, ending number
# print the path from x->y

    N = len(num)
    path=""
    while visit[int(num)][0] != 0:
        path = path + " " + str(num).zfill(N)[::-1]
        num = str(visit[int(num)][0])
    path = path+" " + str(num).zfill(N)[::-1]
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

    N=len(p1)
    for depth in xrange(6):
        visit = [(x,depth) for x in xrange(10**N)]
        visit[int(p1)]=(0,0)
        stack=[(p1,0)]
        while len(stack) != 0:
            node, d = stack.pop()
            if node == p2:
                return printPath(visit,p2)
            if d == depth:
                continue
            for child in getPossibleActions(node):
                tmp = int(child)
                if (visit[tmp][0] == tmp) or (d < visit[tmp][1]):
                    visit[tmp] = (int(node),d+1)
                    stack+=[(child,d+1)]
    return "UNSOLVABLE"

def main() :
    argv=str(sys.stdin.readline()).split()
    print(getPath(argv[0],argv[1]))


if __name__ == '__main__':
    main()
