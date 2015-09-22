
class Operations:
	def computePower(self, num):
		arr = []
		for i in range(1,num+1):
			#utility.get_usage()
			arr.append(2**i)
			print 2**i
			#print arr


	def computeFibonacci(self, num):
		for i in range(1,num+1):
			#utility.get_usage()
			print self.fib(i)


	def fib(self, n):
		if n == 1:
			return 1
		elif n == 0:   
			return 0            
		else:                      
			return self.fib(n-1) + self.fib(n-2)