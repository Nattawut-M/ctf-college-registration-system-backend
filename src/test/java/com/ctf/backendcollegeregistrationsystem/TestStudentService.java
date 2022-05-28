package com.ctf.backendcollegeregistrationsystem;

import com.ctf.backendcollegeregistrationsystem.entity.Student;
import com.ctf.backendcollegeregistrationsystem.service.StudentService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestStudentService {

    @Autowired
    private StudentService service;

    @Order(1)
    @Test
    void initialStudents() {
//        initial student list
        List<Student> studentList = new ArrayList<>();
//        loop add student into list
        for (int i = 0; i < 3; i++) {
            Student student = new Student(DemoData.firstNameList.get(i), DemoData.lastNameList.get(i), DemoData.emailList.get(i), DemoData.dobList.get(i));
            studentList.add(student);
        }
//        save list of student to database
        service.createAll(studentList);

    }

    @Order(2)
    @Test
    void findStudentByEmail() {
        Student studentByEmail = service.findByEmail("t.nattawut2020@mail.com");
        Assertions.assertEquals("t.nattawut2020@mail.com", studentByEmail.getEmail());
        Assertions.assertEquals(LocalDate.of(1998, 8, 12), studentByEmail.getDateOfBirth());
    }


    @Order(3)
    @Test
    void findStudentByFirstName() {
        Student studentByFirstname = service.findByFirstName("khainui");
        Assertions.assertEquals("khainui", studentByFirstname.getFirstName());
        Assertions.assertEquals(LocalDate.of(1998, 8, 12), studentByFirstname.getDateOfBirth());
    }

    @Order(4)
    @Test
    void findStudentByLastName() {
        Student studentByLastname = service.findByLastName("puipui");
        Assertions.assertEquals("puipui", studentByLastname.getLastName());
        Assertions.assertEquals(LocalDate.of(1998, 8, 12), studentByLastname.getDateOfBirth());
    }

    @Order(5)
    @Test
    void findById() {
        Student byFirstNameStudent = service.findByFirstName("aim");
        Assertions.assertNotNull(byFirstNameStudent);

        Student byIdStudent = service.findById(byFirstNameStudent.getId());
        Assertions.assertNotNull(byIdStudent);

        Assertions.assertEquals(byFirstNameStudent.getId(), byIdStudent.getId());
        Assertions.assertEquals(byFirstNameStudent.getEmail(), byIdStudent.getEmail());
        Assertions.assertEquals(byFirstNameStudent.getFirstName(), byIdStudent.getFirstName());
        Assertions.assertEquals(byFirstNameStudent.getLastName(), byIdStudent.getLastName());
        Assertions.assertEquals(byFirstNameStudent.getDateOfBirth(), byIdStudent.getDateOfBirth());

    }

    @Order(6)
    @Test
    void update() {
//        get
        Student studentBefore = service.findByEmail("aim_za@mail.com");

//        update and save
        studentBefore.setDateOfBirth(LocalDate.of(1999, 10, 23));
        studentBefore.setFirstName("aimpuiza");
        service.update(studentBefore);    //  saving

//        get student by updated data by 'firstName'
        Student studentAfter = service.findByFirstName("aimpuiza");

//        testing
        Assertions.assertNotNull(studentAfter);
        Assertions.assertEquals(studentBefore.getEmail(), studentAfter.getEmail());
        Assertions.assertEquals(studentBefore.getFirstName(), studentAfter.getFirstName());
        Assertions.assertEquals(studentBefore.getLastName(), studentAfter.getLastName());
        Assertions.assertNotEquals(studentBefore.getUpdatedDate(), studentAfter.getUpdatedDate());

    }

    @Order(7)
    @Test
    void delete() {
        String firstname = "khainui";
//        query
        Student student = service.findByFirstName(firstname);
//        test equal
        Assertions.assertEquals(firstname, student.getFirstName());
//        delete
        service.deleteById(student.getId());

//        find student after delete
        Student afterDelete = service.findByFirstName(firstname);
        Assertions.assertNull(afterDelete);
    }

    public static class DemoData {
        static List<String> emailList = List.of("t.nattawut2020@mail.com", "khainui@mail.com", "aim_za@mail.com");

        static List<String> firstNameList = List.of("nattawut", "khainui", "aim");

        static List<String> lastNameList = List.of("meesamsen", "za", "puipui");
        static List<LocalDate> dobList = List.of(
                LocalDate.of(1998, 8, 12),
                LocalDate.of(1998, 8, 12),
                LocalDate.of(1998, 8, 12)
        );

    }
}
