var apiClient = (function () {
    var url=window.location.href+'mensajes';
    function addMensaje(){
        var mensaje=document.getElementById("Mensaje").value;
        console.log(mensaje)
        axios.post(url,mensaje)
            .then(res => {
                getMensaje();
            })
    }
    function getMensaje(){
        var num=1;
        $("#Table > tbody").empty();
        axios.get(url).then(res=>{
            console.log(res.data)
            res.data.map(mensaje=>{
                console.log(mensaje)
                $("#Table > tbody").append(
                    "<tr>" +
                    "<td>" + num + "</td>" +
                    "<td>" + mensaje.mensaje + "</td>" +
                    "<td>" + mensaje.fecha + "</td> " +
                    "</tr>"
                );
                num = num +1;
            })
        })
    }
    return {
    	getMensaje:getMensaje,
    	addMensaje:addMensaje
    };
})();