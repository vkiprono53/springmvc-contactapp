package com.vkiprono.controllers;

import com.vkiprono.command.LoginCommand;
import com.vkiprono.command.PersonCommand;
import com.vkiprono.models.Person;
import com.vkiprono.services.PersonServiceI;
import com.vkiprono.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.logging.Logger;

@Controller
public class PersonController {
    private static final Logger logger = Logger.getLogger(PersonController.class.getName());
    @Autowired
    private PersonServiceI personServiceI;

    @RequestMapping(value = {"/", "/home"})
    public String home(Model model) {
        logger.info(":::PersonController.home. Fetching homepage");
        model.addAttribute("command", new LoginCommand());

        logger.info(":::PersonController.home. Exiting home.......");

        return "home";
    }

    /**
     * Method to handle login
     *
     * @param
     * @return String
     */

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute("command") LoginCommand loginCommand, Model model, BindingResult bindingResult, HttpSession httpSession) {
        logger.info(":::PersonController.login.Logging in...");

        if (bindingResult.hasErrors()) {
            return "home";
        }
        String username = loginCommand.getUserName();
        String password = loginCommand.getPassword();
        Person person = personServiceI.login(username, password);
        if (person == null) {
            model.addAttribute("err", "Login Failed! Please check your username and password");
        } else {
            if (person.getRole().equals(Constants.ROLE_ADMIN)) {

                addUserSession(person, httpSession);
                logger.info("---ADMIN: THE PERSON ID HERE IS : " + person.getUserId() + " ::::PERSON ID IN SESSION IS : > " + httpSession.getAttribute("personId"));
                return "redirect:/admin/dashboard";// "Admin-dashboard";

            } else if (person.getRole().equals(Constants.ROLE_USER)) {
                addUserSession(person, httpSession);
                logger.info("---user: THE PERSON ID HERE IS : " + person.getUserId() + " ::::USER ID IN SESSION IS : > " + httpSession.getAttribute("personId"));

                return "redirect:/user/dashboard";// "dashboard_user";
            } else {
                model.addAttribute("err", "No such Role.Please contact the system admin.");
                return "home";
            }
        }


        logger.info(":::PersonController.login. Exiting Login....");

        return "home";
    }

    /**
     * Dashboard for a normal user
     *
     * @return
     */
    @RequestMapping(value = "/user/dashboard")
    public String userDashboard() {
        return "dashboard_user";
    }

    /**
     * Admin Dashbord
     *
     * @return
     */
    @RequestMapping(value = "/admin/dashboard")
    public String adminDashboard() {
        return "dashboard_admin";
    }

    /**
     * Registering New User
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/user/getRegisterForm", method = RequestMethod.GET)
    public String loadRegisterPage(Model model) {
        logger.info(":::PersonController.Beginning to Fetch Register Form ....");
        model.addAttribute("personCommand", new PersonCommand());

        logger.info(":::PersonController.registerUser. Exiting to register user....");

        return "register_user";
    }


    /**
     * Registering New User
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/user/register")
    public String registerUser(Model model,@ModelAttribute("command") PersonCommand personCommand) {
        logger.info(":::PersonController.registerUser. Beginning to register user....");

        Person person = new Person();

        String firstName = personCommand.getFirstName();
        String lastName = personCommand.getLastName();
        String phone = personCommand.getPhone();
        String email = personCommand.getEmail();
        String address = personCommand.getAddress();
        String loginName = personCommand.getLoginName();
        String password = personCommand.getLoginName();

        logger.info(":::PersonController.registerUser. User Details are : ....");
        logger.info(":::firstName==========" + firstName);
        logger.info(":::lastName==========" + lastName);
        logger.info(":::phone==========" + phone);
        logger.info(":::email==========" + email);
        logger.info(":::address==========" + address);
        logger.info(":::loginName==========" + loginName);

        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setPhone(phone);
        person.setEmail(email);
        person.setAddress(address);
        person.setLoginName(loginName);
        person.setPassword(password);
        person.setLoginStatus(Constants.LOGIN_STATUS_ACTIVE);
        person.setRole(Constants.ROLE_USER);


        try {
            personServiceI.registerUser(person);

            return "redirect:/home?action=re";


        }
        catch (DuplicateKeyException exception){
            exception.printStackTrace();
            model.addAttribute("error", "Username already exists . Please try another username");
            model.addAttribute("personCommand", new PersonCommand());
            return "register_user";
          //  return "redirect:/user/getRegisterForm";

        }

    }


    private void addUserSession(Person person, HttpSession httpSession) {
        httpSession.setAttribute("person", person);
        httpSession.setAttribute("personId", person.getUserId());
        httpSession.setAttribute("personRole", person.getRole());
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession httpSession) {
        logger.info(":::PersonController.logout. Inside logout....");

        httpSession.invalidate();
        logger.info(":::PersonController.logout. Exiting logout....");

        return "redirect:/home?action=lo";

    }

}
