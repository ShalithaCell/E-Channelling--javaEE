//load users
function LoadHospitals() {
    //$.fn.dataTable.moment('M/D/YYYY h:mm:ss A');
    var table = $('#tbl_hospital').DataTable({
        ajax: {
            type: "post",
            url: "adminPanel",
            dataType: "json",
            data: { "command" : "getHospital"},
            beforeSend: function (request) {
                $('#loadingSpinner').show();
            },
            dataSrc: function (json) {
                $('#loadingSpinner').hide();
                var result = (JSON.stringify(json));
                console.log(result);
                return JSON.parse(result);
            },
            fnDrawCallback: function (oSettings) {
                $('#loadingSpinner').hide();
            },
        },
        fixedHeader: {
            "header": true
        },
        scrollY: "500px",
        scrollCollapse: true,
        deferRender: true,
        pageLength: 10,
        order: [[0, "asc"]],
        columns: [
            { 'data': 'HospitalID'},
            { 'data': 'Description' },
            { 'data': 'Address' },
            { 'data': 'Active' },
            { 'data': null, 'render': function (data, type, row) {
                    return '<button type= "button" class="btn btn-info btn-header" style="font-size: .5vw;" onclick="editCenter(' + data.HospitalID + ');"><i class="fa fa-pencil" aria-hidden="true"></i> Edit</button>'
                }
            },
            { 'data': null, 'render': function (data, type, row) {
                    return '<button type= "button" class="btn btn-danger btn-header" style="font-size: .5vw;" onclick="Remove(' + data.HospitalID + ');"><i class="fa fa-trash" aria-hidden="true"></i> Remove</button>'
                }
            }
        ],

        dom: 'lrtip',
        initComplete: function () {
            $('#tbl_hospital').DataTable().columns.adjust();
        }
    });
}

function addHospital() {
    $.confirm({
        title: 'Enter Medical Center Details!',
        content: '' +
            '<form action="" class="formName">' +
            '<div class="form-group">' +
            '<label>Medical Center Name :</label>' +
            '<input type="text" placeholder="Medical Center Name" id="txtHospitalName" class="name form-control" required />' +
            '</div>' +
            '<div class="form-group">' +
            '<label>Address</label>' +
            '<input type="textarea" placeholder="Address" id="hospitalAddress" class="address form-control" required />' +
            '</div>' +
            '</form>',
        buttons: {
            cancel: function () {
                //close
            },
            Save: {
                text: 'Save',
                btnClass: 'btn-success',
                action: function () {

                    var valied = 0;

                    if($('#txtHospitalName').val().length == 0){
                        this.$content.find('.name').addClass('has-error')
                        valied += 1;
                    }else{
                        this.$content.find('.name').removeClass('has-error');
                    }

                    if($('#hospitalAddress').val().length == 0){
                        this.$content.find('.address').addClass('has-error')
                        valied += 1;
                    }else{
                        this.$content.find('.address').removeClass('has-error');
                    }

                    if(valied > 0)
                        return false;

                    console.log($('#hospitalAddress').val());

                    $.ajax({
                        url: 'adminPanel',
                        type: 'post',
                        data: { "HosName": $('#txtHospitalName').val(), "address" : $('#hospitalAddress').val(), "command" : "newitem"},
                        beforeSend: function(){
                            $('#loadingSpinner').show();
                        },
                        complete: function(){

                        },
                        success: function(response) {
                            $('#loadingSpinner').hide();

                            var result = JSON.parse(response);
                            if(result.result == 'true') {
                                $("#tbl_hospital").dataTable().fnDestroy();
                                StartToasterMessage(MESSAGE_SUCCESS, result.message, 'New Item Added !');

                                LoadHospitals();
                                return true;
                            }else{
                                StartToasterMessage(MESSAGE_DANGER, 'Something went wrong.please try again', 'Error');
                                return false;
                            }

                        }
                    });


                }
            },

        },
        contentLoaded: function(data, status, xhr){
            $('#loadingSpinner').show();
        },
        onContentReady: function () {
            // bind to events
            $('#loadingSpinner').hide();
        }
    });
}

function editCenter(HospitalID) {
    $.confirm({
        theme: 'modern',
        title: 'Update Medical Center',
        content: '' +
            '<form action="" class="frmeditHospital">' +
            '<div class="form-group">' +
            '<label>Medical Center Name :</label>' +
            '<input type="text" placeholder="Medical Center Name" id="txtHospitalNameE" class="name form-control" required />' +
            '</div>' +
            '<div class="form-group">' +
            '<label>Address</label>' +
            '<input type="textarea" placeholder="Address" id="hospitalAddressE" class="address form-control" required />' +
            '</div>' +
            '</form>',
        buttons: {
            cancel: function () {
                //close
            },
            Save: {
                text: 'Save',
                btnClass: 'btn-blue',
                action: function () {
                    var valied = 0;

                    if($('#txtHospitalNameE').val().length == 0){
                        this.$content.find('.name').addClass('has-error')
                        valied += 1;
                    }else{
                        this.$content.find('.name').removeClass('has-error');
                    }

                    if($('#hospitalAddressE').val().length == 0){
                        this.$content.find('.address').addClass('has-error')
                        valied += 1;
                    }else{
                        this.$content.find('.address').removeClass('has-error');
                    }

                    if(valied > 0)
                        return false;

                    $.ajax({
                        url: 'adminPanel',
                        type: 'post',
                        data: { "hospitalID": HospitalID, "hospitalname": $('#txtHospitalNameE').val(), "hospitalAddress" : $('#hospitalAddressE').val(), "command" : "updatehospital"},
                        beforeSend: function(){
                            $('#loadingSpinner').show();
                        },
                        complete: function(){

                        },
                        success: function(response) {
                            $('#loadingSpinner').hide();

                            var result = JSON.parse(response);
                            if(result.result == 'true') {
                                $("#tbl_hospital").dataTable().fnDestroy();
                                StartToasterMessage(MESSAGE_SUCCESS, result.message, 'Medical Center updated !');

                                LoadHospitals();
                                return true;
                            }else{
                                StartToasterMessage(MESSAGE_DANGER, 'Something went wrong.please try again', 'Error');
                                return false;
                            }

                        }
                    });

                }
            },

        },
        contentLoaded: function(data, status, xhr){
            $('#loadingSpinner').show();
        },
        onContentReady: function () {
            // bind to events
            $('#loadingSpinner').hide();


            //loading details
            $.ajax({
                url: 'adminPanel',
                type: 'post',
                data: { "command": "getHospitalByID", "HospitalID" : HospitalID},
                beforeSend: function(){
                    $('#loadingSpinner').show();
                },
                complete: function(){

                },
                success: function(response) {
                    $('#loadingSpinner').hide();
                    var result = JSON.parse(response);

                    $('#txtHospitalNameE').val(result[0].Description);
                    $('#hospitalAddressE').val(result[0].Address);
                }
            });


        }
    });
}

function Remove(HospitalID) {
    //loading details
    $.ajax({
        url: 'adminPanel',
        type: 'post',
        data: { "command": "RemoveHospital", "hospitalID" : HospitalID},
        beforeSend: function(){
            $('#loadingSpinner').show();
        },
        complete: function(){

        },
        success: function(response) {
            $('#loadingSpinner').hide();
            $("#tbl_hospital").dataTable().fnDestroy();
            StartToasterMessage(MESSAGE_SUCCESS, "Deleted", 'Medical Center Deleted !');

            LoadHospitals();
        }
    });
}

function LoadDoctors() {
    //$.fn.dataTable.moment('M/D/YYYY h:mm:ss A');
    var table = $('#tbl_doctors').DataTable({
        ajax: {
            type: "post",
            url: "adminPanel",
            dataType: "json",
            data: { "command" : "getDoctors"},
            beforeSend: function (request) {
                $('#loadingSpinner').show();
            },
            dataSrc: function (json) {
                $('#loadingSpinner').hide();
                var result = (JSON.stringify(json));

                return JSON.parse(result);
            },
            fnDrawCallback: function (oSettings) {
                $('#loadingSpinner').hide();
            },
        },
        fixedHeader: {
            "header": true
        },
        scrollY: "500px",
        scrollCollapse: true,
        deferRender: true,
        pageLength: 10,
        order: [[0, "asc"]],
        columns: [
            { 'data': 'DoctorID'},
            { 'data': 'DoctorName' },
            { 'data': 'Speciality' },
            { 'data': 'Active' },
            { 'data': null, 'render': function (data, type, row) {
                    return '<button type= "button" class="btn btn-info btn-header" style="font-size: .5vw;" onclick="editDoctor(' + data.DoctorID + ');"><i class="fa fa-pencil" aria-hidden="true"></i> Edit</button>'
                }
            },
            { 'data': null, 'render': function (data, type, row) {
                    return '<button type= "button" class="btn btn-danger btn-header" style="font-size: .5vw;" onclick="RemoveDoctor(' + data.DoctorID + ');"><i class="fa fa-trash" aria-hidden="true"></i> Remove</button>'
                }
            }
        ],

        dom: 'lrtip',
        initComplete: function () {
            $('#tbl_doctors').DataTable().columns.adjust();
        }
    });
}

function AddDoctor() {
    $.confirm({
        title: 'Doctor Details!',
        content: '' +
            '<form action="" id="frmDct" class="formName">' +
            '<div class="form-group">' +
            '<label>Doctor Name :</label>' +
            '<input type="text" placeholder="Doctor Name" id="txtDoctorName" class="name form-control" required />' +
            '</div>' +
            '<div class="form-group">' +
            '<label>Speciality</label>' +
            '<input type="textarea" placeholder="Speciality" id="txtSpeciality" class="speciality form-control" required />' +
            '</div>' +
            '</form>',
        buttons: {
            cancel: function () {
                //close
            },
            Save: {
                text: 'Save',
                btnClass: 'btn-success',
                action: function () {

                    var valied = 0;

                    if($('#txtDoctorName').val().length == 0){
                        this.$content.find('.name').addClass('has-error')
                        valied += 1;
                    }else{
                        this.$content.find('.name').removeClass('has-error');
                    }

                    if($('#txtSpeciality').val().length == 0){
                        this.$content.find('.speciality').addClass('has-error')
                        valied += 1;
                    }else{
                        this.$content.find('.speciality').removeClass('has-error');
                    }

                    if(valied > 0)
                        return false;


                    $.ajax({
                        url: 'adminPanel',
                        type: 'post',
                        data: { "name": $('#txtDoctorName').val(), "speciality" : $('#txtSpeciality').val(), "command" : "addDoctors"},
                        beforeSend: function(){
                            $('#loadingSpinner').show();
                        },
                        complete: function(){

                        },
                        success: function(response) {
                            $('#loadingSpinner').hide();

                            var result = JSON.parse(response);
                            if(result.result == 'true') {
                                $("#tbl_doctors").dataTable().fnDestroy();
                                StartToasterMessage(MESSAGE_SUCCESS, result.message, 'New Item Added !');

                                LoadDoctors();
                                return true;
                            }else{
                                StartToasterMessage(MESSAGE_DANGER, 'Something went wrong.please try again', 'Error');
                                return false;
                            }

                        }
                    });


                }
            },

        },
        contentLoaded: function(data, status, xhr){
            $('#loadingSpinner').show();

        },
        onContentReady: function () {
            // bind to events
            $('#loadingSpinner').hide();


        }
    });
}


function editDoctor(DoctorID) {
    $.confirm({
        theme: 'modern',
        title: 'Update Doctor',
        content: '' +
            '<form action="" class="fromDoctor">' +
            '<div class="form-group">' +
            '<label>Doctor Name :</label>' +
            '<input type="text" placeholder="Doctor Name" id="txtDoctorName" class="name form-control" required />' +
            '</div>' +
            '<div class="form-group">' +
            '<label>Address</label>' +
            '<input type="textarea" placeholder="Address" id="txtSpeciality" class="speciality form-control" required />' +
            '</div>' +
            '</form>',
        buttons: {
            cancel: function () {
                //close
            },
            Save: {
                text: 'Save',
                btnClass: 'btn-blue',
                action: function () {
                    var valied = 0;

                    if($('#txtDoctorName').val().length == 0){
                        this.$content.find('.name').addClass('has-error')
                        valied += 1;
                    }else{
                        this.$content.find('.name').removeClass('has-error');
                    }

                    if($('#txtSpeciality').val().length == 0){
                        this.$content.find('.address').addClass('has-error')
                        valied += 1;
                    }else{
                        this.$content.find('.address').removeClass('has-error');
                    }

                    if(valied > 0)
                        return false;

                    $.ajax({
                        url: 'adminPanel',
                        type: 'post',
                        data: { "DoctorID": DoctorID, "name": $('#txtDoctorName').val(), "specilality" : $('#txtSpeciality').val(), "command" : "updateDoctor"},
                        beforeSend: function(){
                            $('#loadingSpinner').show();
                        },
                        complete: function(){

                        },
                        success: function(response) {
                            $('#loadingSpinner').hide();

                            var result = JSON.parse(response);
                            if(result.result == 'true') {
                                $("#tbl_doctors").dataTable().fnDestroy();
                                StartToasterMessage(MESSAGE_SUCCESS, result.message, 'Doctor updated !');

                                LoadDoctors();
                                return true;
                            }else{
                                StartToasterMessage(MESSAGE_DANGER, 'Something went wrong.please try again', 'Error');
                                return false;
                            }

                        }
                    });

                }
            },

        },
        contentLoaded: function(data, status, xhr){
            $('#loadingSpinner').show();
        },
        onContentReady: function () {
            // bind to events
            $('#loadingSpinner').hide();


            //loading details
            $.ajax({
                url: 'adminPanel',
                type: 'post',
                data: { "command": "GetDoctorByID", "DoctorID" : DoctorID},
                beforeSend: function(){
                    $('#loadingSpinner').show();
                },
                complete: function(){

                },
                success: function(response) {
                    $('#loadingSpinner').hide();
                    var result = JSON.parse(response);

                    $('#txtDoctorName').val(result[0].DoctorName);
                    $('#txtSpeciality').val(result[0].Speciality);
                }
            });


        }
    });
}


function RemoveDoctor(DoctID) {
    //loading details
    $.ajax({
        url: 'adminPanel',
        type: 'post',
        data: { "command": "RemoveDoctor", "docID" : DoctID},
        beforeSend: function(){
            $('#loadingSpinner').show();
        },
        complete: function(){

        },
        success: function(response) {
            $('#loadingSpinner').hide();
            $("#tbl_doctors").dataTable().fnDestroy();
            StartToasterMessage(MESSAGE_SUCCESS, "Deleted", 'Medical Center Deleted !');

            LoadDoctors();
        }
    });
}
