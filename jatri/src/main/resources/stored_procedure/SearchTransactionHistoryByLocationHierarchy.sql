DELIMITER $$

DROP PROCEDURE IF EXISTS `emis`.`search_transaction_history_by_location_hierarchy` $$
CREATE PROCEDURE `emis`.`search_transaction_history_by_location_hierarchy` (in year int, in month int, 
																			in searchType varchar(50), 
																			in searchTypeId varchar(50), 
																			in instituteId varchar(50),
																			in gradeId varchar(50),
																			in studentId varchar(50))
BEGIN

# variable declaration
declare jql varchar(5000) default '';

# search query
set jql = "";
set jql = concat(jql, " select");
set jql = concat(jql, " sum(th.deposited) as total_paid");
set jql = concat(jql, " FROM emis.transaction_history th join emis.institute i on (th.institute_id=i.id)");
set jql = concat(jql, " where");
set jql = concat(jql, " th.year = ", year);
set jql = concat(jql, " and (th.month <= ", month ," or th.month=999)");
if searchType = 'SEARCH_TYPE_REGION' or  searchType = 'SEARCH_TYPE_AREA' or  searchType = 'SEARCH_TYPE_BRANCH' then
	set jql = concat(jql, " and i.location_hierarchy like '%>", searchTypeId ,">%'");
end if;
if searchType = 'SEARCH_TYPE_INSTITUTE' then
	set jql = concat(jql, " and i.id = '", searchTypeId ,"'");
end if;
if searchType = 'SEARCH_TYPE_GRADE' then
	set jql = concat(jql, " and i.id = '", instituteId ,"'");
	set jql = concat(jql, " and th.grade_id = '", searchTypeId ,"'");
end if;
if searchType = 'SEARCH_TYPE_STUDENT' then
	set jql = concat(jql, " and i.id = '", instituteId ,"'");
	set jql = concat(jql, " and th.grade_id = '", gradeId ,"'");
	set jql = concat(jql, " and th.student_id = '", searchTypeId ,"'");
end if;
if searchType = 'SEARCH_TYPE_STUDENT_FEE' then
	set jql = concat(jql, " and i.id = '", instituteId ,"'");
	set jql = concat(jql, " and th.grade_id = '", gradeId ,"'");
	set jql = concat(jql, " and th.student_id = '", studentId ,"'");
	set jql = concat(jql, " and th.student_fee_id = '", searchTypeId ,"'");
end if;

# execute query
set @cjql = jql;
PREPARE stmt FROM @cjql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;


END $$

DELIMITER 