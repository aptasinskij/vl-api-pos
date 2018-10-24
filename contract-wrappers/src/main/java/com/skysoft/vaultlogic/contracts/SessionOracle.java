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
import org.web3j.abi.datatypes.Utf8String;
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
    private static final String BINARY = "0x608060405234801561001057600080fd5b506040516020806107cf8339810160408181529151828201909252600e8082527f73657373696f6e2d6f7261636c650000000000000000000000000000000000006020830190815260008054600160a060020a0319163317815584939092909161007d9160019190610433565b505081600160a060020a031663713b563f6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156100d657600080fd5b505af11580156100ea573d6000803e3d6000fd5b505050506040513d602081101561010057600080fd5b505160038054600160a060020a031916600160a060020a03928316179055604080517fd0496d6a00000000000000000000000000000000000000000000000000000000815290519184169163d0496d6a916004808201926020929091908290030181600087803b15801561017357600080fd5b505af1158015610187573d6000803e3d6000fd5b505050506040513d602081101561019d57600080fd5b505160028054600160a060020a031916600160a060020a03928316178082556040517f693ec85e00000000000000000000000000000000000000000000000000000000815260206004820190815260018054610100818316150260001901169490940460248301819052929094169363693ec85e9392909182916044909101908490801561026c5780601f106102415761010080835404028352916020019161026c565b820191906000526020600020905b81548152906001019060200180831161024f57829003601f168201915b505092505050602060405180830381600087803b15801561028c57600080fd5b505af11580156102a0573d6000803e3d6000fd5b505050506040513d60208110156102b657600080fd5b50519050600160a060020a038116156103395780600160a060020a03166341c0e1b56040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401600060405180830381600087803b15801561032057600080fd5b505af1158015610334573d6000803e3d6000fd5b505050505b600280546040517ff2c298be00000000000000000000000000000000000000000000000000000000815260206004820190815260018054600019818316156101000201169490940460248301819052600160a060020a039093169363f2c298be9390928291604490910190849080156103f35780601f106103c8576101008083540402835291602001916103f3565b820191906000526020600020905b8154815290600101906020018083116103d657829003601f168201915b505092505050600060405180830381600087803b15801561041357600080fd5b505af1158015610427573d6000803e3d6000fd5b505050505050506104ce565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061047457805160ff19168380011785556104a1565b828001600101855582156104a1579182015b828111156104a1578251825591602001919060010190610486565b506104ad9291506104b1565b5090565b6104cb91905b808211156104ad57600081556001016104b7565b90565b6102f2806104dd6000396000f3006080604052600436106100615763ffffffff7c010000000000000000000000000000000000000000000000000000000060003504166306fdde03811461006657806341c0e1b5146100f05780638da5cb5b14610107578063a3bfdf4714610145575b600080fd5b34801561007257600080fd5b5061007b61015d565b6040805160208082528351818301528351919283929083019185019080838360005b838110156100b557818101518382015260200161009d565b50505050905090810190601f1680156100e25780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b3480156100fc57600080fd5b506101056101ea565b005b34801561011357600080fd5b5061011c610274565b6040805173ffffffffffffffffffffffffffffffffffffffff9092168252519081900360200190f35b34801561015157600080fd5b50610105600435610290565b60018054604080516020600284861615610100026000190190941693909304601f810184900484028201840190925281815292918301828280156101e25780601f106101b7576101008083540402835291602001916101e2565b820191906000526020600020905b8154815290600101906020018083116101c557829003601f168201915b505050505081565b60005473ffffffffffffffffffffffffffffffffffffffff16321461027057604080517f08c379a000000000000000000000000000000000000000000000000000000000815260206004820152601260248201527f6f6e6c79206f776e657220616c6c6f7765640000000000000000000000000000604482015290519081900360640190fd5b6000ff5b60005473ffffffffffffffffffffffffffffffffffffffff1681565b6040805182815290517f71c2e31dde72b491e4b02270b0b15a6a4fc0dd6d696c8d0f43604a92ff1091939181900360200190a1505600a165627a7a72305820770ce1f579698c386ee2fce5c240f7ef464902fc3ca323adaf5ac7d1a889794c0029";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_KILL = "kill";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_CLOSESESSION = "closeSession";

    public static final Event CLOSESESSION_EVENT = new Event("CloseSession", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
        _addresses.put("84107", "0x6abeddb5b5d6d116fb522964ed7d097c1d324d29");
        _addresses.put("37609", "0x43e44d30526a7f69deaee442e1def736374ad732");
        _addresses.put("5777", "0xa11a3ee59bf38ae1d91bca20c61dc8930401f629");
    }

    protected SessionOracle(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected SessionOracle(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<String> name() {
        final Function function = new Function(FUNC_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> kill() {
        final Function function = new Function(
                FUNC_KILL, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> owner() {
        final Function function = new Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public static RemoteCall<SessionOracle> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _config) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_config)));
        return deployRemoteCall(SessionOracle.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static RemoteCall<SessionOracle> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _config) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_config)));
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
