/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

const datos = [
    {
      nombre: "Sucursal Centro",
      maquinas: [
        {
          codigo: "M001",
          modelo: "MTX-900",
          mantenimientos: [
            { fecha: "2025-04-10", descripcion: "Cambio de aceite" },
            { fecha: "2025-04-15", descripcion: "Revisión general" }
          ]
        },
        {
          codigo: "M002",
          modelo: "QRS-450",
          mantenimientos: []
        }
      ]
    },
    {
      nombre: "Sucursal Norte",
      maquinas: [
        {
          codigo: "M003",
          modelo: "ZXD-300",
          mantenimientos: [
            { fecha: "2025-03-20", descripcion: "Limpieza de filtros" }
          ]
        }
      ]
    }
  ];
  
  const contenedor = document.getElementById("contenedorSucursales");
  
  datos.forEach((sucursal, i) => {
    const suc = document.createElement("div");
    suc.className = "item";
    suc.textContent = sucursal.nombre;
  
    const contMaquinas = document.createElement("div");
    contMaquinas.className = "sublista";
  
    suc.addEventListener("click", () => {
      contMaquinas.classList.toggle("visible");
    });
  
    sucursal.maquinas.forEach((maquina, j) => {
      const maq = document.createElement("div");
      maq.className = "item";
      maq.textContent = `${maquina.modelo} (${maquina.codigo})`;
  
      const contMants = document.createElement("div");
      contMants.className = "sublista";
  
      maq.addEventListener("click", (e) => {
        e.stopPropagation(); // Evita que el clic propague y cierre la sucursal
        contMants.classList.toggle("visible");
      });
  
      maquina.mantenimientos.forEach((mant, k) => {
        const mantDiv = document.createElement("div");
        mantDiv.className = "mantenimiento";
        mantDiv.innerHTML = `
          <strong>${mant.fecha}</strong>: ${mant.descripcion}
          <br>
          <button class="eliminar">Eliminar</button>
        `;
  
        mantDiv.querySelector(".eliminar").addEventListener("click", () => {
          contMants.removeChild(mantDiv);
        });
  
        contMants.appendChild(mantDiv);
      });
  
      // Formulario para agregar mantenimiento
      const form = document.createElement("div");
      form.className = "agregar-form";
      form.innerHTML = `
        <input type="date" placeholder="Fecha">
        <textarea rows="2" placeholder="Descripción"></textarea>
        <button>Agregar</button>
      `;
  
      const inputFecha = form.querySelector("input");
      const inputDesc = form.querySelector("textarea");
      const btnAgregar = form.querySelector("button");
  
      btnAgregar.addEventListener("click", () => {
        const nuevaFecha = inputFecha.value;
        const nuevaDesc = inputDesc.value;
        if (nuevaFecha && nuevaDesc) {
          const nuevoMant = document.createElement("div");
          nuevoMant.className = "mantenimiento";
          nuevoMant.innerHTML = `
            <strong>${nuevaFecha}</strong>: ${nuevaDesc}
            <br>
            <button class="eliminar">Eliminar</button>
          `;
          nuevoMant.querySelector(".eliminar").addEventListener("click", () => {
            contMants.removeChild(nuevoMant);
          });
  
          // Insertar el nuevo mantenimiento al inicio
          contMants.insertBefore(nuevoMant, contMants.firstChild);
  
          inputFecha.value = "";
          inputDesc.value = "";
        }
      });
  
      // Evitar que el clic en inputs o botón colapse el menú
      [inputFecha, inputDesc, btnAgregar].forEach(el => {
        el.addEventListener("click", (e) => e.stopPropagation());
      });
  
      contMants.appendChild(form);
      maq.appendChild(contMants);
      contMaquinas.appendChild(maq);
    });
  
    suc.appendChild(contMaquinas);
    contenedor.appendChild(suc);
  });
  
