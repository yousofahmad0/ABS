USE ABS
go
 
 
CREATE VIEW DISTINATION_TotalPrice
AS
select TIC_DESTINATION ,SUM(TOTAL_PRICE) AS TOTALPRICE
FROM RESERVATION GROUP BY TIC_DESTINATION
go


create view CUSTOMERS_Who_CAME as
select c.U_ID ,c.U_username,c.U_GENDER,c.U_AGE,c.U_CHILDREN
from _USER c,MAKE m ,RESERVATION r,PASSENGER p
where m.U_ID = c.U_ID and m.RES_ID = r.RES_ID and p.P_ID=c.U_ID
go


CREATE VIEW ALD
AS
SELECT AIRLINE.A_ID,AIRLINE.A_NAME
FROM  AIRLINE
JOIN FLIGHT
ON airline.A_ID = FLIGHT.A_ID
go
 

CREATE VIEW CSTD 
AS
SELECT _USER.U_ID,_USER.U_username,_USER.U_FULLNAME,_USER.U_AGE,_USER.U_CHILDREN
FROM _USER
JOIN make
ON _USER.U_ID=MAKE.U_ID
go
 

CREATE VIEW PASNGD
AS
SELECT P_ID,P_Username,P_FULLNAME,PASSENGER.F_NO
FROM PASSENGER
JOIN FLIGHT
ON PASSENGER.F_NO=FLIGHT.F_NO
go
 

CREATE VIEW RESD 
AS
SELECT RESERVATION.RES_ID,F_NO,TIC_AMOUNT,TOTAL_PRICE,RES_DATE,CLASS,TIC_DESTINATION
FROM RESERVATION
JOIN MAKE
ON RESERVATION.RES_ID = MAKE.RES_ID
go
 