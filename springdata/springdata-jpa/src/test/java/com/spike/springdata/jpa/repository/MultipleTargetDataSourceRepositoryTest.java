package com.spike.springdata.jpa.repository;

import java.util.Set;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.spike.springdata.jpa.JpaTestBase;
import com.spike.springdata.jpa.domain.Address;
import com.spike.springdata.jpa.support.MultipleTargetRoutingDataSource;

/**
 * multiple data source JPA supported repository test<br>
 * 
 * restriction: <br>
 * 
 * SHOULD NOT use transaction cross data source
 * 
 * TODO add JTA to support cross data source transactions
 * 
 * @author zhoujiagen
 *
 */
public class MultipleTargetDataSourceRepositoryTest extends JpaTestBase {
	private static final Logger LOG = Logger.getLogger(MultipleTargetDataSourceRepositoryTest.class);

	@Autowired
	private AddressRespository addressRespository;

	@Test
	public void testChangeDataSource() {

		Set<String> avaiableDataSourceTargets = MultipleTargetRoutingDataSource.availableTargets();
		String[] targets = avaiableDataSourceTargets.toArray(new String[] {});

		Address address1 = new Address("新海村1", "南通", "中国");
		this.saveOne(targets[0], address1);

		Address address2 = new Address("新海村2", "南通", "中国");
		this.saveOne(targets[1], address2);
	}

	@Transactional
	private void saveOne(String target, Address address) {
		MultipleTargetRoutingDataSource.setTarget(target);
		LOG.info("CURRENT DATASOURCE TARGE IS: " + target);

		address = addressRespository.save(address);
		Assert.assertNotNull(address.getId());
	}

}
