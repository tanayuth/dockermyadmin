create database dockermyadmin character set utf8;
# Add user
grant all privileges on dockermyadmin.* to dkadmin identified by 'pingu123';
grant all privileges on dockermyadmin.* to dkadmin@'localhost' identified by 'pingu123' ;
FLUSH PRIVILEGES;
