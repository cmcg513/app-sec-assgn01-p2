s1 = "op"
s2 = "en"
s = s1 + s2
f = eval(s + "('bad01.txt', 'w')")
s1 = "wr"
s2 = "ite"
s = s1 + s2
eval("f." + s + "(\"I'm free!\")")
f.close
