
create table condition(
id int NOT NULL PRIMARY KEY IDENTITY(1,1),
result varchaR(50) NULL,
step varchar (50)  NULL

)





select *from condition
drop table CONDITION



create table Buyer(
BuyerId int NOT NULL PRIMARY KEY IDENTITY(100,1),
Name varchar(100)  NOT NULL CHECK (Name NOT LIKE '%[^A-Z ]%'),
Address varchar(100) NOT NULL,
Country varchar(50) NULL,
PhoneNumber varchar(11) null  CHECK (PhoneNumber NOT LIKE '%[^+0-9]%'),
EmailId varchar(50) unique NOT NULL ,
Pass varchar(100) null ,
Age  int null ,
Gender varchar(100) null
)
insert  into Buyer
values('Nusrat Khan','C2 alif Housing,Shymoli','Bangladesh','01711010101','nusratkhan.321@gmail.com','123',22,'female')
insert  into Buyer values('Mehedi Hasan','17 jatrabari,dhaka-1204','Bangladesh','01634634634','mmh21@gmail.com','123',22,'female'),
						('Rasel Mohammad','883/A karachi','Pakistan','09423167','rasel444@gmail.com','123',22,'female'),
						('Steven Burg','32/d-2, West Parker Road','China','+3081197','burgp@hotmail.com','123',22,'female'),
						('Shourav Ganguli','kolkata,west Bengal','India','8802935021','shourav5091@agni.com','123',22,'female'),
						('Virat Kohli','Luxury Mansion,Bombay','India','9130815','playerkohli@gmail.com','123',22,'female'),
						('Sakib Al Hasan','H-37,R-8,P-C,Bashundhara','Bangladesh','01634639634','cricket@gmail.com','123',22,'female'),
						('Mushfiq Rahim','Place Square,A2,Dhanmondi','Bangladesh','01634634000','rahim000@gmail.com','123',22,'female')
						
select *from buyer
select *from buyer  where BuyerId like '%7%' or  name like '%7%' or address like '%7%' or  country like '%7%' or  PhoneNumber like '%7%' or  EmailId like '%7%'
drop table buyer

create table userInfo(
p_id int NOT NULL PRIMARY key IDENTITY(1,1),
u_id int not null foreign key references buyer(buyerid),
)
select *from  userInfo
drop table userInfo


create table Cart(
c_id int NOT NULL PRIMARY key IDENTITY(1,1),
u_id int not null foreign key references buyer(buyerid),
ProductName varchar(100)  NOT NULL,
Size varchar(10)  NOT NULL,
Colour varchar(30)  NOT NULL,
Quantity int NOT NULL,
Unit_Price  money  NOT NULL,
Total_Price  money  NOT NULL
)

select *from cart
drop table cart

delete from cart 




create table ProductList(
Product_Id int Not null PRIMARY KEY,
ProductName varchar(100) NOT NULL,
Description varchar(500) NOT NULL,
Size varchar(50) NOT NULL,
Accessories varchar(100) NOT NULL,
MakingCost money NOT NULL  CHECK (MakingCost NOT LIKE '%[^0-9.]%'),
UnitPrice  money NOT NULL CHECK (UnitPrice NOT LIKE '%[^0-9.]%')
)

insert  into ProductList values('1','Full-Shirt','A loose, straight-cut, short sleeved shirt','S,M,L,XL',
'100% cotton fabric,8 Button,Lebel,Tag',3.50,5.75)
insert  into ProductList values('2','Half-Shirt','A loose, straight-cut, full sleeved shirt','S,M,L,XL',
			'90% cotton fabric,10 Button,Lebel,Tag',4.50,7.55),
			
			('3','Polo t-shirt','A loose, straight-cut, full sleeved shirt','S,M,L,XL',
			'90% cotton fabric,10 Button,Lebel,Tag',4.75,8.25)

select *from ProductList
drop table ProductList

create table Ordered(
OrderId int NOT NULL PRIMARY KEY IDENTITY(1000,1),
BuyerId int NOT NULL foreign key references buyer(buyerid),
Product_Id int NOT NULL ,
Product_Name varchar(100)  NULL ,
Size varchar(50)  NULL,
Colour varchar(30)   NULL,
Quantity int NOT NULL,
Unit_Price  money   NULL,

TotalAmount money NOT NULL,
OrderDate date Default getDate(),
DeliveryDate date Default getDate(),
OrderStatus varchar(50 )Default 'Received',
) 

insert  into Ordered values(101,1,'Full-Shirt','XL','Red',500,5.75,2875,'2020-10-12','2021-02-25','Processing')
insert  into Ordered values(102,2,'Half-Shirt','L','Blue',200,7.55,1510,'2020-10-12','2021-02-15','Processing')
insert  into Ordered values(101,3,'Polo t-shirt','M','Red',300,8.25,2475,'2020-10-13','2021-02-28','Processing')
insert  into Ordered values(104,1,'Full-Shirt','S','Purple',400,5.75,2300,'2020-10-13','2021-02-28','Processing')
insert  into Ordered values(105,3,'Polo t-shirt','M','Black',500,8.25,4125,'2020-10-14','2021-03-15','Processing')


insert  into Ordered values(105,1,'Full-Shirt','L','Blue',100,5.75,575,'2020-10-14','2021-03-15','Processing')

insert  into Ordered values(107,2,'Half-Shirt','M','Red',250,7.55,1888,'2020-10-14','2021-03-10','Processing')






delete from ordered


drop table  Ordered


select * from Ordered
delete from  Ordered
select *from cart where u_id in (select max(u_id) from userInfo)
select *from  userInfo
select *from ordered where BuyerId=(select u_id from userInfo where p_id=(select max(p_id)from userInfo))

SELECT * FROM ORDERED where DeliveryDate IN (SELECT MAX(DeliveryDate) FROM ORDERED)
select BuyerId,Transaction_Date from Transaction_Information

select BuyerId,Transaction_Date from Transaction_Information where Transaction_Date in (select max(Transaction)_Date from Transaction_Information)

select OrderId,b.Buyerid from Ordered o inner 
join Buyer b on  o.Buyerid=  b.Buyerid  

select *from Ordered where OrderDate between '2021-04-08'and'2021-05-12'
select sum(TotalAmount) as 'total' from Ordered

select sum(TotalCompleted) as 'totalC' from ProductionStatus
                        where OrderId in (select OrderId from Ordered where OrderDate between '2020-04-03' and '2021-04-02')


select   distinct b.buyerId  from Ordered, buyer b where  b.name like  '%mehedi%'
select sum(quantity) as 'total' from ordered where OrderDate like'2020-_2-__'
select DATEDIFF ( month , '2020-10-12' ,'2020-11-01')
select  getdate()
select * from Ordered
drop table Ordered





create table Employee(
Employee_Id int not null PRIMARY KEY check (Employee_Id > 0),
Name varchar(50) NOT NULL,
Age int NOT NULL check (Age Between 18 AND 45),
Designation varchar(50) NOT NULL,
JoiningDate date DEFAULT getdate(),
)
insert  into Employee values(1,'Jamal Molla',32,'Senior Cutter','2012-03-18'),
                            (2,'Rahim Mia',22,'Junior Iron Man','2018-01-10'),
							(3,'Lima Jahan',21,'sewing operator','2020-05-05'),
							(4,'Rashida Begum',27,'sewing operator','2020-03-05'),
							(5,'Khaleda Hasina',21,'sewing operator','2020-10-05'),
							(6,'Md. Jafor Ahmed',38,'Manager','2018-05-23'),
							(7,'Md. Babul Bepary',40,'senior manager','2017-12-09'),
							(8,'Sumon Islam',20,'sewing operator','2021-01-05'),
							(9,'Lima sultana',29,'sewing operator','2019-01-28'),
							(10,'Bokul Ahmed',42,'senior iron man','2019-05-10'),
							(11,'Jahangir alam',34,'Packaging Head','2018-07-11'),
							(12,'Kawsar Hasan',22,'Junior Cutter','2020-11-25')
							
select *from  Employee  
drop table Employee  

create table Transaction_Information  (
Transaction_Id int not null PRIMARY KEY check (Transaction_Id >2000),
OrderId int NOT NULL foreign key references ordered(orderid),
BuyerId int NOT NULL foreign key references buyer(buyerid),
TotalAmount money not null,
PaidAmount  money not null,
DueAmount  money not null,
Transaction_Date date  not null,
LastDate date not null,
) 
insert  into Transaction_Information values(2001,1000,101,2875,1500,1375,'2020-10-12','2020-12-31')
insert  into Transaction_Information values(2002,1001,102,1510,700,810,'2020-10-17','2020-12-31'),
											(2003,1002,101,2475,1500,975,'2020-10-17','2020-12-30'),
											(2004,1003,104,2300,1700,600,'2020-10-16','2020-12-20'),
											(2005,1005,105,575,575,0,'2020-10-20','2020-12-15'),
											(2006,1006,107,1888,1000,888,'2020-10-18','2020-12-20')
										
           
select *from Transaction_Information 
drop table Transaction_Information 

select *from ordered

create table Production (
Production_Id int not null PRIMARY KEY check (Production_Id > 3000),
OrderId int NOT NULL foreign key references ordered(orderid),
ProductId int NOT NULL foreign key references ProductList(Product_Id),
Employee_Id  int NOT NULL foreign key references Employee(Employee_Id),
CompletedProduct int NOT NULL,
TodaySalary money NOT NULL,
Date date DEFAULT getdate(), 
)

insert  into Production values(3001,1000,1,3,35,70,'2020-10-20'),
                             
                               (3002,1002,3,4,36,72,'2020-10-20'),
							   (3003,1003,1,5,48,96,'2020-10-20'),
							   (3004,1000,1,1,30,60,'2020-10-20'),
							   (3005,1000,1,2,25,50,'2020-10-20'),
							   (3006,1002,3,10,50,100,'2020-10-20'),
							   (3007,1002,3,9,52,102,'2020-10-20'),
							   (3008,1004,3,7,48,96,'2020-10-20'),
							   (3009,1004,3,8,48,96,'2020-10-20'),
							    (3010,1004,3,5,39,78,'2020-10-22'),
								   (3011,1005,1,1,30,60,'2020-10-22'),
								      (3012,1005,2,3,45,90,'2020-10-22'),
									     (3013,1005,1,7,35,70,'2020-10-22'),
										 (3014,1005,1,9,100,200,'2020-10-20'),

										 (3015,1002,3,10,70,140,'2020-11-02'),
										 (3016,1002,3,8,65,130,'2020-11-02'),
										 (3017,1002,3,6,50,100,'2020-11-02'),

										 (3018,1003,1,1,50,100,'2020-11-02'),
										 (3019,1003,1,5,70,140,'2020-11-02'),
										 (3020,1000,1,10,60,120,'2020-11-02'),
										 
										  (3021,1005,3,5,26,52,'2020-11-10'),
										   (3022,1003,3,2,35,70,'2020-11-10'),
										    (3023,1002,3,4,40,80,'2020-11-10'),
											 (3024,1002,3,10,26,52,'2020-11-10'),
											  (3025,1002,3,2,45,90,'2020-11-10'),
											    (30026,1001,2,4,35,70,'2020-10-20'),  
												  (30027,1006,2,9,35,70,'2020-10-25')


								
								 
								
select *from Production 
drop table Production 
select sum(CompletedProduct) as 'sum' from Production where date between'2020-10-20' and '2020-10-21'

select Count(Employee_Id) from employee

select Employee_Id , sum(CompletedProduct) as 'com' from Production group by Employee_Id having Employee_Id in (select Employee_Id from Production where date between '2020-10-01' and '2020-10-30') 


 select Employee_Id, sum(CompletedProduct) as 'com',sum(todaySalary) as 'coms' from Production group by Employee_Id having Employee_Id in  (select Employee_Id from Production where Date between '2020-10-01' and '2020-10-30')


create table ProductionStatus  (
ProductionStatus_ID int not null PRIMARY KEY check(ProductionStatus_Id >500),
OrderId int NOT NULL foreign key references ordered(orderid),
ProductId int NOT NULL foreign key references ProductList(Product_Id),
Production_Date date NOT NULL,
TotalOrder int NOT NULL,
TodayCompeted int NOT NULL,
TotalCompleted int NOT NULL,
uncompleted int NOT NULL,
LastDate date NOT NULL,
)

insert  into ProductionStatus values(501,1000,1,'2020-10-20',500,35,35,465,'2021-12-02')
insert  into ProductionStatus values(502,1001,2,'2020-10-20',200,45,45,155,'2021-02-08'),
									(503,1002,3,'2020-10-20',300,120,120,180,'2021-04-18'),
									(504,1003,1,'2020-10-22',400,250,250,150,'2021-03-01'),
									(505,1004,3,'2020-11-02',500,100,100,400,'2021-01-10')

insert  into ProductionStatus values(506,1001,1,'2020-11-02',500,65,100,400,'2021-02-25'),
									(507,1002,3,'2020-11-10',300,80,200,100,'2021-05-20'),
									(508, 1003,1,'2020-11-12',400,100,350,	50,	'2020-12-01')

insert  into ProductionStatus values(509,1003,1,'2020-11-14',400,50,400,0,'2021-02-25'),
                                     (510,1002,3,'2020-11-14',300,100,300,0,'2021-02-25')
                                  

select *from ordered
drop table CONDITION
select *from ProductionStatus
drop table ProductionStatus

create table ShipmentInfo  (
OrderId int NOT NULL PRIMARY KEY foreign key references ordered(orderid) check (OrderId >1000) ,
ProductionStatus_ID int NOT NULL foreign key references ProductionStatus(ProductionStatus_ID),
DeliveryAddress varchar(500) NOT NULL,
Shipment_Date date NOT NULL,
ShipmentChannel varchar(50) NOT NULL,
ShipmentCost money  NOT NULL,
DeliveryStatus varchar(50) NOT NULL,
DeliveryDate date NOT NULL
)
insert  into ShipmentInfo values(1002,510,'883/A karachi,Pakistan','2020-11-25','container shipping',500,'Delivered','2020-12-20')

insert  into ShipmentInfo values	(1003,509,'Luxury Mansion,Bombay,India','2020-11-25','container shipping',500,'Delivered','2020-12-10')

select *from ShipmentInfo
drop table ShipmentInfo
SELECT *FROM ORDERED

