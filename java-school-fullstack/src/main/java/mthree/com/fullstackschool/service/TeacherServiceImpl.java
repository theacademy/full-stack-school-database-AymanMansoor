package mthree.com.fullstackschool.service;

import mthree.com.fullstackschool.dao.CourseDao;
import mthree.com.fullstackschool.dao.TeacherDao;
import mthree.com.fullstackschool.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherServiceInterface {

    //YOUR CODE STARTS HERE
    private TeacherDao teacherDao;
    public TeacherServiceImpl (TeacherDao teacherDao){
        this.teacherDao=teacherDao;
    }
    //YOUR CODE ENDS HERE

    public List<Teacher> getAllTeachers() {
        //YOUR CODE STARTS HERE
        return teacherDao.getAllTeachers();
        //YOUR CODE ENDS HERE
    }

    public Teacher getTeacherById(int id) {
        //YOUR CODE STARTS HERE
        return teacherDao.findTeacherById(id);
        //YOUR CODE ENDS HERE
    }

    public Teacher addNewTeacher(Teacher teacher) {
        //YOUR CODE STARTS HERE
        return teacherDao.createNewTeacher(teacher);
        //YOUR CODE ENDS HERE
    }

    public Teacher updateTeacherData(int id, Teacher teacher) {
        //YOUR CODE STARTS HERE
        if (id == teacher.getTeacherId()) {
            teacherDao.updateTeacher(teacher);
            return teacher;
        }
        teacher.setTeacherFName("IDs do not match, teacher not updated");
        teacher.setTeacherLName("IDs do not match, teacher not updated");
        return null;
        //YOUR CODE ENDS HERE
    }

    public void deleteTeacherById(int id) {
        //YOUR CODE STARTS HERE
        teacherDao.deleteTeacher(id);
        //YOUR CODE ENDS HERE
    }
}
