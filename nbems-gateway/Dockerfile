# 基础镜像
FROM  java:8
# author
MAINTAINER lium

# 挂载目录
VOLUME /home/nbems
# 创建目录
RUN mkdir -p /home/nbems
# 指定路径
WORKDIR /home/nbems
# 复制jar文件到路径
COPY ./target/nbems-gateway.jar /home/nbems/nbems-gateway.jar

ENTRYPOINT ["java","-jar","nbems-gateway.jar"]