Practice
Task 1
Create application with 3 threads producing random hotel booking requests (plain Java object with fields such as 'ad', 'date', 'hotel', etc.) and 6 consuming threads emulating some activity for 5 seconds (sleep).
Use single queue (max capacity - five elements) for all threads, and wait()/notify() methods (implement your own queue).
All threads should log their state ('producer #A: sent XXX', 'booker #Z: received XXX', 'booker #Z: processed XXX').
Total number of generated requests: 15
Program should wait until last thread complete request processing.
Task 2
Goals: concurrency + I/O
Make an application that contains business logic for making money transfer operations between different accounts. Create model for dealing with user accounts (some customer details, balance). Use Long for performing of calculations.
Data with user accounts should be stored as files (one file per account), file should be of binary format (serialize model). Application should scan 'accounts' folder at start to determine accounts and print initial per account/summary balances.
Separate application functionality to DAO, service and utilities. Create module that will provide high-level operations (manage accounts).
Create 10 accounts files (you can tune number of accounts, use a constant) with random initial balance.
Provide concurrent data access to user accounts. Simulate simultaneous transactions for single account (transfer to it from other, from it to another, etc.) by 20 threads (you can tune number of threads, use a constant) and ensure that all the operations are thread-safe.
Use ExecutorService for thread managing. Complete 1000 operations. You should made this operations by using 20 threads.
Make custom exceptions to let user to know the reason of error. Don't handle runtime exceptions.
Validate inputs such an account existence, sufficiency of amount, etc. Log information about what is happening on different application levels and about transaction results. Use Logger for that. At the end of program print final per account/summary balances.
You should use ReentrantLock.
Checklist:
1) Do not use global lock on all accounts, access should be fine grained. Pay attention to deadlocks
2) CPU load in perfomance monitor shows near 100% load during program launch
2) Initial and final summary balances should be equal
Optional: file list should be monitored by background task. create test folder with just 2 accounts, start app with 10 threads, connect to the app with visualvm tool, monitor threads state
Reflection/Annotations processing
Create annotation "Secured" for methods: two parameters, one mandatory of Integer type and optional String with a default value “strict”. Enumerate all methods when one of parameters is of Integer type. Create a class ‘Entity’ with 3+ methods (public, private). Apply annotation for two methods of the Entity(first case - only mandatory annotation parameters, second - set optional too). Create instance of this class using hardcoded string class name. Process instance of this class in runtime, print out all methods of it, for each method show annotation presense and its parameters.
Regexps
You have file with some text mixed with phone numbers in the following format: +Y(XXX) XXX XX XX Sample line of text: "Notification has been sent 150 times to +4(351) 455 22 44 successfully. +1(431) 542 56 12 is unreachable" Using regexp and matchers process such file and create a new one with phone numbers in format of YXXXXXXXXXX.
