package org.ungur.clouddatastore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.ungur.clouddatastore.service.UserService;

@Controller
public class AssignmentController {

	@Autowired
    private UserService userService;
}
