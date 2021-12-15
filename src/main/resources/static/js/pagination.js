var table = $('#myTable');

console.log("table js ");
var sta = 0;

var elements_per_page = 5;

var limit = elements_per_page;
var totalItems = "";

goFun(sta, limit);

function goFun(sta, limit) {


    console.log("go fun method : " + sta + " : " + limit);
    // my code 

    console.log("path variable is : "+ sta);
    $.ajax({
        type: "GET",
        url: "page/" + sta,
        datatype: 'json',
        cache: false,
        success: function (json) {
            // $("#results").append(html);

            console.log("total items " + json.totalItems);
            console.log("current page : " + json.currentPage);
            console.log("total pages : " + json.totalPages);

            var length = json.object.length;
            totalItems = json.totalItems;

            console.log("response length : " + json.object.length);

            console.log("object name : " + json.object[0].empCode)


            for (var i = 0; i < length; i++) {

                console.log("for block : " + i);
                var $nr = $('<tr><td>' + json.object[i].empCode + '</td><td>' + json.object[i].empName + '</td><td>' + json.object[i].category + '</td>'
                    + '<td>' + json.object[i].batchYear + '</td><td>' + json.object[i].designation.desgName + '</td><td>' + 'hii' + '</td></tr>');

                $('#myTable').append($nr);
            }



        }
    });


    //   end


}


$(document).ready(function () {
    $('#nextValue').click(function () {

        console.log("next button click : ");
        var next = limit;

        if (totalItems > next) {

            //limit = limit + elements_per_page;

            $('#myTable').empty();

            console.log(next +' -next- '+limit);

            console.log("next value is : "+ next);

            console.log("limit : "+ limit);
            goFun(next, limit);
        }
    });
});


$(document).ready(function () {
    $('#PreeValue').click(function () {

        console.log("pree button click ");
        //var pre = limit - (2 * elements_per_page);

        var pre = limit - elements_per_page;
        
        console.log("previous value : "+ pre);

        if (pre >= 0) {


            limit = limit - elements_per_page;
            console.log(pre + ' -pre- ' + limit);
            $('#myTable').empty();
            goFun(pre, limit);
        }
    });
});