//Listar tabla actividades

function llamada() {
  fetch("../ListarActividad?op=1")
    .then((response) => response.json())
    .then((data) => pintarResultados(data))
}

// Pinta la tabla
function pintarResultados(datos) {
  let html = "<table>"
  html +=
    "<tr><th>Cod Actividad</th><th>Nombre</th><th>Lugar</th><th>Tema</th><th>Descripción</th><th>Imagen</th><th>Fecha Inicio</th><th>Fecha Fin</th><th>Edad Mínima</th><th>Edad Máxima</th><th>Plazas</th></tr>"
  for (let i = 0; i < datos.length; i++) {
    html +=
      "<tr><td>" +
      datos[i].cod_actividad +
      "</td><td>" +
      datos[i].nombreA +
      "</td><td>" +
      datos[i].lugar +
      "</td><td>" +
      datos[i].tema +
      "</td><td>" +
      datos[i].descripcion +
      "</td><td><img src='../files/" +
      datos[i].imagen +
      "' alt='Imagen de actividad'></td><td>" +
      datos[i].f_inicio +
      "</td><td>" +
      datos[i].f_fin +
      "</td><td>" +
      datos[i].e_min +
      "</td><td>" +
      datos[i].e_max +
      "</td><td>" +
      datos[i].plazas +
      "</td><td><a href='add_actividad.html?cod_actividad=" +
      datos[i].cod_actividad +
      "&op=2'>Editar</a></td><td><a href='javascript:borrar(" +
      datos[i].cod_actividad +
      ")'>Borrar</a></td></tr>"
  }

  html += "</table>"

  document.getElementById("resultados").innerHTML = html
}

//Editar actividades
function llamadaEd(cod_actividad, op) {
  fetch("../ListarActividad?cod_actividad=" + cod_actividad + "&op=" + op)
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

// Carga la imagen que hay en la bdd
// Con el get cojo el nombre y luego se lo sumo a la ruta
function cargarImagenBdd(imagenBdd) {
  document.getElementById("imagenBdd").src = "../files/" + imagenBdd
}

//Pinta el formulario con los datos de la actividad
function pintarFormulario(datos) {
  document.getElementById("cod_actividad").value = datos.cod_actividad
  document.getElementById("nombreA").value = datos.nombreA
  document.getElementById("lugar").value = datos.lugar
  document.getElementById("tema").value = datos.tema
  document.getElementById("descripcion").value = datos.descripcion
  //llamo a la funcion
  cargarImagenBdd(datos.imagen)
  //document.getElementById("imagen").value = datos.imagen
  document.getElementById("f_inicio").value = datos.f_inicio
  document.getElementById("f_fin").value = datos.f_fin
  document.getElementById("e_min").value = datos.e_min
  document.getElementById("e_max").value = datos.e_max
  document.getElementById("plazas").value = datos.plazas
}

function borrar(cod_actividad) {
  if (
    confirm("¿Seguro que quieres borrar? Esta acción no se puede deshacer.")
  ) {
    fetch("../ListarActividad?cod_actividad=" + cod_actividad + "&op=3")
      .then((response) => response.json())
      .then((data) => pintarTabla(data))
  } else {
    alert("Acción cancelada")
  }
}

window.onload = function () {
  llamada()

  // Parámetros desde la URL y función para cargar los detalles de la actividad a editar
  let cod_actividad = getParameterByName("cod_actividad")
  let op = getParameterByName("op")
  if (cod_actividad && op) {
    llamadaEd(cod_actividad, op)
  }
}
//validar formulario
document.addEventListener("DOMContentLoaded", function () {
  document.getElementById("AltaActividad").onsubmit = function (event) {
    event.preventDefault() // Previene el envío del formulario hasta pasar la validación
    validarFormulario()
  }
})

function validarFormulario() {
  let f_inicio = document.getElementById("f_inicio").value
  let f_fin = document.getElementById("f_fin").value
  let e_min = parseInt(document.getElementById("e_min").value, 10)
  let e_max = parseInt(document.getElementById("e_max").value, 10)
  let imagen = document.getElementById("imagen").files.length
  let descripcion = document.getElementById("descripcion").value

  // Fecha_fin mayor que fecha_inicio
  if (new Date(f_inicio) >= new Date(f_fin)) {
    alert("La fecha de fin debe ser mayor que la fecha de inicio.")
    return false
  }

  // Edad_máxima es mayor que edad_mínima
  if (e_max <= e_min) {
    alert("La edad máxima debe ser mayor que la edad mínima.")
    return false
  }

  // edad 18-30
  if (e_min < 18 || e_max > 30) {
    alert("Las edades deben estar comprendidas entre 18 y 30 años.")
    return false
  }

  // Aviso imagen pero no obligatoria
  if (imagen === 0) {
    alert("No has seleccionado ninguna imagen.")
  }

  // Aviso descripción pero no obligatoria
  if (descripcion.trim() === "") {
    alert("No has incluido ninguna descripción.")
  }

  // Todo true, enviar el formulario
  document.getElementById("AltaActividad").submit()
}
