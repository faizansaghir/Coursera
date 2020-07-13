import urllib.request
import xml.etree.ElementTree as ET

urlString=input('Enter URL to receive XML data from:')
fHandle=urllib.request.urlopen(urlString)
data=''
for line in fHandle:
    currData=line.decode()
    data+=currData
fData=ET.fromstring(data)
comments=fData.findall('comments/comment')
sum=0
for comment in comments:
    currNum=int(comment.find('count').text)
    sum+=currNum
print(sum)