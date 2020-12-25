# Wenance challange

**Requirements for building and running the project:**

1. Install [docker engine](https://docs.docker.com/engine/install)
2. Install [docker-compose](https://docs.docker.com/compose/install/)
3. Open a terminal and execute the following commands:\
    3.1 `git clone https://github.com/gabrielromagnoli1987/wenance_challange.git` \
    3.2 `cd wenance_challange` to move to the downloaded folder \
    3.3 `docker-compose up` This command will download a docker maven image
   (this image will download the dependencies of the application and build the app),
   an openjdk:11 image that will run the application.jar,
   a mongodb image for our database, and then it will run the 2 containers (the java 11 one and the mongodb one) connected on the same network.