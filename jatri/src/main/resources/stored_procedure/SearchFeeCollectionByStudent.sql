DELIMITER $$

DROP PROCEDURE IF EXISTS `emis`.`search_fee_collection_by_student` $$
CREATE PROCEDURE `emis`.`search_fee_collection_by_student` (in year int, in month int, in instituteId varchar(50), in gradeId varchar(50), in studentId varchar(50))
BEGIN

# variable declaration
declare jql varchar(5000) default '';

# search query
set jql = "";
set jql = concat(jql, " select ");
set jql = concat(jql, " udv.value as fee_name,");
set jql = concat(jql, " s.id as student_fee_id,");
set jql = concat(jql, " s.month as month,");
set jql = concat(jql, " s.mandatory=1 as mandatory,");
set jql = concat(jql, " s.amount as total_amount");
set jql = concat(jql, " FROM emis.student_fee s join emis.institute i on (s.institute_id=i.id)");
set jql = concat(jql, " join emis.student st on (st.id = s.student_id)");
set jql = concat(jql, " join emis.udv udv on (udv.id = s.fee_type_udv_id)");
set jql = concat(jql, " where");
set jql = concat(jql, " s.year = ", year);
set jql = concat(jql, " and (s.month <= ", month ," or s.month=999)");
set jql = concat(jql, " and i.id = '", instituteId ,"'");
set jql = concat(jql, " and s.grade_id = '", gradeId ,"'");
set jql = concat(jql, " and s.student_id = '", studentId ,"'");
set jql = concat(jql, " group by s.id");


# execute query
set @cjql = jql;
PREPARE stmt FROM @cjql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;


END $$

DELIMITER