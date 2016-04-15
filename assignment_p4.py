__author__ = 'urajkuma@ucsd.edu,A91060509,yil261@ucsd.edu,A91085115,L1Kong@ucsd.edu,A97010449'

from Queue import Queue
from sets import Set
from time import time

def sieve(limit):
    tmp = [True] * limit
    tmp[0] = False
    tmp[1] = False
    for i in xrange(2,limit):
        if tmp[i]:
            for j in xrange(i+i, limit, i):
                tmp[j] = False
    return tmp

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


def printPath(parent,child,num):
    left = ""
    right = ""
    a=parent[num]
    b=child[num]
    while a:
        left = str(a) + " " + left
        a = parent[a]
    while b:
        right += " "+str(b)
        b = child[b]
    print left+"(%s)"%num+right    

def main():
    nodeCount = 0
    a,b = map(int, raw_input().split())
    begin = time()
    size = numDigits(a)
    size2 = numDigits(b)
    N = 10**size
    global prime
    prime = sieve(N)
    digits = [None]*size
    digits2 = [None]*size2
    threshold = exponents(size)
    threshold2 = exponents(size2)
    parent = [None]*N
    visited = [None]*N
    visited2 = [None]*N
    child = [None]*N
    parent[a] = None
    child[b] = None
    visited[a] = True   
    visited2[b] = True

    set1 = Set();
    set2 = Set();

    primes = Queue()
    primes2 = Queue()
    if(not prime[a] or not prime[b]):
        end=time()
        print "node visit:",nodeCount
        print "UNSOLVABLE"
        print "time spent:",end-begin
        return
    if (a == b):
        end=time()
        print "node visit: 2"
        print a
        print "time spent:",end-begin
        return
    primes.put(a)
    set1.add(a)
    nodeCount+=1
    primes2.put(b)
    set2.add(b)
    nodeCount+=1
    
    while (not primes.empty() and not primes2.empty()):
        num = primes.get()
        num2 = primes2.get()
        for x in range(size):
            arr(digits, num)
            for i in range(0, 10):
                digits[x] = i
                temp = arrToNum(digits, size)
                if temp < threshold:
                    continue
                if temp in set2:
                    parent[temp]=num
                    end=time()
                    print "node visit:",nodeCount
                    printPath(parent,child,temp)
                    print "time spent:",end-begin
                    return
                if prime[temp] and not visited[temp]:
                    visited[temp] = True
                    parent[temp] = num
                    primes.put(temp)
                    set1.add(temp)
                    nodeCount+=1

        for x in range(size2):
            arr(digits2, num2)
            for i in range(0, 10):
                digits2[x] = i
                temp = arrToNum(digits2, size2)
                if temp < threshold2:
                    continue
                if temp in set1:
                    child[temp]=num2
                    end=time()
                    print "node visit:",nodeCount
                    printPath(parent,child,temp)
                    print "time spent:",end-begin
                    return
                if prime[temp] and not visited2[temp]:
                    visited2[temp] = True
                    child[temp] = num2
                    primes2.put(temp)
                    set2.add(temp)
                    nodeCount+=1

    end=time()
    print "node visit:",nodeCount
    print "UNSOLVABLE"
    print "time spent:",end-begin
    return


if __name__ == '__main__':
    main()
