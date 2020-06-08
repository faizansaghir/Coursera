def largest(listVar):
    maxSoFar=None
    for i in listVar:
        if maxSoFar is None:
            maxSoFar=i
        elif(i>maxSoFar):
            maxSoFar=i
    return maxSoFar

def smallest(listVar):
    minSoFar=None
    for i in listVar:
        if minSoFar is None:
            minSoFar=i
        elif(i<minSoFar):
            minSoFar=i
    return minSoFar

def numberOfElements(listVar):
    no=0;
    for i in listVar:
        no+=1
    return no

def totalSum(listVar):
    no=0;
    for i in listVar:
        no+=i
    return no

def average(listVar):
    sumElements=totalSum(listVar)
    noOfElements=numberOfElements(listVar)
    avg=sumElements/noOfElements
    return avg

def greaterThan(val,listVar):
    newListVar=[]
    for i in listVar:
        if i>val:
            newListVar.append(i)
    return newListVar
    
def isPresent(val,listVar):
    present=False
    for i in listVar:
        if(i==val):
            present=True
            break
    return present
        
numbers=[22,5,29,7,13,26]
maxNo=largest(numbers)
minNo=smallest(numbers)
elemnetNo=numberOfElements(numbers)
sumOfElements=totalSum(numbers)
avg=average(numbers)
value=13
newNumbers=greaterThan(value,numbers)
searchForVal=isPresent(value,numbers)
print('Largest number',maxNo)
print('Smallest number',minNo)
print('Number of elements',elemnetNo)
print('Sum of elements',sumOfElements)
print('Average of elements',avg)
print(f'Numbers greater than {value} are',newNumbers)
if searchForVal:
    print(f'Numbers {value} is present')
else:
    print(f'Numbers {value} is not present')