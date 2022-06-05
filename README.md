# 🏦	Banking System App 

## 📜		 Description
 Banking project is, a backend application design ,similar to the 
 E.P. used in a bank was aimed. With this application,
 sufficient infrastructure was created to handle the basic transactions in the bank.

- Coding a banking backend was very exciting.I had the motivation to find job opportunities with this project.
- I build this project after Java Spring Bootcamp and it is a final project assignment.
- Banking App project , I have gaind some coding skills and problem solving abilities.
- Also this project helped me to work deeply with many technologies.


## 📄	 Design Patterns in Project

- Strategy Pattern
- Facade Pattern
- Singleton Pattern
- Factory Pattern

  
## 🔊		Project API and Services

There are 6 API's to check and use:
- [Customer Management](#customermanagement)
- [Account Management](#accountmanagement)
- [Card Management](#cardmanagement)
- [Transaction Management](#transactionmanagement)
- [User Management](#usermanagement)
- [Authentication Management](#authenticationmanagement)

## 👨‍💼	CustomerManagement
In the customer management section, 
 The necessary endpoints for Customers; adding, updating, viewing and deleting are written.
In addition, some special checks have been carried out.

- Is there a balance on any of the customer's accounts?
- Is there a balance or debt on any of the customer's cards?
![image](https://user-images.githubusercontent.com/72404480/172072075-3a77a153-d12b-4f1c-aba9-cf93d013687d.png)

## 💰	AccountManagement

Accounts service for bank customers to use to control their investments,vieving accounts,
 deletion accounts will be allowed through APIs. Users will be able to open
  two different types of accounts, checking account and deposit account. 

   Some special checks: 
 - ✅ Money can only be transferred via IBAN through a checking account. 
 Deposit accounts can't be used to send money.
 - ✅ Accounts can be opened in TL, Euro or Dollar currencies.

## 💳	Card Management

In card management for customers prepaid bank card and credit cards are offered.Customers can create
these 2 card types. Creation of these cards, associating them with the customer and the account,
Money transfer (shopping) functions using cards will be provided through APIs.

⭐	 I have used Strategy Pattern for paying credit card debt or adding balance to bank card.

Requirements:
- ✅ Debt inquiry, debt payment from account, debt payment from ATM
Extras:
- ✅ Viewing (in JSON format) operations can still be done via API.

## 💸	Transaction Management

Transaction endpoints are provided for managing customers' money transfers. Transaction 
can be between the accounts opened in the different currencies. I prefered that current rate
 is received via https://api.exchangeratesapi.io/latest?base=TRY and 
 converted into the currency of the account to be transferred.And transfer proceed with this exchanged amount.
 Transfer transactions are carried out only through IBAN.

 ⭐ I have used Strategy Pattern for paying credit card debt or adding balance to bank card.

## 👩	 User Management

In this project I have used Spring Security and JWT token. I have chosen adding User different point
from adding customer. After a customer is added by Admin on bank branch , customer can register so after that process
customer will be a user.
In additionaly a ADMIN user can add a user with customer infos directly.

## 🔐	Authentication Management

I have used Spring security here, it is not possible to send requests to any API without having a JWT token.
 Therefore, with the /auth endpoint, which is open to everyone, customers can obtain tokens first registering and after logging in.

## ✅Validation

In the banking project, I have preferred to Valid request in Controller with @Validated annotation.
Also I used, @Pattern annotations to get correct request.
 In additionaly BaseControllerAdvice helped me with custom methods to handle
  Response Entity.


## ⁉️ Exception Handling

In project, I coded my own Exception Hangling classes.Also all of the exceptions have handled with specific class and method.
I have used and extended BaseException  and coded a Singleton custom exception class.

⭐I have used Singleton Pattern for creating and using custom Exception Handling classes.


## 🩺 Unit Tests

#### Customer Unit Tests
Total 9 Test and 5 Exception tests are :
- ✅ CustomerAlreadyCreated_Exception
- ✅ CustomerNotFound_Exception
- ✅ CustomerAlreadyDeleted_Exception
- ✅ Customer_CanNotDelete_When_AccountBalance_IsNotZero
- ✅ Customer_CanNotDelete_When_CardBalanceorDebt_IsNotZero

#### Account Unit Tests
Total 9 Test and some Exception tests are :
- ✅ AccountNotFound_Exception
- ✅ AccountType_IsAlreadyExist_At_SameBranch_Exception
- ✅ Account_WasDeleted_Exception
- ✅ Account_Card_Balance_or_Debt_NotZero_Exception

#### Card Unit Tests
Total 19 Test and some Exception tests are :
- ✅ Card_NoCardOnAccount_Exception
- ✅ Card_NotFound_Exception
- ✅ Card_AlreadyDeleted_Exception
- ✅ Card_CanNotDelete_When_CardBalance_IsNotZero
- ✅ Card_NotEnoughMoney_toPay_Exception

#### Transaction Unit Tests
Total 16 Test and some Exception tests are :
- ✅ Transaction_NotEnougMoney_Exception
- ✅ Transaction_AccountisDeleted_Exception
- ✅ Transaction_AccountisNotFound_Exception
- ✅ Transaction_WrongIBAN_Exception

#### User Unit Tests
Total 16 Test and some Exception tests are :
- ✅ User_CanNotFound_Exception
- ✅ User_Mail_AlreadyCreated_Exception
- ✅ User_AlreadyDeleted_Exception
- ✅ User_Get_IsDeleted_Exception




## Run With Your Computer

Clone the project with terminal in a folder that you want to download this project

```bash
  https://github.com/HasanDogann/NormaJavaSpringBoot.git
```
---

Open Intellij Idea and choose file and open project that you have cloned

```bash
  File / Open 📁	
```
---

Start Docker Engine to testing application on your computer,
```bash
 YOu can use Docker Desktop
```
--- 

In IDE's terminal go to and up the docker compose
```bash
  cd ....\BankingSystem\src\main\resources>
  docker compose up -d
```


After that you need to instal project with macen
```bash
  mvn clean install
```
--- 

Now App is up and next about JWT usage.

```bash
 Open Postman desktop for taking JWT token.
```

---

First you must register , for this purpose I have created 1 Customer and you can use
this customer infos to register.

```bash
 localhost:8090/auth/register

```
---

When you see User creation successful response now you can log in with your new user.
If you want to log in with Admin authority you can use Admin user that I created as initial.

```bash
 localhost:8090/auth/login
```

Finally, after this POST request you will get a Bearer token response and now you can send request with 
this token and try all API's.

Have a nice try.

  
## Thanks and Coder

- 💻	 [@Hasan DOGAN](https://github.com/HasanDogann) GitHub Link
I would like to thank 🙏	 **BARAN BÜYÜK** who supported me to accomplish
 this project and prepared me for the sector at the 
 Java Spring Bootcamp for about 2 months. I would also
  like to thank the  🙏	 **Patika.Dev** team for giving us this opportunity.

  
