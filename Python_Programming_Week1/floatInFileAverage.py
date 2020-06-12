fname = input("Enter file name: ")
fh = open(fname)
count=0
fVal=0
for line in fh:
    if not line.startswith("X-DSPAM-Confidence:") : continue
    count+=1
    index=line.find(':')
    floatVal=line[index+1:].strip()
    fVal+=float(floatVal)
avg=fVal/count
print('Average spam confidence:',avg)
