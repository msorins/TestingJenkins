package Repository.MemoryRepository;

import static org.junit.Assert.assertTrue;

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

        assertTrue(false);
    }
}
