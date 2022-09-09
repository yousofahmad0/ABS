set projloc=C:\Users\user\Desktop\DBproject\ABS_DB\
set conn=-S DESKTOP-5UK2720\SQLEXPRESS -U sa -P helloyzsf

@REM Create database

sqlcmd %conn% -i %projloc%sql\CreateDBAirlineBS.sql -o %projloc%log\CreateDBAirlineBS.log

@REM Create tables

sqlcmd %conn% -i %projloc%sql\createTablesABS.sql -o %projloc%log\createTablesABS.log

@REM Create Indexes

sqlcmd %conn% -i %projloc%sql\createIndexesABS.sql -o %projloc%log\createIndexesABS.log

@REM Create Views

sqlcmd %conn% -i %projloc%sql\createViews.sql -o %projloc%log\createViews.log

@REM Create Functions Procedures

sqlcmd %conn% -i %projloc%sql\FunctionProcABS.sql -o %projloc%log\FunctionProcABS.log

@REM Create Triggers

sqlcmd %conn% -i %projloc%sql\TriggersABS.sql -o %projloc%log\TriggersABS.log

@REM Create LoginsSecurity

sqlcmd %conn% -i %projloc%sql\secLogins.sql -o %projloc%log\secLogins.log

@REM Create SampleData

sqlcmd %conn% -i %projloc%sql\InsertDataABS.sql -o %projloc%log\InsertDataABS.log

