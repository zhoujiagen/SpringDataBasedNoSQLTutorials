package com.spike.springdata.neo4j.service;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;

import javax.transaction.TransactionManager;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.kernel.impl.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { Neo4jTransactionManagerConfig.class })
public class APretendServiceTest {

	@Autowired
	@Qualifier("tx")
	TransactionManager transactionManager;

	@BeforeClass
	public static void setUpBeforeClass() {
		try {
			FileUtils.deleteRecursively(new File(Neo4jTransactionManagerConfig.Embedded_DB_DIR));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Autowired
	ApplicationContext applicationContext;

	@Test
	public void resource() {
		assertNotNull(applicationContext);
		String beanNames[] = applicationContext.getBeanDefinitionNames();
		for (String beanName : beanNames) {
			System.out.println("|-" + beanName);
		}

		assertNotNull(transactionManager);

	}

}
