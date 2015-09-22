import os
import sys

class Security:

	def fileSize(self, fpath):
		if os.path.getsize(fpath) > 10240:
			return False
		else:
			return True

	def filepath(self, fpath):
		if "/" in fpath:
			return False
		else:
			return True

	def code(self, fpath):
		blacklist = ["eval", "exec", "import", ";", "open", "write", "__import__", "__", "operations", "restrtictions", "security", "call", "bool", "raw_input", "system", "Call", "globals"]
		fopen = open(fpath)
		for line in fopen.readlines():
			if line[0] != "#":
				for blacklist_item in blacklist:
					if blacklist_item in line:
						return False
		return True


	def execute(self, fpath):
		try:
			fopen = open(fpath)
			fread = fopen.read()
			exec(fread)
			return True
		except:
			print "Exception: ", sys.exc_info()[0]
			return False
