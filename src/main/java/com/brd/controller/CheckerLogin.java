package com.brd.controller;

import com.brd.dao.CheckerDAO;
import com.brd.dao.DAO;
import com.brd.entity.Checker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@SessionAttributes({"role", "roleName"})
public class CheckerLogin {
    @Autowired
    @Qualifier("checkerDAO")
    private DAO checkerDAO;
   // private Checker checker = checkerDAO.getChecker();

    @RequestMapping(value="/login/checker", method= RequestMethod.GET)
    public String checkerLogin(@RequestParam("uname") String uname,
                               @RequestParam("pass") String pass,
                               Model model, RedirectAttributes redirectAttributes){
        if (uname.equals("saty") && pass.equals("pass")){
            model.addAttribute("role", "checker");
            model.addAttribute("roleName", "Satyam");
            return "redirect:/newRecords";
        }
        else{
            redirectAttributes.addAttribute("css", "warning");
            redirectAttributes.addAttribute("msg", "Username or password Incorrect!");
            return "redirect:/index";
        }
    }
    @RequestMapping(value="/logoutChecker", method=RequestMethod.GET)
    public String logout(Model model, SessionStatus sessionStatus){
        sessionStatus.setComplete();
        return "index";
    }
}

