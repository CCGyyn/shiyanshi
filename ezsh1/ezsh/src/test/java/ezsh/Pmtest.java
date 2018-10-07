package ezsh;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zeng.ezsh.wy.dao.PlateManagementMapper;
import com.zeng.ezsh.wy.entity.PlateMessage;
import com.zeng.ezsh.wy.service.HousekeepingService;
import com.zeng.ezsh.wy.service.PlateManagementService;


@ContextConfiguration({"classpath:spring/applicationContext-dao.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class Pmtest {

	@Autowired
	private PlateManagementMapper platemanagedao;
	
	@Test
	public void test(){
		Map<String,Object> map = (Map<String, Object>) platemanagedao.selectUserRoomNumber("441623199705110017");
		System.out.println(map);
		
	
	}
}
