no1=int(input('Enter First Number:'))
no2=int(input('Enter First Number:'))
res=no1+no2
resString1=f'fString:sum of {no1} and {no2} is {res}'
resString2='Normal String with named parameters:sum of {n1} and {n2} is {r}'
resString3='Normal String with unnamed parameters:sum of {} and {} is {}'
print('Normal String:sum of '+str(no1)+' and '+str(no2)+' is '+str(res))
print(resString1)
print(resString2.format(n1=no1,n2=no2,r=res))
print(resString3.format(no1,no2,res))