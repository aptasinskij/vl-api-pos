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
public class PrinterOracle extends Contract {
    private static final String BINARY = "0x60806040523480156200001157600080fd5b506040516020806200117a8339810160408181529151828201909252600e8082527f7072696e7465722d6f7261636c650000000000000000000000000000000000006020830190815260008054600160a060020a031916331790558392916200007d91600191620002b8565b505080600160a060020a031663713b563f6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015620000d757600080fd5b505af1158015620000ec573d6000803e3d6000fd5b505050506040513d60208110156200010357600080fd5b505160038054600160a060020a031916600160a060020a03928316179055604080517fd0496d6a00000000000000000000000000000000000000000000000000000000815290519183169163d0496d6a916004808201926020929091908290030181600087803b1580156200017757600080fd5b505af11580156200018c573d6000803e3d6000fd5b505050506040513d6020811015620001a357600080fd5b505160028054600160a060020a031916600160a060020a03928316178082556040517ff2c298be00000000000000000000000000000000000000000000000000000000815260206004820190815260018054610100818316150260001901169490940460248301819052929094169363f2c298be93929091829160449091019084908015620002765780601f106200024a5761010080835404028352916020019162000276565b820191906000526020600020905b8154815290600101906020018083116200025857829003601f168201915b505092505050600060405180830381600087803b1580156200029757600080fd5b505af1158015620002ac573d6000803e3d6000fd5b5050505050506200035d565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10620002fb57805160ff19168380011785556200032b565b828001600101855582156200032b579182015b828111156200032b5782518255916020019190600101906200030e565b50620003399291506200033d565b5090565b6200035a91905b8082111562000339576000815560010162000344565b90565b610e0d806200036d6000396000f3006080604052600436106100975763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416627a5983811461009c57806306fdde031461013a57806341c0e1b5146101c45780634693b378146101d95780635e4a213e146101f15780637ac514d11461020c5780638da5cb5b146102e8578063c642b4ae14610319578063e330bf7314610331575b600080fd5b3480156100a857600080fd5b5060408051602060046024803582810135601f810185900485028601850190965285855261013895833595369560449491939091019190819084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a9998810197919650918201945092508291508401838280828437509497506103499650505050505050565b005b34801561014657600080fd5b5061014f6105e1565b6040805160208082528351818301528351919283929083019185019080838360005b83811015610189578181015183820152602001610171565b50505050905090810190601f1680156101b65780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b3480156101d057600080fd5b5061013861066e565b3480156101e557600080fd5b506101386004356106c2565b3480156101fd57600080fd5b50610138600435602435610885565b34801561021857600080fd5b50604080516020600460443581810135601f810184900484028501840190955284845261013894823594602480359536959460649492019190819084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a99988101979196509182019450925082915084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a9998810197919650918201945092508291508401838280828437509497506108c49650505050505050565b3480156102f457600080fd5b506102fd610a42565b60408051600160a060020a039092168252519081900360200190f35b34801561032557600080fd5b50610138600435610a51565b34801561033d57600080fd5b50610138600435610bf9565b600054600160a060020a03163314610399576040805160e560020a62461bcd0281526020600482015260126024820152600080516020610dc2833981519152604482015290519081900360640190fd5b600254604080518082018252600f8152600080516020610da28339815191526020808301918252925160e160020a63349f642f02815260048101938452825160248201528251600160a060020a039095169463693ec85e949283926044019180838360005b838110156104165781810151838201526020016103fe565b50505050905090810190601f1680156104435780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b15801561046257600080fd5b505af1158015610476573d6000803e3d6000fd5b505050506040513d602081101561048c57600080fd5b50516040517f07fac49600000000000000000000000000000000000000000000000000000000815260048101858152606060248301908152855160648401528551600160a060020a03909416936307fac4969388938893889391929091604482019160840190602087019080838360005b838110156105155781810151838201526020016104fd565b50505050905090810190601f1680156105425780820380516001836020036101000a031916815260200191505b50838103825284518152845160209182019186019080838360005b8381101561057557818101518382015260200161055d565b50505050905090810190601f1680156105a25780820380516001836020036101000a031916815260200191505b5095505050505050600060405180830381600087803b1580156105c457600080fd5b505af11580156105d8573d6000803e3d6000fd5b50505050505050565b60018054604080516020600284861615610100026000190190941693909304601f810184900484028201840190925281815292918301828280156106665780601f1061063b57610100808354040283529160200191610666565b820191906000526020600020905b81548152906001019060200180831161064957829003601f168201915b505050505081565b600054600160a060020a031632146106be576040805160e560020a62461bcd0281526020600482015260126024820152600080516020610dc2833981519152604482015290519081900360640190fd5b6000ff5b600054600160a060020a03163314610712576040805160e560020a62461bcd0281526020600482015260126024820152600080516020610dc2833981519152604482015290519081900360640190fd5b600254604080518082018252600f8152600080516020610da28339815191526020808301918252925160e160020a63349f642f02815260048101938452825160248201528251600160a060020a039095169463693ec85e949283926044019180838360005b8381101561078f578181015183820152602001610777565b50505050905090810190601f1680156107bc5780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b1580156107db57600080fd5b505af11580156107ef573d6000803e3d6000fd5b505050506040513d602081101561080557600080fd5b5051604080517f76eefe27000000000000000000000000000000000000000000000000000000008152600481018490529051600160a060020a03909216916376eefe279160248082019260009290919082900301818387803b15801561086a57600080fd5b505af115801561087e573d6000803e3d6000fd5b5050505050565b604080518381526020810183905281517f62b01c50ba4b1e21a4796deffb7e07540187fffcca5e57189085a08fbfaaaef7929181900390910190a15050565b7f2002963a3c2b2da8c4d63a883c9fbbec67feac2bdafde7da25157ecf89f90813858585858560405180868152602001858152602001806020018060200180602001848103845287818151815260200191508051906020019080838360005b8381101561093b578181015183820152602001610923565b50505050905090810190601f1680156109685780820380516001836020036101000a031916815260200191505b50848103835286518152865160209182019188019080838360005b8381101561099b578181015183820152602001610983565b50505050905090810190601f1680156109c85780820380516001836020036101000a031916815260200191505b50848103825285518152855160209182019187019080838360005b838110156109fb5781810151838201526020016109e3565b50505050905090810190601f168015610a285780820380516001836020036101000a031916815260200191505b509850505050505050505060405180910390a15050505050565b600054600160a060020a031681565b600054600160a060020a03163314610aa1576040805160e560020a62461bcd0281526020600482015260126024820152600080516020610dc2833981519152604482015290519081900360640190fd5b600254604080518082018252600f8152600080516020610da28339815191526020808301918252925160e160020a63349f642f02815260048101938452825160248201528251600160a060020a039095169463693ec85e949283926044019180838360005b83811015610b1e578181015183820152602001610b06565b50505050905090810190601f168015610b4b5780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b158015610b6a57600080fd5b505af1158015610b7e573d6000803e3d6000fd5b505050506040513d6020811015610b9457600080fd5b5051604080517f31e2ab8a000000000000000000000000000000000000000000000000000000008152600481018490529051600160a060020a03909216916331e2ab8a9160248082019260009290919082900301818387803b15801561086a57600080fd5b600054600160a060020a03163314610c49576040805160e560020a62461bcd0281526020600482015260126024820152600080516020610dc2833981519152604482015290519081900360640190fd5b600254604080518082018252600f8152600080516020610da28339815191526020808301918252925160e160020a63349f642f02815260048101938452825160248201528251600160a060020a039095169463693ec85e949283926044019180838360005b83811015610cc6578181015183820152602001610cae565b50505050905090810190601f168015610cf35780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b158015610d1257600080fd5b505af1158015610d26573d6000803e3d6000fd5b505050506040513d6020811015610d3c57600080fd5b5051604080517f52e215ef000000000000000000000000000000000000000000000000000000008152600481018490529051600160a060020a03909216916352e215ef9160248082019260009290919082900301818387803b15801561086a57600080fd007072696e7465722d6d616e6167657200000000000000000000000000000000006f6e6c79206f776e657220616c6c6f7765640000000000000000000000000000a165627a7a72305820d7497ab01aa4d67b183b0958d4381cab5d9fc6df33b16a601c4a2585472031f70029";

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
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
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
            typedResponse._params = (String) eventValues.getNonIndexedValues().get(4).getValue();
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
                typedResponse._params = (String) eventValues.getNonIndexedValues().get(4).getValue();
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

    public RemoteCall<TransactionReceipt> onNextReceiptPrint(BigInteger _commandId, BigInteger _sessionId, String _receiptId, String _data, String _params) {
        final Function function = new Function(
                FUNC_ONNEXTRECEIPTPRINT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_commandId), 
                new org.web3j.abi.datatypes.generated.Uint256(_sessionId), 
                new org.web3j.abi.datatypes.Utf8String(_receiptId), 
                new org.web3j.abi.datatypes.Utf8String(_data), 
                new org.web3j.abi.datatypes.Utf8String(_params)), 
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

        public String _params;
    }
}
