package com.skysoft.vaultlogic.contracts;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.contracts.SmartContractEvent;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple3;
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
public class KioskStorage extends Contract {
    private static final String BINARY = "0x60806040523480156200001157600080fd5b5060405160208062001d748339810160408181529151828201909252600d8082527f6b696f736b2d73746f72616765000000000000000000000000000000000000006020830190815260008054600160a060020a031916331790558392916200007d91600191620002b8565b505080600160a060020a031663713b563f6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015620000d757600080fd5b505af1158015620000ec573d6000803e3d6000fd5b505050506040513d60208110156200010357600080fd5b505160038054600160a060020a031916600160a060020a03928316179055604080517fd0496d6a00000000000000000000000000000000000000000000000000000000815290519183169163d0496d6a916004808201926020929091908290030181600087803b1580156200017757600080fd5b505af11580156200018c573d6000803e3d6000fd5b505050506040513d6020811015620001a357600080fd5b505160028054600160a060020a031916600160a060020a03928316178082556040517ff2c298be00000000000000000000000000000000000000000000000000000000815260206004820190815260018054610100818316150260001901169490940460248301819052929094169363f2c298be93929091829160449091019084908015620002765780601f106200024a5761010080835404028352916020019162000276565b820191906000526020600020905b8154815290600101906020018083116200025857829003601f168201915b505092505050600060405180830381600087803b1580156200029757600080fd5b505af1158015620002ac573d6000803e3d6000fd5b5050505050506200035d565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10620002fb57805160ff19168380011785556200032b565b828001600101855582156200032b579182015b828111156200032b5782518255916020019190600101906200030e565b50620003399291506200033d565b5090565b6200035a91905b8082111562000339576000815560010162000344565b90565b611a07806200036d6000396000f3006080604052600436106100535763ffffffff60e060020a60003504166306fdde038114610058578063314fd4dc146100e257806341c0e1b5146101f7578063693ec85e1461020c5780638da5cb5b146103a9575b600080fd5b34801561006457600080fd5b5061006d6103da565b6040805160208082528351818301528351919283929083019185019080838360005b838110156100a757818101518382015260200161008f565b50505050905090810190601f1680156100d45780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b3480156100ee57600080fd5b506040805160206004803580820135601f81018490048402850184019095528484526101f594369492936024939284019190819084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a99988101979196509182019450925082915084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a99988101979196509182019450925082915084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a9998810197919650918201945092508291508401838280828437509497506104679650505050505050565b005b34801561020357600080fd5b506101f561065b565b34801561021857600080fd5b506040805160206004803580820135601f81018490048402850184019095528484526102659436949293602493928401919081908401838280828437509497506106d89650505050505050565b60405180806020018060200180602001848103845287818151815260200191508051906020019080838360005b838110156102aa578181015183820152602001610292565b50505050905090810190601f1680156102d75780820380516001836020036101000a031916815260200191505b50848103835286518152865160209182019188019080838360005b8381101561030a5781810151838201526020016102f2565b50505050905090810190601f1680156103375780820380516001836020036101000a031916815260200191505b50848103825285518152855160209182019187019080838360005b8381101561036a578181015183820152602001610352565b50505050905090810190601f1680156103975780820380516001836020036101000a031916815260200191505b50965050505050505060405180910390f35b3480156103b557600080fd5b506103be610708565b60408051600160a060020a039092168252519081900360200190f35b60018054604080516020600284861615610100026000190190941693909304601f8101849004840282018401909252818152929183018282801561045f5780601f106104345761010080835404028352916020019161045f565b820191906000526020600020905b81548152906001019060200180831161044257829003601f168201915b505050505081565b60035461048690600160a060020a03168585858563ffffffff61071716565b507f9a70d206ebfed2a7f1731124889425f55cd2eafea00afdf1805f8183bbdd0a28848484846040518080602001806020018060200180602001858103855289818151815260200191508051906020019080838360005b838110156104f55781810151838201526020016104dd565b50505050905090810190601f1680156105225780820380516001836020036101000a031916815260200191505b5085810384528851815288516020918201918a019080838360005b8381101561055557818101518382015260200161053d565b50505050905090810190601f1680156105825780820380516001836020036101000a031916815260200191505b50858103835287518152875160209182019189019080838360005b838110156105b557818101518382015260200161059d565b50505050905090810190601f1680156105e25780820380516001836020036101000a031916815260200191505b50858103825286518152865160209182019188019080838360005b838110156106155781810151838201526020016105fd565b50505050905090810190601f1680156106425780820380516001836020036101000a031916815260200191505b509850505050505050505060405180910390a150505050565b600054600160a060020a031632146106d457604080517f08c379a000000000000000000000000000000000000000000000000000000000815260206004820152601260248201527f6f6e6c79206f776e657220616c6c6f7765640000000000000000000000000000604482015290519081900360640190fd5b6000ff5b600354606090819081906106fb90600160a060020a03168563ffffffff61115f16565b9250925092509193909250565b600054600160a060020a031681565b60006107238686611821565b1561078f57604080517f08c379a000000000000000000000000000000000000000000000000000000000815260206004820152601460248201527f4b696f736b20616c726561647920657869737473000000000000000000000000604482015290519081900360640190fd5b85600160a060020a031663f58660666040805190810160405280600e81526020017f6b696f736b5f73686f72745f6964000000000000000000000000000000000000815250876040516020018083805190602001908083835b602083106108075780518252601f1990920191602091820191016107e8565b51815160209384036101000a600019018019909216911617905285519190930192850191508083835b6020831061084f5780518252601f199092019160209182019101610830565b6001836020036101000a038019825116818451168082178552505050505050905001925050506040516020818303038152906040526040518082805190602001908083835b602083106108b35780518252601f199092019160209182019101610894565b51815160209384036101000a60001901801990921691161790526040805192909401829003822063ffffffff881660e060020a02835260048301818152602484019586528e5160448501528e519197508e96509493506064909201919085019080838360005b83811015610931578181015183820152602001610919565b50505050905090810190601f16801561095e5780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b15801561097e57600080fd5b505af1158015610992573d6000803e3d6000fd5b5050505085600160a060020a031663f58660666040805190810160405280601681526020017f6b696f736b5f6c6f636174696f6e5f6164647265737300000000000000000000815250876040516020018083805190602001908083835b60208310610a0e5780518252601f1990920191602091820191016109ef565b51815160209384036101000a600019018019909216911617905285519190930192850191508083835b60208310610a565780518252601f199092019160209182019101610a37565b6001836020036101000a038019825116818451168082178552505050505050905001925050506040516020818303038152906040526040518082805190602001908083835b60208310610aba5780518252601f199092019160209182019101610a9b565b51815160209384036101000a60001901801990921691161790526040805192909401829003822063ffffffff881660e060020a02835260048301818152602484019586528d5160448501528d519197508d96509493506064909201919085019080838360005b83811015610b38578181015183820152602001610b20565b50505050905090810190601f168015610b655780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b158015610b8557600080fd5b505af1158015610b99573d6000803e3d6000fd5b5050505085600160a060020a031663f58660666040805190810160405280600a81526020017f6b696f736b5f6e616d6500000000000000000000000000000000000000000000815250876040516020018083805190602001908083835b60208310610c155780518252601f199092019160209182019101610bf6565b51815160209384036101000a600019018019909216911617905285519190930192850191508083835b60208310610c5d5780518252601f199092019160209182019101610c3e565b6001836020036101000a038019825116818451168082178552505050505050905001925050506040516020818303038152906040526040518082805190602001908083835b60208310610cc15780518252601f199092019160209182019101610ca2565b51815160209384036101000a60001901801990921691161790526040805192909401829003822063ffffffff881660e060020a02835260048301818152602484019586528c5160448501528c519197508c96509493506064909201919085019080838360005b83811015610d3f578181015183820152602001610d27565b50505050905090810190601f168015610d6c5780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b158015610d8c57600080fd5b505af1158015610da0573d6000803e3d6000fd5b5050505085600160a060020a031663f58660666040805190810160405280600f81526020017f6b696f736b5f74696d655f7a6f6e650000000000000000000000000000000000815250876040516020018083805190602001908083835b60208310610e1c5780518252601f199092019160209182019101610dfd565b51815160209384036101000a600019018019909216911617905285519190930192850191508083835b60208310610e645780518252601f199092019160209182019101610e45565b6001836020036101000a038019825116818451168082178552505050505050905001925050506040516020818303038152906040526040518082805190602001908083835b60208310610ec85780518252601f199092019160209182019101610ea9565b51815160209384036101000a60001901801990921691161790526040805192909401829003822063ffffffff881660e060020a02835260048301818152602484019586528b5160448501528b519197508b96509493506064909201919085019080838360005b83811015610f46578181015183820152602001610f2e565b50505050905090810190601f168015610f735780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b158015610f9357600080fd5b505af1158015610fa7573d6000803e3d6000fd5b5050505085600160a060020a0316633eba9ed26040805190810160405280600c81526020017f6b696f736b2e6578697374730000000000000000000000000000000000000000815250876040516020018083805190602001908083835b602083106110235780518252601f199092019160209182019101611004565b51815160209384036101000a600019018019909216911617905285519190930192850191508083835b6020831061106b5780518252601f19909201916020918201910161104c565b6001836020036101000a038019825116818451168082178552505050505050905001925050506040516020818303038152906040526040518082805190602001908083835b602083106110cf5780518252601f1990920191602091820191016110b0565b5181516020939093036101000a60001901801990911692169190911790526040805191909301819003812063ffffffff871660e060020a028252600482015260016024820152915160448084019550600094509092839003019050818387803b15801561113b57600080fd5b505af115801561114f573d6000803e3d6000fd5b5060019998505050505050505050565b606080606061116e8585611821565b15156111db57604080517f08c379a000000000000000000000000000000000000000000000000000000000815260206004820152601360248201527f4b696f736b206973206e6f742065786973747300000000000000000000000000604482015290519081900360640190fd5b84600160a060020a031663a209a29c6040805190810160405280601681526020017f6b696f736b5f6c6f636174696f6e5f6164647265737300000000000000000000815250866040516020018083805190602001908083835b602083106112535780518252601f199092019160209182019101611234565b51815160209384036101000a600019018019909216911617905285519190930192850191508083835b6020831061129b5780518252601f19909201916020918201910161127c565b6001836020036101000a038019825116818451168082178552505050505050905001925050506040516020818303038152906040526040518082805190602001908083835b602083106112ff5780518252601f1990920191602091820191016112e0565b5181516020939093036101000a60001901801990911692169190911790526040805191909301819003812063ffffffff871660e060020a0282526004820152915160248084019550600094509092839003019050818387803b15801561136457600080fd5b505af1158015611378573d6000803e3d6000fd5b505050506040513d6000823e601f3d908101601f1916820160405260208110156113a157600080fd5b8101908080516401000000008111156113b957600080fd5b820160208101848111156113cc57600080fd5b81516401000000008111828201871017156113e657600080fd5b5050929190505050925084600160a060020a031663a209a29c6040805190810160405280600a81526020017f6b696f736b5f6e616d6500000000000000000000000000000000000000000000815250866040516020018083805190602001908083835b602083106114685780518252601f199092019160209182019101611449565b51815160209384036101000a600019018019909216911617905285519190930192850191508083835b602083106114b05780518252601f199092019160209182019101611491565b6001836020036101000a038019825116818451168082178552505050505050905001925050506040516020818303038152906040526040518082805190602001908083835b602083106115145780518252601f1990920191602091820191016114f5565b5181516020939093036101000a60001901801990911692169190911790526040805191909301819003812063ffffffff871660e060020a0282526004820152915160248084019550600094509092839003019050818387803b15801561157957600080fd5b505af115801561158d573d6000803e3d6000fd5b505050506040513d6000823e601f3d908101601f1916820160405260208110156115b657600080fd5b8101908080516401000000008111156115ce57600080fd5b820160208101848111156115e157600080fd5b81516401000000008111828201871017156115fb57600080fd5b5050929190505050915084600160a060020a031663a209a29c6040805190810160405280600f81526020017f6b696f736b5f74696d655f7a6f6e650000000000000000000000000000000000815250866040516020018083805190602001908083835b6020831061167d5780518252601f19909201916020918201910161165e565b51815160209384036101000a600019018019909216911617905285519190930192850191508083835b602083106116c55780518252601f1990920191602091820191016116a6565b6001836020036101000a038019825116818451168082178552505050505050905001925050506040516020818303038152906040526040518082805190602001908083835b602083106117295780518252601f19909201916020918201910161170a565b5181516020939093036101000a60001901801990911692169190911790526040805191909301819003812063ffffffff871660e060020a0282526004820152915160248084019550600094509092839003019050818387803b15801561178e57600080fd5b505af11580156117a2573d6000803e3d6000fd5b505050506040513d6000823e601f3d908101601f1916820160405260208110156117cb57600080fd5b8101908080516401000000008111156117e357600080fd5b820160208101848111156117f657600080fd5b815164010000000081118282018710171561181057600080fd5b505092919050505090509250925092565b600082600160a060020a03166317e7dd226040805190810160405280600c81526020017f6b696f736b2e6578697374730000000000000000000000000000000000000000815250846040516020018083805190602001908083835b6020831061189b5780518252601f19909201916020918201910161187c565b51815160209384036101000a600019018019909216911617905285519190930192850191508083835b602083106118e35780518252601f1990920191602091820191016118c4565b6001836020036101000a038019825116818451168082178552505050505050905001925050506040516020818303038152906040526040518082805190602001908083835b602083106119475780518252601f199092019160209182019101611928565b51815160209384036101000a60001901801990921691161790526040805192909401829003822063ffffffff881660e060020a0283526004830152925160248083019650939450929083900301905081600087803b1580156119a857600080fd5b505af11580156119bc573d6000803e3d6000fd5b505050506040513d60208110156119d257600080fd5b505193925050505600a165627a7a723058208ef9b85e354362e763b686c76a244b0534617896074b71eaf195b04954ff318c0029";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_KILL = "kill";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_SAVE = "save";

    public static final String FUNC_GET = "get";

    public static final Event SAVED_EVENT = new Event("Saved", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
    ;

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
        _addresses.put("89354", "0x537ba136f99e0b1ad43a085785495e3859539ba3");
    }

    protected KioskStorage(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected KioskStorage(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
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

    public static RemoteCall<KioskStorage> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _config) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_config)));
        return deployRemoteCall(KioskStorage.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static RemoteCall<KioskStorage> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _config) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_config)));
        return deployRemoteCall(KioskStorage.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public List<SavedEventResponse> getSavedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(SAVED_EVENT, transactionReceipt);
        ArrayList<SavedEventResponse> responses = new ArrayList<SavedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            SavedEventResponse typedResponse = new SavedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._kioskId = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._location = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse._name = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse._timezone = (String) eventValues.getNonIndexedValues().get(3).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<SavedEventResponse> savedEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, SavedEventResponse>() {
            @Override
            public SavedEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(SAVED_EVENT, log);
                SavedEventResponse typedResponse = new SavedEventResponse();
                typedResponse.log = log;
                typedResponse._kioskId = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._location = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse._name = (String) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse._timezone = (String) eventValues.getNonIndexedValues().get(3).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<SavedEventResponse> savedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(SAVED_EVENT));
        return savedEventObservable(filter);
    }

    public RemoteCall<TransactionReceipt> save(String _kioskId, String _location, String _name, String _timezone) {
        final Function function = new Function(
                FUNC_SAVE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_kioskId), 
                new org.web3j.abi.datatypes.Utf8String(_location), 
                new org.web3j.abi.datatypes.Utf8String(_name), 
                new org.web3j.abi.datatypes.Utf8String(_timezone)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Tuple3<String, String, String>> get(String _kioskId) {
        final Function function = new Function(FUNC_GET, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_kioskId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteCall<Tuple3<String, String, String>>(
                new Callable<Tuple3<String, String, String>>() {
                    @Override
                    public Tuple3<String, String, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<String, String, String>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (String) results.get(2).getValue());
                    }
                });
    }

    public static KioskStorage load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new KioskStorage(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static KioskStorage load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new KioskStorage(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public interface KioskStorageEvent extends SmartContractEvent {
    }

    public static class SavedEventResponse implements KioskStorageEvent {
        public Log log;

        public String _kioskId;

        public String _location;

        public String _name;

        public String _timezone;
    }
}
