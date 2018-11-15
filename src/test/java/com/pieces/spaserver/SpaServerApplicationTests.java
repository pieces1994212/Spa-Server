package com.pieces.spaserver;

import com.pieces.spaserver.control.function.FunctionController;
import com.pieces.spaserver.mapper.function.FunctionMapper;
import com.pieces.spaserver.mapper.user.UserMapper;
import com.pieces.spaserver.model.function.Function;
import com.pieces.spaserver.model.user.Role;
import com.pieces.spaserver.model.user.User;
import com.pieces.spaserver.service.function.FunctionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpaServerApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Autowired
    FunctionMapper functionMapper;

    @Autowired
    FunctionService functionService;

    @Autowired
    FunctionController functionController;

    @Autowired
    RedisTemplate<String,Serializable> redisTemplate;

    @Test
    public void contextLoads() {

    }

    @Test
    public void test2(){
        Role role = new Role();
        role.setId(Long.parseLong("123123123"));
        role.setName("abcabc");
        role.setNo("roleabac");
        redisTemplate.opsForValue().set("role"+role.getId(),role);
    }

    @Test
    public void test3(){
        Role role = new Role();
        role = (Role) redisTemplate.opsForValue().get("role123123123");
        System.out.print("==============>"+role.getName());
    }

    @Test
    public void test4(){
        List list = new ArrayList();
        Role role = new Role();
        role.setId(Long.parseLong("123123123"));
        role.setName("abcabc");
        role.setNo("roleabac");
        Function fun = new Function();
        fun.setId(Long.parseLong("321123"));
        fun.setName("adadad");
        list.add(role);
        list.add(fun);
        redisTemplate.opsForList().leftPushAll("list", list);
    }

    @Test
    public void test5(){
        List list = (List) redisTemplate.opsForList().range("list", 0, -1);
        System.out.print("===================>"+list.size());
    }

    @Test
    public void test6(){
        Function info = new Function();
        info.setType(3);
        List<Function> list = functionMapper.queryFunctions(info);
    }

    @Test
    public void test7(){
        Function info = new Function();
        info.setType(3);
        List<Function> list = functionController.queryFunction(info);
        System.out.print(list);
    }

    @Test
    public void test8(){
        Function fun1 = new Function();
        Function fun2 = new Function();
        fun1.setNo("123123aaa");
        fun2.setNo("123123aaa");
        Map map = new HashMap();
//        Vector<Function> ver = new Vector<>();
//        ver.add(fun1);
//        ver.add(fun2);
//        Iterator iterator = ver.iterator();
//        while(iterator.hasNext()){
//            Function fun = (Function) iterator.next();
//            System.out.println();
//            System.out.print(fun.getNo());
//
//        }
//        ver.add(fun1);
//        ver.add(fun2);
        HashSet<Function> set = new HashSet<Function>();
        set.add(fun1);
        set.add(fun2);
        System.out.println();
        System.out.println("set size" + set.size());
//        if(fun1.equals(fun2)){
//            System.out.println();
//            System.out.println("=======================================================");
//        }
    }

    @Test
    public void test9(){
        LinkedList<Integer> list = new LinkedList<>();
        list.add(2);
        list.add(3);
        list.add(4);

        for(Integer x:list){
            if(x%2==0){
                list.remove(x);
            }
        }

        Iterator<Integer> itr = list.iterator();
        while(itr.hasNext()){
            if(itr.next()%2==0){
                itr.remove();
            }
        }

        
    }
}
