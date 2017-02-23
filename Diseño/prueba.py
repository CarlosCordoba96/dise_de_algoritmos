#!/usr/bin/python
import string
def recursivo(a,b, error):
	vm=(a+b)/2

def ev(n):
	eq="(X*X*X)+X-1"
	eq=eq.replace('X',str(n))
	return eval(eq)

def recur(a,b,err):
	vm=(a+b)/2
	sol=0
	fa=ev(a)
	fb=ev(b)
	fc=ev(vm)
	if(abs(fc)<=err):
		sol=vm
	else:
		if(fc*fa>0):
			sol=recur(vm,b,err)
		else:
			sol=recur(a,vm,err)
	return sol
	
	
	

def main():
	error=10e-4;
	print(recur(0,1,error))
	print(ev(1))


if __name__ == "__main__":
    main()

