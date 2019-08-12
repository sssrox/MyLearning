# 12 Aug - Day 1:
docker pull nginx
To list images:
docker image ls

To install image
docker container run -p 5000:80 nginx

To list running containers:
docker container ls

To list all containers:
docker container ls -a

To Stop container:
docker container stop f6beed46173d
docker container stop <containerid>
or
docker container stop flamboyant_brattain
docker container stop <containername>


To remove old containers 
docker container rm f6beed46173d



 docker container run -p 3306:3306 -e MYSQL_ROOT_PASSWORD=my-secret-pw --name mysql mysql -d

#To push the image

Login into docker
create tag:
docker tag nginx sssrox/nginx
docker push sssrox/nginx
