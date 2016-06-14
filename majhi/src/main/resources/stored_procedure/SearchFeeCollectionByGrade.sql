DELIMITER $$

DROP PROCEDURE IF EXISTS `emis`.`search_fee_collection_by_grade` $$
CREATE PROCEDURE `emis`.`search_fee_collection_by_grade` (in year int, in month int, in instituteId varchar(50), in gradeId varchar(50))
BEGIN

# variable declaration
declare jql varchar(5000) default '';

# search query
set jql = "";
set jql = concat(jql, " select ");
set jql = concat(jql, " CONCAT(COALESCE( st.first_name, ' '), ' ', COALESCE(st.middle_name,' '), ' ', COALESCE(st.last_name,' ')) as student_name,");
set jql = concat(jql, " st.id as student_id,");
set jql = concat(jql, " st.student_id as roll,");
set jql = concat(jql, " count(distinct(s.id)) as total_fee_type,");
set jql = concat(jql, " sum(s.amount) as total_amount");
set jql = concat(jql, " FROM emis.student_fee s join emis.institute i on (s.institute_id=i.id)");
set jql = concat(jql, " join emis.student st on (st.id = s.student_id)");
set jql = concat(jql, " where");
set jql = concat(jql, " s.year = ", year);
set jql = concat(jql, " and (s.month <= ", month ," or s.month=999)");
set jql = concat(jql, " and i.id = '", instituteId ,"'");
set jql = concat(jql, " and s.grade_id = '", gradeId ,"'");
set jql = concat(jql, " group by s.student_id");


# execute query
set @cjql = jql;
PREPARE stmt FROM @cjql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;


END $$

DELIMITER