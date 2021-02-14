#!/bin/sh

echo "Remember to start Kafka first (~/bin/kafkastart.sh)"

printf "\nPRINT CONFIGURATION\n"
printf "\neventplaceservice\n"
curl -X GET localhost:8011/eventplace/config -d {} -H "Content-Type: application/json"
printf "\neventservice\n"
curl -X GET localhost:8012/event/config -d {} -H "Content-Type: application/json"
printf "\nticketservice\n"
curl -X GET localhost:8013/config -d {} -H "Content-Type: application/json"
printf "\nbookedticketservice\n"
curl -X GET localhost:8014/bookedticket/config -d {} -H "Content-Type: application/json"
printf "\n\n"

##printf "\nACTUATOR\n"
printf "\neventplaceservice\n"
curl -X GET localhost:8011/actuator -d {} -H "Content-Type: application/json"
printf "\neventservice\n"
curl -X GET localhost:8012/actuator -d {} -H "Content-Type: application/json"
printf "\nticketservice\n"
curl -X GET localhost:8013/actuator -d {} -H "Content-Type: application/json"
printf "\nbookedticketservice\n"
curl -X GET localhost:8014/actuator -d {} -H "Content-Type: application/json"
printf "\n\n"

#printf "\nACTUATOR REFRESH\n"
#curl localhost:8011/actuator/refresh -d {} -H "Content-Type: application/json"

printf "\nACTUATOR BUS-REFRESH\n"
curl -X POST localhost:8011/actuator/bus-refresh -d {} -H "Content-Type: application/json"
# Does not support PUT, GET : 405, not allowed

# meant to be called by (f.e.) GitHub on check in
#printf "\nMONITOR\n"
#curl localhost:8761/monitor -d {} -H "Content-Type: application/json"
