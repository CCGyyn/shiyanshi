package ezsh;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zeng.ezsh.wy.dao.HousekeepingMapper;
import com.zeng.ezsh.wy.entity.Housekeeping;

@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring的配置文件
@ContextConfiguration({"classpath:spring/applicationContext-dao.xml"})
public class Hktest {
	
	@Autowired
	private HousekeepingMapper housekeepingMapper;
	
	@Test
	public void test(){
		
		
		
		//int result = housekeepingMapper.insertSelective(record);
		//List<Housekeeping> list = housekeepingMapper.selectNoAudited();
//		Map<String,Object> map = new HashMap();
//		map.put("id", 1);
//		map.put("result", 1);
		List<Housekeeping> list = housekeepingMapper.selectAuditedList(null);
		System.out.println(list);
	}

}
