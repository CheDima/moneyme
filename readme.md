## Usage:

GET
account/all

GET
account/bynumber/{account_number}

POST
transfer

with JSON parameter
{"credAccount":"YOUR_SENDER_ACCOUNT", "debtAccount":"YOUR_RECEIVER_ACCOUNT", "amount":"5.0", "message":"TRANSACTION_COMMENT_TEXT"}


## Example:
### Run it
mvn exec:java -Dexec.mainClass="com.chedmitry.moneyme"
(or java -jar...)

### Check all accounts
http://localhost:8080/moneyme/account/all

result:
_[
    {
        "number": "TEST_ACCOUNT_1",
        "balance": 1000
    },
    {
        "number": "TEST_ACCOUNT_2",
        "balance": -1000
    },
    {
        "number": "1234567890",
        "balance": 0
    }
]_

### Make a transaction:
http://localhost:8080/moneyme/transfer
>(POST with {"credAccount":"TEST_ACCOUNT_1", "debtAccount":"TEST_ACCOUNT_2", "amount":"5.0", "message":"Happy birthday"})

result:
_{
    "result": "SUCCESS",
    "comment": "Money transferred from TEST_ACCOUNT_1 to TEST_ACCOUNT_2"
}_

### Check all accounts
http://localhost:8080/moneyme/account/all

result:
_[
    {
        "number": "TEST_ACCOUNT_1",
        "balance": 995
    },
    {
        "number": "TEST_ACCOUNT_2",
        "balance": -995
    },
    {
        "number": "1234567890",
        "balance": 0
    }
]_

## TODOs
- Add JavaDoc! (especially in DataService)
- Add currencies and exchange rates
- Add different methods for transactions display and search (boilerplate code, didn't want to clutter things up)

## Useless remarks
- Jersey framework was selected because it is relatively lightweight and has both REST and Dependency Injection
- Nobody reads remarks
- Data Service implementation should be moved in a separate independent component

