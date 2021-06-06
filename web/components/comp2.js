class comp2 {
  constructor(user) {
    this.user = user;
    this.voto;
  }

  render = () => {
    let component = document.createElement("div");
    component.className = "colPublicacion";

    let namey = document.createElement("div");
    namey.className = "namey";

    let prom = document.createElement("h2");
    prom.className = "prom";

    let peliculaN = document.createElement("h3");
    peliculaN.className = "tituloPelicula";
    peliculaN.innerHTML = this.user.nombre;

    let stars = document.createElement("div");

    stars.className = "rate";

    let p = document.createElement("p");
    p.id = "texto";
    p.innerHTML = "Gracias por participar";

    stars.appendChild(p);

    database.ref("parcial2/votos/" + this.user.nombre).on("value", (data) => {
      let pel = data.val();

      if (pel === null) {
        prom.innerHTML = "0.0";
      } else {
        let number = pel.votos;
        prom.innerHTML = number.toFixed(1);
      }
    });

    namey.appendChild(peliculaN);
    namey.appendChild(stars);
    component.appendChild(namey);
    component.appendChild(prom);

    return component;
  };
}
