const database = firebase.database();
const auth = firebase.auth();

const nombre = document.getElementById("nombre");
const apellido = document.getElementById("apellido");
const ciudad = document.getElementById("ciudad");
const correo = document.getElementById("correo");
const password = document.getElementById("password");
const repassword = document.getElementById("repassword");
const registrar = document.getElementById("resBtn");

var isSigningUp = false;



auth.onAuthStateChanged((user) => {
console.log(user.uid);
  if (user !== null) {
    if (isSigningUp) {
      let userDb = {
        id: user.uid,
        nombre: nombre.value,
        apellido: apellido.value,
        ciudad: ciudad.value,
        correo: correo.value,
      };

      database
        .ref("parcial2/users/" + userDb.id)
        .set(userDb)
        .then(() => {
          window.location.href = "index.html";
        });

        nombre.value="";
        apellido.value="";
        ciudad.value="";
        correo.value="";
        password.value="";
        repassword.value="";
    } else {
        window.location.href = "index.html";
      }
  }
});

registrar.addEventListener("click", () => {
    let correou = correo.value;
  let nombreu = nombre.value;
  let ciudadu = ciudad.value;
  let apellidou = apellido.value;
  let passwordu = password.value;
  let repasswordu = repassword.value;
  isSigningUp = true;
  if (
    correou === "" ||
    nombreu === "" ||
    apellidou === "" ||
    ciudadu === "" ||
    passwordu === "" ||
    repasswordu === ""
  ) {
    alert("Complete todos los espacios");
    return;
  } else {
    if (passwordu !== repasswordu) {
      alert("La contraseña no coincide");
      return;
    } else {
      auth
        .createUserWithEmailAndPassword(correo.value, password.value)
        .catch((error) => {
          alert(error.message);
          return;
        });
    }
  }
});
