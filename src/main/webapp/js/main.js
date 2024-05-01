document.addEventListener("DOMContentLoaded", function () {
  // Selecciona todas las imágenes con li
  const imagenes = document.querySelectorAll(".carrusel li")
  // Indice de la imagen visible
  let indice = 0
  function cambioImagen() {
    // Oculta la que está
    imagenes[indice].style.opacity = 0
    // incrementa al indice de la imagen actual= indice de la siguiente
    indice = (indice + 1) % imagenes.length
    //muestra la siguiente
    imagenes[indice].style.opacity = 1
  }
  // para intercambio cada 5 seg.
  setInterval(cambioImagen, 3500)
})

//desplegable de actividades en formulario solicitudes
function cargarActividades() {
  fetch("ListarActividad")
    .then((res) => res.json())
    .then((res) => {
      let actividades = document.getElementById("actividades")
      for (let i = 0; i < res.length; i++) {
        let option = document.createElement("option")
        option.text = res[i].nombreA
        option.value = res[i].cod_actividad
        actividades.add(option)
      }
    })
}
