export const Timer = {
    start,
    stop,
    isActive
};

let startTime, stopTime, requestType;

function isActive() {
    return !!startTime;
}
function start(value) {
    startTime = new Date();
    requestType = value;
}
function stop() {
    stopTime = new Date();
    const resultDate = stopTime - startTime;
    console.log(`Time to ${requestType}: ${resultDate} milliseconds`);

    startTime = '';
    requestType = '';

    return resultDate;
}