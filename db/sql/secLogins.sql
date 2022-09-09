use master
go

------Create Login admin-----
Create Login [admin] with PASSWORD = 'yyyy', DEFAULT_DATABASE=[ABS]
go


------Creating Login for demo---- 
Create Login [fordemo] with PASSWORD = 'helloyzsf', DEFAULT_DATABASE=[ABS]
go

------Creating Login for demo Passenger---- 
Create Login [fordemopassenger] with PASSWORD = 'helloyzsf', DEFAULT_DATABASE=[ABS]
go



use ABS
go
-----Creating user admin-----
Create user [admin] for Login [admin]
go
ALTER user [admin] with Default_SCHEMA = [dbo]
go
ALTER ROLE [db_owner] ADD MEMBER [admin]
go


---Creating user for demo-------
Create user [fordemo] for Login [fordemo]
go

ALTER user [fordemo] with Default_SCHEMA = [dbo]
go

---Creating user for demo Passenger-------
Create user [fordemopassenger] for Login [fordemopassenger]
go

ALTER user [fordemopassenger] with Default_SCHEMA = [dbo]
go


----Creating new ROLE----
CREATE ROLE [fordemoR]
GO

GRANT INSERT ON [dbo].[_USER] TO [fordemoR]
GO
GRANT SELECT ON [dbo].[_USER] TO [fordemoR]
GO
GRANT UPDATE ON [dbo].[_USER] TO [fordemoR]
GO
GRANT SELECT ON [dbo].[AIRLINE] TO [fordemoR]
GO
GRANT INSERT ON [dbo].[RESERVATION] TO [fordemoR]
GO
GRANT INSERT ON [dbo].[MAKE] TO [fordemoR]
GO
GRANT SELECT ON [dbo].[FLIGHT] TO [fordemoR]
GO
GRANT SELECT ON [dbo].[MAKE] TO [fordemoR]
GO
GRANT SELECT ON [dbo].[RESERVATION] TO [fordemoR]
GO

----Creating new ROLE for demo P----
CREATE ROLE [fordemoRP]
GO
GRANT SELECT ON [dbo].[MAKE] TO [fordemoRP]
GO
GRANT SELECT ON [dbo].[_USER] TO [fordemoRP]
GO
GRANT SELECT ON [dbo].[RESERVATION] TO [fordemoRP]
GO
GRANT SELECT ON [dbo].[FLIGHT] TO [fordemoRP]
GO
GRANT INSERT ON [dbo].[PASSENGER] TO [fordemoRP]
GO
GRANT SELECT ON [dbo].[PASSENGER] TO [fordemoRP]
GO


------Adding member fordemo for ROLE fordemoR--------

ALTER ROLE [fordemoR] ADD MEMBER [fordemo]
go

------Adding member fordemopassenger for ROLE fordemoRP--------

ALTER ROLE [fordemoRP] ADD MEMBER [fordemopassenger]
go
