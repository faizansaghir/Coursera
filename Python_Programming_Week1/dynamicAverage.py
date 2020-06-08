currTot=0;
currCount=0;
while True:
    noStr=input("Enter a number:")
    if noStr=='done':
        break
    try:
        no=float(noStr)
    except:
        print("Invalid Input")
        continue
    currTot+=no;
    currCount+=1
print("Average value:",(currTot/currCount))