DELIMITER $$

DROP PROCEDURE IF EXISTS `emis`.`search_fee_collection_by_institute` $$
CREATE PROCEDURE `emis`.`search_fee_collection_by_institute` (in year int, in month int, in searchTypeId varchar(50))
BEGIN

# variable declaration
declare jql varchar(5000) default '';

# search query
set jql = "";
set jql = concat(jql, " select ");
set jql = concat(jql, " g.name as grade_name,");
set jql = concat(jql, " g.id as grade_id,");
set jql = concat(jql, " count(distinct(s.student_id)) as total_student,");
set jql = concat(jql, " count(distinct(s.id)) as total_fee_type,");
set jql = concat(jql, " sum(s.amount) as total_amount");
set jql = concat(jql, " FROM emis.student_fee s join emis.institute i on (s.institute_id=i.id)");
set jql = concat(jql, " join emis.grade g on (s.grade_id = g.id)");
set jql = concat(jql, " where");
set jql = concat(jql, " s.year = ", year);
set jql = concat(jql, " and (s.month <= ", month ," or s.month=999)");
set jql = concat(jql, " and i.id = '", searchTypeId ,"'");
set jql = concat(jql, " group by s.grade_id");


# execute query
set @cjql = jql;
PREPARE stmt FROM @cjql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;


END $$

DELIMITER