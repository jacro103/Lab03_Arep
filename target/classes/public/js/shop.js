function loadGetMsg() {
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function () {
        let response = JSON.parse(this.responseText);

        // if data object has atributtes delete all children of the div getrespmsg
        if (response.controls.length !== 0) {
            document.getElementById("getrespmsg").innerHTML = "";
            document.getElementById("getrespmsg").classList.add("getrespmsg");
            let totalPrice = 0;
            // for each control that has games, price and name create an article
            response.controls.forEach(control => {
                let controlArticle = document.createElement("article");
                let divgames = document.createElement("div");
                controlArticle.classList.add("control-details");
                divgames.classList.add("games");
                // for each game in response.games
                control.games.forEach(game => {
                    let gameDiv = document.createElement("div");
                    gameDiv.classList.add("game-details");
                    gameDiv.innerHTML = `
                        <img src="./images/${game}.png" alt="${game}" width="25px" height="25px">
                        <p>${game}</p>
                    `;
                    divgames.appendChild(gameDiv);
                });
                // add to article game div and title and price
                controlArticle.innerHTML += `
                    <h2 class="control-title">${control.name}</h2>
                    <p class="control-price">Price: ${control.price} COP</p>
                `;
                totalPrice += control.price;
                controlArticle.appendChild(divgames);
                // add article to DOM
                document.getElementById("getrespmsg").appendChild(controlArticle);
            });
            let buttonPrice = document.createElement("button");
            buttonPrice.classList.add("shop-button");
            buttonPrice.innerHTML = `
                <p>Pay COP $ ${totalPrice}</p>
            `;
            buttonPrice.onclick = function() {
                window.location.href = "index.html";
            };
            document.getElementById("hero_section").appendChild(buttonPrice);
        }
        
    };
    xhttp.open("GET", "/component/controls");
    xhttp.send();
}