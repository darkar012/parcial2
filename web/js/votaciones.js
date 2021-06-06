const database = firebase.database();
const auth = firebase.auth();

const votaciones = document.getElementById("peliculas");
const logout = document.getElementById("logOut");


auth.onAuthStateChanged((user) => {
  if (user !== null) {
      console.log(user.uid);
    usuario = user.uid;
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
    let pelicula = new comp2(valor);
    votaciones.appendChild(pelicula.render());
  });
});