$(document).ready(() => {
	document.querySelector("#btnRegistrarse").addEventListener("click", registrarUsuario)
});

async function registrarUsuario() {
	let datos = {};
	
	datos = {
		nombre : document.querySelector("#txtNombre").value,
		apellido : document.querySelector("#txtApellido").value,
		email : document.querySelector("#txtEmail").value,
		telefono : document.querySelector("#txtTelefono").value,
		password : document.querySelector("#txtPassword").value
	};
	
	let passwordConfimacion = document.querySelector("#txtRepetirPassword").value;
	
	if (passwordConfimacion !== datos.password) {
		alert("La contraseña es incorrecta");
		return;
	}
	
	const request = await fetch('api/usuarios', {
		method : 'POST',
		headers : {
			'Accept' : 'application/json',
			'Content-Type' : 'application/json'
		},
		body : JSON.stringify(datos)
	});
	
	//const usuarios = await request.json();
	
	alert('Usuario registrado con éxito!');
	
	window.location.href = 'login.html';
}