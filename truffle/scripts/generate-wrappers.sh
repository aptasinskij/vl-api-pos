#!/usr/bin/env bash
web3j truffle generate build/contracts/ApplicationManager.json -p com.skysoft.vaultlogic.contracts -o ../contract-wrappers/src/main/java
web3j truffle generate build/contracts/ApplicationStorage.json -p com.skysoft.vaultlogic.contracts -o ../contract-wrappers/src/main/java
web3j truffle generate build/contracts/SessionManager.json -p com.skysoft.vaultlogic.contracts -o ../contract-wrappers/src/main/java
web3j truffle generate build/contracts/CapitalHero.json -p com.skysoft.vaultlogic.contracts -o ../contract-wrappers/src/main/java
web3j truffle generate build/contracts/CashInOracle.json -p com.skysoft.vaultlogic.contracts -o ../contract-wrappers/src/main/java
web3j truffle generate build/contracts/CashInStorage.json -p com.skysoft.vaultlogic.contracts -o ../contract-wrappers/src/main/java
web3j truffle generate build/contracts/KioskStorage.json -p com.skysoft.vaultlogic.contracts -o ../contract-wrappers/src/main/java
web3j truffle generate build/contracts/SessionOracle.json -p com.skysoft.vaultlogic.contracts -o ../contract-wrappers/src/main/java
web3j truffle generate build/contracts/SessionStorage.json -p com.skysoft.vaultlogic.contracts -o ../contract-wrappers/src/main/java
web3j truffle generate build/contracts/ParameterStorage.json -p com.skysoft.vaultlogic.contracts -o ../contract-wrappers/src/main/java
web3j truffle generate build/contracts/CameraOracle.json -p com.skysoft.vaultlogic.contracts -o ../contract-wrappers/src/main/java
web3j truffle generate build/contracts/PrinterOracle.json -p com.skysoft.vaultlogic.contracts -o ../contract-wrappers/src/main/java
git add ../contract-wrappers/src/main/java*