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
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Bytes32;
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
public class PrinterOracle extends Contract {
    private static final String BINARY = "0x60806040523480156200001157600080fd5b50604051602080620011d08339810160408181529151828201909252600e8082527f7072696e7465722d6f7261636c650000000000000000000000000000000000006020830190815260008054600160a060020a031916331790558392916200007d91600191620002b8565b505080600160a060020a031663713b563f6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015620000d757600080fd5b505af1158015620000ec573d6000803e3d6000fd5b505050506040513d60208110156200010357600080fd5b505160038054600160a060020a031916600160a060020a03928316179055604080517fd0496d6a00000000000000000000000000000000000000000000000000000000815290519183169163d0496d6a916004808201926020929091908290030181600087803b1580156200017757600080fd5b505af11580156200018c573d6000803e3d6000fd5b505050506040513d6020811015620001a357600080fd5b505160028054600160a060020a031916600160a060020a03928316178082556040517ff2c298be00000000000000000000000000000000000000000000000000000000815260206004820190815260018054610100818316150260001901169490940460248301819052929094169363f2c298be93929091829160449091019084908015620002765780601f106200024a5761010080835404028352916020019162000276565b820191906000526020600020905b8154815290600101906020018083116200025857829003601f168201915b505092505050600060405180830381600087803b1580156200029757600080fd5b505af1158015620002ac573d6000803e3d6000fd5b5050505050506200035d565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10620002fb57805160ff19168380011785556200032b565b828001600101855582156200032b579182015b828111156200032b5782518255916020019190600101906200030e565b50620003399291506200033d565b5090565b6200035a91905b8082111562000339576000815560010162000344565b90565b610e63806200036d6000396000f3006080604052600436106100975763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416627a5983811461009c57806306fdde031461013a57806341c0e1b5146101c45780634693b378146101d95780635e4a213e146101f15780637bdf7ed71461020c5780638da5cb5b1461031c578063c642b4ae1461034d578063e330bf7314610365575b600080fd5b3480156100a857600080fd5b5060408051602060046024803582810135601f810185900485028601850190965285855261013895833595369560449491939091019190819084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a99988101979196509182019450925082915084018382808284375094975061037d9650505050505050565b005b34801561014657600080fd5b5061014f610615565b6040805160208082528351818301528351919283929083019185019080838360005b83811015610189578181015183820152602001610171565b50505050905090810190601f1680156101b65780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b3480156101d057600080fd5b506101386106a2565b3480156101e557600080fd5b506101386004356106f6565b3480156101fd57600080fd5b506101386004356024356108b9565b34801561021857600080fd5b50604080516020600460443581810135601f810184900484028501840190955284845261013894823594602480359536959460649492019190819084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a999881019791965091820194509250829150840183828082843750506040805187358901803560208181028481018201909552818452989b9a998901989297509082019550935083925085019084908082843750506040805187358901803560208181028481018201909552818452989b9a9989019892975090820195509350839250850190849080828437509497506108f89650505050505050565b34801561032857600080fd5b50610331610a98565b60408051600160a060020a039092168252519081900360200190f35b34801561035957600080fd5b50610138600435610aa7565b34801561037157600080fd5b50610138600435610c4f565b600054600160a060020a031633146103cd576040805160e560020a62461bcd0281526020600482015260126024820152600080516020610e18833981519152604482015290519081900360640190fd5b600254604080518082018252600f8152600080516020610df88339815191526020808301918252925160e160020a63349f642f02815260048101938452825160248201528251600160a060020a039095169463693ec85e949283926044019180838360005b8381101561044a578181015183820152602001610432565b50505050905090810190601f1680156104775780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b15801561049657600080fd5b505af11580156104aa573d6000803e3d6000fd5b505050506040513d60208110156104c057600080fd5b50516040517f07fac49600000000000000000000000000000000000000000000000000000000815260048101858152606060248301908152855160648401528551600160a060020a03909416936307fac4969388938893889391929091604482019160840190602087019080838360005b83811015610549578181015183820152602001610531565b50505050905090810190601f1680156105765780820380516001836020036101000a031916815260200191505b50838103825284518152845160209182019186019080838360005b838110156105a9578181015183820152602001610591565b50505050905090810190601f1680156105d65780820380516001836020036101000a031916815260200191505b5095505050505050600060405180830381600087803b1580156105f857600080fd5b505af115801561060c573d6000803e3d6000fd5b50505050505050565b60018054604080516020600284861615610100026000190190941693909304601f8101849004840282018401909252818152929183018282801561069a5780601f1061066f5761010080835404028352916020019161069a565b820191906000526020600020905b81548152906001019060200180831161067d57829003601f168201915b505050505081565b600054600160a060020a031632146106f2576040805160e560020a62461bcd0281526020600482015260126024820152600080516020610e18833981519152604482015290519081900360640190fd5b6000ff5b600054600160a060020a03163314610746576040805160e560020a62461bcd0281526020600482015260126024820152600080516020610e18833981519152604482015290519081900360640190fd5b600254604080518082018252600f8152600080516020610df88339815191526020808301918252925160e160020a63349f642f02815260048101938452825160248201528251600160a060020a039095169463693ec85e949283926044019180838360005b838110156107c35781810151838201526020016107ab565b50505050905090810190601f1680156107f05780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b15801561080f57600080fd5b505af1158015610823573d6000803e3d6000fd5b505050506040513d602081101561083957600080fd5b5051604080517f76eefe27000000000000000000000000000000000000000000000000000000008152600481018490529051600160a060020a03909216916376eefe279160248082019260009290919082900301818387803b15801561089e57600080fd5b505af11580156108b2573d6000803e3d6000fd5b5050505050565b604080518381526020810183905281517f62b01c50ba4b1e21a4796deffb7e07540187fffcca5e57189085a08fbfaaaef7929181900390910190a15050565b7ffa10452fabd75b7e57cb7646f414ed84eca5c7a8da25c5f9971e7e3a79fe1a428686868686866040518087815260200186815260200180602001806020018060200180602001858103855289818151815260200191508051906020019080838360005b8381101561097457818101518382015260200161095c565b50505050905090810190601f1680156109a15780820380516001836020036101000a031916815260200191505b5085810384528851815288516020918201918a019080838360005b838110156109d45781810151838201526020016109bc565b50505050905090810190601f168015610a015780820380516001836020036101000a031916815260200191505b508581038352875181528751602091820191808a01910280838360005b83811015610a36578181015183820152602001610a1e565b50505050905001858103825286818151815260200191508051906020019060200280838360005b83811015610a75578181015183820152602001610a5d565b505050509050019a505050505050505050505060405180910390a1505050505050565b600054600160a060020a031681565b600054600160a060020a03163314610af7576040805160e560020a62461bcd0281526020600482015260126024820152600080516020610e18833981519152604482015290519081900360640190fd5b600254604080518082018252600f8152600080516020610df88339815191526020808301918252925160e160020a63349f642f02815260048101938452825160248201528251600160a060020a039095169463693ec85e949283926044019180838360005b83811015610b74578181015183820152602001610b5c565b50505050905090810190601f168015610ba15780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b158015610bc057600080fd5b505af1158015610bd4573d6000803e3d6000fd5b505050506040513d6020811015610bea57600080fd5b5051604080517f31e2ab8a000000000000000000000000000000000000000000000000000000008152600481018490529051600160a060020a03909216916331e2ab8a9160248082019260009290919082900301818387803b15801561089e57600080fd5b600054600160a060020a03163314610c9f576040805160e560020a62461bcd0281526020600482015260126024820152600080516020610e18833981519152604482015290519081900360640190fd5b600254604080518082018252600f8152600080516020610df88339815191526020808301918252925160e160020a63349f642f02815260048101938452825160248201528251600160a060020a039095169463693ec85e949283926044019180838360005b83811015610d1c578181015183820152602001610d04565b50505050905090810190601f168015610d495780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b158015610d6857600080fd5b505af1158015610d7c573d6000803e3d6000fd5b505050506040513d6020811015610d9257600080fd5b5051604080517f52e215ef000000000000000000000000000000000000000000000000000000008152600481018490529051600160a060020a03909216916352e215ef9160248082019260009290919082900301818387803b15801561089e57600080fd007072696e7465722d6d616e6167657200000000000000000000000000000000006f6e6c79206f776e657220616c6c6f7765640000000000000000000000000000a165627a7a72305820dda8aa34b29b36f603c3cfb21d24d329d6feef276e64e154aa3e51b1235b20d00029";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_KILL = "kill";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_ONNEXTRECEIPTCREATE = "onNextReceiptCreate";

    public static final String FUNC_SUCCESSCREATE = "successCreate";

    public static final String FUNC_FAILCREATE = "failCreate";

    public static final String FUNC_ONNEXTRECEIPTPRINT = "onNextReceiptPrint";

    public static final String FUNC_SUCCESSPRINT = "successPrint";

    public static final String FUNC_FAILPRINT = "failPrint";

    public static final Event RECEIPTCREATE_EVENT = new Event("ReceiptCreate", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event RECEIPTPRINT_EVENT = new Event("ReceiptPrint", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<DynamicArray<Bytes32>>() {}, new TypeReference<DynamicArray<Bytes32>>() {}));
    ;

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
        _addresses.put("89354", "0xfd1eee1294d56ed901103a7960655aec4c15df79");
    }

    protected PrinterOracle(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected PrinterOracle(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
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

    public static RemoteCall<PrinterOracle> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _config) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_config)));
        return deployRemoteCall(PrinterOracle.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static RemoteCall<PrinterOracle> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _config) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_config)));
        return deployRemoteCall(PrinterOracle.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public List<ReceiptCreateEventResponse> getReceiptCreateEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(RECEIPTCREATE_EVENT, transactionReceipt);
        ArrayList<ReceiptCreateEventResponse> responses = new ArrayList<ReceiptCreateEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ReceiptCreateEventResponse typedResponse = new ReceiptCreateEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._commandId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._sessionId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<ReceiptCreateEventResponse> receiptCreateEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, ReceiptCreateEventResponse>() {
            @Override
            public ReceiptCreateEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(RECEIPTCREATE_EVENT, log);
                ReceiptCreateEventResponse typedResponse = new ReceiptCreateEventResponse();
                typedResponse.log = log;
                typedResponse._commandId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._sessionId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<ReceiptCreateEventResponse> receiptCreateEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(RECEIPTCREATE_EVENT));
        return receiptCreateEventObservable(filter);
    }

    public List<ReceiptPrintEventResponse> getReceiptPrintEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(RECEIPTPRINT_EVENT, transactionReceipt);
        ArrayList<ReceiptPrintEventResponse> responses = new ArrayList<ReceiptPrintEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ReceiptPrintEventResponse typedResponse = new ReceiptPrintEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._commandId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._sessionId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse._receiptId = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse._data = (String) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse._paramNames = (List<byte[]>) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse._paramValues = (List<byte[]>) eventValues.getNonIndexedValues().get(5).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<ReceiptPrintEventResponse> receiptPrintEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, ReceiptPrintEventResponse>() {
            @Override
            public ReceiptPrintEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(RECEIPTPRINT_EVENT, log);
                ReceiptPrintEventResponse typedResponse = new ReceiptPrintEventResponse();
                typedResponse.log = log;
                typedResponse._commandId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._sessionId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse._receiptId = (String) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse._data = (String) eventValues.getNonIndexedValues().get(3).getValue();
                typedResponse._paramNames = (List<byte[]>) eventValues.getNonIndexedValues().get(4).getValue();
                typedResponse._paramValues = (List<byte[]>) eventValues.getNonIndexedValues().get(5).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<ReceiptPrintEventResponse> receiptPrintEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(RECEIPTPRINT_EVENT));
        return receiptPrintEventObservable(filter);
    }

    public RemoteCall<TransactionReceipt> onNextReceiptCreate(BigInteger _commandId, BigInteger _sessionId) {
        final Function function = new Function(
                FUNC_ONNEXTRECEIPTCREATE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_commandId), 
                new org.web3j.abi.datatypes.generated.Uint256(_sessionId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> successCreate(BigInteger _commandId, String _receiptId, String _url) {
        final Function function = new Function(
                FUNC_SUCCESSCREATE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_commandId), 
                new org.web3j.abi.datatypes.Utf8String(_receiptId), 
                new org.web3j.abi.datatypes.Utf8String(_url)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> failCreate(BigInteger _commandId) {
        final Function function = new Function(
                FUNC_FAILCREATE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_commandId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> onNextReceiptPrint(BigInteger _commandId, BigInteger _sessionId, String _receiptId, String _data, List<byte[]> _paramNames, List<byte[]> _paramValues) {
        final Function function = new Function(
                FUNC_ONNEXTRECEIPTPRINT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_commandId), 
                new org.web3j.abi.datatypes.generated.Uint256(_sessionId), 
                new org.web3j.abi.datatypes.Utf8String(_receiptId), 
                new org.web3j.abi.datatypes.Utf8String(_data), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(
                        org.web3j.abi.Utils.typeMap(_paramNames, org.web3j.abi.datatypes.generated.Bytes32.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(
                        org.web3j.abi.Utils.typeMap(_paramValues, org.web3j.abi.datatypes.generated.Bytes32.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> successPrint(BigInteger _commandId) {
        final Function function = new Function(
                FUNC_SUCCESSPRINT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_commandId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> failPrint(BigInteger _commandId) {
        final Function function = new Function(
                FUNC_FAILPRINT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_commandId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static PrinterOracle load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new PrinterOracle(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static PrinterOracle load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new PrinterOracle(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public interface PrinterOracleEvent extends SmartContractEvent {
    }

    public static class ReceiptCreateEventResponse implements PrinterOracleEvent {
        public Log log;

        public BigInteger _commandId;

        public BigInteger _sessionId;
    }

    public static class ReceiptPrintEventResponse implements PrinterOracleEvent {
        public Log log;

        public BigInteger _commandId;

        public BigInteger _sessionId;

        public String _receiptId;

        public String _data;

        public List<byte[]> _paramNames;

        public List<byte[]> _paramValues;
    }
}
