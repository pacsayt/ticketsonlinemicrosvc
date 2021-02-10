#!/bin/bash

printf "EVENTPLACE\n"
curl -X GET localhost:8011/eventplace/11 -d {} -H "Content-Type: application/json"
curl -X GET localhost:8011/eventplace -d {} -H "Content-Type: application/json"

printf "\nCONFIG\n"
curl -X GET localhost:8011/eventplace/config -d {} -H "Content-Type: application/json"


printf "\nACTUATOR\n"
curl -X GET localhost:8011/actuator/ -d {} -H "Content-Type: application/json"

printf "\nACTUATOR REFRESH\n"
curl localhost:8011/actuator/refresh -d {} -H "Content-Type: application/json"

printf "\n"
