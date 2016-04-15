__author__ = 'urajkuma@ucsd.edu,A91060509,yil261@ucsd.edu,A91085115,L1Kong@ucsd.edu,A97010449'

from Queue import Queue
from sets import Set

million = 1000000
prime = [False]*million

def sieve():
    for i in range(2, 1000, 1):
        if prime[i] == False:
            for j in range(i*2, million, i):
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
    b,a = map(int, raw_input().split())
    size = numDigits(a)
    size2 = numDigits(b)
    digits = [None]*size
    digits2 = [None]*size2
    primes = Queue()
    primes2 = Queue()
    primes.put(a)
    primes2.put(b)
    threshold = exponents(size)
    threshold2 = exponents(size2)
    sieve()
    parent = [-1]*million
    distance = [-1]*million
    distance2 = [-1]*million
    child = [-1]*million
    parent[a] = 0
    child[b] = 0
    distance[a] = 0   
    distance2[b] = 0

    set1 = Set();
    set2 = Set();

    while (primes.empty() == False):
        num = primes.get()
        num2 = primes2.get()

#       if((num in set2) or (num2 in set1)):
#           print "hi"
        for x in range((size-1), -1, -1):
            arr(digits, num)
            for i in range(0, 10):
                digits[x] = i
                temp = arrToNum(digits, size)
                if ((prime[temp] == False) and (distance[temp] == -1) and (temp >= threshold)):
                    distance[temp] = distance[num] + 1
                    parent[temp] = num
                    primes.put(temp)
                    set1.add(temp)

            for i in range((size2-1), -1, -1):
                arr(digits2, num2)
                for i in range(0, 10):
                    digits2[x] = i
                    temp = arrToNum(digits2, size2)
                    if((prime[temp] == False) and (distance2[temp] == -1) and temp >= threshold2):
                        distance2[temp] = distance2[num2] + 1
                        child[temp] = num2
                        primes2.put(temp)
                        set2.add(temp)


    if(distance[b] == -1):
        print "UNSOLVABLE"
    else:
        while(parent[b] != False):
            print b,
            b = parent[b]
        print a

    if(distance2[a] == -1):
        print "UNSOLVABLE"
    else:
        while(child[a] != False):
            print a,
            a = child[a]
        print a

if __name__ == '__main__':
    main()
