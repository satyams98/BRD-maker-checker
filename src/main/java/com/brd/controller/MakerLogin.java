package com.brd.controller;

import com.brd.dao.DAO;
import com.brd.dao.MakerDAO;
import com.brd.entity.Maker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@SessionAttributes({"role", "roleName"})
public class MakerLogin {
    @Autowired
    @Qualifier("makerDAO")
    private DAO makerDAO;
  //  private Maker maker = makerDAO.getMaker();


    @RequestMapping(value="/login/maker", method = RequestMethod.GET)
    public String MakerLogin(@RequestParam("uname") String uname,
                             @RequestParam("pass") String pass,
                             Model model, RedirectAttributes redirectAttributes){
        if (uname.equals("saty") && pass.equals("pass")){
            model.addAttribute("role","maker");
            model.addAttribute("roleName", "satyam");
            return "redirect:/newRecords";
        }
        else{
            redirectAttributes.addAttribute("css", "warning");
            redirectAttributes.addAttribute("msg", "Username or password Incorrect!");
            return "redirect:/index";
        }
    }

    @RequestMapping(value="/logoutMaker", method=RequestMethod.GET)
    public String logout(Model model, SessionStatus sessionStatus){
        sessionStatus.setComplete();
        return "index";
    }

}
