__author__ = 'urajkuma@ucsd.edu,A91060509,yil261@ucsd.edu,A91085115,L1Kong@ucsd.edu,A97010449'
import sys
from Queue import Queue

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

def getPath(a,b):
    size = numDigits(a)
    N = 10**size
    global prime
    prime = sieve(N)
    digits = [None]*size
    threshold = exponents(size)
    parent = [None]*N
    visited = [None]*N # changed your distance[] to visited[]
    parent[a] = None;
    visited[a] = True
    
    primes = Queue()
    if not prime[a]:
        print "UNSOLVABLE"
        return
    if a == b:
        print a
        return
    primes.put(a)
    while (primes.empty() != True):
        num = primes.get()
        for x in xrange(size):
            arr(digits, num)
            for i in xrange(0, 10):
                digits[x] = i
                temp = arrToNum(digits, size)
                if  temp < threshold or not prime[temp]:
                    continue
                if (temp == b):
                    parent[temp]=num
                    output=str(b)
                    tmp=parent[b]
                    while tmp:
                        output = str(tmp)+" "+output
                        tmp = parent[tmp]
                    print output
                    return
                if not visited[temp] :
                    visited[temp] = True
                    parent[temp] = num
                    primes.put(temp)

    # if the function doesn't return in while
    # then no path exists
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
