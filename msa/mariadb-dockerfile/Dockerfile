FROM mariadb
ENV MYSQL_USER zhyun
ENV MYSQL_PASSWORD test123
ENV MYSQL_DATABASE mydb
copy ./mariadb/data /var/lib/mysql
EXPOSE 3307
CMD ["--user=root"]