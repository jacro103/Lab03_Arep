function loadPostMsg(name){
    let url = "/action/gamestore?name=" + name.value;

    fetch (url, {method: 'POST'})
        .then(x => x.text())
        .then(y => makeControl(y));
}

function makeControl(jsonResponse){
    let response = JSON.parse(jsonResponse);
    let controlArticle = document.createElement("article");
    let divGames= document.createElement("div");
    controlArticle.classList.add("control-details");
    divGames.classList.add("games");

    console.log(response);
    response.games.forEach(game => {
        console.log(game);
        let gameDiv = document.createElement("div");
        gameDiv.classList.add("game-details");
        gameDiv.innerHTML = `
            <img src="./images/${game}.png" alt="${game}" width="25px" height="25px">
            <p>${game}</p>
        `;
        divGames.appendChild(gameDiv);
    });
   
    controlArticle.innerHTML += `
        <img class="control-image" src="./images/control.jpg" alt="control">
        <h2 class="control-title">${response.name}</h2>
        <p class="control-price">Price: ${response.price} COP</p>
    `;
    controlArticle.appendChild(divGames);
    // add article to DOM
    document.getElementById("postrespmsg").innerHTML = "";
    document.getElementById("postrespmsg").appendChild(controlArticle);

}