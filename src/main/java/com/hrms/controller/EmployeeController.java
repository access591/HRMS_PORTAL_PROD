package com.hrms.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hrms.helper.FileService;
import com.hrms.model.ArmsLicenses;
import com.hrms.model.ArmsLicensesDetail;
import com.hrms.model.Category;
import com.hrms.model.City;
import com.hrms.model.Country;
import com.hrms.model.Department;
import com.hrms.model.Designation;
import com.hrms.model.Employee;
import com.hrms.model.EmployeeAcr;
import com.hrms.model.EmployeePromotion;
import com.hrms.util.EmployeeDto;
import com.hrms.util.EmployeeMasterDto;
import com.hrms.util.PaginationDto;
import com.hrms.model.MenuModule;
import com.hrms.model.State;
import com.hrms.repository.ArmsLicenseDao;
import com.hrms.service.ArmsLicenseService;
import com.hrms.service.CategoryService;
import com.hrms.service.CityService;
import com.hrms.service.CountryService;
import com.hrms.service.DepartmentService;
import com.hrms.service.DesignationService;
import com.hrms.service.EmployeeAcrService;
import com.hrms.service.EmployeePromotionService;
import com.hrms.service.EmployeeService;
import com.hrms.service.ModuleService;
import com.hrms.service.PageMappingService;
import com.hrms.service.StateService;

@Controller
public class EmployeeController {

	int pageno = 43;
	String reqPage = "/employeeMaster";

	@Autowired private DepartmentService departmentService;
	@Autowired private DesignationService designationService;
	@Autowired private PageMappingService pageMappingService;
	@Autowired private ModuleService moduleService;
	@Autowired private EmployeeService employeeService;
	@Autowired private ArmsLicenseService armsLicenseService;
	@Autowired private CityService cityService;
	@Autowired private StateService stateService;
	@Autowired private CountryService countryService;
	@Autowired private ArmsLicenseDao armsLicenseDao;
	@Autowired private EmployeePromotionService employeePromotionService;
	@Autowired private EmployeeAcrService employeeAcrService;
	@Autowired private CategoryService categoryService;

	@GetMapping("/employeeMaster")
	public String employeeMaster(@Valid @ModelAttribute("employeeDto") EmployeeDto employeeDto, Model model,
			HttpSession session) {



		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}

		List<Designation> listDesignation = designationService.getAllDesignations();
		if (listDesignation != null) {
			model.addAttribute("listDesignation", listDesignation);
		}
		List<Department> listDepartment = departmentService.getAllDepartments();
		model.addAttribute("listDepartment", listDepartment);

		List<City> cityList = cityService.getAllCities();
		model.addAttribute("CityList", cityList);

		List<State> listState = stateService.getAllStates();
		model.addAttribute("listState", listState);

		List<Country> listCountry = countryService.getAllCountrys();
		model.addAttribute("listCountry", listCountry);

		model.addAttribute("employeeDto", employeeDto);

		List<ArmsLicenses> listArmsLicenses = armsLicenseService.getAllArmsLicenses();
		model.addAttribute("listArmsLicenses", listArmsLicenses);

		

		List<Employee> listEmployee = employeeService.findAllEmployee();
		model.addAttribute("listEmployee", listEmployee);

		List<Category> categoryList = categoryService.getActiveCategory();
		model.addAttribute("categoryList", categoryList);
		
	
		return "employeeMaster";
	}

	@PostMapping("/saveEmployee")
	public String employeeMasterSave(@ModelAttribute("employeeDto") EmployeeDto employeeDto, Model model,
			HttpSession session, @RequestParam("profileImage") MultipartFile file,
			RedirectAttributes redirectAttributes) {

		System.out.println("save controller");
		
		System.out.println("save controller" + employeeDto.getArmsLicensesDetail().get(0).getArmsLicenseNumber());


		System.out.println("hiii");
		String originalFile = "";

		try {

			Employee employee = employeeDto.getEmployee();

			if (file.isEmpty()) {
				employee.setProfilePic("default.png");
			} else {

				String name = FilenameUtils.removeExtension(file.getOriginalFilename());
				String extension = FilenameUtils.getExtension(file.getOriginalFilename());

				Date d = new Date();

				originalFile = name + d.getSeconds() + "." + extension;

				System.out.println("original file name : " + originalFile);

				System.out.println("extension : " + extension);
				employee.setProfilePic(originalFile);

				File saveFileFolder = new ClassPathResource("static/img/").getFile();

				System.out.println("save file folder : " + saveFileFolder);
				String uploadDir = saveFileFolder.getAbsolutePath();

				System.out.println("upload dir : " + uploadDir);

				FileService.saveFile(uploadDir, originalFile, file);

			}
			employee.setInsBy((String) session.getAttribute("USER_NAME"));
			Employee empl = employeeService.addEmployee(employee);
			System.out.println("========employee tsting saving or not ===========" + empl.getEmpCode());

			ArmsLicenses armsLicenses = employeeDto.getArmsLicenses();

			armsLicenses.setArmsCode(armsLicenseDao.getMaxId("ARM"));
			armsLicenses.setEmployee(empl);

			List<ArmsLicensesDetail> armsLicensesDetail = employeeDto.getArmsLicensesDetail();
			List<ArmsLicensesDetail> aLicDetail = new ArrayList<>();

			int numberOfArms = 0;
			int count = 0;
			for (ArmsLicensesDetail licensesDetail : armsLicensesDetail) {
				numberOfArms = count++;
				licensesDetail.setArmsLicenses(armsLicenses);
				aLicDetail.add(licensesDetail);
			}
			armsLicenses.setArmsNol(String.valueOf(numberOfArms));
			armsLicenses.setArmsLicensesDetail(aLicDetail);

			//employee.setArmLicense(armsLicenses);

			armsLicenseService.addArmsLicenseDetails(armsLicenses);
			redirectAttributes.addFlashAttribute("message", "Record  has been saved successfully!");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");	

		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message", "Something Went Wrong !!  ");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		}

		return "redirect:employeeMaster";
		
		
		

	}

	@GetMapping(value = { "/editEmployee/{id}" })
	public String editEmployee(@PathVariable("id") String empCode,
			@Valid @ModelAttribute("employeeDto") EmployeeDto employeeDto, Model model, HttpSession session) {


		List<City> cityList = cityService.getAllCities();
		model.addAttribute("CityList", cityList);

		List<State> listState = stateService.getAllStates();
		model.addAttribute("listState", listState);

		List<Country> listCountry = countryService.getAllCountrys();
		model.addAttribute("listCountry", listCountry);

		List<Designation> listDesignation = designationService.getAllDesignations();
		if (listDesignation != null) {
			model.addAttribute("listDesignation", listDesignation);
		}
		List<Department> listDepartment = departmentService.getAllDepartments();
		model.addAttribute("listDepartment", listDepartment);

		List<Category> categoryList = categoryService.getActiveCategory();
		model.addAttribute("categoryList", categoryList);
		
		
		ArmsLicenses armLicenses = armsLicenseService.findArmsLicensesByEmployee(empCode);

		System.out.println("employee department : " + armLicenses.getEmployee().getDepartment().getDepartmentCode());
		employeeDto.setEmployee(armLicenses.getEmployee());
		employeeDto.setArmsLicenses(armLicenses);

		employeeDto.setArmsLicensesDetail(armLicenses.getArmsLicensesDetail());
		model.addAttribute("employeeDto", employeeDto);

		return "editEmployee";
	}

	@PostMapping("/updateEmployeemaster")
	public String updatePageUrl(@Valid @ModelAttribute("employeeDto") EmployeeDto employeeDto, Model model,
			HttpSession session, @RequestParam("profileImage") MultipartFile file,RedirectAttributes redirectAttributes) throws IOException {

		System.out.println("==============Hit Controller==================");

		System.out.println("arms license : id ========");

		System.out.println("file image : ===>" + file.getOriginalFilename());

		String originalFile = "";

		if (!file.isEmpty()) {

			String name = FilenameUtils.removeExtension(file.getOriginalFilename());
			String extension = FilenameUtils.getExtension(file.getOriginalFilename());

			System.out.println("file is not empty");
			// employeeDto.getEmployee().setProfilePic(file.getOriginalFilename() + new
			// Date());

			Employee employee = employeeService.findEmployeeByEmpCode(employeeDto.getEmployee().getEmpCode());
			String profilePic = employee.getProfilePic();

			System.out.println("profile pic from database : " + profilePic);

			if (!profilePic.equals("default.png")) {

				File saveFileFolder = new ClassPathResource("static/img/").getFile();

				String uploadDir = saveFileFolder.getAbsolutePath();

				File f = new File(uploadDir + File.separator + profilePic);

				System.out.println("profile pic path : " + f.getAbsolutePath());

				if (f.exists()) {
					System.out.println("file exists");
					if (f.delete()) {
						System.out.println("file deleted");

						Date d = new Date();

						originalFile = name + d.getSeconds() + "." + extension;

						FileService.saveFile(uploadDir, originalFile, file);
					} else {
						System.out.println("file is not delete");
					}
				} else {
					System.out.println("f is not exists");
				}

			}

			employeeDto.getEmployee().setProfilePic(originalFile);

		} else {
			System.out.println("file is empty");
			employeeDto.getEmployee().setProfilePic("default.png");
		}

		ArmsLicenses armsLicenses = employeeDto.getArmsLicenses();

		armsLicenses.setEmployee(employeeDto.getEmployee());

		List<ArmsLicensesDetail> armsLicensesDetail = employeeDto.getArmsLicensesDetail();

		for (ArmsLicensesDetail licensesDetail : armsLicensesDetail) {

			licensesDetail.setArmsLicenses(armsLicenses);
		}

		armsLicenses.setArmsLicensesDetail(armsLicensesDetail);

		armsLicenseService.updateArmsLicenseService(armsLicenses);
		
		redirectAttributes.addFlashAttribute("message", "Employee has been Updated Successfully  ");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");

		return "redirect:employeeMaster";
	}

	@GetMapping(value = { "/deleteEmployee/{id}/" })
	public String deleteEmployee(@PathVariable("id") String id, Model model,
			HttpSession session,RedirectAttributes redirectAttributes) {
		
		
		System.out.println("employee id " + id);
		
		try {
			
			employeeService.removeEmployeet(id);
			redirectAttributes.addFlashAttribute("message", "Record  has been deleted successfully!");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		/*
		 * try {
		 * 
		 * armsLicenseService.removeArmsLicenseService(id2);
		 * session.setAttribute("username", session.getAttribute("username"));
		 * employeeService.removeEmployeet(id);
		 * 
		 * redirectAttributes.addFlashAttribute("message",
		 * "Employee has been Deleted !!  ");
		 * redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		 * 
		 * } catch (Exception e) { e.printStackTrace();
		 * 
		 * redirectAttributes.addFlashAttribute("message", "Something went wrong !!  ");
		 * redirectAttributes.addFlashAttribute("alertClass", "alert-danger"); }
		 */
		
		return "redirect:/" + pageMappingService.PageRequestMapping(reqPage, pageno);
	}

	@GetMapping("/viewEmployeeMaster/{id}")
	public String viewEmployeeMaster(@PathVariable("id") String empCode,
			@ModelAttribute("employeeDto") EmployeeMasterDto employeeDto, Model model, HttpSession session) {

		System.out.println("view employee controller");


		List<City> cityList = cityService.getAllCities();
		model.addAttribute("CityList", cityList);

		List<State> listState = stateService.getAllStates();
		model.addAttribute("listState", listState);

		List<Country> listCountry = countryService.getAllCountrys();
		model.addAttribute("listCountry", listCountry);

		List<Designation> listDesignation = designationService.getAllDesignations();
		if (listDesignation != null) {
			model.addAttribute("listDesignation", listDesignation);
		}
		List<Department> listDepartment = departmentService.getAllDepartments();
		model.addAttribute("listDepartment", listDepartment);

		ArmsLicenses armLicenses = armsLicenseService.findArmsLicensesByEmployee(empCode);

		employeeDto.setEmployee(armLicenses.getEmployee());
		employeeDto.setArmsLicenses(armLicenses);

		employeeDto.setArmsLicensesDetail(armLicenses.getArmsLicensesDetail());

		EmployeePromotion promotion = employeePromotionService.findemployeePromotionByEmpCode(empCode);

		if (promotion != null) {
			employeeDto.setEmployeePromotion(promotion);
		}

		EmployeeAcr employeeAcr = employeeAcrService.findEmployeeAcrByEmpCode(empCode);
		if (employeeAcr != null) {
			employeeDto.setEmployeeAcr(employeeAcr);
		}

		model.addAttribute("employeeDto", employeeDto);
		return "viewEmployeeMaster";

	}

	@ResponseBody
	@GetMapping("/page/{pageNo}")
	public PaginationDto findPaginated(@PathVariable(value = "pageNo") int currentPageNo, Model model) {

		System.out.println("pagination controller");
		int pageSize = 5;

		List<Employee> page = employeeService.findPaginated(currentPageNo, pageSize);

		int totalItems = employeeService.findAllEmployee().size();
		double totalPages = Math.ceil(totalItems / pageSize);

		PaginationDto pagination = new PaginationDto();

		pagination.setCurrentPage(currentPageNo);
		pagination.setTotalItems(totalItems);
		pagination.setTotalPages(totalPages);
		pagination.setObject(page);

		return pagination;
	}

	@ResponseBody
	@GetMapping("/viewStateByCountryCode/{id}")
	public List<State> getStateCountryById(@PathVariable(value = "id") String id, Model model, HttpSession session) {
		List<State> e = stateService.findStateByCountry(id);
		List<State> lisStateUtil = new ArrayList<>();
		for (int i = 0; i < e.size(); i++) {
			State st = new State();
			st.setStateCode(e.get(i).getStateCode());
			st.setStateName(e.get(i).getStateName());
			lisStateUtil.add(st);
		}
		return lisStateUtil;

	}

	@ResponseBody
	@GetMapping("/viewCityBySatateCode/{id}")
	public List<City> viewCityBySatateCode(@PathVariable(value = "id") String stateCode, Model model,
			HttpSession session) {
		List<City> y = cityService.findCityByState(stateCode);
		List<City> lisCityUtil = new ArrayList<>();
		for (int i = 0; i < y.size(); i++) {
			City ct = new City();
			ct.setCityCode(y.get(i).getCityCode());
			ct.setCityName(y.get(i).getCityName());
			lisCityUtil.add(ct);
		}
		return lisCityUtil;
	}

}
