FROM ubuntu:latest
LABEL authors="guilherme.ayusso"

ENTRYPOINT ["top", "-b"]