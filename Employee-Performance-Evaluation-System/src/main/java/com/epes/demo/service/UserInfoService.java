package com.epes.demo.service;

import com.epes.demo.dao.UserInfoDao;
import com.epes.demo.entity.UserInfo;
import com.epes.demo.entity.UserLogin;
import com.epes.demo.tool.Encryption;
import com.epes.demo.tool.SearchParams;
import com.epes.demo.tool.exception.NotTableEntityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;



/**
 * Created with IntelliJ IDEA.
 * Description:
 * @author lixingjie
 * Date: 2018-01-05
 * Time: 15:39
 */
@Transactional(rollbackFor = {})
@Service
public class UserInfoService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private IdService idService = new IdService();

    private final UserInfoDao userInfoDao;
    private final BaseService baseService;

    @Autowired
    public UserInfoService(UserInfoDao userInfoDao, BaseService baseService) {
        this.userInfoDao = userInfoDao;
        this.baseService = baseService;
    }


    /**
     * 创建用户
     *
     */
    public Map<String ,String> addUser(List<UserInfo> userList) throws NoSuchAlgorithmException {
        for (UserInfo user: userList) {
            user.setId(UUID.randomUUID().toString());
            UserLogin userLogin = new UserLogin();
            byte[] src = Encryption.encoderByMd5("123456a");
            userLogin.setPassword(Encryption.ToHexString(src));
            userLogin.setId(user.getId());
            baseService.insert(user);
            baseService.insert(userLogin);
        }
        return null;
    }

    /**
     * 修改用户个人信息
     * @param userInfo
     * @return
     */
    public Map<String,String> updataUser(UserInfo userInfo){
        Map<String, String> map = new HashMap<>(0);
        int p = baseService.updata(userInfo);
        if (p>0){
            map.put("message","修改成功");
        }else {
            map.put("message","数据不存在或数据无法修改");
        }
        return map;
    }

    /**
     * 查找所有用户
     * @return
     */
    public List<Map<String, Object>> findAllUsers(PageRequest pageRequest, SearchParams searchParams) {
        List<Map<String, Object>> susers = baseService.pageFindByCondition(UserInfo.class,pageRequest,searchParams);
        return susers;
    }

    /**
     * 删除用户
     * @param id
     * @return
     * @throws NotTableEntityException
     */
    public Map<String,String> deleteUser(String id) throws NotTableEntityException {
        Map<String, String> map = new HashMap<>(0);
        int p = baseService.delete(UserInfo.class, id);
        if (p>0){
            baseService.delete(UserLogin.class,id);
            map.put("message","删除成功");
        }else {
            map.put("message","该数据不存在");
        }
        return map;
    }

    /**
     * 查询单个用户
     */
    public UserInfo findUser(String id){
        return userInfoDao.findUser(id);
    }


/*    public Map<String, Object> login(String loginname,String password) throws NoSuchAlgorithmException {
        password = Encryption.encoder(password, Encryption.SHA1);
        int p = 0;
        Map<String, Object> result = new HashMap<String, Object>();
        UserInfo userInfo = susersMapper.login(loginname,password);
        if (userInfo != null) {
            p = 1;
            result.put("success","登陆成功！");
        } else {
            result.put("error","账户或密码错误！");
        }
        result.put("userinfo", userInfo);
        result.put("p", p);
        return result;
    }*/

}
