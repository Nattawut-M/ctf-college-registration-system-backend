package com.ctf.backendcollegeregistrationsystem;

import com.ctf.backendcollegeregistrationsystem.entity.Department;
import com.ctf.backendcollegeregistrationsystem.entity.Faculty;
import com.ctf.backendcollegeregistrationsystem.entity.Student;
import com.ctf.backendcollegeregistrationsystem.exception.DefaultException;
import com.ctf.backendcollegeregistrationsystem.service.DepartmentService;
import com.ctf.backendcollegeregistrationsystem.service.FacultyService;
import com.ctf.backendcollegeregistrationsystem.service.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class BackendCollegeRegistrationSystemApplication implements CommandLineRunner {

    private final StudentService studentService;
    private final FacultyService facultyService;
    private final DepartmentService departmentService;

    public BackendCollegeRegistrationSystemApplication(StudentService studentService, FacultyService facultyService, DepartmentService departmentService) {
        this.studentService = studentService;
        this.facultyService = facultyService;
        this.departmentService = departmentService;
    }

    public static void main(String[] args) {
        SpringApplication.run(BackendCollegeRegistrationSystemApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        initialFaculties();
//        initialDepartments();
//        initialStudents();
    }

    private void initialFaculties() throws DefaultException {
        List<Faculty> faculties = List.of(
                new Faculty("Science and Technology"),
                new Faculty("Liberal Arts"),
                new Faculty("Architecture"),
                new Faculty("Business Administration"),
                new Faculty("Engineering")
        );

        facultyService.createAll(faculties);
    }

    private void initialDepartments() throws DefaultException {
        List<String> techDepts = List.of("Computer Science", "Information Technology", "Statistics");
        List<String> liberalArtsDepts = List.of("Tourism", "English for Communication", "Hotel Management");
        List<String> businessAdminDepts = List.of("Marketing", "finance", "business computer");

        Faculty techFaculty = facultyService.findByName("science and Technology");
        techDepts.forEach(name -> {
            Department department = new Department(name, techFaculty);
            try {
                departmentService.createByObject(department);
            } catch (DefaultException e) {
                throw new RuntimeException(e);
            }
        });

        Faculty liberalArtsFaculty = facultyService.findByName("Liberal Arts");
        liberalArtsDepts.forEach(name -> {
            Department department = new Department(name, liberalArtsFaculty);
            try {
                departmentService.createByObject(department);
            } catch (DefaultException e) {
                throw new RuntimeException(e);
            }
        });

        Faculty BA_Faculty = facultyService.findByName("Business Administration");
        businessAdminDepts.forEach(name -> {
            Department department = new Department(name, BA_Faculty);
            try {
                departmentService.createByObject(department);
            } catch (DefaultException e) {
                throw new RuntimeException(e);
            }
        });

    }
    private void initialStudents() throws DefaultException {
        Department computerScienceDept = departmentService.findByName("computer science");
        Department financeDept = departmentService.findByName("finance");
        Department tourismDept = departmentService.findByName("Tourism");

//        initial students
        List<Student> students = List.of(
                new Student(
                        "nattawut",
                        "meesamsent",
                        "t.nattawut2020@mail.com",
                        LocalDate.of(1998, 8, 12),
                        computerScienceDept
                ),
                new Student(
                        "khainui",
                        "mawza",
                        "khainui@mail.com",
                        LocalDate.of(1998, 8, 12),
                        financeDept
                ),
                new Student(
                        "aim",
                        "puipui",
                        "aim_pui@mail.com",
                        LocalDate.of(1998, 8, 12),
                        tourismDept
                )
        );

//        save list student, save one student
        studentService.createAll(students);
    }
}
