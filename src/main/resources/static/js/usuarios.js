// Call the dataTables jQuery plugin
$(document).ready(function() {
	
  cargarUsuarios();
  	
  $('#usuarios').DataTable();
});

async function cargarUsuarios() {
	const response = await fetch('api/usuarios', {
		method : "GET",
		headers : {
			'Accept' : 'application/json',
			'Content-Type' : 'application/json'
		},
	});
    const usuarios = await response.json();
    
    let listadoHTML = '';
    
    for (const usuario of usuarios) {
		
		let botonEliminar = `
		<a 
		href="#" 
		onclick="eliminarUsuario(${usuario.id})"
		class="btn btn-danger btn-circle btn-sm">
			<i class="fas fa-trash"></i>
		</a>
		`;
		
		let usuarioHTML = `
		<tr>
			<td>${usuario.id}</td>
			<td>${usuario.nombre} ${usuario.apellido}</td>
			<td>${usuario.email}</td>
			<td>${usuario.telefono}</td>
			<td>
				${botonEliminar}
			</td>
		</tr>`;
		
		listadoHTML+=usuarioHTML;
	}
	
	document.querySelector('#usuarios tbody').outerHTML=listadoHTML;
}

async function eliminarUsuario(id) {
	if (!confirm('¿Desea eliminar este usuario?')) {
		return;
	}
	
	const request = await fetch(`api/usuarios/${id}`, {
		method : 'DELETE',
		headers : {
			'Accept' : 'application/json',
			'Content-Type' : 'application/json'
			}
	})
	
	location.reload();
}