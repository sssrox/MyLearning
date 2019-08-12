# 12 Aug - Day 1:
## Images
docker pull nginx  
To list images:  
docker image ls  

## Container
To install image  
docker container run -p 5000:80 nginx  

### To list running containers:
docker container ls  

### To list all containers:
docker container ls -a  

### To Stop container:
docker container stop f6beed46173d  
docker container stop <containerid>  
or
docker container stop flamboyant_brattain  
docker container stop <containername>  


### To remove old containers 
docker container rm f6beed46173d  


## To push the image

Login into docker  
create tag:  
docker tag nginx sssrox/nginx  
docker push sssrox/nginx  


## Network
To list networks  
docker network ls  

Create a Network  
docker network create mynetwork  

start a container in a network  
docker container run -p 3000:80 --name nginx1 -d --network mynetwork nginx  

### Connecting to another network
docker network connect mynetwork2 nginx2 

docker network disconnect mynetwork2 nginx2  

## Execute commands in Container
To check inside a container  
docker container exec -it f27d2eca66ea bash  

If linux first execute apt-get update

## Load Balance check using elastic
docker pull elasticsearch:2  
create a network by name network1  
docker run -d --name elastic1 --network network1 --network-alias search -d elasticsearch:2  
docker run -d --name elastic2 --network network1 --network-alias search -d elasticsearch:2  

to test it install alpine os  
docker pull alpine  
docker run --net socgen alpine nslookup search  
