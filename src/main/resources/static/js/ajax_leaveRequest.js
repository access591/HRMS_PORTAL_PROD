function leaveEmployeDisplay(){
	
	 var d = document.getElementById("employeeDropDownLeave"); 
	var dValue = document.getElementById("employeeDropDownLeave").value;	
	console.log(" d value is :"+ dValue );  
	 var displayItem = d.options[d.selectedIndex].text;	 
	 document.getElementById("hiddenField").value = displayItem;	 
	document.getElementById("leaveRequestEmpId").value = dValue;	
    console.log(displayItem);
    ajaxRequestLeave(dValue);
}


function ajaxRequestLeave(val){
	var empCode = val;
    console.log("ajax request : "+ empCode);
    const xhr = new XMLHttpRequest();
    xhr.open('POST','empPayDetail/'+empCode,true);
    xhr.getResponseHeader('content-type','application/json');
    xhr.onload = function(){
        if(this.status===200){
            let obj = JSON.parse(this.responseText);
            console.log(obj);

			var deptCode = obj.departmentCode;
			
			console.log(" depARTMENT CODE :"+	deptCode);
			
			$('#leaveDepartmentId').val( obj.departmentCode); 
			
			ajaxRequestForDepartmentLeave(deptCode);
        }
        else{
            console.error("some error occured");
        }
	
    }
    xhr.send();
}

function ajaxRequestForDepartmentLeave(val){
	var deptCode = val;
    console.log("ajax request : "+ deptCode);
    var xhr1 = new XMLHttpRequest();
    xhr1.open('GET','getdepartmentd/'+deptCode,true);
    xhr1.getResponseHeader('content-type','application/json');
	xhr1.response = 'json';
    xhr1.onload = function(){
        if(this.status===200){
		
			console.log("successfull");
             let obj1 = JSON.parse(this.responseText);
            console.log(obj1);

			$('#leaveDepartmentName').val(obj1.deptName); 
        }
        else{
            console.error("some error occured");
        }
    }
    xhr1.send();
}

var leaveTypeId = "";
var fromDateType = "f";
var toDateType = "f"

function leaveTypeFun(){
	
	leaveTypeId =  document.getElementById("leave_type_id").value;
	console.log("type of leave : "+leaveTypeId);
	
	if(leaveTypeId=="single"){
		console.log(" single if block");
		document.getElementById("to_date_type").disabled = true;
		
		document.getElementById("to_date").disabled = true;
		document.getElementById("to_date").value = document.getElementById("from_date").value;
		document.getElementById("to_date_type").value = "";
		countLeaveDays();
	}																

	else{
		console.log("multiple else block ");
		document.getElementById("to_date_type").disabled = false;
		document.getElementById("to_date").disabled = false;
		
		document.getElementById("to_date_type").value = f;
		
		countLeaveDays();

	}
}
	

function changeToDate(val){
	console.log(" dateChange function" + val);
	console.log("leave type : "+ leaveTypeId);
	if(leaveTypeId == "single"){
		document.getElementById("to_date").value = val;

		//document.getElementById("to_date").disabled = true;
	}
	if(leaveTypeId == "multiple"){
		countLeaveDays();
	}
}


function getFromDate(val){
	console.log("get ffrom date : ="+ val);

	if(leaveTypeId == "single" || leaveTypeId==""){
		document.getElementById("to_date").value = document.getElementById("from_date").value;
	}
	
}

function getToDate(val){
	console.log("get to date : "+ val);
	if(leaveTypeId == "multiple" ){
		countLeaveDays();
	}
}

//  click form type
function fromDayTypeValue(val){
	console.log("val is : " + val );
	fromDateType = val;
	
	console.log("from date type : " + fromDateType);
	
	console.log(fromDateType + " in day type ");
	document.getElementById("to_date_type").value = fromDateType;
	countLeaveDays();
}

function toDayTypeValue(val){
	console.log("to date type val is : "+ val);
	toDateType = val;
	countLeaveDays();
}

function countLeaveDays(){

	console.log("==========count leave=========== ");
	console.log("leave type ===>"+leaveTypeId);
	console.log("toDateType ===>"+toDateType);
	console.log("fromDateType==>" + fromDateType);
	
	if(leaveTypeId == "single" && (fromDateType =="f" || toDateType=="f") )
	{
		
		document.getElementById("leave_for").value = 1 ;
		console.log("1st block");
	}
	if(leaveTypeId == "single" && (fromDateType =="fh" || fromDateType == "sh") )
	{
		document.getElementById("leave_for").value = 0.5 ;
		console.log("2nd block");
	}
	if(leaveTypeId == "" && (fromDateType =="fh" || fromDateType == "sh") )
	{
		document.getElementById("leave_for").value = 1 ;
		console.log("3rd block");
	}
	if(leaveTypeId == "multiple" ){

		var fromDate = document.getElementById("from_date").value;
		var toDate = document.getElementById("to_date").value;

		var date1 = new Date(fromDate);
		var date2 = new Date(toDate);

		const diffTime = Math.abs(date2 - date1);
		const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));
			
		console.log(" toatl leaves : " + diffDays);
	
		if(fromDateType=="f" && toDateType != "f"){
			console.log("first full & second half day");
			document.getElementById("leave_for").value = diffDays + 1 - 0.5;
		}
		if(fromDateType !="f" && toDateType =="f"){
			console.log("from half & second full day");
			document.getElementById("leave_for").value = diffDays + 1 - 0.5;
		}
		if(fromDateType == "f" && toDateType == "f"){
			console.log("complete off");
			document.getElementById("leave_for").value = diffDays+1;
		}
		if((fromDateType=="fh" || fromDateType =="sh") && (toDateType=="fh" || toDateType=="sh")){
			console.log("- one ")
			document.getElementById("leave_for").value = diffDays + 1 - 1;
			// - 0.5
		}

		
		

	
	}


}
