package com.brd.entity.controller;

import com.brd.dao.CheckerDAO;
import com.brd.entity.Checker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@SessionAttributes("role")
public class CheckerLogin {
    @Autowired
    private CheckerDAO checkerDao;
    private Checker checker = checkerDao.getChecker();

    @RequestMapping(value="/checker/login", method= RequestMethod.POST)
    public String checkerLogin(@RequestParam("uname") String uname,
                               @RequestParam("pass") String pass,
                               Model model, RedirectAttributes redirectAttributes){
        if (uname.equals(checker.getUserName()) && pass.equals(checker.getPass())){
            model.addAttribute("role", checker);
            return "checkerHome";
        }
        else{
            redirectAttributes.addAttribute("msg", "Username or password Incorrect!");
            return "index";
        }
    }
    @RequestMapping(value="/logout/checker", method=RequestMethod.POST)
    public String checkerLogOut(Model model, SessionStatus sessionStatus){
        sessionStatus.setComplete();
        return "index";
    }
}

