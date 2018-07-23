FROM registry.saas.hand-china.com/tools/mysql
COPY ./docker/mysql/sakila* /
VOLUME /data
ONBUILD RUN mysql -uroot -p $MYSQL_ROOT_PASSWORD -e "create database sakila" -D sakila</sakila-schema.sql
ONBUILD RUN mysql -uroot -p $MYSQL_ROOT_PASSWORD -D sakila</sakila-data.sql