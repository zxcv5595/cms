#!
docker run -it \
--name good-cms-mysql \
-e MYSQL_ROOT_PASSWORD=1 \
-p 3306:3306 \
-d mysql