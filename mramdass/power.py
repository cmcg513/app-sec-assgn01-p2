# -*- coding: utf-8 -*-
"""
    Munieshwar (Kevin) Ramdass
    Professor Justin Cappos
    CS-UY 4753
    14 September 2015

    Prints the of 2 from 1 to 128
"""

def print_powers(base, start, end):
	
	def power(base_, exponent_):
		result = base_
		for i in range(1, exponent_):
			result *= base_
		return result

	for i in range(start, end + 1):
		print (power(base, i))


print_powers(2, 1, 128)
