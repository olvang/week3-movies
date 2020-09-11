
document.getElementById("all").addEventListener("click", function (event) {
    event.preventDefault();
    
    getAndSetAll();
});

document.getElementById("single").addEventListener("click", function (event) {
    event.preventDefault();
    let url = "https://vangomango.dk/week3-devops-starter/api/movies/" + document.getElementById('id').value;

    fetch(url)
            .then(res => res.json()) //in flow1, just do it
            .then(movie => {
                var actors = "";
                if (movie.actors) {
                    actors += movie.actors.map(actor => actor.name);
                }
                tableBody = "<tr><td>" + movie.id + "</td>" + "<td>" + movie.title + "</td>" + "<td>" + movie.year + "</td>" + "<td>" + actors + "</td></tr>"
                document.getElementById('tbody').innerHTML = tableBody;
            });

});



document.getElementById("addMovie").addEventListener("click", function (event) {
    event.preventDefault();
    let url = "https://vangomango.dk/week3-devops-starter/api/movies/" + document.getElementById('title').value + "/" + document.getElementById('year').value;

    fetch(url, {
        method: 'post'}
    )
            .then(movie => {
                getAndSetAll();
            });

});

document.getElementById("addActorToMovie").addEventListener("click", function (event) {
    event.preventDefault();
    let url = "https://vangomango.dk/week3-devops-starter/api/movies/actor/" + document.getElementById('mid').value + "/" + document.getElementById('aname').value + "/" + document.getElementById('age').value;

    fetch(url, {
        method: 'post'}
    )
            .then(movie => {
                getAndSetAll();
            });

});



function createTableBody(data) {
    return  data.map(movie => "<tr><td>" + movie.id + "</td>" + "<td>" + movie.title + "</td>" + "<td>" + movie.year + "</td>" + "<td>" + movie.actors.map(actor => actor.name) + "</td></tr>").join('');
}


function getAndSetAll(data) {
    let url = "https://vangomango.dk/week3-devops-starter/api/movies/all";

    fetch(url)
            .then(res => res.json()) //in flow1, just do it
            .then(data => {
                // Inside this callback, and only here, the response data is available
                tableBody = createTableBody(data);
                document.getElementById('tbody').innerHTML = tableBody;
            });
}

