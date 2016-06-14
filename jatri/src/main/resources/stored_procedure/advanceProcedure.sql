BEGIN
   # Variable or Cursor declearation section
   DECLARE id, countStudents, countFees, i INTEGER;
   DECLARE name     CHAR(45);
   DECLARE my_var   VARCHAR(100);

   # Declear cursor for all student of specific grade from a institute
   DECLARE
      curStudent CURSOR FOR
         SELECT stu.id
           FROM student stu 
							WHERE institute_id = institute_id 
							AND stu.grade_id = grade_id;
	 
	 #DECLARE cursor for student fee 
		DECLARE curStudentFee CURSOR FOR 
				SELECT fee.id 
					FROM student_fee fee
						WHERE fee.institute_id = institute_id
						AND fee.grade_id = grade_id
						AND fee.fee_type_udv_id = feeType_id
						AND fee.fee_category_udv_id = feeCategory_id
						AND fee.`year` = year
						AND fee.mandatory = mandatory;

   # Open Cursor for institute
   OPEN curStudentFee;
	 SELECT FOUND_ROWS() INTO	countFees;
	 CLOSE curStudentFee;

	 OPEN curStudent;
	 SELECT FOUND_ROWS() INTO countStudents;	
	 CLOSE curStudent;

	
	OPEN curStudent;
		read_loop: LOOP
			SET my_var = "42";
		END LOOP read_loop;
	CLOSE curStudent;

   #SET countStudents = FOUND_ROWS();
		
	 #IF countFees == 0 THEN
		
	    

   

   #END IF;

   /*WHILE i > 0
   DO
      SET i = i - 1;

      FETCH curStudentFee INTO id;

      BEGIN
         DECLARE
            institute_students CURSOR FOR
               SELECT *
                 FROM student stu
                WHERE stu.institute_id = id;

         DECLARE
            institute_fees CURSOR FOR
               SELECT *
                 FROM fee fe
                WHERE fe.institute_id = id;

      END;
   END WHILE;

   CLOSE curStudentFee;

   # Assign value to variable
   SET my_var = "42";

   #use of into keyword must return one row by the select query
   SELECT fe.institute_id, fe.grade_id
     INTO @institute_id, @grade_id
     FROM fee fe
    WHERE fe.id = '45309537-5045-Fee-b33d-deb04ae05a94';*/
SELECT countFees , countStudents;
END