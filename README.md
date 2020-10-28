# README

### Endpoints

* /account/create
* /account/withdraw/amount (Example /account/withdraw/20.52)
* /account/deposit/amount
* /account/enquiry

### Dummy data

* Account Create
{
    "username": "john",
    "password": "abc",
    "firstname": "John",
    "lastname": "Doe"
}

* Account enquiry
{
    "username": "john",
    "password": "abc"
}

* Account Deposit
{
    "username": "john",
    "password": "abc"
}

* Account withdraw
{
    "username": "john",
    "password": "abc"
}


### Other Info

* For personal details of user, we are taking only firstname and lastname
* Invalid requests will return a json object with an error message and status of BAD REQUEST.
* @Version is used for optimistic locking

### Assumptions

* username and password are only two attributes required to create a account.
* Balance of new accounts will be zero on creation.
* String matching of password is ok
* Any valid string can be used as a password except null string.
* Any valid non negative real number(float) can be deposited or withdrawn(given enough balance). Real life denominations of 50's, 100's are not taken into account
* Using Post Mapping in case of enquiry because we are not using token based authentication.
* Actions outside the scope of problem definition such as deleting accounts etc. are not modeled in this API.

### Running Tests
Use `mvn test`
