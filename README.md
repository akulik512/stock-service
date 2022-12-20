Please, find the results of manual testing in the docs folder.

Save stock:

```bash
curl --location --request POST 'http://localhost:8080/stock' \
--header 'Content-Type: application/json' \
--data-raw '{
	"companyName": "Stock Service",
	"shareName": "NSE:DRREDDY",
	"shareIsinCode": "US-000402625-0",
	"country": "Sweden",
	"fieldEconomicActivity": "crypto",
	"pricePerShare": "12.34 EUR",
	"volume": 2,
	"date": "20221219",
	"employeeId": 12345
}'
```

Get all stocks:

```bash
curl --location --request GET 'http://localhost:8080/stock/12345' \
--header 'Content-Type: application/json' \
--data-raw '{
	"companyName": "Stock Service",
	"shareName": "NSE:DRREDDY",
	"shareIsinCode": "US-000402625-0",
	"country": "Sweden",
	"fieldEconomicActivity": "crypto",
	"pricePerShare": "12.34 EUR",
	"volume": 2,
	"date": "20221219",
	"employeeId": 12345
}'
```