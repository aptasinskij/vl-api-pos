const convertToNumber = (structure, disableAddressConvert) => { // convert props/elements no number
    if (structure instanceof Object && !Array.isArray(structure)) { // if Object
        for (let key in structure) {
            if (disableAddressConvert) {
                if (typeof structure[key] !== 'string') {
                    structure[key] = Number(structure[key]);
                }
            } else {
                if (structure.hasOwnProperty(key)) {
                    if (typeof structure[key] === 'string' && structure[key].startsWith('0x')) { // allow address converting
                        structure[key] = Number(structure[key]);
                    } else if (typeof structure[key] !== 'string') {
                        structure[key] = Number(structure[key]);
                    }
                }
            }
        }
    } else if (Array.isArray(structure)) { // if Array
        structure = structure.map(item => {
            if (disableAddressConvert) {
                if (typeof item !== 'string') {
                    return Number(item);
                } else {
                    return item;
                }
            } else {
                if (typeof item === 'string' && item.startsWith('0x')) { // allow address converting
                    return Number(item);
                } else if (typeof item !== 'string') {
                    return Number(item);
                } else {
                    return item;
                }
            }
        });
    }
    return structure;
};

const sleep = millis => {
    const t = (new Date()).getTime();
    let i = 0;
    while (((new Date()).getTime() - t) < millis) i++;
};

exports.convertToNumber = convertToNumber;
exports.sleep = sleep;