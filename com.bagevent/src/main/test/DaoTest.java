import com.bagevent.dao.UserDao;
import com.bagevent.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ContextConfiguration(locations = { "classpath:applicationContext.xml",
        "classpath:datasource.xml" })
public class DaoTest {

    @Resource
    private UserDao userDao;

    @Test
    public void test1(){
        User user=new User();
        user.setCellphone("1");
        user.setEmail("2");
        user.setSalt("3");
        user.setPassword("4");
        user.setCreateTime(new Date());
        user.setUserId(2l);
        user.setUserName("w");
        user.setState(false);
        userDao.save(user);
    }
}
