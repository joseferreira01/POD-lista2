FROM payara/micro
MAINTAINER jose <joseifpb2015@gmail.com>
ADD target/node2-jar-with-dependencies.jar $PAYARA_PATH/
#ENTRYPOINT ["java", "-jar", "payara-micro.jar", "--deploy", "node2-jar-with-dependencies.jar"]
ENTRYPOINT ["java", "-jar", "node2-jar-with-dependencies.jar"]
