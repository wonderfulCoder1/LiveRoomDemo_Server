#docker run -d \
#  --name rabbitmq \
#  -p 5672:5672 \
#  -p 15672:15672 \
#  -p 61613:61613 \
#  rabbitmq


docker run -d ^
  --name rtmp-server ^
  -p 1935:1935 ^
  -p 80:80 ^
  -v D:\zengli\code\LiveRoomDemo_Server\nginx.conf:/etc/nginx/nginx.conf.template ^
  -v D:\zengli\code\LiveRoomDemo_Server\video:/yjdata/www/www/video ^
  -v D:\zengli\code\LiveRoomDemo_Server\live\hls:/yjdata/www/www//live/hls/ ^
  alfg/nginx-rtmp
