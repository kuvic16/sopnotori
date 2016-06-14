DELIMITER $$

DROP PROCEDURE IF EXISTS `emis`.`test` $$
CREATE PROCEDURE `emis`.`test` (in startdate VARCHAR(255), in uname VARCHAR(255))
BEGIN

 # variable declaration
  declare jql varchar(5000) default 'a';



# set data from table
select u.email, u.username, u.id into @email, @username, jql from emis.users u where u.username='riad';


# string concat
set @email = concat(@email, startdate);


#if condition
if jql is not null then
set jql = "is not null";
end if;

#dynamic query
set jql = "select u.id into @uid from emis.users u where ";
set jql = concat(jql, " u.username='");
set jql = concat(jql, uname);
set jql = concat(jql, "'");

# execute query
set @cjql = jql;
  	PREPARE stmt FROM @cjql;
  	EXECUTE stmt;
	  DEALLOCATE PREPARE stmt;


# final output
select @email, @username, startdate, jql, uname, @uid;




END $$

DELIMITER 