<!DOCTYPE html>
<html>
<body>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

<h2>AJAX</h2>

<script>
function getPersonInfo() {
	console.log($('#gatorlinkInput').val());
    $.ajax({
        type: "POST", //Type of post
        url: "GetPersonJson", //Where it is sent (Which servlet)
        dataType: "json", //datatype
        data: {gatorlink:$('#gatorlinkInput').val()},
        success: function (msg) {
        	console.log("HELLO!");
            if (msg.failure != 1) {
                $("#demo").text(JSON.stringify(msg));
            } else {
                alert("Bad Gatorlink!");
            }
        }
    });
}
</script>

<input type="text" id="gatorlinkInput"></input>
<button type="button" onclick="getPersonInfo()">Request data</button>
<p id="demo"></p>

</body>
</html>
