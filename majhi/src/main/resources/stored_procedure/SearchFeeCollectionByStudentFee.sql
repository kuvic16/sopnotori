DELIMITER $$

DROP PROCEDURE IF EXISTS `emis`.`search_fee_collection_by_student_fee` $$
CREATE PROCEDURE `emis`.`search_fee_collection_by_student_fee` (in year int, in month int, in instituteId varchar(50), in gradeId varchar(50), in studentId varchar(50), in studentFeeId varchar(50))
BEGIN

# variable declaration
declare jql varchar(5000) default '';

set jql = "";
set jql = concat(jql, " select");
set jql = concat(jql, " th.collection_date as collection_date,");
set jql = concat(jql, " th.id as id,");
set jql = concat(jql, " th.month as month,");
set jql = concat(jql, " th.amount as total_amount,");
set jql = concat(jql, " th.total_deposited as total_deposited,");
set jql = concat(jql, " th.deposited as deposited");
set jql = concat(jql, " FROM emis.transaction_history th join emis.institute i on (th.institute_id=i.id)");
set jql = concat(jql, " where");
set jql = concat(jql, " th.year = ", year);
set jql = concat(jql, " and (th.month <= ", month ," or th.month=999)");
set jql = concat(jql, " and i.id = '", instituteId ,"'");
set jql = concat(jql, " and th.grade_id = '", gradeId ,"'");
set jql = concat(jql, " and th.student_id = '", studentId ,"'");
set jql = concat(jql, " and th.student_fee_id = '", studentFeeId ,"'");


# execute query
set @cjql = jql;
PREPARE stmt FROM @cjql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;


END $$

DELIMITER