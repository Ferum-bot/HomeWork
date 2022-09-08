"use strict";
function getFullName(user) {
    return `${user.firstName} ${user.surName}`;
}
const user = {
    // firstName: 'First name',
    surName: 'Sur name',
    city: 'Moscow',
    age: 20
};
getFullName(user);
