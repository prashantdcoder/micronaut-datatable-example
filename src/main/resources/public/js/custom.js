/*=====Custom JS======*/
var START = 0;

$(document).on('click', '#scrollButton', function () {
    $(".row").append(initDiamonds());
});

function generateProductHtml(color, name, id) {
    var div = document.createElement("div");
    var img = document.createElement("img");
    var p = document.createElement("p");
    var span = document.createElement("span");
    div.setAttribute("class", "col-md-4");
    img.setAttribute("src", "/public/images/ruby.jpg");
    p.innerHTML = id + " : " + name;
    span.innerHTML = color;
    div.append(img, p, span);
    return div
}

function initDiamonds() {
    var url = '/fetchDiamonds';
    $.ajax({
        type: 'get',
        url: url,
        data: {start: START++, length: 3},
        dataType: 'json',
        success: function (response) {
            console.log(response);
            $.each(response, function (i, val) {
                $(".row").append(generateProductHtml(val.color, val.name, val.id));
            });
        },
        error: function () {
            alert("error");
        }
    })
}