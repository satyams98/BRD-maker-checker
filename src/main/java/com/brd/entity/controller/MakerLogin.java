package com.brd.entity.controller;

import com.brd.dao.MakerDAO;
import com.brd.entity.Maker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@SessionAttributes("role")
public class MakerLogin {
    @Autowired
    private MakerDAO makerDAO;
    private Maker maker = makerDAO.getMaker();


    @RequestMapping(value="/maker/login", method = RequestMethod.POST)
    public String MakerLogin(@RequestParam("uname") String uname,
                             @RequestParam("pass") String pass,
                             Model model, RedirectAttributes redirectAttributes){
        if (uname.equals(maker.getUserName()) && pass.equals(maker.getPass())){
            model.addAttribute("role", maker);
            return "redirect:/maker/home";
        }
        else{
            redirectAttributes.addAttribute("msg", "Username or password Incorrect!");
            return "redirect:/index";
        }
    }
    @RequestMapping(value="/maker/home", method=RequestMethod.GET)
    public String showMakerHome(Model model){
        return "makerHome";
    }
    @RequestMapping(value="/maker/logout", method=RequestMethod.POST)
    public String MakerLogOut(Model model, SessionStatus sessionStatus){
        sessionStatus.setComplete();
        return "index";
    }

}
