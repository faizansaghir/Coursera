import socket
mySocket=socket.socket(socket.AF_INET,socket.SOCK_STREAM)
mySocket.connect(('data.pr4e.org',80))
cmd='GET http://data.pr4e.org/intro-short.txt HTTP/1.0\r\n\r\n'.encode()
#the cmd is as we would request after connecting to a server using command window hence \r\n\r\n and encode is used to convert from one form to another
mySocket.send(cmd)
while(True):
    data=mySocket.recv(512)
    if len(data)<1:
        break
    print(data.decode())
mySocket.close()