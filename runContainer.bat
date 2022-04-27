title transaction microservice
docker build -t transactionms .
docker run --network=opennetworkconnection --env-file ./config.txt -p 8070:8070 transactionms  
cmd /k