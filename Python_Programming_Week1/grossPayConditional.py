def calculatePay(h,r):
    if(h<=40):
        result=h*r
    else:
        result=40*r+1.5*(h-40)*r
    return result

hrs =float(input("Enter Hours:"))
rate=float(input("Enter Rate:"))
pay=calculatePay(hrs,rate)
print('Pay',pay)