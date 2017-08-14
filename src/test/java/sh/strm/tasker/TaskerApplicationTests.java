package sh.strm.tasker;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import sh.strm.tasker.runner.DockerTaskRunner;
import sh.strm.tasker.runner.TaskExecutionResult;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskerApplicationTests {

	@Autowired
	private TaskConfiguration conf;

	@Autowired
	private DockerTaskRunner dockerRunner;

	@Test
	public void contextLoads() {
		assertNotNull(conf);
	}

	@Test
	public void testDockerConfigurationLoad() {
		assertNotEquals(0, conf.getTasks().size());
	}

	@Test
	public void testTaskConfigurationName() {
		assertEquals("hello", conf.getTasks().get(0).getName());
	}

	@Test
	public void testDockerConfigurationImageName() {
		assertEquals("debian:jessie", conf.getDocker().get(0).getImage());
	}

	@Test
	public void testDockerRunContainer() throws Exception {
		TaskExecutionResult result = dockerRunner.executeTask(conf.getDocker().get(0));
		assertEquals("green bar", result.getOutput());
	}

}