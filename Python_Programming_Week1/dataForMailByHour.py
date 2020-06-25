name = input("Enter file:")
if len(name) < 1 : name = "mbox-short.txt"
handle = open(name)
hourDict=dict()
for line in handle:
    if 'From ' in line:
        parts = line.split()
        currTime=parts[5]
        currHour=currTime[:2]
        hourDict[currHour]=hourDict.get(currHour,0)+1
hourList=list()
for k,v in hourDict.items():
	hourList.append((k,v))
hourList.sort()
for k,v in hourList:
	print(k,v)