# -*- coding: utf-8 -*-
"""
    Munieshwar (Kevin) Ramdass
    Professor Justin Cappos
    CS-UY 4753
    14 September 2015

    Compute up to the ith Fibonacci number
"""

def fib(ith):                   # BAD IMPLEMENTATION
    if ith == 0:
        return 0
    elif ith == 1:
        return 1
    else:
        return fib(ith - 1) + fib(ith - 2)

def fast_fib(ith, _cache={}):   # FASTER IMPLEMENTATION
    if ith in _cache:
        return _cache[ith]
    elif ith > 1:
        return _cache.setdefault(ith, fast_fib(ith - 1) + fast_fib(ith - 2))
    return ith

def print_fib(the_range):
    """
    This function will print the Fibonacci numbers up to a certain point.
    This function uses the bad implementation above.
    """
    def fib(ith):                   # BAD IMPLEMENTATION
        if ith == 0:
            return 0
        elif ith == 1:
            return 1
        else:
            return fib(ith - 1) + fib(ith - 2)
    
    for i in range(0, the_range + 1):
        print (str(fib(i)))

def print_fast_fib(the_range):
    """
    This function will print the Fibonacci numbers up to a certain point.
    This function uses the faster implementation above.
    """
    def fast_fib(ith, _cache={}):   # FASTER IMPLEMENTATION
        if ith in _cache:
            return _cache[ith]
        elif ith > 1:
            return _cache.setdefault(ith, fast_fib(ith - 1) + fast_fib(ith - 2))
        return ith
    
    for i in range(0, the_range + 1):
        print (str(fast_fib(i)))

print_fib(16)
print_fast_fib(128)
