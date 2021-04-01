#!/bin/sh
NAME="server-0.0.1-SNAPSHOT.jar"
echo $NAME
# shellcheck disable=SC2006
ID=`ps -ef | grep "$NAME" | grep -v "$0" | grep -v "grep" | awk '{print $2}'`
echo "$ID"
echo "---------------"
for id in $ID ;do kill -9 "$id"; echo "killed $id"; done
echo "---------------"
