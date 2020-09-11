
document.getElementById("all").addEventListener("click", function(event){
  event.preventDefault();
  let url = "http://localhost:8080/jpareststarter/api/movies/all";
  
  fetch(url)
  .then(res => res.json()) //in flow1, just do it
  .then(data => {
   // Inside this callback, and only here, the response data is available
   tableBody = createTableBody(data);
   document.getElementById('tbody').innerHTML = tableBody;
});

});

document.getElementById("single").addEventListener("click", function(event){
  event.preventDefault();
  let url = "http://localhost:8080/jpareststarter/api/movies/" + document.getElementById('id').value;
  
  fetch(url)
  .then(res => res.json()) //in flow1, just do it
  .then(movie => {
   var actors = "";
   if(movie.actors){
      actors += movie.actors.map(actor => actor.name);
   }
   tableBody = "<tr><td>" + movie.id + "</td>" + "<td>" + movie.title + "</td>" + "<td>" + movie.year + "</td>" + "<td>" + actors + "</td></tr>"
   document.getElementById('tbody').innerHTML = tableBody;
});

});


function createTableBody (data) {
    return  data.map(movie => "<tr><td>" + movie.id + "</td>" + "<td>" + movie.title + "</td>" + "<td>" + movie.year + "</td>" + "<td>" + movie.actors.map(actor => actor.name) + "</td></tr>").join('');
}