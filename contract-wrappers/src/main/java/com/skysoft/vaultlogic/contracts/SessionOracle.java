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
    private static final String BINARY = "0x60806040523480156200001157600080fd5b5060405160208062000dde8339810160408181529151828201909252600e8082527f73657373696f6e2d6f7261636c650000000000000000000000000000000000006020830190815260008054600160a060020a031916331790558392916200007d91600191620002b8565b505080600160a060020a031663713b563f6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015620000d757600080fd5b505af1158015620000ec573d6000803e3d6000fd5b505050506040513d60208110156200010357600080fd5b505160038054600160a060020a031916600160a060020a03928316179055604080517fd0496d6a00000000000000000000000000000000000000000000000000000000815290519183169163d0496d6a916004808201926020929091908290030181600087803b1580156200017757600080fd5b505af11580156200018c573d6000803e3d6000fd5b505050506040513d6020811015620001a357600080fd5b505160028054600160a060020a031916600160a060020a03928316178082556040517ff2c298be00000000000000000000000000000000000000000000000000000000815260206004820190815260018054610100818316150260001901169490940460248301819052929094169363f2c298be93929091829160449091019084908015620002765780601f106200024a5761010080835404028352916020019162000276565b820191906000526020600020905b8154815290600101906020018083116200025857829003601f168201915b505092505050600060405180830381600087803b1580156200029757600080fd5b505af1158015620002ac573d6000803e3d6000fd5b5050505050506200035d565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10620002fb57805160ff19168380011785556200032b565b828001600101855582156200032b579182015b828111156200032b5782518255916020019190600101906200030e565b50620003399291506200033d565b5090565b6200035a91905b8082111562000339576000815560010162000344565b90565b610a71806200036d6000396000f30060806040526004361061008d5763ffffffff7c010000000000000000000000000000000000000000000000000000000060003504166306fdde038114610092578063081907261461011c57806341c0e1b5146101bc57806352bfd1ce146101d15780638da5cb5b146101e957806398f472171461021a578063a0e36aba14610232578063b260c42a1461024a575b600080fd5b34801561009e57600080fd5b506100a7610262565b6040805160208082528351818301528351919283929083019185019080838360005b838110156100e15781810151838201526020016100c9565b50505050905090810190601f16801561010e5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561012857600080fd5b50604080516020600460443581810135601f81018490048402850184019095528484526101ba94823594602480359536959460649492019190819084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a9998810197919650918201945092508291508401838280828437509497506102ef9650505050505050565b005b3480156101c857600080fd5b506101ba610540565b3480156101dd57600080fd5b506101ba6004356105bd565b3480156101f557600080fd5b506101fe610730565b60408051600160a060020a039092168252519081900360200190f35b34801561022657600080fd5b506101ba60043561073f565b34801561023e57600080fd5b506101ba600435610775565b34801561025657600080fd5b506101ba6004356108cd565b60018054604080516020600284861615610100026000190190941693909304601f810184900484028201840190925281815292918301828280156102e75780601f106102bc576101008083540402835291602001916102e7565b820191906000526020600020905b8154815290600101906020018083116102ca57829003601f168201915b505050505081565b600254604080518082018252600f8152600080516020610a268339815191526020808301918252925160e160020a63349f642f02815260048101938452825160248201528251600160a060020a039095169463693ec85e949283926044019180838360005b8381101561036c578181015183820152602001610354565b50505050905090810190601f1680156103995780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b1580156103b857600080fd5b505af11580156103cc573d6000803e3d6000fd5b505050506040513d60208110156103e257600080fd5b50516040517f081907260000000000000000000000000000000000000000000000000000000081526004810186815260248201869052608060448301908152855160848401528551600160a060020a03909416936308190726938993899389938993606481019160a490910190602087019080838360005b8381101561047257818101518382015260200161045a565b50505050905090810190601f16801561049f5780820380516001836020036101000a031916815260200191505b50838103825284518152845160209182019186019080838360005b838110156104d25781810151838201526020016104ba565b50505050905090810190601f1680156104ff5780820380516001836020036101000a031916815260200191505b509650505050505050600060405180830381600087803b15801561052257600080fd5b505af1158015610536573d6000803e3d6000fd5b5050505050505050565b600054600160a060020a031632146105b957604080517f08c379a000000000000000000000000000000000000000000000000000000000815260206004820152601260248201527f6f6e6c79206f776e657220616c6c6f7765640000000000000000000000000000604482015290519081900360640190fd5b6000ff5b600254604080518082018252600f8152600080516020610a268339815191526020808301918252925160e160020a63349f642f02815260048101938452825160248201528251600160a060020a039095169463693ec85e949283926044019180838360005b8381101561063a578181015183820152602001610622565b50505050905090810190601f1680156106675780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b15801561068657600080fd5b505af115801561069a573d6000803e3d6000fd5b505050506040513d60208110156106b057600080fd5b5051604080517f882d5f7a000000000000000000000000000000000000000000000000000000008152600481018490529051600160a060020a039092169163882d5f7a9160248082019260009290919082900301818387803b15801561071557600080fd5b505af1158015610729573d6000803e3d6000fd5b5050505050565b600054600160a060020a031681565b6040805182815290517f71c2e31dde72b491e4b02270b0b15a6a4fc0dd6d696c8d0f43604a92ff1091939181900360200190a150565b600254604080518082018252600f8152600080516020610a268339815191526020808301918252925160e160020a63349f642f02815260048101938452825160248201528251600160a060020a039095169463693ec85e949283926044019180838360005b838110156107f25781810151838201526020016107da565b50505050905090810190601f16801561081f5780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b15801561083e57600080fd5b505af1158015610852573d6000803e3d6000fd5b505050506040513d602081101561086857600080fd5b5051604080517fc61307af000000000000000000000000000000000000000000000000000000008152600481018490529051600160a060020a039092169163c61307af9160248082019260009290919082900301818387803b15801561071557600080fd5b600254604080518082018252600f8152600080516020610a268339815191526020808301918252925160e160020a63349f642f02815260048101938452825160248201528251600160a060020a039095169463693ec85e949283926044019180838360005b8381101561094a578181015183820152602001610932565b50505050905090810190601f1680156109775780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b15801561099657600080fd5b505af11580156109aa573d6000803e3d6000fd5b505050506040513d60208110156109c057600080fd5b5051604080517fb260c42a000000000000000000000000000000000000000000000000000000008152600481018490529051600160a060020a039092169163b260c42a9160248082019260009290919082900301818387803b15801561071557600080fd0073657373696f6e2d6d616e616765720000000000000000000000000000000000a165627a7a723058203556c5803ddf80ff10decf47cf88cad1e36d0d6f24f3c1d9a5b674deba90b7570029";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_KILL = "kill";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_CREATESESSION = "createSession";

    public static final String FUNC_ACTIVATE = "activate";

    public static final String FUNC_ONNEXTCLOSESESSION = "onNextCloseSession";

    public static final String FUNC_SUCCESSCLOSE = "successClose";

    public static final String FUNC_FAILCLOSE = "failClose";

    public static final Event CLOSESESSION_EVENT = new Event("CloseSession", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
        _addresses.put("4447", "0x74e3fc764c2474f25369b9d021b7f92e8441a2dc");
        _addresses.put("89354", "0x1b7abc500166541b0572f38a15cd6d0310e071a3");
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

    public RemoteCall<TransactionReceipt> createSession(BigInteger _sessionId, BigInteger _appId, String _xToken, String _kioskId) {
        final Function function = new Function(
                FUNC_CREATESESSION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_sessionId), 
                new org.web3j.abi.datatypes.generated.Uint256(_appId), 
                new org.web3j.abi.datatypes.Utf8String(_xToken), 
                new org.web3j.abi.datatypes.Utf8String(_kioskId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> activate(BigInteger _sessionId) {
        final Function function = new Function(
                FUNC_ACTIVATE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_sessionId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> onNextCloseSession(BigInteger sessionId) {
        final Function function = new Function(
                FUNC_ONNEXTCLOSESESSION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(sessionId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> successClose(BigInteger _sessionId) {
        final Function function = new Function(
                FUNC_SUCCESSCLOSE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_sessionId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> failClose(BigInteger _sessionId) {
        final Function function = new Function(
                FUNC_FAILCLOSE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_sessionId)), 
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
