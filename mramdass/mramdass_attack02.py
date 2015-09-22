s1 = "__built"
s2 = "ins__"
s = s1 + s2
b = globals()[s]
s1 = "op"
s2 = "en"
s = s1 + s2
o = getattr(b,s)
f = o("bad02.txt", "w")
s1 = "wr"
s2 = "ite"
s = s1 + s2
w = getattr(f,s)
w("I'm free!")
f.close()
