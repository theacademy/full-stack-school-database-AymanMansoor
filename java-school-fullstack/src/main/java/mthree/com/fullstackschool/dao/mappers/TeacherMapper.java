package mthree.com.fullstackschool.dao.mappers;

import mthree.com.fullstackschool.model.Teacher;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherMapper implements RowMapper<Teacher> {
    @Override
    public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
        //YOUR CODE STARTS HERE
        Teacher teacher = new Teacher();
        teacher.setTeacherId(rs.getInt("teacherId"));
        teacher.setTeacherFName(rs.getString("teacherFName"));
        teacher.setTeacherLName(rs.getString("teacherLName"));
        return teacher;
        //YOUR CODE ENDS HERE
    }
}
