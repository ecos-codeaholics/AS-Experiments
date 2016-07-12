<#import "layout.ftl" as layout />

<@layout.masterTemplate>

<h1>${title}</h1>

<form id="doctor-form" class="pure-form pure-form-stacked">
    <fieldset>
        <legend>
            Find migraine episodes
        </legend>
        <label for="cedula">Patient id</label>
        <input id="cedula" type="text" placeholder="Patient ID" name="cedula">
        <label for="start-date">Start date</label>
        <input id="start-date" type="text" placeholder="yyyy-MM-dd" name="start-date">
        <label for="end-date">End date</label>
        <input id="end-date" type="text" placeholder="yyyy-MM-dd" name="end-date">
        <button id="search" type="submit" class="pure-button pure-button-primary">Search</button>
    </fieldset>
</form>

<script>

    $("#search").click( function () {
        form = $("#doctor-form");
        cedula = form.find("#cedula").val();
        start = form.find("#start-date").val();
        end = form.find("#end-date").val();
        url =  "/episodes/id/"+cedula+"/from/"+start+"/to/"+end,
        console.log(url)

        $.ajax({
            url: url,
            type: "POST",
            data: form.serialize(),
            success: function (res) {
                console.log(res);
            }
        });

        //$.post(url, function (res){
        //    console.log(res);
        //})
    });

</script>

</@layout.masterTemplate>
