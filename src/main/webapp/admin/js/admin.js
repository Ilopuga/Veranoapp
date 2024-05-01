//Listar administradores

function llamada() {
  fetch("../ListarAdministrador?op=1")
    .then((response) => response.json())
    .then((data) => pintarResultados(data))
}

//Pinta la tabla
function pintarResultados(datos) {
  let html = "<table>"
  html += "<tr><th>ID</th><th>Usuario</th><th>Pass</th></tr>"
  for (let i = 0; i < datos.length; i++) {
    html +=
      "<tr></tr><td>" +
      datos[i].id +
      "</td><td>" +
      datos[i].usuario +
      "</td><td>" +
      datos[i].pass +
      "</td><td><a href='add_admin.html?id=" +
      datos[i].id +
      "&op=2'>Editar</a></td><td><a href='javascript:borrar(" +
      datos[i].id +
      ")'>Borrar</a></td></tr>"
  }

  html += "</table>"

  document.getElementById("resultados").innerHTML = html
}

//editar actividades
function llamadaEd(id, op) {
  fetch("../ListarAdministrador?id=" + id + "&op=" + op)
    .then((response) => response.json())
    .then((data) => pintarFormulario(data))
}

//Función para otener el valor de un parametro en el GET. Expresiones regulares
function getParameterByName(name) {
  name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]")
  var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
    results = regex.exec(location.search)
  return results === null
    ? ""
    : decodeURIComponent(results[1].replace(/\+/g, " "))
}

//Pinta el formulario con los datos de la actividad
function pintarFormulario(datos) {
  document.getElementById("id").value = datos.id
  document.getElementById("usuario").value = datos.usuario
  document.getElementById("pass").value = datos.pass
}

function borrar(id) {
  if (
    confirm("¿Seguro que quieres borrar? Esta acción no se puede deshacer.")
  ) {
    fetch("../ListarAdministrador?id=" + id + "&op=3")
      .then((response) => response.json())
      .then((data) => pintarTabla(data))
  } else {
    alert("Acción cancelada")
  }
}

window.onload = function () {
  llamada()

  // Parámetros desde la URL y función para cargar los detalles del admin a editar
  let id = getParameterByName("id")
  let op = getParameterByName("op")
  if (id && op) {
    llamadaEd(id, op)
  }
}
