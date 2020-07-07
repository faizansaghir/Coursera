import urllib.request
from bs4 import BeautifulSoup


fhandle=urllib.request.urlopen('http://py4e-data.dr-chuck.net/comments_732979.html')
soup=BeautifulSoup(fhandle,'html.parser')
tags=soup('span')
sum=0
for tag in tags:
    sum+=int(tag.contents[0])
print(sum)