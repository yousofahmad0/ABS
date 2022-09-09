use master
go

CREATE DATABASE ABS
 ON  PRIMARY 
( NAME = ABS_dat, FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\ABS.mdf')
 LOG ON 
( NAME = ABS_log, FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\ABS.ldf')
GO