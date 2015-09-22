# -*- coding: utf-8 -*-
"""
    Munieshwar (Kevin) Ramdass
    Professor Justin Cappos
    CS-UY 4753
    14 September 2015

    Compute permutations and combinations
    Combinatorics
"""

def factorial(n):
	    result = 1
	    for i in range(1, n + 1):
	        result *= i
	    return result

def combination(n, r):
    """
    Calculates the numbers of combinations possible
    nCr = n! / ((n - r)! * r!)
    """
    def factorial(n_):
	    result = 1
	    for i in range(1, n_ + 1):
	        result *= i
	    return result
    return factorial(n) / (factorial(n - r) * factorial(r))

def permutation(n, r):
    """
    Calculates the numbers of permutations possible
    nPr = n! / (n - r)!
    """
    def factorial(n_):
	    result = 1
	    for i in range(1, n_ + 1):
	        result *= i
	    return result
    return factorial(n) / factorial(n - r)

print (factorial(10))
print (combination(10, 5))
print (permutation(10, 5))
