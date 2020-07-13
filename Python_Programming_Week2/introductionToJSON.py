import urllib.request
import json

url=input('Enter URL:')
fHandle=urllib.request.urlopen(url)
urlData=''
for line in fHandle:
    currData=line.decode()
    urlData=urlData+currData
data=json.loads(urlData)
comments=data['comments']
sum=0
for comment in comments:
    currNum=comment['count']
    sum=sum+int(currNum)
print(sum)