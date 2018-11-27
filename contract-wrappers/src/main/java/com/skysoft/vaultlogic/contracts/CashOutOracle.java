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
public class CashOutOracle extends Contract {
    private static final String BINARY = "0x60806040523480156200001157600080fd5b506040516020806200148b8339810160408181529151828201909252600f8082527f636173682d6f75742d6f7261636c6500000000000000000000000000000000006020830190815260008054600160a060020a031916331790558392916200007d91600191620002b8565b505080600160a060020a031663713b563f6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015620000d757600080fd5b505af1158015620000ec573d6000803e3d6000fd5b505050506040513d60208110156200010357600080fd5b505160038054600160a060020a031916600160a060020a03928316179055604080517fd0496d6a00000000000000000000000000000000000000000000000000000000815290519183169163d0496d6a916004808201926020929091908290030181600087803b1580156200017757600080fd5b505af11580156200018c573d6000803e3d6000fd5b505050506040513d6020811015620001a357600080fd5b505160028054600160a060020a031916600160a060020a03928316178082556040517ff2c298be00000000000000000000000000000000000000000000000000000000815260206004820190815260018054610100818316150260001901169490940460248301819052929094169363f2c298be93929091829160449091019084908015620002765780601f106200024a5761010080835404028352916020019162000276565b820191906000526020600020905b8154815290600101906020018083116200025857829003601f168201915b505092505050600060405180830381600087803b1580156200029757600080fd5b505af1158015620002ac573d6000803e3d6000fd5b5050505050506200035d565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10620002fb57805160ff19168380011785556200032b565b828001600101855582156200032b579182015b828111156200032b5782518255916020019190600101906200030e565b50620003399291506200033d565b5090565b6200035a91905b8082111562000339576000815560010162000344565b90565b61111e806200036d6000396000f3006080604052600436106100da5763ffffffff7c010000000000000000000000000000000000000000000000000000000060003504166306fdde0381146100df578063105ecd49146101695780631188ad251461018357806341c0e1b5146101e757806352bfd1ce146101fc5780635a2c5cd0146102145780635d51e5b51461022c57806375da247a14610244578063874e44b91461025c5780638da5cb5b14610274578063a0e36aba146102a5578063c2a92192146102bd578063cd0ce82a146102d5578063db118f2f146102ed578063ed57310714610351575b600080fd5b3480156100eb57600080fd5b506100f4610369565b6040805160208082528351818301528351919283929083019185019080838360005b8381101561012e578181015183820152602001610116565b50505050905090810190601f16801561015b5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561017557600080fd5b506101816004356103f6565b005b34801561018f57600080fd5b50604080516064356004818101356020818102858101820190965281855261018195833595602480359660443596369690956084959290930192909182918501908490808284375094975061042c9650505050505050565b3480156101f357600080fd5b506101816104bf565b34801561020857600080fd5b5061018160043561053c565b34801561022057600080fd5b506101816004356106af565b34801561023857600080fd5b506101816004356106e5565b34801561025057600080fd5b5061018160043561083d565b34801561026857600080fd5b50610181600435610995565b34801561028057600080fd5b50610289610aed565b60408051600160a060020a039092168252519081900360200190f35b3480156102b157600080fd5b50610181600435610afc565b3480156102c957600080fd5b50610181600435610c54565b3480156102e157600080fd5b50610181600435610dac565b3480156102f957600080fd5b506040805160643560048181013560208181028581018201909652818552610181958335956024803596604435963696909560849592909301929091829185019084908082843750949750610f049650505050505050565b34801561035d57600080fd5b50610181600435610f7a565b60018054604080516020600284861615610100026000190190941693909304601f810184900484028201840190925281815292918301828280156103ee5780601f106103c3576101008083540402835291602001916103ee565b820191906000526020600020905b8154815290600101906020018083116103d157829003601f168201915b505050505081565b6040805182815290517f2967346fffed0ad64b0253ca5c77f4c36508615c35af3ca38a34302eeb36d1789181900360200190a150565b7ff0fb88fbd2e502da9cf0967d342066f74c1dc15370c2059a458ff31b1d3267e4848484846040518085815260200184815260200183815260200180602001828103825283818151815260200191508051906020019060200280838360005b838110156104a357818101518382015260200161048b565b505050509050019550505050505060405180910390a150505050565b600054600160a060020a0316321461053857604080517f08c379a000000000000000000000000000000000000000000000000000000000815260206004820152601260248201527f6f6e6c79206f776e657220616c6c6f7765640000000000000000000000000000604482015290519081900360640190fd5b6000ff5b600254604080518082018252601081526000805160206110d38339815191526020808301918252925160e160020a63349f642f02815260048101938452825160248201528251600160a060020a039095169463693ec85e949283926044019180838360005b838110156105b95781810151838201526020016105a1565b50505050905090810190601f1680156105e65780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b15801561060557600080fd5b505af1158015610619573d6000803e3d6000fd5b505050506040513d602081101561062f57600080fd5b5051604080517f882d5f7a000000000000000000000000000000000000000000000000000000008152600481018490529051600160a060020a039092169163882d5f7a9160248082019260009290919082900301818387803b15801561069457600080fd5b505af11580156106a8573d6000803e3d6000fd5b5050505050565b6040805182815290517fc9d1053d14afbe69aa453e0292458589d5a5497bae1ab0d880f327d8fe12f6b09181900360200190a150565b600254604080518082018252601081526000805160206110d38339815191526020808301918252925160e160020a63349f642f02815260048101938452825160248201528251600160a060020a039095169463693ec85e949283926044019180838360005b8381101561076257818101518382015260200161074a565b50505050905090810190601f16801561078f5780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b1580156107ae57600080fd5b505af11580156107c2573d6000803e3d6000fd5b505050506040513d60208110156107d857600080fd5b5051604080517fd8fb4c60000000000000000000000000000000000000000000000000000000008152600481018490529051600160a060020a039092169163d8fb4c609160248082019260009290919082900301818387803b15801561069457600080fd5b600254604080518082018252601081526000805160206110d38339815191526020808301918252925160e160020a63349f642f02815260048101938452825160248201528251600160a060020a039095169463693ec85e949283926044019180838360005b838110156108ba5781810151838201526020016108a2565b50505050905090810190601f1680156108e75780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b15801561090657600080fd5b505af115801561091a573d6000803e3d6000fd5b505050506040513d602081101561093057600080fd5b5051604080517f5940c655000000000000000000000000000000000000000000000000000000008152600481018490529051600160a060020a0390921691635940c6559160248082019260009290919082900301818387803b15801561069457600080fd5b600254604080518082018252601081526000805160206110d38339815191526020808301918252925160e160020a63349f642f02815260048101938452825160248201528251600160a060020a039095169463693ec85e949283926044019180838360005b83811015610a125781810151838201526020016109fa565b50505050905090810190601f168015610a3f5780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b158015610a5e57600080fd5b505af1158015610a72573d6000803e3d6000fd5b505050506040513d6020811015610a8857600080fd5b5051604080517fe79be969000000000000000000000000000000000000000000000000000000008152600481018490529051600160a060020a039092169163e79be9699160248082019260009290919082900301818387803b15801561069457600080fd5b600054600160a060020a031681565b600254604080518082018252601081526000805160206110d38339815191526020808301918252925160e160020a63349f642f02815260048101938452825160248201528251600160a060020a039095169463693ec85e949283926044019180838360005b83811015610b79578181015183820152602001610b61565b50505050905090810190601f168015610ba65780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b158015610bc557600080fd5b505af1158015610bd9573d6000803e3d6000fd5b505050506040513d6020811015610bef57600080fd5b5051604080517fc61307af000000000000000000000000000000000000000000000000000000008152600481018490529051600160a060020a039092169163c61307af9160248082019260009290919082900301818387803b15801561069457600080fd5b600254604080518082018252601081526000805160206110d38339815191526020808301918252925160e160020a63349f642f02815260048101938452825160248201528251600160a060020a039095169463693ec85e949283926044019180838360005b83811015610cd1578181015183820152602001610cb9565b50505050905090810190601f168015610cfe5780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b158015610d1d57600080fd5b505af1158015610d31573d6000803e3d6000fd5b505050506040513d6020811015610d4757600080fd5b5051604080517f7eaa901f000000000000000000000000000000000000000000000000000000008152600481018490529051600160a060020a0390921691637eaa901f9160248082019260009290919082900301818387803b15801561069457600080fd5b600254604080518082018252601081526000805160206110d38339815191526020808301918252925160e160020a63349f642f02815260048101938452825160248201528251600160a060020a039095169463693ec85e949283926044019180838360005b83811015610e29578181015183820152602001610e11565b50505050905090810190601f168015610e565780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b158015610e7557600080fd5b505af1158015610e89573d6000803e3d6000fd5b505050506040513d6020811015610e9f57600080fd5b5051604080517fdf37e8d8000000000000000000000000000000000000000000000000000000008152600481018490529051600160a060020a039092169163df37e8d89160248082019260009290919082900301818387803b15801561069457600080fd5b7fa9834ca918477210355c5749bed6bba83abd538c47bdd0c0f3a32cd7c6930e9184848484604051808581526020018481526020018381526020018060200182810382528381815181526020019150805190602001906020028083836000838110156104a357818101518382015260200161048b565b600254604080518082018252601081526000805160206110d38339815191526020808301918252925160e160020a63349f642f02815260048101938452825160248201528251600160a060020a039095169463693ec85e949283926044019180838360005b83811015610ff7578181015183820152602001610fdf565b50505050905090810190601f1680156110245780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b15801561104357600080fd5b505af1158015611057573d6000803e3d6000fd5b505050506040513d602081101561106d57600080fd5b5051604080517f94024af4000000000000000000000000000000000000000000000000000000008152600481018490529051600160a060020a03909216916394024af49160248082019260009290919082900301818387803b15801561069457600080fd00636173682d6f75742d6d616e6167657200000000000000000000000000000000a165627a7a72305820bb4a64dfe448667170a9ca2dd64614f5d5fac1d0434a3e37d53fb27333204b020029";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_KILL = "kill";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_ONNEXTOPENCASHOUT = "onNextOpenCashOut";

    public static final String FUNC_SUCCESSOPEN = "successOpen";

    public static final String FUNC_FAILOPEN = "failOpen";

    public static final String FUNC_ONNEXTVALIDATECASHOUT = "onNextValidateCashOut";

    public static final String FUNC_SUCCESSVALIDATE = "successValidate";

    public static final String FUNC_FAILVALIDATE = "failValidate";

    public static final String FUNC_ONNEXTCLOSECASHOUT = "onNextCloseCashOut";

    public static final String FUNC_SUCCESSCLOSE = "successClose";

    public static final String FUNC_FAILCLOSE = "failClose";

    public static final String FUNC_ONNEXTROLLBACKCASHOUT = "onNextRollbackCashOut";

    public static final String FUNC_SUCCESSROLLBACK = "successRollback";

    public static final String FUNC_FAILROLLBACK = "failRollback";

    public static final Event OPENCASHOUT_EVENT = new Event("OpenCashOut", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    public static final Event VALIDATECASHOUT_EVENT = new Event("ValidateCashOut", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<DynamicArray<Uint256>>() {}));
    ;

    public static final Event CLOSECASHOUT_EVENT = new Event("CloseCashOut", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<DynamicArray<Uint256>>() {}));
    ;

    public static final Event ROLLBACKCASHOUT_EVENT = new Event("RollbackCashOut", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
        _addresses.put("4447", "0x9efec315e368e8812025b85b399a69513cd0e716");
        _addresses.put("89354", "0x21610c94ebc106a1b91f7093c5fbb185dc907d09");
    }

    protected CashOutOracle(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected CashOutOracle(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
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

    public static RemoteCall<CashOutOracle> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _config) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_config)));
        return deployRemoteCall(CashOutOracle.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static RemoteCall<CashOutOracle> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _config) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_config)));
        return deployRemoteCall(CashOutOracle.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public List<OpenCashOutEventResponse> getOpenCashOutEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(OPENCASHOUT_EVENT, transactionReceipt);
        ArrayList<OpenCashOutEventResponse> responses = new ArrayList<OpenCashOutEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OpenCashOutEventResponse typedResponse = new OpenCashOutEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._commandId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<OpenCashOutEventResponse> openCashOutEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, OpenCashOutEventResponse>() {
            @Override
            public OpenCashOutEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(OPENCASHOUT_EVENT, log);
                OpenCashOutEventResponse typedResponse = new OpenCashOutEventResponse();
                typedResponse.log = log;
                typedResponse._commandId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<OpenCashOutEventResponse> openCashOutEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OPENCASHOUT_EVENT));
        return openCashOutEventObservable(filter);
    }

    public List<ValidateCashOutEventResponse> getValidateCashOutEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(VALIDATECASHOUT_EVENT, transactionReceipt);
        ArrayList<ValidateCashOutEventResponse> responses = new ArrayList<ValidateCashOutEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ValidateCashOutEventResponse typedResponse = new ValidateCashOutEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._commandId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._sessionId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse._toWithdraw = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse._bills = (List<Uint256>) eventValues.getNonIndexedValues().get(3).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<ValidateCashOutEventResponse> validateCashOutEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, ValidateCashOutEventResponse>() {
            @Override
            public ValidateCashOutEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(VALIDATECASHOUT_EVENT, log);
                ValidateCashOutEventResponse typedResponse = new ValidateCashOutEventResponse();
                typedResponse.log = log;
                typedResponse._commandId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._sessionId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse._toWithdraw = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse._bills = (List<Uint256>) eventValues.getNonIndexedValues().get(3).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<ValidateCashOutEventResponse> validateCashOutEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(VALIDATECASHOUT_EVENT));
        return validateCashOutEventObservable(filter);
    }

    public List<CloseCashOutEventResponse> getCloseCashOutEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CLOSECASHOUT_EVENT, transactionReceipt);
        ArrayList<CloseCashOutEventResponse> responses = new ArrayList<CloseCashOutEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            CloseCashOutEventResponse typedResponse = new CloseCashOutEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._commandId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._sessionId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse._toWithdraw = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse._bills = (List<Uint256>) eventValues.getNonIndexedValues().get(3).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<CloseCashOutEventResponse> closeCashOutEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, CloseCashOutEventResponse>() {
            @Override
            public CloseCashOutEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CLOSECASHOUT_EVENT, log);
                CloseCashOutEventResponse typedResponse = new CloseCashOutEventResponse();
                typedResponse.log = log;
                typedResponse._commandId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._sessionId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse._toWithdraw = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse._bills = (List<Uint256>) eventValues.getNonIndexedValues().get(3).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<CloseCashOutEventResponse> closeCashOutEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CLOSECASHOUT_EVENT));
        return closeCashOutEventObservable(filter);
    }

    public List<RollbackCashOutEventResponse> getRollbackCashOutEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ROLLBACKCASHOUT_EVENT, transactionReceipt);
        ArrayList<RollbackCashOutEventResponse> responses = new ArrayList<RollbackCashOutEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RollbackCashOutEventResponse typedResponse = new RollbackCashOutEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._commandId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<RollbackCashOutEventResponse> rollbackCashOutEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, RollbackCashOutEventResponse>() {
            @Override
            public RollbackCashOutEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ROLLBACKCASHOUT_EVENT, log);
                RollbackCashOutEventResponse typedResponse = new RollbackCashOutEventResponse();
                typedResponse.log = log;
                typedResponse._commandId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<RollbackCashOutEventResponse> rollbackCashOutEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ROLLBACKCASHOUT_EVENT));
        return rollbackCashOutEventObservable(filter);
    }

    public RemoteCall<TransactionReceipt> onNextOpenCashOut(BigInteger _commandId) {
        final Function function = new Function(
                FUNC_ONNEXTOPENCASHOUT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_commandId)), 
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

    public RemoteCall<TransactionReceipt> failOpen(BigInteger _commandId) {
        final Function function = new Function(
                FUNC_FAILOPEN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_commandId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> onNextValidateCashOut(BigInteger _commandId, BigInteger _sessionId, BigInteger _toWithdraw, List<BigInteger> _bills) {
        final Function function = new Function(
                FUNC_ONNEXTVALIDATECASHOUT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_commandId), 
                new org.web3j.abi.datatypes.generated.Uint256(_sessionId), 
                new org.web3j.abi.datatypes.generated.Uint256(_toWithdraw), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint256>(
                        org.web3j.abi.Utils.typeMap(_bills, org.web3j.abi.datatypes.generated.Uint256.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> successValidate(BigInteger _commandId) {
        final Function function = new Function(
                FUNC_SUCCESSVALIDATE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_commandId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> failValidate(BigInteger _commandId) {
        final Function function = new Function(
                FUNC_FAILVALIDATE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_commandId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> onNextCloseCashOut(BigInteger _commandId, BigInteger _sessionId, BigInteger _toWithdraw, List<BigInteger> _bills) {
        final Function function = new Function(
                FUNC_ONNEXTCLOSECASHOUT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_commandId), 
                new org.web3j.abi.datatypes.generated.Uint256(_sessionId), 
                new org.web3j.abi.datatypes.generated.Uint256(_toWithdraw), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint256>(
                        org.web3j.abi.Utils.typeMap(_bills, org.web3j.abi.datatypes.generated.Uint256.class))), 
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

    public RemoteCall<TransactionReceipt> onNextRollbackCashOut(BigInteger _commandId) {
        final Function function = new Function(
                FUNC_ONNEXTROLLBACKCASHOUT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_commandId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> successRollback(BigInteger _commandId) {
        final Function function = new Function(
                FUNC_SUCCESSROLLBACK, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_commandId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> failRollback(BigInteger _commandId) {
        final Function function = new Function(
                FUNC_FAILROLLBACK, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_commandId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static CashOutOracle load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new CashOutOracle(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static CashOutOracle load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new CashOutOracle(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public interface CashOutOracleEvent extends SmartContractEvent {
    }

    public static class OpenCashOutEventResponse implements CashOutOracleEvent {
        public Log log;

        public BigInteger _commandId;
    }

    public static class ValidateCashOutEventResponse implements CashOutOracleEvent {
        public Log log;

        public BigInteger _commandId;

        public BigInteger _sessionId;

        public BigInteger _toWithdraw;

        public List<Uint256> _bills;
    }

    public static class CloseCashOutEventResponse implements CashOutOracleEvent {
        public Log log;

        public BigInteger _commandId;

        public BigInteger _sessionId;

        public BigInteger _toWithdraw;

        public List<Uint256> _bills;
    }

    public static class RollbackCashOutEventResponse implements CashOutOracleEvent {
        public Log log;

        public BigInteger _commandId;
    }
}
