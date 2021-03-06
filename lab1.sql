USE [YellowMoonShopDB]
GO
/****** Object:  Table [dbo].[tbl_Cake]    Script Date: 9/22/2021 9:49:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Cake](
	[cakeID] [int] IDENTITY(1,1) NOT NULL,
	[cakeName] [nvarchar](50) NULL,
	[categoryID] [int] NULL,
	[cakeImage] [nvarchar](100) NULL,
	[description] [nvarchar](50) NULL,
	[price] [int] NULL,
	[quantity] [int] NULL,
	[createDate] [date] NULL,
	[expirationDate] [date] NULL,
	[statusID] [int] NULL,
	[lastModified] [date] NULL,
	[lastModifier] [varchar](50) NULL,
 CONSTRAINT [PK_tbl_Cake] PRIMARY KEY CLUSTERED 
(
	[cakeID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_CakeStatus]    Script Date: 9/22/2021 9:49:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_CakeStatus](
	[statusID] [int] IDENTITY(1,1) NOT NULL,
	[statusName] [nvarchar](50) NULL,
 CONSTRAINT [PK_tbl_CakeStatus] PRIMARY KEY CLUSTERED 
(
	[statusID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_Category]    Script Date: 9/22/2021 9:49:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Category](
	[categoryID] [int] IDENTITY(1,1) NOT NULL,
	[categoryName] [nvarchar](50) NULL,
 CONSTRAINT [PK_tbl_Category] PRIMARY KEY CLUSTERED 
(
	[categoryID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_Order]    Script Date: 9/22/2021 9:49:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Order](
	[orderID] [int] IDENTITY(1,1) NOT NULL,
	[email] [varchar](50) NULL,
	[customerName] [nvarchar](50) NULL,
	[address] [nvarchar](50) NULL,
	[phoneNumber] [nvarchar](50) NULL,
	[orderDate] [datetime] NULL,
	[total] [int] NULL,
	[paymentMethodID] [int] NULL,
	[paymentStatusID] [int] NULL,
 CONSTRAINT [PK_tbl_Order] PRIMARY KEY CLUSTERED 
(
	[orderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_OrderDetail]    Script Date: 9/22/2021 9:49:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_OrderDetail](
	[orderID] [int] NOT NULL,
	[cakeID] [int] NULL,
	[quantity] [int] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_PaymentMethod]    Script Date: 9/22/2021 9:49:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_PaymentMethod](
	[paymentID] [int] IDENTITY(1,1) NOT NULL,
	[paymentName] [nvarchar](50) NULL,
 CONSTRAINT [PK_tbl_PaymentMethod] PRIMARY KEY CLUSTERED 
(
	[paymentID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_PaymentStatus]    Script Date: 9/22/2021 9:49:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_PaymentStatus](
	[statusID] [int] IDENTITY(1,1) NOT NULL,
	[statusName] [nvarchar](50) NULL,
 CONSTRAINT [PK_tbl_PaymentStatus] PRIMARY KEY CLUSTERED 
(
	[statusID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_Role]    Script Date: 9/22/2021 9:49:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Role](
	[roleId] [int] NOT NULL,
	[roleName] [nvarchar](50) NULL,
 CONSTRAINT [PK_tbl_Role] PRIMARY KEY CLUSTERED 
(
	[roleId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_User]    Script Date: 9/22/2021 9:49:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_User](
	[email] [varchar](50) NOT NULL,
	[password] [varchar](250) NULL,
	[fullName] [varchar](50) NULL,
	[roleId] [int] NULL,
	[phoneNumber] [nvarchar](50) NULL,
	[address] [nvarchar](50) NULL,
 CONSTRAINT [PK_tbl_User] PRIMARY KEY CLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[tbl_Cake] ON 

INSERT [dbo].[tbl_Cake] ([cakeID], [cakeName], [categoryID], [cakeImage], [description], [price], [quantity], [createDate], [expirationDate], [statusID], [lastModified], [lastModifier]) VALUES (2, N'Chocolate Black', 1, N'cake1.jpg', N'', 2, 8, CAST(N'2021-09-14' AS Date), CAST(N'2021-09-23' AS Date), 1, CAST(N'2021-09-21' AS Date), N'admin@gmail.com')
INSERT [dbo].[tbl_Cake] ([cakeID], [cakeName], [categoryID], [cakeImage], [description], [price], [quantity], [createDate], [expirationDate], [statusID], [lastModified], [lastModifier]) VALUES (13, N'Black Forest', 1, N'cake1.jpg', N'', 20, 8, CAST(N'2021-09-16' AS Date), CAST(N'2021-09-20' AS Date), 1, CAST(N'2021-09-21' AS Date), N'admin@gmail.com')
INSERT [dbo].[tbl_Cake] ([cakeID], [cakeName], [categoryID], [cakeImage], [description], [price], [quantity], [createDate], [expirationDate], [statusID], [lastModified], [lastModifier]) VALUES (14, N'White Forest', 2, N'cake1.jpg', N'', 123, 120, CAST(N'2021-09-21' AS Date), CAST(N'2021-09-21' AS Date), 1, CAST(N'2021-09-21' AS Date), N'admin@gmail.com')
INSERT [dbo].[tbl_Cake] ([cakeID], [cakeName], [categoryID], [cakeImage], [description], [price], [quantity], [createDate], [expirationDate], [statusID], [lastModified], [lastModifier]) VALUES (15, N'Chocolate White', 1, N'cake2.jpg', N'', 123, 118, CAST(N'2021-09-21' AS Date), CAST(N'2021-09-23' AS Date), 1, CAST(N'2021-09-21' AS Date), N'admin@gmail.com')
INSERT [dbo].[tbl_Cake] ([cakeID], [cakeName], [categoryID], [cakeImage], [description], [price], [quantity], [createDate], [expirationDate], [statusID], [lastModified], [lastModifier]) VALUES (17, N'Red Velvet', 2, N'cake1.jpg', N'', 123, 9, CAST(N'2021-09-22' AS Date), CAST(N'2021-09-25' AS Date), 1, CAST(N'2021-09-22' AS Date), N'admin@gmail.com')
INSERT [dbo].[tbl_Cake] ([cakeID], [cakeName], [categoryID], [cakeImage], [description], [price], [quantity], [createDate], [expirationDate], [statusID], [lastModified], [lastModifier]) VALUES (18, N'Yellow Butter Cake', 2, N'cake1.jpg', N'', 123, 8, CAST(N'2021-09-22' AS Date), CAST(N'2021-09-25' AS Date), 1, CAST(N'2021-09-22' AS Date), N'admin@gmail.com')
INSERT [dbo].[tbl_Cake] ([cakeID], [cakeName], [categoryID], [cakeImage], [description], [price], [quantity], [createDate], [expirationDate], [statusID], [lastModified], [lastModifier]) VALUES (19, N'Pound Cake
', 2, N'cake1.jpg', N'', 123, 8, CAST(N'2021-09-22' AS Date), CAST(N'2021-09-25' AS Date), 1, CAST(N'2021-09-22' AS Date), N'admin@gmail.com')
INSERT [dbo].[tbl_Cake] ([cakeID], [cakeName], [categoryID], [cakeImage], [description], [price], [quantity], [createDate], [expirationDate], [statusID], [lastModified], [lastModifier]) VALUES (20, N'Red Velvet', 2, N'cake1.jpg', N'', 123, 9, CAST(N'2021-09-22' AS Date), CAST(N'2021-09-25' AS Date), 1, CAST(N'2021-09-22' AS Date), N'admin@gmail.com')
INSERT [dbo].[tbl_Cake] ([cakeID], [cakeName], [categoryID], [cakeImage], [description], [price], [quantity], [createDate], [expirationDate], [statusID], [lastModified], [lastModifier]) VALUES (21, N'Red Velvet Cake
', 2, N'cake1.jpg', N'', 123, 7, CAST(N'2021-09-22' AS Date), CAST(N'2021-09-25' AS Date), 1, CAST(N'2021-09-22' AS Date), N'admin@gmail.com')
INSERT [dbo].[tbl_Cake] ([cakeID], [cakeName], [categoryID], [cakeImage], [description], [price], [quantity], [createDate], [expirationDate], [statusID], [lastModified], [lastModifier]) VALUES (22, N'Carrot Cake
', 2, N'cake1.jpg', N'', 123, 9, CAST(N'2021-09-22' AS Date), CAST(N'2021-09-25' AS Date), 1, CAST(N'2021-09-22' AS Date), N'admin@gmail.com')
INSERT [dbo].[tbl_Cake] ([cakeID], [cakeName], [categoryID], [cakeImage], [description], [price], [quantity], [createDate], [expirationDate], [statusID], [lastModified], [lastModifier]) VALUES (23, N'Sponge Cake', 2, N'cake1.jpg', N'', 123, 9, CAST(N'2021-09-22' AS Date), CAST(N'2021-09-25' AS Date), 1, CAST(N'2021-09-22' AS Date), N'admin@gmail.com')
INSERT [dbo].[tbl_Cake] ([cakeID], [cakeName], [categoryID], [cakeImage], [description], [price], [quantity], [createDate], [expirationDate], [statusID], [lastModified], [lastModifier]) VALUES (24, N'Genoise Cake', 2, N'cake1.jpg', N'', 123, 9, CAST(N'2021-09-22' AS Date), CAST(N'2021-09-25' AS Date), 1, CAST(N'2021-09-22' AS Date), N'admin@gmail.com')
INSERT [dbo].[tbl_Cake] ([cakeID], [cakeName], [categoryID], [cakeImage], [description], [price], [quantity], [createDate], [expirationDate], [statusID], [lastModified], [lastModifier]) VALUES (25, N'Chiffon Cake', 2, N'cake1.jpg', N'', 123, 9, CAST(N'2021-09-22' AS Date), CAST(N'2021-09-25' AS Date), 1, CAST(N'2021-09-22' AS Date), N'admin@gmail.com')
INSERT [dbo].[tbl_Cake] ([cakeID], [cakeName], [categoryID], [cakeImage], [description], [price], [quantity], [createDate], [expirationDate], [statusID], [lastModified], [lastModifier]) VALUES (26, N'Flourless Cake', 2, N'cake1.jpg', N'', 123, 9, CAST(N'2021-09-22' AS Date), CAST(N'2021-09-25' AS Date), 1, CAST(N'2021-09-22' AS Date), N'admin@gmail.com')
INSERT [dbo].[tbl_Cake] ([cakeID], [cakeName], [categoryID], [cakeImage], [description], [price], [quantity], [createDate], [expirationDate], [statusID], [lastModified], [lastModifier]) VALUES (27, N'Upside Down Cake', 2, N'cake1.jpg', N'', 123, 9, CAST(N'2021-09-22' AS Date), CAST(N'2021-09-25' AS Date), 1, CAST(N'2021-09-22' AS Date), N'admin@gmail.com')
INSERT [dbo].[tbl_Cake] ([cakeID], [cakeName], [categoryID], [cakeImage], [description], [price], [quantity], [createDate], [expirationDate], [statusID], [lastModified], [lastModifier]) VALUES (28, N'Devil’s Food Cake', 2, N'cake1.jpg', N'', 123, 9, CAST(N'2021-09-22' AS Date), CAST(N'2021-09-25' AS Date), 1, CAST(N'2021-09-22' AS Date), N'admin@gmail.com')
INSERT [dbo].[tbl_Cake] ([cakeID], [cakeName], [categoryID], [cakeImage], [description], [price], [quantity], [createDate], [expirationDate], [statusID], [lastModified], [lastModifier]) VALUES (29, N'Hummingbird Cake', 2, N'cake1.jpg', N'', 123, 9, CAST(N'2021-09-22' AS Date), CAST(N'2021-09-25' AS Date), 1, CAST(N'2021-09-22' AS Date), N'admin@gmail.com')
INSERT [dbo].[tbl_Cake] ([cakeID], [cakeName], [categoryID], [cakeImage], [description], [price], [quantity], [createDate], [expirationDate], [statusID], [lastModified], [lastModifier]) VALUES (30, N'Opera Cake', 2, N'cake1.jpg', N'', 123, 9, CAST(N'2021-09-22' AS Date), CAST(N'2021-09-25' AS Date), 1, CAST(N'2021-09-22' AS Date), N'admin@gmail.com')
INSERT [dbo].[tbl_Cake] ([cakeID], [cakeName], [categoryID], [cakeImage], [description], [price], [quantity], [createDate], [expirationDate], [statusID], [lastModified], [lastModifier]) VALUES (31, N'Lady Baltimore Cake', 2, N'cake1.jpg', N'', 123, 9, CAST(N'2021-09-22' AS Date), CAST(N'2021-09-25' AS Date), 1, CAST(N'2021-09-22' AS Date), N'admin@gmail.com')
INSERT [dbo].[tbl_Cake] ([cakeID], [cakeName], [categoryID], [cakeImage], [description], [price], [quantity], [createDate], [expirationDate], [statusID], [lastModified], [lastModifier]) VALUES (32, N'Fruit Cake', 2, N'cake1.jpg', N'', 123, 9, CAST(N'2021-09-22' AS Date), CAST(N'2021-09-25' AS Date), 1, CAST(N'2021-09-22' AS Date), N'admin@gmail.com')
INSERT [dbo].[tbl_Cake] ([cakeID], [cakeName], [categoryID], [cakeImage], [description], [price], [quantity], [createDate], [expirationDate], [statusID], [lastModified], [lastModifier]) VALUES (51, N'Red Velvet', 2, N'cake1.jpg', N'', 123, 9, CAST(N'2021-09-22' AS Date), CAST(N'2021-09-25' AS Date), 1, CAST(N'2021-09-22' AS Date), N'admin@gmail.com')
INSERT [dbo].[tbl_Cake] ([cakeID], [cakeName], [categoryID], [cakeImage], [description], [price], [quantity], [createDate], [expirationDate], [statusID], [lastModified], [lastModifier]) VALUES (52, N'Mina Cake', 2, N'cake1.jpg', N'', 123, 9, CAST(N'2021-09-22' AS Date), CAST(N'2021-09-25' AS Date), 1, CAST(N'2021-09-22' AS Date), N'admin@gmail.com')
INSERT [dbo].[tbl_Cake] ([cakeID], [cakeName], [categoryID], [cakeImage], [description], [price], [quantity], [createDate], [expirationDate], [statusID], [lastModified], [lastModifier]) VALUES (53, N'Yellow Cake', 2, N'cake1.jpg', N'', 123, 9, CAST(N'2021-09-22' AS Date), CAST(N'2021-09-25' AS Date), 1, CAST(N'2021-09-22' AS Date), N'admin@gmail.com')
SET IDENTITY_INSERT [dbo].[tbl_Cake] OFF
GO
SET IDENTITY_INSERT [dbo].[tbl_CakeStatus] ON 

INSERT [dbo].[tbl_CakeStatus] ([statusID], [statusName]) VALUES (1, N'ACTIVE')
INSERT [dbo].[tbl_CakeStatus] ([statusID], [statusName]) VALUES (2, N'INACTIVE')
SET IDENTITY_INSERT [dbo].[tbl_CakeStatus] OFF
GO
SET IDENTITY_INSERT [dbo].[tbl_Category] ON 

INSERT [dbo].[tbl_Category] ([categoryID], [categoryName]) VALUES (1, N'bakery')
INSERT [dbo].[tbl_Category] ([categoryID], [categoryName]) VALUES (2, N'treats')
SET IDENTITY_INSERT [dbo].[tbl_Category] OFF
GO
SET IDENTITY_INSERT [dbo].[tbl_Order] ON 

INSERT [dbo].[tbl_Order] ([orderID], [email], [customerName], [address], [phoneNumber], [orderDate], [total], [paymentMethodID], [paymentStatusID]) VALUES (1, N'a@gmail.com', N'Nguyen Van A', N'Viet Nam', N'0123456789', CAST(N'2021-09-20T17:14:54.000' AS DateTime), 35, 1, 2)
INSERT [dbo].[tbl_Order] ([orderID], [email], [customerName], [address], [phoneNumber], [orderDate], [total], [paymentMethodID], [paymentStatusID]) VALUES (2, N'a@gmail.com', N'Nguyen Van A', N'Viet Nam', N'0123456789', CAST(N'2021-09-20T17:16:55.000' AS DateTime), 65, 1, 2)
INSERT [dbo].[tbl_Order] ([orderID], [email], [customerName], [address], [phoneNumber], [orderDate], [total], [paymentMethodID], [paymentStatusID]) VALUES (3, NULL, N'tu tai', N'vietnam', N'0123456789', CAST(N'2021-09-20T17:56:38.000' AS DateTime), 185, 1, 2)
INSERT [dbo].[tbl_Order] ([orderID], [email], [customerName], [address], [phoneNumber], [orderDate], [total], [paymentMethodID], [paymentStatusID]) VALUES (4, N'a@gmail.com', N'Nguyen Van A', N'Viet Nam', N'0123456789', CAST(N'2021-09-21T09:46:30.000' AS DateTime), 20, 1, 2)
INSERT [dbo].[tbl_Order] ([orderID], [email], [customerName], [address], [phoneNumber], [orderDate], [total], [paymentMethodID], [paymentStatusID]) VALUES (6, N'a@gmail.com', N'Nguyen Van A', N'Viet Nam', N'0123456789', CAST(N'2021-09-21T11:59:26.000' AS DateTime), 15, 2, 2)
INSERT [dbo].[tbl_Order] ([orderID], [email], [customerName], [address], [phoneNumber], [orderDate], [total], [paymentMethodID], [paymentStatusID]) VALUES (7, N'a@gmail.com', N'Nguyen Van A', N'Viet Nam', N'0123456789', CAST(N'2021-09-21T00:00:00.000' AS DateTime), 153, 1, 2)
INSERT [dbo].[tbl_Order] ([orderID], [email], [customerName], [address], [phoneNumber], [orderDate], [total], [paymentMethodID], [paymentStatusID]) VALUES (8, N'a@gmail.com', N'Nguyen Van A', N'Viet Nam', N'0123456789', CAST(N'2021-09-21T22:10:34.970' AS DateTime), 261, 1, 2)
INSERT [dbo].[tbl_Order] ([orderID], [email], [customerName], [address], [phoneNumber], [orderDate], [total], [paymentMethodID], [paymentStatusID]) VALUES (9, NULL, N'tu tai', N'Long An', N'0123456789', CAST(N'2021-09-22T20:50:24.487' AS DateTime), 248, 1, 2)
INSERT [dbo].[tbl_Order] ([orderID], [email], [customerName], [address], [phoneNumber], [orderDate], [total], [paymentMethodID], [paymentStatusID]) VALUES (10, N'b@gmail.com', N'Nguyen Van B', N'Viet Nam', N'0222222222', CAST(N'2021-09-22T21:21:53.873' AS DateTime), 496, 1, 2)
INSERT [dbo].[tbl_Order] ([orderID], [email], [customerName], [address], [phoneNumber], [orderDate], [total], [paymentMethodID], [paymentStatusID]) VALUES (11, N'a@gmail.com', N'Nguyen Van A', N'Viet Nam', N'0123456789', CAST(N'2021-09-22T21:38:01.170' AS DateTime), 492, 1, 2)
SET IDENTITY_INSERT [dbo].[tbl_Order] OFF
GO
INSERT [dbo].[tbl_OrderDetail] ([orderID], [cakeID], [quantity]) VALUES (1, 2, 1)
INSERT [dbo].[tbl_OrderDetail] ([orderID], [cakeID], [quantity]) VALUES (1, 13, 1)
INSERT [dbo].[tbl_OrderDetail] ([orderID], [cakeID], [quantity]) VALUES (2, 2, 3)
INSERT [dbo].[tbl_OrderDetail] ([orderID], [cakeID], [quantity]) VALUES (2, 13, 1)
INSERT [dbo].[tbl_OrderDetail] ([orderID], [cakeID], [quantity]) VALUES (3, 2, 3)
INSERT [dbo].[tbl_OrderDetail] ([orderID], [cakeID], [quantity]) VALUES (3, 13, 7)
INSERT [dbo].[tbl_OrderDetail] ([orderID], [cakeID], [quantity]) VALUES (4, 13, 1)
INSERT [dbo].[tbl_OrderDetail] ([orderID], [cakeID], [quantity]) VALUES (6, 2, 1)
INSERT [dbo].[tbl_OrderDetail] ([orderID], [cakeID], [quantity]) VALUES (7, 2, 2)
INSERT [dbo].[tbl_OrderDetail] ([orderID], [cakeID], [quantity]) VALUES (8, 2, 1)
INSERT [dbo].[tbl_OrderDetail] ([orderID], [cakeID], [quantity]) VALUES (8, 14, 2)
INSERT [dbo].[tbl_OrderDetail] ([orderID], [cakeID], [quantity]) VALUES (9, 2, 1)
INSERT [dbo].[tbl_OrderDetail] ([orderID], [cakeID], [quantity]) VALUES (9, 15, 2)
INSERT [dbo].[tbl_OrderDetail] ([orderID], [cakeID], [quantity]) VALUES (10, 17, 1)
INSERT [dbo].[tbl_OrderDetail] ([orderID], [cakeID], [quantity]) VALUES (10, 2, 2)
INSERT [dbo].[tbl_OrderDetail] ([orderID], [cakeID], [quantity]) VALUES (10, 15, 3)
INSERT [dbo].[tbl_OrderDetail] ([orderID], [cakeID], [quantity]) VALUES (11, 18, 1)
INSERT [dbo].[tbl_OrderDetail] ([orderID], [cakeID], [quantity]) VALUES (11, 19, 1)
INSERT [dbo].[tbl_OrderDetail] ([orderID], [cakeID], [quantity]) VALUES (11, 21, 2)
INSERT [dbo].[tbl_OrderDetail] ([orderID], [cakeID], [quantity]) VALUES (7, 14, 1)
GO
SET IDENTITY_INSERT [dbo].[tbl_PaymentMethod] ON 

INSERT [dbo].[tbl_PaymentMethod] ([paymentID], [paymentName]) VALUES (1, N'COD')
INSERT [dbo].[tbl_PaymentMethod] ([paymentID], [paymentName]) VALUES (2, N'Paypal')
SET IDENTITY_INSERT [dbo].[tbl_PaymentMethod] OFF
GO
SET IDENTITY_INSERT [dbo].[tbl_PaymentStatus] ON 

INSERT [dbo].[tbl_PaymentStatus] ([statusID], [statusName]) VALUES (1, N'PAID')
INSERT [dbo].[tbl_PaymentStatus] ([statusID], [statusName]) VALUES (2, N'UNPAID')
SET IDENTITY_INSERT [dbo].[tbl_PaymentStatus] OFF
GO
INSERT [dbo].[tbl_Role] ([roleId], [roleName]) VALUES (1, N'customer')
INSERT [dbo].[tbl_Role] ([roleId], [roleName]) VALUES (2, N'Admin')
GO
INSERT [dbo].[tbl_User] ([email], [password], [fullName], [roleId], [phoneNumber], [address]) VALUES (N'a@gmail.com', N'trihung1', N'Nguyen Van A', 1, N'0123456789', N'Viet Nam')
INSERT [dbo].[tbl_User] ([email], [password], [fullName], [roleId], [phoneNumber], [address]) VALUES (N'admin@gmail.com', N'trihung1', N'admin', 2, N'0987654321', N'Viet Nam')
INSERT [dbo].[tbl_User] ([email], [password], [fullName], [roleId], [phoneNumber], [address]) VALUES (N'b@gmail.com', N'trihung1', N'Nguyen Van B', 1, N'0222222222', N'Viet Nam')
GO
ALTER TABLE [dbo].[tbl_Cake]  WITH CHECK ADD  CONSTRAINT [FK_tbl_Cake_tbl_CakeStatus] FOREIGN KEY([statusID])
REFERENCES [dbo].[tbl_CakeStatus] ([statusID])
GO
ALTER TABLE [dbo].[tbl_Cake] CHECK CONSTRAINT [FK_tbl_Cake_tbl_CakeStatus]
GO
ALTER TABLE [dbo].[tbl_Cake]  WITH CHECK ADD  CONSTRAINT [FK_tbl_Cake_tbl_Category] FOREIGN KEY([categoryID])
REFERENCES [dbo].[tbl_Category] ([categoryID])
GO
ALTER TABLE [dbo].[tbl_Cake] CHECK CONSTRAINT [FK_tbl_Cake_tbl_Category]
GO
ALTER TABLE [dbo].[tbl_Cake]  WITH CHECK ADD  CONSTRAINT [FK_tbl_Cake_tbl_User] FOREIGN KEY([lastModifier])
REFERENCES [dbo].[tbl_User] ([email])
GO
ALTER TABLE [dbo].[tbl_Cake] CHECK CONSTRAINT [FK_tbl_Cake_tbl_User]
GO
ALTER TABLE [dbo].[tbl_Order]  WITH CHECK ADD  CONSTRAINT [FK_tbl_Order_tbl_PaymentMethod] FOREIGN KEY([paymentMethodID])
REFERENCES [dbo].[tbl_PaymentMethod] ([paymentID])
GO
ALTER TABLE [dbo].[tbl_Order] CHECK CONSTRAINT [FK_tbl_Order_tbl_PaymentMethod]
GO
ALTER TABLE [dbo].[tbl_Order]  WITH CHECK ADD  CONSTRAINT [FK_tbl_Order_tbl_PaymentStatus] FOREIGN KEY([paymentStatusID])
REFERENCES [dbo].[tbl_PaymentStatus] ([statusID])
GO
ALTER TABLE [dbo].[tbl_Order] CHECK CONSTRAINT [FK_tbl_Order_tbl_PaymentStatus]
GO
ALTER TABLE [dbo].[tbl_Order]  WITH CHECK ADD  CONSTRAINT [FK_tbl_Order_tbl_User] FOREIGN KEY([email])
REFERENCES [dbo].[tbl_User] ([email])
GO
ALTER TABLE [dbo].[tbl_Order] CHECK CONSTRAINT [FK_tbl_Order_tbl_User]
GO
ALTER TABLE [dbo].[tbl_OrderDetail]  WITH CHECK ADD  CONSTRAINT [FK_tbl_OrderDetail_tbl_Cake] FOREIGN KEY([cakeID])
REFERENCES [dbo].[tbl_Cake] ([cakeID])
GO
ALTER TABLE [dbo].[tbl_OrderDetail] CHECK CONSTRAINT [FK_tbl_OrderDetail_tbl_Cake]
GO
ALTER TABLE [dbo].[tbl_OrderDetail]  WITH CHECK ADD  CONSTRAINT [FK_tbl_OrderDetail_tbl_Order] FOREIGN KEY([orderID])
REFERENCES [dbo].[tbl_Order] ([orderID])
GO
ALTER TABLE [dbo].[tbl_OrderDetail] CHECK CONSTRAINT [FK_tbl_OrderDetail_tbl_Order]
GO
ALTER TABLE [dbo].[tbl_User]  WITH CHECK ADD  CONSTRAINT [FK_tbl_User_tbl_Role] FOREIGN KEY([roleId])
REFERENCES [dbo].[tbl_Role] ([roleId])
GO
ALTER TABLE [dbo].[tbl_User] CHECK CONSTRAINT [FK_tbl_User_tbl_Role]
GO
