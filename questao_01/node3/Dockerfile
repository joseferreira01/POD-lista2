FROM payara/micro
MAINTAINER jose <joseifpb2015@gmail.com>
ADD target/node3-jar-with-dependencies.jar $PAYARA_PATH/
#ENTRYPOINT ["java", "-jar", "payara-micro.jar", "--deploy", "node3-jar-with-dependencies.jar"]
ENTRYPOINT ["java", "-jar", "node3-jar-with-dependencies.jar"]
