mvn clean compiler:compile assembly:single
sudo docker build -t javatest3 . -f ./docker/java/Dockerfile
sudo docker build -t javatest3mysql . -f ./docker/mysql/Dockerfile