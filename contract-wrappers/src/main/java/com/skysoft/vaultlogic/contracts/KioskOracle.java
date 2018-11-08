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
public class KioskOracle extends Contract {
    private static final String BINARY = "0x60806040523480156200001157600080fd5b5060405160208062000d6a8339810160408181529151828201909252600c8082527f6b696f736b2d6f7261636c6500000000000000000000000000000000000000006020830190815260008054600160a060020a031916331790558392916200007d91600191620002b8565b505080600160a060020a031663713b563f6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015620000d757600080fd5b505af1158015620000ec573d6000803e3d6000fd5b505050506040513d60208110156200010357600080fd5b505160038054600160a060020a031916600160a060020a03928316179055604080517fd0496d6a00000000000000000000000000000000000000000000000000000000815290519183169163d0496d6a916004808201926020929091908290030181600087803b1580156200017757600080fd5b505af11580156200018c573d6000803e3d6000fd5b505050506040513d6020811015620001a357600080fd5b505160028054600160a060020a031916600160a060020a03928316178082556040517ff2c298be00000000000000000000000000000000000000000000000000000000815260206004820190815260018054610100818316150260001901169490940460248301819052929094169363f2c298be93929091829160449091019084908015620002765780601f106200024a5761010080835404028352916020019162000276565b820191906000526020600020905b8154815290600101906020018083116200025857829003601f168201915b505092505050600060405180830381600087803b1580156200029757600080fd5b505af1158015620002ac573d6000803e3d6000fd5b5050505050506200035d565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10620002fb57805160ff19168380011785556200032b565b828001600101855582156200032b579182015b828111156200032b5782518255916020019190600101906200030e565b50620003399291506200033d565b5090565b6200035a91905b8082111562000339576000815560010162000344565b90565b6109fd806200036d6000396000f3006080604052600436106100775763ffffffff7c010000000000000000000000000000000000000000000000000000000060003504166306fdde03811461007c578063264561681461010657806341c0e1b5146102925780634a43bfe6146102a757806354c40740146102bf5780638da5cb5b146102da575b600080fd5b34801561008857600080fd5b5061009161030b565b6040805160208082528351818301528351919283929083019185019080838360005b838110156100cb5781810151838201526020016100b3565b50505050905090810190601f1680156100f85780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561011257600080fd5b5060408051602060046024803582810135601f810185900485028601850190965285855261029095833595369560449491939091019190819084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a99988101979196509182019450925082915084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a99988101979196509182019450925082915084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a999881019791965091820194509250829150840183828082843750506040805187358901803560208181028481018201909552818452989b9a998901989297509082019550935083925085019084908082843750506040805187358901803560208181028481018201909552818452989b9a9989019892975090820195509350839250850190849080828437509497506103989650505050505050565b005b34801561029e57600080fd5b5061029061076b565b3480156102b357600080fd5b506102906004356107e8565b3480156102cb57600080fd5b50610290600435602435610983565b3480156102e657600080fd5b506102ef6109c2565b60408051600160a060020a039092168252519081900360200190f35b60018054604080516020600284861615610100026000190190941693909304601f810184900484028201840190925281815292918301828280156103905780601f1061036557610100808354040283529160200191610390565b820191906000526020600020905b81548152906001019060200180831161037357829003601f168201915b505050505081565b600254604080518082018252600d81527f6b696f736b2d6d616e6167657200000000000000000000000000000000000000602080830191825292517f693ec85e00000000000000000000000000000000000000000000000000000000815260048101938452825160248201528251600160a060020a039095169463693ec85e949283926044019180838360005b8381101561043d578181015183820152602001610425565b50505050905090810190601f16801561046a5780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b15801561048957600080fd5b505af115801561049d573d6000803e3d6000fd5b505050506040513d60208110156104b357600080fd5b50516040517f3bf201e00000000000000000000000000000000000000000000000000000000081526004810189815260e060248301908152895160e48401528951600160a060020a0390941693633bf201e0938c938c938c938c938c938c938c939192909160448201916064810191608482019160a481019160c4820191610104019060208f019080838360005b83811015610559578181015183820152602001610541565b50505050905090810190601f1680156105865780820380516001836020036101000a031916815260200191505b5087810386528c5181528c516020918201918e019080838360005b838110156105b95781810151838201526020016105a1565b50505050905090810190601f1680156105e65780820380516001836020036101000a031916815260200191505b5087810385528b5181528b516020918201918d019080838360005b83811015610619578181015183820152602001610601565b50505050905090810190601f1680156106465780820380516001836020036101000a031916815260200191505b5087810384528a5181528a516020918201918c019080838360005b83811015610679578181015183820152602001610661565b50505050905090810190601f1680156106a65780820380516001836020036101000a031916815260200191505b508781038352895181528951602091820191808c01910280838360005b838110156106db5781810151838201526020016106c3565b50505050905001878103825288818151815260200191508051906020019060200280838360005b8381101561071a578181015183820152602001610702565b505050509050019d5050505050505050505050505050600060405180830381600087803b15801561074a57600080fd5b505af115801561075e573d6000803e3d6000fd5b5050505050505050505050565b600054600160a060020a031632146107e457604080517f08c379a000000000000000000000000000000000000000000000000000000000815260206004820152601260248201527f6f6e6c79206f776e657220616c6c6f7765640000000000000000000000000000604482015290519081900360640190fd5b6000ff5b600254604080518082018252600d81527f6b696f736b2d6d616e6167657200000000000000000000000000000000000000602080830191825292517f693ec85e00000000000000000000000000000000000000000000000000000000815260048101938452825160248201528251600160a060020a039095169463693ec85e949283926044019180838360005b8381101561088d578181015183820152602001610875565b50505050905090810190601f1680156108ba5780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b1580156108d957600080fd5b505af11580156108ed573d6000803e3d6000fd5b505050506040513d602081101561090357600080fd5b5051604080517f4a051595000000000000000000000000000000000000000000000000000000008152600481018490529051600160a060020a0390921691634a0515959160248082019260009290919082900301818387803b15801561096857600080fd5b505af115801561097c573d6000803e3d6000fd5b5050505050565b604080518381526020810183905281517fe34a320ea1475ca686926df791a1594dadf29abfa82b41e935ec64544ad738a1929181900390910190a15050565b600054600160a060020a0316815600a165627a7a7230582061d45e2acfb2b0e7d22ce2329b6fe4f93b1775923a25dd338649fa088feb171e0029";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_KILL = "kill";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_ONNEXTGETKIOSKINFO = "onNextGetKioskInfo";

    public static final String FUNC_SUCCESSGETKIOSKINFO = "successGetKioskInfo";

    public static final String FUNC_FAILGETKIOSKINFO = "failGetKioskInfo";

    public static final Event GETKIOSKINFO_EVENT = new Event("GetKioskInfo", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
    }

    protected KioskOracle(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected KioskOracle(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
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

    public static RemoteCall<KioskOracle> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _config) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_config)));
        return deployRemoteCall(KioskOracle.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static RemoteCall<KioskOracle> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _config) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_config)));
        return deployRemoteCall(KioskOracle.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public List<GetKioskInfoEventResponse> getGetKioskInfoEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(GETKIOSKINFO_EVENT, transactionReceipt);
        ArrayList<GetKioskInfoEventResponse> responses = new ArrayList<GetKioskInfoEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            GetKioskInfoEventResponse typedResponse = new GetKioskInfoEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._commandId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._sessionId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<GetKioskInfoEventResponse> getKioskInfoEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, GetKioskInfoEventResponse>() {
            @Override
            public GetKioskInfoEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(GETKIOSKINFO_EVENT, log);
                GetKioskInfoEventResponse typedResponse = new GetKioskInfoEventResponse();
                typedResponse.log = log;
                typedResponse._commandId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._sessionId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<GetKioskInfoEventResponse> getKioskInfoEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(GETKIOSKINFO_EVENT));
        return getKioskInfoEventObservable(filter);
    }

    public RemoteCall<TransactionReceipt> onNextGetKioskInfo(BigInteger _commandId, BigInteger _sessionId) {
        final Function function = new Function(
                FUNC_ONNEXTGETKIOSKINFO, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_commandId), 
                new org.web3j.abi.datatypes.generated.Uint256(_sessionId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> successGetKioskInfo(BigInteger _commandId, String _id, String _location, String _name, String _timezone, List<BigInteger> _bills, List<BigInteger> _amounts) {
        final Function function = new Function(
                FUNC_SUCCESSGETKIOSKINFO, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_commandId), 
                new org.web3j.abi.datatypes.Utf8String(_id), 
                new org.web3j.abi.datatypes.Utf8String(_location), 
                new org.web3j.abi.datatypes.Utf8String(_name), 
                new org.web3j.abi.datatypes.Utf8String(_timezone), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint256>(
                        org.web3j.abi.Utils.typeMap(_bills, org.web3j.abi.datatypes.generated.Uint256.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint256>(
                        org.web3j.abi.Utils.typeMap(_amounts, org.web3j.abi.datatypes.generated.Uint256.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> failGetKioskInfo(BigInteger _commandId) {
        final Function function = new Function(
                FUNC_FAILGETKIOSKINFO, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_commandId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static KioskOracle load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new KioskOracle(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static KioskOracle load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new KioskOracle(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public interface KioskOracleEvent extends SmartContractEvent {
    }

    public static class GetKioskInfoEventResponse implements KioskOracleEvent {
        public Log log;

        public BigInteger _commandId;

        public BigInteger _sessionId;
    }
}
