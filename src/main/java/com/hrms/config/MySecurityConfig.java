package com.hrms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import com.hrms.service.impl.CustomUserDetailService;

@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	CustomUserDetailService custumUserDetailService;
	
	
	 @Autowired CustomAuthenticationPasswordProvider authProvider;
	 
	 @Autowired
	    private CustomLogoutHandler logoutHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		System.out.println("role configure ");
		

		

		http.csrf().disable().authorizeRequests().antMatchers("/css/**", "/js/**", "/img/**", "/signin**", "/fonts/**","/admin","/doLogin","/forgetPassword/**","/forgotMsgPassword/**")
				.permitAll().antMatchers("/userProgramRights/**", "/deleteUserRights/**", "/saveUserRights")
				.hasAuthority("ROLE_USERPROGRAMRIGHTS")

				.antMatchers("/actMaster/**", "/saveActivity", "**/deleteActivity/**")
				.hasAuthority("ROLE_ACTIVITIESMASTER")

				.antMatchers("/applicantInformation/**", "/saveApplicantInfo").hasAuthority("ROLE_APPLICANTINFORMATION") // APPLICANTINFO

				.antMatchers("/armsreport/**", "/createarmsreport").hasAuthority("ROLE_ARMSREPORT")

				.antMatchers("/attendanceRegMothlyReport/**", "/createAttendenceMonthly").hasAuthority("ROLE_ATTENDANCEREGMOTHLYREPORT")

				.antMatchers("/attendanceRegDateReport/**", "/createAttendenceDatewise").hasAuthority("ROLE_ATTENDANCEREGDATEREPORT")

				.antMatchers("/overtimeRegMonthlyReport/**", "/createOvertimeMonthly").hasAuthority("ROLE_OVERTIMEREGMONTHLYREPORT")

				.antMatchers("/overtimeRegDayReport/**", "/createOvertimeRegDate").hasAuthority("ROLE_OVERTIMEREGDAYREPORT")

				.antMatchers("/absentismEmployeePage/**", "/createAbsentEmployeeReport").hasAuthority("ROLE_ABSENTISMEMPLOYEEPAGE") // ABSENTIEMPLOYEEWISEDETAIL

				.antMatchers("/attendanceRegister/**", "/saveAttendenceRegister").hasAuthority("ROLE_ATTENDANCEREGISTER") // ATTENDANCEREGISTERMONTHLY

				.antMatchers("/awardMaster/**", "/saveAward", "**/updateAward/**").hasAuthority("ROLE_AWARDMASTER") // AWARDMASTER

				.antMatchers("/bankMaster/**", "/saveBank", "**/updateBank/**").hasAuthority("ROLE_BANKMASTER")

				.antMatchers("/budgetProvision/**", "/saveBudgetProvision", "**/updateBudgetProvision/**")
				.hasAuthority("ROLE_BUDGETPROVISIONPAGE")

				.antMatchers("/budgetReport/**", "/createbudgetreport").hasAuthority("ROLE_BUDGETREPORT")

				.antMatchers("/categoryMaster/**", "/saveCategory", "**/updateCategory/**")
				.hasAuthority("ROLE_CATEGORYMASTER") // CATEGORY MASTER

				.antMatchers("/cityMaster/**", "/saveCity", "**/updateCity/**").hasAuthority("ROLE_CITYMASTER") // CITYMASTER

				.antMatchers("/conveyanceApproval/**", "/approvedLocalConv/**")
				.hasAuthority("ROLE_CONVEYANCEAPPROVAL") /// LOCALCONVAYENCEAPPROVAL

				.antMatchers("/copyMasterSheet/**").hasAuthority("ROLE_")

				.antMatchers("/countryMaster/**", "/saveCountry", "**/updateCountry").hasAuthority("ROLE_COUNTRYMASTER")

				.antMatchers("/departmentMaster/**", "/saveDepartment", "**/updateDepartment")
				.hasAuthority("ROLE_DEPARTMENTMASTER") // DEPARTMENTMASTER

				.antMatchers("/designationMaster/**", "/saveDesignation", "**/updateDesignation")
				.hasAuthority("ROLE_DESIGNATIONMASTER") // ROLE_DESIGNATIONMASTER

				.antMatchers("/employeeAcr/**", "/saveEmployeeAcr", "**/updateEmployeeAcr").hasAuthority("ROLE_EMPLOYEEACR")

				.antMatchers("/employeeMaster/**", "/saveEmployee", "**/updateEmployeemaster")
				.hasAuthority("ROLE_EMPLOYEEMASTER") // EMPLOYEEMASTER

				.antMatchers("/employeeRequisitionApproval/**", "/approveRequisition/**")
				.hasAuthority("ROLE_EMPLOYEEREQUISITIONAPPROVAL") // EMPLOYEEREQUISITIONAPPROVAL

				.antMatchers("/employeeRequisition/**", "/saveEmployeeRequisition", "**/updateRequisition/**")
				.hasAuthority("ROLE_EMPLOYEEREQUISITION") // EMPLOYEEREQUISITION

				.antMatchers("/employeeTransfer/**", "/saveEmployeeTransfer").hasAuthority("ROLE_EMPLOYEETRANSFER")

				.antMatchers("/employeeunderrule/**", "/saveemployeeunderRule", "**/updateemployeeunderrule/**")
				.hasAuthority("ROLE_EMPLOYEEUNDERRULE")

				.antMatchers("/gradeMaster/**", "/saveGrade", "**/updateGrade/**").hasAuthority("ROLE_GRADEMASTER")

				.antMatchers("/HolidayMaster/**", "/saveHolidays", "**/updateHoliday/**")
				.hasAuthority("ROLE_HOLIDAYMASTER") // HOLIDAYMASTER

				.antMatchers("/inductionTraining/**", "/saveInductionTraining", "**/updateInductionTraining/**")
				.hasAuthority("ROLE_INDUCTIONTRAINING") // INDUCTIONTRAINING

				.antMatchers("/insuranceMaster/**", "/saveInsurance/**", "**/updateInsurance/**")
				.hasAuthority("ROLE_INSURANCEMASTER") // INSURANCEMASTER

				.antMatchers("/interviewDetails/**", "/addInterviewDetail").hasAuthority("ROLE_INTERVIEWDETAILS") // INTERVIEWDETAIL

				.antMatchers("/interviewApproval/**", "/approveInterview/**").hasAuthority("ROLE_INTERVIEWAPPROVAL") // INTERVIEWAPPROVAL

				.antMatchers("/interviewFinalSelection/**", "/interviewFileApproval/**")
				.hasAuthority("ROLE_INTERVIEWFINALSELECTION") // INTERVIEWFINALSELECTION

				.antMatchers("/leaveApproval/**", "/approveLeaveRequest/**").hasAuthority("ROLE_LEAVEAPPROVAL") // LEAVEAPPROVAL

				.antMatchers("/leaveDetailMaster/**", "/saveLeaveDetail", "**/updateLeaveDetail")
				.hasAuthority("ROLE_LEAVEDETAILMASTER")

				.antMatchers("/leaveGrant/**", "/saveLeaveGrant", "**/updateLeaveGrant").hasAuthority("ROLE_LEAVEGRANT")

				.antMatchers("/leaveMaster/**", "/saveLeave/**", "**/updateLeave").hasAuthority("ROLE_LEAVEMASTER")

				.antMatchers("/leaveRequest/**", "/saveLeaveRequest").hasAuthority("ROLE_LEAVEREQUEST") /// LEAVEREQUEST

				.antMatchers("/leaveRegister/**", "/createLeaveRegisterReport").hasAuthority("ROLE_LEAVEREGISTER") // LEAVEREGISTER

				.antMatchers("/leaveRequestReport/**", "/createleaveRequestReport").hasAuthority("ROLE_LEAVEREQUESTREPORT")

				.antMatchers("/leaveTransactionReport/**", "/createleaveTransactionReport").hasAuthority("ROLE_LEAVETRANSACTIONREPORT")

				.antMatchers("/loanApproval/**", "/approvedLoanRequest/**").hasAuthority("ROLE_LOANAPPROVAL")

				.antMatchers("/loanMaster/**", "/saveLoan", "**/updateLoan/**").hasAuthority("ROLE_LOANMASTER") // LOANMASTER

				.antMatchers("/loanRequest/**", "/saveLoanRequest/**", "**/updateLoanRequest/**")
				.hasAuthority("ROLE_LOANREQUEST") // LOANREQUEST

				.antMatchers("/loanTracking/**", "/saveLoanTracking").hasAuthority("ROLE_LOANTRACKING")

				.antMatchers("/localConvyence/**", "/saveLocalConvyence/**", "**/updateLocalConvyence/**")
				.hasAuthority("ROLE_LOCALCONVYENCE") // LOCALCONVAYENCE

				.antMatchers("/ltaApproval/**", "/approvedLtaRequest/**").hasAuthority("ROLE_LTAAPPROVAL") // LTAAPPROVAL

				.antMatchers("/ltaRequest/**", "/saveLtaRequest/**", "**/updateLtaRequest/**")
				.hasAuthority("ROLE_LTAREQUEST") // LTAREQUEST

				.antMatchers("/medicalReimbursement/**", "/saveMedicalReimbursement", "**/updateMedicalReimbursement")
				.hasAuthority("ROLE_MEDICALREIMBURSEMENT") // MEDICALREIMBURSEMENT

				.antMatchers("/mediclaimApproval/**", "/approveMedicalReimbursement/**")
				.hasAuthority("ROLE_MEDICLAIMAPPROVAL") // MEDICLAIMAPPROVE

				.antMatchers("/miscAllowances/**", "/saveAllowncesDeduction/**", "**/updateAllowanceDeduction/**")
				.hasAuthority("ROLE_MISCALLOWANCES")

				.antMatchers("/module/**", "/saveModule", "**/updateModule/**").hasAuthority("ROLE_MODULE") // MODULE

				.antMatchers("/monthlyOvertimeEvaluation/**", "/saveOvertimeEvaluation").hasAuthority("ROLE_MONTHLYOVERTIMEEVALUATION")

				.antMatchers("/orderissuetracking/**", "/saveOrderIssueTracking", "**/updateOrderTracking/**")
				.hasAuthority("ROLE_ORDERISSUETRACKING")

				.antMatchers("/ordertrackreport/**", "/createordertrackreport").hasAuthority("ROLE_ORDERTRACKREPORT")

				.antMatchers("/overtimeRegister/**").hasAuthority("ROLE_OVERTIMEREGISTER") // OVERTIMEREGISTERDATEWISE

				.antMatchers("/program/**", "/saveProgram", "**/updateProgram/**").hasAuthority("ROLE_PROGRAM") // PROGRAMMASTER

				.antMatchers("/registerMaster/**", "/saveRegister/**", "**/updateRegister/**")
				.hasAuthority("ROLE_REGISTERMASTER") // REGISTERMASTER

				.antMatchers("/tourclaimPage/**", "/generateTourClaim").hasAuthority("ROLE_TOURCLAIMPAGE")

				.antMatchers("/localclaimPage/**", "/localClaimReport/**").hasAuthority("ROLE_LOCALCLAIMPAGE")

				.antMatchers("/ltaReport/**", "/createLtaReport/**").hasAuthority("ROLE_LTAREPORT")

				.antMatchers("/advertisment/**", "/saveAdvertisement/**", "**/updateAdvertisement/**")
				.hasAuthority("ROLE_ADVERTISMENT") // REQUISITIONADVERTISEMENT

				.antMatchers("/sectionMaster/**", "/saveSection", "**/updateSection").hasAuthority("ROLE_SECTIONMASTER") // SECTIONMASTER

				.antMatchers("/shiftMaster/**", "/saveShift/**", "**/updateShift/**").hasAuthority("ROLE_SHIFTMASTER") // SHIFT
																														// MASTER

				.antMatchers("/skillCategoryMaster/**", "/saveSkillCategory/**", "**/updateSkillCategory/**")
				.hasAuthority("ROLE_SKILLCATEGORYMASTER") // SKILLCATEGORYMASTER

				.antMatchers("/staffPostingDuties/**", "/savestaffDuties", "**/updateStaffDuties/**")
				.hasAuthority("ROLE_STAFFPOSTINGDUTIES")

				.antMatchers("/stateMaster/**", "/saveState", "**/updateState/**").hasAuthority("ROLE_STATEMASTER") // STATE
																													// MASTER

				.antMatchers("/subModule/**", "/saveSubModule", "**/updateSubModule/**").hasAuthority("ROLE_SUBMODULE") /// SUBMODULE

				.antMatchers("/tourClaim/**", "/saveTourClaim").hasAuthority("ROLE_TOURCLAIM") // TOURCLAIM

				.antMatchers("/tourPlanApproval/**", "**/approvedTourPlan/**").hasAuthority("ROLE_TOURPLANAPPROVAL") // TOURPLANAPPROVAL

				.antMatchers("/tourClaimApproval/**").hasAuthority("ROLE_TOURCLAIMAPPROVAL")// tourClaimApproval

				.antMatchers("/tourPlan/**", "/saveTourPlan", "**/updateTourPlan/**").hasAuthority("ROLE_TOURPLAN") // TOURPLAN

				.antMatchers("/trackallEnquiries/**", "/saveTrackallEnquiries/**", "**/updateTrackallEnquiries/**")
				.hasAuthority("ROLE_TRACKALLENQUIRIES")

				.antMatchers("/trainingRegister/**", "/saveTrainingRegister", "**/updateTrainingRegister/**")
				.hasAuthority("ROLE_TRAININGREGISTER") // TRAININGREGISTER

				.antMatchers("/trainingRequisitionApproval/**", "/approveTrainingRequisition/**")
				.hasAuthority("ROLE_TRAININGREQUISITIONAPPROVAL")

				.antMatchers("/trainingRequisition/**", "/saveTrainingRequisition", "**/updateTrainingRequisition/**")
				.hasAuthority("ROLE_TRAININGREQUISITION") // TRAININGREQUISITION

				.antMatchers("/trainingSchedule/**", "/saveTrainerSchedule/**", "**/updateTrainerSchedule/**")
				.hasAuthority("ROLE_TRAININGSCHEDULE") // TRAININGSCHEDULE

				.antMatchers("/travelDetails/**", "/saveTravel", "**/updateTravel")
				.hasAuthority("ROLE_TRAVELDETAILS")

				.antMatchers("/userMaster/**", "/saveUser/**", "**/upadteUser/**").hasAuthority("ROLE_USERMASTER") // USERMASTER
				.antMatchers("**/aboutDept/**","**/saveAboutdept/**" ,"**/updateAboutDept/**").hasAuthority("ROLE_ABOUTDEPT")
				.antMatchers("**/slider/**","**/saveSlider/**" ,"**/updateSlider/**").hasAnyAuthority("ROLE_SLIDER")
				.antMatchers("**/rule/**","**/saveRules/**" ,"**/updateRules/**").hasAuthority("ROLE_RULE")
				.antMatchers("**/policy/**","**/savePolicy/**" ,"**/updatePolicy/**").hasAuthority("ROLE_POLICY")
				.antMatchers("**/order/**","**/saveOrder/**" ,"**/updateOrder/**").hasAuthority("ROLE_ORDER")
				.antMatchers("**/notification/**","**/saveNotification/**" ,"**/updateNotification/**").hasAuthority("ROLE_NOTIFICATION")
				.antMatchers("**/latestUpdate/**","**/saveLatestUpdate/**" ,"**/updateLatestUpdate/**").hasAuthority("LATESTUPDATE")
				.antMatchers("**/acts/**","**/saveActs/**" ,"**/updateActs/**").hasAuthority("ROLE_ACTS")
				.antMatchers("**/contactUs/**","**/saveContactUs/**" ,"**/updateContactUs/**").hasAuthority("ROLE_CONTACTUS")
				.antMatchers("**/commission/**","**/saveCommission/**" ,"**/updateCommission/**").hasAuthority("ROLE_COMMISSION")
				.antMatchers("**/information/**","**/saveInformation/**" ,"**/updateInformation/**").hasAuthority("ROLE_INFO")
				.antMatchers("**/eservices/**","**/saveEservices/**" ,"**/updateEservices/**").hasAuthority("ROLE_ESERVICES")
				.antMatchers("**/imageGallery/**","**/saveImageGallery/**" ,"**/updateImageGallery/**").hasAuthority("ROLE_IMAGEGALLERY")
				.antMatchers("**/pathDirectory/**","**/savePathDirectory/**" ,"**/updatePathDirectory/**").hasAuthority("ROLE_PATHDIRECTORY")
																													
				.anyRequest().authenticated().and()
				
				.formLogin().loginPage("/signin").loginProcessingUrl("/doLogin")
				.defaultSuccessUrl("/home",true).failureUrl("/signin?error")
				.and()
			    .headers()
		        .frameOptions().disable()
		        .and()

	            .logout()
	            .logoutUrl("/logout")
	            .addLogoutHandler(logoutHandler)
	            .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK))
	            .permitAll()
				; // logout configuration

		
//		logout handler code 
//		.and()
//        .logout()
//            .logoutSuccessHandler(logoutSuccessHandler)
//            .permitAll()
		
//		 http.sessionManagement()
//        
//         .maximumSessions(1).maxSessionsPreventsLogin(true)
//         .expiredUrl("/signin?invalid-session=true");
		 
		 http.sessionManagement()
         .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
         .sessionFixation()
         .migrateSession()
         .maximumSessions(1).sessionRegistry(sessionRegistry()).expiredUrl( "/login?expire")
         .maxSessionsPreventsLogin(true);

		
		
//		http.sessionManagement().maximumSessions(1);
		
				http.exceptionHandling().accessDeniedPage("/UnauthorizePage")
				; // UnauthorizePage.html //logout
			
//				http.addFilterBefore(new CustomSecurityFilter(), 
//					      SecurityContextPersistenceFilter.class);
	}

	

	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		
		
			System.out.println("authentication manager builder ");
			//auth.userDetailsService(custumUserDetailService).passwordEncoder(passwordEncoder());
		
			auth.authenticationProvider(authProvider);
		
	}

	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public SessionRegistry sessionRegistry() {
	    SessionRegistry sessionRegistry = new SessionRegistryImpl();
	    return sessionRegistry;
	}

	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
	    return new HttpSessionEventPublisher();
	}
	
	@Autowired UserDetailsService userDetailsService;
	  @Bean
	  public AuthenticationProvider daoAuthenticationProvider() {
	      DaoAuthenticationProvider impl = new DaoAuthenticationProvider();
	      impl.setUserDetailsService(userDetailsService());
	      impl.setHideUserNotFoundExceptions(false) ;
	      return impl ;
	  }
	
	
	
}
