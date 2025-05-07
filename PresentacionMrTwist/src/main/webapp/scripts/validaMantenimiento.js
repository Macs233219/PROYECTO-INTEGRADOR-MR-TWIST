/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
function mostrarConfirmacion(event) {
    event.preventDefault();

    var maquinaSelect = document.querySelector('select[name="idMaquina"]');
    var idMaquina = maquinaSelect.value;
    var descripcion = document.getElementById('descripcion').value;

    if (!idMaquina) {
        mostrarAlerta("Debe seleccionar un producto.");
        return false;
    }

    if (descripcion.trim() === "" ) {
        mostrarAlerta("La descripción es obligatoria.");
        return false;
    }

    document.getElementById('confirmModal').style.display = 'flex';
}


function aceptarConfirmacion() {
    document.getElementById('formularioRegistrarMantenimiento').submit();
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

