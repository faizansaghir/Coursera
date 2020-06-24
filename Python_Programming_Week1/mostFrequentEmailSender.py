fileHandle=open('mbox-short.txt')
senders=dict()
for line in fileHandle:
    if 'From ' in line:
        parts=line.split()
        currSender=parts[1]
        senders[currSender]=senders.get(currSender,0)+1
    else:
        continue
maxCount=None
maxSender=None
for sender in senders:
    if maxCount is None or maxCount<senders[sender]:
        maxCount=senders[sender]
        maxSender=sender
print(maxSender,maxCount)