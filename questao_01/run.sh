cd node3 && mvn clean install && cd ..
docker build -t jose/node3 .
 docker run -p 10991:10991 --name node3 jose/node3 -d

cd node2 && mvn clean install && cd ..
docker build -t jose/node2 .
docker run -p 1099:1099 jose/node2 --name node2 -- link node3:host-node3 -d

cd node1 && mvn clean install && cd ..
docker build -t jose/node1 .
docker run  jose/node1 --name node1 --link node2:host-node2 -d

 
