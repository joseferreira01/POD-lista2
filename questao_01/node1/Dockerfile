FROM payara/micro
MAINTAINER Ricardo Job <joseifpb2015@gmail.com>
ADD target/node1-jar-with-dependencies.jar $PAYARA_PATH/
#ENTRYPOINT ["java", "-jar", "payara-micro.jar", "--deploy", "node1-jar-with-dependencies.jar"]
ENTRYPOINT ["java", "-jar", "node1-jar-with-dependencies.jar"]
