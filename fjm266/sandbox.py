import sys

if len(sys.argv)>2:
    code = open(sys.argv[1],'rb').read()
    data = open(sys.argv[2], 'rb').read()
elif len(sys.argv)==2:
    code = open(sys.argv[1],'rb').read()
    data = open('data.in', 'rb').read()
elif len(sys.argv)<2:
    code = open('code.in','rb').read()
    data = open('data.in', 'rb').read()


namespace = {}
namespace['__builtins__'] = {'True':True, 'False':False, 'None':None, 'data':data,'dir':dir,'locals':locals}

exec code in namespace
