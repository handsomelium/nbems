#!/bin/bash
project_name=$1
tag=$2
port=$3

if [ "$project_name" != "" ] ; then

  #查询容器是否存在，存在则删除
  containerId=`docker ps -a | grep -w $project_name:$tag | awk '{print $1}'`
  if [ "$containerId" != "" ] ; then
      #停掉容器
      docker stop $containerId
      #删除容器
      docker rm $containerId

      echo "成功删除容器"
  fi

  #查询镜像是否存在，存在则删除
  imageId=`docker images | grep -w $project_name | awk '{print $3}'`
  if [ "$imageId" != "" ] ; then
      #删除镜像
      docker rmi -f $imageId

      echo "成功删除镜像"
  fi

fi