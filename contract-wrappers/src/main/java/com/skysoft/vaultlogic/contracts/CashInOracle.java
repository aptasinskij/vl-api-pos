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
    private static final String BINARY = "0x60806040523480156200001157600080fd5b5060405160208062001b968339810160408181529151828201909252600e8082527f636173682d696e2d6f7261636c650000000000000000000000000000000000006020830190815260008054600160a060020a031916331781558493909290916200008191600191906200044e565b505081600160a060020a031663713b563f6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015620000db57600080fd5b505af1158015620000f0573d6000803e3d6000fd5b505050506040513d60208110156200010757600080fd5b505160038054600160a060020a031916600160a060020a03928316179055604080517fd0496d6a00000000000000000000000000000000000000000000000000000000815290519184169163d0496d6a916004808201926020929091908290030181600087803b1580156200017b57600080fd5b505af115801562000190573d6000803e3d6000fd5b505050506040513d6020811015620001a757600080fd5b505160028054600160a060020a031916600160a060020a03928316178082556040517f693ec85e00000000000000000000000000000000000000000000000000000000815260206004820190815260018054610100818316150260001901169490940460248301819052929094169363693ec85e939290918291604490910190849080156200027a5780601f106200024e576101008083540402835291602001916200027a565b820191906000526020600020905b8154815290600101906020018083116200025c57829003601f168201915b505092505050602060405180830381600087803b1580156200029b57600080fd5b505af1158015620002b0573d6000803e3d6000fd5b505050506040513d6020811015620002c757600080fd5b50519050600160a060020a038116156200034d5780600160a060020a03166341c0e1b56040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401600060405180830381600087803b1580156200033357600080fd5b505af115801562000348573d6000803e3d6000fd5b505050505b600280546040517ff2c298be00000000000000000000000000000000000000000000000000000000815260206004820190815260018054600019818316156101000201169490940460248301819052600160a060020a039093169363f2c298be9390928291604490910190849080156200040b5780601f10620003df576101008083540402835291602001916200040b565b820191906000526020600020905b815481529060010190602001808311620003ed57829003601f168201915b505092505050600060405180830381600087803b1580156200042c57600080fd5b505af115801562000441573d6000803e3d6000fd5b50505050505050620004f3565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106200049157805160ff1916838001178555620004c1565b82800160010185558215620004c1579182015b82811115620004c1578251825591602001919060010190620004a4565b50620004cf929150620004d3565b5090565b620004f091905b80821115620004cf5760008155600101620004da565b90565b61169380620005036000396000f30060806040526004361061008a5763ffffffff60e060020a600035041663054fa677811461008f57806306fdde03146100bb57806341c0e1b51461014557806352bfd1ce1461015c57806375da247a146101745780638da5cb5b1461018c578063a0e36aba146101bd578063c2a92192146101d5578063c6669ec8146101ed578063f479d37714610205575b600080fd5b34801561009b57600080fd5b506100a7600435610220565b604080519115158252519081900360200190f35b3480156100c757600080fd5b506100d0610299565b6040805160208082528351818301528351919283929083019185019080838360005b8381101561010a5781810151838201526020016100f2565b50505050905090810190601f1680156101375780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561015157600080fd5b5061015a610326565b005b34801561016857600080fd5b5061015a6004356103a3565b34801561018057600080fd5b5061015a60043561053e565b34801561019857600080fd5b506101a1610737565b60408051600160a060020a039092168252519081900360200190f35b3480156101c957600080fd5b5061015a600435610746565b3480156101e157600080fd5b5061015a600435610749565b3480156101f957600080fd5b506100a76004356107c2565b34801561021157600080fd5b5061015a60043560243561082f565b600061022a611619565b60035461024690600160a060020a03168463ffffffff6109d216565b805160208083015160408051888152928301939093528183015290519192507fa0ca351a143349b8bf0cb616b17c89387132218bcb2408bd70411d906787ad2d919081900360600190a150600192915050565b60018054604080516020600284861615610100026000190190941693909304601f8101849004840282018401909252818152929183018282801561031e5780601f106102f35761010080835404028352916020019161031e565b820191906000526020600020905b81548152906001019060200180831161030157829003601f168201915b505050505081565b600054600160a060020a0316321461039f57604080517f08c379a000000000000000000000000000000000000000000000000000000000815260206004820152601260248201527f6f6e6c79206f776e657220616c6c6f7765640000000000000000000000000000604482015290519081900360640190fd5b6000ff5b600254604080518082018252601581527f636173682d6368616e6e656c732d6d616e616765720000000000000000000000602080830191825292517f693ec85e00000000000000000000000000000000000000000000000000000000815260048101938452825160248201528251600160a060020a039095169463693ec85e949283926044019180838360005b83811015610448578181015183820152602001610430565b50505050905090810190601f1680156104755780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b15801561049457600080fd5b505af11580156104a8573d6000803e3d6000fd5b505050506040513d60208110156104be57600080fd5b5051604080517f882d5f7a000000000000000000000000000000000000000000000000000000008152600481018490529051600160a060020a039092169163882d5f7a9160248082019260009290919082900301818387803b15801561052357600080fd5b505af1158015610537573d6000803e3d6000fd5b5050505050565b600054600160a060020a031633146105b757604080517f08c379a000000000000000000000000000000000000000000000000000000000815260206004820152601260248201527f6f6e6c79206f776e657220616c6c6f7765640000000000000000000000000000604482015290519081900360640190fd5b600254604080518082018252601581527f636173682d6368616e6e656c732d6d616e616765720000000000000000000000602080830191825292517f693ec85e00000000000000000000000000000000000000000000000000000000815260048101938452825160248201528251600160a060020a039095169463693ec85e949283926044019180838360005b8381101561065c578181015183820152602001610644565b50505050905090810190601f1680156106895780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b1580156106a857600080fd5b505af11580156106bc573d6000803e3d6000fd5b505050506040513d60208110156106d257600080fd5b5051604080517f5940c655000000000000000000000000000000000000000000000000000000008152600481018490529051600160a060020a0390921691635940c6559160248082019260009290919082900301818387803b15801561052357600080fd5b600054600160a060020a031681565b50565b600054600160a060020a0316331461074657604080517f08c379a000000000000000000000000000000000000000000000000000000000815260206004820152601260248201527f6f6e6c79206f776e657220616c6c6f7765640000000000000000000000000000604482015290519081900360640190fd5b60006107cc611647565b6003546107e890600160a060020a03168463ffffffff61117616565b8051604080519182526020820186905280519293507fe118fe67faa4f47f4ef978dcb745bb527acd9dcbae480e8ce8c3dcbd48607c0792918290030190a150600192915050565b600254604080518082018252601581527f636173682d6368616e6e656c732d6d616e616765720000000000000000000000602080830191825292517f693ec85e00000000000000000000000000000000000000000000000000000000815260048101938452825160248201528251600160a060020a039095169463693ec85e949283926044019180838360005b838110156108d45781810151838201526020016108bc565b50505050905090810190601f1680156109015780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b15801561092057600080fd5b505af1158015610934573d6000803e3d6000fd5b505050506040513d602081101561094a57600080fd5b5051604080517f54e027d800000000000000000000000000000000000000000000000000000000815260048101859052602481018490529051600160a060020a03909216916354e027d89160448082019260009290919082900301818387803b1580156109b657600080fd5b505af11580156109ca573d6000803e3d6000fd5b505050505050565b6109da611619565b82600160a060020a031663e82617fb6040805190810160405280601f81526020017f636173682d696e2e6f70656e2e73657373696f6e2e69643a75696e7432353600815250846040516020018083805190602001908083835b60208310610a525780518252601f199092019160209182019101610a33565b51815160209384036101000a600019018019909216911617905292019384525060408051808503815293820190819052835193945092839250908401908083835b60208310610ab25780518252601f199092019160209182019101610a93565b51815160209384036101000a60001901801990921691161790526040805192909401829003822063ffffffff881660e060020a0283526004830152925160248083019650939450929083900301905081600087803b158015610b1357600080fd5b505af1158015610b27573d6000803e3d6000fd5b505050506040513d6020811015610b3d57600080fd5b50518152604080518082018252601f8082527f636173682d696e2e6f70656e2e6d61782d616d6f756e743a75696e743235360060208381019182529351600160a060020a0388169463e82617fb94938893929091019182918083835b60208310610bb85780518252601f199092019160209182019101610b99565b51815160209384036101000a600019018019909216911617905292019384525060408051808503815293820190819052835193945092839250908401908083835b60208310610c185780518252601f199092019160209182019101610bf9565b51815160209384036101000a60001901801990921691161790526040805192909401829003822063ffffffff881660e060020a0283526004830152925160248083019650939450929083900301905081600087803b158015610c7957600080fd5b505af1158015610c8d573d6000803e3d6000fd5b505050506040513d6020811015610ca357600080fd5b5051602082810191909152604080518082018252601d8082527f636173682d696e2e6f70656e2e737563636573733a66756e6374696f6e0000008285019081529251600160a060020a038816946350ca291d94889392909101918291908083835b60208310610d235780518252601f199092019160209182019101610d04565b51815160209384036101000a600019018019909216911617905292019384525060408051808503815293820190819052835193945092839250908401908083835b60208310610d835780518252601f199092019160209182019101610d64565b51815160209384036101000a60001901801990921691161790526040805192909401829003822063ffffffff881660e060020a0283526004830152925160248083019650939450929083900301905081600087803b158015610de457600080fd5b505af1158015610df8573d6000803e3d6000fd5b505050506040513d6020811015610e0e57600080fd5b50516401000000006c0100000000000000000000000082040263ffffffff680100000000000000009283900416170260408083019190915280518082018252601c8082527f636173682d696e2e6f70656e2e7570646174653a66756e6374696f6e0000000060208381019182529351600160a060020a038816946377e645a294938893929091019182918083835b60208310610ebb5780518252601f199092019160209182019101610e9c565b51815160209384036101000a600019018019909216911617905292019384525060408051808503815293820190819052835193945092839250908401908083835b60208310610f1b5780518252601f199092019160209182019101610efc565b51815160209384036101000a60001901801990921691161790526040805192909401829003822063ffffffff881660e060020a0283526004830152925160248083019650939450929083900301905081600087803b158015610f7c57600080fd5b505af1158015610f90573d6000803e3d6000fd5b505050506040513d6020811015610fa657600080fd5b50516401000000006c0100000000000000000000000082040263ffffffff68010000000000000000928390041617026060820152604080518082018252601a8082527f636173682d696e2e6f70656e2e6661696c3a66756e6374696f6e00000000000060208381019182529351600160a060020a0388169463f8b98ab194938893929091019182918083835b602083106110515780518252601f199092019160209182019101611032565b51815160209384036101000a600019018019909216911617905292019384525060408051808503815293820190819052835193945092839250908401908083835b602083106110b15780518252601f199092019160209182019101611092565b51815160209384036101000a60001901801990921691161790526040805192909401829003822063ffffffff881660e060020a0283526004830152925160248083019650939450929083900301905081600087803b15801561111257600080fd5b505af1158015611126573d6000803e3d6000fd5b505050506040513d602081101561113c57600080fd5b50516401000000006c0100000000000000000000000082040263ffffffff6801000000000000000092839004161702608082015292915050565b61117e611647565b82600160a060020a031663e82617fb6040805190810160405280602081526020017f636173682d696e2e636c6f73652e73657373696f6e2e69643a75696e74323536815250846040516020018083805190602001908083835b602083106111f65780518252601f1990920191602091820191016111d7565b51815160209384036101000a600019018019909216911617905292019384525060408051808503815293820190819052835193945092839250908401908083835b602083106112565780518252601f199092019160209182019101611237565b51815160209384036101000a60001901801990921691161790526040805192909401829003822063ffffffff881660e060020a0283526004830152925160248083019650939450929083900301905081600087803b1580156112b757600080fd5b505af11580156112cb573d6000803e3d6000fd5b505050506040513d60208110156112e157600080fd5b50518152604080518082018252601e8082527f636173682d696e2e636c6f73652e737563636573733a66756e6374696f6e000060208381019182529351600160a060020a038816946350ca291d94938893929091019182918083835b6020831061135c5780518252601f19909201916020918201910161133d565b51815160209384036101000a600019018019909216911617905292019384525060408051808503815293820190819052835193945092839250908401908083835b602083106113bc5780518252601f19909201916020918201910161139d565b51815160209384036101000a60001901801990921691161790526040805192909401829003822063ffffffff881660e060020a0283526004830152925160248083019650939450929083900301905081600087803b15801561141d57600080fd5b505af1158015611431573d6000803e3d6000fd5b505050506040513d602081101561144757600080fd5b50516401000000006c0100000000000000000000000082040263ffffffff6801000000000000000092839004161702602082810191909152604080518082018252601b8082527f636173682d696e2e636c6f73652e6661696c3a66756e6374696f6e00000000008285019081529251600160a060020a038816946350ca291d94889392909101918291908083835b602083106114f45780518252601f1990920191602091820191016114d5565b51815160209384036101000a600019018019909216911617905292019384525060408051808503815293820190819052835193945092839250908401908083835b602083106115545780518252601f199092019160209182019101611535565b51815160209384036101000a60001901801990921691161790526040805192909401829003822063ffffffff881660e060020a0283526004830152925160248083019650939450929083900301905081600087803b1580156115b557600080fd5b505af11580156115c9573d6000803e3d6000fd5b505050506040513d60208110156115df57600080fd5b50516401000000006c0100000000000000000000000082040263ffffffff6801000000000000000092839004161702604082015292915050565b6040805160a08101825260008082526020820181905291810182905260608101829052608081019190915290565b6040805160608101825260008082526020820181905291810191909152905600a165627a7a723058202a757940424573ee4ee18e8dadc035b4386b52154b1742aed93c9ca61d663fce0029";

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

    public static final Event OPENCASHACCEPTOR_EVENT = new Event("OpenCashAcceptor", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event CLOSECASHACCEPTOR_EVENT = new Event("CloseCashAcceptor", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event OPENCASHIN_EVENT = new Event("OpenCashIn", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
        _addresses.put("84107", "0x562d1aa2b5fb8eca9c62d540785b3f459da82c1a");
        _addresses.put("37609", "0x085b21ff49be71548ddc927174c83605213649cc");
        _addresses.put("5777", "0xd112aa126b0c081e5926d6e830228ba7634dfb0d");
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
            typedResponse._maxAmount = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
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
                typedResponse._maxAmount = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
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

    public RemoteCall<TransactionReceipt> onNextOpenCashIn(BigInteger _commandId) {
        final Function function = new Function(
                FUNC_ONNEXTOPENCASHIN, 
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

    public RemoteCall<TransactionReceipt> onNextCloseCashIn(BigInteger _commandId) {
        final Function function = new Function(
                FUNC_ONNEXTCLOSECASHIN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_commandId)), 
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

    public static class OpenCashAcceptorEventResponse implements CashInOracleEvent {
        public Log log;

        public BigInteger sessionId;

        public BigInteger channelId;

        public BigInteger channelStatus;

        public BigInteger _maxAmount;
    }

    public static class CloseCashAcceptorEventResponse implements CashInOracleEvent {
        public Log log;

        public BigInteger sessionId;

        public BigInteger channelId;
    }

    public static class OpenCashInEventResponse implements CashInOracleEvent {
        public Log log;

        public BigInteger _commandId;

        public BigInteger _sessionId;

        public BigInteger _maxBalance;
    }
}
