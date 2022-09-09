use ABS
go


CREATE function AbsentsInFlight(@f int)
returns varchar(300)
Begin


declare @nbr integer
declare @mess varchar(300)

if not exists(select * from FLIGHT where F_NO = @f)
Begin
		set @mess ='There is no Flight with this number('+RTRIM(@f)+').'
		return @mess
end

if(Select count(*) From PASSENGER P where P.F_NO = @f) = 0
	Begin
		set @mess ='There is no Passengers in this Flight('+RTRIM(@f)+').'
		return @mess
	end
set @nbr=0

declare @C_ID int,@C_USERNAME char(30),@C_FULLNAME char(30),@gend char(10),@age int,@children int

DECLARE cus_cursor CURSOR
FOR
select c.U_ID,c.U_username,c.U_FULLNAME,c.U_GENDER,c.U_AGE,c.U_CHILDREN
from _USER c,MAKE m ,RESERVATION r
where m.U_ID = c.U_ID and m.RES_ID = r.RES_ID and r.F_NO = @f
order by c.U_ID

OPEN cus_cursor
FETCH NEXT FROM cus_cursor INTO  @C_ID,@C_USERNAME,@C_FULLNAME,@gend,@age,@children
WHILE @@FETCH_STATUS=0
BEGIN
      if not exists(select * from PASSENGER where P_Username=@C_USERNAME)
	  begin
	      set @mess = COALESCE(@mess, '') +'-- Customer id: '+RTRIM(cast(@C_ID as char(5))) + '  Username: '+RTRIM(@C_USERNAME) + '  Fullname: '+RTRIM(@C_FULLNAME) + '  Children: '+ cast(@children as char(1))+ char(10)
		  set @nbr += 1
		  set @nbr += @children
	  end
	FETCH NEXT FROM cus_cursor INTO  @C_ID,@C_USERNAME,@C_FULLNAME,@gend,@age,@children
	if @@FETCH_STATUS != 0
	begin
		set @mess = COALESCE(@mess, '') + 'The Absents total number in this Flight('+RTRIM(@f)+') is ' +cast(@nbr as char(1))+'.'
	end
END

CLOSE cus_cursor
DEALLOCATE cus_cursor
if @nbr = 0
Begin
	set @mess ='There is no Absents in this Flight('+RTRIM(@f)+').'
	return @mess
end

return @mess
end
go



CREATE function totalMembersFor1Reserv(@r int)
returns int
BEGIN
	declare @nbr integer
	set @nbr = (select count(*) from MAKE where RES_ID = @r)
	
	Declare chCurser CURSOR For select U_CHILDREN from _USER c,MAKE m where c.U_ID = m.U_ID and m.RES_ID = @r

	declare @count integer
	--declare @c integer
	--set @c = 0

	open chCurser  
	FETCH NEXT FROM chCurser into @count
	WHILE @@FETCH_STATUS = 0 
	BEGIN 
		set @nbr += @count
		FETCH NEXT FROM chCurser into @count
	END
	close chCurser
	deallocate chCurser

	return @nbr
end
go


CREATE function totalNumberOfPassenger(@f int)
returns varchar(300)
BEGIN
	declare @nbr integer
	declare @mess varchar(300)
	set @nbr=0
	if(Select count(*) From PASSENGER P where P.F_NO = @f) = 0
	Begin
		set @mess ='There is no Passengers in this Flight('+RTRIM(@f)+').'
		return @mess
	end

	Declare PassengerF cursor 
	for 
	Select P.P_ID,P.P_Username, P.P_FULLNAME, P.P_CHILDREN
		From PASSENGER P
		where P.F_NO = @f
		order by P.P_ID

set @nbr += (select count(*) from PASSENGER where F_NO = @f)
declare @pid int,@pusername char(15) ,@pname varchar(15) , @children int

open PassengerF 
FETCH NEXT FROM PassengerF  INTO @pid,@pusername, @pname, @children

WHILE @@FETCH_STATUS = 0 
BEGIN 
	
	set @mess = COALESCE(@mess, '') +'-- Passenger id: '+RTRIM(cast(@pid as char(4)))+ '    Username: '+RTRIM(@pusername) + ' Fullname: '+RTRIM(@pname) + '  Children: '+ cast(@children as char(1))+ char(10)
	
	set @nbr += @children

	FETCH NEXT FROM PassengerF  INTO @pid, @pusername,@pname, @children 
	if @@FETCH_STATUS != 0
	begin
		set @mess = COALESCE(@mess, '') + 'The Passenger total number in this Flight('+RTRIM(@f)+') is ' +cast(@nbr as char(1))+'.'
	end
END 
close PassengerF
deallocate PassengerF

return @mess


end
go


CREATE function whoElseReserv(@r int)
returns integer
BEGIN
	declare @nbr integer
	set @nbr = (select count(*) from MAKE where RES_ID = @r)
	return @nbr
end
go


create procedure updateTic_Amount @ResID int, @newAm int
AS
Begin
	Begin Tran
	update RESERVATION set TIC_AMOUNT=@newAm where RES_ID=@ResID
	commit tran
end

--exec updateTic_Amount 1,2

/*

declare @mee char(300)
exec @mee = totalNumberOfPassenger 1
print @mee

*/