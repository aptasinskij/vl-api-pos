package com.skysoft.vaultlogic.blockchain.contracts;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
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
public class CashInOracle extends Contract {
    private static final String BINARY = "0x608060405234801561001057600080fd5b50604051602080610909833981018060405281019080805190602001909291905050508060008173ffffffffffffffffffffffffffffffffffffffff16141515156100c3576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260168152602001807f53797374656d2073746174652076696f6c6174696f6e0000000000000000000081525060200191505060405180910390fd5b806000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16631e59c529610157610261640100000000026401000000009004565b306040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825284818151815260200191508051906020019080838360005b838110156101f55780820151818401526020810190506101da565b50505050905090810190601f1680156102225780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b15801561024257600080fd5b505af1158015610256573d6000803e3d6000fd5b50505050505061029e565b60606040805190810160405280600e81526020017f636173682d696e2d6f7261636c65000000000000000000000000000000000000815250905090565b61065c806102ad6000396000f30060806040526004361061006d576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680633d57b2a9146100725780635940c655146100b3578063596c8976146100e0578063882d5f7a14610117578063f479d37714610144575b600080fd5b34801561007e57600080fd5b506100b160048036038101908080359060200190929190803590602001909291908035906020019092919050505061017b565b005b3480156100bf57600080fd5b506100de600480360381019080803590602001909291905050506101c7565b005b3480156100ec57600080fd5b50610115600480360381019080803590602001909291908035906020019092919050505061028e565b005b34801561012357600080fd5b50610142600480360381019080803590602001909291905050506102d1565b005b34801561015057600080fd5b506101796004803603810190808035906020019092919080359060200190929190505050610398565b005b7fd5e06f715ea045b85ecbf99e39def109e0d16a5b56924190bcd2a388171d8ac483838360405180848152602001838152602001828152602001935050505060405180910390a1505050565b6102056040805190810160405280601581526020017f636173682d6368616e6e656c732d6d616e616765720000000000000000000000815250610468565b73ffffffffffffffffffffffffffffffffffffffff16635940c655826040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050600060405180830381600087803b15801561027357600080fd5b505af1158015610287573d6000803e3d6000fd5b5050505050565b7fe118fe67faa4f47f4ef978dcb745bb527acd9dcbae480e8ce8c3dcbd48607c078282604051808381526020018281526020019250505060405180910390a15050565b61030f6040805190810160405280601581526020017f636173682d6368616e6e656c732d6d616e616765720000000000000000000000815250610468565b73ffffffffffffffffffffffffffffffffffffffff1663882d5f7a826040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050600060405180830381600087803b15801561037d57600080fd5b505af1158015610391573d6000803e3d6000fd5b5050505050565b6103d66040805190810160405280601581526020017f636173682d6368616e6e656c732d6d616e616765720000000000000000000000815250610468565b73ffffffffffffffffffffffffffffffffffffffff166354e027d883836040518363ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018083815260200182815260200192505050600060405180830381600087803b15801561044c57600080fd5b505af1158015610460573d6000803e3d6000fd5b505050505050565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663693ec85e836040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825283818151815260200191508051906020019080838360005b838110156105135780820151818401526020810190506104f8565b50505050905090810190601f1680156105405780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b15801561055f57600080fd5b505af1158015610573573d6000803e3d6000fd5b505050506040513d602081101561058957600080fd5b8101908080519060200190929190505050905060008173ffffffffffffffffffffffffffffffffffffffff161415151561062b576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260168152602001807f53797374656d2073746174652076696f6c6174696f6e0000000000000000000081525060200191505060405180910390fd5b9190505600a165627a7a72305820dce99e8c3e474b30330639359173a1a56cea9db0681dd27e22435cbdeb006a2d0029";

    public static final String FUNC_OPEN = "open";

    public static final String FUNC_CLOSE = "close";

    public static final String FUNC_CONFIRMOPEN = "confirmOpen";

    public static final String FUNC_CONFIRMCLOSE = "confirmClose";

    public static final String FUNC_INCREASEBALANCE = "increaseBalance";

    public static final Event OPENCASHACCEPTOR_EVENT = new Event("OpenCashAcceptor", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event CLOSECASHACCEPTOR_EVENT = new Event("CloseCashAcceptor", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
        _addresses.put("5777", "0x34653005dc3f732e97f24101c842d634a1bb3c0c");
    }

    protected CashInOracle(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected CashInOracle(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static RemoteCall<CashInOracle> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String regAddr) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(regAddr)));
        return deployRemoteCall(CashInOracle.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static RemoteCall<CashInOracle> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String regAddr) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(regAddr)));
        return deployRemoteCall(CashInOracle.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public List<OpenCashAcceptorEventResponse> getOpenCashAcceptorEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(OPENCASHACCEPTOR_EVENT, transactionReceipt);
        ArrayList<OpenCashAcceptorEventResponse> responses = new ArrayList<OpenCashAcceptorEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OpenCashAcceptorEventResponse typedResponse = new OpenCashAcceptorEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.sessionId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.channelId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.channelStatus = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<OpenCashAcceptorEventResponse> openCashAcceptorEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, OpenCashAcceptorEventResponse>() {
            @Override
            public OpenCashAcceptorEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(OPENCASHACCEPTOR_EVENT, log);
                OpenCashAcceptorEventResponse typedResponse = new OpenCashAcceptorEventResponse();
                typedResponse.log = log;
                typedResponse.sessionId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.channelId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.channelStatus = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<OpenCashAcceptorEventResponse> openCashAcceptorEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OPENCASHACCEPTOR_EVENT));
        return openCashAcceptorEventObservable(filter);
    }

    public List<CloseCashAcceptorEventResponse> getCloseCashAcceptorEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CLOSECASHACCEPTOR_EVENT, transactionReceipt);
        ArrayList<CloseCashAcceptorEventResponse> responses = new ArrayList<CloseCashAcceptorEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            CloseCashAcceptorEventResponse typedResponse = new CloseCashAcceptorEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.sessionId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.channelId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<CloseCashAcceptorEventResponse> closeCashAcceptorEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, CloseCashAcceptorEventResponse>() {
            @Override
            public CloseCashAcceptorEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CLOSECASHACCEPTOR_EVENT, log);
                CloseCashAcceptorEventResponse typedResponse = new CloseCashAcceptorEventResponse();
                typedResponse.log = log;
                typedResponse.sessionId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.channelId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<CloseCashAcceptorEventResponse> closeCashAcceptorEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CLOSECASHACCEPTOR_EVENT));
        return closeCashAcceptorEventObservable(filter);
    }

    public RemoteCall<TransactionReceipt> open(BigInteger sessionId, BigInteger channelId, BigInteger channelStatus) {
        final Function function = new Function(
                FUNC_OPEN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(sessionId), 
                new org.web3j.abi.datatypes.generated.Uint256(channelId), 
                new org.web3j.abi.datatypes.generated.Uint256(channelStatus)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> close(BigInteger sessionId, BigInteger channelId) {
        final Function function = new Function(
                FUNC_CLOSE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(sessionId), 
                new org.web3j.abi.datatypes.generated.Uint256(channelId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> confirmOpen(BigInteger channelId) {
        final Function function = new Function(
                FUNC_CONFIRMOPEN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(channelId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> confirmClose(BigInteger channelId) {
        final Function function = new Function(
                FUNC_CONFIRMCLOSE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(channelId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> increaseBalance(BigInteger channelId, BigInteger amount) {
        final Function function = new Function(
                FUNC_INCREASEBALANCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(channelId), 
                new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static CashInOracle load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new CashInOracle(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static CashInOracle load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new CashInOracle(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public interface CashInOracleEvent extends SmartContractEvent {
    }

    public static class OpenCashAcceptorEventResponse implements CashInOracleEvent {
        public Log log;

        public BigInteger sessionId;

        public BigInteger channelId;

        public BigInteger channelStatus;
    }

    public static class CloseCashAcceptorEventResponse implements CashInOracleEvent {
        public Log log;

        public BigInteger sessionId;

        public BigInteger channelId;
    }
}
