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
    private static final String BINARY = "0x608060405234801561001057600080fd5b5060405160208062000cfd8339810160408181529151828201909252600e8082527f636173682d696e2d6f7261636c650000000000000000000000000000000000006020830190815260008054600160a060020a0319163317905583929161007a916001916102a8565b505080600160a060020a031663713b563f6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156100d357600080fd5b505af11580156100e7573d6000803e3d6000fd5b505050506040513d60208110156100fd57600080fd5b505160038054600160a060020a031916600160a060020a03928316179055604080517fd0496d6a00000000000000000000000000000000000000000000000000000000815290519183169163d0496d6a916004808201926020929091908290030181600087803b15801561017057600080fd5b505af1158015610184573d6000803e3d6000fd5b505050506040513d602081101561019a57600080fd5b505160028054600160a060020a031916600160a060020a03928316178082556040517ff2c298be00000000000000000000000000000000000000000000000000000000815260206004820190815260018054610100818316150260001901169490940460248301819052929094169363f2c298be939290918291604490910190849080156102695780601f1061023e57610100808354040283529160200191610269565b820191906000526020600020905b81548152906001019060200180831161024c57829003601f168201915b505092505050600060405180830381600087803b15801561028957600080fd5b505af115801561029d573d6000803e3d6000fd5b505050505050610343565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106102e957805160ff1916838001178555610316565b82800160010185558215610316579182015b828111156103165782518255916020019190600101906102fb565b50610322929150610326565b5090565b61034091905b80821115610322576000815560010161032c565b90565b6109aa80620003536000396000f3006080604052600436106100a35763ffffffff7c010000000000000000000000000000000000000000000000000000000060003504166306fdde0381146100a857806328ac71181461013257806341c0e1b51461014f57806352bfd1ce1461016457806375da247a1461017c5780638da5cb5b14610194578063a0e36aba146101c5578063c2a92192146101dd578063f479d377146101f5578063f61f069c14610210575b600080fd5b3480156100b457600080fd5b506100bd61022e565b6040805160208082528351818301528351919283929083019185019080838360005b838110156100f75781810151838201526020016100df565b50505050905090810190601f1680156101245780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561013e57600080fd5b5061014d6004356024356102bb565b005b34801561015b57600080fd5b5061014d6102fa565b34801561017057600080fd5b5061014d600435610377565b34801561018857600080fd5b5061014d600435610512565b3480156101a057600080fd5b506101a961070b565b60408051600160a060020a039092168252519081900360200190f35b3480156101d157600080fd5b5061014d60043561071a565b3480156101e957600080fd5b5061014d60043561071d565b34801561020157600080fd5b5061014d600435602435610796565b34801561021c57600080fd5b5061014d600435602435604435610939565b60018054604080516020600284861615610100026000190190941693909304601f810184900484028201840190925281815292918301828280156102b35780601f10610288576101008083540402835291602001916102b3565b820191906000526020600020905b81548152906001019060200180831161029657829003601f168201915b505050505081565b604080518381526020810183905281517f52e3b18263b18761b9533091a4bd773927ef0f235b43d9dccb01e0c6484daf73929181900390910190a15050565b600054600160a060020a0316321461037357604080517f08c379a000000000000000000000000000000000000000000000000000000000815260206004820152601260248201527f6f6e6c79206f776e657220616c6c6f7765640000000000000000000000000000604482015290519081900360640190fd5b6000ff5b600254604080518082018252600f81527f636173682d696e2d6d616e616765720000000000000000000000000000000000602080830191825292517f693ec85e00000000000000000000000000000000000000000000000000000000815260048101938452825160248201528251600160a060020a039095169463693ec85e949283926044019180838360005b8381101561041c578181015183820152602001610404565b50505050905090810190601f1680156104495780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b15801561046857600080fd5b505af115801561047c573d6000803e3d6000fd5b505050506040513d602081101561049257600080fd5b5051604080517f882d5f7a000000000000000000000000000000000000000000000000000000008152600481018490529051600160a060020a039092169163882d5f7a9160248082019260009290919082900301818387803b1580156104f757600080fd5b505af115801561050b573d6000803e3d6000fd5b5050505050565b600054600160a060020a0316331461058b57604080517f08c379a000000000000000000000000000000000000000000000000000000000815260206004820152601260248201527f6f6e6c79206f776e657220616c6c6f7765640000000000000000000000000000604482015290519081900360640190fd5b600254604080518082018252600f81527f636173682d696e2d6d616e616765720000000000000000000000000000000000602080830191825292517f693ec85e00000000000000000000000000000000000000000000000000000000815260048101938452825160248201528251600160a060020a039095169463693ec85e949283926044019180838360005b83811015610630578181015183820152602001610618565b50505050905090810190601f16801561065d5780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b15801561067c57600080fd5b505af1158015610690573d6000803e3d6000fd5b505050506040513d60208110156106a657600080fd5b5051604080517f5940c655000000000000000000000000000000000000000000000000000000008152600481018490529051600160a060020a0390921691635940c6559160248082019260009290919082900301818387803b1580156104f757600080fd5b600054600160a060020a031681565b50565b600054600160a060020a0316331461071a57604080517f08c379a000000000000000000000000000000000000000000000000000000000815260206004820152601260248201527f6f6e6c79206f776e657220616c6c6f7765640000000000000000000000000000604482015290519081900360640190fd5b600254604080518082018252600f81527f636173682d696e2d6d616e616765720000000000000000000000000000000000602080830191825292517f693ec85e00000000000000000000000000000000000000000000000000000000815260048101938452825160248201528251600160a060020a039095169463693ec85e949283926044019180838360005b8381101561083b578181015183820152602001610823565b50505050905090810190601f1680156108685780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b15801561088757600080fd5b505af115801561089b573d6000803e3d6000fd5b505050506040513d60208110156108b157600080fd5b5051604080517f54e027d800000000000000000000000000000000000000000000000000000000815260048101859052602481018490529051600160a060020a03909216916354e027d89160448082019260009290919082900301818387803b15801561091d57600080fd5b505af1158015610931573d6000803e3d6000fd5b505050505050565b604080518481526020810184905280820183905290517fa0ca351a143349b8bf0cb616b17c89387132218bcb2408bd70411d906787ad2d9181900360600190a15050505600a165627a7a72305820e65a434429db831e3e84ea582b7196d9bba22a94749496363cabb67f2affb36c0029";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_KILL = "kill";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_ONNEXTOPENCASHIN = "onNextOpenCashIn";

    public static final String FUNC_SUCCESSOPEN = "successOpen";

    public static final String FUNC_INCREASEBALANCE = "increaseBalance";

    public static final String FUNC_FAILOPEN = "failOpen";

    public static final String FUNC_ONNEXTCLOSECASHIN = "onNextCloseCashIn";

    public static final String FUNC_SUCCESSCLOSE = "successClose";

    public static final String FUNC_FAILCLOSE = "failClose";

    public static final Event OPENCASHIN_EVENT = new Event("OpenCashIn", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event CLOSECASHIN_EVENT = new Event("CloseCashIn", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
        _addresses.put("4447", "0x82d50ad3c1091866e258fd0f1a7cc9674609d254");
        _addresses.put("89354", "0x5ad509a77b30ecc65268063b412d715e93d7a28e");
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

    public List<OpenCashInEventResponse> getOpenCashInEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(OPENCASHIN_EVENT, transactionReceipt);
        ArrayList<OpenCashInEventResponse> responses = new ArrayList<OpenCashInEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OpenCashInEventResponse typedResponse = new OpenCashInEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._commandId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._sessionId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse._maxBalance = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<OpenCashInEventResponse> openCashInEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, OpenCashInEventResponse>() {
            @Override
            public OpenCashInEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(OPENCASHIN_EVENT, log);
                OpenCashInEventResponse typedResponse = new OpenCashInEventResponse();
                typedResponse.log = log;
                typedResponse._commandId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._sessionId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse._maxBalance = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<OpenCashInEventResponse> openCashInEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OPENCASHIN_EVENT));
        return openCashInEventObservable(filter);
    }

    public List<CloseCashInEventResponse> getCloseCashInEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CLOSECASHIN_EVENT, transactionReceipt);
        ArrayList<CloseCashInEventResponse> responses = new ArrayList<CloseCashInEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            CloseCashInEventResponse typedResponse = new CloseCashInEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._cashInId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._sessionId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<CloseCashInEventResponse> closeCashInEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, CloseCashInEventResponse>() {
            @Override
            public CloseCashInEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CLOSECASHIN_EVENT, log);
                CloseCashInEventResponse typedResponse = new CloseCashInEventResponse();
                typedResponse.log = log;
                typedResponse._cashInId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._sessionId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<CloseCashInEventResponse> closeCashInEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CLOSECASHIN_EVENT));
        return closeCashInEventObservable(filter);
    }

    public RemoteCall<TransactionReceipt> onNextOpenCashIn(BigInteger _commandId, BigInteger _sessionId, BigInteger _maxBalance) {
        final Function function = new Function(
                FUNC_ONNEXTOPENCASHIN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_commandId), 
                new org.web3j.abi.datatypes.generated.Uint256(_sessionId), 
                new org.web3j.abi.datatypes.generated.Uint256(_maxBalance)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> successOpen(BigInteger _commandId) {
        final Function function = new Function(
                FUNC_SUCCESSOPEN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_commandId)), 
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

    public RemoteCall<TransactionReceipt> failOpen(BigInteger _commandId) {
        final Function function = new Function(
                FUNC_FAILOPEN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_commandId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> onNextCloseCashIn(BigInteger _commandId, BigInteger _sessionId) {
        final Function function = new Function(
                FUNC_ONNEXTCLOSECASHIN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_commandId), 
                new org.web3j.abi.datatypes.generated.Uint256(_sessionId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> successClose(BigInteger _commandId) {
        final Function function = new Function(
                FUNC_SUCCESSCLOSE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_commandId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> failClose(BigInteger _commandId) {
        final Function function = new Function(
                FUNC_FAILCLOSE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_commandId)), 
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

    public static class OpenCashInEventResponse implements CashInOracleEvent {
        public Log log;

        public BigInteger _commandId;

        public BigInteger _sessionId;

        public BigInteger _maxBalance;
    }

    public static class CloseCashInEventResponse implements CashInOracleEvent {
        public Log log;

        public BigInteger _cashInId;

        public BigInteger _sessionId;
    }
}
