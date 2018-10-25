module.exports = {
    networks: {
        development: {
            host: "127.0.0.1",
            port: 7545,
            network_id: "*"
        },
        quorum: {
            host: "35.188.28.38",
            port: 22000,
            network_id: "*",
            gasPrice: 0,
            gas: 4500000
        },
        quorum_local: {
            host: "127.0.0.1",
            port: 22000,
            network_id: "37609",
            gasPrice: 0,
            gas: 45000000
        }
    },
    solc: {
        optimizer: {
            enabled: true,
            runs: 200
        }
    }
};