import re

sum=0
handle=open('actualData.txt')
currNum=list()
for line in handle:
    currNum=re.findall('[0-9]+',line)
    if(currNum is not None):
        for num in currNum:
            sum+=int(num)
print(sum)