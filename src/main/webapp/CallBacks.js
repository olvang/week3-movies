console.log('--- Callbacks (with map, filter and forEach) ---')
var names = ["Peter", "lars", "Ole", "Janne", "hanne", "Sanne", "Bo", "Oli"];

//1
const result1 = names.filter(name => name.length <= 3);
console.log('1: ' + result1);

//2
const result2 = names.map(name => name.toUpperCase());
console.log('2: ' + result2);

//3
function createListFromArray (array) {
    var string = "<ul>";
    array.map(name => string += "<li>" + name + "</li>");
    string += "</ul>";
    return string;
}
console.log('3: ' + createListFromArray(names));

//4
var cars = [
  { id: 1, year: 1997, make: 'Ford', model: 'E350', price: 3000 },
  { id: 2, year: 1999, make: 'Chevy', model: 'Venture', price: 4900 },
  { id: 3, year: 2000, make: 'Chevy', model: 'Venture', price: 5000 },
  { id: 4, year: 1996, make: 'Jeep', model: 'Grand Cherokee', price: 4799 },
  { id: 5, year: 2005, make: 'Volvo', model: 'V70', price: 44799 }
];

console.log('4a1: ', cars.filter(car => car.year > 1999));
console.log('4a2: ', cars.filter(car => car.make === "Volvo"));
console.log('4a3: ', cars.filter(car => car.price < 5000));

//4a
function createSqlFromArray (cars) {
    var string = "INSERT INTO cars (id,year,make,model,price) VALUES ";
    string += cars.map(car => '(' + car.id + ',' + car.year + ',' + car.make + ',' + car.model + ',' + car.price + ")");
    string += ";";
    return string;
}
console.log('4: ', createSqlFromArray(cars));


console.log('--- Asynchronous Callbacks ---');
var msgPrinter = function(msg,delay){
  setTimeout(function(){
    console.log(msg);
  },delay);
};
console.log("aaaaaaaaaa");
//msgPrinter ("bbbbbbbbbb",2000);
console.log("dddddddddd");
//msgPrinter ("eeeeeeeeee",1000);
console.log("ffffffffff");



console.log('--- this and constructor functions ---');
//1
//function Person(name){
//  this.name = name;
//  console.log("Name: "+ this.name);
//  setTimeout(function(){
//    console.log("Hi  "+this.name);  //Explain this
//  },2000);
//}
////call it like this (do it, even if you know it’s silly ;-)
//Person("Kurt Wonnegut");  //This calls the function
//console.log("I'm global: "+ name);  //Explain this


//2
//var p = new Person("Kurt Wonnegut");  //Create an instance using the constructor function
//console.log("I'm global: "+ name);  //What’s different ?


//3
//Store a reference to the outer this
function Person(name){
  this.name = name;
  var self = this;
  console.log("Name: "+ this.name);
  setTimeout(function(){
    console.log("Hi  "+self.name);  
  },2000);
}
//Using the bind(..) function
function Person(name){
  this.name = name;  
  console.log("Name: "+ this.name);
  setTimeout(function(){
    console.log("Hi  "+this.name);  
  }.bind(this),2000);
}

//var p = new Person("Kurt Wonnegut3");  //Create an instance using the constructor function
//console.log("I'm global: "+ name);  //What’s different ?


//4
var greeter = function(){
  console.log(this.message);
};
var comp1 = { message: "Hello World" };
var comp2 = { message: "Hi" };

var g1 = greeter.bind(comp1 );//We can store a reference, with a specific “this” to use
var g2 = greeter.bind(comp2 );//And here another “this”
setTimeout(g1,500);
setTimeout(g2,1000);

