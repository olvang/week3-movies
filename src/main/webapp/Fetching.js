
document.getElementById("all").addEventListener("click", function(event){
  event.preventDefault();
  let url = "https://jsonplaceholder.typicode.com/users/";
  
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
  let url = "https://jsonplaceholder.typicode.com/users/" + document.getElementById('uid').value;
  
  fetch(url)
  .then(res => res.json()) //in flow1, just do it
  .then(user => {
   // Inside this callback, and only here, the response data is available
   tableBody = "<tr><td>" + user.id + "</td>" + "<td>" + user.name + "</td>" + "<td>" + user.username + "</td>" + "<td>" + user.email + "</td></tr>"
   document.getElementById('tbody').innerHTML = tableBody;
});

});


function createTableBody (data) {
    return  data.map(user => "<tr><td>" + user.id + "</td>" + "<td>" + user.name + "</td>" + "<td>" + user.username + "</td>" + "<td>" + user.email + "</td></tr>").join('');
}