package com.skysoft.vaultlogic.contracts;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.contracts.SmartContractEvent;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.5.0.
 */
public class ASessionManager extends Contract {
    private static final String BINARY = "0x";

    public static final String FUNC_CREATESESSION = "createSession";

    public static final String FUNC_CLOSESESSION = "closeSession";

    public static final String FUNC_CONFIRMCLOSE = "confirmClose";

    public static final String FUNC_ISACTIVE = "isActive";

    public static final String FUNC_ISHASACTIVECASHIN = "isHasActiveCashIn";

    public static final String FUNC_ACTIVATE = "activate";

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
    }

    protected ASessionManager(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected ASessionManager(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<TransactionReceipt> createSession(BigInteger sessionId, BigInteger appId, String xToken) {
        final Function function = new Function(
                FUNC_CREATESESSION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(sessionId), 
                new org.web3j.abi.datatypes.generated.Uint256(appId), 
                new org.web3j.abi.datatypes.Utf8String(xToken)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> closeSession(BigInteger sessionId) {
        final Function function = new Function(
                FUNC_CLOSESESSION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(sessionId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> confirmClose(BigInteger sessionId) {
        final Function function = new Function(
                FUNC_CONFIRMCLOSE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(sessionId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Boolean> isActive(BigInteger sessionId) {
        final Function function = new Function(FUNC_ISACTIVE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(sessionId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<Boolean> isHasActiveCashIn(BigInteger _sessionId) {
        final Function function = new Function(FUNC_ISHASACTIVECASHIN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_sessionId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<TransactionReceipt> activate(BigInteger _sessionId) {
        final Function function = new Function(
                FUNC_ACTIVATE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_sessionId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static RemoteCall<ASessionManager> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ASessionManager.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<ASessionManager> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ASessionManager.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static ASessionManager load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new ASessionManager(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static ASessionManager load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ASessionManager(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public interface ASessionManagerEvent extends SmartContractEvent {
    }
}
