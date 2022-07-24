create database FoodOrder
go

use FoodOrder
go

--use master
--go
--drop database FoodOrder
--go


create table [User](
	userId int identity(1, 1) primary key,
	userName nvarchar(max)
)

create table Food(
	foodId int identity(1, 1) primary key,
	foodName varchar(50),
	foodCost float,
	--foodUrlIMG varchar(max)
)

create table [Order](
	orderId int identity(1, 1) primary key,
	totalMoney float
)

create table FoodOrder(
	foodOrderId int identity(1, 1) primary key,
	foodId int,
	orderId int,
	quantity int
)

create table UserOrder(
	userOrderId int identity(1, 1) primary key,
	userId int,
	orderId int
)

alter table FoodOrder
add foreign key (foodId) references Food(foodId)

alter table FoodOrder
add foreign key (orderId) references [Order](orderId)

alter table UserOrder
add foreign key (userId) references [User](userId)

alter table UserOrder
add foreign key (orderId) references [Order](orderId)


insert into Food
values 
	('Fish', 100000),
	('Meat', 150000),
	('Vegetable', 50000)


insert into [User]
values
	('Gia Minh'),
	('Bao Khanh')

insert into [Order]
values
	(0),
	(0)


insert into FoodOrder
values 
	(3, 1, 5),	
	(1, 1, 10),
	(2, 1, 7),
	(1, 2, 10),
	(3, 2, 5)

insert into UserOrder
values
	(1, 2),
	(2, 1)

select * from [User];
select * from [Order];
select * from FoodOrder;
select * from UserOrder;
select * from Food;


go
create function getTableOfMoneyForEachFoodInAnOrder
(@orderId int)
returns table
as
	return select quantity*(select foodCost from Food where Food.foodId = FoodOrder.foodId) as [money] from FoodOrder where FoodOrder.orderId = @orderId

go




select * from FoodOrder
go
-- trigger when insert into FoodOrder
create trigger update_money_when_insert on FoodOrder after insert
as
begin
	declare @orderId int;
	select @orderId = orderId from inserted
	declare @totalMoney int;
	select @totalMoney = sum([money]) from getTableOfMoneyForEachFoodInAnOrder(@orderId)
	update  [Order] set totalMoney = @totalMoney where [Order].orderId = @orderId
end
-- trigger when delete from FoodOrder
go
create trigger update_money_when_delete on FoodOrder after delete
as
begin
	declare @orderId int;
	select @orderId = orderId from deleted
	declare @totalMoney int;
	select @totalMoney = sum([money]) from getTableOfMoneyForEachFoodInAnOrder(@orderId)
	update  [Order] set totalMoney = @totalMoney where [Order].orderId = @orderId
end
-- trigger when update from FoodOrder
go
create trigger update_money_when_update on FoodOrder after update
as
begin
	declare @orderId int;
	select @orderId = orderId from deleted
	declare @totalMoney int;
	select @totalMoney = sum([money]) from getTableOfMoneyForEachFoodInAnOrder(@orderId)
	update  [Order] set totalMoney = @totalMoney where [Order].orderId = @orderId
end




select * from [order]
--insert into FoodOrder values (3, 2, 5)
--update FoodOrder set quantity = 1 where foodOrderId = 6
select * from FoodOrder 	
--select * from Food

