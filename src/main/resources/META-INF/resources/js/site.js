function unifyArray(data) {
    var obj = {};
    data.map(x => ({ [x.name]: x.value })).forEach(x => Object.assign(obj, x));
    return obj;
}