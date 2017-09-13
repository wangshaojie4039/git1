#!/bin/bash
function killJava(){
if [ ! -f "$PIDFILE" ];then
  return
fi
pid=`cat $PIDFILE`
echo "关闭$PROJECTNAME,pid=$pid"
kill -15 $pid
sleep 1
rm -rf $PIDFILE
}

PROJECTNAME=ec-web

PIDFILE="/var/run/$PROJECTNAME.pid"

killJava
cd $PROJECTNAME/target
nohup java -jar $PROJECTNAME.jar -server -Xms512m -Xmx1024m -XX:+AggressiveOpts -XX:+UseG1GC --logging.config=../../logback.xml --spring.profiles.active=staging >/dev/null &
echo $! > /var/run/$PROJECTNAME.pid
echo "$PROJECTNAME.jar started"
cat $PIDFILE
