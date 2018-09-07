const convertToNumber = structure => { // convert props/elements no number
    if (structure instanceof Object && !Array.isArray(structure)) { // if Object
        for (let key in structure) {
            if (structure.hasOwnProperty(key)) {
                if (typeof structure[key] === 'string' && structure[key].startsWith('0x')) { // allow address converting
                    structure[key] = Number(structure[key]);
                } else if (typeof structure[key] !== 'string') {
                    structure[key] = Number(structure[key]);
                }
            }
        }
    } else if (Array.isArray(structure)) { // if Array
        structure = structure.map(item => {
            if (typeof item === 'string' && item.startsWith('0x')) { // allow address converting
                return Number(item);
            } else if (typeof item !== 'string') {
                return Number(item);
            } else {
                return item;
            }
        });
    }
    return structure;
};

exports.convertToNumber = convertToNumber;