$(document).ready(function () {
  //1.hide error section
  $("#firstNameError").hide();
  $("#lastNameError").hide();
  $("#emailError").hide();
  $("#addressError").hide();
  $("#mobileError").hide();
  $("#genderError").hide();
  $("#noteError").hide();

  //2.define error variable
  var firstNameError = false;
  var lastNameError = false;
  var emailError = false;
  var addressError = false;
  var mobileError = false;
  var genderError = false;
  var noteError = false;

  //3.validate function
  function validate_firstName() {
    var val = $("#firstName").val();
    var exp = /^[A-Za-z0-9\s\.]{4,60}$/;
    if (val == "") {
      $("#firstNameError").show();
      $("#firstNameError").html("*<b>firstName</b> can not be empty");
      $("#firstNameError").css("color", "red");
      firstNameError = false;
    } else if (!exp.test(val)) {
      $("#firstNameError").show();
      $("#firstNameError").html("*<b>firstName</b> must be 4 to 60 chars only");
      $("#firstNameError").css("color", "red");
      firstNameError = false;
    }else {
                    $("#firstNameError").hide();
                    firstNameError = true;
                    $.ajax({
                    	url:'checkFirstName',
                    	data:{"firstname":val},
                    	success:function(respTxt){
                    		if(respTxt!=''){
                    			$("#firstNameError").show();
                                $("#firstNameError").html(respTxt)
                                $("#firstNameError").css('color','red');
                                firstNameError=false;
                    		}else{
                    			$("#firstNameError").hide();
                    			firstNameError=true;
                    		}
                    	}	
                       });
                }
    return firstNameError;
  }
  
  function validate_lastName() {
    var val = $("#lastName").val();
    var exp = /^[A-Za-z0-9\s\.]{4,60}$/;
    if (val == "") {
      $("#lastNameError").show();
      $("#lastNameError").html("*<b>lastName</b> can not be empty");
      $("#lastNameError").css("color", "red");
      lastNameError = false;
    } else if (!exp.test(val)) {
      $("#lastNameError").show();
      $("#lastNameError").html("*<b>lastName</b> must be 4 to 60 chars only");
      $("#lastNameError").css("color", "red");
      lastNameError = false;
    } else {
                    $("#lastNameError").hide();
                    lastNameError = true;
                     $.ajax({
                    	url:'checkLastName',
                    	data:{"lastname":val},
                    	success:function(respTxt){
                    		if(respTxt!=''){
                    			$("#lastNameError").show();
                                $("#lastNameError").html(respTxt)
                                $("#lastNameError").css('color','red');
                                lastNameError=false;
                    		}else{
                    			$("#lastNameError").hide();
                    			lastNameError=true;
                    		}
                    	}	
                       });
                }
    return lastNameError;
  }

  function validate_email() {
    var val = $("#email").val();
    var exp = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    if (val == "") {
      $("#emailError").show();
      $("#emailError").html("*<b>email</b> Can not be empty");
      $("#emailError").css("color", "red");
      emailError = false;
    } else if (!exp.test(val)) {
      $("#emailError").show();
      $("#emailError").html("*<b>email</b> not valid");
      $("#emailError").css("color", "red");
      emailError = false;
    } else {
                    $("#emailError").hide();
                    emailError = true;
                    $.ajax({
                    	url:'checkEmail',
                    	data:{"emailid":val},
                    	success:function(respTxt){
                    		if(respTxt!=''){
                    			$("#emailError").show();
                                $("#emailError").html(respTxt)
                                $("#emailError").css('color','red');
                                emailError=false;
                    		}else{
                    			$("#emailError").hide();
                    			emailError=true;
                    		}
                    	}	
                       });
                }
    return emailError;
  }

  function validate_address() {
    var val = $("#address").val();
    var exp = /^[A-Za-z0-9\s\.\-\,\']{10,250}$/;
    if (val == "") {
      $("#addressError").show();
      $("#addressError").html("*<b>address</b> Can not be empty");
      $("#addressError").css("color", "red");
      addressError = false;
    } else if (!exp.test(val)) {
      $("#addressError").show();
      $("#addressError").html("*<b>address</b> can have 10 to 250 chars");
      $("#addressError").css("color", "red");
      addressError = false;
    } else {
                    $("#addressError").hide();
                    addressError = true;
                     $.ajax({
                    	url:'checkAddress',
                    	data:{"addr":val},
                    	success:function(respTxt){
                    		if(respTxt!=''){
                    			$("#addressError").show();
                                $("#addressError").html(respTxt)
                                $("#addressError").css('color','red');
                                addressError=false;
                    		}else{
                    			$("#addressError").hide();
                    			addressError=true;
                    		}
                    	}	
                       });
                }
    return addressError;
  }

  function validate_mobile() {
    var val = $("#mobile").val();
    var exp = /^[0-9]{10}$/;
    if (val == "") {
      $("#mobileError").show();
      $("#mobileError").html("*<b>mobile</b> Can not be empty");
      $("#mobileError").css("color", "red");
      mobileError = false;
    } else if (!exp.test(val)) {
      $("#mobileError").show();
      $("#mobileError").html("*<b>mobile</b> not valid");
      $("#mobileError").css("color", "red");
      mobileError = false;
    } else {
                    $("#mobileError").hide();
                    mobileError = true;
                     $.ajax({
                    	url:'checkMobile',
                    	data:{"Number":val},
                    	success:function(respTxt){
                    		if(respTxt!=''){
                    			$("#mobileError").show();
                                $("#mobileError").html(respTxt)
                                $("#mobileError").css('color','red');
                                mobileError=false;
                    		}else{
                    			$("#mobileError").hide();
                    			mobileError=true;
                    		}
                    	}	
                       });
                }
    return mobileError;
  }

  function validate_gender() {
    var val = $("#gender").val();
    if (val == "") {
      $("#genderError").show();
      $("#genderError").html("*<b>gender</b> not selected");
      $("#genderError").css("color", "red");
      genderError = false;
    } else if (!exp.test(val)) {
      genderError = false;
    } else {
                    $("#genderError").hide();
                    genderError = true;
                }
    return genderError;
  }

  function validate_note() {
    var val = $("#note").val();
    var exp = /^[A-Za-z0-9\s\.\-\,\']{10,250}$/;
    if (val == "") {
      $("#noteError").show();
      $("#noteError").html("*<b>note</b> Can not be empty");
      $("#noteError").css("color", "red");
      noteError = false;
    } else if (!exp.test(val)) {
      $("#noteError").show();
      $("#noteError").html("*<b>note</b> can have 10 to 250 chars");
      $("#noteError").css("color", "red");
      noteError = false;
    } else {
                    $("#noteError").hide();
                    noteError = true;
                    $.ajax({
                    	url:'checkNote',
                    	data:{"Info":val},
                    	success:function(respTxt){
                    		if(respTxt!=''){
                    			$("#noteError").show();
                                $("#noteError").html(respTxt)
                                $("#noteError").css('color','red');
                                noteError=false;
                    		}else{
                    			$("#noteError").hide();
                    			noteError=true;
                    		}
                    	}	
                       });
                }
    return noteError;
  }

  //4.action event
  $("#firstName").keyup(function () {
    $(this).val($(this).val().toUpperCase());
    validate_firstName();
  });

  $("#lastName").keyup(function () {
    $(this).val($(this).val().toUpperCase());
    validate_lastName();
  });

  $("#email").keyup(function () {
    validate_email();
  });

  $("#address").keyup(function () {
    validate_address();
  });

  $("#mobile").keyup(function () {
    validate_mobile();
  });

  $("#gender").keyup(function () {
    validate_gender();
  });

  $("#note").keyup(function () {
    validate_note();
  });

  //5.on submit
  $("#docForm").submit(function () {
    validate_firstName();
    validate_lastName();
    validate_email();
    validate_address();
    validate_mobile();
    validate_gender();
    validate_note();

    if (
      firstNameError &&
      lastNameError &&
      emailError &&
      addressError &&
      mobileError &&
      genderError &&
      noteError
    ) return true;
    else return false;
  });
});
