/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
function mostrarConfirmacion(event) {
    event.preventDefault(); 

    var nombre = document.getElementById('nombre').value;
    var cantidad = document.getElementById('cantidad').value;
    var precio = document.getElementById('precio').value;
    var escasez = document.getElementById('escasez').value;
    var descripcion = document.getElementById('descripcion').value;

    if (nombre.trim() === "") {
        mostrarAlerta("El nombre del producto es obligatorio.");
        return false;
    }
    if (cantidad.trim() === "" || isNaN(cantidad) || cantidad < 0) {
        mostrarAlerta("La cantidad total debe ser un número positivo.");
        return false;
    }
    if (precio.trim() === "" || isNaN(precio) || precio < 0) {
        mostrarAlerta("El precio unitario debe ser un número positivo.");
        return false;
    }
    if (escasez.trim() === "" || isNaN(escasez) || escasez < 0) {
        mostrarAlerta("La cantidad de escasez debe ser un número positivo.");
        return false;
    }
    if (descripcion.trim() === "") {
        mostrarAlerta("La descripción es obligatoria.");
        return false;
    }

    document.getElementById('confirmModal').style.display = 'flex';
}

function aceptarConfirmacion() {
    document.getElementById('formularioAgregarProducto').submit();
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

