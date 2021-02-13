#!/bin/sh

echo "Remember to start Kafka first (~/bin/kafkastart.sh)"

printf "\nPRINT CONFIGURATION\n"
curl -X GET localhost:8014/bookedticket/config -d {} -H "Content-Type: application/json"
curl -X GET localhost:/ -d {} -H "Content-Type: application/json"

printf "\nACTUATOR\n"
curl -X GET localhost:8080/actuator/ -d {} -H "Content-Type: application/json"

#printf "\nACTUATOR REFRESH\n"
#curl localhost:8080/actuator/refresh -d {} -H "Content-Type: application/json"

printf "\nACTUATOR BUS-REFRESH\n"
curl -X POST localhost:8080/actuator/bus-refresh -d {} -H "Content-Type: application/json"
# Does not support PUT, GET : 405, not allowed

# meant to be called by (f.e.) GitHub on check in
printf "\nMONITOR\n"
curl localhost:8888/monitor -d {} -H "Content-Type: application/json"
