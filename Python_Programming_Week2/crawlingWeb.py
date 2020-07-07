import urllib.request
from bs4 import BeautifulSoup

count=int(input('Enter Count'))
position=int(input('Enter Position'))

urlVar='http://py4e-data.dr-chuck.net/known_by_Areeba.html'
tag=None
i=1
while(i<=count):
    fhandle=urllib.request.urlopen(urlVar)
    soup=BeautifulSoup(fhandle,'html.parser')
    tags=soup('a')
    tag=tags[position-1]
    urlVar=tag.get('href')
    i+=1
    print(urlVar)
print(tag.contents[0])