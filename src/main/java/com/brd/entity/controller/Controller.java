package com.brd.entity.controller;
import com.brd.dao.CheckerDAO;
import com.brd.dao.MakerDAO;
import com.brd.entity.CustomerPerm;
import com.brd.entity.CustomerTemp;
import com.brd.entity.RecordStatus;
import com.brd.service.CheckerActions;
import com.brd.service.MakerActions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;
import java.util.stream.Collectors;

@org.springframework.stereotype.Controller
@SessionAttributes({"role","table"})
public class Controller {
    @Autowired
    @Qualifier("checkerActions")
    private CheckerActions checkerActions;
    @Autowired
    @Qualifier("makerActions")
    private MakerActions makerActions;
    @Autowired
    @Qualifier("checkerDAO")
    private MakerDAO makerDAO;
    @Autowired
    @Qualifier("makerDAO")
    private CheckerDAO checkerDAO;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showHome(){
        return "index";
    }

    //show add customer form
    @RequestMapping(value = "/customer/add", method = RequestMethod.GET)
    public String showAddCustomerForm(Model model){
        CustomerTemp customerTemp = new CustomerTemp();
        model.addAttribute("customer", customerTemp);
        model.addAttribute("new", true);
        return "customerForm";
    }


    //add or update customer
    @RequestMapping(value ="/addUpdateCustomer", method = RequestMethod.POST)
    public String saveOrUpdateCustomer(Model model,
                                       @ModelAttribute("customer")CustomerTemp customerTemp,
                                       @RequestParam("new")boolean isNew,
                                       BindingResult bindingResult, final RedirectAttributes redirectAttributes){

                if(bindingResult.hasErrors()){
                    model.addAttribute(customerTemp);
                    return "customerForm";
                }
                else {
                    redirectAttributes.addFlashAttribute("css", "success");
                    if (isNew) {
                        makerActions.createNewRecord("satyam",customerTemp);
                        redirectAttributes.addFlashAttribute("msg", "User Added Successfully!");
                    }else{
                        makerActions.modifies("satyam", customerTemp);
                        redirectAttributes.addFlashAttribute("msg", "User Updated Successfully!");
                    }
                }
                return "redirect:/maker/home";
    }
    //show update customer form
    @RequestMapping(value = "/customer/{customerId}/update", method = RequestMethod.GET)
    public String showUpdateCustomerForm(@PathVariable("customerId") String customerId,
                                         @RequestParam("table") String table, Model model){
        if(table.equals("N") || table.equals("R")){
            CustomerTemp customerTemp = makerDAO.getTempRecord(customerId);
            model.addAttribute("customer", customerTemp);
        }
        else if(table.equals("A")){
            CustomerPerm customerPerm = makerDAO.getPermRecord(customerId);
            model.addAttribute("customer", customerPerm);
        }
        model.addAttribute("new", false);
        return "customerForm";
    }

    @RequestMapping(value = "/customer/{customerId}/delete", method = RequestMethod.POST)
    public String deleteCustomer(@PathVariable("customerId") String customerId,
                                 @RequestParam("table") String table,
                                 Model model, final RedirectAttributes redirectAttributes) {

        if(table.equals("N")||table.equals("R")){
            CustomerTemp customerTemp = makerDAO.getTempRecord(customerId);
            makerDAO.deleteTempRecord(customerTemp);
            redirectAttributes.addFlashAttribute("msg", "User is deleted!");
        }
        else if (table.equals("A")){
            CustomerPerm customerPerm = makerDAO.getPermRecord(customerId);
            customerPerm.setRecordStatus(RecordStatus.D);
            redirectAttributes.addFlashAttribute("msg", "User requested for delete!");
        }
        return "redirect:/maker/home";
    }

    @RequestMapping(value = "/newRecords", method = RequestMethod.GET)
    public String showNewRecordTable(Model model){
        List<CustomerTemp> newRecordTable;
        newRecordTable= makerActions.getTempCustomerList();
        model.addAttribute("customerList", newRecordTable);
        model.addAttribute("table", "N");
        return "recordTable";
    }

    @RequestMapping(value = "/authorizedRecords",method = RequestMethod.GET)
    public String showAuthorizedRecords(Model model){
        List<CustomerPerm> customerPermList;
        customerPermList = makerActions.getPermCustomerList().stream()
                .filter(c->c.getRecordStatus().equals(RecordStatus.A))
                .collect(Collectors.toList());

        model.addAttribute("customerList", customerPermList);
        model.addAttribute("table", "A");
        return "recordTable";
    }
    @RequestMapping(value = "/rejectedRecords", method = RequestMethod.GET)
    public String showRejectedRecords(Model model){
        List<CustomerTemp> customerTemp;
        customerTemp = makerActions.getTempCustomerList().stream()
                .filter(c->c.getRecordStatus().equals(RecordStatus.NR)||
                        c.getRecordStatus().equals(RecordStatus.MR)||
                c.getRecordStatus().equals(RecordStatus.DR))
                .collect(Collectors.toList());
        model.addAttribute("customerList", customerTemp);
        model.addAttribute("table", "R");
        return "recordTable";
    }

}
