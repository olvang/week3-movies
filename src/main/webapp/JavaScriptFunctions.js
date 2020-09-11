console.log('--- Javascript Functions ---')
//Observe: no return type, no type on parameters
 //1
function add(n1, n2){
   return n1 +n2;
};

var sub = function(n1,n2){
  return n1 - n2;
} ;

var cb = function(n1,n2, callback){
try {
    if(n1 === "number") throw new Error('n1 is undefined, or is not a number!');
    if(n2 === "number") throw new Error('n2 is undefined, or is not a number!');
    if(callback  === "function") throw new Error('callback is undefined or is not a function!');
} catch (e) {
  console.error(e.name + ': ' + e.message)
}
  return "Result from the two numbers: "+n1+"+"+n2+"="+callback(n1,n2);
};

//2
console.log( add(1,2) )     // What will this print?
console.log( add )          // What will it print and what does add represent?
console.log( add(1,2,3) ) ; // What will it print
console.log( add(1) );      // What will it print     
console.log( cb(3,3,add) ); // What will it print
console.log( cb(4,3,sub) ); // What will it print
//console.log(cb(3,3,add())); // What will it print (and what was the problem)
console.log(cb(3,"hh",add));// What will it print

//3
cb = function(n1,n2, callback){
try {
    if(n1 === "number") throw new Error('n1 is undefined, or is not a number!');
    if(n2 === "number") throw new Error('n2 is undefined, or is not a number!');
    if(callback  === "function") throw new Error('callback is undefined or is not a function!');
    return "Result from the two numbers: "+n1+"+"+n2+"="+callback(n1,n2);
} catch (e) {
  console.error(e.name + ': ' + e.message)
}
};

console.log(cb(3,3,add())); // What will it print (and what was the problem)


//4
var mul = function(n1,n2){
  return n1 * n2;
} ;

console.log(cb(3,3,mul));

//5
var anonymous = function (n1, n2) {return n1 / n2;};
console.log(cb(4,3,anonymous));