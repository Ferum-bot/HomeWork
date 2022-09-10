"use strict";
function signIn(id) {
    console.log(id);
    if (typeof id === 'string') {
        console.log(id.toLocaleLowerCase());
    }
}
signIn(1);
signIn('Hello world');
function signWithAuth(url, method) {
    return -1;
}
function isString(input) {
    return typeof input === 'string';
}
