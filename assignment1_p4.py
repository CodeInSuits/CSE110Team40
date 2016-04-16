__author__ = 'urajkuma@ucsd.edu,A91060509,yil261@ucsd.edu,A91085115,L1Kong@ucsd.edu,A97010449'

import sys
from Queue import Queue
from sets import Set

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
        temp = temp*10 + int(digits[x])
        x+=1

    return temp

def arr(digits, num):
    x = numDigits(num) - 1
    while num:
        digits[x] = str(num%10)
        num = num/10
        x -= 1

def exponents(size):
    threshold = 1
    while size > 1:
        threshold *= 10
        size -= 1

    return threshold


def printPath(parent,child,num):
    left = str(num)
    right = ""
    a=parent[num]
    b=child[num]
    while a:
        left = str(a) + " " + left
        a = parent[a]
    while b:
        right += " "+str(b)
        b = child[b]
    print left+right    

def getPath(a,b):
    size = numDigits(a)
    size2 = numDigits(b)
    N1 = 10**size
    N2 = 10**size2
    global prime
    prime = sieve(max(N1,N2))
    digits = [None]*size
    digits2 = [None]*size2
    threshold = exponents(size)
    threshold2 = exponents(size2)
    parent = [None]*N1
    visited = [None]*N1
    visited2 = [None]*N2
    child = [None]*N2
    parent[a] = None
    child[b] = None
    visited[a] = True   
    visited2[b] = True

    set1 = Set();
    set2 = Set();

    primes = Queue()
    primes2 = Queue()
    if(not prime[a] or not prime[b]):
        print "UNSOLVABLE"
        return
    if (a == b):
        print a,b
        return
    primes.put(a)
    arr(digits, a)
    set1.add("".join(digits))
    primes2.put(b)
    arr(digits2, b)
    set2.add("".join(digits2))
    
    while (not primes.empty() and not primes2.empty()):
        num = primes.get()
        num2 = primes2.get()
        for x in range(size):
            arr(digits, num)
            for i in range(0, 10):
                digits[x] = str(i)
                temp = arrToNum(digits, size)
                if temp < threshold:
                    continue
                stemp="".join(digits)
                if stemp in set2:
                    parent[temp]=num
                    printPath(parent,child,temp)
                    return
                if prime[temp] and not visited[temp]:
                    visited[temp] = True
                    parent[temp] = num
                    primes.put(temp)
                    set1.add(stemp)

        for x in range(size2):
            arr(digits2, num2)
            for i in range(0, 10):
                digits2[x] = str(i)
                temp = arrToNum(digits2, size2)
                if temp < threshold2:
                    continue
                stemp="".join(digits2)
                if stemp in set1:
                    child[temp]=num2
                    printPath(parent,child,temp)
                    return
                if prime[temp] and not visited2[temp]:
                    visited2[temp] = True
                    child[temp] = num2
                    primes2.put(temp)
                    set2.add(stemp)

    print "UNSOLVABLE"
    return

def main():
    while True:
        argv=str(sys.stdin.readline()).split()
        if not argv:
            break
        getPath(int(argv[0]),int(argv[1]))


if __name__ == '__main__':
    main()
