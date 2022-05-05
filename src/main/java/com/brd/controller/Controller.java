package com.brd.controller;
import com.brd.dao.CheckerDAO;
import com.brd.dao.DAO;
import com.brd.dao.MakerDAO;
import com.brd.entity.CustomerPerm;
import com.brd.entity.CustomerTemp;
import com.brd.entity.FillRecord;
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
@SessionAttributes({"role","roleName"})
public class Controller {
    @Autowired
    @Qualifier("checkerActions")
    private CheckerActions checkerActions;
    @Autowired
    @Qualifier("makerActions")
    private MakerActions makerActions;
    @Autowired
    @Qualifier("makerDAO")
    private DAO makerDAO;
    @Autowired
    @Qualifier("checkerDAO")
    private DAO checkerDAO;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showHome(){
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("role")String role, Model model,
    final RedirectAttributes redirectAttributes, @RequestParam("uname") String uname,
            @RequestParam("pass") String pass){

        redirectAttributes.addAttribute("uname", uname);
        redirectAttributes.addAttribute("pass", pass);
        if(role.equals("maker")){
            return "redirect:/login/maker";
        }

            return "redirect:/login/checker";

    }
    //show add customer form
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showAddCustomerForm(Model model){
        CustomerTemp customerTemp = new CustomerTemp();
        model.addAttribute("customer", customerTemp);
        model.addAttribute("newCustomer", "true");
        return "customerForm";
    }


    //add customer
    @RequestMapping(value ="/add", method = RequestMethod.POST)
    public String saveCustomer(Model model, @SessionAttribute("roleName") String maker,
                                       @ModelAttribute("customer")CustomerTemp customerTemp,
                                       BindingResult bindingResult, final RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute(customerTemp);
            return "customerForm";

        } else {
            redirectAttributes.addFlashAttribute("css", "success");
            makerActions.createNewRecord(maker, customerTemp);
            redirectAttributes.addFlashAttribute("msg", "Record Added Successfully!");
            return "redirect:/newRecords";
        }
    }
    //show update customer form
    @RequestMapping(value="/update", method=RequestMethod.GET)
    public String showUpdateCustomer(Model model,
                                     @RequestParam("customerCode") String customerCode,
                                     @RequestParam("table") String table,
                                     final RedirectAttributes redirectAttributes){
        if (table.equals("A")){
            CustomerTemp customerTemp =FillRecord.toTemp(makerDAO.getPermRecord(customerCode));
            model.addAttribute("customer", customerTemp);
        } else{
            CustomerTemp customerTemp = makerDAO.getTempRecord(customerCode);
            model.addAttribute("customer",customerTemp);
        }
        model.addAttribute("newCustomer", "false");
        model.addAttribute("table", table);
        return "customerForm";
    }
    //update customer
    @RequestMapping(value="/update", method=RequestMethod.POST)
    public String updateCustomer(Model model, @SessionAttribute("roleName") String maker,
                                 @ModelAttribute("customer")CustomerTemp customerTemp,
                                 BindingResult bindingResult,
                                @RequestParam("table") String table,
                                 final RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.getAllErrors());
        }

          if(table.equals("A")){
              if(makerDAO.getTempRecord(customerTemp.getCustomerCode())!=null){
                  redirectAttributes.addFlashAttribute("css", "warning");
                  redirectAttributes.addFlashAttribute("msg", "Record already rejected or" +
                          " modified, update " +
                          "it there!");
              }
              else {
                  CustomerPerm customerPerm = FillRecord.toPerm(customerTemp);
                  makerActions.modifies("satyam", customerPerm);
                  redirectAttributes.addFlashAttribute("css", "success");
                  redirectAttributes.addFlashAttribute("msg", "Record Updated Successfully!");
              }
          }
          else{
              makerActions.modifies("satyam", customerTemp);
              redirectAttributes.addFlashAttribute("css", "success");
              redirectAttributes.addFlashAttribute("msg", "Record Updated Successfully!");
          }
          return "redirect:/newRecords";
    }


    @RequestMapping(value = "/deleteCustomer", method = RequestMethod.POST)
    public String deleteCustomer(Model model, @RequestParam("customerCode") String customerCode,
                                 @RequestParam("table") String table
                                 , final RedirectAttributes redirectAttributes) {

        if(table.equals("N")||table.equals("R")){
            CustomerTemp customerTemp = makerDAO.getTempRecord(customerCode);
            makerActions.deletes(customerTemp);
            redirectAttributes.addFlashAttribute("css", "success");
            redirectAttributes.addFlashAttribute("msg", "Record is deleted!");
        }
        else if (table.equals("A")){
            CustomerPerm customerPerm = makerDAO.getPermRecord(customerCode);
            makerActions.deletes(customerPerm);
            redirectAttributes.addFlashAttribute("css", "warning");
            redirectAttributes.addFlashAttribute("msg", "Record requested for delete!");
        }
        return "redirect:/newRecords";
    }

    @RequestMapping(value = "/newRecords", method = RequestMethod.GET)
    public String showNewRecordTable(Model model){
        List<CustomerTemp> newRecordTable;
        newRecordTable= makerActions.getTempCustomerList().stream()
                .filter(c->c.getRecordStatus().equals(RecordStatus.N) ||
                        c.getRecordStatus().equals(RecordStatus.M))
                .collect(Collectors.toList());
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

    @RequestMapping(value = "/deletedRecords", method = RequestMethod.GET)
    public String showDeletedRecords(Model model){
        List<CustomerTemp> recordTable;
        recordTable= makerActions.getTempCustomerList().stream()
                .filter(c->c.getRecordStatus().equals(RecordStatus.D))
                .collect(Collectors.toList());
        model.addAttribute("customerList", recordTable);
        model.addAttribute("table", "D");
        return "recordTable";
    }
    @RequestMapping(value= "/authorize", method = RequestMethod.POST)
    public String authorizes(Model model, @RequestParam("table") String table,
                             @SessionAttribute("roleName") String checker,
                             @RequestParam("customerCode") String customerCode,
                             @RequestParam("recordStatus") RecordStatus recordStatus,
                             final RedirectAttributes redirectAttributes){

            CustomerTemp customerTemp = makerDAO.getTempRecord(customerCode);
            checkerActions.authorize(checker, customerTemp);
            redirectAttributes.addFlashAttribute("css", "success");
            if (table.equals("D")){
                redirectAttributes.addFlashAttribute("msg", "Record Deleted!");
            }
            else{
                redirectAttributes.addFlashAttribute("msg", "Record Authorized");
            }
            return "redirect:/newRecords";
        }
        @RequestMapping(value="/reject", method = RequestMethod.POST)
    public String rejects(Model model, @RequestParam("table") String table,
                          @RequestParam("customerCode") String customerCode,
                          final RedirectAttributes redirectAttributes){

            CustomerTemp customerTemp = makerDAO.getTempRecord(customerCode);
            checkerActions.reject(customerTemp);
            redirectAttributes.addFlashAttribute("css", "success");
            if (table.equals("D")){
                redirectAttributes.addFlashAttribute("msg", "Record delete request rejected!");
            }
            else{
                redirectAttributes.addFlashAttribute("msg", "Record Rejected!");
            }
            return "redirect:/newRecords";

        }

}
