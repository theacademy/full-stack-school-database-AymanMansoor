package mthree.com.fullstackschool.dao;

import mthree.com.fullstackschool.dao.mappers.StudentMapper;
import mthree.com.fullstackschool.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.sql.*;
import java.util.List;
import java.util.Objects;

@Repository
public class StudentDaoImpl implements StudentDao {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public StudentDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Student createNewStudent(Student student) {
        //YOUR CODE STARTS HERE
        final String sql = "INSERT INTO Student (fName, lName) VALUES (?, ?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, student.getStudentFirstName());
            ps.setString(2, student.getStudentLastName());
            return ps;
        }, keyHolder);

        student.setStudentId(Objects.requireNonNull(keyHolder.getKey()).intValue());
        return student;

        //YOUR CODE ENDS HERE
    }

    @Override
    public List<Student> getAllStudents() {
        //YOUR CODE STARTS HERE
        final String sql = "SELECT * FROM Student";
        return jdbcTemplate.query(sql, new StudentMapper());
        //YOUR CODE ENDS HERE
    }

    @Override
    public Student findStudentById(int id) {
        //YOUR CODE STARTS HERE
        final String sql = "SELECT * FROM Student WHERE sId = ?";
        return jdbcTemplate.queryForObject(sql, new StudentMapper(), id);
        //YOUR CODE ENDS HERE
    }

    @Override
    public void updateStudent(Student student) {
        //YOUR CODE STARTS HERE

        final String sql = "UPDATE Student SET fName = ?, lName = ? WHERE sId = ?";
        jdbcTemplate.update(sql, student.getStudentFirstName(), student.getStudentLastName(), student.getStudentId());
        //YOUR CODE ENDS HERE
    }

    @Override
    public void deleteStudent(int id) {
        //YOUR CODE STARTS HERE
        final String sql = "DELETE FROM Student WHERE sId = ?";
        jdbcTemplate.update(sql, id);

        //YOUR CODE ENDS HERE
    }

    @Override
    public void addStudentToCourse(int studentId, int courseId) {
        //YOUR CODE STARTS HERE
        final String sql = "INSERT INTO Student_Course (sId, courseId) VALUES (?, ?)";
        jdbcTemplate.update(sql, studentId, courseId);


        //YOUR CODE ENDS HERE
    }

    @Override
    public void deleteStudentFromCourse(int studentId, int courseId) {
        //YOUR CODE STARTS HERE

        final String sql = "DELETE FROM Student_Course WHERE sId = ? AND courseId = ?";
        jdbcTemplate.update(sql, studentId, courseId);

        //YOUR CODE ENDS HERE
    }
}
