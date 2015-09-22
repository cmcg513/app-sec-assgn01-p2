# -*- coding: utf-8 -*-
"""
    Munieshwar (Kevin) Ramdass
    Professor Justin Cappos
    CS-UY 4753
    14 September 2015

    The following is a very simple sandbox that only allows programs
    that perform computation. It restricts the user's choice of language
    and modules and keywords to work with.

    User is expected to use Python version 3.3 or above.
    User is expected to write computable functions only.
"""

from sys import version_info, argv, platform

# if platform == "linux" and version_info > (3, 2):	# Force user to use Python 3.3 or 3.4 or 3.5 on Linux
#     pass
# else:
#     raise Exception(str(platform) + " Pythonv" + str(version_info.major) + "." + str(version_info.minor) + " not supported")

import fcntl

def __execute():
    """Executes user's program with limited keywords"""
    
    UNTRUSTED = ("import", "open", "write", "file", "sandbox.py", "sandbox", "class", ">>", "<<", "assert", "slice", "yield", "gloabl", "@", "builtin", "exec", "sys", "lambda", "struct", "mem", "memory")

    sandbox = argv[0]
    litter_lock = open(sandbox, 'r+')
    fcntl.flock(litter_lock, fcntl.LOCK_EX | fcntl.LOCK_NB)
    litter = None
    
    if sandbox == "sandbox.py":
        pass
    else:
        raise Exception("Name of sandbox changed")

    if len(argv) == 2:
        pass
    else:
        raise Exception(str(len(argv)) + " arguments passed - only 2 arguments needed")
    
    try:
        program = argv[1]
        litter = open(program, 'rb+')
        litter_bytes = litter.read()
        
        for i in UNTRUSTED:
            if i in str(litter_bytes):
                raise Exception("Found untrusted string in program: " + str(i))
        
        litterbox = {'__execute': __execute, 'None': None, 'builtins': None}
        exec (litter_bytes) in litterbox
    except Exception:
        raise Exception("Program not executed")
    finally:
        if litter != None:
            litter.close()
        fcntl.flock(litter_lock, fcntl.LOCK_UN)

__execute()
