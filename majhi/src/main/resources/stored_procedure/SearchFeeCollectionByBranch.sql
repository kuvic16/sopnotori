DELIMITER $$

DROP PROCEDURE IF EXISTS `emis`.`search_fee_collection_by_branch` $$
CREATE PROCEDURE `emis`.`search_fee_collection_by_branch` (in year int, in month int, in searchTypeId varchar(500), in poId varchar(100))
BEGIN

# variable declaration
declare jql varchar(5000) default '';

# search query
set jql = "";
set jql = concat(jql, " select ");
set jql = concat(jql, " i.name as institute_name,");
set jql = concat(jql, " i.id as institute_id,");
set jql = concat(jql, " count(distinct(s.student_id)) as total_student,");
set jql = concat(jql, " count(distinct(s.grade_id)) as total_grade,");
set jql = concat(jql, " sum(s.amount) as total_amount");
set jql = concat(jql, " FROM emis.student_fee s join emis.institute i on (s.institute_id=i.id)");
set jql = concat(jql, " where");
set jql = concat(jql, " s.year = ", year);
set jql = concat(jql, " and (s.month <= ", month ," or s.month=999)");

if searchTypeId != '' then
	set jql = concat(jql, " and i.location_hierarchy like '%>", searchTypeId ,">%'");
end if;

if poId != '' then
	set jql = concat(jql, " and i.po_id = '", poId ,"'");
end if;

set jql = concat(jql, " group by s.institute_id");


# execute query
set @cjql = jql;
PREPARE stmt FROM @cjql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;


END $$

DELIMITER