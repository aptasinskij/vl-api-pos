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
import org.web3j.abi.datatypes.Bool;
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
public class CameraOracle extends Contract {
    private static final String BINARY = "0x60806040523480156200001157600080fd5b50604051602080620011398339810160408181529151828201909252600d8082527f63616d6572612d6f7261636c65000000000000000000000000000000000000006020830190815260008054600160a060020a031916331790558392916200007d91600191620002b8565b505080600160a060020a031663713b563f6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015620000d757600080fd5b505af1158015620000ec573d6000803e3d6000fd5b505050506040513d60208110156200010357600080fd5b505160038054600160a060020a031916600160a060020a03928316179055604080517fd0496d6a00000000000000000000000000000000000000000000000000000000815290519183169163d0496d6a916004808201926020929091908290030181600087803b1580156200017757600080fd5b505af11580156200018c573d6000803e3d6000fd5b505050506040513d6020811015620001a357600080fd5b505160028054600160a060020a031916600160a060020a03928316178082556040517ff2c298be00000000000000000000000000000000000000000000000000000000815260206004820190815260018054610100818316150260001901169490940460248301819052929094169363f2c298be93929091829160449091019084908015620002765780601f106200024a5761010080835404028352916020019162000276565b820191906000526020600020905b8154815290600101906020018083116200025857829003601f168201915b505092505050600060405180830381600087803b1580156200029757600080fd5b505af1158015620002ac573d6000803e3d6000fd5b5050505050506200035d565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10620002fb57805160ff19168380011785556200032b565b828001600101855582156200032b579182015b828111156200032b5782518255916020019190600101906200030e565b50620003399291506200033d565b5090565b6200035a91905b8082111562000339576000815560010162000344565b90565b610dcc806200036d6000396000f3006080604052600436106100a35763ffffffff7c010000000000000000000000000000000000000000000000000000000060003504166306fdde0381146100a85780630f9cac12146101325780631986721a1461020e578063272412b21461022657806341c0e1b5146102415780638da5cb5b14610256578063b9641d8314610287578063b9bd330d1461029f578063edbf5060146102fd578063efbc3b0f14610315575b600080fd5b3480156100b457600080fd5b506100bd610335565b6040805160208082528351818301528351919283929083019185019080838360005b838110156100f75781810151838201526020016100df565b50505050905090810190601f1680156101245780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561013e57600080fd5b5060408051602060046024803582810135601f810185900485028601850190965285855261020c95833595369560449491939091019190819084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a99988101979196509182019450925082915084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a9998810197919650918201945092508291508401838280828437509497506103c29650505050505050565b005b34801561021a57600080fd5b5061020c600435610670565b34801561023257600080fd5b5061020c6004356024356107e3565b34801561024d57600080fd5b5061020c610822565b34801561026257600080fd5b5061026b61089f565b60408051600160a060020a039092168252519081900360200190f35b34801561029357600080fd5b5061020c6004356108ae565b3480156102ab57600080fd5b5060408051602060046024803582810135601f810185900485028601850190965285855261020c958335953695604494919390910191908190840183828082843750949750610a069650505050505050565b34801561030957600080fd5b5061020c600435610be2565b34801561032157600080fd5b5061020c6004356024356044351515610d3a565b60018054604080516020600284861615610100026000190190941693909304601f810184900484028201840190925281815292918301828280156103ba5780601f1061038f576101008083540402835291602001916103ba565b820191906000526020600020905b81548152906001019060200180831161039d57829003601f168201915b505050505081565b600254604080518082018252600e8152600080516020610d818339815191526020808301918252925160e160020a63349f642f02815260048101938452825160248201528251600160a060020a039095169463693ec85e949283926044019180838360005b8381101561043f578181015183820152602001610427565b50505050905090810190601f16801561046c5780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b15801561048b57600080fd5b505af115801561049f573d6000803e3d6000fd5b505050506040513d60208110156104b557600080fd5b50516040517fd511529f00000000000000000000000000000000000000000000000000000000815260048101868152608060248301908152865160848401528651600160a060020a039094169363d511529f9389938993899389936044810191606482019160a40190602089019080838360005b83811015610541578181015183820152602001610529565b50505050905090810190601f16801561056e5780820380516001836020036101000a031916815260200191505b50848103835286518152865160209182019188019080838360005b838110156105a1578181015183820152602001610589565b50505050905090810190601f1680156105ce5780820380516001836020036101000a031916815260200191505b50848103825285518152855160209182019187019080838360005b838110156106015781810151838201526020016105e9565b50505050905090810190601f16801561062e5780820380516001836020036101000a031916815260200191505b50975050505050505050600060405180830381600087803b15801561065257600080fd5b505af1158015610666573d6000803e3d6000fd5b5050505050505050565b600254604080518082018252600e8152600080516020610d818339815191526020808301918252925160e160020a63349f642f02815260048101938452825160248201528251600160a060020a039095169463693ec85e949283926044019180838360005b838110156106ed5781810151838201526020016106d5565b50505050905090810190601f16801561071a5780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b15801561073957600080fd5b505af115801561074d573d6000803e3d6000fd5b505050506040513d602081101561076357600080fd5b5051604080517f0c965f0b000000000000000000000000000000000000000000000000000000008152600481018490529051600160a060020a0390921691630c965f0b9160248082019260009290919082900301818387803b1580156107c857600080fd5b505af11580156107dc573d6000803e3d6000fd5b5050505050565b604080518381526020810183905281517f16de1666eb16a2670c9d390205bb86bc674940dc7eb116416cdbcdeaeedf6bd6929181900390910190a15050565b600054600160a060020a0316321461089b57604080517f08c379a000000000000000000000000000000000000000000000000000000000815260206004820152601260248201527f6f6e6c79206f776e657220616c6c6f7765640000000000000000000000000000604482015290519081900360640190fd5b6000ff5b600054600160a060020a031681565b600254604080518082018252600e8152600080516020610d818339815191526020808301918252925160e160020a63349f642f02815260048101938452825160248201528251600160a060020a039095169463693ec85e949283926044019180838360005b8381101561092b578181015183820152602001610913565b50505050905090810190601f1680156109585780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b15801561097757600080fd5b505af115801561098b573d6000803e3d6000fd5b505050506040513d60208110156109a157600080fd5b5051604080517f6d0fd4e2000000000000000000000000000000000000000000000000000000008152600481018490529051600160a060020a0390921691636d0fd4e29160248082019260009290919082900301818387803b1580156107c857600080fd5b600254604080518082018252600e8152600080516020610d818339815191526020808301918252925160e160020a63349f642f02815260048101938452825160248201528251600160a060020a039095169463693ec85e949283926044019180838360005b83811015610a83578181015183820152602001610a6b565b50505050905090810190601f168015610ab05780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b158015610acf57600080fd5b505af1158015610ae3573d6000803e3d6000fd5b505050506040513d6020811015610af957600080fd5b5051604080517f4be833ff0000000000000000000000000000000000000000000000000000000081526004810185815260248201928352845160448301528451600160a060020a0390941693634be833ff938793879392606490910190602085019080838360005b83811015610b79578181015183820152602001610b61565b50505050905090810190601f168015610ba65780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b158015610bc657600080fd5b505af1158015610bda573d6000803e3d6000fd5b505050505050565b600254604080518082018252600e8152600080516020610d818339815191526020808301918252925160e160020a63349f642f02815260048101938452825160248201528251600160a060020a039095169463693ec85e949283926044019180838360005b83811015610c5f578181015183820152602001610c47565b50505050905090810190601f168015610c8c5780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b158015610cab57600080fd5b505af1158015610cbf573d6000803e3d6000fd5b505050506040513d6020811015610cd557600080fd5b5051604080517f26811a0e000000000000000000000000000000000000000000000000000000008152600481018490529051600160a060020a03909216916326811a0e9160248082019260009290919082900301818387803b1580156107c857600080fd5b60408051848152602081018490528215158183015290517f91b0050209d6ad8c284e25db5226a5a47b480d0d53da14b53e1c84439d5a44a29181900360600190a1505050560063616d6572612d6d616e61676572000000000000000000000000000000000000a165627a7a72305820ae01f5d7ddbd09c63b3534fac1ff2d0b4089e6f817d1b9effd13598167d344040029";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_KILL = "kill";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_ONNEXTSTARTQRSCAN = "onNextStartQRScan";

    public static final String FUNC_SUCCESSSTART = "successStart";

    public static final String FUNC_FAILSTART = "failStart";

    public static final String FUNC_SCANNED = "scanned";

    public static final String FUNC_ONNEXTSTOPQRSCAN = "onNextStopQRScan";

    public static final String FUNC_SUCCESSSTOP = "successStop";

    public static final String FUNC_FAILSTOP = "failStop";

    public static final Event STARTSCANQR_EVENT = new Event("StartScanQR", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}));
    ;

    public static final Event STOPSCANQR_EVENT = new Event("StopScanQR", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
        _addresses.put("89354", "0xd8c02d31312c9307ef8d57d78601b01f7cae2256");
    }

    protected CameraOracle(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected CameraOracle(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
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

    public static RemoteCall<CameraOracle> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _config) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_config)));
        return deployRemoteCall(CameraOracle.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static RemoteCall<CameraOracle> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _config) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_config)));
        return deployRemoteCall(CameraOracle.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public List<StartScanQREventResponse> getStartScanQREvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(STARTSCANQR_EVENT, transactionReceipt);
        ArrayList<StartScanQREventResponse> responses = new ArrayList<StartScanQREventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            StartScanQREventResponse typedResponse = new StartScanQREventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._commandId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._sessionId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse._lights = (Boolean) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<StartScanQREventResponse> startScanQREventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, StartScanQREventResponse>() {
            @Override
            public StartScanQREventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(STARTSCANQR_EVENT, log);
                StartScanQREventResponse typedResponse = new StartScanQREventResponse();
                typedResponse.log = log;
                typedResponse._commandId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._sessionId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse._lights = (Boolean) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<StartScanQREventResponse> startScanQREventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(STARTSCANQR_EVENT));
        return startScanQREventObservable(filter);
    }

    public List<StopScanQREventResponse> getStopScanQREvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(STOPSCANQR_EVENT, transactionReceipt);
        ArrayList<StopScanQREventResponse> responses = new ArrayList<StopScanQREventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            StopScanQREventResponse typedResponse = new StopScanQREventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._commandId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._sessionId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<StopScanQREventResponse> stopScanQREventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, StopScanQREventResponse>() {
            @Override
            public StopScanQREventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(STOPSCANQR_EVENT, log);
                StopScanQREventResponse typedResponse = new StopScanQREventResponse();
                typedResponse.log = log;
                typedResponse._commandId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._sessionId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<StopScanQREventResponse> stopScanQREventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(STOPSCANQR_EVENT));
        return stopScanQREventObservable(filter);
    }

    public RemoteCall<TransactionReceipt> onNextStartQRScan(BigInteger _commandId, BigInteger _sessionId, Boolean _lights) {
        final Function function = new Function(
                FUNC_ONNEXTSTARTQRSCAN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_commandId), 
                new org.web3j.abi.datatypes.generated.Uint256(_sessionId), 
                new org.web3j.abi.datatypes.Bool(_lights)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> successStart(BigInteger _commandId, String _port, String _url, String _href) {
        final Function function = new Function(
                FUNC_SUCCESSSTART, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_commandId), 
                new org.web3j.abi.datatypes.Utf8String(_port), 
                new org.web3j.abi.datatypes.Utf8String(_url), 
                new org.web3j.abi.datatypes.Utf8String(_href)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> failStart(BigInteger _commandId) {
        final Function function = new Function(
                FUNC_FAILSTART, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_commandId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> scanned(BigInteger _sessionId, String _qr) {
        final Function function = new Function(
                FUNC_SCANNED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_sessionId), 
                new org.web3j.abi.datatypes.Utf8String(_qr)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> onNextStopQRScan(BigInteger _commandId, BigInteger _sessionId) {
        final Function function = new Function(
                FUNC_ONNEXTSTOPQRSCAN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_commandId), 
                new org.web3j.abi.datatypes.generated.Uint256(_sessionId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> successStop(BigInteger _commandId) {
        final Function function = new Function(
                FUNC_SUCCESSSTOP, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_commandId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> failStop(BigInteger _commandId) {
        final Function function = new Function(
                FUNC_FAILSTOP, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_commandId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static CameraOracle load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new CameraOracle(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static CameraOracle load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new CameraOracle(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public interface CameraOracleEvent extends SmartContractEvent {
    }

    public static class StartScanQREventResponse implements CameraOracleEvent {
        public Log log;

        public BigInteger _commandId;

        public BigInteger _sessionId;

        public Boolean _lights;
    }

    public static class StopScanQREventResponse implements CameraOracleEvent {
        public Log log;

        public BigInteger _commandId;

        public BigInteger _sessionId;
    }
}
