/*  ajax or js for EMP_PAY_DETAIL   */

var epfDropdown = '';
var esiDropdown = '';

function display(){ 

    var d = document.getElementById("employeeDropdown");
    var displayItem = d.options[d.selectedIndex].text;
    console.log(displayItem);

    ajaxRequest(displayItem);
}

function ajaxRequest(val){
	var empCode = val;
    console.log("ajax request : "+ empCode);
    const xhr = new XMLHttpRequest();
    xhr.open('POST','/empPayDetail/'+empCode,true);
    xhr.getResponseHeader('content-type','application/json');
    xhr.onload = function(){
        if(this.status===200){
            let obj = JSON.parse(this.responseText);
            console.log(obj);

            $('#emp_name').val(obj.empName);
			$('#emp_dept_code').val(obj.departmentCode);
			$('#emp_desig_name').val(obj.empDesignation);
			$('#wef_date').val(obj.dateOfJoining);
			var deptCode = document.getElementById("emp_dept_code").value;
			ajaxRequestForDepartment(deptCode);
        }
        else{
            console.error("some error occured");
        }
	
    }
    xhr.send();
}

function ajaxRequestForDepartment(val){
	var deptCode = val;
    console.log("ajax request : "+ deptCode);
    var xhr1 = new XMLHttpRequest();
    xhr1.open('POST','/getdepartment/'+deptCode,true);
    xhr1.getResponseHeader('content-type','application/json');
	xhr1.response = 'json';
    xhr1.onload = function(){
        if(this.status===200){
		
			console.log("successfull");
			//console.log(" result : "+this.responseText);
             let obj1 = JSON.parse(this.responseText);
            console.log(obj1);
			$('#emp_dept_name').val(obj1.deptName);
			//$('#emp_desig_name').val(obj.empDesignation);

        }
        else{
            console.error("some error occured");
        }
    }
	xhr1.responseType="text";
    xhr1.send();
}

// employee pay details calculations

function basicPayCal(value){
	
	if(value==""){
		document.getElementById("basic_pay_m").value=0;
		document.getElementById("basic_pay_y").value=0;
	}
    var x = value * 12;
    document.getElementById("basic_pay_y").value=x;
	
	//calculatePercentage();
	calculateEsiAndEpf(value)
	calculateGrossSalary();
	calculateCtc();
    
}

function basicAditionalCal(value){
	
	if(value==""){
		document.getElementById("addl_pay_m").value=0;
		document.getElementById("addl_pay_y").value=0;
	}
    var x = value * 12;
    document.getElementById("addl_pay_y").value=x;

	calculateGrossSalary();
	calculateCtc();
	
}

function fdaPercentMonthly(value){
	
	if(value==""){
		document.getElementById("fda_m_percent").value=0;
		document.getElementById("fda_y_percent").value=0;
		document.getElementById("fda_m_percent_result").value=0;
		document.getElementById("fda_y_percent_result").value=0;
	}
	var basicSalary = document.getElementById("basic_pay_m").value;
    var m = (basicSalary * value)/100;
	var y = (basicSalary*12*value)/100;
    document.getElementById("fda_m_percent_result").value=m;
	document.getElementById("fda_y_percent_result").value=y;
	
	calculateGrossSalary();
	calculateCtc();
	
}

function hraPercent(value){
	//var x = document.getElementById("hra_m_percent").value;
	var y = document.getElementById("basic_pay_m").value;
	
	if(value==""){
		document.getElementById("hra_m_percent").value=0;
		document.getElementById("hra_m_percent").value=0;
		document.getElementById("hra_m_percent_result").value=0;
		document.getElementById("hra_y_percent_result").value=0;
	}
	
	var result = (y*value)/100;
	var yearlyResult = (y*12*value)/100;
	document.getElementById("hra_m_percent_result").value=result;
	document.getElementById("hra_y_percent_result").value = yearlyResult;
	
	calculateGrossSalary();
	calculateCtc();
	
}

function medicalAllowanceCal(value){
	document.getElementById("medical_allow_y").value=value*12;
	totalAllowance();
	
	calculateGrossSalary();
	calculateCtc();
}

function çonveyanceCal(value){
	document.getElementById("conveyance_y").value=value*12;
	totalAllowance();
	
	calculateGrossSalary();
	calculateCtc();
}

function bonusCal(value){
	document.getElementById("bonus_y").value=value*12;
	
	calculateGrossSalary();
	calculateCtc();
}

function totalAllowance(){
	
	//var basicPay = document.getElementById("basic_pay_m").value;
	//var additionalM = document.getElementById("addl_pay_m").value;
	//var fdaM = document.getElementById("fda_m_percent_result").value;
	//var hraValueM = document.getElementById("hra_m_percent_result").value;
	var medicalAllowanceM = document.getElementById("medical_allow_m").value;
	var conveyanceAllowanceM = document.getElementById("conveyance_m").value;
	//var bonusM = document.getElementById("hra_m_percent_result").value;
	
	
	var totalAllowance = parseInt(medicalAllowanceM)+parseInt(conveyanceAllowanceM);

	document.getElementById("total_allowances_m").value=totalAllowance;
	document.getElementById("total_allowances_y").value=totalAllowance*12;
}



function calculateVpf(value){
	document.getElementById("deduction_vpf_y").value= value*12;
	totalDeduction();
	calculateCtc();
}

function calculateLabour(value){
	document.getElementById("deduction_labour_y").value= value*12;
	totalDeduction();
	calculateCtc();
}

function calculateInsurancePremium(value){
	document.getElementById("deduction_insurance_y").value= value*12;
	totalDeduction();
	calculateCtc();
}
function calculateSi(value){
	document.getElementById("deduction_si_y").value= value*12;
	totalDeduction();
	calculateCtc();
}

function totalDeduction(){
	
	console.log("total deduction");
	
	    var esiM = document.getElementById("deduction_esi_m").value ;
		var esiY = document.getElementById("deduction_esi_y").value;
		
		var siM = document.getElementById("deduction_si_m").value;
		var siY = document.getElementById("deduction_si_y").value;
		
		var fpfM = document.getElementById("deduction_fpf_m").value;
		var pfM =document.getElementById("deduction_pf_m").value;
		var epfM = document.getElementById("deduction_epf_m").value;
		
		var fpfY = document.getElementById("deduction_fpf_y").value;
		var pfY = document.getElementById("deduction_pf_y").value;
		var epfY = document.getElementById("deduction_epf_y").value;
		
		var vpfM = document.getElementById("deduction_vpf_m").value;
		var vpfY = document.getElementById("deduction_vpf_y").value;
		
		var labourM = document.getElementById("deduction_labour_m").value;
		var labourY = document.getElementById("deduction_labour_y").value
		
		var insM = document.getElementById("deduction_insurance_m").value;
		var insY = document.getElementById("deduction_insurance_y").value;
		
		var tds = document.getElementById("deduction_tds").value;
		var sarcharge = document.getElementById("deduction_surcharge").value;
		var edu = document.getElementById("deduction_edu").value;
		
		
		var totalMonthlyDeduction = parseInt(esiM)+parseInt(siM)+parseInt(fpfM)+parseInt(pfM)
										+parseInt(epfM)+parseInt(vpfM)+
									parseInt(labourM)+parseInt(insM)+parseInt(tds)+parseInt(sarcharge)+parseInt(edu);
		
		var totalYearlyDeduction = parseInt(esiY)+parseInt(siY)+parseInt(fpfY)+parseInt(pfY)
										+parseInt(epfY)+parseInt(vpfY)+
									parseInt(labourY)+parseInt(insY);
		console.log("month deduction"+totalMonthlyDeduction);
		console.log("yealry deduction"+totalYearlyDeduction);
		
		document.getElementById("deduction_total_m").value = totalMonthlyDeduction;
		document.getElementById("deduction_total_y").value = totalYearlyDeduction;
		
	
}


function calculateGrossSalary(){
	var basicSalaryM = document.getElementById("basic_pay_m").value;
	var medicalAllowanceM = document.getElementById("medical_allow_m").value;
	var conveyanceAllowanceM = document.getElementById("conveyance_m").value;
	var bonusM = document.getElementById("bonus_m").value;
	var hraM = document.getElementById("hra_m_percent_result").value;
 	var fdaM = document.getElementById("fda_m_percent_result").value;
	var addtionM = document.getElementById("addl_pay_m").value;
	var esiM = document.getElementById("deduction_esi_m").value ;
	var pfM =document.getElementById("deduction_pf_m").value;
	
	//var fpfM = document.getElementById("deduction_fpf_m").value ;
	//var epfM = document.getElementById("deduction_epf_m").value ;
	//var pfM =document.getElementById("deduction_pf_m").value;
	
	
	
	var monthGross = parseInt(basicSalaryM)+parseInt(medicalAllowanceM)+parseInt(conveyanceAllowanceM)+parseInt(esiM)
						+parseInt(bonusM)+parseInt(hraM)+parseInt(fdaM)+parseInt(addtionM)+parseInt(pfM);
	
	document.getElementById("grossM").value = monthGross;
	document.getElementById("grossY").value = monthGross*12;
}

function calculatePercentage(){
	
	var basicSalaryM = document.getElementById("basic_pay_m").value;
	console.log("basic salary is :"+basicSalaryM);
	var basicSalaryY = document.getElementById("basic_pay_y").value;
	
	if(basicSalaryM<15000){
		
		let fpfPercentM = parseFloat(basicSalaryM * 3.67/100);
		let pfPercentM = parseFloat(basicSalaryM * 8.21/100);
		let epfPercentM = parseFloat(basicSalaryM * 12/100);
		
		var esi = parseFloat(basicSalaryM * 5/100);
		var si = parseFloat(basicSalaryM*5/100);
		
		document.getElementById("deduction_esi_m").value = esi;
		document.getElementById("deduction_esi_y").value = esi*12;
		
		document.getElementById("deduction_si_m").value = si;
		document.getElementById("deduction_si_y").value = si*12;
		
		document.getElementById("deduction_fpf_percent").value = 3.67;
		document.getElementById("deduction_pf_percent").value = 8.21;
		document.getElementById("deduction_epf_percent").value = 12.0;
		
		document.getElementById("deduction_fpf_m").value = fpfPercentM;
		document.getElementById("deduction_pf_m").value = pfPercentM;
		document.getElementById("deduction_epf_m").value = epfPercentM;
		
		document.getElementById("deduction_fpf_y").value = fpfPercentM*12;
		document.getElementById("deduction_pf_y").value = pfPercentM*12;
		document.getElementById("deduction_epf_y").value = epfPercentM*12;
		
		console.log(" done ");
		
		totalDeduction();
		calculateCtc();
	}
	else if(basicSalaryM==15000){
		
		let fpfPercentM = parseFloat(basicSalaryM * 5.67/100);
		let pfPercentM = parseFloat(basicSalaryM * 9.21/100);
		let epfPercentM = parseFloat(basicSalaryM * 12.40/100);
		
		var esi = parseFloat(basicSalaryM * 6/100);
		var si = parseFloat(basicSalaryM*6/100);
		
		document.getElementById("deduction_esi_m").value = esi;
		document.getElementById("deduction_esi_y").value = esi*12;
		
		document.getElementById("deduction_si_m").value = si;
		document.getElementById("deduction_si_y").value = si*12;
		
		document.getElementById("deduction_fpf_percent").value = 5.67;
		document.getElementById("deduction_pf_percent").value = 9.21;
		document.getElementById("deduction_epf_percent").value = 12.40;
		
		document.getElementById("deduction_fpf_m").value = fpfPercentM;
		document.getElementById("deduction_pf_m").value = pfPercentM;
		document.getElementById("deduction_epf_m").value = epfPercentM;
		
		document.getElementById("deduction_fpf_y").value = fpfPercentM*12;
		document.getElementById("deduction_pf_y").value = pfPercentM*12;
		document.getElementById("deduction_epf_y").value = epfPercentM*12;
		
		totalDeduction();
		calculateCtc();
	}
	else{
		
		let fpfPercentM = parseFloat(basicSalaryM * 7.67/100);
		let pfPercentM = parseFloat(basicSalaryM * 10.21/100);
		let epfPercentM = parseFloat(basicSalaryM * 13.40/100);
		
		var esi = parseFloat(basicSalaryM * 7/100);
		var si = parseFloat(basicSalaryM*7/100);
		
		document.getElementById("deduction_esi_m").value = esi;
		document.getElementById("deduction_esi_y").value = esi*12;
		
		document.getElementById("deduction_si_m").value = si;
		document.getElementById("deduction_si_y").value = si*12;
		
		document.getElementById("deduction_fpf_percent").value = 7.67;
		document.getElementById("deduction_pf_percent").value = 10.21;
		document.getElementById("deduction_epf_percent").value = 13.40;
		
		document.getElementById("deduction_fpf_m").value = fpfPercentM;
		document.getElementById("deduction_pf_m").value = pfPercentM;
		document.getElementById("deduction_epf_m").value = epfPercentM;
		
		document.getElementById("deduction_fpf_y").value = fpfPercentM*12;
		document.getElementById("deduction_pf_y").value = pfPercentM*12;
		document.getElementById("deduction_epf_y").value = epfPercentM*12;
		
		totalDeduction();
		calculateCtc();
	}
	
}

function calculateEsiAndEpf(){

	var basic = document.getElementById("basic_pay_m").value;
	if (document.getElementById("r1").checked){
		/*var fpfPer = document.getElementById("deduction_fpf_percent").value ;
		var pfPer = document.getElementById("deduction_pf_percent").value ;
		var epfPer = document.getElementById("deduction_epf_percent").value ;
		
		var fpM = basic*fpfPer/100;
		var pfM = basic*pfPer/100;
		var epM = basic*epfPer/100; */
		
		document.getElementById("deduction_fpf_m").value = 0;
		document.getElementById("deduction_pf_m").value = 0;
		document.getElementById("deduction_epf_m").value = 0;
		document.getElementById("deduction_fpf_y").value = 0;
		document.getElementById("deduction_pf_y").value = 0;
		document.getElementById("deduction_epf_y").value = 0;
		totalDeduction();
		calculateGrossSalary();
		calculateCtc();
	}
	if (document.getElementById("r2").checked){
		var fpfPer = document.getElementById("deduction_fpf_percent").value ;
		var pfPer = document.getElementById("deduction_pf_percent").value ;
		var epfPer = document.getElementById("deduction_epf_percent").value ;
		
		var fpM = basic*fpfPer/100;
		var pfM = basic*pfPer/100;
		var epM = basic*epfPer/100;
		
		document.getElementById("deduction_fpf_m").value = fpM;
		document.getElementById("deduction_pf_m").value = pfM;
		
		if( epfDropdown=='yes'){
			document.getElementById("deduction_epf_m").value = epM;
			document.getElementById("deduction_epf_y").value = epM*12;
			calculateGrossSalary();
		}else{
			document.getElementById("deduction_epf_m").value = 0;
			document.getElementById("deduction_epf_y").value = 0*12;
			calculateGrossSalary();
		}
		
		
		document.getElementById("deduction_fpf_y").value = fpM*12;
		document.getElementById("deduction_pf_y").value = pfM*12;
		totalDeduction();
		calculateCtc();
	}
	if (document.getElementById("r3").checked){
		var fpfPer = document.getElementById("deduction_fpf_percent").value ;
		var pfPer = document.getElementById("deduction_pf_percent").value ;
		var epfPer = document.getElementById("deduction_epf_percent").value ;
		
		var fpM = basic*fpfPer/100;
		var pfM = basic*pfPer/100;
		var epM = basic*epfPer/100;
		
		document.getElementById("deduction_fpf_m").value = fpM;
		document.getElementById("deduction_pf_m").value = pfM;
		if( epfDropdown=='yes'){
			document.getElementById("deduction_epf_m").value = epM;
			document.getElementById("deduction_epf_y").value = epM*12;
			calculateGrossSalary();
		}else{
			document.getElementById("deduction_epf_m").value = 0;
			document.getElementById("deduction_epf_y").value = 0*12;
			calculateGrossSalary();
		}
		
		document.getElementById("deduction_fpf_y").value = fpM*12;
		document.getElementById("deduction_pf_y").value = pfM*12;
		totalDeduction();
		calculateCtc();
	}
	if (document.getElementById("r4").checked){
		var fpfPer = document.getElementById("deduction_fpf_percent").value ;
		var pfPer = document.getElementById("deduction_pf_percent").value ;
		var epfPer = document.getElementById("deduction_epf_percent").value ;
		
		var fpM = basic*fpfPer/100;
		var pfM = basic*pfPer/100;
		var epM = basic*epfPer/100;
		
		document.getElementById("deduction_fpf_m").value = fpM;
		document.getElementById("deduction_pf_m").value = pfM;
		if( epfDropdown=='yes'){
			document.getElementById("deduction_epf_m").value = epM;
			document.getElementById("deduction_epf_y").value = epM*12;
			calculateGrossSalary();
		}else{
			document.getElementById("deduction_epf_m").value = 0;
			document.getElementById("deduction_epf_y").value = 0*12;
			calculateGrossSalary();
		}
		
		document.getElementById("deduction_fpf_y").value = fpM*12;
		document.getElementById("deduction_pf_y").value = pfM*12;
		totalDeduction();
		calculateCtc();
	}
	
}

function calculateEsiAndEpfByFpf(value){

	var basic = document.getElementById("basic_pay_m").value;
	
	if (document.getElementById("r1").checked){	
		document.getElementById("deduction_fpf_m").value = 0;
		document.getElementById("deduction_fpf_y").value = 0;
		totalDeduction();
		calculateGrossSalary();
		calculateCtc();
	}
	if (document.getElementById("r2").checked){
		
		var fpM = basic*value/100;
		document.getElementById("deduction_fpf_m").value = fpM;
		document.getElementById("deduction_fpf_y").value = fpM*12;
		totalDeduction();
		calculateGrossSalary();
		calculateCtc();
	}
	if (document.getElementById("r3").checked){
		var fpM = basic*value/100;
		document.getElementById("deduction_fpf_m").value = fpM;
		document.getElementById("deduction_fpf_y").value = fpM*12;
		totalDeduction();
		calculateGrossSalary();
		calculateCtc();
	}
	if (document.getElementById("r4").checked){
		var fpM = basic*value/100;
		document.getElementById("deduction_fpf_m").value = fpM;
		document.getElementById("deduction_fpf_y").value = fpM*12;
		totalDeduction();
		calculateGrossSalary();
		calculateCtc();
	}	
}

function calculateEsiAndEpfByPf(value){

	var basic = document.getElementById("basic_pay_m").value;
	
	if (document.getElementById("r1").checked){	
		document.getElementById("deduction_pf_m").value = 0;
		document.getElementById("deduction_pf_y").value = 0;
		totalDeduction();
		calculateGrossSalary();
		calculateCtc();
	}
	if (document.getElementById("r2").checked){
		
		var fpM = basic*value/100;
		document.getElementById("deduction_pf_m").value = fpM;
		document.getElementById("deduction_pf_y").value = fpM*12;
		totalDeduction();
		calculateGrossSalary();
		calculateCtc();
	}
	if (document.getElementById("r3").checked){
		var fpM = basic*value/100;
		document.getElementById("deduction_pf_m").value = fpM;
		document.getElementById("deduction_pf_y").value = fpM*12;
		totalDeduction();
		calculateGrossSalary();
		calculateCtc();
	}
	if (document.getElementById("r4").checked){
		var fpM = basic*value/100;
		document.getElementById("deduction_pf_m").value = fpM;
		document.getElementById("deduction_pf_y").value = fpM*12;
		totalDeduction();
		calculateGrossSalary();
		calculateCtc();
	}	
}

function calculateEsiAndEpfByEpf(value){

	var basic = document.getElementById("basic_pay_m").value;
	
	if (document.getElementById("r1").checked){	
		document.getElementById("deduction_epf_m").value = 0;
		document.getElementById("deduction_epf_y").value = 0;
		totalDeduction();
		calculateGrossSalary();
		calculateCtc();
	}
	if (document.getElementById("r2").checked){
		if( epfDropdown=='yes'){
			console.log( 'épf value '+epfDropdown);
			console.log( " yes");
			var fpM = basic*value/100;
		document.getElementById("deduction_epf_m").value = fpM;
		document.getElementById("deduction_epf_y").value = fpM*12;
		totalDeduction();
		calculateGrossSalary();
		calculateCtc();
		}
		else{
			console.log( 'épf value '+epfDropdown.value);
			console.log( " no");
			document.getElementById("deduction_epf_m").value = 0;
			document.getElementById("deduction_epf_y").value = 0*12;
			totalDeduction();
			calculateGrossSalary();
			calculateCtc();
		}
	}
	if (document.getElementById("r3").checked){
		if( epfDropdown=='yes'){
			var fpM = basic*value/100;
		document.getElementById("deduction_epf_m").value = fpM;
		document.getElementById("deduction_epf_y").value = fpM*12;
		totalDeduction();
		calculateGrossSalary();
		calculateCtc();
		}
		else{
			document.getElementById("deduction_epf_m").value = 0;
			document.getElementById("deduction_epf_y").value = 0*12;
			totalDeduction();
			calculateGrossSalary();
			calculateCtc();
		}
	}
	if (document.getElementById("r4").checked){
		if( epfDropdown=='yes'){
			var fpM = basic*value/100;
		document.getElementById("deduction_epf_m").value = fpM;
		document.getElementById("deduction_epf_y").value = fpM*12;
		totalDeduction();
		calculateGrossSalary();
		calculateCtc();
		}
		else{
			document.getElementById("deduction_epf_m").value = 0;
			document.getElementById("deduction_epf_y").value = 0*12;
			totalDeduction();
			calculateGrossSalary();
			calculateCtc();
		}
	}	
}

function calculateEsi(){
		var esiM = document.getElementById("deduction_esi_m").value;
		var esiY = document.getElementById("deduction_esi_y").value;
		
		 var d = document.getElementById("esiDropdown");
	var basicM = document.getElementById('basic_pay_m').value;
	
	if(d.value=='y'){
		console.log("yes");
		esiDropdown = 'yes';
		 var r = basicM*4/100;
		document.getElementById("deduction_esi_m").value = r;
		document.getElementById("deduction_esi_y").value = r*12;
		totalDeduction();
		calculateGrossSalary();
		calculateCtc();
	}
	else{
		console.log("no");
		esiDropdown = 'no';
		document.getElementById("deduction_esi_m").value = 0;
		document.getElementById("deduction_esi_y").value = 0*12;
		//calculateEsiAndEpf();
		totalDeduction();
		calculateGrossSalary();
		calculateCtc();
	}
    
		
}

function calculateEpf(){
		var esiM = document.getElementById("deduction_esi_m").value;
		var esiY = document.getElementById("deduction_esi_y").value;
		
		 var d = document.getElementById("epfDropdown");
    	if(d.value=='y'){
		console.log("yes");
		//epfDropdown;
		 	epfDropdown = 'yes';
		calculateEsiAndEpf();
		
		
	}
	else{
		console.log("no");
		epfDropdown = 'no';
		calculateEsiAndEpf();
	}
		
}

function calculateCtc(){
	
	console.log(" ctc block ");
	var grossY =document.getElementById("grossM").value *12;
	var deductionY =document.getElementById("deduction_total_m").value*12;
	
	var r = parseInt(grossY)+parseInt(deductionY);
	
	 document.getElementById("ctc").value = r;

	var netpay = parseInt(r) - (parseInt(deductionY)/12);
	document.getElementById("net_pay_y").value = netpay;
	
}




/* ajax or js for LEAVE_REQUEST*/











