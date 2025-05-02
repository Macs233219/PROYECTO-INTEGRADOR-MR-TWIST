/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function mostrarConfirmacion(event) {
    event.preventDefault();

    var productoSelect = document.querySelector('select[name="idProducto"]');
    var idProducto = productoSelect.value;
    var cantidad = document.getElementById('cantidad').value;
    var motivo = document.getElementById('motivo').value;

    if (!idProducto) {
        mostrarAlerta("Debe seleccionar un producto.");
        return false;
    }

    if (cantidad.trim() === "" || isNaN(cantidad) || cantidad <= 0) {
        mostrarAlerta("La cantidad debe ser mayor a cero.");
        return false;
    }
    if (motivo.trim() === "") {
        mostrarAlerta("El motivo de la merma es obligatorio.");
        return false;
    }

    document.getElementById('confirmModal').style.display = 'flex';
}


function aceptarConfirmacion() {
    document.getElementById('formularioRegistrarMerma').submit();
}

function cancelarConfirmacion() {
    document.getElementById('confirmModal').style.display = 'none';
}

function mostrarAlerta(mensaje, tipo = 'error') {
    const alerta = document.getElementById('alertaCampos');
    alerta.innerText = mensaje;

    alerta.className = 'alerta';

    if (tipo === 'error') {
        alerta.classList.add('alerta-error');
    } else if (tipo === 'exito') {
        alerta.classList.add('alerta-exito');
    }

    alerta.style.display = 'block';

    setTimeout(() => {
        alerta.style.display = 'none';
    }, 8000);
}
