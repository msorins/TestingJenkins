package Repository.MemoryRepository;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import Domain.Student;
import Exceptions.ValidatorException;
import Validator.StudentValidator;
import org.junit.Test;

/**
 * Unit test for Memory Repository
 */
public class MemoryRepositoryTest
{
    @Test
    public void shouldSuccessfullyAddStudent() throws ValidatorException {
        StudentValidator vs=new StudentValidator();
        StudentRepo studentRepo = new StudentRepo(vs);
        Student student = new Student(
                "testId",
                "testName",
                3112,
                "testMail",
                "testTeacher"
        );
        assertTrue(studentRepo.findAll().spliterator().getExactSizeIfKnown()==0);
        studentRepo.save(student);
        assertTrue(studentRepo.findAll().spliterator().getExactSizeIfKnown()==1);
    }

    @Test
    public void shouldThrowWhenGivenIncorrectGroupId() throws ValidatorException {
        StudentValidator vs=new StudentValidator();
        StudentRepo studentRepo = new StudentRepo(vs);
        Student student = new Student(
                "testId",
                "testName",
                -5,
                "testMail",
                "testTeacher"
        );
        assertTrue(studentRepo.findAll().spliterator().getExactSizeIfKnown()==0);
        try{
            studentRepo.save(student);
            assertTrue(false);
        }catch(ValidatorException ex){
            assertTrue(ex.getMessage().contains("Grupa invalid"));
        }
    }

    @Test
    public void testExistsOk() throws ValidatorException {
        StudentValidator vs=new StudentValidator();
        StudentRepo studentRepo = new StudentRepo(vs);
        Student student = new Student(
                "testId",
                "testName",
                3112,
                "testMail",
                "testTeacher"
        );
        studentRepo.save(student);
        assertTrue(true);
    }

    @Test
    public void testExistsNOk() throws Exception {
        StudentValidator vs=new StudentValidator();
        StudentRepo studentRepo = new StudentRepo(vs);
        Student student = new Student(
                "testId",
                "testName",
                3112,
                "testMail",
                "testTeacher"
        );
        studentRepo.save(student);
        studentRepo.save(student);

        // should not crash, but handle everything internally
        assertTrue(true);
    }

    @Test
    public void testNameOk() throws Exception {
        StudentValidator vs=new StudentValidator();
        StudentRepo studentRepo = new StudentRepo(vs);
        Student student = new Student(
                "testId",
                "testName",
                3112,
                "testMail",
                "testTeacher"
        );
        studentRepo.save(student);
        assertTrue(true);
    }

    @Test
    public void testNameNOk() throws Exception {
        StudentValidator vs=new StudentValidator();
        StudentRepo studentRepo = new StudentRepo(vs);
        Student student = new Student(
                "",
                "testName",
                3112,
                "testMail",
                "testTeacher"
        );

        try{
            studentRepo.save(student);
            fail();
        }catch(Exception ex){
            assertTrue(true);
        }
    }

    @Test
    public void testGroupOk() throws Exception {
        StudentValidator vs=new StudentValidator();
        StudentRepo studentRepo = new StudentRepo(vs);
        Student student = new Student(
                "blabla",
                "testName",
                3112,
                "testMail",
                "testTeacher"
        );

        studentRepo.save(student);
        assertTrue(true);
    }

    @Test
    public void testGroupNOk() throws Exception {
        StudentValidator vs=new StudentValidator();
        StudentRepo studentRepo = new StudentRepo(vs);
        Student student = new Student(
                "blabla",
                "testName",
                0,
                "testMail",
                "testTeacher"
        );

        try{
            studentRepo.save(student);
            fail();
        }catch(Exception ex){
            assertTrue(true);
        }

        student.setGrupa(-1);
        try{
            studentRepo.save(student);
            fail();
        }catch(Exception ex){
            assertTrue(true);
        }
    }
}
