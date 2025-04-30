/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
const contenedor = document.getElementById("contenedorSucursales");

fetch("/api/sucursales")
  .then(response => {
    if (!response.ok) {
      throw new Error(`Error al cargar datos: ${response.statusText}`);
    }
    return response.json();
  })
  .then(datos => {
    if (datos.length === 0) {
      contenedor.innerHTML = "<p>No se encontraron sucursales.</p>";
      return;
    }

    datos.forEach((sucursal) => {
      const suc = document.createElement("div");
      suc.className = "item";
      suc.textContent = sucursal.nombre;

      const contMaquinas = document.createElement("div");
      contMaquinas.className = "sublista";

      suc.addEventListener("click", () => {
        contMaquinas.classList.toggle("visible");
      });

      sucursal.maquinas.forEach((maquina) => {
        const maq = document.createElement("div");
        maq.className = "item";
        maq.textContent = `${maquina.modelo} (${maquina.codigo})`;

        const contMants = document.createElement("div");
        contMants.className = "sublista";

        maq.addEventListener("click", (e) => {
          e.stopPropagation();
          contMants.classList.toggle("visible");
        });

        maquina.mantenimientos.forEach((mant) => {
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
            fetch("/api/mantenimientos", {
              method: "POST",
              headers: {
                "Content-Type": "application/json"
              },
              body: JSON.stringify({
                descripcion: nuevaDesc,
                fecha: nuevaFecha,
                idMaquina: maquina.id
              })
            })
            .then(response => {
              if (!response.ok) {
                return response.json().then(err => { throw err });
              }
              return response.json();
            })
            .then(data => {
              console.log("Mantenimiento guardado:", data.mensaje);

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
              contMants.insertBefore(nuevoMant, contMants.firstChild);

              inputFecha.value = "";
              inputDesc.value = "";
            })
            .catch(error => {
              console.error("Error al guardar mantenimiento:", error);
              alert("Error al guardar mantenimiento");
            });
          }
        });

        maq.appendChild(contMants);
        maq.appendChild(form);
        contMaquinas.appendChild(maq);
      });

      suc.appendChild(contMaquinas);
      contenedor.appendChild(suc);
    });
  })
  .catch(error => {
    console.error("Error al cargar sucursales:", error);
    contenedor.innerHTML = `<p>Error al cargar datos: ${error.message}</p>`;
  });
