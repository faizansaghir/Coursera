import urllib.request, urllib.parse, urllib.error

fhandle=urllib.request.urlopen('http://data.pr4e.org/intro-short.txt')
for line in fhandle:
    print(line.decode().strip())