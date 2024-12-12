insert into users(username, password) values('admin','$2a$10$k9644mshajjDvMhU8p76.u4sgOFuINZDkZ/csNgzFY99W1diZjBuC');

insert into todos(username, description, target_date, done) values 
('admin', 'Learn Python', CURRENT_DATE(), false),
('admin', 'Learn Java', CURRENT_DATE(), false),
('admin', 'Learn Web Development', CURRENT_DATE(), false);
