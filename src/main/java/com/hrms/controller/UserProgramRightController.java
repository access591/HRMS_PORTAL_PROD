package com.hrms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hrms.ImageUtil;
import com.hrms.model.MenuModule;
import com.hrms.model.Module;
import com.hrms.model.Program;
import com.hrms.model.Role;
import com.hrms.model.SubModule;
import com.hrms.model.UserEntity;
import com.hrms.model.UserRights;
import com.hrms.model.UserRole;
import com.hrms.service.ModuleService;
import com.hrms.service.ProgramService;
import com.hrms.service.RoleRepository;
import com.hrms.service.SubModuleService;
import com.hrms.service.UserProgramRightService;
import com.hrms.service.UserRoleService;
import com.hrms.service.UserService;

@Controller
public class UserProgramRightController {

	@Autowired
	UserService userService;
	@Autowired
	private ModuleService moduleService;
	@Autowired
	SubModuleService subModuleService;

	@Autowired
	ProgramService programService;

	@Autowired
	UserProgramRightService userProgramRightService;

	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserRoleService userRoleService;

	@GetMapping("/userProgramRights")
	public String userProgramRightsList(Model model, HttpSession session) {
		String userCode = (String) session.getAttribute("username");

		if (userCode != null) {
			List<UserRights> listUserRights = userProgramRightService.getAllUserRights();
			model.addAttribute("listUserRight", listUserRights);

			List<UserEntity> listUsers = userService.getActiveUsers();
			model.addAttribute("listUsers", listUsers);

			List<Module> modulesList = moduleService.getActiveModules();
			model.addAttribute("modulesList", modulesList);
			List<MenuModule> modules = moduleService.getAllModulesList(userCode);
			if (modules != null) {
				model.addAttribute("modules", modules);
			}
			session.setAttribute("imgUtil", new ImageUtil());
			session.setAttribute("username", session.getAttribute("username"));
			return "UserProgramRight";
		} else {
			return "redirect:" + "./";
		}
	}

	@PostMapping("/saveUserRights")
	public String userProgramRightSave(@ModelAttribute("UserRights") UserRights userRights, Model model,
			HttpSession session, RedirectAttributes redirectAttributes) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}

		System.out.println("testing =  :" + userRights.getUserCode().getUserCode());

		String userCode = userRights.getUserCode().getUserCode();
		String prgCode = userRights.getPrgCode().getProgramCode();
		String subModulec = userRights.getSubModuleCode().getSubModuleCode();
		String moduleCode=userRights.getModuleCode().getModuleCode();

		boolean isUserRightsExist = userProgramRightService.checkUserRightsExists(userRights);
		if (isUserRightsExist) {
			redirectAttributes.addFlashAttribute("message", "User Rights  Already exists !  ");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");
			return "redirect:/userProgramRights";

		}

		else if (userRights.getModuleCode().getModuleCode().equals("ALL")
				&& userRights.getSubModuleCode().getSubModuleCode().equals("ALL")
				&& userRights.getPrgCode().getProgramCode().equals("ALL")) {

			System.out.println("1th case");
			List<Program> listUserRights = programService.getAllPrograms();

			UserRights urights = new UserRights();
			for (int i = 0; i < listUserRights.size(); i++) {

				Program p = new Program();
				if (listUserRights.get(i).getActiveYn().equals("Y")) {
					p.setProgramCode(listUserRights.get(i).getProgramCode());
					urights.setModuleCode(listUserRights.get(i).getpModuleCode());
					urights.setSubModuleCode(listUserRights.get(i).getSubModuleCode());
					urights.setPrgCode(p);

					urights.setUserCode(userRights.getUserCode());
					urights.setActive(userRights.getActive());
					urights.setInsBy((String) session.getAttribute("USER_NAME"));
					addOrUpdateRole(listUserRights.get(i).getProgramCode(), userCode);
					userProgramRightService.addUserProgramRight(urights);
				}

			}
		}

		else if (userRights.getModuleCode().getModuleCode().equals("ALL")
				&& userRights.getSubModuleCode().getSubModuleCode().equals("ALL")
				&& userRights.getPrgCode().getProgramCode().equals(prgCode)) {

			System.out.println("2nd case");

			Program programByid = programService.findProgramById(prgCode);
			UserRights urights = new UserRights();
			if (programByid.getActiveYn().equals("Y")) {
				urights.setPrgCode(programByid);
				urights.setModuleCode(programByid.getpModuleCode());
				urights.setSubModuleCode(programByid.getSubModuleCode());
				urights.setUserCode(userRights.getUserCode());
				urights.setActive(userRights.getActive());
				urights.setUserCode(userRights.getUserCode());
				urights.setActive(userRights.getActive());
				urights.setInsBy((String) session.getAttribute("USER_NAME"));
				addOrUpdateRole(prgCode, userCode);

				userProgramRightService.addUserProgramRight(urights);

			}

		}
		
	//==================ALL Single ALL===========
		
		else if (userRights.getModuleCode().getModuleCode().equals("ALL")
				&& userRights.getSubModuleCode().getSubModuleCode().equals(subModulec)
				&& userRights.getPrgCode().getProgramCode().equals("ALL")) {

			System.out.println("ALL Single ALL");

			List<Program> pr = programService.findProgramByIdSubModule(subModulec);	
			
			for(int i=0;i<pr.size();i++)
			{
				
			Program programByid = programService.findProgramById(pr.get(i).getProgramCode());
			UserRights urights = new UserRights();
			if (programByid.getActiveYn().equals("Y")) {
				urights.setPrgCode(programByid);
				urights.setModuleCode(programByid.getpModuleCode());
				urights.setSubModuleCode(programByid.getSubModuleCode());
				urights.setUserCode(userRights.getUserCode());
				urights.setActive(userRights.getActive());
				urights.setUserCode(userRights.getUserCode());
				urights.setActive(userRights.getActive());

				urights.setInsBy((String) session.getAttribute("USER_NAME"));
				//addOrUpdateRole(prgCode, userCode);

				addOrUpdateRole(programByid.getProgramCode(), userCode);

				userProgramRightService.addUserProgramRight(urights);

			}
			}
		}
		
		
		//==========Single ========ALL===================All==============
		else if (userRights.getModuleCode().getModuleCode().equals(moduleCode)
				&& userRights.getSubModuleCode().getSubModuleCode().equals("ALL")
				&& userRights.getPrgCode().getProgramCode().equals("ALL")) {

			System.out.println(" Single  ALL ALL");

			List<Program> prg = programService.findProgramByIdModuleCode(moduleCode);	
			
			for(int i=0;i<prg.size();i++)
			{
			Program programByid = programService.findProgramById(prg.get(i).getProgramCode());
			UserRights urights = new UserRights();
			if (programByid.getActiveYn().equals("Y")) {
				urights.setPrgCode(programByid);
				urights.setModuleCode(programByid.getpModuleCode());
				urights.setSubModuleCode(programByid.getSubModuleCode());
				urights.setUserCode(userRights.getUserCode());
				urights.setActive(userRights.getActive());
				urights.setUserCode(userRights.getUserCode());
				urights.setActive(userRights.getActive());

				urights.setInsBy((String) session.getAttribute("USER_NAME"));
				//addOrUpdateRole(prgCode, userCode);

				addOrUpdateRole(programByid.getProgramCode(), userCode);

				userProgramRightService.addUserProgramRight(urights);

			}
			}

		}
		
		
	//=================SINGLE,SINGLE,ALL===========
		
		else if (userRights.getModuleCode().getModuleCode().equals(moduleCode)
				&& userRights.getSubModuleCode().getSubModuleCode().equals(subModulec)
				&& userRights.getPrgCode().getProgramCode().equals("ALL")) {

			System.out.println("ALL Single ALL");

			List<Program> pr = programService.findProgramByModuleCodeSubModule(moduleCode,subModulec);	
			
			for(int i=0;i<pr.size();i++)
			{
				
			Program programByid = programService.findProgramById(pr.get(i).getProgramCode());
			UserRights urights = new UserRights();
			if (programByid.getActiveYn().equals("Y")) {
				urights.setPrgCode(programByid);
				urights.setModuleCode(programByid.getpModuleCode());
				urights.setSubModuleCode(programByid.getSubModuleCode());
				urights.setUserCode(userRights.getUserCode());
				urights.setActive(userRights.getActive());
				urights.setUserCode(userRights.getUserCode());
				urights.setActive(userRights.getActive());

				urights.setInsBy((String) session.getAttribute("USER_NAME"));
				//addOrUpdateRole(prgCode, userCode);

				addOrUpdateRole(programByid.getProgramCode(), userCode);

				userProgramRightService.addUserProgramRight(urights);

			}
			}
		}

		else if (userRights.getModuleCode().getModuleCode().equals("ALL")
				&& userRights.getSubModuleCode().getSubModuleCode().equals(subModulec)
				&& userRights.getPrgCode().getProgramCode().equals(prgCode)) {

			System.out.println("3rd case");
			Program programByidRecord = programService.findProgramById(prgCode);
			UserRights urights = new UserRights();
			if (programByidRecord.getActiveYn().equals("Y")) {
				urights.setPrgCode(programByidRecord);
				urights.setModuleCode(programByidRecord.getpModuleCode());
				urights.setSubModuleCode(programByidRecord.getSubModuleCode());
				urights.setUserCode(userRights.getUserCode());
				urights.setActive(userRights.getActive());
				urights.setUserCode(userRights.getUserCode());
				urights.setActive(userRights.getActive());
				urights.setInsBy((String) session.getAttribute("USER_NAME"));
				addOrUpdateRole(prgCode, userCode);

				userProgramRightService.addUserProgramRight(urights);

			}

		}

		else {

			System.out.println("4th case");

			String programCode = userRights.getPrgCode().getProgramCode();
			Program program = programService.findProgramById(programCode);

			System.out.println("program detailos : " + program.getProgramHrefName());
			userRights.setInsBy((String) session.getAttribute("USER_NAME"));
			addOrUpdateRole(programCode, userCode);

			userProgramRightService.addUserProgramRight(userRights);
			session.setAttribute("username", session.getAttribute("username"));
		}
		return "redirect:/userProgramRights";

	}

	@GetMapping(value = { "/editUserRights/{id}" })
	public String editUserRights(@PathVariable("id") long id, Model model, HttpSession session) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		List<UserEntity> listUsers = userService.getAllUsers();
		model.addAttribute("listUsers", listUsers);

		List<Module> modulesList = moduleService.getActiveModules();
		model.addAttribute("modulesList", modulesList);
		session.setAttribute("imgUtil", new ImageUtil());
		List<SubModule> subModulesList = subModuleService.getActiveSubModules();
		model.addAttribute("subModulesList", subModulesList);

		List<Program> programsList = programService.getActivePrograms();
		model.addAttribute("programsList", programsList);

		UserRights userRightsEdit = userProgramRightService.findUserRightById(id);
		model.addAttribute("userRightsEdit", userRightsEdit);

		session.setAttribute("username", session.getAttribute("username"));
		return "/editUserRights";
	}

	@PostMapping("/updateUserRights")
	public String upadteUserRight(@ModelAttribute("UserRights") UserRights ur, Model model, HttpSession session) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		ur.setUpdBy((String) session.getAttribute("USER_NAME"));
		this.userProgramRightService.updateUserRights(ur);
		return "redirect:/userProgramRights";

	}

	@GetMapping(value = { "/deleteUserRights/{id}" })
	public String deleteUserRights(@PathVariable("id") Long id, Model model, HttpSession session) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		
		deleteUserRole(id);
		this.userProgramRightService.removeUserProgramRight(id);
		session.setAttribute("username", session.getAttribute("username"));
		return "redirect:/userProgramRights";
		
	}

	@ResponseBody
	@GetMapping("/viewModuleBySubModule/{id}")
	public List<SubModule> viewModuleBySubModule(@PathVariable(value = "id") String id, Model model,
			HttpSession session) {
		List<SubModule> e = subModuleService.findSubModuleByModuleCode(id);
		List<SubModule> lisSubModuleUtil = new ArrayList<>();
		for (int i = 0; i < e.size(); i++) {
			SubModule st = new SubModule();
			st.setSubModuleCode(e.get(i).getSubModuleCode());
			st.setSubModuleName(e.get(i).getSubModuleName());

			lisSubModuleUtil.add(st);
		}
		return lisSubModuleUtil;

	}

	@ResponseBody
	@GetMapping("/viewSubModuleByProgramCode/{id}/{id2}")
	public List<Program> viewSubModuleByProgramCode(@PathVariable(value = "id") String id,
			@PathVariable("id2") String id2, Model model, HttpSession session) {
		List<Program> e = programService.findByProgramCodeSubModule(id, id2);
		List<Program> lisSubModuleUtil = new ArrayList<>();
		for (int i = 0; i < e.size(); i++) {
			Program st = new Program();
			st.setProgramCode(e.get(i).getProgramCode());
			st.setProgramName(e.get(i).getProgramName());

			lisSubModuleUtil.add(st);
		}
		return lisSubModuleUtil;

	}


public void addOrUpdateRole(String prgCode, String userCode) {
		// ./programname
		// role management

		Program program = programService.findProgramById(prgCode);

		System.out.println("get program code +=====" + prgCode);

		String rolename = program.getProgramHrefName().replaceAll("\\s", "").toUpperCase().substring(2);
		System.out.println("role name " + rolename);
		Role r = roleRepository.isRoleExistOrNot(rolename); // null == data not found (role is not exists)

		UserEntity u = userService.findByUserCode(userCode);

		if (r != null) {

			UserRole userRole = new UserRole();

			userRole.setRole(r);
			userRole.setUserEntity(u);

			// to check role already assign or not
			boolean isExists = userRoleService.roleExistForUser(u.getUserCode(), r.getRoleName()); // if true : file
																									// alredy exists

			if (!isExists) {
				System.out.println(" file is not exists ");
				userRoleService.addUserRole(userRole);
			}
			System.out.println("close");

		}

		else {
			Role role = new Role();
			role.setRoleName(rolename);
			roleRepository.addRole(role);
			Role r2 = roleRepository.isRoleExistOrNot(rolename);

			UserRole userRole = new UserRole();
			userRole.setRole(r2);
			userRole.setUserEntity(u);
			userRoleService.addUserRole(userRole);
		}
	}
				
		
	
		
	
	
	public void deleteUserRole(Long userProgramRightId) {
		UserRights userRight = this.userProgramRightService.findUserRightById(userProgramRightId);
		
		String roleName = userRight.getPrgCode().getProgramHrefName().replaceAll("\\s", "").toUpperCase().substring(2);
		Role role = roleRepository.isRoleExistOrNot(roleName);
		UserRole userRole = new UserRole();
		
		UserEntity u = new UserEntity();
		u.setUserCode(userRight.getUserCode().getUserCode());
		
		
		userRole.setUserEntity(u);
		userRole.setRole(role);
		
		userRoleService.deleteUserRole(u, role);
		
		
	}
}
