#!/bin/bash

CONTAINER_NAME=dev-postgres
docker pull postgres:11
docker run --name $CONTAINER_NAME -p 5432:5432 -e POSTGRES_PASSWORD=postgres  -e POSTGRES_DB=coursedb -d postgres:11
