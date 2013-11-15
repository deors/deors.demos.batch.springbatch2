package deors.demos.batch.springbatch2;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import deors.demos.batch.springbatch2.Employee;
import deors.demos.batch.springbatch2.EmployeeDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "classpath:spring-batch-test.xml"
})
public class EmployeeTest {

    @Autowired
    private SimpleJobLauncher launcher;

    @Autowired
    private Job job;

    @Test
    public void testJob() throws Exception {

        System.out.println("this is the initial employee list " + EmployeeDao.EMPLOYEES);

        JobParametersBuilder builder = new JobParametersBuilder();
        builder.addDate("execution", new Date());
        builder.addString("jobName", "employee updater job test");
        JobParameters parameters = builder.toJobParameters();
        launcher.run(job, parameters);

        System.out.println("this is the final employee list " + EmployeeDao.EMPLOYEES);

        assertEquals(3, EmployeeDao.EMPLOYEES.size());

        assertEquals(new Employee("1", "Jorge", "Hidalgo", "PTA", "952044000", "jorge.hidalgo@goodmail.com"), EmployeeDao.EMPLOYEES.get("1"));
        assertEquals(new Employee("2", "Antonio", "Hidalgo", "Unknown", "958404080", "antonio.hidalgo@goodmail.com"), EmployeeDao.EMPLOYEES.get("2"));
        assertEquals(new Employee("4", "Angela", "Rodriguez", "PTA Malaga", "676909080", "angie90@gmail.com"), EmployeeDao.EMPLOYEES.get("4"));
    }
}
