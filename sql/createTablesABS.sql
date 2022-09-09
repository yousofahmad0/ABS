use ABS
Go
/*==============================================================*/
/* DBMS name:      SAP SQL Anywhere 17                          */
/* Created on:     1/8/2022 6:11:44 PM                          */
/*==============================================================*/


/*==============================================================*/
/* Table: AIRLINE                                               */
/*==============================================================*/

create table AIRLINE
(
   A_ID                  INT                         not null IDENTITY( 1 , 1 ),
   A_NAME	varchar(30)		null,
   constraint PK_AIRLINE primary key clustered (A_ID)
);

/*==============================================================*/
/* Table: user*/
/*==============================================================*/
create table _USER
(
   U_ID                 INT							   not null IDENTITY( 1 , 1 ),
   U_username			varchar(30)					   null unique,
   U_password			varchar(30)					   null,
   U_FULLNAME           varchar(30)					   null,
   U_GENDER             varchar(10)                       null,
   U_ADDRESS            varchar(20)                       null,
   U_AGE				integer						   null,
   U_CHILDREN			integer						   null,
   U_PHONE			integer							   null,
   constraint PK_CUSTOMER primary key clustered (U_ID)
);

/*==============================================================*/
/* Table: FLIGHT                                                */
/*==============================================================*/
create table FLIGHT 
(
   F_NO                  INT                        not null,
   F_DATE                varchar(35)                      null,
   A_ID                 INT                        not null,
   F_DEST               varchar(20)                       null,
   F_START              varchar(12)                       null,
   F_ARRIVAL            varchar(12)                       null,
   F_CTHRO              varchar(100)                      null,
   NO_OF_SEATS          integer                        null,
   F_DEFAULTPRICE       double precision                       null,

   constraint PK_FLIGHT primary key clustered (F_NO),
   foreign key(A_ID) References AIRLINE(A_ID)

);

/*==============================================================*/
/* Table: RESERVATION                                           */
/*==============================================================*/
create table RESERVATION 
(
   RES_ID                INT                         not null IDENTITY( 1 , 1 ),
   TIC_AMOUNT           integer		                   null,
   TOTAL_PRICE			double precision		null,
   RES_DATE             varchar(50)                           null,
   F_NO                 INT                        not null,
   CLASS                varchar(3)                       null,
   TIC_DESTINATION      varchar(30)                       null,
   constraint PK_RESERVATION primary key clustered (RES_ID),
   constraint FK__RESERVATION_FNO foreign key(F_NO) References FLIGHT(F_NO)
);


/*==============================================================*/
/* Table: MAKE                                                  */
/*==============================================================*/
create table MAKE 
(
   U_ID                  INT                     not null,
   RES_ID               INT                        not null,
   constraint PK_MAKE primary key clustered (U_ID, RES_ID),
   foreign key(U_ID) References _USER(U_ID),
   foreign key(RES_ID) References RESERVATION(RES_ID)
);

/*==============================================================*/
/* Table: PASSENGER                                             */
/*==============================================================*/
create table PASSENGER 
(
   P_ID                  INT                         not null IDENTITY( 1 , 1 ),
   P_Username		    varchar(15)                       null unique,
   P_FULLNAME               varchar(15)                       null,
   F_NO                 int                        not null,
   P_GENDER             char(10)                       null,
   P_ADDRESS            char(25)                       null,
   P_AGE				integer                        null,
   P_CHILDREN			integer						   null,
   constraint PK_PASSENGER primary key clustered (P_ID),
   constraint FK__PASSENGER_FNO_FDATE foreign key(F_NO) References FLIGHT(F_NO)
   
);