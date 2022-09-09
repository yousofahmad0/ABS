use ABS
go

---------INDEX   FLIGHT---------------
create index BELONG_FK on FLIGHT (
A_ID ASC
);

---------INDEX    RESERVATION-------------
create index RESERVATION_FK on RESERVATION (
F_NO ASC
);


---------INDEXES   MAKE------------------
create index MAKE_FK on MAKE (
U_ID ASC
);

create index MAKE2_FK on MAKE (
RES_ID ASC
);


---------INDEXES    PASSENGER---------------
create index PASSENGER_FK on PASSENGER (
F_NO ASC
);

