package com.skysoft.vaultlogic.contracts;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.contracts.SmartContractEvent;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.5.0.
 */
public class SessionOracle extends Contract {
    private static final String BINARY = "0x608060405234801561001057600080fd5b5060405160208061044f8339810180604052810190808051906020019092919050505080336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060008173ffffffffffffffffffffffffffffffffffffffff1614151515610103576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260168152602001807f53797374656d2073746174652076696f6c6174696f6e0000000000000000000081525060200191505060405180910390fd5b80600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16631e59c5296101996102a3640100000000026401000000009004565b306040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825284818151815260200191508051906020019080838360005b8381101561023757808201518184015260208101905061021c565b50505050905090810190601f1680156102645780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b15801561028457600080fd5b505af1158015610298573d6000803e3d6000fd5b5050505050506102e0565b60606040805190810160405280600e81526020017f73657373696f6e2d6f7261636c65000000000000000000000000000000000000815250905090565b610160806102ef6000396000f30060806040526004361061004c576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680638da5cb5b14610051578063a3bfdf47146100a8575b600080fd5b34801561005d57600080fd5b506100666100d5565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156100b457600080fd5b506100d3600480360381019080803590602001909291905050506100fa565b005b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b7f71c2e31dde72b491e4b02270b0b15a6a4fc0dd6d696c8d0f43604a92ff109193816040518082815260200191505060405180910390a1505600a165627a7a72305820e1bb2a447bef2495bd4c3367747a1cc32d64d6ffdc50bd9aaafe1451cd80b5c60029";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_CLOSESESSION = "closeSession";

    public static final Event CLOSESESSION_EVENT = new Event("CloseSession", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
        _addresses.put("77142", "0x030bfc1f8e4a60782058cc834f444bb455d7e258");
    }

    protected SessionOracle(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected SessionOracle(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<String> owner() {
        final Function function = new Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public static RemoteCall<SessionOracle> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String regAddr) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(regAddr)));
        return deployRemoteCall(SessionOracle.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static RemoteCall<SessionOracle> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String regAddr) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(regAddr)));
        return deployRemoteCall(SessionOracle.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public List<CloseSessionEventResponse> getCloseSessionEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CLOSESESSION_EVENT, transactionReceipt);
        ArrayList<CloseSessionEventResponse> responses = new ArrayList<CloseSessionEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            CloseSessionEventResponse typedResponse = new CloseSessionEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.sessionId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<CloseSessionEventResponse> closeSessionEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, CloseSessionEventResponse>() {
            @Override
            public CloseSessionEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CLOSESESSION_EVENT, log);
                CloseSessionEventResponse typedResponse = new CloseSessionEventResponse();
                typedResponse.log = log;
                typedResponse.sessionId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<CloseSessionEventResponse> closeSessionEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CLOSESESSION_EVENT));
        return closeSessionEventObservable(filter);
    }

    public RemoteCall<TransactionReceipt> closeSession(BigInteger sessionId) {
        final Function function = new Function(
                FUNC_CLOSESESSION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(sessionId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static SessionOracle load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new SessionOracle(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static SessionOracle load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new SessionOracle(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public interface SessionOracleEvent extends SmartContractEvent {
    }

    public static class CloseSessionEventResponse implements SessionOracleEvent {
        public Log log;

        public BigInteger sessionId;
    }
}
