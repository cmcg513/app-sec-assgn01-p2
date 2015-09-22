import resource
import sys
import pip
import argparse
from operations import Operations
from security import Security


class Restrictions:
	def load(self):
		self.restrictImport()
		self.restrictResources()


	def restrictImport(self):
		sys.modules['subprocess'] = None
		sys.modules['os'] = None
		sys.modules['sys'] = None
		#eval

		for name in pip.get_installed_distributions():
			sys.modules[str(name).split(" ")[0]] = None

	def restrictResources(self):
		resource.setrlimit(resource.RLIMIT_AS, (1024000000, 10240000000000))
		resource.setrlimit(resource.RLIMIT_NPROC, (1,1))
		resource.setrlimit(resource.RLIMIT_CPU, (1,1))
		resource.setrlimit(resource.RLIMIT_NOFILE, (4,4))
	

if __name__ == "__main__":
	
	ob_restrictions = Restrictions()
	ob_restrictions.load()
	print "\n\nRestrictions Loaded !\n"
	try:
		ob_operations = Operations()
		print "Computing: Fibonacci"
		ob_operations.computeFibonacci(10)
		print "computing: Power of 2"
		ob_operations.computePower(128)
	except IOError:
		print "Exception: Too Many Files Open"
	except MemoryError:
		print "Exception: Memory Usage"
	except:
		print "Exception: ", sys.exc_info()[0]

	print "\nLoading Exploit Program\n"

	try:
		ob_security = Security()

		parser = argparse.ArgumentParser()
		parser.add_argument('--file', '-f')
		args = parser.parse_args()

		if args.file:
			if ob_security.filepath(args.file):
				print "check: file path: pass"
				if ob_security.fileSize(args.file):
					print "check: file size: pass"
					if ob_security.code(args.file):
						print "check: source code: pass"
						print "\nExploit Code Execution & Output"
						if ob_security.execute(args.file):
							print "\ncheck: code execute: pass"
						else:
							print "check: code execute: fail"
					else:
						print "check: source code: fail"
				else:
					print "check: file size: fail"
			else:
				print "check: file path: fail"
		else:
			print "Note: to execute exploit program, give exploit script with -f option"
	except:
		print "Exception: ", sys.exc_info()[0]


	
	