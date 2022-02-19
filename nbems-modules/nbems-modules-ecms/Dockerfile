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
COPY ./target/nbems-ecms.jar /home/nbems/nbems-ecms.jar

ENTRYPOINT ["java","-jar","nbems-ecms.jar"]