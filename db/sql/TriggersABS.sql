Use ABS
go

---------Trigger on USER---------
Create trigger tri_USER On _USER
FOR Insert, update
As
BEGIN 
	declare @gend varchar(10)
	declare @age int
	declare @errno int
	declare @errmsg varchar(255)
	declare @uname varchar(15)
	set @uname = (select U_username from inserted)
	set @age = (select U_AGE from inserted)
	set @gend = (select U_GENDER from inserted)

	if @gend != 'Male' AND @gend != 'Female' AND @gend != 'Other'
	BEGIN
		Begin tran
		select @errno = 10003,
				   @errmsg = 'ERROR:BAD GENDER!';
		print @errmsg
		rollback
	end


	if (@age) >= 18 
	Begin
		print 'Good Customer Age ' + cast(@age as char(3))
	end
	else
	BEGIN
		Begin tran
		select @errno = 10002,
				   @errmsg = 'ERROR: AGE MUST BE MORE THAN 17 YEARS!';
		print @errmsg
		rollback
	end
	if (select U_CHILDREN from inserted) < 0
	BEGIN
	Begin tran
	select @errno = 10001,
				   @errmsg = 'ERROR: CHILDREN NUMBER MUST NOT BE NEGATIVE?!';
		print @errmsg
		rollback
	end

end
go

/*
drop trigger tri_USER
select * from _USER where U_username='ALI'

INSERT INTO _USER
 VALUES('ALI','yyyy','ali','MALE','BEIRUT',31,3,77171);

 select * from _USER

 delete from _USER where U_username='ALI'

 update CUSTOMER
 set C_GENDER='MALE',C_CHILDREN=-1
 where C_ID=2007
*/


---------Trigger on USER for Delete---------

create trigger TD_USER on _USER for delete as
begin
    declare
       @numrows  int,
       @errno    int,
       @errmsg   varchar(255)

    select  @numrows = @@rowcount
    if @numrows = 0
       return

    /*  Cannot delete parent USER if children still exist in "MAKE"  */
    if exists (select 1
               from   MAKE t2, deleted t1
               where  t2.U_ID = t1.U_ID)
       begin
          select @errno  = 50001,
                 @errmsg = 'Children still exist in "MAKE". Cannot delete parent "USER".'
          goto error
       end
	else
	Begin
		print 'The Deleted User do not have an MAKE Reservtaion'
	end

    return

/*  Errors handling  */
error:
    raiserror(@errmsg,@errno,0)
	rollback  transaction
end
go


/*
drop trigger TD_CUSTOMER

select * from MAKE
select * from CUSTOMER

delete from MAKE where C_ID=4000
delete from CUSTOMER where C_ID=4000

INSERT INTO CUSTOMER
 VALUES(4000,'HADI','MALE','SAIDA',30,2);
 INSERT INTO MAKE
VALUES(4000,2001);


*/

---------Trigger on RESERVATION---------
create trigger tri_Reservation on RESERVATION
For insert, update
AS
BEGIN
	declare @errno int
	declare @errmsg varchar(255)
	declare @cl char(1)
	declare @fn int
	declare @date date

	set @cl = (select CLASS from inserted)
	set @fn = (select F_NO from inserted)



	if(exists(select * from FLIGHT f, inserted i where f.F_NO=i.F_NO))
	Begin
			print 'GOOD FLIGHT NUMBER'
	end
	else
	Begin
		BEGIN TRANSACTION
		select @errno = 10005,
				   @errmsg = 'ERROR:Wrong FLIGHT!';
		print @errmsg
		rollback

	end


	
	if @cl != 'A' AND @cl != 'B' AND @cl!= 'C'
	BEGIN
		BEGIN TRANSACTION
		select @errno = 40001,
				   @errmsg = 'ERROR: Wrong class. We have only 3 choices A,B or C!';
		print @errmsg
		rollback
	end


	
	declare @rd char(20)
	declare @fd char(20)

	set @rd = (select TIC_DESTINATION from inserted)
	set @fd = (select f.F_DEST from FLIGHT f,inserted i where f.F_NO = i.F_NO)

	if @rd = @fd
	begin 
		print 'Good TICKET DESTINATION....' + @rd
	end
	else 
	Begin
		BEGIN TRANSACTION
		declare @noS1 varchar(20)
		declare @noS2 varchar(20)
		set @noS1 = RTRIM(@fd);
		set @noS2 = RTRIM(@rd);--this function remove extra spaces from the right of the string
		select @errno = 20003,
				   @errmsg = 'TICKET Destination('+ @noS2 +') does not equivalent to its FLIGHT Destination in "FLIGHT"('+ @noS1+ ').Cannot create Reservation!';
		print @errmsg
		rollback
	end
end
go

/*
drop trigger tri_Reservation
INSERT INTO RESERVATION
  VALUES(3000,2,200,'2020-6-1',36,'2020-5-1','B','nb');
delete from RESERVATION where RES_ID=3000

select r.TIC_DESTINATION from RESERVATION r where r.F_NO=36

select * from RESERVATION
select * from FLIGHT

*/

---------Trigger on RESERVATION for Delete---------

create trigger TD_RESERVATION on RESERVATION for delete as
begin
    declare
       @numrows  int,
       @errno    int,
       @errmsg   varchar(255)

    select  @numrows = @@rowcount
    if @numrows = 0
       return

	/*  Cannot delete parent "RESERVATION" if children still exist in "MAKE"  */
    if exists (select 1
               from   MAKE t2, deleted t1
               where  t2.RES_ID = t1.RES_ID)
    begin
		select @errno  = 50002,
                 @errmsg = 'Children still exist in "MAKE". Cannot delete parent "RESERVATION".'
		goto error
	end
	else
	Begin
		print 'The Deleted Reservation do not have an MAKE Reservtaion'
	end


    return

/*  Errors handling  */
error:
    raiserror(@errmsg,@errno,0)
	rollback  transaction
end
go

/*
select * from MAKE
select * from RESERVATION



delete from MAKE where RES_ID=2001
delete from RESERVATION where RES_ID=2001


INSERT INTO RESERVATION
  VALUES(2001,3,999, '2020-7-11','B','FRANCE');
 INSERT INTO MAKE
VALUES(4000,2001);

*/

---------Trigger on RESERVATION for Update---------

create trigger TU_RESERVATION on RESERVATION for update as
begin
   declare
      @maxcard  int,
      @numrows  int,
      @numnull  int,
      @errno    int,
      @errmsg   varchar(255)

      select  @numrows = @@rowcount
      if @numrows = 0
         return

     
	  if update(TIC_AMOUNT)
	  begin
         if exists (select 1
                    from   MAKE m, deleted d
                    where  m.RES_ID = d.RES_ID)
            begin
               select @errno  = 7,
                      @errmsg = 'Children still exist in "MAKE". Cannot modify Ticket Amount in "RESERVATION".'
               goto error
            end
      end
    
      
      /*  Cannot modify parent code in "RESERVATION" if children still exist in "MAKE"  */
      if update(RES_ID)
      begin
         if exists (select 1
                    from   MAKE t2, inserted i1, deleted d1
                    where  t2.RES_ID = d1.RES_ID
                     and  (i1.RES_ID != d1.RES_ID))
            begin
               select @errno  = 50005,
                      @errmsg = 'Children still exist in "MAKE". Cannot modify parent code in "RESERVATION".'
               goto error
            end
      end


      return

/*  Errors handling  */
error:
    raiserror(@errmsg,@errno,0)
    rollback  transaction
end
go

/*
drop trigger TU_RESERVATION

update RESERVATION 
set TIC_AMOUNT=	2
where RES_ID=2001

select * from RESERVATION
select * from MAKE
select * from CUSTOMER
*/


Create trigger tri_Passenger on PASSENGER
FOR Insert,update
As
BEGIN 
	declare @pid int
	declare @uname char(15)
	declare @cuname char(15)
	declare @fno int
	declare @date date
	declare @errno int
	declare @errmsg varchar(255)
	declare @chn int
	declare @name char(15)
	
	set @uname = (select P_Username from inserted)
	set @fno = (select F_NO from inserted)
	
    set @cuname = (select U_username from _USER c,MAKE m,inserted i where c.U_username = i.P_Username and c.U_ID=m.U_ID)
	set @chn = (select c.U_CHILDREN from _USER c,inserted i where c.U_username=i.P_Username)
	set @name = (select P_FULLNAME from inserted)
	if(exists(select * from FLIGHT f, inserted i where f.F_NO=i.F_NO))
	Begin
			print 'GOOD FLIGHT NUMBER'
	end
	else
	Begin
		BEGIN TRANSACTION
		select @errno = 10005,
				   @errmsg = 'ERROR: Wrong FLIGHT!';
		print @errmsg
		rollback
	end
	
	if @uname=@cuname
	begin
		print 'WELCOME DEAR ' + @name
	end
	else
	begin
	BEGIN TRANSACTION
		select @errno = 10005,
				   @errmsg = 'BYE BYE DEAR ;)';
		print @errmsg
		ROLLBACK
	end


	declare @gend varchar(10)
	set @gend = (select P_GENDER from inserted)
	if @gend != 'MALE' AND @gend != 'FEMALE' AND @gend != 'OTHER'
	BEGIN
	BEGIN TRANSACTION
		select @errno = 10004,
				   @errmsg = 'ERROR: BAD GENDER! '+@gend;
		print @errmsg
		rollback
	end
	if (select P_CHILDREN from inserted) < 0
	Begin
		BEGIN TRANSACTION
		select @errno = 10009,
				   @errmsg = 'ERROR: CHILDREN NUMBER MUST NOT BE NEGATIVE?!';
		print @errmsg
		ROLLBACK
	end
	if (select P_CHILDREN from inserted) > @chn
	Begin
		BEGIN TRANSACTION
		select @errno = 10009,
				   @errmsg = 'ERROR: THERE ARE MORE CHILDREN!?Children number must not be less than or equal to'+cast(@chn as char(1));
		print @errmsg
		ROLLBACK
	end
end
go


---------Trigger on FLIGHT---------
Create trigger tri_Flight on FLIGHT
FOR Insert,update
As
BEGIN 
	declare @a_line int
	declare @errno int
	declare @errmsg varchar(255)
	declare @errmsg1 varchar(255)
	
	set @a_line = (select A_ID from inserted)

	if (not exists (select * from AIRLINE a, inserted i where a.A_ID = i.A_ID))
	begin
		select @errno = 20001,
				   @errmsg = 'Airline id('+RTRIM(cast(@a_line as char(4)))+') does not exists in "AIRLINE".Cannot create Flight!';
		print @errmsg
		ROLLBACK
		
	end
	else
	begin
		print 'GOOD AIRLINE ID....' + RTRIM(cast(@a_line as char(4)))
	end

end
go


/*drop trigger tri_Flight
INSERT INTO FLIGHT 
VALUES(99,'2020-7-12',10,'AMRICA','6:30 ' , '15:00','ITALY,SPAIN,PORTUGAL');


select * from AIRLINE
select * from FLIGHT


DELETE from FLIGHT where F_NO = '99'*/


---------Trigger on FLIGHT for Delete---------

create trigger TD_FLIGHT on FLIGHT for delete as
begin
    declare
       @numrows  int,
       @errno    int,
       @errmsg   varchar(255)

    select  @numrows = @@rowcount
    if @numrows = 0
       return

    /*  Cannot delete parent "FLIGHT" if children still exist in "RESERVATION"  */
    if exists (select 1
               from   RESERVATION t2, deleted t1
               where  t2.F_NO = t1.F_NO)
       begin
          select @errno  = 50008,
                 @errmsg = 'Children still exist in "RESERVATION". Cannot delete parent "FLIGHT".'
          goto error
       end
	else
	Begin
		print 'The Deleted Flight do not have an Reservtaion'
	end

	/*  Cannot delete parent "FLIGHT" if children still exist in "PASSENGER"  */
	if exists (select 1
               from   PASSENGER t2, deleted t1
               where  t2.F_NO = t1.F_NO)
       begin
          select @errno  = 50006,
                 @errmsg = 'Children still exist in "PASSENGER". Cannot delete parent "FLIGHT".'
          goto error
       end
	Begin
		print 'The Deleted Flight do not have an Passenger'
	end


    return

/*  Errors handling  */
error:
    raiserror(@errmsg,@errno,0)
    rollback  transaction
end
go

---------Trigger on MAKE---------
Create trigger tri_MAKE on MAKE
FOR Insert, update
As
BEGIN 
	declare @errno int
	declare @errmsg varchar(255)
	declare @errmsg1 varchar(255)
	declare @cid int
	declare @rid int
	declare @n integer
	declare @ticN integer

	set @cid = (select U_ID from inserted)
	set @rid = (select RES_ID from inserted)

	if(exists(select * from _USER c, inserted i where c.U_ID = i.U_ID ))
	Begin
		print 'GOOD USER ID....'+ cast(@cid as char(5))
	end
	else
	Begin
		select @errno = 30001,
				   @errmsg = 'User id('+RTRIM(cast(@cid as char(15)))+') does not exists in "_USER".Cannot create MAKE!';
		raiserror(@errmsg,@errno,2)
		rollback  transaction
	end

	if(exists(select * from RESERVATION r, inserted i where r.RES_ID = i.RES_ID ))
	Begin
		print 'GOOD RESERVATION ID....'+ cast(@rid as char(5))
	end
	else
	Begin
		select @errno = 30002,
				   @errmsg = 'Reservation id('+@rid+') does not exists in "RESERVATION".Cannot create MAKE!';
		print @errmsg
		ROLLBACK
	end

	exec @n = totalMembersFor1Reserv @rid
	set @ticN = (select TIC_AMOUNT from RESERVATION r, inserted i where r.RES_ID = i.RES_ID)

	/*if @n = @ticN
	Begin
		print 'You make Good Reservation with right number of tickets ' + cast(@ticN as char(2))
	end
	else
	Begin
		
		select @errno = 30003,
					@errmsg = 'Please be sure of your Reservation TIC_Amount before make the Reservation.It is '+cast(@ticN as char(2)) + 'but it must be '+cast(@n as char(2))+'(you can use the procedure updateTic_Amount resID,new Amount).',
					@errmsg1 = 'Or be sure if it is the right User ID with the right Reservation ID.';
		print @errmsg
		print @errmsg1
		Rollback
	end*/

end
go

---------Trigger on AIRLINE---------

create trigger TD_AIRLINE on AIRLINE for delete as
begin
    declare
       @numrows  int,
       @errno    int,
       @errmsg   varchar(255)

    select  @numrows = @@rowcount
    if @numrows = 0
       return

    /*  Cannot delete parent "AIRLINE" if children still exist in "FLIGHT"  */
    if exists (select 1
               from   FLIGHT t2, deleted t1
               where  t2.A_ID = t1.A_ID)
       begin
          select @errno  = 50006,
                 @errmsg = 'Children still exist in "FLIGHT". Cannot delete parent "AIRLINE".'
          goto error
       end
	Begin
		print 'The Deleted Airline do not have any Flights'
	end


    return

/*  Errors handling  */
error:
    raiserror(@errmsg,@errno,0)
    rollback  transaction
end
go


create trigger TU_AIRLINE on AIRLINE for update as
begin
   declare
      @numrows  int,
      @numnull  int,
      @errno    int,
      @errmsg   varchar(255)

      select  @numrows = @@rowcount
      if @numrows = 0
         return

      /*  Cannot modify parent code in "AIRLINE" if children still exist in "FLIGHT"  */
      if update(A_ID)
      begin
         if exists (select 1
                    from   FLIGHT t2, inserted i1, deleted d1
                    where  t2.A_ID = d1.A_ID
                     and  (i1.A_ID != d1.A_ID))
            begin
               select @errno  = 50005,
                      @errmsg = 'Children still exist in "FLIGHT". Cannot modify parent code in "AIRLINE".'
               goto error
            end
      end


      return

/*  Errors handling  */
error:
    raiserror(@errmsg,@errno,0)
    rollback  transaction
end
go