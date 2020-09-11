var boys = ["Peter", "lars", "Ole"];
console.log('a: boys: ' + boys);
var girls = ["Janne", "hanne", "Sanne"];
console.log('a: girls: ' + girls);

var all = boys.concat(girls);
console.log('b: all: ' + all);

console.log('c1: ' + all.join());
console.log('c2: ' + all.join('-'));

all.push('Lone', 'Gitte');
console.log('d: ' + all);

all.unshift('Hans', 'Kurt');
console.log('e: ' + all);


all.shift();
console.log('f: ' + all);

all.pop();
console.log('g: ' + all);

all.splice(3,2);
console.log('h: ' + all);

all.reverse();
console.log('i: ' + all);

all.sort();
console.log('j: ' + all);

all.sort(function (a, b) {
    return a.toLowerCase().localeCompare(b.toLowerCase());
});
console.log('k: ' + all);

all = all.map(name => name.charAt(0).toUpperCase() + name.slice(1));
console.log('l: ' + all);


const filtered = all.filter(name => name.charAt(0) === 'L');
console.log('m: ' + filtered)