const database = firebase.database();
const auth = firebase.auth();

const votaciones = document.getElementById("peliculas");
const logout = document.getElementById("logOut");
const votar = document.getElementById("votar");
const cargarVot = document.getElementById("cargarVotos");

votar.style.display = "none";

let arrayN = [];
let array = [];
let once = false;
let vote = false;
let usuario = "";

auth.onAuthStateChanged((user) => {
  if (user !== null) {
    console.log(user.uid);
    database.ref("parcial2/votosUser/" + user.uid).once("value", (data) => {
      let us = data.val();
      if (us === null) {
        usuario=user.uid;
      } else {
        window.location.href = "yaVoto.html";
      }
    });
  } else {
    window.location.href = "login.html";
  }
});

logout.addEventListener("click", () => {
  auth
    .signOut()
    .then(() => {
      window.location.href = "login.html";
    })
    .catch(() => {
      alert(error.message);
    });
});

database.ref("parcial2/películas").on("value", function (data) {
  votaciones.innerHTML = "";
  data.forEach((movie) => {
    let valor = movie.val();
    let pelicula = new component(valor);
    votaciones.appendChild(pelicula.render());
    cargarVot.addEventListener("click", () => {
      array[pelicula.user.Id] = pelicula.voto;
      arrayN[pelicula.user.Id] = pelicula.user.nombre;
      if (array.length == 5) {
        votar.style.display = "block";
        cargarVot.style.display = "none";
      }
    });
  });
});

votar.addEventListener("click", () => {
  if (
    array[0] == null ||
    array[1] == null ||
    array[2] == null ||
    array[3] == null ||
    array[4] == null
  ) {
    array = [];
    cargarVot.style.display = "block";
    votar.style.display = "none";
    alert("por favor, vote por todas las peliculas");

    return;
  } else {
   
        for (let i = 0; i < arrayN.length; i++) {
          database.ref("parcial2/votos/" + arrayN[i]).once("value", (data) => {
            let us = data.val();
            if (us == null) {
              let votanding = {
                id: arrayN[i],
                votos: parseInt(array[i], 10),
                total: 1,
              };
              database.ref("parcial2/votos/" + votanding.id).set(votanding);
            } else {
              let ar = parseInt(array[i], 10);
              let vot = parseInt(us.votos, 10);
              let t = parseInt(us.total, 10);
              let prom = (ar + (vot * t)) / (t + 1);

              let votanding = {
                id: arrayN[i],
                votos: prom,
                total: t + 1,
              };
              database.ref("parcial2/votos/" + votanding.id).set(votanding);
            }
          });
        }

        let userDb = {
          id: usuario,
          cruella: array[0],
          demon: array[1],
          conjuro: array[2],
          ruegapor: array[3],
          silencio: array[4],
        };

        database.ref("parcial2/votosUser/" + userDb.id).set(userDb);
        alert("gracias por participar");
        window.location.href = "yaVoto.html";
  }
});
