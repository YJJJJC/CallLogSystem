package com.ssmDemo.web.controller;

import com.ssmDemo.domain.User;
import com.ssmDemo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author:yjc
 * @Date: 2019/6/30 14:36
 * @Description:
 */
@Controller
public class UserController {

    @Resource(name = "userService")
    private UserService us;

    @RequestMapping("/user/findAll")
    public String findAll(Model model){
        List<User> list = us.selectAll();
        model.addAttribute("allUsers",list);
        return "user/userList";
    }

    @RequestMapping("/user/findPage")
    public String findPage(Model model,@RequestParam("pn") int pn){
        int count = us.selectCount();
        if(pn==0){
            pn=1;
        }
        int recordPerPage = 5;

        int pages = 0;
        if(count%recordPerPage==0){
            pages = count/recordPerPage;
        }else {
            pages = (count/recordPerPage) +1;
        }
        //偏移量
        int offset = (pn -1)*recordPerPage;
        List<User> list = us.selectPage(offset,recordPerPage);
        model.addAttribute("allUsers",list);
        model.addAttribute("pages",pages);
        model.addAttribute("pn",pn);
        return "user/userList";
    }

    @RequestMapping("/user/deleteUser")
    public String deleteUser(@RequestParam("uid") int uid){
        us.delete(uid);
        return "redirect:/user/findAll";
    }

    @RequestMapping("/user/insertUser")
    public String insert(User user){
        us.insert(user);
        return "redirect:/user/findAll";
    }

    @RequestMapping("/user/editUser")
    public String editUser(){
        return "user/userList";
    }
}
