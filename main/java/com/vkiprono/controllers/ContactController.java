package com.vkiprono.controllers;

import com.vkiprono.models.Contact;
import com.vkiprono.services.ContactServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class ContactController {
    private static final Logger logger = Logger.getLogger(ContactController.class.getName());


    @Autowired
    private ContactServiceI contactServiceI;

    @RequestMapping(value = "/contact/contactForm", method = RequestMethod.GET)
    public String getContactForm(Model model) {
        logger.info("------Fetching contact form-------");
        model.addAttribute("contactCommand", new Contact());
        logger.info("------Exiting contact form-------");
        return "add_contact";

    }

    @RequestMapping(value = "/contact/add", method = RequestMethod.POST)
    public String saveContact(Contact contact, Model model, HttpSession httpSession) {
        model.addAttribute("contactCommand", new Contact());
        Long userId = (Long) httpSession.getAttribute("personId");
        Long contactId = (Long) httpSession.getAttribute("contactId");
        contact.setUserId(userId);
        if (contactId == null) {
            logger.info("---ContactController.saveContact::::SAVING NEW contact---");
            contactServiceI.save(contact);

        } else {
            contact.setContactId(contactId);
            contactServiceI.update(contact);
        }
        logger.info("------Exiting ContactController.saveContact----");
        return "redirect:/contact/getAll?action=suc";

    }

    @RequestMapping(value = "/contact/getAll", method = RequestMethod.GET)
    @SuppressWarnings("Duplicates")
    public String getContacts(Model model, HttpSession httpSession) {
        logger.info("---ContactController.getContacts::::Beginning to fetch contacts---");
        Long userId = (Long) httpSession.getAttribute("personId");
        logger.info("Logged in user id is: " + userId);

        List<Contact> contactList = contactServiceI.findByProp("user_id", userId);
        model.addAttribute("contactList", contactList);
        logger.info("------Exiting ContactController.getContacts----");
        return "fetch_contacts";

    }

    @RequestMapping(value = "/contact/getAllUsers", method = RequestMethod.GET)
    @SuppressWarnings("Duplicates")
    public String getAllContacts(Model model, HttpSession httpSession) {
        logger.info("---ContactController.getAllContacts::::Beginning to fetch All contacts---");
        List<Contact> contactList = contactServiceI.findAll();
        model.addAttribute("contactList", contactList);
        logger.info("------Exiting ContactController.getContacts----");
        return "fetch_contacts";

    }

    @RequestMapping(value = "/contact/delete/{contactId}", method = RequestMethod.GET)
    public String deleteContact(@PathVariable Long contactId, HttpSession httpSession) {
        logger.info("---ContactController.deleteContact::::Beginning to delete contact---");
        Long userId = (Long) httpSession.getAttribute("personId");
        contactServiceI.deleteById(contactId);
        logger.info("------Exiting ContactController.deleteContact----");
        return "redirect:/contact/getAll?action=del";

    }

    @RequestMapping(value = "/contact/edit/{contactId}", method = RequestMethod.GET)
    public String getEditForm(Model model, HttpSession httpSession, @PathVariable("contactId") Long contactId) {
        logger.info("------Fetching contact form for editing-------");
        httpSession.setAttribute("contactId", contactId);
        Contact contact = contactServiceI.findById(contactId);
        contact.setContactId(contactId);
        model.addAttribute("contactCommand", contact);
        logger.info("------Exiting contact form-------");
        return "add_contact";

    }

    @RequestMapping(value = "/contact/edit", method = RequestMethod.POST)
    public String editContact(Model model, HttpSession httpSession) {
        logger.info("---ContactController.editContact::::Beginning to fetch contacts---");
        Long userId = (Long) httpSession.getAttribute("personId");
        List<Contact> contactList = contactServiceI.findByProp("user_id", userId);
        model.addAttribute("contactList", contactList);
        logger.info("Size of the contacts are: " + contactList.size());
        logger.info("------Exiting ContactController.getContacts----");
        return "fetch_contacts";
    }

    @RequestMapping(value = "/contact/search")
    public String search(Model model, HttpSession httpSession, @RequestParam(value = "keyword") String keyword) {
        logger.info("Beginning to search text:::");
        Long userId = (Long) httpSession.getAttribute("personId");
        List<Contact> contactList = contactServiceI.getUsersContacts(userId, keyword);
        model.addAttribute("contactList", contactList);
        logger.info("End to search text:::");
        return "fetch_contacts";
    }

    @RequestMapping(value = "/contact/bulkDelete")
    public String deleteBulk(@RequestParam("contactId") Long[] contactIds) {
        logger.info("Beginning to deleteBulk:::");
        logger.info("LENGHT OF SELECTED DATA  ::: " + contactIds.length);
        //  logger.info("SIZE OF SELECTED RECORDS IS ::: " + contactIds.length);
        contactServiceI.delete(contactIds);
        logger.info("End of delete bulk:::");

        return "redirect:/contact/getAll?action=del";

    }

    @RequestMapping(value = "/contact/getTime")
    @ResponseBody
    public String getTime() {
        logger.info(":::GETTING TIME:::");
        Date date = new Date();
        System.out.println("Date is " + date.toString());
        return date.toString();
    }


}
