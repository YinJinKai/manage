package com.system.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.system.model.RolePermissionRelModel;
import com.system.service.RolePermissionRelService;

@Controller
@RequestMapping("/rolePerRel")
public class RolePermissionRelAction {
@Autowired
private RolePermissionRelService<RolePermissionRelModel> rolePermissionRelService;











}
