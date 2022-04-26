package com.brd.controller;
import com.brd.entity.CustomerTemp;
import com.brd.service.CheckerActions;
import com.brd.service.MakerActions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    @Qualifier("checkerActions")
    private CheckerActions checkerActions;
    @Autowired
    @Qualifier("makerActions")
    private MakerActions makerActions;
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showHome(){
        return "index";
    }

    @RequestMapping(value = "/addCustomer", method = RequestMethod.GET)
    public String showAddCustomerForm(Model model){
        CustomerTemp customerTemp = new CustomerTemp();
        model.addAttribute("customerForm", customerTemp);
        return "addCustomer";
    }

    @RequestMapping(value ="/addCustomer", method = RequestMethod.POST)
    public String saveCustomer(Model model,
                             @ModelAttribute("customer")CustomerTemp customerTemp,
                            BindingResult bindingResult){
                if(bindingResult.hasErrors()){
                    return "error";
                }
                makerActions.createNewRecord(customerTemp);

                return "success";
    }

    @RequestMapping(value = "/newRecordTable", method = RequestMethod.GET)
    public String showNewRecordTable(Model model){
        List<CustomerTemp> newRecordTable;
        newRecordTable= makerActions.getTempCustomerList();
        model.addAttribute("customerList", newRecordTable);
        return "newRecordTable";

    }
}
