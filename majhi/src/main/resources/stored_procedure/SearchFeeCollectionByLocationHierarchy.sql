DELIMITER $$

DROP PROCEDURE IF EXISTS `search_fee_collection_by_location_hierarchy` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `search_fee_collection_by_location_hierarchy`(in startPos int, in endPos int, in year int, in month int, in searchTypeId varchar(50))
BEGIN

# variable declaration
declare jql varchar(5000) default '';

# search query
set jql = "";
set jql = concat(jql, " select ");
set jql = concat(jql, " l.name as location_name,");
set jql = concat(jql, " substring(i.location_hierarchy," , startPos , "," , endPos , " ) as location_id,");
set jql = concat(jql, " count(distinct(i.id)) as total_institute,");
set jql = concat(jql, " count(distinct(s.student_id)) as total_student,");
set jql = concat(jql, " sum(s.amount) as total_amount");
set jql = concat(jql, " FROM emis.student_fee s join emis.institute i on (s.institute_id=i.id)");
set jql = concat(jql, " join emis.location l on (substring(i.location_hierarchy," , startPos , "," , endPos , " )=l.id) ");
set jql = concat(jql, " where");
set jql = concat(jql, " s.year = ", year);
set jql = concat(jql, " and (s.month <= ", month ," or s.month=999)");

if searchTypeId != '' then
	set jql = concat(jql, " and i.location_hierarchy like '%>", searchTypeId ,">%'");
end if;

set jql = concat(jql, " group by l.id");


# execute query
set @cjql = jql;
PREPARE stmt FROM @cjql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;


END $$

DELIMITER ;