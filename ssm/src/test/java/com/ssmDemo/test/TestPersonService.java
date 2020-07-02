package com.ssmDemo.test;

import com.alibaba.fastjson.JSON;
import com.ssmDemo.domain.Person;
import com.ssmDemo.service.PersonService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:yjc
 * @Date: 2019/7/6 10:48
 * @Description:
 */
public class TestPersonService {

    public static Map<String,String> callers = new HashMap<String, String>();
    static {
        callers.put("15810092493","史玉龙");
        callers.put("18000696806","赵贺彪");
        callers.put("15151889601","张倩");
        callers.put("13269361119","王世昌");
        callers.put("15032293356","张涛");
        callers.put("17731088562","张阳");
        callers.put("15338595369","李进全");
        callers.put("15733218050","杜泽文");
        callers.put("15614201525","任宗阳");
        callers.put("15778423030","梁鹏");
        callers.put("18641241020","郭美彤");
        callers.put("15732648446","刘飞飞");
        callers.put("13341109505","段光星");
        callers.put("13560190665","唐会华");
        callers.put("18301589432","杨力谋");
        callers.put("13520404983","温海英");
        callers.put("18332562075","朱尚宽");
        callers.put("18620192711","刘能宗");
    }

    @Test
    public void test1(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
        PersonService ps = (PersonService)ac.getBean("personService");
        for (Map.Entry<String,String> c: callers.entrySet()){
            Person p = new Person();
            p.setName(c.getValue());
            p.setPhone(c.getKey());
            ps.insert(p);
        }
    }

    @Test
    public void test2(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
        PersonService ps = (PersonService)ac.getBean("personService");
        List<Person> list = ps.selectAll();
        for (Person p:list) {
            System.out.println(p.getName()+":"+p.getPhone());
        }

        //测试json串
        String json = JSON.toJSONString(list);
        System.out.println(json);
    }

    @Test
    public void test3(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
        PersonService ps = (PersonService)ac.getBean("personService");
        String name = ps.selectNameByPhone("15732648446");
        System.out.println(name);
    }

}
