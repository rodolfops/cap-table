# cap-table

This project reads a csv file with lines in the following standard

#INVESTMENT DATE, SHARES PURCHASED, CASH PAID, INVESTOR

And produces a JSON with a summarized cap table until a date passed by argument. If no date is passed by the user, it will summarize the file until today date.

There is a build file to call maven and build the project, generating a cap_table file that the user can call to execute the program.

The program must receive a csv file to read or it will not run.
