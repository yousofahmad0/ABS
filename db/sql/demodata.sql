use ABS
go

INSERT INTO AIRLINE
VALUES('YousAirline');


INSERT INTO FLIGHT
VALUES(1,'9/12/2021',1,'FRANCE','13:30pm ','12:30pm','Greece Italy',122,500);

insert into _USER
 VALUES('admin','yyyy','yosof ahmad','MALE','BEIRUT',20,0,81791012); 

 insert into _USER
 VALUES('ysf','yyyy','yosof ahmad','MALE','BEIRUT',20,0,81791012); 
 insert into _USER
 VALUES('mrm','mmmm','mariam','FEMALE','BEIRUT',20,0,81791012); 

  insert into _USER
 VALUES('new','yyyy','new','MALE','BEIRUT',18,0,81791012); 
select * from _USER

 insert into PASSENGER
 VALUES('ysf','yosof ahmad',1,'MALE','BEIRUT',20,0); 

 select * from PASSENGER
/*insert into RESERVATION VALUES
(1,2,0,'',1,'A','FRANCE');

insert into MAKE VALUES(2,1);
insert into MAKE VAlues(3,1);*/

select * from MAKE
select * from RESERVATION
select * from _USER
select m.U_ID,u.U_CHILDREN, f.F_NO, f.F_DEST from MAKE m, RESERVATION r, FLIGHT f, _USER u where u.U_username = 'ysf' and u.U_ID = m.U_ID and m.RES_ID = r.RES_ID and f.F_NO = r.F_NO
select m.U_ID, f.F_NO, f.F_DEST from MAKE m, _USER u, RESERVATION r, FLIGHT f where u.U_username = 'ysf' and u.U_ID = m.U_ID and m.RES_ID = r.RES_ID and f.F_NO = r.F_NO