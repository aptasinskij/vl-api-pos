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
public class CashInOracle extends Contract {
    private static final String BINARY = "0x60806040523480156200001157600080fd5b5060405160208062001134833981018060405281019080805190602001909291905050508060006040805190810160405280600e81526020017f636173682d696e2d6f7261636c65000000000000000000000000000000000000815250336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508060019080519060200190620000c6929190620005bf565b50508173ffffffffffffffffffffffffffffffffffffffff1663713b563f6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156200012d57600080fd5b505af115801562000142573d6000803e3d6000fd5b505050506040513d60208110156200015957600080fd5b8101908080519060200190929190505050600360006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508173ffffffffffffffffffffffffffffffffffffffff1663d0496d6a6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156200020f57600080fd5b505af115801562000224573d6000803e3d6000fd5b505050506040513d60208110156200023b57600080fd5b8101908080519060200190929190505050600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663693ec85e60016040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825283818154600181600116156101000203166002900481526020019150805460018160011615610100020316600290048015620003845780601f10620003585761010080835404028352916020019162000384565b820191906000526020600020905b8154815290600101906020018083116200036657829003601f168201915b505092505050602060405180830381600087803b158015620003a557600080fd5b505af1158015620003ba573d6000803e3d6000fd5b505050506040513d6020811015620003d157600080fd5b8101908080519060200190929190505050905060008173ffffffffffffffffffffffffffffffffffffffff1614151562000484578073ffffffffffffffffffffffffffffffffffffffff166341c0e1b56040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401600060405180830381600087803b1580156200046a57600080fd5b505af11580156200047f573d6000803e3d6000fd5b505050505b600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663f2c298be60016040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018281038252838181546001816001161561010002031660029004815260200191508054600181600116156101000203166002900480156200057c5780601f1062000550576101008083540402835291602001916200057c565b820191906000526020600020905b8154815290600101906020018083116200055e57829003601f168201915b505092505050600060405180830381600087803b1580156200059d57600080fd5b505af1158015620005b2573d6000803e3d6000fd5b505050505050506200066e565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106200060257805160ff191683800117855562000633565b8280016001018555821562000633579182015b828111156200063257825182559160200191906001019062000615565b5b50905062000642919062000646565b5090565b6200066b91905b80821115620006675760008160009055506001016200064d565b5090565b90565b610ab6806200067e6000396000f30060806040526004361061008e576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806306fdde03146100935780633d57b2a91461012357806341c0e1b5146101645780635940c6551461017b578063596c8976146101a8578063882d5f7a146101df5780638da5cb5b1461020c578063f479d37714610263575b600080fd5b34801561009f57600080fd5b506100a861029a565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156100e85780820151818401526020810190506100cd565b50505050905090810190601f1680156101155780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561012f57600080fd5b50610162600480360381019080803590602001909291908035906020019092919080359060200190929190505050610338565b005b34801561017057600080fd5b50610179610384565b005b34801561018757600080fd5b506101a66004803603810190808035906020019092919050505061044c565b005b3480156101b457600080fd5b506101dd600480360381019080803590602001909291908035906020019092919050505061063b565b005b3480156101eb57600080fd5b5061020a6004803603810190808035906020019092919050505061067e565b005b34801561021857600080fd5b5061022161086d565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561026f57600080fd5b506102986004803603810190808035906020019092919080359060200190929190505050610892565b005b60018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156103305780601f1061030557610100808354040283529160200191610330565b820191906000526020600020905b81548152906001019060200180831161031357829003601f168201915b505050505081565b7fd5e06f715ea045b85ecbf99e39def109e0d16a5b56924190bcd2a388171d8ac483838360405180848152602001838152602001828152602001935050505060405180910390a1505050565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163273ffffffffffffffffffffffffffffffffffffffff16141515610448576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260128152602001807f6f6e6c79206f776e657220616c6c6f776564000000000000000000000000000081525060200191505060405180910390fd5b6000ff5b600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663693ec85e6040805190810160405280601581526020017f636173682d6368616e6e656c732d6d616e6167657200000000000000000000008152506040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825283818151815260200191508051906020019080838360005b8381101561052b578082015181840152602081019050610510565b50505050905090810190601f1680156105585780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b15801561057757600080fd5b505af115801561058b573d6000803e3d6000fd5b505050506040513d60208110156105a157600080fd5b810190808051906020019092919050505073ffffffffffffffffffffffffffffffffffffffff16635940c655826040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050600060405180830381600087803b15801561062057600080fd5b505af1158015610634573d6000803e3d6000fd5b5050505050565b7fe118fe67faa4f47f4ef978dcb745bb527acd9dcbae480e8ce8c3dcbd48607c078282604051808381526020018281526020019250505060405180910390a15050565b600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663693ec85e6040805190810160405280601581526020017f636173682d6368616e6e656c732d6d616e6167657200000000000000000000008152506040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825283818151815260200191508051906020019080838360005b8381101561075d578082015181840152602081019050610742565b50505050905090810190601f16801561078a5780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b1580156107a957600080fd5b505af11580156107bd573d6000803e3d6000fd5b505050506040513d60208110156107d357600080fd5b810190808051906020019092919050505073ffffffffffffffffffffffffffffffffffffffff1663882d5f7a826040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050600060405180830381600087803b15801561085257600080fd5b505af1158015610866573d6000803e3d6000fd5b5050505050565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663693ec85e6040805190810160405280601581526020017f636173682d6368616e6e656c732d6d616e6167657200000000000000000000008152506040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825283818151815260200191508051906020019080838360005b83811015610971578082015181840152602081019050610956565b50505050905090810190601f16801561099e5780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b1580156109bd57600080fd5b505af11580156109d1573d6000803e3d6000fd5b505050506040513d60208110156109e757600080fd5b810190808051906020019092919050505073ffffffffffffffffffffffffffffffffffffffff166354e027d883836040518363ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018083815260200182815260200192505050600060405180830381600087803b158015610a6e57600080fd5b505af1158015610a82573d6000803e3d6000fd5b5050505050505600a165627a7a72305820681f4ffeea1ffe3c79e657ee812400b6d3b5de008136416ee0748617890f9cb50029";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_KILL = "kill";

    public static final String FUNC_OWNER = "owner";

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
        _addresses.put("77142", "0x9b6a0fb3f64177572d10bab61e2519fbad6b8d88");
    }

    protected CashInOracle(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected CashInOracle(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
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

    public static RemoteCall<CashInOracle> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _config) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_config)));
        return deployRemoteCall(CashInOracle.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static RemoteCall<CashInOracle> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _config) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_config)));
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
