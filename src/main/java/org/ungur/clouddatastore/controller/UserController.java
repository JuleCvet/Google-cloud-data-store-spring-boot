package org.ungur.clouddatastore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.ungur.clouddatastore.model.Role;
import org.ungur.clouddatastore.model.Message;
import org.ungur.clouddatastore.model.User;
import org.ungur.clouddatastore.service.UserService;

import java.util.ArrayList;
import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    
    @RequestMapping(value="/users", method= RequestMethod.GET)
    public ArrayList<User> getUsers(Model model) {
    	ArrayList<User> users = (ArrayList<User>) userService.readAllUsers();
    	model.addAttribute("list", users);
    	
		return users;
    }
    
    @RequestMapping(value="/getusers", method= RequestMethod.GET)
    @ResponseBody//so postMan
    public ArrayList<User> getAllUsers() {
    	ArrayList<User> users = (ArrayList<User>) userService.readAllUsers();
    	
    	return users;
		 
    }
    
    
    @RequestMapping(value="/getuser/{id}", method= RequestMethod.GET)
    @ResponseBody
    public User getUser(@PathVariable("id") Long id) {
    	User userExample = userService.readUser(id);
    	
    	return userExample;
    }

    
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ResponseEntity<Message> addUser(@Valid @RequestBody User user) {
        userService.createUser(user);//so postMan
        
        return ResponseEntity.ok().body(new Message("Created"));
    }
    
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registration(Model model) {
		model.addAttribute("userForm", new User());

		return "registration";
	}
    
    
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "registration";
		}
		userService.createUser(userForm);
		//securityService.autologin(userForm.getFullName(), userForm.get);
			return "redirect:/welcome";
	}
	
	
	@RequestMapping(value = "/updateuser", method = RequestMethod.GET)
    public String updateuser(Model model) {
		model.addAttribute("updateForm", new User());

    	return "updateuser";  
    }
	
	@RequestMapping(value = "/updateuser", method = RequestMethod.POST)
	public String updateuser(@ModelAttribute("updateForm") User updateForm, BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()) {
			return "updateuser";
		}
		userService.updateUser(updateForm);
		
		return "redirect:/users";	
	}
    
    @RequestMapping(value = "/updateUser", method = RequestMethod.PUT)
    public ResponseEntity<Message> updateUser(@Valid @RequestBody User user) {
        userService.updateUser(user);//so postMan
        
        return ResponseEntity.ok().body(new Message("Updated"));
    }
    
        
    
	@RequestMapping(value= {"/", "/welcome" }, method = RequestMethod.GET)
	public String welcome(Model model) {
		
		return "welcome";	
	}
	
    
    @RequestMapping(value = "/users/batch", method = RequestMethod.POST)
    public ResponseEntity<Message> addUsers(@Valid @RequestBody Role users) {
        userService.createUser(users);
        
        return ResponseEntity.ok().body(new Message("Batch created: " + users.getUsers().size()));
    }

    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginUser(Model model, String error, String logout) {
    	if(error!=null) {
    		model.addAttribute("error", "Your username and password is invalid.");
    	}	
    	
    	if(logout!=null) {
    		model.addAttribute("message", "You have been logout successfully.");
    	}
		return "login";
	}
    
    
    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Message> deleteUser(@PathVariable ("id") Long id) {
        userService.deleteUser(id);
        
        return ResponseEntity.ok().body(new Message("Successfully deleted"));
    }
    
   /* @RequestMapping(value="/delete/{id}", method= RequestMethod.GET)
    public String deleteEmployeeById(Model model, @PathVariable Long employeeId) {
    	model.addAttribute("deleteUser", userService.readUser(employeeId));
    	
    	return "delete";
    }*/
    
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
   	public String delete(Model model) {
   		model.addAttribute("deleteUser", new User());

   		return "delete";
   	}
    
   @RequestMapping(value = "/delete", method = RequestMethod.POST)
   public String deleteEmployeeById(@ModelAttribute("userDelete") User userForm){
   
    	userService.deleteUser(userForm.getId());
    	
    	return "redirect:/users";
   }
   
}
